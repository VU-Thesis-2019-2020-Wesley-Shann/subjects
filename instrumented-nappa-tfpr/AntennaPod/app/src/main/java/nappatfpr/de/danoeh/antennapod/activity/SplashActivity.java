package nappatfpr.de.danoeh.antennapod.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ProgressBar;

import nappatfpr.de.danoeh.antennapod.R;
import nappatfpr.de.danoeh.antennapod.core.storage.PodDBAdapter;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import nl.vu.cs.s2group.nappa.*;
import nl.vu.cs.s2group.nappa.prefetch.PrefetchingStrategyType;

/**
 * Shows the AntennaPod logo while waiting for the main activity to start
 */
public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Nappa.init(this, PrefetchingStrategyType.STRATEGY_TFPR);
        getLifecycle().addObserver(new NappaLifecycleObserver(this));
        setContentView(R.layout.splash);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Drawable wrapDrawable = DrawableCompat.wrap(progressBar.getIndeterminateDrawable());
            DrawableCompat.setTint(wrapDrawable, 0xffffffff);
            progressBar.setIndeterminateDrawable(DrawableCompat.unwrap(wrapDrawable));
        } else {
            progressBar.getIndeterminateDrawable().setColorFilter(0xffffffff, PorterDuff.Mode.SRC_IN);
        }

        Completable.create(subscriber -> {
            // Trigger schema updates
            PodDBAdapter.getInstance().open();
            PodDBAdapter.getInstance().close();
            subscriber.onComplete();
        })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(() -> {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                Nappa.notifyExtras(intent.getExtras());
                startActivity(intent);
                finish();
            });
    }
}
