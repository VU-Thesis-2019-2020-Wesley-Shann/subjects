package nappatfpr.com.newsblur.fragment;

import android.content.Context;

import nappatfpr.com.newsblur.domain.ActivityDetails;
import nappatfpr.com.newsblur.domain.UserDetails;
import nappatfpr.com.newsblur.network.domain.ActivitiesResponse;
import nappatfpr.com.newsblur.view.ActivitiesAdapter;
import nappatfpr.com.newsblur.view.ActivityDetailsAdapter;

/**
 * Created by mark on 15/06/15.
 */
public class ProfileActivitiesFragment extends ProfileActivityDetailsFragment {

    @Override
    protected ActivityDetailsAdapter createAdapter(Context context, UserDetails user) {
        return new ActivitiesAdapter(context, user);
    }

    @Override
    protected ActivityDetails[] loadActivityDetails(String id, int pageNumber) {
        ActivitiesResponse activitiesResponse = apiManager.getActivities(id, pageNumber);
        if (activitiesResponse != null) {
            return activitiesResponse.activities;
        } else {
            return new ActivityDetails[0];
        }
    }
}
