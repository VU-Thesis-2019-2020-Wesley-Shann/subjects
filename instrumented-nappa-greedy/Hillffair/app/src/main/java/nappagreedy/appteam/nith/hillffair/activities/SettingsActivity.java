package nappagreedy.appteam.nith.hillffair.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import nappagreedy.appteam.nith.hillffair.R;
import nappagreedy.appteam.nith.hillffair.application.SharedPref;

public class SettingsActivity extends AppCompatActivity {

    private TextView intro,theme;
    private SharedPref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref= new SharedPref(this);
        setTheme(pref.getThemeId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        intro=(TextView)findViewById(R.id.app_intro);
        theme=(TextView)findViewById(R.id.change_theme);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(SettingsActivity.this,WelcomeActivity.class);
                in.putExtra("settings_call",true);

                startActivity(in);
            }
        });

        theme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(SettingsActivity.this,ThemeSelectionActivity.class);
                in.putExtra("settings_call",true);

                startActivity(in);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            Intent in=new Intent(SettingsActivity.this,HomeActivity.class);
            overridePendingTransition(0,0);
            startActivity(in);
            finish();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent in=new Intent(SettingsActivity.this,HomeActivity.class);
        overridePendingTransition(0,0);
        startActivity(in);
        finish();
    }

}
