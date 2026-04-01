package net.kurobako.cef4j.sample;

/** Installs a SIGINT handler via reflection (sun.misc.Signal), falling back to a shutdown hook. */
final class SigintHelper {

    private SigintHelper() {}

    static void install(Runnable onSignal) {
        try {
            Class<?> signalClass = Class.forName("sun.misc.Signal");
            Class<?> handlerClass = Class.forName("sun.misc.SignalHandler");
            Object sigInt = signalClass.getConstructor(String.class).newInstance("INT");
            Object handler = java.lang.reflect.Proxy.newProxyInstance(
                    handlerClass.getClassLoader(), new Class<?>[] {handlerClass}, (proxy, method, margs) -> {
                        if ("handle".equals(method.getName())) {
                            onSignal.run();
                        }
                        return null;
                    });
            signalClass.getMethod("handle", signalClass, handlerClass).invoke(null, sigInt, handler);
        } catch (Exception e) {
            Runtime.getRuntime().addShutdownHook(new Thread(onSignal));
        }
    }
}
