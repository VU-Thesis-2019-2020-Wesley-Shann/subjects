package baseline.com.newsblur.activity;

import android.os.Bundle;
import android.util.Log;

import baseline.com.newsblur.R;
import baseline.com.newsblur.util.UIUtils;

public class AllSharedStoriesItemsList extends ItemsList {
	@Override
	protected void onResume() {
		super.onResume();
		Log.d("WESLEY_GRAPH_ACTIVITY", this.getClass().getSimpleName());
	}

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

        UIUtils.setCustomActionBar(this, R.drawable.ak_icon_blurblogs, getResources().getString(R.string.all_shared_stories_title));
	}

}
