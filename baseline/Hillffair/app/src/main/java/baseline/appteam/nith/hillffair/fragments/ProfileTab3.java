package baseline.appteam.nith.hillffair.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import baseline.appteam.nith.hillffair.R;
import baseline.appteam.nith.hillffair.adapters.CardAdapter;
import baseline.appteam.nith.hillffair.application.SharedPref;
import baseline.appteam.nith.hillffair.models.NewsfeedModel;
import baseline.appteam.nith.hillffair.models.NewsfeedModel2;
import baseline.appteam.nith.hillffair.utilities.Utils;

import java.util.ArrayList;

import nl.vu.cs.s2group.nappa.nappaexperimentation.MetricNetworkRequestExecutionTime;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Aditya on 9/13/2016.
 */
public class ProfileTab3 extends Fragment {

    private static final String USER_POST ="post" ;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CardAdapter adapter;
    private ArrayList<NewsfeedModel2> list=new ArrayList<>();
    private TextView noData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.profile_tab3_fragment, container, false);
        noData= (TextView) v.findViewById(R.id.no_data_textview);
        recyclerView = (RecyclerView) v.findViewById(R.id.list);
        progressBar = (ProgressBar) v.findViewById(R.id.progress);
        adapter = new CardAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        final SharedPref sharedPref=new SharedPref(getActivity());
        Log.d("id",sharedPref.getUserId());
        if(savedInstanceState==null){
            getData(1,sharedPref.getUserId());
            progressBar.setVisibility(View.VISIBLE);
        }

        else {
            if(savedInstanceState.getParcelableArrayList(USER_POST)!=null)
            {
                recyclerView.setVisibility(View.VISIBLE);
                list=savedInstanceState.getParcelableArrayList(USER_POST);
                adapter.refresh(list);
            }
            else {
                noData.setVisibility(View.VISIBLE);
                noData.setText("No Post Uploaded");
            }

        }
        return v;
    }


    private void getData(int from, String id) {
        Call<NewsfeedModel> getUserNewsFeed = Utils.getRetrofitService().getAllUserNews(""+from, id);
        long sentRequestAtMillis = System.currentTimeMillis();
        getUserNewsFeed.enqueue(new Callback<NewsfeedModel>() {
            @Override
            public void onResponse(Call<NewsfeedModel> call, Response<NewsfeedModel> response) {
                long receivedResponseAtMillis = System.currentTimeMillis();
                MetricNetworkRequestExecutionTime.log(response.raw(), sentRequestAtMillis, receivedResponseAtMillis);
                NewsfeedModel data = response.body();

                if (data != null && response.isSuccessful()) {
                    if (data.isSuccess()) {
                        if(data.getFeed()!=null){
                            list.addAll(data.getFeed());
                            if(list.size()>0){
                                recyclerView.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                                adapter.refresh(list);
                            }
                            else {
                                recyclerView.setVisibility(View.GONE);
                                progressBar.setVisibility(View.GONE);
                                noData.setVisibility(View.VISIBLE);
                                noData.setText("No Post Uploaded");
                            }

                        }
                    } else {
                        noData.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        noData.setText(data.getMsg());

                    }
                } else {
                    noData.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    noData.setText("Please Check Internet Connection");

                }
            }

            @Override

            public void onFailure(Call<NewsfeedModel> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                t.printStackTrace();
                noData.setText("Please Check Internet Connection");

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(USER_POST,list);
    }
}
