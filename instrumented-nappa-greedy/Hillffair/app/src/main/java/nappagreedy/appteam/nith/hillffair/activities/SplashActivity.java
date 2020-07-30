package nappagreedy.appteam.nith.hillffair.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;

import nappagreedy.appteam.nith.hillffair.R;
import nl.vu.cs.s2group.nappa.*;
import nl.vu.cs.s2group.nappa.prefetch.PrefetchingStrategyType;

public class SplashActivity extends AppCompatActivity {
    private static final long TIME_SPLASH =1500 ;
    private ImageView image_splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Nappa.init(this, PrefetchingStrategyType.STRATEGY_GREEDY_VISIT_FREQUENCY_AND_TIME);
        getLifecycle().addObserver(new NappaLifecycleObserver(this));
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        image_splash = (ImageView) findViewById(R.id.image_spalsh);
        Target<GlideDrawable> into = Glide.with(SplashActivity.this).load(R.drawable.splash_new2).into(image_splash);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                Nappa.notifyExtras(intent.getExtras());
                startActivity(intent);
                  finish();
            }
        },TIME_SPLASH);

    }

}

