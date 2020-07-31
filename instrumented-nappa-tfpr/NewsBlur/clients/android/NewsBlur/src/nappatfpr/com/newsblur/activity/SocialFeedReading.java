package nappatfpr.com.newsblur.activity;

import android.os.Bundle;

import nappatfpr.com.newsblur.domain.SocialFeed;
import nappatfpr.com.newsblur.util.FeedUtils;
import nappatfpr.com.newsblur.util.UIUtils;
import nl.vu.cs.s2group.nappa.*;

public class SocialFeedReading extends Reading {

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        getLifecycle().addObserver(new NappaLifecycleObserver(this));
        SocialFeed socialFeed = FeedUtils.dbHelper.getSocialFeed(fs.getSingleSocialFeed().getKey());
        if (socialFeed == null) finish(); // don't open fatally stale intents
        UIUtils.setCustomActionBar(this, socialFeed.photoUrl, socialFeed.feedTitle);
    }

}
