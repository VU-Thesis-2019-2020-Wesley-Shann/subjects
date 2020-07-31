package nappatfpr.com.newsblur.activity;

import android.os.Bundle;

import nappatfpr.com.newsblur.domain.SocialFeed;
import nappatfpr.com.newsblur.util.UIUtils;
import nl.vu.cs.s2group.nappa.*;

public class SocialFeedItemsList extends ItemsList {

	public static final String EXTRA_SOCIAL_FEED = "social_feed";

	private SocialFeed socialFeed;

	@Override
	protected void onCreate(Bundle bundle) {
		getLifecycle().addObserver(new NappaLifecycleObserver(this));
		socialFeed = (SocialFeed) getIntent().getSerializableExtra(EXTRA_SOCIAL_FEED);
		super.onCreate(bundle);
				
        UIUtils.setCustomActionBar(this, socialFeed.photoUrl, socialFeed.feedTitle);
	}

}
