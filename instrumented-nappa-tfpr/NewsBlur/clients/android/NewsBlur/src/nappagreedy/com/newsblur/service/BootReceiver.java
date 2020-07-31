package nappagreedy.com.newsblur.service;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import nappagreedy.com.newsblur.util.AppConstants;

/**
 * First receiver in the chain that starts with the device.  Simply schedules another broadcast
 * that will periodicaly start the sync service.
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        nappagreedy.com.newsblur.util.Log.d(this, "triggering sync service from device boot");
        scheduleSyncService(context);
    }

    public static void scheduleSyncService(Context context) {
        nappagreedy.com.newsblur.util.Log.d(BootReceiver.class.getName(), "scheduling sync service");
        JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(context, NBSyncService.class));
        builder.setPeriodic(AppConstants.BG_SERVICE_CYCLE_MILLIS);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPersisted(true);
        JobScheduler sched = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        sched.schedule(builder.build());
    }
        
}
