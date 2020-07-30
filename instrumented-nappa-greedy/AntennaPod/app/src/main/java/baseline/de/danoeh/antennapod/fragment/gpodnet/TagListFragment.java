package baseline.de.danoeh.antennapod.fragment.gpodnet;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.ListFragment;
import androidx.core.view.MenuItemCompat;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import baseline.de.danoeh.antennapod.menuhandler.MenuItemUtils;
import baseline.de.danoeh.antennapod.R;
import baseline.de.danoeh.antennapod.activity.MainActivity;
import baseline.de.danoeh.antennapod.adapter.gpodnet.TagListAdapter;
import baseline.de.danoeh.antennapod.core.gpoddernet.GpodnetService;
import baseline.de.danoeh.antennapod.core.gpoddernet.GpodnetServiceException;
import baseline.de.danoeh.antennapod.core.gpoddernet.model.GpodnetTag;

public class TagListFragment extends ListFragment {

    private static final String TAG = "TagListFragment";
    private static final int COUNT = 50;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.gpodder_podcasts, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView sv = (SearchView) MenuItemCompat.getActionView(searchItem);
        MenuItemUtils.adjustTextColor(getActivity(), sv);
        sv.setQueryHint(getString(R.string.gpodnet_search_hint));
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Activity activity = getActivity();
                if (activity != null) {
                    sv.clearFocus();
                    ((MainActivity) activity).loadChildFragment(SearchListFragment.newInstance(s));
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getListView().setOnItemClickListener((parent, view1, position, id) -> {
            GpodnetTag tag = (GpodnetTag) getListAdapter().getItem(position);
            MainActivity activity = (MainActivity) getActivity();
            activity.loadChildFragment(TagFragment.newInstance(tag));
        });

        startLoadTask();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.add_feed_label);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        cancelLoadTask();
    }

    private AsyncTask<Void, Void, List<GpodnetTag>> loadTask;

    private void cancelLoadTask() {
        if (loadTask != null && !loadTask.isCancelled()) {
            loadTask.cancel(true);
        }
    }

    private void startLoadTask() {
        cancelLoadTask();
        loadTask = new AsyncTask<Void, Void, List<GpodnetTag>>() {
            private Exception exception;

            @Override
            protected List<GpodnetTag> doInBackground(Void... params) {
                GpodnetService service = new GpodnetService();
                try {
                    return service.getTopTags(COUNT);
                } catch (GpodnetServiceException e) {
                    e.printStackTrace();
                    exception = e;
                    return null;
                } finally {
                    service.shutdown();
                }
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                setListShown(false);
            }

            @Override
            protected void onPostExecute(List<GpodnetTag> gpodnetTags) {
                super.onPostExecute(gpodnetTags);
                final Context context = getActivity();
                if (context != null) {
                    if (gpodnetTags != null) {
                        setListAdapter(new TagListAdapter(context, android.R.layout.simple_list_item_1, gpodnetTags));
                    } else if (exception != null) {
                        TextView txtvError = new TextView(getActivity());
                        txtvError.setText(exception.getMessage());
                        getListView().setEmptyView(txtvError);
                    }
                    setListShown(true);

                }
            }
        };
        loadTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}

