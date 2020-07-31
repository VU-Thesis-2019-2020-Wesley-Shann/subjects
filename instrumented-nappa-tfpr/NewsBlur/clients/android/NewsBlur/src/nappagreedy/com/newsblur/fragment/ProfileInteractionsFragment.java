package nappagreedy.com.newsblur.fragment;

import android.content.Context;

import nappagreedy.com.newsblur.domain.ActivityDetails;
import nappagreedy.com.newsblur.domain.UserDetails;
import nappagreedy.com.newsblur.network.domain.InteractionsResponse;
import nappagreedy.com.newsblur.view.ActivityDetailsAdapter;
import nappagreedy.com.newsblur.view.InteractionsAdapter;

/**
 * Created by mark on 15/06/15.
 */
public class ProfileInteractionsFragment extends ProfileActivityDetailsFragment {

    @Override
    protected ActivityDetailsAdapter createAdapter(Context context, UserDetails user) {
        return new InteractionsAdapter(context, user);
    }

    @Override
    protected ActivityDetails[] loadActivityDetails(String id, int pageNumber) {
        InteractionsResponse interactionsResponse = apiManager.getInteractions(id, pageNumber);
        if (interactionsResponse != null) {
            return interactionsResponse.interactions;
        } else {
            return null;
        }
    }
}

