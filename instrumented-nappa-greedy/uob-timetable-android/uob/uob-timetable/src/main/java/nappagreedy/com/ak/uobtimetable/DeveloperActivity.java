package nappagreedy.com.ak.uobtimetable;

import android.content.ClipData;
import android.content.ClipboardManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import nappagreedy.com.ak.uobtimetable.API.Models;
import nappagreedy.com.ak.uobtimetable.Notifications.SessionReminderNotifier;
import nappagreedy.com.ak.uobtimetable.Utilities.AndroidUtilities;
import nappagreedy.com.ak.uobtimetable.Utilities.Logging.Logger;
import nappagreedy.com.ak.uobtimetable.Utilities.Logging.MemoryLogger;
import nappagreedy.com.ak.uobtimetable.Utilities.SettingsManager;
import nl.vu.cs.s2group.nappa.*;

import java.util.List;

/**
 * Hidden activity which displays the application log.
 */
public class DeveloperActivity extends AppCompatActivity {

    private TextView tvLog;
    private Button btClearSettings;
    private Button btCopyLog;
    private Button btClearLog;
    private Button btRestart;
    private Button btSendNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new NappaLifecycleObserver(this));
        setContentView(R.layout.activity_developer);

        Logger.getInstance().debug("DeveloperActivity", "onCreate");

        // Show back button in actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get UI references
        tvLog = (TextView)findViewById(R.id.tvLog);
        btClearSettings = (Button)findViewById(R.id.btClearSettings);
        btCopyLog = (Button)findViewById(R.id.btCopyLog);
        btClearLog = (Button)findViewById(R.id.btClearLog);
        btRestart = (Button)findViewById(R.id.btRestart);
        btSendNotification = (Button)findViewById(R.id.btSendNotification);

        // Set initial values
        tvLog.setMovementMethod(new ScrollingMovementMethod());

        if (AndroidUtilities.isTabletLayout(this) == false)
            tvLog.setTextSize(12);

        btClearSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SettingsManager.getInstance(DeveloperActivity.this).clear();
                updateLog();
            }
        });

        btCopyLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MemoryLogger logger = (MemoryLogger)Logger.getInstance().getLogger("memory");

                ClipboardManager clipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("", logger.toString());
                clipboard.setPrimaryClip(clip);

                Toast toast = Toast.makeText(DeveloperActivity.this, "Copied log!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        btClearLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MemoryLogger logger = (MemoryLogger)Logger.getInstance().getLogger("memory");
                logger.clearEntries();

                Toast toast = Toast.makeText(DeveloperActivity.this, "Cleared log!", Toast.LENGTH_SHORT);
                toast.show();

                updateLog();
            }
        });

        btRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        btSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SettingsManager settings = new SettingsManager(getBaseContext());
                List<Models.DisplaySession> sessions = settings.getSessions();

                if (sessions.size() == 0)
                    return;

                Models.DisplaySession session = sessions.get((int)(Math.random() * sessions.size()));

                SessionReminderNotifier notifier = new SessionReminderNotifier(getBaseContext());
                notifier.showSessionReminder(session);
            }
        });

        // Set log view contents
        updateLog();
    }

    private void updateLog(){

        MemoryLogger memLogger = (MemoryLogger)Logger.getInstance().getLogger("memory");
        tvLog.setText(AndroidUtilities.fromHtml(memLogger.toHtml()));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){

        Logger.getInstance().debug("DeveloperActivity", "Saving state");

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onResume() {

        super.onResume();

        Logger.getInstance().debug("DeveloperActivity", "OnResume");
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
