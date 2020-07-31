package nappatfpr.com.newsblur.service;

import nappatfpr.com.newsblur.util.FileCache;
import nappatfpr.com.newsblur.util.PrefConstants;
import nappatfpr.com.newsblur.util.PrefsUtils;

public class CleanupService extends SubService {

    public static boolean activelyRunning = false;

    public CleanupService(NBSyncService parent) {
        super(parent);
    }

    @Override
    protected void exec() {

        if (!PrefsUtils.isTimeToCleanup(parent)) return;

        activelyRunning = true;

        nappatfpr.com.newsblur.util.Log.d(this.getClass().getName(), "cleaning up old stories");
        parent.dbHelper.cleanupVeryOldStories();
        if (!PrefsUtils.isKeepOldStories(parent)) {
            parent.dbHelper.cleanupReadStories();
        }
        PrefsUtils.updateLastCleanupTime(parent);

        nappatfpr.com.newsblur.util.Log.d(this.getClass().getName(), "cleaning up old story texts");
        parent.dbHelper.cleanupStoryText();

        nappatfpr.com.newsblur.util.Log.d(this.getClass().getName(), "cleaning up notification dismissals");
        parent.dbHelper.cleanupDismissals();

        nappatfpr.com.newsblur.util.Log.d(this.getClass().getName(), "cleaning up story image cache");
        FileCache imageCache = FileCache.asStoryImageCache(parent);
        imageCache.cleanupUnusedAndOld(parent.dbHelper.getAllStoryImages(), PrefsUtils.getMaxCachedAgeMillis(parent));

        nappatfpr.com.newsblur.util.Log.d(this.getClass().getName(), "cleaning up icon cache");
        FileCache iconCache = FileCache.asIconCache(parent);
        iconCache.cleanupOld(PrefConstants.CACHE_AGE_VALUE_30D);

        nappatfpr.com.newsblur.util.Log.d(this.getClass().getName(), "cleaning up thumbnail cache");
        FileCache thumbCache = FileCache.asThumbnailCache(parent);
        thumbCache.cleanupUnusedAndOld(parent.dbHelper.getAllStoryThumbnails(), PrefsUtils.getMaxCachedAgeMillis(parent));

        activelyRunning = false;
    }
    
}
        
