package nappagreedy.com.ak.uobtimetable;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import nappagreedy.com.ak.uobtimetable.Utilities.Logging.Logger;
import nl.vu.cs.s2group.nappa.*;

/**
 * Activity which allows the user to configure settings
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new NappaLifecycleObserver(this));
        setContentView(R.layout.activity_settings);

        Logger.getInstance().debug("SettingsActivity", "onCreate");

        // Show back button in actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){

        Logger.getInstance().debug("SettingsActivity", "Saving state");

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onResume() {

        super.onResume();

        Logger.getInstance().debug("SettingsActivity", "OnResume");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
