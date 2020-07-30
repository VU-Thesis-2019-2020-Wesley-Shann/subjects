package nappagreedy.com.newsblur.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import nappagreedy.com.newsblur.R;
import nappagreedy.com.newsblur.util.PrefConstants;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager preferenceManager = getPreferenceManager();
        preferenceManager.setSharedPreferencesName(PrefConstants.PREFERENCES);
        addPreferencesFromResource(R.xml.activity_settings);
    }

}
