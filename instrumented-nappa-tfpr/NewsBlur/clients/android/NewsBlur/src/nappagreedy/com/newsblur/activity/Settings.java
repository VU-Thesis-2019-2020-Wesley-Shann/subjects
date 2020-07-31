package nappagreedy.com.newsblur.activity;

import androidx.fragment.app.FragmentActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import nappagreedy.com.newsblur.fragment.SettingsFragment;
import nappagreedy.com.newsblur.util.PrefConstants;
import nappagreedy.com.newsblur.util.PrefsUtils;
import nappagreedy.com.newsblur.util.UIUtils;
import nl.vu.cs.s2group.nappa.*;

public class Settings extends FragmentActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getLifecycle().addObserver(new NappaLifecycleObserver(this));
        PrefsUtils.applyThemePreference(this);

        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        SettingsFragment fragment = new SettingsFragment();
        getFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();

        SharedPreferences prefs = getSharedPreferences(PrefConstants.PREFERENCES, 0);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences prefs = getSharedPreferences(PrefConstants.PREFERENCES, 0);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);   
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(PrefConstants.THEME)) {
            UIUtils.restartActivity(this);
        }
    }
}
