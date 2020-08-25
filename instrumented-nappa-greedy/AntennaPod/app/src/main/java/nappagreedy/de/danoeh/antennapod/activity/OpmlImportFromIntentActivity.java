package nappagreedy.de.danoeh.antennapod.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import nappagreedy.de.danoeh.antennapod.core.preferences.UserPreferences;
import nl.vu.cs.s2group.nappa.*;

/**
 * Lets the user start the OPML-import process.
 */
public class OpmlImportFromIntentActivity extends OpmlImportBaseActivity {

    private static final String TAG = "OpmlImportFromIntentAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLifecycle().addObserver(new NappaLifecycleObserver(this));
        setTheme(UserPreferences.getTheme());
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith("/")) {
            uri = Uri.parse("file://" + uri.toString());
        } else {
            String extraText = getIntent().getStringExtra(Intent.EXTRA_TEXT);
            if(extraText != null) {
                uri = Uri.parse(extraText);
            }
        }
        importUri(uri);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("WESLEY_GRAPH_ACTIVITY", this.getClass().getSimpleName());
    }

    @Override
    protected boolean finishWhenCanceled() {
        return true;
    }

}
