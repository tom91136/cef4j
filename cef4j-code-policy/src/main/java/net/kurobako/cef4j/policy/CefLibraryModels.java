package net.kurobako.cef4j.policy;

import static com.uber.nullaway.LibraryModels.MethodRef.methodRef;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;
import com.uber.nullaway.LibraryModels;
import java.util.List;

/**
 * NullAway models for library APIs whose nullness NullAway does not know.
 *
 * <p>Queue and deque retrieval methods return null when empty. JavaFX object properties hold null until set, so their
 * value type parameter is nullable and must be declared {@code @Nullable} where the property can be empty.
 */
@AutoService(LibraryModels.class)
public final class CefLibraryModels implements LibraryModels {
    private static final List<String> QUEUES = List.of(
            "java.util.Queue",
            "java.util.Deque",
            "java.util.ArrayDeque",
            "java.util.concurrent.BlockingQueue",
            "java.util.concurrent.ArrayBlockingQueue",
            "java.util.concurrent.LinkedBlockingQueue",
            "java.util.concurrent.ConcurrentLinkedQueue",
            "java.util.concurrent.ConcurrentLinkedDeque");
    private static final List<String> QUEUE_RETRIEVALS =
            List.of("poll()", "peek()", "pollFirst()", "pollLast()", "peekFirst()", "peekLast()");
    private static final List<String> TIMED_QUEUES = List.of(
            "java.util.concurrent.BlockingQueue",
            "java.util.concurrent.ArrayBlockingQueue",
            "java.util.concurrent.LinkedBlockingQueue");
    private static final List<String> JAVAFX_OBJECT_VALUES = List.of(
            "javafx.beans.value.ObservableObjectValue",
            "javafx.beans.value.WritableObjectValue",
            "javafx.beans.binding.ObjectExpression",
            "javafx.beans.property.ReadOnlyObjectProperty",
            "javafx.beans.property.ReadOnlyObjectPropertyBase",
            "javafx.beans.property.ObjectProperty",
            "javafx.beans.property.ObjectPropertyBase",
            "javafx.beans.property.SimpleObjectProperty",
            "javafx.beans.property.ReadOnlyObjectWrapper");

    @Override
    public ImmutableSet<MethodRef> nullableReturns() {
        ImmutableSet.Builder<MethodRef> returns = ImmutableSet.builder();
        for (String queue : QUEUES) {
            for (String method : QUEUE_RETRIEVALS) returns.add(methodRef(queue, method));
        }
        for (String queue : TIMED_QUEUES) {
            returns.add(methodRef(queue, "poll(long,java.util.concurrent.TimeUnit)"));
        }
        return returns.build();
    }

    @Override
    public ImmutableSet<String> nullMarkedClasses() {
        return ImmutableSet.copyOf(JAVAFX_OBJECT_VALUES);
    }

    @Override
    public ImmutableSetMultimap<String, Integer> typeVariablesWithNullableUpperBounds() {
        ImmutableSetMultimap.Builder<String, Integer> bounds = ImmutableSetMultimap.builder();
        for (String type : JAVAFX_OBJECT_VALUES) bounds.put(type, 0);
        return bounds.build();
    }

    @Override
    public ImmutableSetMultimap<MethodRef, Integer> failIfNullParameters() {
        return ImmutableSetMultimap.of();
    }

    @Override
    public ImmutableSetMultimap<MethodRef, Integer> explicitlyNullableParameters() {
        return ImmutableSetMultimap.of();
    }

    @Override
    public ImmutableSetMultimap<MethodRef, Integer> nonNullParameters() {
        return ImmutableSetMultimap.of();
    }

    @Override
    public ImmutableSetMultimap<MethodRef, Integer> nullImpliesTrueParameters() {
        return ImmutableSetMultimap.of();
    }

    @Override
    public ImmutableSetMultimap<MethodRef, Integer> nullImpliesFalseParameters() {
        return ImmutableSetMultimap.of();
    }

    @Override
    public ImmutableSetMultimap<MethodRef, Integer> nullImpliesNullParameters() {
        return ImmutableSetMultimap.of();
    }

    @Override
    public ImmutableSet<MethodRef> nonNullReturns() {
        return ImmutableSet.of();
    }

    @Override
    public ImmutableSetMultimap<MethodRef, Integer> castToNonNullMethods() {
        return ImmutableSetMultimap.of();
    }
}
