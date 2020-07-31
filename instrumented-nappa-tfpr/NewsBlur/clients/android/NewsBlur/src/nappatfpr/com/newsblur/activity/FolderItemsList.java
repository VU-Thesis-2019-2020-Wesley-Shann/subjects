package nappatfpr.com.newsblur.activity;

import android.os.Bundle;

import nappatfpr.com.newsblur.R;
import nappatfpr.com.newsblur.util.UIUtils;
import nl.vu.cs.s2group.nappa.*;

public class FolderItemsList extends ItemsList {

	public static final String EXTRA_FOLDER_NAME = "folderName";
	private String folderName;

	@Override
	protected void onCreate(Bundle bundle) {
		getLifecycle().addObserver(new NappaLifecycleObserver(this));
		folderName = getIntent().getStringExtra(EXTRA_FOLDER_NAME);

		super.onCreate(bundle);

        UIUtils.setCustomActionBar(this, R.drawable.g_icn_folder_rss, folderName);
	}

}
