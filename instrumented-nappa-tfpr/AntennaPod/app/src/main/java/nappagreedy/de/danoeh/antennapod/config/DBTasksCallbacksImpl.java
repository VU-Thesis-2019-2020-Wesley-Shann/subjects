package nappagreedy.de.danoeh.antennapod.config;

import nappagreedy.de.danoeh.antennapod.core.DBTasksCallbacks;
import nappagreedy.de.danoeh.antennapod.core.preferences.UserPreferences;
import nappagreedy.de.danoeh.antennapod.core.storage.APDownloadAlgorithm;
import nappagreedy.de.danoeh.antennapod.core.storage.AutomaticDownloadAlgorithm;
import nappagreedy.de.danoeh.antennapod.core.storage.EpisodeCleanupAlgorithm;

public class DBTasksCallbacksImpl implements DBTasksCallbacks {

    @Override
    public AutomaticDownloadAlgorithm getAutomaticDownloadAlgorithm() {
        return new APDownloadAlgorithm();
    }

    @Override
    public EpisodeCleanupAlgorithm getEpisodeCacheCleanupAlgorithm() {
        return UserPreferences.getEpisodeCleanupAlgorithm();
    }
}
