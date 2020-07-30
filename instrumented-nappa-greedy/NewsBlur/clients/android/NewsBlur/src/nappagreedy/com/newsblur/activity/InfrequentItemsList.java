package nappagreedy.com.newsblur.activity;

import android.os.Bundle;
import android.view.MenuItem;

import nappagreedy.com.newsblur.R;
import nappagreedy.com.newsblur.fragment.InfrequentCutoffDialogFragment;
import nappagreedy.com.newsblur.fragment.InfrequentCutoffDialogFragment.InfrequentCutoffChangedListener;
import nappagreedy.com.newsblur.util.FeedUtils;
import nappagreedy.com.newsblur.util.PrefsUtils;
import nappagreedy.com.newsblur.util.UIUtils;

public class InfrequentItemsList extends ItemsList implements InfrequentCutoffChangedListener {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);

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
