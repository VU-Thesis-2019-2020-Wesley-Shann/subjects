package nappagreedy.de.danoeh.antennapod.core.util.exception;

import nappagreedy.de.danoeh.antennapod.core.feed.FeedMedia;

public class MediaFileNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	private final FeedMedia media;

	public MediaFileNotFoundException(String msg, FeedMedia media) {
		super(msg);
		this.media = media;
	}

	public FeedMedia getMedia() {
		return media;
	}
}
