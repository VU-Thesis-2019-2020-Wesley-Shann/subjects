package nappatfpr.io.github.project_travel_mate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.card.MaterialCardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import nappatfpr.io.github.project_travel_mate.destinations.CityFragment;
import nappatfpr.io.github.project_travel_mate.friend.MyFriendsFragment;
import nappatfpr.io.github.project_travel_mate.mytrips.MyTripsFragment;
import nappatfpr.io.github.project_travel_mate.travel.HotelsActivity;
import nappatfpr.io.github.project_travel_mate.utilities.UtilitiesFragment;
import nl.vu.cs.s2group.nappa.*;

public class HomeFragment extends Fragment {

    private Activity mActivity;
    @BindView(R.id.materialCardView2)
    MaterialCardView mHotelBookingView;
    @BindView(R.id.materialCardView21)
    MaterialCardView mFriendsView;
    @BindView(R.id.materialCardView3)
    MaterialCardView mTripsView;
    @BindView(R.id.popular_cities_home)
    MaterialCardView mCitiesView;
    @BindView(R.id.utilities_home)
    MaterialCardView mUtilitiesView;

    private FragmentManager mFragmentManager;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, rootview);
        mFragmentManager = getFragmentManager();
        mHotelBookingView.setOnClickListener(v -> {
            Intent hotelIntent = HotelsActivity.getStartIntent(mActivity);
            Nappa.notifyExtras(hotelIntent.getExtras());
            startActivity(hotelIntent);
        });
        mFriendsView.setOnClickListener(v1 -> {
            Fragment friendsFragment = new MyFriendsFragment();
            transactFragment(friendsFragment);
        });
        mTripsView.setOnClickListener(v -> {
            Fragment tripsFragment = new MyTripsFragment();
            transactFragment(tripsFragment);
        });
        mCitiesView.setOnClickListener(v -> {
            Fragment citiesFragment = new CityFragment();
            transactFragment(citiesFragment);
        });
        mUtilitiesView.setOnClickListener(v -> {
            Fragment utilitiesFragment = new UtilitiesFragment();
            transactFragment(utilitiesFragment);
        });
        return rootview;
    }

    /**
     * This function handles the transaction of one fragment to another.
     * @param fragment
     */
    private void transactFragment(Fragment fragment) {
        mFragmentManager.beginTransaction().replace(R.id.parent_home,
                fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }
}
