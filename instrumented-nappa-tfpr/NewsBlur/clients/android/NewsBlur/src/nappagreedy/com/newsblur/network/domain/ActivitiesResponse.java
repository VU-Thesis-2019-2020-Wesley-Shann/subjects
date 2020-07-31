package nappagreedy.com.newsblur.network.domain;

import com.google.gson.annotations.SerializedName;
import nappagreedy.com.newsblur.domain.ActivityDetails;

/**
 * Response for /social/activities endpoint
 */
public class ActivitiesResponse extends NewsBlurResponse {

    @SerializedName("activities")
    public ActivityDetails[] activities;
}
