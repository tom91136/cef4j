package net.kurobako.cef4j.webdriver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

final class WebDriverRoute {
    enum Command {
        STATUS,
        NEW_SESSION,
        DELETE_SESSION,
        CURRENT_URL,
        NAVIGATE,
        TITLE,
        SOURCE,
        SCREENSHOT,
        EXECUTE_SCRIPT,
        BACK,
        FORWARD,
        REFRESH,
        GET_TIMEOUTS,
        SET_TIMEOUTS,
        GET_COOKIES,
        GET_COOKIE,
        ADD_COOKIE,
        DELETE_COOKIE,
        DELETE_COOKIES,
        FIND_ELEMENT,
        FIND_ELEMENTS,
        ACTIVE_ELEMENT,
        FIND_CHILD_ELEMENT,
        FIND_CHILD_ELEMENTS,
        ELEMENT_NAME,
        ELEMENT_TEXT,
        ELEMENT_RECT,
        ELEMENT_DISPLAYED,
        ELEMENT_ENABLED,
        ELEMENT_SELECTED,
        ELEMENT_CLICK,
        ELEMENT_CLEAR,
        ELEMENT_VALUE,
        ELEMENT_ATTRIBUTE,
        ELEMENT_PROPERTY,
        ELEMENT_CSS
    }

    private static final List<WebDriverRoute> ROUTES = routes();

    private final String method;
    private final String[] segments;
    private final Command command;

    private WebDriverRoute(String method, String path, Command command) {
        this.method = method;
        this.segments = parts(path);
        this.command = command;
    }

    static Optional<Match> match(String method, String path) {
        String[] actual = parts(path);
        for (WebDriverRoute route : ROUTES) {
            if (!route.method.equals(method) || route.segments.length != actual.length) continue;
            Map<String, String> parameters = new LinkedHashMap<>();
            boolean matched = true;
            for (int index = 0; index < actual.length; index++) {
                String expected = route.segments[index];
                if (expected.startsWith("{") && expected.endsWith("}")) {
                    parameters.put(expected.substring(1, expected.length() - 1), actual[index]);
                } else if (!expected.equals(actual[index])) {
                    matched = false;
                    break;
                }
            }
            if (matched) return Optional.of(new Match(route.command, parameters));
        }
        return Optional.empty();
    }

    private static List<WebDriverRoute> routes() {
        List<WebDriverRoute> routes = new ArrayList<>();
        add(routes, "GET", "/status", Command.STATUS);
        add(routes, "POST", "/session", Command.NEW_SESSION);
        add(routes, "DELETE", "/session/{session}", Command.DELETE_SESSION);
        add(routes, "GET", "/session/{session}/url", Command.CURRENT_URL);
        add(routes, "POST", "/session/{session}/url", Command.NAVIGATE);
        add(routes, "GET", "/session/{session}/title", Command.TITLE);
        add(routes, "GET", "/session/{session}/source", Command.SOURCE);
        add(routes, "GET", "/session/{session}/screenshot", Command.SCREENSHOT);
        add(routes, "POST", "/session/{session}/execute/sync", Command.EXECUTE_SCRIPT);
        add(routes, "POST", "/session/{session}/back", Command.BACK);
        add(routes, "POST", "/session/{session}/forward", Command.FORWARD);
        add(routes, "POST", "/session/{session}/refresh", Command.REFRESH);
        add(routes, "GET", "/session/{session}/timeouts", Command.GET_TIMEOUTS);
        add(routes, "POST", "/session/{session}/timeouts", Command.SET_TIMEOUTS);
        add(routes, "GET", "/session/{session}/cookie", Command.GET_COOKIES);
        add(routes, "POST", "/session/{session}/cookie", Command.ADD_COOKIE);
        add(routes, "DELETE", "/session/{session}/cookie", Command.DELETE_COOKIES);
        add(routes, "GET", "/session/{session}/cookie/{name}", Command.GET_COOKIE);
        add(routes, "DELETE", "/session/{session}/cookie/{name}", Command.DELETE_COOKIE);
        add(routes, "POST", "/session/{session}/element", Command.FIND_ELEMENT);
        add(routes, "POST", "/session/{session}/elements", Command.FIND_ELEMENTS);
        add(routes, "GET", "/session/{session}/element/active", Command.ACTIVE_ELEMENT);
        add(routes, "POST", "/session/{session}/element/{element}/element", Command.FIND_CHILD_ELEMENT);
        add(routes, "POST", "/session/{session}/element/{element}/elements", Command.FIND_CHILD_ELEMENTS);
        add(routes, "GET", "/session/{session}/element/{element}/name", Command.ELEMENT_NAME);
        add(routes, "GET", "/session/{session}/element/{element}/text", Command.ELEMENT_TEXT);
        add(routes, "GET", "/session/{session}/element/{element}/rect", Command.ELEMENT_RECT);
        add(routes, "GET", "/session/{session}/element/{element}/displayed", Command.ELEMENT_DISPLAYED);
        add(routes, "GET", "/session/{session}/element/{element}/enabled", Command.ELEMENT_ENABLED);
        add(routes, "GET", "/session/{session}/element/{element}/selected", Command.ELEMENT_SELECTED);
        add(routes, "POST", "/session/{session}/element/{element}/click", Command.ELEMENT_CLICK);
        add(routes, "POST", "/session/{session}/element/{element}/clear", Command.ELEMENT_CLEAR);
        add(routes, "POST", "/session/{session}/element/{element}/value", Command.ELEMENT_VALUE);
        add(routes, "GET", "/session/{session}/element/{element}/attribute/{name}", Command.ELEMENT_ATTRIBUTE);
        add(routes, "GET", "/session/{session}/element/{element}/property/{name}", Command.ELEMENT_PROPERTY);
        add(routes, "GET", "/session/{session}/element/{element}/css/{name}", Command.ELEMENT_CSS);
        return Collections.unmodifiableList(routes);
    }

    private static void add(List<WebDriverRoute> routes, String method, String path, Command command) {
        routes.add(new WebDriverRoute(method, path, command));
    }

    private static String[] parts(String path) {
        if (path.length() < 2 || path.charAt(0) != '/' || path.endsWith("/")) return new String[0];
        return path.substring(1).split("/");
    }

    static final class Match {
        private final Command command;
        private final Map<String, String> parameters;

        private Match(Command command, Map<String, String> parameters) {
            this.command = command;
            this.parameters = Collections.unmodifiableMap(new LinkedHashMap<>(parameters));
        }

        Command command() {
            return command;
        }

        String parameter(String name) {
            String value = parameters.get(name);
            if (value == null) throw new IllegalArgumentException("route parameter not present: " + name);
            return value;
        }
    }
}
