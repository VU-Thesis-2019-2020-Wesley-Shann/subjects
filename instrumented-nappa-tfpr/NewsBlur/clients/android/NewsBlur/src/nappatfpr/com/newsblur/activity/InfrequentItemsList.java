package nappatfpr.com.newsblur.activity;

import android.os.Bundle;
import android.view.MenuItem;

import nappatfpr.com.newsblur.R;
import nappatfpr.com.newsblur.fragment.InfrequentCutoffDialogFragment;
import nappatfpr.com.newsblur.fragment.InfrequentCutoffDialogFragment.InfrequentCutoffChangedListener;
import nappatfpr.com.newsblur.util.FeedUtils;
import nappatfpr.com.newsblur.util.PrefsUtils;
import nappatfpr.com.newsblur.util.UIUtils;
import nl.vu.cs.s2group.nappa.*;

public class InfrequentItemsList extends ItemsList implements InfrequentCutoffChangedListener {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
        getLifecycle().addObserver(new NappaLifecycleObserver(this));

        UIUtils.setCustomActionBar(this, R.drawable.ak_icon_allstories, getResources().getString(R.string.infrequent_title));
	}

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_infrequent_cutoff) {
			InfrequentCutoffDialogFragment dialog = InfrequentCutoffDialogFragment.newInstance(PrefsUtils.getInfrequentCutoff(this));
			dialog.show(getSupportFragmentManager(), InfrequentCutoffDialogFragment.class.getName());
			return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void infrequentCutoffChanged(int newValue) {
        PrefsUtils.setInfrequentCutoff(this, newValue);
        FeedUtils.dbHelper.clearInfrequentSession();
        restartReadingSession();
    }

}
