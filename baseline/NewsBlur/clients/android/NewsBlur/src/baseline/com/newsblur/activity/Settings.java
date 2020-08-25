package baseline.com.newsblur.activity;

import androidx.fragment.app.FragmentActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import baseline.com.newsblur.fragment.SettingsFragment;
import baseline.com.newsblur.util.PrefConstants;
import baseline.com.newsblur.util.PrefsUtils;
import baseline.com.newsblur.util.UIUtils;

public class Settings extends FragmentActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("WESLEY_GRAPH_ACTIVITY", this.getClass().getSimpleName());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
