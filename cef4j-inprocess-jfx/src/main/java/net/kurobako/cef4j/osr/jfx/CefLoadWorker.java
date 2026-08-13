package net.kurobako.cef4j.osr.jfx;

import java.util.Objects;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.concurrent.Worker;

final class CefLoadWorker implements Worker<Void> {
    private final CefWebEngine engine;
    private final ReadOnlyObjectWrapper<State> state = new ReadOnlyObjectWrapper<>(this, "state", State.READY);
    private final ReadOnlyObjectWrapper<Void> value = new ReadOnlyObjectWrapper<>(this, "value");
    private final ReadOnlyObjectWrapper<Throwable> exception = new ReadOnlyObjectWrapper<>(this, "exception");
    private final ReadOnlyDoubleWrapper workDone = new ReadOnlyDoubleWrapper(this, "workDone", -1);
    private final ReadOnlyDoubleWrapper totalWork = new ReadOnlyDoubleWrapper(this, "totalWork", -1);
    private final ReadOnlyDoubleWrapper progress = new ReadOnlyDoubleWrapper(this, "progress", -1);
    private final ReadOnlyBooleanWrapper running = new ReadOnlyBooleanWrapper(this, "running", false);
    private final ReadOnlyStringWrapper message = new ReadOnlyStringWrapper(this, "message", "");
    private final ReadOnlyStringWrapper title = new ReadOnlyStringWrapper(this, "title", "");

    CefLoadWorker(CefWebEngine engine) {
        this.engine = engine;
    }

    void beginLoad(String location) {
        state.set(State.SCHEDULED);
        running.set(true);
        exception.set(null);
        workDone.set(-1);
        totalWork.set(-1);
        progress.set(-1);
        message.set("Loading");
        title.set(Objects.requireNonNullElse(location, ""));
    }

    void updateRunning(boolean isLoading) {
        if (isLoading) {
            state.set(State.RUNNING);
            running.set(true);
            message.set("Loading");
        }
    }

    void updateProgress(double nextProgress) {
        progress.set(nextProgress);
        totalWork.set(1.0);
        workDone.set(nextProgress < 0 ? -1 : nextProgress);
    }

    void markSucceeded() {
        state.set(State.SUCCEEDED);
        running.set(false);
        progress.set(1.0);
        totalWork.set(1.0);
        workDone.set(1.0);
        message.set("");
    }

    void markFailed(Throwable failure) {
        exception.set(failure);
        state.set(State.FAILED);
        running.set(false);
        message.set(failure != null && failure.getMessage() != null ? failure.getMessage() : "Load failed");
    }

    void markCancelled() {
        state.set(State.CANCELLED);
        running.set(false);
        message.set("Cancelled");
    }

    @Override
    public State getState() {
        return state.get();
    }

    @Override
    public ReadOnlyObjectProperty<State> stateProperty() {
        return state.getReadOnlyProperty();
    }

    @Override
    public Void getValue() {
        return value.get();
    }

    @Override
    public ReadOnlyObjectProperty<Void> valueProperty() {
        return value.getReadOnlyProperty();
    }

    @Override
    public Throwable getException() {
        return exception.get();
    }

    @Override
    public ReadOnlyObjectProperty<Throwable> exceptionProperty() {
        return exception.getReadOnlyProperty();
    }

    @Override
    public double getWorkDone() {
        return workDone.get();
    }

    @Override
    public ReadOnlyDoubleProperty workDoneProperty() {
        return workDone.getReadOnlyProperty();
    }

    @Override
    public double getTotalWork() {
        return totalWork.get();
    }

    @Override
    public ReadOnlyDoubleProperty totalWorkProperty() {
        return totalWork.getReadOnlyProperty();
    }

    @Override
    public double getProgress() {
        return progress.get();
    }

    @Override
    public ReadOnlyDoubleProperty progressProperty() {
        return progress.getReadOnlyProperty();
    }

    @Override
    public boolean isRunning() {
        return running.get();
    }

    @Override
    public ReadOnlyBooleanProperty runningProperty() {
        return running.getReadOnlyProperty();
    }

    @Override
    public String getMessage() {
        return message.get();
    }

    @Override
    public ReadOnlyStringProperty messageProperty() {
        return message.getReadOnlyProperty();
    }

    @Override
    public String getTitle() {
        return title.get();
    }

    @Override
    public ReadOnlyStringProperty titleProperty() {
        return title.getReadOnlyProperty();
    }

    /** Fluent aliases for the JavaFX {@link Worker} bean contract. */
    public State state() {
        return getState();
    }

    public Void value() {
        return getValue();
    }

    public Throwable exception() {
        return getException();
    }

    public double workDone() {
        return getWorkDone();
    }

    public double totalWork() {
        return getTotalWork();
    }

    public double progress() {
        return getProgress();
    }

    public boolean running() {
        return isRunning();
    }

    public String message() {
        return getMessage();
    }

    public String title() {
        return getTitle();
    }

    @Override
    public boolean cancel() {
        if (!isRunning()) return false;
        engine.stop();
        markCancelled();
        return true;
    }
}
