package baseline.com.newsblur.activity;

import android.os.Bundle;
import android.util.Log;

import baseline.com.newsblur.R;
import baseline.com.newsblur.util.UIUtils;

public class SavedStoriesItemsList extends ItemsList {
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("WESLEY_GRAPH_ACTIVITY", this.getClass().getSimpleName());
    }

    @Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

        String title = getResources().getString(R.string.saved_stories_title);
        if (fs.getSingleSavedTag() != null) {
            title = title + " - " + fs.getSingleSavedTag();
        }
        UIUtils.setCustomActionBar(this, R.drawable.clock, title);
	}

}
