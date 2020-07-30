package baseline.de.danoeh.antennapod.adapter.actionbutton;

import android.content.Context;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import android.widget.Toast;

import baseline.de.danoeh.antennapod.R;
import baseline.de.danoeh.antennapod.core.dialog.DownloadRequestErrorDialogCreator;
import baseline.de.danoeh.antennapod.core.feed.FeedItem;
import baseline.de.danoeh.antennapod.core.feed.FeedMedia;
import baseline.de.danoeh.antennapod.core.storage.DBTasks;
import baseline.de.danoeh.antennapod.core.storage.DBWriter;
import baseline.de.danoeh.antennapod.core.storage.DownloadRequestException;
import baseline.de.danoeh.antennapod.core.storage.DownloadRequester;
import baseline.de.danoeh.antennapod.core.util.NetworkUtils;

class DownloadActionButton extends ItemActionButton {
    private boolean isInQueue;

    DownloadActionButton(FeedItem item, boolean isInQueue) {
        super(item);
        this.isInQueue = isInQueue;
    }

    @Override
    @StringRes
    public int getLabel() {
        return R.string.download_label;
    }

    @Override
    @AttrRes
    public int getDrawable() {
        return R.attr.av_download;
    }

    @Override
    public void onClick(Context context) {
        final FeedMedia media = item.getMedia();
        if (media == null || shouldNotDownload(media)) {
            return;
        }

        if (NetworkUtils.isDownloadAllowed() || MobileDownloadHelper.userAllowedMobileDownloads()) {
            downloadEpisode(context);
        } else if (MobileDownloadHelper.userChoseAddToQueue() && !isInQueue) {
            addEpisodeToQueue(context);
        } else {
            MobileDownloadHelper.confirmMobileDownload(context, item);
        }
    }

    private boolean shouldNotDownload(@NonNull FeedMedia media) {
        boolean isDownloading = DownloadRequester.getInstance().isDownloadingFile(media);
        return isDownloading || media.isDownloaded();
    }

    private void addEpisodeToQueue(Context context) {
        DBWriter.addQueueItem(context, item);
        Toast.makeText(context, R.string.added_to_queue_label, Toast.LENGTH_SHORT).show();
    }

    private void downloadEpisode(Context context) {
        try {
            DBTasks.downloadFeedItems(context, item);
            Toast.makeText(context, R.string.status_downloading_label, Toast.LENGTH_SHORT).show();
        } catch (DownloadRequestException e) {
            e.printStackTrace();
            DownloadRequestErrorDialogCreator.newRequestErrorDialog(context, e.getMessage());
        }
    }
}
