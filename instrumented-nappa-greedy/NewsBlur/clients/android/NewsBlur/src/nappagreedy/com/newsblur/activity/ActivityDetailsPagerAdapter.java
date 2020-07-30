package nappagreedy.com.newsblur.activity;

import android.content.res.Resources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import nappagreedy.com.newsblur.R;
import nappagreedy.com.newsblur.domain.UserDetails;
import nappagreedy.com.newsblur.fragment.ProfileActivitiesFragment;
import nappagreedy.com.newsblur.fragment.ProfileActivityDetailsFragment;
import nappagreedy.com.newsblur.fragment.ProfileInteractionsFragment;

/**
 * Created by mark on 15/06/15.
 */
public class ActivityDetailsPagerAdapter extends FragmentPagerAdapter {

    private final ProfileActivityDetailsFragment interactionsFragment;
    private final ProfileActivityDetailsFragment activitiesFragment;
    private final Profile profile;

    public ActivityDetailsPagerAdapter(FragmentManager fragmentManager, Profile profile) {
        super(fragmentManager);

        this.profile = profile;

        interactionsFragment = new ProfileInteractionsFragment();
        interactionsFragment.setRetainInstance(true);
        activitiesFragment = new ProfileActivitiesFragment();
        activitiesFragment.setRetainInstance(true);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return interactionsFragment;
        } else {
            return activitiesFragment;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Resources resources = profile.getResources();
        if (position == 0) {
            return resources.getString(R.string.profile_recent_interactions);
        } else {
            return resources.getString(R.string.profile_recent_actvity);
        }
    }

    public void setUser(UserDetails user) {
        interactionsFragment.setUser(profile, user);
        activitiesFragment.setUser(profile, user);
    }
}
