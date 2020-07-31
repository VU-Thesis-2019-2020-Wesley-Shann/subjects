package nappatfpr.com.newsblur.activity;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.Window;

import nappatfpr.com.newsblur.R;
import nappatfpr.com.newsblur.fragment.LoginProgressFragment;
import nappatfpr.com.newsblur.util.PrefsUtils;
import nl.vu.cs.s2group.nappa.*;

public class LoginProgress extends FragmentActivity {

	private FragmentManager fragmentManager;
	private String currentTag = "fragment";

	@Override
	protected void onCreate(Bundle bundle) {
		getLifecycle().addObserver(new NappaLifecycleObserver(this));
		PrefsUtils.applyThemePreference(this);

		super.onCreate(bundle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_loginprogress);
		
		fragmentManager = getSupportFragmentManager();
		
		if (fragmentManager.findFragmentByTag(currentTag ) == null) {
			String username = getIntent().getStringExtra("username");
			String password = getIntent().getStringExtra("password");
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			LoginProgressFragment login = LoginProgressFragment.getInstance(username, password);
			transaction.add(R.id.login_progress_container, login, currentTag);
			transaction.commit();
		}
	}
	
}
