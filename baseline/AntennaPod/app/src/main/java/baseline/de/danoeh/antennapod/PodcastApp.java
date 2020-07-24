package baseline.de.danoeh.antennapod;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.MaterialModule;

import baseline.de.danoeh.antennapod.spa.SPAUtil;
import baseline.de.danoeh.antennapod.core.ApCoreEventBusIndex;
import baseline.de.danoeh.antennapod.core.ClientConfig;
import baseline.de.danoeh.antennapod.core.feed.EventDistributor;

import de.danoeh.antennapod.ApEventBusIndex;
import org.greenrobot.eventbus.EventBus;
import baseline.de.danoeh.antennapod.BuildConfig;
/** Main application class. */
public class PodcastApp extends Application {

    // make sure that ClientConfigurator executes its static code
    static {
        try {
            Class.forName("baseline.de.danoeh.antennapod.config.ClientConfigurator");
        } catch (Exception e) {
            throw new RuntimeException("ClientConfigurator not found", e);
        }
    }

	private static PodcastApp singleton;

	public static PodcastApp getInstance() {
		return singleton;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		Thread.setDefaultUncaughtExceptionHandler(new CrashReportWriter());

		if(BuildConfig.DEBUG) {
			StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects()
				.penaltyLog()
				.penaltyDropBox();
			builder.detectActivityLeaks();
			builder.detectLeakedClosableObjects();
			if(Build.VERSION.SDK_INT >= 16) {
				builder.detectLeakedRegistrationObjects();
			}
			StrictMode.setVmPolicy(builder.build());
		}

		singleton = this;

		ClientConfig.initialize(this);

		EventDistributor.getInstance();
		Iconify.with(new FontAwesomeModule());
		Iconify.with(new MaterialModule());

        SPAUtil.sendSPAppsQueryFeedsIntent(this);
		EventBus.builder()
				.addIndex(new ApEventBusIndex())
				.addIndex(new ApCoreEventBusIndex())
				.installDefaultEventBus();
    }

}
