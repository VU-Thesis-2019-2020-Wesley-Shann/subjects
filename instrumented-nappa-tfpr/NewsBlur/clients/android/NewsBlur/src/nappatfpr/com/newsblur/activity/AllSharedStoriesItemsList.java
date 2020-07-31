package nappatfpr.com.newsblur.activity;

import android.os.Bundle;

import nappatfpr.com.newsblur.R;
import nappatfpr.com.newsblur.util.UIUtils;
import nl.vu.cs.s2group.nappa.*;

public class AllSharedStoriesItemsList extends ItemsList {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		getLifecycle().addObserver(new NappaLifecycleObserver(this));

		UIUtils.setCustomActionBar(this, R.drawable.ak_icon_blurblogs, getResources().getString(R.string.all_shared_stories_title));
	}

}
