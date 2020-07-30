package nappagreedy.com.newsblur.service;

import nappagreedy.com.newsblur.util.FileCache;
import nappagreedy.com.newsblur.util.PrefConstants;
import nappagreedy.com.newsblur.util.PrefsUtils;

public class CleanupService extends SubService {

    public static boolean activelyRunning = false;

    public CleanupService(NBSyncService parent) {
        super(parent);
    }

    @Override
    protected void exec() {

        if (!PrefsUtils.isTimeToCleanup(parent)) return;

        activelyRunning = true;

        nappagreedy.com.newsblur.util.Log.d(this.getClass().getName(), "cleaning up old stories");
        parent.dbHelper.cleanupVeryOldStories();
        if (!PrefsUtils.isKeepOldStories(parent)) {
            parent.dbHelper.cleanupReadStories();
        }
        PrefsUtils.updateLastCleanupTime(parent);

        nappagreedy.com.newsblur.util.Log.d(this.getClass().getName(), "cleaning up old story texts");
        parent.dbHelper.cleanupStoryText();

        nappagreedy.com.newsblur.util.Log.d(this.getClass().getName(), "cleaning up notification dismissals");
        parent.dbHelper.cleanupDismissals();

        nappagreedy.com.newsblur.util.Log.d(this.getClass().getName(), "cleaning up story image cache");
        FileCache imageCache = FileCache.asStoryImageCache(parent);
        imageCache.cleanupUnusedAndOld(parent.dbHelper.getAllStoryImages(), PrefsUtils.getMaxCachedAgeMillis(parent));

        nappagreedy.com.newsblur.util.Log.d(this.getClass().getName(), "cleaning up icon cache");
        FileCache iconCache = FileCache.asIconCache(parent);
        iconCache.cleanupOld(PrefConstants.CACHE_AGE_VALUE_30D);

        nappagreedy.com.newsblur.util.Log.d(this.getClass().getName(), "cleaning up thumbnail cache");
        FileCache thumbCache = FileCache.asThumbnailCache(parent);
        thumbCache.cleanupUnusedAndOld(parent.dbHelper.getAllStoryThumbnails(), PrefsUtils.getMaxCachedAgeMillis(parent));

        activelyRunning = false;
    }
    
}
        
