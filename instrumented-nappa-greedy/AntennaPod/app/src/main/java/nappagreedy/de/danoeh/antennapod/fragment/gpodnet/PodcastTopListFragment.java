package nappagreedy.de.danoeh.antennapod.fragment.gpodnet;

import java.util.List;

import nappagreedy.de.danoeh.antennapod.core.gpoddernet.GpodnetService;
import nappagreedy.de.danoeh.antennapod.core.gpoddernet.GpodnetServiceException;
import nappagreedy.de.danoeh.antennapod.core.gpoddernet.model.GpodnetPodcast;

/**
 *
 */
public class PodcastTopListFragment extends PodcastListFragment {
    private static final String TAG = "PodcastTopListFragment";
    private static final int PODCAST_COUNT = 50;

    @Override
    protected List<GpodnetPodcast> loadPodcastData(GpodnetService service) throws GpodnetServiceException {
        return service.getPodcastToplist(PODCAST_COUNT);
    }
}
