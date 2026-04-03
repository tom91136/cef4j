package net.kurobako.cef4j.osr.jfx;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** JavaFX-style history facade for {@link CefWebEngine}. */
public final class CefWebHistory {
    private final CefWebView view;
    private final ReadOnlyIntegerWrapper currentIndex = new ReadOnlyIntegerWrapper(this, "currentIndex", -1);
    private final IntegerProperty maxSize = new SimpleIntegerProperty(this, "maxSize", 100);
    private final ObservableList<Entry> entries = FXCollections.observableArrayList();

    CefWebHistory(CefWebView view) {
        this.view = view;
    }

    public ReadOnlyIntegerProperty currentIndexProperty() {
        return currentIndex.getReadOnlyProperty();
    }

    public int getCurrentIndex() {
        return currentIndex.get();
    }

    public IntegerProperty maxSizeProperty() {
        return maxSize;
    }

    public void setMaxSize(int value) {
        maxSize.set(value);
        trimToMaxSize();
    }

    public int getMaxSize() {
        return maxSize.get();
    }

    public ObservableList<Entry> getEntries() {
        return FXCollections.unmodifiableObservableList(entries);
    }

    public void go(int offset) {
        int target = getCurrentIndex() + offset;
        if (offset == 0) return;
        if (target < 0 || target >= entries.size()) {
            throw new IndexOutOfBoundsException("History index out of bounds: " + target);
        }
        if (offset < 0) {
            for (int i = 0; i < -offset; i++) {
                view.goBack();
            }
        } else {
            for (int i = 0; i < offset; i++) {
                view.goForward();
            }
        }
    }

    void replaceEntries(java.util.List<EntrySnapshot> snapshots, int nextCurrentIndex) {
        entries.clear();
        for (EntrySnapshot snapshot : snapshots) {
            entries.add(new Entry(snapshot.url, snapshot.title, snapshot.lastVisitedDate));
        }
        trimToMaxSize();
        if (entries.isEmpty()) {
            currentIndex.set(-1);
        } else {
            currentIndex.set(Math.max(0, Math.min(entries.size() - 1, nextCurrentIndex)));
        }
    }

    private void trimToMaxSize() {
        int limit = getMaxSize();
        if (limit < 0 || entries.size() <= limit) return;
        int removeCount = entries.size() - limit;
        entries.subList(0, removeCount).clear();
        currentIndex.set(Math.max(-1, currentIndex.get() - removeCount));
    }

    static final class EntrySnapshot {
        final String url;
        final String title;
        final Date lastVisitedDate;

        EntrySnapshot(String url, String title, Date lastVisitedDate) {
            this.url = url;
            this.title = title;
            this.lastVisitedDate = lastVisitedDate;
        }
    }

    public static final class Entry {
        private final String url;
        private final ReadOnlyObjectWrapper<String> title = new ReadOnlyObjectWrapper<>(this, "title");
        private final ReadOnlyObjectWrapper<Date> lastVisitedDate =
                new ReadOnlyObjectWrapper<>(this, "lastVisitedDate");

        private Entry(String url, String title, Date lastVisitedDate) {
            this.url = url;
            this.title.set(title);
            this.lastVisitedDate.set(lastVisitedDate);
        }

        public String getUrl() {
            return url;
        }

        public ReadOnlyObjectProperty<String> titleProperty() {
            return title.getReadOnlyProperty();
        }

        public String getTitle() {
            return title.get();
        }

        public ReadOnlyObjectProperty<Date> lastVisitedDateProperty() {
            return lastVisitedDate.getReadOnlyProperty();
        }

        public Date getLastVisitedDate() {
            return lastVisitedDate.get();
        }
    }
}
