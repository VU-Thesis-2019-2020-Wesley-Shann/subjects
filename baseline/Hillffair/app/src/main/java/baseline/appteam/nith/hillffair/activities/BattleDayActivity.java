package baseline.appteam.nith.hillffair.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import baseline.appteam.nith.hillffair.R;
import baseline.appteam.nith.hillffair.adapters.BattleDayAdapter;
import baseline.appteam.nith.hillffair.application.SharedPref;
import baseline.appteam.nith.hillffair.models.BattleDayItem;
import baseline.appteam.nith.hillffair.models.BattleDayModel;
import baseline.appteam.nith.hillffair.utilities.APIINTERFACE;
import baseline.appteam.nith.hillffair.utilities.RecyclerItemClickListener;
import baseline.appteam.nith.hillffair.utilities.Utils;

import java.util.ArrayList;

import nl.vu.cs.s2group.nappa.nappaexperimentation.MetricNetworkRequestExecutionTime;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BattleDayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar bar;
    private ArrayList<BattleDayItem> list=new ArrayList<>();
    private BattleDayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPref pref= new SharedPref(this);
        setTheme(pref.getThemeId());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Toolbar toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        LinearLayoutManager manager=new LinearLayoutManager(BattleDayActivity.this);
        recyclerView.setLayoutManager(manager);
        adapter=new BattleDayAdapter(BattleDayActivity.this);
        recyclerView.setAdapter(adapter);
        bar=(ProgressBar)findViewById(R.id.progress);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i=new Intent(BattleDayActivity.this,ClubActivity.class);
                i.putExtra("name",list.get(position).getName());
                i.putExtra("id",list.get(position).getId());
                i.putExtra("battleday",true);

                startActivity(i);
            }
        }));

        bar.setVisibility(View.VISIBLE);
        retrofit();

    }

    public void retrofit(){

        APIINTERFACE apiservice= Utils.getRetrofitService();
        Call<BattleDayModel> call=apiservice.getSpecialEvents();
        long sentRequestAtMillis = System.currentTimeMillis();

        call.enqueue(new Callback<BattleDayModel>() {
            @Override
            public void onResponse(Call<BattleDayModel> call, Response<BattleDayModel> response) {
                long receivedResponseAtMillis = System.currentTimeMillis();
                MetricNetworkRequestExecutionTime.log(response.raw(), sentRequestAtMillis, receivedResponseAtMillis, false);
                bar.setVisibility(View.GONE);

                BattleDayModel model=response.body();
                int status=response.code();

                if(model!=null && response.isSuccessful()){
                   recyclerView.setVisibility(View.VISIBLE);

                    list=model.getEvents();
                    adapter.refresh(list);

                }else{
                    Toast.makeText(BattleDayActivity.this,"Some error occurred!!",Toast.LENGTH_SHORT).show();
                }

              }

            @Override
            public void onFailure(Call<BattleDayModel> call, Throwable t) {
                bar.setVisibility(View.GONE);
                Toast.makeText(BattleDayActivity.this,"Some error occurred!!",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
