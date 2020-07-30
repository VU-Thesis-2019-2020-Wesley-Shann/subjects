package baseline.com.newsblur.activity;

import android.os.Bundle;

import baseline.com.newsblur.R;
import baseline.com.newsblur.util.UIUtils;

public class GlobalSharedStoriesItemsList extends ItemsList {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

        UIUtils.setCustomActionBar(this, R.drawable.ak_icon_global, getResources().getString(R.string.global_shared_stories_title));
	}

}
