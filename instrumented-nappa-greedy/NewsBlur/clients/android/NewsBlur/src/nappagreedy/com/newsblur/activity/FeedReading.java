package nappagreedy.com.newsblur.activity;

import android.os.Bundle;

import nappagreedy.com.newsblur.domain.Feed;
import nappagreedy.com.newsblur.util.FeedUtils;
import nappagreedy.com.newsblur.util.UIUtils;

public class FeedReading extends Reading {

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);

        if (fs == null) {
            // if the activity got launch with a missing FeedSet, it will be in the process of cancelling
            return;
        }
        Feed feed = FeedUtils.dbHelper.getFeed(fs.getSingleFeed());
        if (feed == null) {
            // if this is somehow an intent so stale that the feed no longer exists, bail.
            finish();
            return;
        }

        UIUtils.setCustomActionBar(this, feed.faviconUrl, feed.title);
    }

}
