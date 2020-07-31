package nappatfpr.com.newsblur.activity;

import android.os.Bundle;

import nappatfpr.com.newsblur.R;
import nappatfpr.com.newsblur.util.UIUtils;
import nl.vu.cs.s2group.nappa.*;

public class SavedStoriesReading extends Reading {

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        getLifecycle().addObserver(new NappaLifecycleObserver(this));

        String title = getResources().getString(R.string.saved_stories_title);
        if (fs.getSingleSavedTag() != null) {
            title = title + " - " + fs.getSingleSavedTag();
        }
        UIUtils.setCustomActionBar(this, R.drawable.clock, title);
    }

}
