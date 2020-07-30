package baseline.de.danoeh.antennapod.core.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.Log;

import baseline.de.danoeh.antennapod.core.ClientConfig;
import baseline.de.danoeh.antennapod.core.preferences.UserPreferences;
import baseline.de.danoeh.antennapod.core.util.FeedUpdateUtils;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class FeedUpdateJobService extends JobService {
    private static final String TAG = "FeedUpdateJobService";

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");
        ClientConfig.initialize(getApplicationContext());

        FeedUpdateUtils.startAutoUpdate(getApplicationContext(), () -> {
            UserPreferences.restartUpdateAlarm(false);
            jobFinished(params, false); // needsReschedule = false
        });

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }

}
