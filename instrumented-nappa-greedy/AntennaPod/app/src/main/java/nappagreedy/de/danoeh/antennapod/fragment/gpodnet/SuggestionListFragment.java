package nappagreedy.de.danoeh.antennapod.fragment.gpodnet;

import java.util.Collections;
import java.util.List;

import nappagreedy.de.danoeh.antennapod.core.gpoddernet.GpodnetService;
import nappagreedy.de.danoeh.antennapod.core.gpoddernet.GpodnetServiceException;
import nappagreedy.de.danoeh.antennapod.core.gpoddernet.model.GpodnetPodcast;
import nappagreedy.de.danoeh.antennapod.core.preferences.GpodnetPreferences;

/**
 * Displays suggestions from gpodder.net
 */
public class SuggestionListFragment extends PodcastListFragment {
    private static final int SUGGESTIONS_COUNT = 50;

    @Override
    protected List<GpodnetPodcast> loadPodcastData(GpodnetService service) throws GpodnetServiceException {
        if (GpodnetPreferences.loggedIn()) {
            service.authenticate(GpodnetPreferences.getUsername(), GpodnetPreferences.getPassword());
            return service.getSuggestions(SUGGESTIONS_COUNT);
        } else {
            return Collections.emptyList();
        }
    }
}
