package nappagreedy.com.newsblur.activity;

import android.os.Bundle;

import nappagreedy.com.newsblur.R;
import nappagreedy.com.newsblur.util.UIUtils;
import nl.vu.cs.s2group.nappa.*;

public class AllStoriesReading extends Reading {

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        getLifecycle().addObserver(new NappaLifecycleObserver(this));

        UIUtils.setCustomActionBar(this, R.drawable.ak_icon_allstories, getResources().getString(R.string.all_stories_title));
        setTitle(getResources().getString(R.string.all_stories_row_title));
    }

}
