package net.kurobako.cef4j.webdriver;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

final class WebDriverRouteTest {
    @ParameterizedTest
    @MethodSource("routes")
    void matchesEverySupportedRoute(String method, String path, WebDriverRoute.Command command) {
        assertThat(WebDriverRoute.match(method, path)).hasValueSatisfying(match -> {
            assertThat(match.command()).isEqualTo(command);
            if (path.contains("abc")) assertThat(match.parameter("session")).isEqualTo("abc");
            if (path.contains("node-1")) assertThat(match.parameter("element")).isEqualTo("node-1");
            if (path.contains("colour")) assertThat(match.parameter("name")).isEqualTo("colour");
        });
    }

    @Test
    void rejectsWrongMethodsAndMalformedPaths() {
        assertThat(WebDriverRoute.match("POST", "/status")).isEmpty();
        assertThat(WebDriverRoute.match("GET", "/session/abc/title/")).isEmpty();
        assertThat(WebDriverRoute.match("GET", "/session/abc/unknown")).isEmpty();
    }

    private static Stream<Arguments> routes() {
        return Stream.of(
                route("GET", "/status", "STATUS"),
                route("POST", "/session", "NEW_SESSION"),
                route("DELETE", "/session/abc", "DELETE_SESSION"),
                route("GET", "/session/abc/url", "CURRENT_URL"),
                route("POST", "/session/abc/url", "NAVIGATE"),
                route("GET", "/session/abc/title", "TITLE"),
                route("GET", "/session/abc/source", "SOURCE"),
                route("GET", "/session/abc/screenshot", "SCREENSHOT"),
                route("POST", "/session/abc/execute/sync", "EXECUTE_SCRIPT"),
                route("POST", "/session/abc/back", "BACK"),
                route("POST", "/session/abc/forward", "FORWARD"),
                route("POST", "/session/abc/refresh", "REFRESH"),
                route("GET", "/session/abc/timeouts", "GET_TIMEOUTS"),
                route("POST", "/session/abc/timeouts", "SET_TIMEOUTS"),
                route("GET", "/session/abc/cookie", "GET_COOKIES"),
                route("POST", "/session/abc/cookie", "ADD_COOKIE"),
                route("DELETE", "/session/abc/cookie", "DELETE_COOKIES"),
                route("GET", "/session/abc/cookie/colour", "GET_COOKIE"),
                route("DELETE", "/session/abc/cookie/colour", "DELETE_COOKIE"),
                route("POST", "/session/abc/element", "FIND_ELEMENT"),
                route("POST", "/session/abc/elements", "FIND_ELEMENTS"),
                route("GET", "/session/abc/element/active", "ACTIVE_ELEMENT"),
                route("POST", "/session/abc/element/node-1/element", "FIND_CHILD_ELEMENT"),
                route("POST", "/session/abc/element/node-1/elements", "FIND_CHILD_ELEMENTS"),
                route("GET", "/session/abc/element/node-1/name", "ELEMENT_NAME"),
                route("GET", "/session/abc/element/node-1/text", "ELEMENT_TEXT"),
                route("GET", "/session/abc/element/node-1/rect", "ELEMENT_RECT"),
                route("GET", "/session/abc/element/node-1/displayed", "ELEMENT_DISPLAYED"),
                route("GET", "/session/abc/element/node-1/enabled", "ELEMENT_ENABLED"),
                route("GET", "/session/abc/element/node-1/selected", "ELEMENT_SELECTED"),
                route("POST", "/session/abc/element/node-1/click", "ELEMENT_CLICK"),
                route("POST", "/session/abc/element/node-1/clear", "ELEMENT_CLEAR"),
                route("POST", "/session/abc/element/node-1/value", "ELEMENT_VALUE"),
                route("GET", "/session/abc/element/node-1/attribute/colour", "ELEMENT_ATTRIBUTE"),
                route("GET", "/session/abc/element/node-1/property/colour", "ELEMENT_PROPERTY"),
                route("GET", "/session/abc/element/node-1/css/colour", "ELEMENT_CSS"));
    }

    private static Arguments route(String method, String path, String command) {
        return Arguments.of(method, path, WebDriverRoute.Command.valueOf(command));
    }
}
