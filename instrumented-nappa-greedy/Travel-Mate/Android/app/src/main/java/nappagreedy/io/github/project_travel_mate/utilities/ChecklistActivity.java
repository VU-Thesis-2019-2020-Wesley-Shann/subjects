package nappagreedy.io.github.project_travel_mate.utilities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import java.util.Objects;

import butterknife.ButterKnife;
import nappagreedy.io.github.project_travel_mate.R;
import nl.vu.cs.s2group.nappa.*;

public class ChecklistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new NappaLifecycleObserver(this));
        setContentView(R.layout.activity_check_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = ChecklistFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.checklist_root_layout, fragment).commit();

        setTitle(getResources().getString(R.string.text_checklist));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //menu item selected
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ChecklistActivity.class);
        return intent;
    }
}