// GENERATED - do not edit. Run scripts/update-cdp-schema.sh.
package net.kurobako.cef4j.cdp.generated;

import net.kurobako.cef4j.cdp.CdpClient;

@SuppressWarnings("deprecation")
public final class CdpDomains {
    private final Accessibility.Client accessibility;
    private final Ads.Client ads;
    private final Animation.Client animation;
    private final Audits.Client audits;
    private final Autofill.Client autofill;
    private final BackgroundService.Client backgroundService;
    private final BluetoothEmulation.Client bluetoothEmulation;
    private final Browser.Client browser;
    private final CSS.Client cSS;
    private final CacheStorage.Client cacheStorage;
    private final Cast.Client cast;
    private final CrashReportContext.Client crashReportContext;
    private final DOM.Client dOM;
    private final DOMDebugger.Client dOMDebugger;
    private final DOMSnapshot.Client dOMSnapshot;
    private final DOMStorage.Client dOMStorage;
    private final DeviceAccess.Client deviceAccess;
    private final DeviceOrientation.Client deviceOrientation;
    private final Emulation.Client emulation;
    private final EventBreakpoints.Client eventBreakpoints;
    private final Extensions.Client extensions;
    private final FedCm.Client fedCm;
    private final Fetch.Client fetch;
    private final FileSystem.Client fileSystem;
    private final HeadlessExperimental.Client headlessExperimental;
    private final IO.Client iO;
    private final IndexedDB.Client indexedDB;
    private final Input.Client input;
    private final Inspector.Client inspector;
    private final LayerTree.Client layerTree;
    private final Log.Client log;
    private final Media.Client media;
    private final Memory.Client memory;
    private final Network.Client network;
    private final Overlay.Client overlay;
    private final PWA.Client pWA;
    private final Page.Client page;
    private final Performance.Client performance;
    private final PerformanceTimeline.Client performanceTimeline;
    private final Preload.Client preload;
    private final Security.Client security;
    private final ServiceWorker.Client serviceWorker;
    private final SmartCardEmulation.Client smartCardEmulation;
    private final Storage.Client storage;
    private final SystemInfo.Client systemInfo;
    private final Target.Client target;
    private final Tethering.Client tethering;
    private final Tracing.Client tracing;
    private final WebAudio.Client webAudio;
    private final WebAuthn.Client webAuthn;
    private final WebMCP.Client webMCP;
    private final Console.Client console;
    private final Debugger.Client debugger;
    private final HeapProfiler.Client heapProfiler;
    private final Profiler.Client profiler;
    private final Runtime.Client runtime;
    private final Schema.Client schema;
    public CdpDomains(CdpClient client) {
        accessibility = new Accessibility.Client(client);
        ads = new Ads.Client(client);
        animation = new Animation.Client(client);
        audits = new Audits.Client(client);
        autofill = new Autofill.Client(client);
        backgroundService = new BackgroundService.Client(client);
        bluetoothEmulation = new BluetoothEmulation.Client(client);
        browser = new Browser.Client(client);
        cSS = new CSS.Client(client);
        cacheStorage = new CacheStorage.Client(client);
        cast = new Cast.Client(client);
        crashReportContext = new CrashReportContext.Client(client);
        dOM = new DOM.Client(client);
        dOMDebugger = new DOMDebugger.Client(client);
        dOMSnapshot = new DOMSnapshot.Client(client);
        dOMStorage = new DOMStorage.Client(client);
        deviceAccess = new DeviceAccess.Client(client);
        deviceOrientation = new DeviceOrientation.Client(client);
        emulation = new Emulation.Client(client);
        eventBreakpoints = new EventBreakpoints.Client(client);
        extensions = new Extensions.Client(client);
        fedCm = new FedCm.Client(client);
        fetch = new Fetch.Client(client);
        fileSystem = new FileSystem.Client(client);
        headlessExperimental = new HeadlessExperimental.Client(client);
        iO = new IO.Client(client);
        indexedDB = new IndexedDB.Client(client);
        input = new Input.Client(client);
        inspector = new Inspector.Client(client);
        layerTree = new LayerTree.Client(client);
        log = new Log.Client(client);
        media = new Media.Client(client);
        memory = new Memory.Client(client);
        network = new Network.Client(client);
        overlay = new Overlay.Client(client);
        pWA = new PWA.Client(client);
        page = new Page.Client(client);
        performance = new Performance.Client(client);
        performanceTimeline = new PerformanceTimeline.Client(client);
        preload = new Preload.Client(client);
        security = new Security.Client(client);
        serviceWorker = new ServiceWorker.Client(client);
        smartCardEmulation = new SmartCardEmulation.Client(client);
        storage = new Storage.Client(client);
        systemInfo = new SystemInfo.Client(client);
        target = new Target.Client(client);
        tethering = new Tethering.Client(client);
        tracing = new Tracing.Client(client);
        webAudio = new WebAudio.Client(client);
        webAuthn = new WebAuthn.Client(client);
        webMCP = new WebMCP.Client(client);
        console = new Console.Client(client);
        debugger = new Debugger.Client(client);
        heapProfiler = new HeapProfiler.Client(client);
        profiler = new Profiler.Client(client);
        runtime = new Runtime.Client(client);
        schema = new Schema.Client(client);
    }
    public Accessibility.Client accessibility() { return accessibility; }
    public Ads.Client ads() { return ads; }
    public Animation.Client animation() { return animation; }
    public Audits.Client audits() { return audits; }
    public Autofill.Client autofill() { return autofill; }
    public BackgroundService.Client backgroundService() { return backgroundService; }
    public BluetoothEmulation.Client bluetoothEmulation() { return bluetoothEmulation; }
    public Browser.Client browser() { return browser; }
    public CSS.Client cSS() { return cSS; }
    public CacheStorage.Client cacheStorage() { return cacheStorage; }
    public Cast.Client cast() { return cast; }
    public CrashReportContext.Client crashReportContext() { return crashReportContext; }
    public DOM.Client dOM() { return dOM; }
    public DOMDebugger.Client dOMDebugger() { return dOMDebugger; }
    public DOMSnapshot.Client dOMSnapshot() { return dOMSnapshot; }
    public DOMStorage.Client dOMStorage() { return dOMStorage; }
    public DeviceAccess.Client deviceAccess() { return deviceAccess; }
    public DeviceOrientation.Client deviceOrientation() { return deviceOrientation; }
    public Emulation.Client emulation() { return emulation; }
    public EventBreakpoints.Client eventBreakpoints() { return eventBreakpoints; }
    public Extensions.Client extensions() { return extensions; }
    public FedCm.Client fedCm() { return fedCm; }
    public Fetch.Client fetch() { return fetch; }
    public FileSystem.Client fileSystem() { return fileSystem; }
    public HeadlessExperimental.Client headlessExperimental() { return headlessExperimental; }
    public IO.Client iO() { return iO; }
    public IndexedDB.Client indexedDB() { return indexedDB; }
    public Input.Client input() { return input; }
    public Inspector.Client inspector() { return inspector; }
    public LayerTree.Client layerTree() { return layerTree; }
    public Log.Client log() { return log; }
    public Media.Client media() { return media; }
    public Memory.Client memory() { return memory; }
    public Network.Client network() { return network; }
    public Overlay.Client overlay() { return overlay; }
    public PWA.Client pWA() { return pWA; }
    public Page.Client page() { return page; }
    public Performance.Client performance() { return performance; }
    public PerformanceTimeline.Client performanceTimeline() { return performanceTimeline; }
    public Preload.Client preload() { return preload; }
    public Security.Client security() { return security; }
    public ServiceWorker.Client serviceWorker() { return serviceWorker; }
    public SmartCardEmulation.Client smartCardEmulation() { return smartCardEmulation; }
    public Storage.Client storage() { return storage; }
    public SystemInfo.Client systemInfo() { return systemInfo; }
    public Target.Client target() { return target; }
    public Tethering.Client tethering() { return tethering; }
    public Tracing.Client tracing() { return tracing; }
    public WebAudio.Client webAudio() { return webAudio; }
    public WebAuthn.Client webAuthn() { return webAuthn; }
    public WebMCP.Client webMCP() { return webMCP; }
    public Console.Client console() { return console; }
    public Debugger.Client debugger() { return debugger; }
    public HeapProfiler.Client heapProfiler() { return heapProfiler; }
    public Profiler.Client profiler() { return profiler; }
    public Runtime.Client runtime() { return runtime; }
    public Schema.Client schema() { return schema; }
}
