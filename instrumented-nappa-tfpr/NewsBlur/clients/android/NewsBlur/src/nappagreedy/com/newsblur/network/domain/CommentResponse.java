package nappagreedy.com.newsblur.network.domain;

import com.google.gson.annotations.SerializedName;

import nappagreedy.com.newsblur.domain.Comment;
import nappagreedy.com.newsblur.domain.UserProfile;

/**
 * API response binding for APIs that vend an updated Comment object.
 */
public class CommentResponse extends NewsBlurResponse {
    
    @SerializedName("comment")
    public Comment comment;
    
    @SerializedName("user_profiles")
    public UserProfile[] users;
    
}
