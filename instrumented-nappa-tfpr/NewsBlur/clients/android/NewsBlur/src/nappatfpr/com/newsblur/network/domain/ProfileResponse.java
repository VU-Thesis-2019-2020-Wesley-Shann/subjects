package nappatfpr.com.newsblur.network.domain;

import com.google.gson.annotations.SerializedName;
import nappatfpr.com.newsblur.domain.ActivityDetails;
import nappatfpr.com.newsblur.domain.UserDetails;

public class ProfileResponse extends NewsBlurResponse {
	
	@SerializedName("user_profile")
	public UserDetails user;
	
	@SerializedName("activities")
	public ActivityDetails[] activities;
	
}
