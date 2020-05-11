package com.newsblur.activity;

import android.os.Bundle;

import com.newsblur.domain.SocialFeed;
import com.newsblur.util.UIUtils;

import nl.vu.cs.s2group.*;

public class SocialFeedItemsList extends ItemsList {

	public static final String EXTRA_SOCIAL_FEED = "social_feed";

	private SocialFeed socialFeed;

	@Override
	protected void onCreate(Bundle bundle) {
	    socialFeed = (SocialFeed) getIntent().getSerializableExtra(EXTRA_SOCIAL_FEED);
		super.onCreate(bundle);
				
        UIUtils.setCustomActionBar(this, socialFeed.photoUrl, socialFeed.feedTitle);
	}

    @Override
    protected void onResume() {
        super.onResume();
        PrefetchingLib.setCurrentActivity(this);
    }
}
