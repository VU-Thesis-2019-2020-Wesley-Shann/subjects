package baseline.com.newsblur.activity;

import android.os.Bundle;

import baseline.com.newsblur.R;
import baseline.com.newsblur.util.UIUtils;

public class FolderReading extends Reading {

    @Override
    protected void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);

        UIUtils.setCustomActionBar(this, R.drawable.g_icn_folder_rss, fs.getFolderName());
    }

}
