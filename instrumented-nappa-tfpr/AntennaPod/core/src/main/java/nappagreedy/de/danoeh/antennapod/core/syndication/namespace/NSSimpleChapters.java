package nappagreedy.de.danoeh.antennapod.core.syndication.namespace;

import android.util.Log;

import org.xml.sax.Attributes;

import java.util.ArrayList;

import nappagreedy.de.danoeh.antennapod.core.feed.FeedItem;
import nappagreedy.de.danoeh.antennapod.core.feed.SimpleChapter;
import nappagreedy.de.danoeh.antennapod.core.syndication.handler.HandlerState;
import nappagreedy.de.danoeh.antennapod.core.util.DateUtils;

public class NSSimpleChapters extends Namespace {
    private static final String TAG = "NSSimpleChapters";

    public static final String NSTAG = "psc|sc";
    public static final String NSURI = "http://podlove.org/simple-chapters";

    private static final String CHAPTERS = "chapters";
    private static final String CHAPTER = "chapter";
    private static final String START = "start";
    private static final String TITLE = "title";
    private static final String HREF = "href";

    @Override
    public SyndElement handleElementStart(String localName, HandlerState state,
                                          Attributes attributes) {
        FeedItem currentItem = state.getCurrentItem();
        if(currentItem != null) {
            if (localName.equals(CHAPTERS)) {
                currentItem.setChapters(new ArrayList<>());
            } else if (localName.equals(CHAPTER)) {
                try {
                    long start = DateUtils.parseTimeString(attributes.getValue(START));
                    String title = attributes.getValue(TITLE);
                    String link = attributes.getValue(HREF);
                    SimpleChapter chapter = new SimpleChapter(start, title, currentItem, link);
                    currentItem.getChapters().add(chapter);
                } catch (NumberFormatException e) {
                    Log.e(TAG, "Unable to read chapter", e);
                }
            }
        }
        return new SyndElement(localName, this);
    }

    @Override
    public void handleElementEnd(String localName, HandlerState state) {
    }

}
