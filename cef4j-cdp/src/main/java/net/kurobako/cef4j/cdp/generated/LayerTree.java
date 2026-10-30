// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpValue;
import net.kurobako.cef4j.policy.NullableBoundary;
import org.jspecify.annotations.Nullable;

/**
 * Chrome DevTools Protocol LayerTree domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/LayerTree.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class LayerTree {
    private LayerTree() {}
    /**
     * Unique Layer identifier.
     */
    public static final class LayerId implements CdpValue<String> {
        public final String value;
        public LayerId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof LayerId)) return false;
            return value.equals(((LayerId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "LayerId(" + value + ")"; }
    }
    /**
     * Unique snapshot identifier.
     */
    public static final class SnapshotId implements CdpValue<String> {
        public final String value;
        public SnapshotId(String value) { this.value = java.util.Objects.requireNonNull(value); }
        @Override public String value() { return value; }
        @Override public boolean equals(@org.jspecify.annotations.Nullable Object other) {
            if (this == other) return true;
            if (!(other instanceof SnapshotId)) return false;
            return value.equals(((SnapshotId) other).value);
        }
        @Override public int hashCode() { return value.hashCode(); }
        @Override public String toString() { return "SnapshotId(" + value + ")"; }
    }
    /**
     * Rectangle where scrolling happens on the main thread.
     */
    public static final class ScrollRect extends CdpObject {
        public ScrollRect() {}
        private ScrollRect(Map<String, Object> values) { super(values); }
        public static ScrollRect fromMap(Map<String, Object> values) {
            return new ScrollRect(values);
        }
        /**
         * Reason for rectangle to force scrolling on the main thread
         */
        public enum TypeValues implements CdpValue<String> {
            REPAINTSONSCROLL("RepaintsOnScroll"),
            TOUCHEVENTHANDLER("TouchEventHandler"),
            WHEELEVENTHANDLER("WheelEventHandler");
            public final String value;
            TypeValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static TypeValues of(String value) {
                for (TypeValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown TypeValues value: " + value);
            }
        }
        /**
         * Rectangle itself.
         * @return the protocol field value
         */
        public DOM.Rect rect() {
            return java.util.Objects.requireNonNull(DOM.Rect.fromMap(CdpObject.requireObjectMap(require("rect"))));
        }
        /**
         * Reason for rectangle to force scrolling on the main thread
         * @return the protocol field value
         */
        public ScrollRect.TypeValues type() {
            return ScrollRect.TypeValues.of((String) require("type"));
        }
        /**
         * Rectangle itself.
         * @param rect field value
         * @return this model
         */
        public ScrollRect rect(DOM.Rect rect) {
            set("rect", rect);
            return this;
        }
        /**
         * Reason for rectangle to force scrolling on the main thread
         * @param type field value
         * @return this model
         */
        public ScrollRect type(ScrollRect.TypeValues type) {
            set("type", type);
            return this;
        }
    }
    /**
     * Sticky position constraints.
     */
    public static final class StickyPositionConstraint extends CdpObject {
        public StickyPositionConstraint() {}
        private StickyPositionConstraint(Map<String, Object> values) { super(values); }
        public static StickyPositionConstraint fromMap(Map<String, Object> values) {
            return new StickyPositionConstraint(values);
        }
        /**
         * Layout rectangle of the sticky element before being shifted
         * @return the protocol field value
         */
        public DOM.Rect stickyBoxRect() {
            return java.util.Objects.requireNonNull(DOM.Rect.fromMap(CdpObject.requireObjectMap(require("stickyBoxRect"))));
        }
        /**
         * Layout rectangle of the containing block of the sticky element
         * @return the protocol field value
         */
        public DOM.Rect containingBlockRect() {
            return java.util.Objects.requireNonNull(DOM.Rect.fromMap(CdpObject.requireObjectMap(require("containingBlockRect"))));
        }
        /**
         * The nearest sticky layer that shifts the sticky box
         * @return the protocol field value, empty when absent
         */
        public Optional<LayerTree.LayerId> nearestLayerShiftingStickyBox() {
            return raw("nearestLayerShiftingStickyBox").map(value -> new LayerTree.LayerId((String) value));
        }
        /**
         * The nearest sticky layer that shifts the containing block
         * @return the protocol field value, empty when absent
         */
        public Optional<LayerTree.LayerId> nearestLayerShiftingContainingBlock() {
            return raw("nearestLayerShiftingContainingBlock").map(value -> new LayerTree.LayerId((String) value));
        }
        /**
         * Layout rectangle of the sticky element before being shifted
         * @param stickyBoxRect field value
         * @return this model
         */
        public StickyPositionConstraint stickyBoxRect(DOM.Rect stickyBoxRect) {
            set("stickyBoxRect", stickyBoxRect);
            return this;
        }
        /**
         * Layout rectangle of the containing block of the sticky element
         * @param containingBlockRect field value
         * @return this model
         */
        public StickyPositionConstraint containingBlockRect(DOM.Rect containingBlockRect) {
            set("containingBlockRect", containingBlockRect);
            return this;
        }
        /**
         * The nearest sticky layer that shifts the sticky box
         * @param nearestLayerShiftingStickyBox field value
         * @return this model
         */
        public StickyPositionConstraint nearestLayerShiftingStickyBox(LayerTree.LayerId nearestLayerShiftingStickyBox) {
            set("nearestLayerShiftingStickyBox", nearestLayerShiftingStickyBox);
            return this;
        }
        /**
         * The nearest sticky layer that shifts the containing block
         * @param nearestLayerShiftingContainingBlock field value
         * @return this model
         */
        public StickyPositionConstraint nearestLayerShiftingContainingBlock(LayerTree.LayerId nearestLayerShiftingContainingBlock) {
            set("nearestLayerShiftingContainingBlock", nearestLayerShiftingContainingBlock);
            return this;
        }
    }
    /**
     * Serialized fragment of layer picture along with its offset within the layer.
     */
    public static final class PictureTile extends CdpObject {
        public PictureTile() {}
        private PictureTile(Map<String, Object> values) { super(values); }
        public static PictureTile fromMap(Map<String, Object> values) {
            return new PictureTile(values);
        }
        /**
         * Offset from owning layer left boundary
         * @return the protocol field value
         */
        public double x() {
            return ((Number) require("x")).doubleValue();
        }
        /**
         * Offset from owning layer top boundary
         * @return the protocol field value
         */
        public double y() {
            return ((Number) require("y")).doubleValue();
        }
        /**
         * Base64-encoded snapshot data. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        public String picture() {
            return (String) require("picture");
        }
        /**
         * Offset from owning layer left boundary
         * @param x field value
         * @return this model
         */
        public PictureTile x(double x) {
            set("x", x);
            return this;
        }
        /**
         * Offset from owning layer top boundary
         * @param y field value
         * @return this model
         */
        public PictureTile y(double y) {
            set("y", y);
            return this;
        }
        /**
         * Base64-encoded snapshot data. (Encoded as a base64 string when passed over JSON)
         * @param picture field value
         * @return this model
         */
        public PictureTile picture(String picture) {
            set("picture", picture);
            return this;
        }
    }
    /**
     * Information about a compositing layer.
     */
    public static final class Layer extends CdpObject {
        public Layer() {}
        private Layer(Map<String, Object> values) { super(values); }
        public static Layer fromMap(Map<String, Object> values) {
            return new Layer(values);
        }
        /**
         * The unique id for this layer.
         * @return the protocol field value
         */
        public LayerTree.LayerId layerId() {
            return new LayerTree.LayerId((String) require("layerId"));
        }
        /**
         * The id of parent (not present for root).
         * @return the protocol field value, empty when absent
         */
        public Optional<LayerTree.LayerId> parentLayerId() {
            return raw("parentLayerId").map(value -> new LayerTree.LayerId((String) value));
        }
        /**
         * The backend id for the node associated with this layer.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return raw("backendNodeId").map(value -> new DOM.BackendNodeId(((Number) value).longValue()));
        }
        /**
         * Offset from parent layer, X coordinate.
         * @return the protocol field value
         */
        public double offsetX() {
            return ((Number) require("offsetX")).doubleValue();
        }
        /**
         * Offset from parent layer, Y coordinate.
         * @return the protocol field value
         */
        public double offsetY() {
            return ((Number) require("offsetY")).doubleValue();
        }
        /**
         * Layer width.
         * @return the protocol field value
         */
        public double width() {
            return ((Number) require("width")).doubleValue();
        }
        /**
         * Layer height.
         * @return the protocol field value
         */
        public double height() {
            return ((Number) require("height")).doubleValue();
        }
        /**
         * Transformation matrix for layer, default is identity matrix
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<Double>> transform() {
            return raw("transform").flatMap(value -> list(value, element0 -> ((Number) element0).doubleValue()));
        }
        /**
         * Transform anchor point X, absent if no transform specified
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble anchorX() {
            return raw("anchorX").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Transform anchor point Y, absent if no transform specified
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble anchorY() {
            return raw("anchorY").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Transform anchor point Z, absent if no transform specified
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble anchorZ() {
            return raw("anchorZ").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * Indicates how many time this layer has painted.
         * @return the protocol field value
         */
        public long paintCount() {
            return ((Number) require("paintCount")).longValue();
        }
        /**
         * Indicates whether this layer hosts any content, rather than being used for transform/scrolling purposes only.
         * @return the protocol field value
         */
        public boolean drawsContent() {
            return (Boolean) require("drawsContent");
        }
        /**
         * Set if layer is not visible.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> invisible() {
            return raw("invisible").map(value -> (Boolean) value);
        }
        /**
         * Rectangles scrolling on main thread only.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<LayerTree.ScrollRect>> scrollRects() {
            return raw("scrollRects").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(LayerTree.ScrollRect.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Sticky position constraint information
         * @return the protocol field value, empty when absent
         */
        public Optional<LayerTree.StickyPositionConstraint> stickyPositionConstraint() {
            return raw("stickyPositionConstraint").flatMap(CdpObject::objectMap).map(LayerTree.StickyPositionConstraint::fromMap);
        }
        /**
         * The unique id for this layer.
         * @param layerId field value
         * @return this model
         */
        public Layer layerId(LayerTree.LayerId layerId) {
            set("layerId", layerId);
            return this;
        }
        /**
         * The id of parent (not present for root).
         * @param parentLayerId field value
         * @return this model
         */
        public Layer parentLayerId(LayerTree.LayerId parentLayerId) {
            set("parentLayerId", parentLayerId);
            return this;
        }
        /**
         * The backend id for the node associated with this layer.
         * @param backendNodeId field value
         * @return this model
         */
        public Layer backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * Offset from parent layer, X coordinate.
         * @param offsetX field value
         * @return this model
         */
        public Layer offsetX(double offsetX) {
            set("offsetX", offsetX);
            return this;
        }
        /**
         * Offset from parent layer, Y coordinate.
         * @param offsetY field value
         * @return this model
         */
        public Layer offsetY(double offsetY) {
            set("offsetY", offsetY);
            return this;
        }
        /**
         * Layer width.
         * @param width field value
         * @return this model
         */
        public Layer width(double width) {
            set("width", width);
            return this;
        }
        /**
         * Layer height.
         * @param height field value
         * @return this model
         */
        public Layer height(double height) {
            set("height", height);
            return this;
        }
        /**
         * Transformation matrix for layer, default is identity matrix
         * @param transform field value
         * @return this model
         */
        public Layer transform(java.util.List<Double> transform) {
            set("transform", transform);
            return this;
        }
        /**
         * Transform anchor point X, absent if no transform specified
         * @param anchorX field value
         * @return this model
         */
        public Layer anchorX(double anchorX) {
            set("anchorX", anchorX);
            return this;
        }
        /**
         * Transform anchor point Y, absent if no transform specified
         * @param anchorY field value
         * @return this model
         */
        public Layer anchorY(double anchorY) {
            set("anchorY", anchorY);
            return this;
        }
        /**
         * Transform anchor point Z, absent if no transform specified
         * @param anchorZ field value
         * @return this model
         */
        public Layer anchorZ(double anchorZ) {
            set("anchorZ", anchorZ);
            return this;
        }
        /**
         * Indicates how many time this layer has painted.
         * @param paintCount field value
         * @return this model
         */
        public Layer paintCount(long paintCount) {
            set("paintCount", paintCount);
            return this;
        }
        /**
         * Indicates whether this layer hosts any content, rather than being used for transform/scrolling purposes only.
         * @param drawsContent field value
         * @return this model
         */
        public Layer drawsContent(boolean drawsContent) {
            set("drawsContent", drawsContent);
            return this;
        }
        /**
         * Set if layer is not visible.
         * @param invisible field value
         * @return this model
         */
        public Layer invisible(boolean invisible) {
            set("invisible", invisible);
            return this;
        }
        /**
         * Rectangles scrolling on main thread only.
         * @param scrollRects field value
         * @return this model
         */
        public Layer scrollRects(java.util.List<LayerTree.ScrollRect> scrollRects) {
            set("scrollRects", scrollRects);
            return this;
        }
        /**
         * Sticky position constraint information
         * @param stickyPositionConstraint field value
         * @return this model
         */
        public Layer stickyPositionConstraint(LayerTree.StickyPositionConstraint stickyPositionConstraint) {
            set("stickyPositionConstraint", stickyPositionConstraint);
            return this;
        }
    }
    /**
     * Provides the reasons why the given layer was composited.
     */
    public static final class CompositingReasonsRequest extends CdpObject {
        public CompositingReasonsRequest() {}
        /**
         * Provides the reasons why the given layer was composited.
         * @param layerId protocol value
         */
        public CompositingReasonsRequest(LayerTree.LayerId layerId) {
            layerId(layerId);
        }
        public static CompositingReasonsRequest fromMap(Map<String, Object> values) {
            final CompositingReasonsRequest instance_ = new CompositingReasonsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The id of the layer for which we want to get the reasons it was composited.
         * @return the protocol field value
         */
        public LayerTree.LayerId layerId() {
            return new LayerTree.LayerId((String) require("layerId"));
        }
        /**
         * The id of the layer for which we want to get the reasons it was composited.
         * @param layerId field value
         * @return this model
         */
        public CompositingReasonsRequest layerId(LayerTree.LayerId layerId) {
            set("layerId", layerId);
            return this;
        }
    }
    /**
     * Returns the snapshot identifier.
     */
    public static final class LoadSnapshotRequest extends CdpObject {
        public LoadSnapshotRequest() {}
        /**
         * Returns the snapshot identifier.
         * @param tiles protocol value
         */
        public LoadSnapshotRequest(java.util.List<LayerTree.PictureTile> tiles) {
            tiles(tiles);
        }
        public static LoadSnapshotRequest fromMap(Map<String, Object> values) {
            final LoadSnapshotRequest instance_ = new LoadSnapshotRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An array of tiles composing the snapshot.
         * @return the protocol field value
         */
        public java.util.List<LayerTree.PictureTile> tiles() {
            return CdpObject.requireList(require("tiles"), element0 -> java.util.Objects.requireNonNull(LayerTree.PictureTile.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * An array of tiles composing the snapshot.
         * @param tiles field value
         * @return this model
         */
        public LoadSnapshotRequest tiles(java.util.List<LayerTree.PictureTile> tiles) {
            set("tiles", tiles);
            return this;
        }
    }
    /**
     * Returns the layer snapshot identifier.
     */
    public static final class MakeSnapshotRequest extends CdpObject {
        public MakeSnapshotRequest() {}
        /**
         * Returns the layer snapshot identifier.
         * @param layerId protocol value
         */
        public MakeSnapshotRequest(LayerTree.LayerId layerId) {
            layerId(layerId);
        }
        public static MakeSnapshotRequest fromMap(Map<String, Object> values) {
            final MakeSnapshotRequest instance_ = new MakeSnapshotRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The id of the layer.
         * @return the protocol field value
         */
        public LayerTree.LayerId layerId() {
            return new LayerTree.LayerId((String) require("layerId"));
        }
        /**
         * The id of the layer.
         * @param layerId field value
         * @return this model
         */
        public MakeSnapshotRequest layerId(LayerTree.LayerId layerId) {
            set("layerId", layerId);
            return this;
        }
    }
    /**
     * Request parameters for LayerTree.profileSnapshot.
     */
    public static final class ProfileSnapshotRequest extends CdpObject {
        public ProfileSnapshotRequest() {}
        /**
         * Creates a new ProfileSnapshotRequest with all required parameters.
         * @param snapshotId protocol value
         */
        public ProfileSnapshotRequest(LayerTree.SnapshotId snapshotId) {
            snapshotId(snapshotId);
        }
        public static ProfileSnapshotRequest fromMap(Map<String, Object> values) {
            final ProfileSnapshotRequest instance_ = new ProfileSnapshotRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The id of the layer snapshot.
         * @return the protocol field value
         */
        public LayerTree.SnapshotId snapshotId() {
            return new LayerTree.SnapshotId((String) require("snapshotId"));
        }
        /**
         * The maximum number of times to replay the snapshot (1, if not specified).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong minRepeatCount() {
            return raw("minRepeatCount").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * The minimum duration (in seconds) to replay the snapshot.
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble minDuration() {
            return raw("minDuration").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * The clip rectangle to apply when replaying the snapshot.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.Rect> clipRect() {
            return raw("clipRect").flatMap(CdpObject::objectMap).map(DOM.Rect::fromMap);
        }
        /**
         * The id of the layer snapshot.
         * @param snapshotId field value
         * @return this model
         */
        public ProfileSnapshotRequest snapshotId(LayerTree.SnapshotId snapshotId) {
            set("snapshotId", snapshotId);
            return this;
        }
        /**
         * The maximum number of times to replay the snapshot (1, if not specified).
         * @param minRepeatCount field value
         * @return this model
         */
        public ProfileSnapshotRequest minRepeatCount(long minRepeatCount) {
            set("minRepeatCount", minRepeatCount);
            return this;
        }
        /**
         * The minimum duration (in seconds) to replay the snapshot.
         * @param minDuration field value
         * @return this model
         */
        public ProfileSnapshotRequest minDuration(double minDuration) {
            set("minDuration", minDuration);
            return this;
        }
        /**
         * The clip rectangle to apply when replaying the snapshot.
         * @param clipRect field value
         * @return this model
         */
        public ProfileSnapshotRequest clipRect(DOM.Rect clipRect) {
            set("clipRect", clipRect);
            return this;
        }
    }
    /**
     * Releases layer snapshot captured by the back-end.
     */
    public static final class ReleaseSnapshotRequest extends CdpObject {
        public ReleaseSnapshotRequest() {}
        /**
         * Releases layer snapshot captured by the back-end.
         * @param snapshotId protocol value
         */
        public ReleaseSnapshotRequest(LayerTree.SnapshotId snapshotId) {
            snapshotId(snapshotId);
        }
        public static ReleaseSnapshotRequest fromMap(Map<String, Object> values) {
            final ReleaseSnapshotRequest instance_ = new ReleaseSnapshotRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The id of the layer snapshot.
         * @return the protocol field value
         */
        public LayerTree.SnapshotId snapshotId() {
            return new LayerTree.SnapshotId((String) require("snapshotId"));
        }
        /**
         * The id of the layer snapshot.
         * @param snapshotId field value
         * @return this model
         */
        public ReleaseSnapshotRequest snapshotId(LayerTree.SnapshotId snapshotId) {
            set("snapshotId", snapshotId);
            return this;
        }
    }
    /**
     * Replays the layer snapshot and returns the resulting bitmap.
     */
    public static final class ReplaySnapshotRequest extends CdpObject {
        public ReplaySnapshotRequest() {}
        /**
         * Replays the layer snapshot and returns the resulting bitmap.
         * @param snapshotId protocol value
         */
        public ReplaySnapshotRequest(LayerTree.SnapshotId snapshotId) {
            snapshotId(snapshotId);
        }
        public static ReplaySnapshotRequest fromMap(Map<String, Object> values) {
            final ReplaySnapshotRequest instance_ = new ReplaySnapshotRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The id of the layer snapshot.
         * @return the protocol field value
         */
        public LayerTree.SnapshotId snapshotId() {
            return new LayerTree.SnapshotId((String) require("snapshotId"));
        }
        /**
         * The first step to replay from (replay from the very start if not specified).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong fromStep() {
            return raw("fromStep").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * The last step to replay to (replay till the end if not specified).
         * @return the protocol field value, empty when absent
         */
        public OptionalLong toStep() {
            return raw("toStep").flatMap(CdpObject::numberAsLong).stream().mapToLong(Long::longValue).findFirst();
        }
        /**
         * The scale to apply while replaying (defaults to 1).
         * @return the protocol field value, empty when absent
         */
        public OptionalDouble scale() {
            return raw("scale").flatMap(CdpObject::numberAsDouble).stream().mapToDouble(Double::doubleValue).findFirst();
        }
        /**
         * The id of the layer snapshot.
         * @param snapshotId field value
         * @return this model
         */
        public ReplaySnapshotRequest snapshotId(LayerTree.SnapshotId snapshotId) {
            set("snapshotId", snapshotId);
            return this;
        }
        /**
         * The first step to replay from (replay from the very start if not specified).
         * @param fromStep field value
         * @return this model
         */
        public ReplaySnapshotRequest fromStep(long fromStep) {
            set("fromStep", fromStep);
            return this;
        }
        /**
         * The last step to replay to (replay till the end if not specified).
         * @param toStep field value
         * @return this model
         */
        public ReplaySnapshotRequest toStep(long toStep) {
            set("toStep", toStep);
            return this;
        }
        /**
         * The scale to apply while replaying (defaults to 1).
         * @param scale field value
         * @return this model
         */
        public ReplaySnapshotRequest scale(double scale) {
            set("scale", scale);
            return this;
        }
    }
    /**
     * Replays the layer snapshot and returns canvas log.
     */
    public static final class SnapshotCommandLogRequest extends CdpObject {
        public SnapshotCommandLogRequest() {}
        /**
         * Replays the layer snapshot and returns canvas log.
         * @param snapshotId protocol value
         */
        public SnapshotCommandLogRequest(LayerTree.SnapshotId snapshotId) {
            snapshotId(snapshotId);
        }
        public static SnapshotCommandLogRequest fromMap(Map<String, Object> values) {
            final SnapshotCommandLogRequest instance_ = new SnapshotCommandLogRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The id of the layer snapshot.
         * @return the protocol field value
         */
        public LayerTree.SnapshotId snapshotId() {
            return new LayerTree.SnapshotId((String) require("snapshotId"));
        }
        /**
         * The id of the layer snapshot.
         * @param snapshotId field value
         * @return this model
         */
        public SnapshotCommandLogRequest snapshotId(LayerTree.SnapshotId snapshotId) {
            set("snapshotId", snapshotId);
            return this;
        }
    }
    /**
     * Provides the reasons why the given layer was composited.
     */
    public static final class CompositingReasonsResult extends CdpObject {
        public CompositingReasonsResult() {}
        private CompositingReasonsResult(Map<String, Object> values) { super(values); }
        public static CompositingReasonsResult fromMap(Map<String, Object> values) {
            return new CompositingReasonsResult(values);
        }
        /**
         * A list of strings specifying reasons for the given layer to become composited.
         * @return the protocol field value
         */
        public java.util.List<String> compositingReasons() {
            return CdpObject.requireList(require("compositingReasons"), element0 -> (String) element0);
        }
        /**
         * A list of strings specifying reason IDs for the given layer to become composited.
         * @return the protocol field value
         */
        public java.util.List<String> compositingReasonIds() {
            return CdpObject.requireList(require("compositingReasonIds"), element0 -> (String) element0);
        }
        /**
         * A list of strings specifying reasons for the given layer to become composited.
         * @param compositingReasons field value
         * @return this model
         */
        public CompositingReasonsResult compositingReasons(java.util.List<String> compositingReasons) {
            set("compositingReasons", compositingReasons);
            return this;
        }
        /**
         * A list of strings specifying reason IDs for the given layer to become composited.
         * @param compositingReasonIds field value
         * @return this model
         */
        public CompositingReasonsResult compositingReasonIds(java.util.List<String> compositingReasonIds) {
            set("compositingReasonIds", compositingReasonIds);
            return this;
        }
    }
    /**
     * Payload of the LayerTree.layerPainted event.
     */
    public static final class LayerPaintedEvent extends CdpObject {
        public LayerPaintedEvent() {}
        private LayerPaintedEvent(Map<String, Object> values) { super(values); }
        public static LayerPaintedEvent fromMap(Map<String, Object> values) {
            return new LayerPaintedEvent(values);
        }
        /**
         * The id of the painted layer.
         * @return the protocol field value
         */
        public LayerTree.LayerId layerId() {
            return new LayerTree.LayerId((String) require("layerId"));
        }
        /**
         * Clip rectangle.
         * @return the protocol field value
         */
        public DOM.Rect clip() {
            return java.util.Objects.requireNonNull(DOM.Rect.fromMap(CdpObject.requireObjectMap(require("clip"))));
        }
        /**
         * The id of the painted layer.
         * @param layerId field value
         * @return this model
         */
        public LayerPaintedEvent layerId(LayerTree.LayerId layerId) {
            set("layerId", layerId);
            return this;
        }
        /**
         * Clip rectangle.
         * @param clip field value
         * @return this model
         */
        public LayerPaintedEvent clip(DOM.Rect clip) {
            set("clip", clip);
            return this;
        }
    }
    /**
     * Payload of the LayerTree.layerTreeDidChange event.
     */
    public static final class LayerTreeDidChangeEvent extends CdpObject {
        public LayerTreeDidChangeEvent() {}
        private LayerTreeDidChangeEvent(Map<String, Object> values) { super(values); }
        public static LayerTreeDidChangeEvent fromMap(Map<String, Object> values) {
            return new LayerTreeDidChangeEvent(values);
        }
        /**
         * Layer tree, absent if not in the compositing mode.
         * @return the protocol field value, empty when absent
         */
        public Optional<java.util.List<LayerTree.Layer>> layers() {
            return raw("layers").flatMap(value -> list(value, element0 -> java.util.Objects.requireNonNull(LayerTree.Layer.fromMap(CdpObject.requireObjectMap(element0)))));
        }
        /**
         * Layer tree, absent if not in the compositing mode.
         * @param layers field value
         * @return this model
         */
        public LayerTreeDidChangeEvent layers(java.util.List<LayerTree.Layer> layers) {
            set("layers", layers);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Provides the reasons why the given layer was composited.
         * @param layerId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<CompositingReasonsResult> compositingReasons(LayerTree.LayerId layerId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("layerId", CdpObject.json(layerId));
            return client.call("LayerTree.compositingReasons", params, result_ -> new CompositingReasonsResult(result_));
        }
        /**
         * Provides the reasons why the given layer was composited.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CompositingReasonsResult> compositingReasons(CompositingReasonsRequest request) {
            return client.call("LayerTree.compositingReasons", request == null ? null : request.toMap(), result_ -> new CompositingReasonsResult(result_));
        }
        /**
         * Disables compositing tree inspection.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("LayerTree.disable", null);
        }
        /**
         * Enables compositing tree inspection.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("LayerTree.enable", null);
        }
        /**
         * Returns the snapshot identifier.
         * @param tiles protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<LayerTree.SnapshotId> loadSnapshot(java.util.List<LayerTree.PictureTile> tiles) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("tiles", CdpObject.json(tiles));
            return client.call("LayerTree.loadSnapshot", params, result_ -> new LayerTree.SnapshotId((String) java.util.Objects.requireNonNull(result_.get("snapshotId"))));
        }
        /**
         * Returns the snapshot identifier.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<LayerTree.SnapshotId> loadSnapshot(LoadSnapshotRequest request) {
            return client.call("LayerTree.loadSnapshot", request == null ? null : request.toMap(), result_ -> new LayerTree.SnapshotId((String) java.util.Objects.requireNonNull(result_.get("snapshotId"))));
        }
        /**
         * Returns the layer snapshot identifier.
         * @param layerId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<LayerTree.SnapshotId> makeSnapshot(LayerTree.LayerId layerId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("layerId", CdpObject.json(layerId));
            return client.call("LayerTree.makeSnapshot", params, result_ -> new LayerTree.SnapshotId((String) java.util.Objects.requireNonNull(result_.get("snapshotId"))));
        }
        /**
         * Returns the layer snapshot identifier.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<LayerTree.SnapshotId> makeSnapshot(MakeSnapshotRequest request) {
            return client.call("LayerTree.makeSnapshot", request == null ? null : request.toMap(), result_ -> new LayerTree.SnapshotId((String) java.util.Objects.requireNonNull(result_.get("snapshotId"))));
        }
        /**
         * Invokes LayerTree.profileSnapshot.
         * @param snapshotId protocol value
         * @param minRepeatCount protocol value
         * @param minDuration protocol value
         * @param clipRect protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.List<Double>>> profileSnapshot(LayerTree.SnapshotId snapshotId, @Nullable Long minRepeatCount, @Nullable Double minDuration, DOM.@Nullable Rect clipRect) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("snapshotId", CdpObject.json(snapshotId));
            if (minRepeatCount != null) params.put("minRepeatCount", CdpObject.json(minRepeatCount));
            if (minDuration != null) params.put("minDuration", CdpObject.json(minDuration));
            if (clipRect != null) params.put("clipRect", CdpObject.json(clipRect));
            return client.call("LayerTree.profileSnapshot", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("timings")), element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue())));
        }
        /**
         * Invokes LayerTree.profileSnapshot with the required parameters.
         * @param snapshotId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.List<Double>>> profileSnapshot(LayerTree.SnapshotId snapshotId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("snapshotId", CdpObject.json(snapshotId));
            return client.call("LayerTree.profileSnapshot", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("timings")), element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue())));
        }
        /**
         * Invokes LayerTree.profileSnapshot with a request object.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.List<Double>>> profileSnapshot(ProfileSnapshotRequest request) {
            return client.call("LayerTree.profileSnapshot", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("timings")), element0 -> CdpObject.requireList(element0, element1 -> ((Number) element1).doubleValue())));
        }
        /**
         * Releases layer snapshot captured by the back-end.
         * @param snapshotId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> releaseSnapshot(LayerTree.SnapshotId snapshotId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("snapshotId", CdpObject.json(snapshotId));
            return client.call("LayerTree.releaseSnapshot", params);
        }
        /**
         * Releases layer snapshot captured by the back-end.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> releaseSnapshot(ReleaseSnapshotRequest request) {
            return client.call("LayerTree.releaseSnapshot", request == null ? null : request.toMap());
        }
        /**
         * Replays the layer snapshot and returns the resulting bitmap.
         * @param snapshotId protocol value
         * @param fromStep protocol value
         * @param toStep protocol value
         * @param scale protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> replaySnapshot(LayerTree.SnapshotId snapshotId, @Nullable Long fromStep, @Nullable Long toStep, @Nullable Double scale) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("snapshotId", CdpObject.json(snapshotId));
            if (fromStep != null) params.put("fromStep", CdpObject.json(fromStep));
            if (toStep != null) params.put("toStep", CdpObject.json(toStep));
            if (scale != null) params.put("scale", CdpObject.json(scale));
            return client.call("LayerTree.replaySnapshot", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("dataURL")));
        }
        /**
         * Replays the layer snapshot and returns the resulting bitmap.
         * @param snapshotId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<String> replaySnapshot(LayerTree.SnapshotId snapshotId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("snapshotId", CdpObject.json(snapshotId));
            return client.call("LayerTree.replaySnapshot", params, result_ -> (String) java.util.Objects.requireNonNull(result_.get("dataURL")));
        }
        /**
         * Replays the layer snapshot and returns the resulting bitmap.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<String> replaySnapshot(ReplaySnapshotRequest request) {
            return client.call("LayerTree.replaySnapshot", request == null ? null : request.toMap(), result_ -> (String) java.util.Objects.requireNonNull(result_.get("dataURL")));
        }
        /**
         * Replays the layer snapshot and returns canvas log.
         * @param snapshotId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.Map<String, Object>>> snapshotCommandLog(LayerTree.SnapshotId snapshotId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("snapshotId", CdpObject.json(snapshotId));
            return client.call("LayerTree.snapshotCommandLog", params, result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("commandLog")), element0 -> CdpObject.requireObjectMap(element0)));
        }
        /**
         * Replays the layer snapshot and returns canvas log.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.List<java.util.Map<String, Object>>> snapshotCommandLog(SnapshotCommandLogRequest request) {
            return client.call("LayerTree.snapshotCommandLog", request == null ? null : request.toMap(), result_ -> CdpObject.requireList(java.util.Objects.requireNonNull(result_.get("commandLog")), element0 -> CdpObject.requireObjectMap(element0)));
        }
        /**
         * Subscribes to LayerTree.layerPainted.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onLayerPainted(Consumer<LayerPaintedEvent> handler) {
            return client.on("LayerTree.layerPainted", LayerPaintedEvent::fromMap, handler);
        }
        /**
         * Subscribes to LayerTree.layerTreeDidChange.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onLayerTreeDidChange(Consumer<LayerTreeDidChangeEvent> handler) {
            return client.on("LayerTree.layerTreeDidChange", LayerTreeDidChangeEvent::fromMap, handler);
        }
    }
}
