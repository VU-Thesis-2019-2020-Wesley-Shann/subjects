package nappatfpr.com.newsblur.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;

import nappatfpr.com.newsblur.R;
import nappatfpr.com.newsblur.util.FeedUtils;
import nappatfpr.com.newsblur.util.PrefConstants;
import nappatfpr.com.newsblur.util.PrefsUtils;
import nl.vu.cs.s2group.nappa.*;
import nl.vu.cs.s2group.nappa.prefetch.PrefetchingStrategyType;

/**
 * The very first activity we launch. Checks to see if there is a user logged in yet and then
 * either loads the Main UI or a Login screen as needed.  Also responsible for warming up the
 * DB connection used by all other Activities.
 */
public class InitActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Nappa.init(this, PrefetchingStrategyType.STRATEGY_TFPR);
//        getLifecycle not available. doesn't seems to have requests here. It just selects the next activity
//        getLifecycle().addObserver(new NappaLifecycleObserver(this));

        setContentView(R.layout.activity_init);

        // do actual app launch after just a moment so the init screen smoothly loads
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... arg) {
                start();
                return null;
            }
        }.execute();

        nappatfpr.com.newsblur.util.Log.i(this, "cold launching version " + PrefsUtils.getVersion(this));

    }

    private void start() {
        // this is the first Activity launched; use it to init the global singletons in FeedUtils
        FeedUtils.offerInitContext(this);

        // now before there is any chance at all of an activity hitting the DB and crashing when it
        // cannot find new tables or columns right after an app upgrade, check to see if the DB
        // needs an upgrade
        upgradeCheck();

        // see if a user is already logged in; if so, jump to the Main activity
        preferenceCheck();
    }

    private void preferenceCheck() {
        SharedPreferences preferences = getSharedPreferences(PrefConstants.PREFERENCES, Context.MODE_PRIVATE);
        if (preferences.getString(PrefConstants.PREF_COOKIE, null) != null) {
            Intent mainIntent = new Intent(this, Main.class);
            Nappa.notifyExtras(mainIntent.getExtras());
            startActivity(mainIntent);
        } else {
            Intent loginIntent = new Intent(this, Login.class);
            Nappa.notifyExtras(loginIntent.getExtras());
            startActivity(loginIntent);
        }
    }

    private void upgradeCheck() {
        boolean upgrade = PrefsUtils.checkForUpgrade(this);
        if (upgrade) {
            FeedUtils.dbHelper.dropAndRecreateTables();
            // don't actually unset the upgrade flag, the sync service will do this same check and
            // update everything
        }
    }

}
