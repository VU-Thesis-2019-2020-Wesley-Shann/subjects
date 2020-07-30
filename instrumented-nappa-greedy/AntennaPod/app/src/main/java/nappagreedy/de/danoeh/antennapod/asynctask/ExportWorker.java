package nappagreedy.de.danoeh.antennapod.asynctask;

import androidx.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import nappagreedy.de.danoeh.antennapod.core.export.ExportWriter;
import nappagreedy.de.danoeh.antennapod.core.preferences.UserPreferences;
import nappagreedy.de.danoeh.antennapod.core.storage.DBReader;
import nappagreedy.de.danoeh.antennapod.core.util.LangUtils;
import io.reactivex.Observable;

/**
 * Writes an OPML file into the export directory in the background.
 */
public class ExportWorker {

    private static final String EXPORT_DIR = "export/";
    private static final String TAG = "ExportWorker";
    private static final String DEFAULT_OUTPUT_NAME = "antennapod-feeds";

    private final @NonNull ExportWriter exportWriter;
    private final @NonNull File output;

    public ExportWorker(@NonNull ExportWriter exportWriter) {
        this(exportWriter, new File(UserPreferences.getDataFolder(EXPORT_DIR),
                DEFAULT_OUTPUT_NAME + "." + exportWriter.fileExtension()));
    }

    private ExportWorker(@NonNull ExportWriter exportWriter, @NonNull File output) {
        this.exportWriter = exportWriter;
        this.output = output;
    }

    public Observable<File> exportObservable() {
        if (output.exists()) {
            Log.w(TAG, "Overwriting previously exported file.");
            output.delete();
        }
        return Observable.create(subscriber -> {
            OutputStreamWriter writer = null;
            try {
                writer = new OutputStreamWriter(new FileOutputStream(output), LangUtils.UTF_8);
                exportWriter.writeDocument(DBReader.getFeedList(), writer);
                subscriber.onNext(output);
            } catch (IOException e) {
                subscriber.onError(e);
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        subscriber.onError(e);
                    }
                }
                subscriber.onComplete();
            }
        });
    }

}