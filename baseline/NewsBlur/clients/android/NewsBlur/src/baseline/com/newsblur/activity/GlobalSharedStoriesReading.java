package baseline.com.newsblur.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import baseline.com.newsblur.R;
import baseline.com.newsblur.util.UIUtils;

public class GlobalSharedStoriesReading extends Reading {
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("WESLEY_GRAPH_ACTIVITY", this.getClass().getSimpleName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);

        UIUtils.setCustomActionBar(this, R.drawable.ak_icon_global, getResources().getString(R.string.global_shared_stories_title));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.removeItem(R.id.menu_reading_markunread);
        return true;
    }
}
