package baseline.com.newsblur.activity;

import android.os.Bundle;

import baseline.com.newsblur.R;
import baseline.com.newsblur.util.UIUtils;

public class AllStoriesReading extends Reading {

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);

        UIUtils.setCustomActionBar(this, R.drawable.ak_icon_allstories, getResources().getString(R.string.all_stories_title));
        setTitle(getResources().getString(R.string.all_stories_row_title));
    }

}
