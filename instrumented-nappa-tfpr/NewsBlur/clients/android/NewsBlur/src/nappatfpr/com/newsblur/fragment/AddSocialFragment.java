package nappatfpr.com.newsblur.fragment;

import java.util.HashSet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import nappatfpr.com.newsblur.R;
import nappatfpr.com.newsblur.activity.AddFacebook;
import nappatfpr.com.newsblur.activity.AddTwitter;
import nappatfpr.com.newsblur.network.APIManager;
import nl.vu.cs.s2group.nappa.*;

public class AddSocialFragment extends Fragment {

	private APIManager apiManager;
	private View parentView;
	
	HashSet<String> categoriesToAdd = new HashSet<String>();
	private LinearLayout twitterButton, facebookButton;
	private CheckBox autofollow;
	private boolean twitterAuthed, facebookAuthed;
	private TextView twitterText;
	private TextView facebookText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
		apiManager = new APIManager(getActivity());
	}
	
	public void setTwitterAuthed() {
		twitterAuthed = true;
		authCheck();
	}
	
	public void setFacebookAuthed() {
		facebookAuthed = true;
		authCheck();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.fragment_addsocial, null);
		
		twitterButton = (LinearLayout) parentView.findViewById(R.id.addsocial_twitter);
		twitterText = (TextView) parentView.findViewById(R.id.addsocial_twitter_text);
		
		twitterButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getActivity(), AddTwitter.class);
				Nappa.notifyExtras(i.getExtras());
				startActivityForResult(i, 0);
			}			
		});
		facebookButton = (LinearLayout) parentView.findViewById(R.id.addsocial_facebook);
		
		facebookButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getActivity(), AddFacebook.class);
				Nappa.notifyExtras(i.getExtras());
				startActivityForResult(i, 0);
			}			
		});
		

		authCheck();
		
		facebookText = (TextView) parentView.findViewById(R.id.addsocial_facebook_text);
		
		autofollow = (CheckBox) parentView.findViewById(R.id.addsocial_autofollow_checkbox);
		autofollow.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, final boolean checked) {
				new AsyncTask<Void, Void, Boolean>() {
					@Override
					protected Boolean doInBackground(Void... params) {
						return apiManager.setAutoFollow(checked);
					}
				}.execute();
			}
		});
		
		return parentView;
	}

	private void authCheck() {
		if (twitterAuthed) {
			twitterText.setText("Added Twitter friends!");
			twitterButton.setEnabled(false);
		}
		
		if (facebookAuthed) {
			facebookText.setText("Added Facebook friends!");
			facebookButton.setEnabled(false);
		}
	}


}