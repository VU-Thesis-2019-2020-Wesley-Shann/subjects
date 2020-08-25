package baseline.com.newsblur.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import baseline.com.newsblur.R;
import baseline.com.newsblur.fragment.InfrequentCutoffDialogFragment;
import baseline.com.newsblur.fragment.InfrequentCutoffDialogFragment.InfrequentCutoffChangedListener;
import baseline.com.newsblur.util.FeedUtils;
import baseline.com.newsblur.util.PrefsUtils;
import baseline.com.newsblur.util.UIUtils;

public class InfrequentItemsList extends ItemsList implements InfrequentCutoffChangedListener {
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("WESLEY_GRAPH_ACTIVITY", this.getClass().getSimpleName());
    }

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
