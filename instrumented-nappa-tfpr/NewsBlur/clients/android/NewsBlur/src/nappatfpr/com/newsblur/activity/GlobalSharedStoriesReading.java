package nappatfpr.com.newsblur.activity;

import android.os.Bundle;
import android.view.Menu;

import nappatfpr.com.newsblur.R;
import nappatfpr.com.newsblur.util.UIUtils;
import nl.vu.cs.s2group.nappa.*;

public class GlobalSharedStoriesReading extends Reading {

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        getLifecycle().addObserver(new NappaLifecycleObserver(this));

        UIUtils.setCustomActionBar(this, R.drawable.ak_icon_global, getResources().getString(R.string.global_shared_stories_title));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.removeItem(R.id.menu_reading_markunread);
        return true;
    }
}
