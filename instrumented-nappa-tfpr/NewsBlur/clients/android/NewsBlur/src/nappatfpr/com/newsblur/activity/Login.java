package nappatfpr.com.newsblur.activity;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.Window;

import nappatfpr.com.newsblur.R;
import nappatfpr.com.newsblur.fragment.LoginRegisterFragment;
import nl.vu.cs.s2group.nappa.*;

public class Login extends FragmentActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new NappaLifecycleObserver(this));

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        FragmentManager fragmentManager = getSupportFragmentManager();
        
        if (fragmentManager.findFragmentByTag(LoginRegisterFragment.class.getName()) == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            LoginRegisterFragment login = new LoginRegisterFragment();
            transaction.add(R.id.login_container, login, LoginRegisterFragment.class.getName());
            transaction.commit();
        }
    }

}
