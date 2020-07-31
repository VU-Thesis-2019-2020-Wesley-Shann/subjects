package nappatfpr.de.danoeh.antennapod.fragment.gpodnet;

import java.util.Collections;
import java.util.List;

import nappatfpr.de.danoeh.antennapod.core.gpoddernet.GpodnetService;
import nappatfpr.de.danoeh.antennapod.core.gpoddernet.GpodnetServiceException;
import nappatfpr.de.danoeh.antennapod.core.gpoddernet.model.GpodnetPodcast;
import nappatfpr.de.danoeh.antennapod.core.preferences.GpodnetPreferences;

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
