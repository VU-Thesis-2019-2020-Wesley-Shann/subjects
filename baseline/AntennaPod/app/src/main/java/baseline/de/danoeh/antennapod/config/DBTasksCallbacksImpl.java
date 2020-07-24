package baseline.de.danoeh.antennapod.config;

import baseline.de.danoeh.antennapod.core.DBTasksCallbacks;
import baseline.de.danoeh.antennapod.core.preferences.UserPreferences;
import baseline.de.danoeh.antennapod.core.storage.APDownloadAlgorithm;
import baseline.de.danoeh.antennapod.core.storage.AutomaticDownloadAlgorithm;
import baseline.de.danoeh.antennapod.core.storage.EpisodeCleanupAlgorithm;

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
