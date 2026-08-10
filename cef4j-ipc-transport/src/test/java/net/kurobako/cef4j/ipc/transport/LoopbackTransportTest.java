package net.kurobako.cef4j.ipc.transport;

class LoopbackTransportTest extends CefTransportContractTest {
    @Override
    protected Pair newPair() {
        LoopbackTransport.Pair p = LoopbackTransport.create();
        return new Pair(p.a, p.b);
    }
}
