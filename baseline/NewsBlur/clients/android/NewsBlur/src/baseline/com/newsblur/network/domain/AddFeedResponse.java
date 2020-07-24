package baseline.com.newsblur.network.domain;

import com.google.gson.annotations.SerializedName;

import baseline.com.newsblur.domain.Feed;

public class AddFeedResponse extends NewsBlurResponse {
    
    @SerializedName("feed")
    public Feed feed;
    
}
