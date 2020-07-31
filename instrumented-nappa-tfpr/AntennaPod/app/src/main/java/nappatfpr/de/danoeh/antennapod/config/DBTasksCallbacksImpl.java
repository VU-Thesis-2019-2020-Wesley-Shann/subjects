package nappatfpr.de.danoeh.antennapod.config;

import nappatfpr.de.danoeh.antennapod.core.DBTasksCallbacks;
import nappatfpr.de.danoeh.antennapod.core.preferences.UserPreferences;
import nappatfpr.de.danoeh.antennapod.core.storage.APDownloadAlgorithm;
import nappatfpr.de.danoeh.antennapod.core.storage.AutomaticDownloadAlgorithm;
import nappatfpr.de.danoeh.antennapod.core.storage.EpisodeCleanupAlgorithm;

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
