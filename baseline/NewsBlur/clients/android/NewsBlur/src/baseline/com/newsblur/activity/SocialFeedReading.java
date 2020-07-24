package baseline.com.newsblur.activity;

import android.os.Bundle;

import baseline.com.newsblur.domain.SocialFeed;
import baseline.com.newsblur.util.FeedUtils;
import baseline.com.newsblur.util.UIUtils;

public class SocialFeedReading extends Reading {

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
        SocialFeed socialFeed = FeedUtils.dbHelper.getSocialFeed(fs.getSingleSocialFeed().getKey());
        if (socialFeed == null) finish(); // don't open fatally stale intents
        UIUtils.setCustomActionBar(this, socialFeed.photoUrl, socialFeed.feedTitle);
    }

}
