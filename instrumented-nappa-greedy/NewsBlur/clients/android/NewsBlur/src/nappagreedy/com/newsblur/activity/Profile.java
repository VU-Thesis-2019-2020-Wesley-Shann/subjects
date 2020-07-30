package nappagreedy.com.newsblur.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import android.text.TextUtils;
import android.view.MenuItem;

import nappagreedy.com.newsblur.R;
import nappagreedy.com.newsblur.domain.UserDetails;
import nappagreedy.com.newsblur.fragment.ProfileDetailsFragment;
import nappagreedy.com.newsblur.network.APIManager;
import nappagreedy.com.newsblur.util.PrefsUtils;
import nl.vu.cs.s2group.nappa.*;

public class Profile extends NbActivity {

	private FragmentManager fragmentManager;
	private String detailsTag = "details";
	private APIManager apiManager;
	public static final String USER_ID = "user_id";
	private ProfileDetailsFragment detailsFragment;
	private ActivityDetailsPagerAdapter activityDetailsPagerAdapter;
	private String userId = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getLifecycle().addObserver(new NappaLifecycleObserver(this));
		setContentView(R.layout.activity_profile);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		apiManager = new APIManager(this);
        if (savedInstanceState == null) {
            userId = getIntent().getStringExtra(USER_ID);
        } else {
            userId = savedInstanceState.getString(USER_ID);
        }
		
		fragmentManager = getSupportFragmentManager();

		if (fragmentManager.findFragmentByTag(detailsTag) == null) {
			FragmentTransaction detailsTransaction = fragmentManager.beginTransaction();
			detailsFragment = new ProfileDetailsFragment();
			detailsFragment.setRetainInstance(true);
			detailsTransaction.add(R.id.profile_details, detailsFragment, detailsTag);
			detailsTransaction.commit();

            activityDetailsPagerAdapter = new ActivityDetailsPagerAdapter(fragmentManager, this);
            ViewPager activityDetailsPager = (ViewPager) findViewById(R.id.activity_details_pager);
            activityDetailsPager.setAdapter(activityDetailsPagerAdapter);

			new LoadUserTask().execute();
		} else {
			detailsFragment = (ProfileDetailsFragment) fragmentManager.findFragmentByTag(detailsTag);
		}
	}

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (userId != null) {
            outState.putString(USER_ID, userId);
        }
    }

    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);	
		}
	}

	private class LoadUserTask extends AsyncTask<Void, Void, Void> {
		private UserDetails user;

		@Override
		protected void onPreExecute() {
			if (TextUtils.isEmpty(userId)) {
				detailsFragment.setUser(Profile.this, PrefsUtils.getUserDetails(Profile.this), true);
			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			if (!TextUtils.isEmpty(userId)) {
				String intentUserId  = getIntent().getStringExtra(USER_ID);
				user = apiManager.getUser(intentUserId).user;
			} else {
				apiManager.updateUserProfile();
				user = PrefsUtils.getUserDetails(Profile.this);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (user != null && detailsFragment != null && activityDetailsPagerAdapter != null) {
				detailsFragment.setUser(Profile.this, user, TextUtils.isEmpty(userId));
                activityDetailsPagerAdapter.setUser(user);
			}
		}
	}
}
