package net.kurobako.cef4j.http;

import static org.assertj.core.api.Assertions.assertThatIOException;

import java.lang.reflect.Proxy;
import java.util.Optional;
import net.kurobako.cef4j.gen.CefPostData;
import net.kurobako.cef4j.gen.CefPostDataElement;
import org.junit.jupiter.api.Test;

class CefUrlRequestHttpEngineTest {

    @Test
    void rejectsMissingPostDataElementForNonEmptyBody() {
        assertThatIOException()
                .isThrownBy(() -> CefUrlRequestHttpEngine.buildPostData(
                        new byte[] {1},
                        Optional::<CefPostDataElement>empty,
                        () -> Optional.of(dummy(CefPostData.class))))
                .withMessageContaining("CefPostDataElement.create");
    }

    @Test
    void rejectsMissingPostDataForNonEmptyBody() {
        assertThatIOException()
                .isThrownBy(() -> CefUrlRequestHttpEngine.buildPostData(
                        new byte[] {1},
                        () -> Optional.of(dummy(CefPostDataElement.class)),
                        Optional::<CefPostData>empty))
                .withMessageContaining("CefPostData.create");
    }

    private static <T> T dummy(Class<T> type) {
        return type.cast(Proxy.newProxyInstance(
                type.getClassLoader(),
                new Class<?>[] {type},
                (proxy, method, arguments) -> defaultValue(method.getReturnType())));
    }

    @SuppressWarnings("NullAway")
    private static Object defaultValue(Class<?> type) {
        if (!type.isPrimitive()) return null;
        if (type == boolean.class) return false;
        if (type == char.class) return '\0';
        if (type == byte.class) return (byte) 0;
        if (type == short.class) return (short) 0;
        if (type == int.class) return 0;
        if (type == long.class) return 0L;
        if (type == float.class) return 0F;
        return 0D;
    }
}
