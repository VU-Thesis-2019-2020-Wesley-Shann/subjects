package nappagreedy.com.newsblur.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import nappagreedy.com.newsblur.R;
import nappagreedy.com.newsblur.fragment.AddSocialFragment;
import nl.vu.cs.s2group.nappa.*;

public class AddSocial extends NbActivity {

	private FragmentManager fragmentManager;
	private String currentTag = "addSocialFragment";
	private AddSocialFragment addSocialFragment;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		getLifecycle().addObserver(new NappaLifecycleObserver(this));
		setContentView(R.layout.activity_addsocial);
		
		fragmentManager = getSupportFragmentManager();

		if (fragmentManager.findFragmentByTag(currentTag) == null) {
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			addSocialFragment = new AddSocialFragment();
			transaction.add(R.id.addsocial_container, addSocialFragment, currentTag);
			transaction.commit();
		}
		
		Button nextStep = (Button) findViewById(R.id.login_addsocial_nextstep);
		nextStep.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
                Intent i = new Intent(AddSocial.this, Main.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
				Nappa.notifyExtras(i.getExtras());
				startActivity(i);
			}
		});
		
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		switch (resultCode) {
		case AddTwitter.TWITTER_AUTHED:
			addSocialFragment.setTwitterAuthed();
		case AddFacebook.FACEBOOK_AUTHED:
			addSocialFragment.setFacebookAuthed();	
		}
	}
}
