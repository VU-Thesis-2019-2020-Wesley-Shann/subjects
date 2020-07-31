package nappatfpr.appteam.nith.hillffair.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import nappatfpr.appteam.nith.hillffair.R;
import nappatfpr.appteam.nith.hillffair.application.SharedPref;
import nl.vu.cs.s2group.nappa.*;

public class ThemeSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    private int themeVal;
    ImageView batman, superman, hulk, wonderwoman, flash, captain;
    Context context;
    boolean settings_call=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new NappaLifecycleObserver(this));
        setContentView(R.layout.activity_theme_selection);
        init();
        context=this;

        settings_call=getIntent().getBooleanExtra("settings_call",false);

        setRoundImage(batman,R.drawable.batman_btn);
        setRoundImage(superman,R.drawable.superman_btn);
        setRoundImage(hulk,R.drawable.hulk);
        setRoundImage(wonderwoman,R.drawable.wonderwoman_btn);
        setRoundImage(captain,R.drawable.captain);
        setRoundImage(flash,R.drawable.flash);

    }

    void init() {
        batman = (ImageView) findViewById(R.id.batman);
        superman = (ImageView) findViewById(R.id.superman);
        hulk = (ImageView) findViewById(R.id.hulk);
        wonderwoman = (ImageView) findViewById(R.id.wonderwoman);
        flash = (ImageView) findViewById(R.id.flash);
        captain = (ImageView) findViewById(R.id.captain);
        batman.setOnClickListener(this);
        superman.setOnClickListener(this);
        hulk.setOnClickListener(this);
        wonderwoman.setOnClickListener(this);
        flash.setOnClickListener(this);
        captain.setOnClickListener(this);

    }

    void setRoundImage(ImageView view,int id){

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),id);
        RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCornerRadius(2.0f);
        roundedBitmapDrawable.setCircular(true);
        view.setImageDrawable(roundedBitmapDrawable);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.batman:
                themeVal = R.style.batman;
                break;
            case R.id.flash:
                themeVal = R.style.flash;
                break;
            case R.id.hulk:
                themeVal = R.style.hulk;
                break;
            case R.id.wonderwoman:
                themeVal = R.style.wonderwoman;
                break;
            case R.id.superman:
                themeVal = R.style.superman;
                break;
            case R.id.captain:
                themeVal = R.style.captainamerica;
                break;

        }
        savetoSharedPref();

        if(settings_call==false) {
            Intent intent1 = new Intent(ThemeSelectionActivity.this, LoginActivity.class);
            Nappa.notifyExtras(intent1.getExtras());
            startActivity(intent1);
        }
        else {
            Intent intent = new Intent(ThemeSelectionActivity.this, SettingsActivity.class);
            Nappa.notifyExtras(intent.getExtras());
            startActivity(intent);
        }

        finish();
    }
    
    void savetoSharedPref(){
        SharedPref sharedPref = new SharedPref(context);
        sharedPref.setThemeId(themeVal);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(settings_call){
            Intent in=new Intent(ThemeSelectionActivity.this,SettingsActivity.class);
            overridePendingTransition(0,0);
            Nappa.notifyExtras(in.getExtras());
            startActivity(in);
            finish();
        }

    }

}
