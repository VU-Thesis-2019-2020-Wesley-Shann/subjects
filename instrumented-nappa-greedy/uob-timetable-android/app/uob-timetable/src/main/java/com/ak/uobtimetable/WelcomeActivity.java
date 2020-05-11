package com.ak.uobtimetable;

import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ak.uobtimetable.Utilities.AndroidUtilities;

import nl.vu.cs.s2group.*;

/**
 * Splash screen shown when there is no saved preference data.
 */
public class WelcomeActivity extends AppCompatActivity {

    private Button btContinue;
    private TextView tvInfoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btContinue = (Button)findViewById(R.id.btContinue);
        tvInfoText = (TextView)findViewById(R.id.tvInfoText);

        // Hide the action bar, does nothing in this activity
        getSupportActionBar().hide();

        // Change the text if migrating from version 1.x
        MyApplication application = (MyApplication)getApplication();
        if (application.hadPrefDataOnLaunch()){
            tvInfoText.setText(R.string.text_welcome_migrate);
        }

        btContinue.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                boolean isConnected = AndroidUtilities.hasNetwork(WelcomeActivity.this);

                // If connected select course
                if (isConnected) {
                    Intent intent = new Intent(WelcomeActivity.this, CourseListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    PrefetchingLib.notifyExtras(intent.getExtras());startActivity(intent);
                }
                // Otherwise complain about network connectivity
                else {

                    AlertDialog d = new AlertDialog.Builder(WelcomeActivity.this)
                        .setPositiveButton(R.string.dialog_close, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setTitle(R.string.warning_net_connection)
                        .setMessage(R.string.net_required_first_run)
                        .create();
                    d.show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        PrefetchingLib.setCurrentActivity(this);
    }
}
