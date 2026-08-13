package net.kurobako.cef4j.cdp;

@FunctionalInterface
public interface CdpSubscription extends AutoCloseable {
    @Override
    void close();
}
