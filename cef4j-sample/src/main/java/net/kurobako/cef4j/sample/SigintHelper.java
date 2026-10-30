package net.kurobako.cef4j.sample;

/** Installs a SIGINT handler via reflection (sun.misc.Signal), falling back to a shutdown hook. */
final class SigintHelper {

    private SigintHelper() {}

    // no public API handles SIGINT on Java 11, so without sun.misc onSignal runs from a shutdown hook at JVM exit
    @SuppressWarnings("baumkuchen:jdk-internal-api")
    static void install(Runnable onSignal) {
        try {
            final Class<?> signalClass = Class.forName("sun.misc.Signal");
            final Class<?> handlerClass = Class.forName("sun.misc.SignalHandler");
            final Object sigInt = signalClass.getConstructor(String.class).newInstance("INT");
            final Object handler = java.lang.reflect.Proxy.newProxyInstance(
                    handlerClass.getClassLoader(), new Class<?>[] {handlerClass}, (proxy, method, margs) -> {
                        switch (method.getName()) {
                            case "handle":
                                onSignal.run();
                                return null;
                            case "equals":
                                return proxy == margs[0];
                            case "hashCode":
                                return System.identityHashCode(proxy);
                            case "toString":
                                return "SigintHandler";
                            default:
                                return null;
                        }
                    });
            signalClass.getMethod("handle", signalClass, handlerClass).invoke(null, sigInt, handler);
        } catch (Exception ignored) {
            Runtime.getRuntime().addShutdownHook(new Thread(onSignal));
        }
    }
}
