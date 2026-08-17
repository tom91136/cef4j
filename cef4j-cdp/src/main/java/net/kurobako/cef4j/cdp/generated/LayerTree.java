// GENERATED - do not edit. Run scripts/update-cdp-schema.sh.
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;

/**
 * Chrome DevTools Protocol LayerTree domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/LayerTree.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class LayerTree {
    private LayerTree() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Rectangle where scrolling happens on the main thread.
     */
    public static final class ScrollRect extends CdpObject {
        private ScrollRect(Map<String, Object> values) { super(values); }
        @Nullable public static ScrollRect fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScrollRect(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Rectangle itself.
         * @return the protocol field value
         */
        @Nullable public DOM.Rect rect() {
            return DOM.Rect.fromMap(objectMap(value("rect")));
        }
        /**
         * Reason for rectangle to force scrolling on the main thread
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        /**
         * Reason for rectangle to force scrolling on the main thread
         */
        public static final class TypeValues {
            private TypeValues() {}
            public static final String REPAINTSONSCROLL = "RepaintsOnScroll";
            public static final String TOUCHEVENTHANDLER = "TouchEventHandler";
            public static final String WHEELEVENTHANDLER = "WheelEventHandler";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Rectangle itself.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rect(@Nullable DOM.Rect value) {
                if (value == null) values.remove("rect");
                else values.put("rect", jsonValue(value));
                return this;
            }
            /**
             * Reason for rectangle to force scrolling on the main thread
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            public ScrollRect build() {
                if (!values.containsKey("rect")) throw new IllegalStateException("Missing required CDP field: rect");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new ScrollRect(values);
            }
        }
    }
    /**
     * Sticky position constraints.
     */
    public static final class StickyPositionConstraint extends CdpObject {
        private StickyPositionConstraint(Map<String, Object> values) { super(values); }
        @Nullable public static StickyPositionConstraint fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new StickyPositionConstraint(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Layout rectangle of the sticky element before being shifted
         * @return the protocol field value
         */
        @Nullable public DOM.Rect stickyBoxRect() {
            return DOM.Rect.fromMap(objectMap(value("stickyBoxRect")));
        }
        /**
         * Layout rectangle of the containing block of the sticky element
         * @return the protocol field value
         */
        @Nullable public DOM.Rect containingBlockRect() {
            return DOM.Rect.fromMap(objectMap(value("containingBlockRect")));
        }
        /**
         * The nearest sticky layer that shifts the sticky box
         * @return the protocol field value
         */
        @Nullable public String nearestLayerShiftingStickyBox() {
            return (String) value("nearestLayerShiftingStickyBox");
        }
        /**
         * The nearest sticky layer that shifts the containing block
         * @return the protocol field value
         */
        @Nullable public String nearestLayerShiftingContainingBlock() {
            return (String) value("nearestLayerShiftingContainingBlock");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Layout rectangle of the sticky element before being shifted
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stickyBoxRect(@Nullable DOM.Rect value) {
                if (value == null) values.remove("stickyBoxRect");
                else values.put("stickyBoxRect", jsonValue(value));
                return this;
            }
            /**
             * Layout rectangle of the containing block of the sticky element
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder containingBlockRect(@Nullable DOM.Rect value) {
                if (value == null) values.remove("containingBlockRect");
                else values.put("containingBlockRect", jsonValue(value));
                return this;
            }
            /**
             * The nearest sticky layer that shifts the sticky box
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nearestLayerShiftingStickyBox(@Nullable String value) {
                if (value == null) values.remove("nearestLayerShiftingStickyBox");
                else values.put("nearestLayerShiftingStickyBox", jsonValue(value));
                return this;
            }
            /**
             * The nearest sticky layer that shifts the containing block
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nearestLayerShiftingContainingBlock(@Nullable String value) {
                if (value == null) values.remove("nearestLayerShiftingContainingBlock");
                else values.put("nearestLayerShiftingContainingBlock", jsonValue(value));
                return this;
            }
            public StickyPositionConstraint build() {
                if (!values.containsKey("stickyBoxRect")) throw new IllegalStateException("Missing required CDP field: stickyBoxRect");
                if (!values.containsKey("containingBlockRect")) throw new IllegalStateException("Missing required CDP field: containingBlockRect");
                return new StickyPositionConstraint(values);
            }
        }
    }
    /**
     * Serialized fragment of layer picture along with its offset within the layer.
     */
    public static final class PictureTile extends CdpObject {
        private PictureTile(Map<String, Object> values) { super(values); }
        @Nullable public static PictureTile fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new PictureTile(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Offset from owning layer left boundary
         * @return the protocol field value
         */
        @Nullable public Double x() {
            return numberAsDouble(value("x"));
        }
        /**
         * Offset from owning layer top boundary
         * @return the protocol field value
         */
        @Nullable public Double y() {
            return numberAsDouble(value("y"));
        }
        /**
         * Base64-encoded snapshot data. (Encoded as a base64 string when passed over JSON)
         * @return the protocol field value
         */
        @Nullable public String picture() {
            return (String) value("picture");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Offset from owning layer left boundary
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder x(@Nullable Double value) {
                if (value == null) values.remove("x");
                else values.put("x", jsonValue(value));
                return this;
            }
            /**
             * Offset from owning layer top boundary
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder y(@Nullable Double value) {
                if (value == null) values.remove("y");
                else values.put("y", jsonValue(value));
                return this;
            }
            /**
             * Base64-encoded snapshot data. (Encoded as a base64 string when passed over JSON)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder picture(@Nullable String value) {
                if (value == null) values.remove("picture");
                else values.put("picture", jsonValue(value));
                return this;
            }
            public PictureTile build() {
                if (!values.containsKey("x")) throw new IllegalStateException("Missing required CDP field: x");
                if (!values.containsKey("y")) throw new IllegalStateException("Missing required CDP field: y");
                if (!values.containsKey("picture")) throw new IllegalStateException("Missing required CDP field: picture");
                return new PictureTile(values);
            }
        }
    }
    /**
     * Information about a compositing layer.
     */
    public static final class Layer extends CdpObject {
        private Layer(Map<String, Object> values) { super(values); }
        @Nullable public static Layer fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Layer(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The unique id for this layer.
         * @return the protocol field value
         */
        @Nullable public String layerId() {
            return (String) value("layerId");
        }
        /**
         * The id of parent (not present for root).
         * @return the protocol field value
         */
        @Nullable public String parentLayerId() {
            return (String) value("parentLayerId");
        }
        /**
         * The backend id for the node associated with this layer.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * Offset from parent layer, X coordinate.
         * @return the protocol field value
         */
        @Nullable public Double offsetX() {
            return numberAsDouble(value("offsetX"));
        }
        /**
         * Offset from parent layer, Y coordinate.
         * @return the protocol field value
         */
        @Nullable public Double offsetY() {
            return numberAsDouble(value("offsetY"));
        }
        /**
         * Layer width.
         * @return the protocol field value
         */
        @Nullable public Double width() {
            return numberAsDouble(value("width"));
        }
        /**
         * Layer height.
         * @return the protocol field value
         */
        @Nullable public Double height() {
            return numberAsDouble(value("height"));
        }
        /**
         * Transformation matrix for layer, default is identity matrix
         * @return the protocol field value
         */
        @Nullable public java.util.List<Double> transform() {
            return list(value("transform"), element0 -> numberAsDouble(element0));
        }
        /**
         * Transform anchor point X, absent if no transform specified
         * @return the protocol field value
         */
        @Nullable public Double anchorX() {
            return numberAsDouble(value("anchorX"));
        }
        /**
         * Transform anchor point Y, absent if no transform specified
         * @return the protocol field value
         */
        @Nullable public Double anchorY() {
            return numberAsDouble(value("anchorY"));
        }
        /**
         * Transform anchor point Z, absent if no transform specified
         * @return the protocol field value
         */
        @Nullable public Double anchorZ() {
            return numberAsDouble(value("anchorZ"));
        }
        /**
         * Indicates how many time this layer has painted.
         * @return the protocol field value
         */
        @Nullable public Long paintCount() {
            return numberAsLong(value("paintCount"));
        }
        /**
         * Indicates whether this layer hosts any content, rather than being used for transform/scrolling purposes only.
         * @return the protocol field value
         */
        @Nullable public Boolean drawsContent() {
            return (Boolean) value("drawsContent");
        }
        /**
         * Set if layer is not visible.
         * @return the protocol field value
         */
        @Nullable public Boolean invisible() {
            return (Boolean) value("invisible");
        }
        /**
         * Rectangles scrolling on main thread only.
         * @return the protocol field value
         */
        @Nullable public java.util.List<LayerTree.ScrollRect> scrollRects() {
            return list(value("scrollRects"), element0 -> LayerTree.ScrollRect.fromMap(objectMap(element0)));
        }
        /**
         * Sticky position constraint information
         * @return the protocol field value
         */
        @Nullable public LayerTree.StickyPositionConstraint stickyPositionConstraint() {
            return LayerTree.StickyPositionConstraint.fromMap(objectMap(value("stickyPositionConstraint")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The unique id for this layer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder layerId(@Nullable String value) {
                if (value == null) values.remove("layerId");
                else values.put("layerId", jsonValue(value));
                return this;
            }
            /**
             * The id of parent (not present for root).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentLayerId(@Nullable String value) {
                if (value == null) values.remove("parentLayerId");
                else values.put("parentLayerId", jsonValue(value));
                return this;
            }
            /**
             * The backend id for the node associated with this layer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * Offset from parent layer, X coordinate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder offsetX(@Nullable Double value) {
                if (value == null) values.remove("offsetX");
                else values.put("offsetX", jsonValue(value));
                return this;
            }
            /**
             * Offset from parent layer, Y coordinate.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder offsetY(@Nullable Double value) {
                if (value == null) values.remove("offsetY");
                else values.put("offsetY", jsonValue(value));
                return this;
            }
            /**
             * Layer width.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder width(@Nullable Double value) {
                if (value == null) values.remove("width");
                else values.put("width", jsonValue(value));
                return this;
            }
            /**
             * Layer height.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder height(@Nullable Double value) {
                if (value == null) values.remove("height");
                else values.put("height", jsonValue(value));
                return this;
            }
            /**
             * Transformation matrix for layer, default is identity matrix
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder transform(@Nullable java.util.List<Double> value) {
                if (value == null) values.remove("transform");
                else values.put("transform", jsonValue(value));
                return this;
            }
            /**
             * Transform anchor point X, absent if no transform specified
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder anchorX(@Nullable Double value) {
                if (value == null) values.remove("anchorX");
                else values.put("anchorX", jsonValue(value));
                return this;
            }
            /**
             * Transform anchor point Y, absent if no transform specified
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder anchorY(@Nullable Double value) {
                if (value == null) values.remove("anchorY");
                else values.put("anchorY", jsonValue(value));
                return this;
            }
            /**
             * Transform anchor point Z, absent if no transform specified
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder anchorZ(@Nullable Double value) {
                if (value == null) values.remove("anchorZ");
                else values.put("anchorZ", jsonValue(value));
                return this;
            }
            /**
             * Indicates how many time this layer has painted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder paintCount(@Nullable Long value) {
                if (value == null) values.remove("paintCount");
                else values.put("paintCount", jsonValue(value));
                return this;
            }
            /**
             * Indicates whether this layer hosts any content, rather than being used for transform/scrolling purposes only.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder drawsContent(@Nullable Boolean value) {
                if (value == null) values.remove("drawsContent");
                else values.put("drawsContent", jsonValue(value));
                return this;
            }
            /**
             * Set if layer is not visible.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder invisible(@Nullable Boolean value) {
                if (value == null) values.remove("invisible");
                else values.put("invisible", jsonValue(value));
                return this;
            }
            /**
             * Rectangles scrolling on main thread only.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scrollRects(@Nullable java.util.List<LayerTree.ScrollRect> value) {
                if (value == null) values.remove("scrollRects");
                else values.put("scrollRects", jsonValue(value));
                return this;
            }
            /**
             * Sticky position constraint information
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder stickyPositionConstraint(@Nullable LayerTree.StickyPositionConstraint value) {
                if (value == null) values.remove("stickyPositionConstraint");
                else values.put("stickyPositionConstraint", jsonValue(value));
                return this;
            }
            public Layer build() {
                if (!values.containsKey("layerId")) throw new IllegalStateException("Missing required CDP field: layerId");
                if (!values.containsKey("offsetX")) throw new IllegalStateException("Missing required CDP field: offsetX");
                if (!values.containsKey("offsetY")) throw new IllegalStateException("Missing required CDP field: offsetY");
                if (!values.containsKey("width")) throw new IllegalStateException("Missing required CDP field: width");
                if (!values.containsKey("height")) throw new IllegalStateException("Missing required CDP field: height");
                if (!values.containsKey("paintCount")) throw new IllegalStateException("Missing required CDP field: paintCount");
                if (!values.containsKey("drawsContent")) throw new IllegalStateException("Missing required CDP field: drawsContent");
                return new Layer(values);
            }
        }
    }
    /**
     * Provides the reasons why the given layer was composited.
     */
    public static final class CompositingReasonsParams extends CdpObject {
        private CompositingReasonsParams(Map<String, Object> values) { super(values); }
        @Nullable public static CompositingReasonsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CompositingReasonsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The id of the layer for which we want to get the reasons it was composited.
         * @return the protocol field value
         */
        @Nullable public String layerId() {
            return (String) value("layerId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The id of the layer for which we want to get the reasons it was composited.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder layerId(@Nullable String value) {
                if (value == null) values.remove("layerId");
                else values.put("layerId", jsonValue(value));
                return this;
            }
            public CompositingReasonsParams build() {
                if (!values.containsKey("layerId")) throw new IllegalStateException("Missing required CDP field: layerId");
                return new CompositingReasonsParams(values);
            }
        }
    }
    /**
     * Provides the reasons why the given layer was composited.
     */
    public static final class CompositingReasonsResult extends CdpObject {
        private CompositingReasonsResult(Map<String, Object> values) { super(values); }
        @Nullable public static CompositingReasonsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new CompositingReasonsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A list of strings specifying reasons for the given layer to become composited.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> compositingReasons() {
            return list(value("compositingReasons"), element0 -> (String) element0);
        }
        /**
         * A list of strings specifying reason IDs for the given layer to become composited.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> compositingReasonIds() {
            return list(value("compositingReasonIds"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A list of strings specifying reasons for the given layer to become composited.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder compositingReasons(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("compositingReasons");
                else values.put("compositingReasons", jsonValue(value));
                return this;
            }
            /**
             * A list of strings specifying reason IDs for the given layer to become composited.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder compositingReasonIds(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("compositingReasonIds");
                else values.put("compositingReasonIds", jsonValue(value));
                return this;
            }
            public CompositingReasonsResult build() {
                if (!values.containsKey("compositingReasons")) throw new IllegalStateException("Missing required CDP field: compositingReasons");
                if (!values.containsKey("compositingReasonIds")) throw new IllegalStateException("Missing required CDP field: compositingReasonIds");
                return new CompositingReasonsResult(values);
            }
        }
    }
    /**
     * Disables compositing tree inspection.
     */
    public static final class DisableParams extends CdpObject {
        private DisableParams(Map<String, Object> values) { super(values); }
        @Nullable public static DisableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisableParams build() {
                return new DisableParams(values);
            }
        }
    }
    /**
     * Disables compositing tree inspection.
     */
    public static final class DisableResult extends CdpObject {
        private DisableResult(Map<String, Object> values) { super(values); }
        @Nullable public static DisableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new DisableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public DisableResult build() {
                return new DisableResult(values);
            }
        }
    }
    /**
     * Enables compositing tree inspection.
     */
    public static final class EnableParams extends CdpObject {
        private EnableParams(Map<String, Object> values) { super(values); }
        @Nullable public static EnableParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableParams build() {
                return new EnableParams(values);
            }
        }
    }
    /**
     * Enables compositing tree inspection.
     */
    public static final class EnableResult extends CdpObject {
        private EnableResult(Map<String, Object> values) { super(values); }
        @Nullable public static EnableResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new EnableResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public EnableResult build() {
                return new EnableResult(values);
            }
        }
    }
    /**
     * Returns the snapshot identifier.
     */
    public static final class LoadSnapshotParams extends CdpObject {
        private LoadSnapshotParams(Map<String, Object> values) { super(values); }
        @Nullable public static LoadSnapshotParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LoadSnapshotParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An array of tiles composing the snapshot.
         * @return the protocol field value
         */
        @Nullable public java.util.List<LayerTree.PictureTile> tiles() {
            return list(value("tiles"), element0 -> LayerTree.PictureTile.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An array of tiles composing the snapshot.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder tiles(@Nullable java.util.List<LayerTree.PictureTile> value) {
                if (value == null) values.remove("tiles");
                else values.put("tiles", jsonValue(value));
                return this;
            }
            public LoadSnapshotParams build() {
                if (!values.containsKey("tiles")) throw new IllegalStateException("Missing required CDP field: tiles");
                return new LoadSnapshotParams(values);
            }
        }
    }
    /**
     * Returns the snapshot identifier.
     */
    public static final class LoadSnapshotResult extends CdpObject {
        private LoadSnapshotResult(Map<String, Object> values) { super(values); }
        @Nullable public static LoadSnapshotResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LoadSnapshotResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The id of the snapshot.
         * @return the protocol field value
         */
        @Nullable public String snapshotId() {
            return (String) value("snapshotId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The id of the snapshot.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder snapshotId(@Nullable String value) {
                if (value == null) values.remove("snapshotId");
                else values.put("snapshotId", jsonValue(value));
                return this;
            }
            public LoadSnapshotResult build() {
                if (!values.containsKey("snapshotId")) throw new IllegalStateException("Missing required CDP field: snapshotId");
                return new LoadSnapshotResult(values);
            }
        }
    }
    /**
     * Returns the layer snapshot identifier.
     */
    public static final class MakeSnapshotParams extends CdpObject {
        private MakeSnapshotParams(Map<String, Object> values) { super(values); }
        @Nullable public static MakeSnapshotParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new MakeSnapshotParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The id of the layer.
         * @return the protocol field value
         */
        @Nullable public String layerId() {
            return (String) value("layerId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The id of the layer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder layerId(@Nullable String value) {
                if (value == null) values.remove("layerId");
                else values.put("layerId", jsonValue(value));
                return this;
            }
            public MakeSnapshotParams build() {
                if (!values.containsKey("layerId")) throw new IllegalStateException("Missing required CDP field: layerId");
                return new MakeSnapshotParams(values);
            }
        }
    }
    /**
     * Returns the layer snapshot identifier.
     */
    public static final class MakeSnapshotResult extends CdpObject {
        private MakeSnapshotResult(Map<String, Object> values) { super(values); }
        @Nullable public static MakeSnapshotResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new MakeSnapshotResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The id of the layer snapshot.
         * @return the protocol field value
         */
        @Nullable public String snapshotId() {
            return (String) value("snapshotId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The id of the layer snapshot.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder snapshotId(@Nullable String value) {
                if (value == null) values.remove("snapshotId");
                else values.put("snapshotId", jsonValue(value));
                return this;
            }
            public MakeSnapshotResult build() {
                if (!values.containsKey("snapshotId")) throw new IllegalStateException("Missing required CDP field: snapshotId");
                return new MakeSnapshotResult(values);
            }
        }
    }
    /**
     * Parameters for LayerTree.profileSnapshot.
     */
    public static final class ProfileSnapshotParams extends CdpObject {
        private ProfileSnapshotParams(Map<String, Object> values) { super(values); }
        @Nullable public static ProfileSnapshotParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ProfileSnapshotParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The id of the layer snapshot.
         * @return the protocol field value
         */
        @Nullable public String snapshotId() {
            return (String) value("snapshotId");
        }
        /**
         * The maximum number of times to replay the snapshot (1, if not specified).
         * @return the protocol field value
         */
        @Nullable public Long minRepeatCount() {
            return numberAsLong(value("minRepeatCount"));
        }
        /**
         * The minimum duration (in seconds) to replay the snapshot.
         * @return the protocol field value
         */
        @Nullable public Double minDuration() {
            return numberAsDouble(value("minDuration"));
        }
        /**
         * The clip rectangle to apply when replaying the snapshot.
         * @return the protocol field value
         */
        @Nullable public DOM.Rect clipRect() {
            return DOM.Rect.fromMap(objectMap(value("clipRect")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The id of the layer snapshot.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder snapshotId(@Nullable String value) {
                if (value == null) values.remove("snapshotId");
                else values.put("snapshotId", jsonValue(value));
                return this;
            }
            /**
             * The maximum number of times to replay the snapshot (1, if not specified).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder minRepeatCount(@Nullable Long value) {
                if (value == null) values.remove("minRepeatCount");
                else values.put("minRepeatCount", jsonValue(value));
                return this;
            }
            /**
             * The minimum duration (in seconds) to replay the snapshot.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder minDuration(@Nullable Double value) {
                if (value == null) values.remove("minDuration");
                else values.put("minDuration", jsonValue(value));
                return this;
            }
            /**
             * The clip rectangle to apply when replaying the snapshot.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clipRect(@Nullable DOM.Rect value) {
                if (value == null) values.remove("clipRect");
                else values.put("clipRect", jsonValue(value));
                return this;
            }
            public ProfileSnapshotParams build() {
                if (!values.containsKey("snapshotId")) throw new IllegalStateException("Missing required CDP field: snapshotId");
                return new ProfileSnapshotParams(values);
            }
        }
    }
    /**
     * Result of LayerTree.profileSnapshot.
     */
    public static final class ProfileSnapshotResult extends CdpObject {
        private ProfileSnapshotResult(Map<String, Object> values) { super(values); }
        @Nullable public static ProfileSnapshotResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ProfileSnapshotResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The array of paint profiles, one per run.
         * @return the protocol field value
         */
        @Nullable public java.util.List<java.util.List<Double>> timings() {
            return list(value("timings"), element0 -> list(element0, element1 -> numberAsDouble(element1)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The array of paint profiles, one per run.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder timings(@Nullable java.util.List<java.util.List<Double>> value) {
                if (value == null) values.remove("timings");
                else values.put("timings", jsonValue(value));
                return this;
            }
            public ProfileSnapshotResult build() {
                if (!values.containsKey("timings")) throw new IllegalStateException("Missing required CDP field: timings");
                return new ProfileSnapshotResult(values);
            }
        }
    }
    /**
     * Releases layer snapshot captured by the back-end.
     */
    public static final class ReleaseSnapshotParams extends CdpObject {
        private ReleaseSnapshotParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReleaseSnapshotParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReleaseSnapshotParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The id of the layer snapshot.
         * @return the protocol field value
         */
        @Nullable public String snapshotId() {
            return (String) value("snapshotId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The id of the layer snapshot.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder snapshotId(@Nullable String value) {
                if (value == null) values.remove("snapshotId");
                else values.put("snapshotId", jsonValue(value));
                return this;
            }
            public ReleaseSnapshotParams build() {
                if (!values.containsKey("snapshotId")) throw new IllegalStateException("Missing required CDP field: snapshotId");
                return new ReleaseSnapshotParams(values);
            }
        }
    }
    /**
     * Releases layer snapshot captured by the back-end.
     */
    public static final class ReleaseSnapshotResult extends CdpObject {
        private ReleaseSnapshotResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReleaseSnapshotResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReleaseSnapshotResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public ReleaseSnapshotResult build() {
                return new ReleaseSnapshotResult(values);
            }
        }
    }
    /**
     * Replays the layer snapshot and returns the resulting bitmap.
     */
    public static final class ReplaySnapshotParams extends CdpObject {
        private ReplaySnapshotParams(Map<String, Object> values) { super(values); }
        @Nullable public static ReplaySnapshotParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReplaySnapshotParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The id of the layer snapshot.
         * @return the protocol field value
         */
        @Nullable public String snapshotId() {
            return (String) value("snapshotId");
        }
        /**
         * The first step to replay from (replay from the very start if not specified).
         * @return the protocol field value
         */
        @Nullable public Long fromStep() {
            return numberAsLong(value("fromStep"));
        }
        /**
         * The last step to replay to (replay till the end if not specified).
         * @return the protocol field value
         */
        @Nullable public Long toStep() {
            return numberAsLong(value("toStep"));
        }
        /**
         * The scale to apply while replaying (defaults to 1).
         * @return the protocol field value
         */
        @Nullable public Double scale() {
            return numberAsDouble(value("scale"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The id of the layer snapshot.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder snapshotId(@Nullable String value) {
                if (value == null) values.remove("snapshotId");
                else values.put("snapshotId", jsonValue(value));
                return this;
            }
            /**
             * The first step to replay from (replay from the very start if not specified).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fromStep(@Nullable Long value) {
                if (value == null) values.remove("fromStep");
                else values.put("fromStep", jsonValue(value));
                return this;
            }
            /**
             * The last step to replay to (replay till the end if not specified).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder toStep(@Nullable Long value) {
                if (value == null) values.remove("toStep");
                else values.put("toStep", jsonValue(value));
                return this;
            }
            /**
             * The scale to apply while replaying (defaults to 1).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scale(@Nullable Double value) {
                if (value == null) values.remove("scale");
                else values.put("scale", jsonValue(value));
                return this;
            }
            public ReplaySnapshotParams build() {
                if (!values.containsKey("snapshotId")) throw new IllegalStateException("Missing required CDP field: snapshotId");
                return new ReplaySnapshotParams(values);
            }
        }
    }
    /**
     * Replays the layer snapshot and returns the resulting bitmap.
     */
    public static final class ReplaySnapshotResult extends CdpObject {
        private ReplaySnapshotResult(Map<String, Object> values) { super(values); }
        @Nullable public static ReplaySnapshotResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ReplaySnapshotResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A data: URL for resulting image.
         * @return the protocol field value
         */
        @Nullable public String dataURL() {
            return (String) value("dataURL");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A data: URL for resulting image.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder dataURL(@Nullable String value) {
                if (value == null) values.remove("dataURL");
                else values.put("dataURL", jsonValue(value));
                return this;
            }
            public ReplaySnapshotResult build() {
                if (!values.containsKey("dataURL")) throw new IllegalStateException("Missing required CDP field: dataURL");
                return new ReplaySnapshotResult(values);
            }
        }
    }
    /**
     * Replays the layer snapshot and returns canvas log.
     */
    public static final class SnapshotCommandLogParams extends CdpObject {
        private SnapshotCommandLogParams(Map<String, Object> values) { super(values); }
        @Nullable public static SnapshotCommandLogParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SnapshotCommandLogParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The id of the layer snapshot.
         * @return the protocol field value
         */
        @Nullable public String snapshotId() {
            return (String) value("snapshotId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The id of the layer snapshot.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder snapshotId(@Nullable String value) {
                if (value == null) values.remove("snapshotId");
                else values.put("snapshotId", jsonValue(value));
                return this;
            }
            public SnapshotCommandLogParams build() {
                if (!values.containsKey("snapshotId")) throw new IllegalStateException("Missing required CDP field: snapshotId");
                return new SnapshotCommandLogParams(values);
            }
        }
    }
    /**
     * Replays the layer snapshot and returns canvas log.
     */
    public static final class SnapshotCommandLogResult extends CdpObject {
        private SnapshotCommandLogResult(Map<String, Object> values) { super(values); }
        @Nullable public static SnapshotCommandLogResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SnapshotCommandLogResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The array of canvas function calls.
         * @return the protocol field value
         */
        @Nullable public java.util.List<java.util.Map<String, Object>> commandLog() {
            return list(value("commandLog"), element0 -> objectMap(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The array of canvas function calls.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder commandLog(@Nullable java.util.List<java.util.Map<String, Object>> value) {
                if (value == null) values.remove("commandLog");
                else values.put("commandLog", jsonValue(value));
                return this;
            }
            public SnapshotCommandLogResult build() {
                if (!values.containsKey("commandLog")) throw new IllegalStateException("Missing required CDP field: commandLog");
                return new SnapshotCommandLogResult(values);
            }
        }
    }
    /**
     * Payload of the LayerTree.layerPainted event.
     */
    public static final class LayerPaintedEvent extends CdpObject {
        private LayerPaintedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static LayerPaintedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LayerPaintedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The id of the painted layer.
         * @return the protocol field value
         */
        @Nullable public String layerId() {
            return (String) value("layerId");
        }
        /**
         * Clip rectangle.
         * @return the protocol field value
         */
        @Nullable public DOM.Rect clip() {
            return DOM.Rect.fromMap(objectMap(value("clip")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The id of the painted layer.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder layerId(@Nullable String value) {
                if (value == null) values.remove("layerId");
                else values.put("layerId", jsonValue(value));
                return this;
            }
            /**
             * Clip rectangle.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder clip(@Nullable DOM.Rect value) {
                if (value == null) values.remove("clip");
                else values.put("clip", jsonValue(value));
                return this;
            }
            public LayerPaintedEvent build() {
                if (!values.containsKey("layerId")) throw new IllegalStateException("Missing required CDP field: layerId");
                if (!values.containsKey("clip")) throw new IllegalStateException("Missing required CDP field: clip");
                return new LayerPaintedEvent(values);
            }
        }
    }
    /**
     * Payload of the LayerTree.layerTreeDidChange event.
     */
    public static final class LayerTreeDidChangeEvent extends CdpObject {
        private LayerTreeDidChangeEvent(Map<String, Object> values) { super(values); }
        @Nullable public static LayerTreeDidChangeEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LayerTreeDidChangeEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Layer tree, absent if not in the compositing mode.
         * @return the protocol field value
         */
        @Nullable public java.util.List<LayerTree.Layer> layers() {
            return list(value("layers"), element0 -> LayerTree.Layer.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Layer tree, absent if not in the compositing mode.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder layers(@Nullable java.util.List<LayerTree.Layer> value) {
                if (value == null) values.remove("layers");
                else values.put("layers", jsonValue(value));
                return this;
            }
            public LayerTreeDidChangeEvent build() {
                return new LayerTreeDidChangeEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Provides the reasons why the given layer was composited.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<CompositingReasonsResult> compositingReasons(CompositingReasonsParams params) {
            return client.call("LayerTree.compositingReasons", params, CompositingReasonsResult::fromMap);
        }
        /**
         * Disables compositing tree inspection.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("LayerTree.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables compositing tree inspection.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("LayerTree.enable", null, EnableResult::fromMap);
        }
        /**
         * Returns the snapshot identifier.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<LoadSnapshotResult> loadSnapshot(LoadSnapshotParams params) {
            return client.call("LayerTree.loadSnapshot", params, LoadSnapshotResult::fromMap);
        }
        /**
         * Returns the layer snapshot identifier.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<MakeSnapshotResult> makeSnapshot(MakeSnapshotParams params) {
            return client.call("LayerTree.makeSnapshot", params, MakeSnapshotResult::fromMap);
        }
        /**
         * Invokes LayerTree.profileSnapshot.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ProfileSnapshotResult> profileSnapshot(ProfileSnapshotParams params) {
            return client.call("LayerTree.profileSnapshot", params, ProfileSnapshotResult::fromMap);
        }
        /**
         * Releases layer snapshot captured by the back-end.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReleaseSnapshotResult> releaseSnapshot(ReleaseSnapshotParams params) {
            return client.call("LayerTree.releaseSnapshot", params, ReleaseSnapshotResult::fromMap);
        }
        /**
         * Replays the layer snapshot and returns the resulting bitmap.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<ReplaySnapshotResult> replaySnapshot(ReplaySnapshotParams params) {
            return client.call("LayerTree.replaySnapshot", params, ReplaySnapshotResult::fromMap);
        }
        /**
         * Replays the layer snapshot and returns canvas log.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SnapshotCommandLogResult> snapshotCommandLog(SnapshotCommandLogParams params) {
            return client.call("LayerTree.snapshotCommandLog", params, SnapshotCommandLogResult::fromMap);
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
