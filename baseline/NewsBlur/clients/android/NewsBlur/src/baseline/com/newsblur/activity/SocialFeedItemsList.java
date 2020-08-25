package baseline.com.newsblur.activity;

import android.os.Bundle;
import android.util.Log;

import baseline.com.newsblur.domain.SocialFeed;
import baseline.com.newsblur.util.UIUtils;

public class SocialFeedItemsList extends ItemsList {
	@Override
	protected void onResume() {
		super.onResume();
		Log.d("WESLEY_GRAPH_ACTIVITY", this.getClass().getSimpleName());
	}

	public static final String EXTRA_SOCIAL_FEED = "social_feed";

	private SocialFeed socialFeed;

	@Override
	protected void onCreate(Bundle bundle) {
	    socialFeed = (SocialFeed) getIntent().getSerializableExtra(EXTRA_SOCIAL_FEED);
		super.onCreate(bundle);
				
        UIUtils.setCustomActionBar(this, socialFeed.photoUrl, socialFeed.feedTitle);
	}

}
