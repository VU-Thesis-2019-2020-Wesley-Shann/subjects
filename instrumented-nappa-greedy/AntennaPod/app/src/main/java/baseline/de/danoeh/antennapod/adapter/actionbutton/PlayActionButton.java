package baseline.de.danoeh.antennapod.adapter.actionbutton;

import android.content.Context;
import androidx.annotation.AttrRes;
import androidx.annotation.StringRes;

import baseline.de.danoeh.antennapod.R;
import baseline.de.danoeh.antennapod.core.feed.FeedItem;
import baseline.de.danoeh.antennapod.core.feed.FeedMedia;
import baseline.de.danoeh.antennapod.core.storage.DBTasks;
import baseline.de.danoeh.antennapod.core.util.IntentUtils;
import baseline.de.danoeh.antennapod.core.util.playback.PlaybackServiceStarter;

import static baseline.de.danoeh.antennapod.core.service.playback.PlaybackService.ACTION_PAUSE_PLAY_CURRENT_EPISODE;
import static baseline.de.danoeh.antennapod.core.service.playback.PlaybackService.ACTION_RESUME_PLAY_CURRENT_EPISODE;

class PlayActionButton extends ItemActionButton {

    PlayActionButton(FeedItem item) {
        super(item);
    }

    @Override
    @StringRes
    public int getLabel() {
        return R.string.play_label;
    }

    @Override
    @AttrRes
    public int getDrawable() {
        FeedMedia media = item.getMedia();
        if (media != null && media.isCurrentlyPlaying()) {
            return R.attr.av_pause;
        } else {
            return R.attr.av_play;
        }
    }

    @Override
    public void onClick(Context context) {
        FeedMedia media = item.getMedia();
        if (media == null) {
            return;
        }

        if (media.isPlaying()) {
            togglePlayPause(context, media);
        } else {
            DBTasks.playMedia(context, media, false, true, false);
        }
    }

    private void togglePlayPause(Context context, FeedMedia media) {
        new PlaybackServiceStarter(context, media)
                .startWhenPrepared(true)
                .shouldStream(false)
                .start();

        String pauseOrResume = media.isCurrentlyPlaying() ? ACTION_PAUSE_PLAY_CURRENT_EPISODE : ACTION_RESUME_PLAY_CURRENT_EPISODE;
        IntentUtils.sendLocalBroadcast(context, pauseOrResume);
    }
}
