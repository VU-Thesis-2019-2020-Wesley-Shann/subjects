package nappagreedy.de.danoeh.antennapod.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.core.view.MenuItemCompat;
import androidx.appcompat.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nappagreedy.de.danoeh.antennapod.menuhandler.MenuItemUtils;
import nappagreedy.de.danoeh.antennapod.R;
import nappagreedy.de.danoeh.antennapod.activity.OnlineFeedViewActivity;
import nappagreedy.de.danoeh.antennapod.adapter.itunes.ItunesAdapter;
import nappagreedy.de.danoeh.antennapod.core.service.download.AntennapodHttpClient;
import de.mfietz.fyydlin.FyydClient;
import de.mfietz.fyydlin.FyydResponse;
import de.mfietz.fyydlin.SearchHit;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import nl.vu.cs.s2group.nappa.*;

import static nappagreedy.de.danoeh.antennapod.adapter.itunes.ItunesAdapter.Podcast;
import static java.util.Collections.emptyList;

public class FyydSearchFragment extends Fragment {

    private static final String TAG = "FyydSearchFragment";

    /**
     * Adapter responsible with the search results
     */
    private ItunesAdapter adapter;
    private GridView gridView;
    private ProgressBar progressBar;
    private TextView txtvError;
    private Button butRetry;
    private TextView txtvEmpty;

    private final FyydClient client = new FyydClient(AntennapodHttpClient.getHttpClient());

    /**
     * List of podcasts retreived from the search
     */
    private List<Podcast> searchResults;
    private Disposable disposable;

    /**
     * Constructor
     */
    public FyydSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_itunes_search, container, false);
        gridView = root.findViewById(R.id.gridView);
        adapter = new ItunesAdapter(getActivity(), new ArrayList<>());
        gridView.setAdapter(adapter);

        //Show information about the podcast when the list item is clicked
        gridView.setOnItemClickListener((parent, view1, position, id) -> {
            Podcast podcast = searchResults.get(position);
            Intent intent = new Intent(getActivity(), OnlineFeedViewActivity.class);
            intent.putExtra(OnlineFeedViewActivity.ARG_FEEDURL, podcast.feedUrl);
            intent.putExtra(OnlineFeedViewActivity.ARG_TITLE, podcast.title);
            Nappa.notifyExtras(intent.getExtras());
            startActivity(intent);
        });
        progressBar = root.findViewById(R.id.progressBar);
        txtvError = root.findViewById(R.id.txtvError);
        butRetry = root.findViewById(R.id.butRetry);
        txtvEmpty = root.findViewById(android.R.id.empty);

        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
        adapter = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.itunes_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView sv = (SearchView) MenuItemCompat.getActionView(searchItem);
        MenuItemUtils.adjustTextColor(getActivity(), sv);
        sv.setQueryHint(getString(R.string.search_fyyd_label));
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                sv.clearFocus();
                search(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                getActivity().getSupportFragmentManager().popBackStack();
                return true;
            }
        });
        MenuItemCompat.expandActionView(searchItem);
    }

    private void search(String query) {
        if (disposable != null) {
            disposable.dispose();
        }
        showOnlyProgressBar();
        disposable =  client.searchPodcasts(query, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    progressBar.setVisibility(View.GONE);
                    processSearchResult(result);
                }, error -> {
                    Log.e(TAG, Log.getStackTraceString(error));
                    progressBar.setVisibility(View.GONE);
                    txtvError.setText(error.toString());
                    txtvError.setVisibility(View.VISIBLE);
                    butRetry.setOnClickListener(v -> search(query));
                    butRetry.setVisibility(View.VISIBLE);
                });
    }

    private void showOnlyProgressBar() {
        gridView.setVisibility(View.GONE);
        txtvError.setVisibility(View.GONE);
        butRetry.setVisibility(View.GONE);
        txtvEmpty.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void processSearchResult(FyydResponse response) {
        adapter.clear();
        if (!response.getData().isEmpty()) {
            adapter.clear();
            searchResults = new ArrayList<>();
            for (SearchHit searchHit : response.getData()) {
                Podcast podcast = Podcast.fromSearch(searchHit);
                searchResults.add(podcast);
            }
        } else {
            searchResults = emptyList();
        }
        for(Podcast podcast : searchResults) {
            adapter.add(podcast);
        }
        adapter.notifyDataSetInvalidated();
        gridView.setVisibility(!searchResults.isEmpty() ? View.VISIBLE : View.GONE);
        txtvEmpty.setVisibility(searchResults.isEmpty() ? View.VISIBLE : View.GONE);
    }

}
