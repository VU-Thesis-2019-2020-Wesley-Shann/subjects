package nappatfpr.appteam.nith.hillffair.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import nappatfpr.appteam.nith.hillffair.R;
import nappatfpr.appteam.nith.hillffair.adapters.SponsorAdapter;
import nappatfpr.appteam.nith.hillffair.application.SharedPref;
import nappatfpr.appteam.nith.hillffair.models.SponsorItem;
import nl.vu.cs.s2group.nappa.*;

import java.util.ArrayList;

public class SponsorActivity extends AppCompatActivity {

    RecyclerView rvSponsor;
    SponsorAdapter sponsorAdapter;
    Toolbar tbSponsor;
    ArrayList<SponsorItem> sponsorItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getLifecycle().addObserver(new NappaLifecycleObserver(this));
        SharedPref pref= new SharedPref(this);
        setTheme(pref.getThemeId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor);
        String BASE_URL="https://s3.ap-south-1.amazonaws.com/hillffair2016/images/";
        rvSponsor = (RecyclerView)findViewById(R.id.rvSponsor);

        sponsorItems = new ArrayList<>();

        sponsorItems.add(new SponsorItem("SkyCandle.in",BASE_URL+"sponsor-22.png"));
        sponsorItems.add(new SponsorItem("Board Of School Education, H.P.",BASE_URL+"sponsor-16.png"));
        sponsorItems.add(new SponsorItem("Tata Shaktee",BASE_URL+"sponsor-17.png"));
        sponsorItems.add(new SponsorItem("Cad Desk",BASE_URL+"sponsor-18.png"));
        sponsorItems.add(new SponsorItem("HPSEDC",BASE_URL+"sponsor-19.png"));
        sponsorItems.add(new SponsorItem("Ratan Jewellers",BASE_URL+"sponsor-20.png"));
        sponsorItems.add(new SponsorItem("Chankya The Guru",BASE_URL+"sponsor-21.png"));
        sponsorItems.add(new SponsorItem("L'OREAL",BASE_URL+"sponsor-3.png"));
        sponsorItems.add(new SponsorItem("Made Easy",BASE_URL+"sponsor-4.png"));

        sponsorAdapter = new SponsorAdapter(sponsorItems,SponsorActivity.this);
        rvSponsor.setAdapter(sponsorAdapter);

        tbSponsor = (Toolbar)findViewById(R.id.tbSponsor);
        tbSponsor.setTitle("Our Sponsors");
        setSupportActionBar(tbSponsor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager lvmanager = new LinearLayoutManager(this);
        lvmanager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSponsor.setLayoutManager(lvmanager);
    }
}
