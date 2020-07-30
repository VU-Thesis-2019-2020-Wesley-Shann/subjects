package nappagreedy.de.danoeh.antennapod.core.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import nappagreedy.de.danoeh.antennapod.core.ClientConfig;
import nappagreedy.de.danoeh.antennapod.core.preferences.UserPreferences;
import nappagreedy.de.danoeh.antennapod.core.util.FeedUpdateUtils;

/**
 * Refreshes all feeds when it receives an intent
 */
public class FeedUpdateReceiver extends BroadcastReceiver {

    private static final String TAG = "FeedUpdateReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "Received intent");
        ClientConfig.initialize(context);
        FeedUpdateUtils.startAutoUpdate(context, null);
        UserPreferences.restartUpdateAlarm(false);
    }

}
