package baseline.de.danoeh.antennapod.config;


import android.app.Application;
import android.content.Context;
import android.content.Intent;

import baseline.de.danoeh.antennapod.activity.StorageErrorActivity;
import baseline.de.danoeh.antennapod.PodcastApp;
import baseline.de.danoeh.antennapod.core.ApplicationCallbacks;

public class ApplicationCallbacksImpl implements ApplicationCallbacks {

    @Override
    public Application getApplicationInstance() {
        return PodcastApp.getInstance();
    }

    @Override
    public Intent getStorageErrorActivity(Context context) {
        return new Intent(context, StorageErrorActivity.class);
    }

}
