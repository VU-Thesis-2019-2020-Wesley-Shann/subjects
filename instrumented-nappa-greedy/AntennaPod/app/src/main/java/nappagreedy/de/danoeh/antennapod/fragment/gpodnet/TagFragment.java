package nappagreedy.de.danoeh.antennapod.fragment.gpodnet;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import org.apache.commons.lang3.Validate;

import java.util.List;

import nappagreedy.de.danoeh.antennapod.activity.MainActivity;
import nappagreedy.de.danoeh.antennapod.core.gpoddernet.GpodnetService;
import nappagreedy.de.danoeh.antennapod.core.gpoddernet.GpodnetServiceException;
import nappagreedy.de.danoeh.antennapod.core.gpoddernet.model.GpodnetPodcast;
import nappagreedy.de.danoeh.antennapod.core.gpoddernet.model.GpodnetTag;

/**
 * Shows all podcasts from gpodder.net that belong to a specific tag.
 * Use the newInstance method of this class to create a new TagFragment.
 */
public class TagFragment extends PodcastListFragment {

    private static final String TAG = "TagFragment";
    private static final int PODCAST_COUNT = 50;

    private GpodnetTag tag;

    public static TagFragment newInstance(GpodnetTag tag) {
        Validate.notNull(tag);
        TagFragment fragment = new TagFragment();
        Bundle args = new Bundle();
        args.putParcelable("tag", tag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        Validate.isTrue(args != null && args.getParcelable("tag") != null, "args invalid");
        tag = args.getParcelable("tag");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("WESLEY_GRAPH_FRAGMENT", this.getClass().getSimpleName());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(tag.getTitle());
    }

    @Override
    protected List<GpodnetPodcast> loadPodcastData(GpodnetService service) throws GpodnetServiceException {
        return service.getPodcastsForTag(tag, PODCAST_COUNT);
    }
}
