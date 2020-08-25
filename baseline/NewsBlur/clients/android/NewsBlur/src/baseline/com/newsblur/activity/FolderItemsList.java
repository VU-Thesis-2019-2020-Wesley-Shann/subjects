package baseline.com.newsblur.activity;

import android.os.Bundle;
import android.util.Log;

import baseline.com.newsblur.R;
import baseline.com.newsblur.util.UIUtils;

public class FolderItemsList extends ItemsList {
	@Override
	protected void onResume() {
		super.onResume();
		Log.d("WESLEY_GRAPH_ACTIVITY", this.getClass().getSimpleName());
	}

	public static final String EXTRA_FOLDER_NAME = "folderName";
	private String folderName;

	@Override
	protected void onCreate(Bundle bundle) {
		folderName = getIntent().getStringExtra(EXTRA_FOLDER_NAME);

		super.onCreate(bundle);

        UIUtils.setCustomActionBar(this, R.drawable.g_icn_folder_rss, folderName);
	}

}
