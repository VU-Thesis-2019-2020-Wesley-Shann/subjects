package baseline.com.newsblur.activity;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.Window;

import baseline.com.newsblur.R;
import baseline.com.newsblur.fragment.LoginRegisterFragment;

public class Login extends FragmentActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
