package nappagreedy.de.danoeh.antennapod.dialog;

import android.app.Activity;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;

import java.lang.ref.WeakReference;

import nappagreedy.de.danoeh.antennapod.core.feed.Feed;
import nappagreedy.de.danoeh.antennapod.core.storage.DBWriter;

public class RenameFeedDialog {

    private final WeakReference<Activity> activityRef;
    private final Feed feed;

    public RenameFeedDialog(Activity activity, Feed feed) {
        this.activityRef = new WeakReference<>(activity);
        this.feed = feed;
    }

    public void show() {
        Activity activity = activityRef.get();
        if(activity == null) {
            return;
        }
        new MaterialDialog.Builder(activity)
                .title(nappagreedy.de.danoeh.antennapod.core.R.string.rename_feed_label)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(feed.getTitle(), feed.getTitle(), true, (dialog, input) -> {
                    feed.setCustomTitle(input.toString());
                    DBWriter.setFeedCustomTitle(feed);
                    dialog.dismiss();
                })
                .neutralText(nappagreedy.de.danoeh.antennapod.core.R.string.reset)
                .onNeutral((dialog, which) -> dialog.getInputEditText().setText(feed.getFeedTitle()))
                .negativeText(nappagreedy.de.danoeh.antennapod.core.R.string.cancel_label)
                .onNegative((dialog, which) -> dialog.dismiss())
                .autoDismiss(false)
                .show();
    }

}
