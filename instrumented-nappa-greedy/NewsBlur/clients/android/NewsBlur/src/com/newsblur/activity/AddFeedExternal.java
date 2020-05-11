package com.newsblur.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.BindView;
import nl.vu.cs.s2group.*;

import com.newsblur.R;
import com.newsblur.fragment.AddFeedFragment;
import com.newsblur.util.UIUtils;
import com.newsblur.util.ViewUtils;
import com.newsblur.view.ProgressThrobber;

public class AddFeedExternal extends NbActivity implements AddFeedFragment.AddFeedProgressListener {

    @BindView(R.id.loading_throb) ProgressThrobber progressView;
    @BindView(R.id.progress_text) TextView progressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_addfeedexternal);
        ButterKnife.bind(this);

        progressView.setEnabled(!ViewUtils.isPowerSaveMode(this));
        progressView.setColors(UIUtils.getColor(this, R.color.refresh_1),
                               UIUtils.getColor(this, R.color.refresh_2),
                               UIUtils.getColor(this, R.color.refresh_3),
                               UIUtils.getColor(this, R.color.refresh_4));

        Intent intent = getIntent();
        Uri uri = intent.getData();
        
        com.newsblur.util.Log.d(this, "intent filter caught feed-like URI: " + uri);

		DialogFragment addFeedFragment = AddFeedFragment.newInstance(uri.toString(), uri.toString());
		addFeedFragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void addFeedStarted() {
        runOnUiThread(new Runnable() {
            public void run() {
                progressText.setText(R.string.adding_feed_progress);
                progressText.setVisibility(View.VISIBLE);
                progressView.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
	public void handleUpdate(int updateType) {
        ; // we don't care about anything but completion
    }

    @Override
    protected void onResume() {
        super.onResume();
        PrefetchingLib.setCurrentActivity(this);
    }
}
