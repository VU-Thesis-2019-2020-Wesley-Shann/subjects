package baseline.appteam.nith.hillffair.Notification;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;


import java.util.List;

import baseline.appteam.nith.hillffair.R;
import baseline.appteam.nith.hillffair.activities.Home_posts_gns;
import baseline.appteam.nith.hillffair.adapters.Notification;
import baseline.appteam.nith.hillffair.application.SharedPref;

public class NotificationActivity extends AppCompatActivity {
DbHelper dbHandler;

    List<Home_posts_gns> arrayList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPref pref= new SharedPref(this);
        setTheme(pref.getThemeId());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notifications");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dbHandler=new DbHelper(this);
        arrayList=dbHandler.gethomedata();
        Log.v("aa",""+arrayList);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.activity_notification_listview);
        final Notification notification = new Notification(arrayList,NotificationActivity.this);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(notification);
        recyclerView.addOnItemTouchListener(new OnItemTouchListener(NotificationActivity.this, new OnItemTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int db_position) {
                //  int  position = db_position+1;
                int n = arrayList.size();
                int pos=n-db_position;
                Home_posts_gns home_post2 = arrayList.get(db_position);
                String id = home_post2.getNotification_id();
                Intent expand = new Intent(getApplicationContext(), Notification2.class);
                Log.d("afasdf","intent_putextrats"+id+"g12112ddddd"+db_position);
                expand.putExtra("id",id);
                 startActivity(expand);

            }
        }));

    }

}