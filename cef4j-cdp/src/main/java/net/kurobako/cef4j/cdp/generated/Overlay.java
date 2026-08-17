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
 * This domain provides various functionality related to drawing atop the inspected page.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Overlay.pdl">Pinned protocol source</a>
 */
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class Overlay {
    private Overlay() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     * Configuration data for drawing the source order of an elements children.
     */
    public static final class SourceOrderConfig extends CdpObject {
        private SourceOrderConfig(Map<String, Object> values) { super(values); }
        @Nullable public static SourceOrderConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SourceOrderConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * the color to outline the given element in.
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA parentOutlineColor() {
            return DOM.RGBA.fromMap(objectMap(value("parentOutlineColor")));
        }
        /**
         * the color to outline the child elements in.
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA childOutlineColor() {
            return DOM.RGBA.fromMap(objectMap(value("childOutlineColor")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * the color to outline the given element in.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder parentOutlineColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("parentOutlineColor");
                else values.put("parentOutlineColor", jsonValue(value));
                return this;
            }
            /**
             * the color to outline the child elements in.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder childOutlineColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("childOutlineColor");
                else values.put("childOutlineColor", jsonValue(value));
                return this;
            }
            public SourceOrderConfig build() {
                if (!values.containsKey("parentOutlineColor")) throw new IllegalStateException("Missing required CDP field: parentOutlineColor");
                if (!values.containsKey("childOutlineColor")) throw new IllegalStateException("Missing required CDP field: childOutlineColor");
                return new SourceOrderConfig(values);
            }
        }
    }
    /**
     * Configuration data for the highlighting of Grid elements.
     */
    public static final class GridHighlightConfig extends CdpObject {
        private GridHighlightConfig(Map<String, Object> values) { super(values); }
        @Nullable public static GridHighlightConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GridHighlightConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether the extension lines from grid cells to the rulers should be shown (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean showGridExtensionLines() {
            return (Boolean) value("showGridExtensionLines");
        }
        /**
         * Show Positive line number labels (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean showPositiveLineNumbers() {
            return (Boolean) value("showPositiveLineNumbers");
        }
        /**
         * Show Negative line number labels (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean showNegativeLineNumbers() {
            return (Boolean) value("showNegativeLineNumbers");
        }
        /**
         * Show area name labels (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean showAreaNames() {
            return (Boolean) value("showAreaNames");
        }
        /**
         * Show line name labels (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean showLineNames() {
            return (Boolean) value("showLineNames");
        }
        /**
         * Show track size labels (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean showTrackSizes() {
            return (Boolean) value("showTrackSizes");
        }
        /**
         * The grid container border highlight color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA gridBorderColor() {
            return DOM.RGBA.fromMap(objectMap(value("gridBorderColor")));
        }
        /**
         * The cell border color (default: transparent). Deprecated, please use rowLineColor and columnLineColor instead.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public DOM.RGBA cellBorderColor() {
            return DOM.RGBA.fromMap(objectMap(value("cellBorderColor")));
        }
        /**
         * The row line color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA rowLineColor() {
            return DOM.RGBA.fromMap(objectMap(value("rowLineColor")));
        }
        /**
         * The column line color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA columnLineColor() {
            return DOM.RGBA.fromMap(objectMap(value("columnLineColor")));
        }
        /**
         * Whether the grid border is dashed (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean gridBorderDash() {
            return (Boolean) value("gridBorderDash");
        }
        /**
         * Whether the cell border is dashed (default: false). Deprecated, please us rowLineDash and columnLineDash instead.
         * @return the protocol field value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        @Nullable public Boolean cellBorderDash() {
            return (Boolean) value("cellBorderDash");
        }
        /**
         * Whether row lines are dashed (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean rowLineDash() {
            return (Boolean) value("rowLineDash");
        }
        /**
         * Whether column lines are dashed (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean columnLineDash() {
            return (Boolean) value("columnLineDash");
        }
        /**
         * The row gap highlight fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA rowGapColor() {
            return DOM.RGBA.fromMap(objectMap(value("rowGapColor")));
        }
        /**
         * The row gap hatching fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA rowHatchColor() {
            return DOM.RGBA.fromMap(objectMap(value("rowHatchColor")));
        }
        /**
         * The column gap highlight fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA columnGapColor() {
            return DOM.RGBA.fromMap(objectMap(value("columnGapColor")));
        }
        /**
         * The column gap hatching fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA columnHatchColor() {
            return DOM.RGBA.fromMap(objectMap(value("columnHatchColor")));
        }
        /**
         * The named grid areas border color (Default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA areaBorderColor() {
            return DOM.RGBA.fromMap(objectMap(value("areaBorderColor")));
        }
        /**
         * The grid container background color (Default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA gridBackgroundColor() {
            return DOM.RGBA.fromMap(objectMap(value("gridBackgroundColor")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether the extension lines from grid cells to the rulers should be shown (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder showGridExtensionLines(@Nullable Boolean value) {
                if (value == null) values.remove("showGridExtensionLines");
                else values.put("showGridExtensionLines", jsonValue(value));
                return this;
            }
            /**
             * Show Positive line number labels (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder showPositiveLineNumbers(@Nullable Boolean value) {
                if (value == null) values.remove("showPositiveLineNumbers");
                else values.put("showPositiveLineNumbers", jsonValue(value));
                return this;
            }
            /**
             * Show Negative line number labels (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder showNegativeLineNumbers(@Nullable Boolean value) {
                if (value == null) values.remove("showNegativeLineNumbers");
                else values.put("showNegativeLineNumbers", jsonValue(value));
                return this;
            }
            /**
             * Show area name labels (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder showAreaNames(@Nullable Boolean value) {
                if (value == null) values.remove("showAreaNames");
                else values.put("showAreaNames", jsonValue(value));
                return this;
            }
            /**
             * Show line name labels (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder showLineNames(@Nullable Boolean value) {
                if (value == null) values.remove("showLineNames");
                else values.put("showLineNames", jsonValue(value));
                return this;
            }
            /**
             * Show track size labels (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder showTrackSizes(@Nullable Boolean value) {
                if (value == null) values.remove("showTrackSizes");
                else values.put("showTrackSizes", jsonValue(value));
                return this;
            }
            /**
             * The grid container border highlight color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder gridBorderColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("gridBorderColor");
                else values.put("gridBorderColor", jsonValue(value));
                return this;
            }
            /**
             * The cell border color (default: transparent). Deprecated, please use rowLineColor and columnLineColor instead.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder cellBorderColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("cellBorderColor");
                else values.put("cellBorderColor", jsonValue(value));
                return this;
            }
            /**
             * The row line color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rowLineColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("rowLineColor");
                else values.put("rowLineColor", jsonValue(value));
                return this;
            }
            /**
             * The column line color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnLineColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("columnLineColor");
                else values.put("columnLineColor", jsonValue(value));
                return this;
            }
            /**
             * Whether the grid border is dashed (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder gridBorderDash(@Nullable Boolean value) {
                if (value == null) values.remove("gridBorderDash");
                else values.put("gridBorderDash", jsonValue(value));
                return this;
            }
            /**
             * Whether the cell border is dashed (default: false). Deprecated, please us rowLineDash and columnLineDash instead.
             * @param value field value; null removes an optional value
             * @return this builder
             * @deprecated Deprecated by the Chromium DevTools Protocol.
             */
            @Deprecated
            public Builder cellBorderDash(@Nullable Boolean value) {
                if (value == null) values.remove("cellBorderDash");
                else values.put("cellBorderDash", jsonValue(value));
                return this;
            }
            /**
             * Whether row lines are dashed (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rowLineDash(@Nullable Boolean value) {
                if (value == null) values.remove("rowLineDash");
                else values.put("rowLineDash", jsonValue(value));
                return this;
            }
            /**
             * Whether column lines are dashed (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnLineDash(@Nullable Boolean value) {
                if (value == null) values.remove("columnLineDash");
                else values.put("columnLineDash", jsonValue(value));
                return this;
            }
            /**
             * The row gap highlight fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rowGapColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("rowGapColor");
                else values.put("rowGapColor", jsonValue(value));
                return this;
            }
            /**
             * The row gap hatching fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rowHatchColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("rowHatchColor");
                else values.put("rowHatchColor", jsonValue(value));
                return this;
            }
            /**
             * The column gap highlight fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnGapColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("columnGapColor");
                else values.put("columnGapColor", jsonValue(value));
                return this;
            }
            /**
             * The column gap hatching fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnHatchColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("columnHatchColor");
                else values.put("columnHatchColor", jsonValue(value));
                return this;
            }
            /**
             * The named grid areas border color (Default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder areaBorderColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("areaBorderColor");
                else values.put("areaBorderColor", jsonValue(value));
                return this;
            }
            /**
             * The grid container background color (Default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder gridBackgroundColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("gridBackgroundColor");
                else values.put("gridBackgroundColor", jsonValue(value));
                return this;
            }
            public GridHighlightConfig build() {
                return new GridHighlightConfig(values);
            }
        }
    }
    /**
     * Configuration data for the highlighting of Flex container elements.
     */
    public static final class FlexContainerHighlightConfig extends CdpObject {
        private FlexContainerHighlightConfig(Map<String, Object> values) { super(values); }
        @Nullable public static FlexContainerHighlightConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FlexContainerHighlightConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The style of the container border
         * @return the protocol field value
         */
        @Nullable public Overlay.LineStyle containerBorder() {
            return Overlay.LineStyle.fromMap(objectMap(value("containerBorder")));
        }
        /**
         * The style of the separator between lines
         * @return the protocol field value
         */
        @Nullable public Overlay.LineStyle lineSeparator() {
            return Overlay.LineStyle.fromMap(objectMap(value("lineSeparator")));
        }
        /**
         * The style of the separator between items
         * @return the protocol field value
         */
        @Nullable public Overlay.LineStyle itemSeparator() {
            return Overlay.LineStyle.fromMap(objectMap(value("itemSeparator")));
        }
        /**
         * Style of content-distribution space on the main axis (justify-content).
         * @return the protocol field value
         */
        @Nullable public Overlay.BoxStyle mainDistributedSpace() {
            return Overlay.BoxStyle.fromMap(objectMap(value("mainDistributedSpace")));
        }
        /**
         * Style of content-distribution space on the cross axis (align-content).
         * @return the protocol field value
         */
        @Nullable public Overlay.BoxStyle crossDistributedSpace() {
            return Overlay.BoxStyle.fromMap(objectMap(value("crossDistributedSpace")));
        }
        /**
         * Style of empty space caused by row gaps (gap/row-gap).
         * @return the protocol field value
         */
        @Nullable public Overlay.BoxStyle rowGapSpace() {
            return Overlay.BoxStyle.fromMap(objectMap(value("rowGapSpace")));
        }
        /**
         * Style of empty space caused by columns gaps (gap/column-gap).
         * @return the protocol field value
         */
        @Nullable public Overlay.BoxStyle columnGapSpace() {
            return Overlay.BoxStyle.fromMap(objectMap(value("columnGapSpace")));
        }
        /**
         * Style of the self-alignment line (align-items).
         * @return the protocol field value
         */
        @Nullable public Overlay.LineStyle crossAlignment() {
            return Overlay.LineStyle.fromMap(objectMap(value("crossAlignment")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The style of the container border
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder containerBorder(@Nullable Overlay.LineStyle value) {
                if (value == null) values.remove("containerBorder");
                else values.put("containerBorder", jsonValue(value));
                return this;
            }
            /**
             * The style of the separator between lines
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lineSeparator(@Nullable Overlay.LineStyle value) {
                if (value == null) values.remove("lineSeparator");
                else values.put("lineSeparator", jsonValue(value));
                return this;
            }
            /**
             * The style of the separator between items
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder itemSeparator(@Nullable Overlay.LineStyle value) {
                if (value == null) values.remove("itemSeparator");
                else values.put("itemSeparator", jsonValue(value));
                return this;
            }
            /**
             * Style of content-distribution space on the main axis (justify-content).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mainDistributedSpace(@Nullable Overlay.BoxStyle value) {
                if (value == null) values.remove("mainDistributedSpace");
                else values.put("mainDistributedSpace", jsonValue(value));
                return this;
            }
            /**
             * Style of content-distribution space on the cross axis (align-content).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder crossDistributedSpace(@Nullable Overlay.BoxStyle value) {
                if (value == null) values.remove("crossDistributedSpace");
                else values.put("crossDistributedSpace", jsonValue(value));
                return this;
            }
            /**
             * Style of empty space caused by row gaps (gap/row-gap).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rowGapSpace(@Nullable Overlay.BoxStyle value) {
                if (value == null) values.remove("rowGapSpace");
                else values.put("rowGapSpace", jsonValue(value));
                return this;
            }
            /**
             * Style of empty space caused by columns gaps (gap/column-gap).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder columnGapSpace(@Nullable Overlay.BoxStyle value) {
                if (value == null) values.remove("columnGapSpace");
                else values.put("columnGapSpace", jsonValue(value));
                return this;
            }
            /**
             * Style of the self-alignment line (align-items).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder crossAlignment(@Nullable Overlay.LineStyle value) {
                if (value == null) values.remove("crossAlignment");
                else values.put("crossAlignment", jsonValue(value));
                return this;
            }
            public FlexContainerHighlightConfig build() {
                return new FlexContainerHighlightConfig(values);
            }
        }
    }
    /**
     * Configuration data for the highlighting of Flex item elements.
     */
    public static final class FlexItemHighlightConfig extends CdpObject {
        private FlexItemHighlightConfig(Map<String, Object> values) { super(values); }
        @Nullable public static FlexItemHighlightConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FlexItemHighlightConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Style of the box representing the item&#x27;s base size
         * @return the protocol field value
         */
        @Nullable public Overlay.BoxStyle baseSizeBox() {
            return Overlay.BoxStyle.fromMap(objectMap(value("baseSizeBox")));
        }
        /**
         * Style of the border around the box representing the item&#x27;s base size
         * @return the protocol field value
         */
        @Nullable public Overlay.LineStyle baseSizeBorder() {
            return Overlay.LineStyle.fromMap(objectMap(value("baseSizeBorder")));
        }
        /**
         * Style of the arrow representing if the item grew or shrank
         * @return the protocol field value
         */
        @Nullable public Overlay.LineStyle flexibilityArrow() {
            return Overlay.LineStyle.fromMap(objectMap(value("flexibilityArrow")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Style of the box representing the item&#x27;s base size
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder baseSizeBox(@Nullable Overlay.BoxStyle value) {
                if (value == null) values.remove("baseSizeBox");
                else values.put("baseSizeBox", jsonValue(value));
                return this;
            }
            /**
             * Style of the border around the box representing the item&#x27;s base size
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder baseSizeBorder(@Nullable Overlay.LineStyle value) {
                if (value == null) values.remove("baseSizeBorder");
                else values.put("baseSizeBorder", jsonValue(value));
                return this;
            }
            /**
             * Style of the arrow representing if the item grew or shrank
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder flexibilityArrow(@Nullable Overlay.LineStyle value) {
                if (value == null) values.remove("flexibilityArrow");
                else values.put("flexibilityArrow", jsonValue(value));
                return this;
            }
            public FlexItemHighlightConfig build() {
                return new FlexItemHighlightConfig(values);
            }
        }
    }
    /**
     * Style information for drawing a line.
     */
    public static final class LineStyle extends CdpObject {
        private LineStyle(Map<String, Object> values) { super(values); }
        @Nullable public static LineStyle fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new LineStyle(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The color of the line (default: transparent)
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA color() {
            return DOM.RGBA.fromMap(objectMap(value("color")));
        }
        /**
         * The line pattern (default: solid)
         * @return the protocol field value
         */
        @Nullable public String pattern() {
            return (String) value("pattern");
        }
        /**
         * The line pattern (default: solid)
         */
        public static final class PatternValues {
            private PatternValues() {}
            public static final String DASHED = "dashed";
            public static final String DOTTED = "dotted";
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The color of the line (default: transparent)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder color(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("color");
                else values.put("color", jsonValue(value));
                return this;
            }
            /**
             * The line pattern (default: solid)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pattern(@Nullable String value) {
                if (value == null) values.remove("pattern");
                else values.put("pattern", jsonValue(value));
                return this;
            }
            public LineStyle build() {
                return new LineStyle(values);
            }
        }
    }
    /**
     * Style information for drawing a box.
     */
    public static final class BoxStyle extends CdpObject {
        private BoxStyle(Map<String, Object> values) { super(values); }
        @Nullable public static BoxStyle fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BoxStyle(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The background color for the box (default: transparent)
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA fillColor() {
            return DOM.RGBA.fromMap(objectMap(value("fillColor")));
        }
        /**
         * The hatching color for the box (default: transparent)
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA hatchColor() {
            return DOM.RGBA.fromMap(objectMap(value("hatchColor")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The background color for the box (default: transparent)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder fillColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("fillColor");
                else values.put("fillColor", jsonValue(value));
                return this;
            }
            /**
             * The hatching color for the box (default: transparent)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hatchColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("hatchColor");
                else values.put("hatchColor", jsonValue(value));
                return this;
            }
            public BoxStyle build() {
                return new BoxStyle(values);
            }
        }
    }
    /**
     * Wire values for ContrastAlgorithm.
     */
    public static final class ContrastAlgorithm {
        private ContrastAlgorithm() {}
        public static final String AA = "aa";
        public static final String AAA = "aaa";
        public static final String APCA = "apca";
    }
    /**
     * Configuration data for the highlighting of page elements.
     */
    public static final class HighlightConfig extends CdpObject {
        private HighlightConfig(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether the node info tooltip should be shown (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean showInfo() {
            return (Boolean) value("showInfo");
        }
        /**
         * Whether the node styles in the tooltip (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean showStyles() {
            return (Boolean) value("showStyles");
        }
        /**
         * Whether the rulers should be shown (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean showRulers() {
            return (Boolean) value("showRulers");
        }
        /**
         * Whether the a11y info should be shown (default: true).
         * @return the protocol field value
         */
        @Nullable public Boolean showAccessibilityInfo() {
            return (Boolean) value("showAccessibilityInfo");
        }
        /**
         * Whether the extension lines from node to the rulers should be shown (default: false).
         * @return the protocol field value
         */
        @Nullable public Boolean showExtensionLines() {
            return (Boolean) value("showExtensionLines");
        }
        /**
         * The content box highlight fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA contentColor() {
            return DOM.RGBA.fromMap(objectMap(value("contentColor")));
        }
        /**
         * The padding highlight fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA paddingColor() {
            return DOM.RGBA.fromMap(objectMap(value("paddingColor")));
        }
        /**
         * The border highlight fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA borderColor() {
            return DOM.RGBA.fromMap(objectMap(value("borderColor")));
        }
        /**
         * The margin highlight fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA marginColor() {
            return DOM.RGBA.fromMap(objectMap(value("marginColor")));
        }
        /**
         * The event target element highlight fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA eventTargetColor() {
            return DOM.RGBA.fromMap(objectMap(value("eventTargetColor")));
        }
        /**
         * The shape outside fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA shapeColor() {
            return DOM.RGBA.fromMap(objectMap(value("shapeColor")));
        }
        /**
         * The shape margin fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA shapeMarginColor() {
            return DOM.RGBA.fromMap(objectMap(value("shapeMarginColor")));
        }
        /**
         * The grid layout color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA cssGridColor() {
            return DOM.RGBA.fromMap(objectMap(value("cssGridColor")));
        }
        /**
         * The color format used to format color styles (default: hex).
         * @return the protocol field value
         */
        @Nullable public String colorFormat() {
            return (String) value("colorFormat");
        }
        /**
         * The grid layout highlight configuration (default: all transparent).
         * @return the protocol field value
         */
        @Nullable public Overlay.GridHighlightConfig gridHighlightConfig() {
            return Overlay.GridHighlightConfig.fromMap(objectMap(value("gridHighlightConfig")));
        }
        /**
         * The flex container highlight configuration (default: all transparent).
         * @return the protocol field value
         */
        @Nullable public Overlay.FlexContainerHighlightConfig flexContainerHighlightConfig() {
            return Overlay.FlexContainerHighlightConfig.fromMap(objectMap(value("flexContainerHighlightConfig")));
        }
        /**
         * The flex item highlight configuration (default: all transparent).
         * @return the protocol field value
         */
        @Nullable public Overlay.FlexItemHighlightConfig flexItemHighlightConfig() {
            return Overlay.FlexItemHighlightConfig.fromMap(objectMap(value("flexItemHighlightConfig")));
        }
        /**
         * The contrast algorithm to use for the contrast ratio (default: aa).
         * @return the protocol field value
         */
        @Nullable public String contrastAlgorithm() {
            return (String) value("contrastAlgorithm");
        }
        /**
         * The container query container highlight configuration (default: all transparent).
         * @return the protocol field value
         */
        @Nullable public Overlay.ContainerQueryContainerHighlightConfig containerQueryContainerHighlightConfig() {
            return Overlay.ContainerQueryContainerHighlightConfig.fromMap(objectMap(value("containerQueryContainerHighlightConfig")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether the node info tooltip should be shown (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder showInfo(@Nullable Boolean value) {
                if (value == null) values.remove("showInfo");
                else values.put("showInfo", jsonValue(value));
                return this;
            }
            /**
             * Whether the node styles in the tooltip (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder showStyles(@Nullable Boolean value) {
                if (value == null) values.remove("showStyles");
                else values.put("showStyles", jsonValue(value));
                return this;
            }
            /**
             * Whether the rulers should be shown (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder showRulers(@Nullable Boolean value) {
                if (value == null) values.remove("showRulers");
                else values.put("showRulers", jsonValue(value));
                return this;
            }
            /**
             * Whether the a11y info should be shown (default: true).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder showAccessibilityInfo(@Nullable Boolean value) {
                if (value == null) values.remove("showAccessibilityInfo");
                else values.put("showAccessibilityInfo", jsonValue(value));
                return this;
            }
            /**
             * Whether the extension lines from node to the rulers should be shown (default: false).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder showExtensionLines(@Nullable Boolean value) {
                if (value == null) values.remove("showExtensionLines");
                else values.put("showExtensionLines", jsonValue(value));
                return this;
            }
            /**
             * The content box highlight fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contentColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("contentColor");
                else values.put("contentColor", jsonValue(value));
                return this;
            }
            /**
             * The padding highlight fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder paddingColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("paddingColor");
                else values.put("paddingColor", jsonValue(value));
                return this;
            }
            /**
             * The border highlight fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder borderColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("borderColor");
                else values.put("borderColor", jsonValue(value));
                return this;
            }
            /**
             * The margin highlight fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder marginColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("marginColor");
                else values.put("marginColor", jsonValue(value));
                return this;
            }
            /**
             * The event target element highlight fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder eventTargetColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("eventTargetColor");
                else values.put("eventTargetColor", jsonValue(value));
                return this;
            }
            /**
             * The shape outside fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder shapeColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("shapeColor");
                else values.put("shapeColor", jsonValue(value));
                return this;
            }
            /**
             * The shape margin fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder shapeMarginColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("shapeMarginColor");
                else values.put("shapeMarginColor", jsonValue(value));
                return this;
            }
            /**
             * The grid layout color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder cssGridColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("cssGridColor");
                else values.put("cssGridColor", jsonValue(value));
                return this;
            }
            /**
             * The color format used to format color styles (default: hex).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder colorFormat(@Nullable String value) {
                if (value == null) values.remove("colorFormat");
                else values.put("colorFormat", jsonValue(value));
                return this;
            }
            /**
             * The grid layout highlight configuration (default: all transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder gridHighlightConfig(@Nullable Overlay.GridHighlightConfig value) {
                if (value == null) values.remove("gridHighlightConfig");
                else values.put("gridHighlightConfig", jsonValue(value));
                return this;
            }
            /**
             * The flex container highlight configuration (default: all transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder flexContainerHighlightConfig(@Nullable Overlay.FlexContainerHighlightConfig value) {
                if (value == null) values.remove("flexContainerHighlightConfig");
                else values.put("flexContainerHighlightConfig", jsonValue(value));
                return this;
            }
            /**
             * The flex item highlight configuration (default: all transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder flexItemHighlightConfig(@Nullable Overlay.FlexItemHighlightConfig value) {
                if (value == null) values.remove("flexItemHighlightConfig");
                else values.put("flexItemHighlightConfig", jsonValue(value));
                return this;
            }
            /**
             * The contrast algorithm to use for the contrast ratio (default: aa).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contrastAlgorithm(@Nullable String value) {
                if (value == null) values.remove("contrastAlgorithm");
                else values.put("contrastAlgorithm", jsonValue(value));
                return this;
            }
            /**
             * The container query container highlight configuration (default: all transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder containerQueryContainerHighlightConfig(@Nullable Overlay.ContainerQueryContainerHighlightConfig value) {
                if (value == null) values.remove("containerQueryContainerHighlightConfig");
                else values.put("containerQueryContainerHighlightConfig", jsonValue(value));
                return this;
            }
            public HighlightConfig build() {
                return new HighlightConfig(values);
            }
        }
    }
    /**
     * Wire values for ColorFormat.
     */
    public static final class ColorFormat {
        private ColorFormat() {}
        public static final String RGB = "rgb";
        public static final String HSL = "hsl";
        public static final String HWB = "hwb";
        public static final String HEX = "hex";
    }
    /**
     * Configurations for Persistent Grid Highlight
     */
    public static final class GridNodeHighlightConfig extends CdpObject {
        private GridNodeHighlightConfig(Map<String, Object> values) { super(values); }
        @Nullable public static GridNodeHighlightConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GridNodeHighlightConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A descriptor for the highlight appearance.
         * @return the protocol field value
         */
        @Nullable public Overlay.GridHighlightConfig gridHighlightConfig() {
            return Overlay.GridHighlightConfig.fromMap(objectMap(value("gridHighlightConfig")));
        }
        /**
         * Identifier of the node to highlight.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A descriptor for the highlight appearance.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder gridHighlightConfig(@Nullable Overlay.GridHighlightConfig value) {
                if (value == null) values.remove("gridHighlightConfig");
                else values.put("gridHighlightConfig", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the node to highlight.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public GridNodeHighlightConfig build() {
                if (!values.containsKey("gridHighlightConfig")) throw new IllegalStateException("Missing required CDP field: gridHighlightConfig");
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GridNodeHighlightConfig(values);
            }
        }
    }
    /**
     */
    public static final class FlexNodeHighlightConfig extends CdpObject {
        private FlexNodeHighlightConfig(Map<String, Object> values) { super(values); }
        @Nullable public static FlexNodeHighlightConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new FlexNodeHighlightConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A descriptor for the highlight appearance of flex containers.
         * @return the protocol field value
         */
        @Nullable public Overlay.FlexContainerHighlightConfig flexContainerHighlightConfig() {
            return Overlay.FlexContainerHighlightConfig.fromMap(objectMap(value("flexContainerHighlightConfig")));
        }
        /**
         * Identifier of the node to highlight.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A descriptor for the highlight appearance of flex containers.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder flexContainerHighlightConfig(@Nullable Overlay.FlexContainerHighlightConfig value) {
                if (value == null) values.remove("flexContainerHighlightConfig");
                else values.put("flexContainerHighlightConfig", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the node to highlight.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public FlexNodeHighlightConfig build() {
                if (!values.containsKey("flexContainerHighlightConfig")) throw new IllegalStateException("Missing required CDP field: flexContainerHighlightConfig");
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new FlexNodeHighlightConfig(values);
            }
        }
    }
    /**
     */
    public static final class ScrollSnapContainerHighlightConfig extends CdpObject {
        private ScrollSnapContainerHighlightConfig(Map<String, Object> values) { super(values); }
        @Nullable public static ScrollSnapContainerHighlightConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScrollSnapContainerHighlightConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The style of the snapport border (default: transparent)
         * @return the protocol field value
         */
        @Nullable public Overlay.LineStyle snapportBorder() {
            return Overlay.LineStyle.fromMap(objectMap(value("snapportBorder")));
        }
        /**
         * The style of the snap area border (default: transparent)
         * @return the protocol field value
         */
        @Nullable public Overlay.LineStyle snapAreaBorder() {
            return Overlay.LineStyle.fromMap(objectMap(value("snapAreaBorder")));
        }
        /**
         * The margin highlight fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA scrollMarginColor() {
            return DOM.RGBA.fromMap(objectMap(value("scrollMarginColor")));
        }
        /**
         * The padding highlight fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA scrollPaddingColor() {
            return DOM.RGBA.fromMap(objectMap(value("scrollPaddingColor")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The style of the snapport border (default: transparent)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder snapportBorder(@Nullable Overlay.LineStyle value) {
                if (value == null) values.remove("snapportBorder");
                else values.put("snapportBorder", jsonValue(value));
                return this;
            }
            /**
             * The style of the snap area border (default: transparent)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder snapAreaBorder(@Nullable Overlay.LineStyle value) {
                if (value == null) values.remove("snapAreaBorder");
                else values.put("snapAreaBorder", jsonValue(value));
                return this;
            }
            /**
             * The margin highlight fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scrollMarginColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("scrollMarginColor");
                else values.put("scrollMarginColor", jsonValue(value));
                return this;
            }
            /**
             * The padding highlight fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scrollPaddingColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("scrollPaddingColor");
                else values.put("scrollPaddingColor", jsonValue(value));
                return this;
            }
            public ScrollSnapContainerHighlightConfig build() {
                return new ScrollSnapContainerHighlightConfig(values);
            }
        }
    }
    /**
     */
    public static final class ScrollSnapHighlightConfig extends CdpObject {
        private ScrollSnapHighlightConfig(Map<String, Object> values) { super(values); }
        @Nullable public static ScrollSnapHighlightConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScrollSnapHighlightConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A descriptor for the highlight appearance of scroll snap containers.
         * @return the protocol field value
         */
        @Nullable public Overlay.ScrollSnapContainerHighlightConfig scrollSnapContainerHighlightConfig() {
            return Overlay.ScrollSnapContainerHighlightConfig.fromMap(objectMap(value("scrollSnapContainerHighlightConfig")));
        }
        /**
         * Identifier of the node to highlight.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A descriptor for the highlight appearance of scroll snap containers.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scrollSnapContainerHighlightConfig(@Nullable Overlay.ScrollSnapContainerHighlightConfig value) {
                if (value == null) values.remove("scrollSnapContainerHighlightConfig");
                else values.put("scrollSnapContainerHighlightConfig", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the node to highlight.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public ScrollSnapHighlightConfig build() {
                if (!values.containsKey("scrollSnapContainerHighlightConfig")) throw new IllegalStateException("Missing required CDP field: scrollSnapContainerHighlightConfig");
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new ScrollSnapHighlightConfig(values);
            }
        }
    }
    /**
     * Configuration for dual screen hinge
     */
    public static final class HingeConfig extends CdpObject {
        private HingeConfig(Map<String, Object> values) { super(values); }
        @Nullable public static HingeConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HingeConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A rectangle represent hinge
         * @return the protocol field value
         */
        @Nullable public DOM.Rect rect() {
            return DOM.Rect.fromMap(objectMap(value("rect")));
        }
        /**
         * The content box highlight fill color (default: a dark color).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA contentColor() {
            return DOM.RGBA.fromMap(objectMap(value("contentColor")));
        }
        /**
         * The content box highlight outline color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA outlineColor() {
            return DOM.RGBA.fromMap(objectMap(value("outlineColor")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A rectangle represent hinge
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder rect(@Nullable DOM.Rect value) {
                if (value == null) values.remove("rect");
                else values.put("rect", jsonValue(value));
                return this;
            }
            /**
             * The content box highlight fill color (default: a dark color).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contentColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("contentColor");
                else values.put("contentColor", jsonValue(value));
                return this;
            }
            /**
             * The content box highlight outline color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder outlineColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("outlineColor");
                else values.put("outlineColor", jsonValue(value));
                return this;
            }
            public HingeConfig build() {
                if (!values.containsKey("rect")) throw new IllegalStateException("Missing required CDP field: rect");
                return new HingeConfig(values);
            }
        }
    }
    /**
     * Configuration for Window Controls Overlay
     */
    public static final class WindowControlsOverlayConfig extends CdpObject {
        private WindowControlsOverlayConfig(Map<String, Object> values) { super(values); }
        @Nullable public static WindowControlsOverlayConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new WindowControlsOverlayConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether the title bar CSS should be shown when emulating the Window Controls Overlay.
         * @return the protocol field value
         */
        @Nullable public Boolean showCSS() {
            return (Boolean) value("showCSS");
        }
        /**
         * Selected platforms to show the overlay.
         * @return the protocol field value
         */
        @Nullable public String selectedPlatform() {
            return (String) value("selectedPlatform");
        }
        /**
         * The theme color defined in app manifest.
         * @return the protocol field value
         */
        @Nullable public String themeColor() {
            return (String) value("themeColor");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether the title bar CSS should be shown when emulating the Window Controls Overlay.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder showCSS(@Nullable Boolean value) {
                if (value == null) values.remove("showCSS");
                else values.put("showCSS", jsonValue(value));
                return this;
            }
            /**
             * Selected platforms to show the overlay.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder selectedPlatform(@Nullable String value) {
                if (value == null) values.remove("selectedPlatform");
                else values.put("selectedPlatform", jsonValue(value));
                return this;
            }
            /**
             * The theme color defined in app manifest.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder themeColor(@Nullable String value) {
                if (value == null) values.remove("themeColor");
                else values.put("themeColor", jsonValue(value));
                return this;
            }
            public WindowControlsOverlayConfig build() {
                if (!values.containsKey("showCSS")) throw new IllegalStateException("Missing required CDP field: showCSS");
                if (!values.containsKey("selectedPlatform")) throw new IllegalStateException("Missing required CDP field: selectedPlatform");
                if (!values.containsKey("themeColor")) throw new IllegalStateException("Missing required CDP field: themeColor");
                return new WindowControlsOverlayConfig(values);
            }
        }
    }
    /**
     */
    public static final class ContainerQueryHighlightConfig extends CdpObject {
        private ContainerQueryHighlightConfig(Map<String, Object> values) { super(values); }
        @Nullable public static ContainerQueryHighlightConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContainerQueryHighlightConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A descriptor for the highlight appearance of container query containers.
         * @return the protocol field value
         */
        @Nullable public Overlay.ContainerQueryContainerHighlightConfig containerQueryContainerHighlightConfig() {
            return Overlay.ContainerQueryContainerHighlightConfig.fromMap(objectMap(value("containerQueryContainerHighlightConfig")));
        }
        /**
         * Identifier of the container node to highlight.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A descriptor for the highlight appearance of container query containers.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder containerQueryContainerHighlightConfig(@Nullable Overlay.ContainerQueryContainerHighlightConfig value) {
                if (value == null) values.remove("containerQueryContainerHighlightConfig");
                else values.put("containerQueryContainerHighlightConfig", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the container node to highlight.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public ContainerQueryHighlightConfig build() {
                if (!values.containsKey("containerQueryContainerHighlightConfig")) throw new IllegalStateException("Missing required CDP field: containerQueryContainerHighlightConfig");
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new ContainerQueryHighlightConfig(values);
            }
        }
    }
    /**
     */
    public static final class ContainerQueryContainerHighlightConfig extends CdpObject {
        private ContainerQueryContainerHighlightConfig(Map<String, Object> values) { super(values); }
        @Nullable public static ContainerQueryContainerHighlightConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ContainerQueryContainerHighlightConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The style of the container border.
         * @return the protocol field value
         */
        @Nullable public Overlay.LineStyle containerBorder() {
            return Overlay.LineStyle.fromMap(objectMap(value("containerBorder")));
        }
        /**
         * The style of the descendants&#x27; borders.
         * @return the protocol field value
         */
        @Nullable public Overlay.LineStyle descendantBorder() {
            return Overlay.LineStyle.fromMap(objectMap(value("descendantBorder")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The style of the container border.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder containerBorder(@Nullable Overlay.LineStyle value) {
                if (value == null) values.remove("containerBorder");
                else values.put("containerBorder", jsonValue(value));
                return this;
            }
            /**
             * The style of the descendants&#x27; borders.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder descendantBorder(@Nullable Overlay.LineStyle value) {
                if (value == null) values.remove("descendantBorder");
                else values.put("descendantBorder", jsonValue(value));
                return this;
            }
            public ContainerQueryContainerHighlightConfig build() {
                return new ContainerQueryContainerHighlightConfig(values);
            }
        }
    }
    /**
     */
    public static final class IsolatedElementHighlightConfig extends CdpObject {
        private IsolatedElementHighlightConfig(Map<String, Object> values) { super(values); }
        @Nullable public static IsolatedElementHighlightConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new IsolatedElementHighlightConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A descriptor for the highlight appearance of an element in isolation mode.
         * @return the protocol field value
         */
        @Nullable public Overlay.IsolationModeHighlightConfig isolationModeHighlightConfig() {
            return Overlay.IsolationModeHighlightConfig.fromMap(objectMap(value("isolationModeHighlightConfig")));
        }
        /**
         * Identifier of the isolated element to highlight.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A descriptor for the highlight appearance of an element in isolation mode.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isolationModeHighlightConfig(@Nullable Overlay.IsolationModeHighlightConfig value) {
                if (value == null) values.remove("isolationModeHighlightConfig");
                else values.put("isolationModeHighlightConfig", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the isolated element to highlight.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public IsolatedElementHighlightConfig build() {
                if (!values.containsKey("isolationModeHighlightConfig")) throw new IllegalStateException("Missing required CDP field: isolationModeHighlightConfig");
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new IsolatedElementHighlightConfig(values);
            }
        }
    }
    /**
     */
    public static final class IsolationModeHighlightConfig extends CdpObject {
        private IsolationModeHighlightConfig(Map<String, Object> values) { super(values); }
        @Nullable public static IsolationModeHighlightConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new IsolationModeHighlightConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The fill color of the resizers (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA resizerColor() {
            return DOM.RGBA.fromMap(objectMap(value("resizerColor")));
        }
        /**
         * The fill color for resizer handles (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA resizerHandleColor() {
            return DOM.RGBA.fromMap(objectMap(value("resizerHandleColor")));
        }
        /**
         * The fill color for the mask covering non-isolated elements (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA maskColor() {
            return DOM.RGBA.fromMap(objectMap(value("maskColor")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The fill color of the resizers (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resizerColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("resizerColor");
                else values.put("resizerColor", jsonValue(value));
                return this;
            }
            /**
             * The fill color for resizer handles (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder resizerHandleColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("resizerHandleColor");
                else values.put("resizerHandleColor", jsonValue(value));
                return this;
            }
            /**
             * The fill color for the mask covering non-isolated elements (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder maskColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("maskColor");
                else values.put("maskColor", jsonValue(value));
                return this;
            }
            public IsolationModeHighlightConfig build() {
                return new IsolationModeHighlightConfig(values);
            }
        }
    }
    /**
     * Wire values for InspectMode.
     */
    public static final class InspectMode {
        private InspectMode() {}
        public static final String SEARCHFORNODE = "searchForNode";
        public static final String SEARCHFORUASHADOWDOM = "searchForUAShadowDOM";
        public static final String CAPTUREAREASCREENSHOT = "captureAreaScreenshot";
        public static final String NONE = "none";
    }
    /**
     */
    public static final class InspectedElementAnchorConfig extends CdpObject {
        private InspectedElementAnchorConfig(Map<String, Object> values) { super(values); }
        @Nullable public static InspectedElementAnchorConfig fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InspectedElementAnchorConfig(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the node to highlight.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Identifier of the backend node to highlight.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the node to highlight.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the backend node to highlight.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            public InspectedElementAnchorConfig build() {
                return new InspectedElementAnchorConfig(values);
            }
        }
    }
    /**
     * Disables domain notifications.
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
     * Disables domain notifications.
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
     * Enables domain notifications.
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
     * Enables domain notifications.
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
     * For testing.
     */
    public static final class GetHighlightObjectForTestParams extends CdpObject {
        private GetHighlightObjectForTestParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetHighlightObjectForTestParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetHighlightObjectForTestParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to get highlight object for.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Whether to include distance info.
         * @return the protocol field value
         */
        @Nullable public Boolean includeDistance() {
            return (Boolean) value("includeDistance");
        }
        /**
         * Whether to include style info.
         * @return the protocol field value
         */
        @Nullable public Boolean includeStyle() {
            return (Boolean) value("includeStyle");
        }
        /**
         * The color format to get config with (default: hex).
         * @return the protocol field value
         */
        @Nullable public String colorFormat() {
            return (String) value("colorFormat");
        }
        /**
         * Whether to show accessibility info (default: true).
         * @return the protocol field value
         */
        @Nullable public Boolean showAccessibilityInfo() {
            return (Boolean) value("showAccessibilityInfo");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to get highlight object for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Whether to include distance info.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeDistance(@Nullable Boolean value) {
                if (value == null) values.remove("includeDistance");
                else values.put("includeDistance", jsonValue(value));
                return this;
            }
            /**
             * Whether to include style info.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder includeStyle(@Nullable Boolean value) {
                if (value == null) values.remove("includeStyle");
                else values.put("includeStyle", jsonValue(value));
                return this;
            }
            /**
             * The color format to get config with (default: hex).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder colorFormat(@Nullable String value) {
                if (value == null) values.remove("colorFormat");
                else values.put("colorFormat", jsonValue(value));
                return this;
            }
            /**
             * Whether to show accessibility info (default: true).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder showAccessibilityInfo(@Nullable Boolean value) {
                if (value == null) values.remove("showAccessibilityInfo");
                else values.put("showAccessibilityInfo", jsonValue(value));
                return this;
            }
            public GetHighlightObjectForTestParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetHighlightObjectForTestParams(values);
            }
        }
    }
    /**
     * For testing.
     */
    public static final class GetHighlightObjectForTestResult extends CdpObject {
        private GetHighlightObjectForTestResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetHighlightObjectForTestResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetHighlightObjectForTestResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Highlight data for the node.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> highlight() {
            return objectMap(value("highlight"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Highlight data for the node.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder highlight(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("highlight");
                else values.put("highlight", jsonValue(value));
                return this;
            }
            public GetHighlightObjectForTestResult build() {
                if (!values.containsKey("highlight")) throw new IllegalStateException("Missing required CDP field: highlight");
                return new GetHighlightObjectForTestResult(values);
            }
        }
    }
    /**
     * For Persistent Grid testing.
     */
    public static final class GetGridHighlightObjectsForTestParams extends CdpObject {
        private GetGridHighlightObjectsForTestParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetGridHighlightObjectsForTestParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetGridHighlightObjectsForTestParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Ids of the node to get highlight object for.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Long> nodeIds() {
            return list(value("nodeIds"), element0 -> numberAsLong(element0));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Ids of the node to get highlight object for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeIds(@Nullable java.util.List<Long> value) {
                if (value == null) values.remove("nodeIds");
                else values.put("nodeIds", jsonValue(value));
                return this;
            }
            public GetGridHighlightObjectsForTestParams build() {
                if (!values.containsKey("nodeIds")) throw new IllegalStateException("Missing required CDP field: nodeIds");
                return new GetGridHighlightObjectsForTestParams(values);
            }
        }
    }
    /**
     * For Persistent Grid testing.
     */
    public static final class GetGridHighlightObjectsForTestResult extends CdpObject {
        private GetGridHighlightObjectsForTestResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetGridHighlightObjectsForTestResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetGridHighlightObjectsForTestResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Grid Highlight data for the node ids provided.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> highlights() {
            return objectMap(value("highlights"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Grid Highlight data for the node ids provided.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder highlights(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("highlights");
                else values.put("highlights", jsonValue(value));
                return this;
            }
            public GetGridHighlightObjectsForTestResult build() {
                if (!values.containsKey("highlights")) throw new IllegalStateException("Missing required CDP field: highlights");
                return new GetGridHighlightObjectsForTestResult(values);
            }
        }
    }
    /**
     * For Source Order Viewer testing.
     */
    public static final class GetSourceOrderHighlightObjectForTestParams extends CdpObject {
        private GetSourceOrderHighlightObjectForTestParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetSourceOrderHighlightObjectForTestParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSourceOrderHighlightObjectForTestParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to highlight.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to highlight.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public GetSourceOrderHighlightObjectForTestParams build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new GetSourceOrderHighlightObjectForTestParams(values);
            }
        }
    }
    /**
     * For Source Order Viewer testing.
     */
    public static final class GetSourceOrderHighlightObjectForTestResult extends CdpObject {
        private GetSourceOrderHighlightObjectForTestResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetSourceOrderHighlightObjectForTestResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetSourceOrderHighlightObjectForTestResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Source order highlight data for the node id provided.
         * @return the protocol field value
         */
        @Nullable public java.util.Map<String, Object> highlight() {
            return objectMap(value("highlight"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Source order highlight data for the node id provided.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder highlight(@Nullable java.util.Map<String, Object> value) {
                if (value == null) values.remove("highlight");
                else values.put("highlight", jsonValue(value));
                return this;
            }
            public GetSourceOrderHighlightObjectForTestResult build() {
                if (!values.containsKey("highlight")) throw new IllegalStateException("Missing required CDP field: highlight");
                return new GetSourceOrderHighlightObjectForTestResult(values);
            }
        }
    }
    /**
     * Hides any highlight.
     */
    public static final class HideHighlightParams extends CdpObject {
        private HideHighlightParams(Map<String, Object> values) { super(values); }
        @Nullable public static HideHighlightParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HideHighlightParams(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HideHighlightParams build() {
                return new HideHighlightParams(values);
            }
        }
    }
    /**
     * Hides any highlight.
     */
    public static final class HideHighlightResult extends CdpObject {
        private HideHighlightResult(Map<String, Object> values) { super(values); }
        @Nullable public static HideHighlightResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HideHighlightResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HideHighlightResult build() {
                return new HideHighlightResult(values);
            }
        }
    }
    /**
     * Highlights owner element of the frame with given id. Deprecated: Doesn&#x27;t work reliably and cannot be fixed due to process separation (the owner node might be in a different process). Determine the owner node in the client and use highlightNode.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class HighlightFrameParams extends CdpObject {
        private HighlightFrameParams(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightFrameParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightFrameParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Identifier of the frame to highlight.
         * @return the protocol field value
         */
        @Nullable public String frameId() {
            return (String) value("frameId");
        }
        /**
         * The content box highlight fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA contentColor() {
            return DOM.RGBA.fromMap(objectMap(value("contentColor")));
        }
        /**
         * The content box highlight outline color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA contentOutlineColor() {
            return DOM.RGBA.fromMap(objectMap(value("contentOutlineColor")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Identifier of the frame to highlight.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder frameId(@Nullable String value) {
                if (value == null) values.remove("frameId");
                else values.put("frameId", jsonValue(value));
                return this;
            }
            /**
             * The content box highlight fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contentColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("contentColor");
                else values.put("contentColor", jsonValue(value));
                return this;
            }
            /**
             * The content box highlight outline color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder contentOutlineColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("contentOutlineColor");
                else values.put("contentOutlineColor", jsonValue(value));
                return this;
            }
            public HighlightFrameParams build() {
                if (!values.containsKey("frameId")) throw new IllegalStateException("Missing required CDP field: frameId");
                return new HighlightFrameParams(values);
            }
        }
    }
    /**
     * Highlights owner element of the frame with given id. Deprecated: Doesn&#x27;t work reliably and cannot be fixed due to process separation (the owner node might be in a different process). Determine the owner node in the client and use highlightNode.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class HighlightFrameResult extends CdpObject {
        private HighlightFrameResult(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightFrameResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightFrameResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HighlightFrameResult build() {
                return new HighlightFrameResult(values);
            }
        }
    }
    /**
     * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
     */
    public static final class HighlightNodeParams extends CdpObject {
        private HighlightNodeParams(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightNodeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightNodeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A descriptor for the highlight appearance.
         * @return the protocol field value
         */
        @Nullable public Overlay.HighlightConfig highlightConfig() {
            return Overlay.HighlightConfig.fromMap(objectMap(value("highlightConfig")));
        }
        /**
         * Identifier of the node to highlight.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Identifier of the backend node to highlight.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * JavaScript object id of the node to be highlighted.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        /**
         * Selectors to highlight relevant nodes.
         * @return the protocol field value
         */
        @Nullable public String selector() {
            return (String) value("selector");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A descriptor for the highlight appearance.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder highlightConfig(@Nullable Overlay.HighlightConfig value) {
                if (value == null) values.remove("highlightConfig");
                else values.put("highlightConfig", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the node to highlight.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the backend node to highlight.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript object id of the node to be highlighted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            /**
             * Selectors to highlight relevant nodes.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder selector(@Nullable String value) {
                if (value == null) values.remove("selector");
                else values.put("selector", jsonValue(value));
                return this;
            }
            public HighlightNodeParams build() {
                if (!values.containsKey("highlightConfig")) throw new IllegalStateException("Missing required CDP field: highlightConfig");
                return new HighlightNodeParams(values);
            }
        }
    }
    /**
     * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
     */
    public static final class HighlightNodeResult extends CdpObject {
        private HighlightNodeResult(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightNodeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightNodeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HighlightNodeResult build() {
                return new HighlightNodeResult(values);
            }
        }
    }
    /**
     * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
     */
    public static final class HighlightQuadParams extends CdpObject {
        private HighlightQuadParams(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightQuadParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightQuadParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Quad to highlight
         * @return the protocol field value
         */
        @Nullable public java.util.List<Double> quad() {
            return list(value("quad"), element0 -> numberAsDouble(element0));
        }
        /**
         * The highlight fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA color() {
            return DOM.RGBA.fromMap(objectMap(value("color")));
        }
        /**
         * The highlight outline color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA outlineColor() {
            return DOM.RGBA.fromMap(objectMap(value("outlineColor")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Quad to highlight
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder quad(@Nullable java.util.List<Double> value) {
                if (value == null) values.remove("quad");
                else values.put("quad", jsonValue(value));
                return this;
            }
            /**
             * The highlight fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder color(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("color");
                else values.put("color", jsonValue(value));
                return this;
            }
            /**
             * The highlight outline color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder outlineColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("outlineColor");
                else values.put("outlineColor", jsonValue(value));
                return this;
            }
            public HighlightQuadParams build() {
                if (!values.containsKey("quad")) throw new IllegalStateException("Missing required CDP field: quad");
                return new HighlightQuadParams(values);
            }
        }
    }
    /**
     * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
     */
    public static final class HighlightQuadResult extends CdpObject {
        private HighlightQuadResult(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightQuadResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightQuadResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HighlightQuadResult build() {
                return new HighlightQuadResult(values);
            }
        }
    }
    /**
     * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport. Issue: the method does not handle device pixel ratio (DPR) correctly. The coordinates currently have to be adjusted by the client if DPR is not 1 (see crbug.com/437807128).
     */
    public static final class HighlightRectParams extends CdpObject {
        private HighlightRectParams(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightRectParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightRectParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * X coordinate
         * @return the protocol field value
         */
        @Nullable public Long x() {
            return numberAsLong(value("x"));
        }
        /**
         * Y coordinate
         * @return the protocol field value
         */
        @Nullable public Long y() {
            return numberAsLong(value("y"));
        }
        /**
         * Rectangle width
         * @return the protocol field value
         */
        @Nullable public Long width() {
            return numberAsLong(value("width"));
        }
        /**
         * Rectangle height
         * @return the protocol field value
         */
        @Nullable public Long height() {
            return numberAsLong(value("height"));
        }
        /**
         * The highlight fill color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA color() {
            return DOM.RGBA.fromMap(objectMap(value("color")));
        }
        /**
         * The highlight outline color (default: transparent).
         * @return the protocol field value
         */
        @Nullable public DOM.RGBA outlineColor() {
            return DOM.RGBA.fromMap(objectMap(value("outlineColor")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * X coordinate
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder x(@Nullable Long value) {
                if (value == null) values.remove("x");
                else values.put("x", jsonValue(value));
                return this;
            }
            /**
             * Y coordinate
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder y(@Nullable Long value) {
                if (value == null) values.remove("y");
                else values.put("y", jsonValue(value));
                return this;
            }
            /**
             * Rectangle width
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder width(@Nullable Long value) {
                if (value == null) values.remove("width");
                else values.put("width", jsonValue(value));
                return this;
            }
            /**
             * Rectangle height
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder height(@Nullable Long value) {
                if (value == null) values.remove("height");
                else values.put("height", jsonValue(value));
                return this;
            }
            /**
             * The highlight fill color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder color(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("color");
                else values.put("color", jsonValue(value));
                return this;
            }
            /**
             * The highlight outline color (default: transparent).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder outlineColor(@Nullable DOM.RGBA value) {
                if (value == null) values.remove("outlineColor");
                else values.put("outlineColor", jsonValue(value));
                return this;
            }
            public HighlightRectParams build() {
                if (!values.containsKey("x")) throw new IllegalStateException("Missing required CDP field: x");
                if (!values.containsKey("y")) throw new IllegalStateException("Missing required CDP field: y");
                if (!values.containsKey("width")) throw new IllegalStateException("Missing required CDP field: width");
                if (!values.containsKey("height")) throw new IllegalStateException("Missing required CDP field: height");
                return new HighlightRectParams(values);
            }
        }
    }
    /**
     * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport. Issue: the method does not handle device pixel ratio (DPR) correctly. The coordinates currently have to be adjusted by the client if DPR is not 1 (see crbug.com/437807128).
     */
    public static final class HighlightRectResult extends CdpObject {
        private HighlightRectResult(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightRectResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightRectResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HighlightRectResult build() {
                return new HighlightRectResult(values);
            }
        }
    }
    /**
     * Highlights the source order of the children of the DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
     */
    public static final class HighlightSourceOrderParams extends CdpObject {
        private HighlightSourceOrderParams(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightSourceOrderParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightSourceOrderParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * A descriptor for the appearance of the overlay drawing.
         * @return the protocol field value
         */
        @Nullable public Overlay.SourceOrderConfig sourceOrderConfig() {
            return Overlay.SourceOrderConfig.fromMap(objectMap(value("sourceOrderConfig")));
        }
        /**
         * Identifier of the node to highlight.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        /**
         * Identifier of the backend node to highlight.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        /**
         * JavaScript object id of the node to be highlighted.
         * @return the protocol field value
         */
        @Nullable public String objectId() {
            return (String) value("objectId");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * A descriptor for the appearance of the overlay drawing.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder sourceOrderConfig(@Nullable Overlay.SourceOrderConfig value) {
                if (value == null) values.remove("sourceOrderConfig");
                else values.put("sourceOrderConfig", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the node to highlight.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            /**
             * Identifier of the backend node to highlight.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            /**
             * JavaScript object id of the node to be highlighted.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder objectId(@Nullable String value) {
                if (value == null) values.remove("objectId");
                else values.put("objectId", jsonValue(value));
                return this;
            }
            public HighlightSourceOrderParams build() {
                if (!values.containsKey("sourceOrderConfig")) throw new IllegalStateException("Missing required CDP field: sourceOrderConfig");
                return new HighlightSourceOrderParams(values);
            }
        }
    }
    /**
     * Highlights the source order of the children of the DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
     */
    public static final class HighlightSourceOrderResult extends CdpObject {
        private HighlightSourceOrderResult(Map<String, Object> values) { super(values); }
        @Nullable public static HighlightSourceOrderResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new HighlightSourceOrderResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public HighlightSourceOrderResult build() {
                return new HighlightSourceOrderResult(values);
            }
        }
    }
    /**
     * Enters the &#x27;inspect&#x27; mode. In this mode, elements that user is hovering over are highlighted. Backend then generates &#x27;inspectNodeRequested&#x27; event upon element selection.
     */
    public static final class SetInspectModeParams extends CdpObject {
        private SetInspectModeParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetInspectModeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInspectModeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Set an inspection mode.
         * @return the protocol field value
         */
        @Nullable public String mode() {
            return (String) value("mode");
        }
        /**
         * A descriptor for the highlight appearance of hovered-over nodes. May be omitted if {@code enabled == false}.
         * @return the protocol field value
         */
        @Nullable public Overlay.HighlightConfig highlightConfig() {
            return Overlay.HighlightConfig.fromMap(objectMap(value("highlightConfig")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Set an inspection mode.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder mode(@Nullable String value) {
                if (value == null) values.remove("mode");
                else values.put("mode", jsonValue(value));
                return this;
            }
            /**
             * A descriptor for the highlight appearance of hovered-over nodes. May be omitted if {@code enabled == false}.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder highlightConfig(@Nullable Overlay.HighlightConfig value) {
                if (value == null) values.remove("highlightConfig");
                else values.put("highlightConfig", jsonValue(value));
                return this;
            }
            public SetInspectModeParams build() {
                if (!values.containsKey("mode")) throw new IllegalStateException("Missing required CDP field: mode");
                return new SetInspectModeParams(values);
            }
        }
    }
    /**
     * Enters the &#x27;inspect&#x27; mode. In this mode, elements that user is hovering over are highlighted. Backend then generates &#x27;inspectNodeRequested&#x27; event upon element selection.
     */
    public static final class SetInspectModeResult extends CdpObject {
        private SetInspectModeResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetInspectModeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetInspectModeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetInspectModeResult build() {
                return new SetInspectModeResult(values);
            }
        }
    }
    /**
     * Highlights owner element of all frames detected to be ads.
     */
    public static final class SetShowAdHighlightsParams extends CdpObject {
        private SetShowAdHighlightsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowAdHighlightsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowAdHighlightsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True for showing ad highlights
         * @return the protocol field value
         */
        @Nullable public Boolean show() {
            return (Boolean) value("show");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True for showing ad highlights
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder show(@Nullable Boolean value) {
                if (value == null) values.remove("show");
                else values.put("show", jsonValue(value));
                return this;
            }
            public SetShowAdHighlightsParams build() {
                if (!values.containsKey("show")) throw new IllegalStateException("Missing required CDP field: show");
                return new SetShowAdHighlightsParams(values);
            }
        }
    }
    /**
     * Highlights owner element of all frames detected to be ads.
     */
    public static final class SetShowAdHighlightsResult extends CdpObject {
        private SetShowAdHighlightsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowAdHighlightsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowAdHighlightsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowAdHighlightsResult build() {
                return new SetShowAdHighlightsResult(values);
            }
        }
    }
    /**
     * Parameters for Overlay.setPausedInDebuggerMessage.
     */
    public static final class SetPausedInDebuggerMessageParams extends CdpObject {
        private SetPausedInDebuggerMessageParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetPausedInDebuggerMessageParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPausedInDebuggerMessageParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * The message to display, also triggers resume and step over controls.
         * @return the protocol field value
         */
        @Nullable public String message() {
            return (String) value("message");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * The message to display, also triggers resume and step over controls.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder message(@Nullable String value) {
                if (value == null) values.remove("message");
                else values.put("message", jsonValue(value));
                return this;
            }
            public SetPausedInDebuggerMessageParams build() {
                return new SetPausedInDebuggerMessageParams(values);
            }
        }
    }
    /**
     * Result of Overlay.setPausedInDebuggerMessage.
     */
    public static final class SetPausedInDebuggerMessageResult extends CdpObject {
        private SetPausedInDebuggerMessageResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetPausedInDebuggerMessageResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetPausedInDebuggerMessageResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetPausedInDebuggerMessageResult build() {
                return new SetPausedInDebuggerMessageResult(values);
            }
        }
    }
    /**
     * Requests that backend shows debug borders on layers
     */
    public static final class SetShowDebugBordersParams extends CdpObject {
        private SetShowDebugBordersParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowDebugBordersParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowDebugBordersParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True for showing debug borders
         * @return the protocol field value
         */
        @Nullable public Boolean show() {
            return (Boolean) value("show");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True for showing debug borders
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder show(@Nullable Boolean value) {
                if (value == null) values.remove("show");
                else values.put("show", jsonValue(value));
                return this;
            }
            public SetShowDebugBordersParams build() {
                if (!values.containsKey("show")) throw new IllegalStateException("Missing required CDP field: show");
                return new SetShowDebugBordersParams(values);
            }
        }
    }
    /**
     * Requests that backend shows debug borders on layers
     */
    public static final class SetShowDebugBordersResult extends CdpObject {
        private SetShowDebugBordersResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowDebugBordersResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowDebugBordersResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowDebugBordersResult build() {
                return new SetShowDebugBordersResult(values);
            }
        }
    }
    /**
     * Requests that backend shows the FPS counter
     */
    public static final class SetShowFPSCounterParams extends CdpObject {
        private SetShowFPSCounterParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowFPSCounterParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowFPSCounterParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True for showing the FPS counter
         * @return the protocol field value
         */
        @Nullable public Boolean show() {
            return (Boolean) value("show");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True for showing the FPS counter
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder show(@Nullable Boolean value) {
                if (value == null) values.remove("show");
                else values.put("show", jsonValue(value));
                return this;
            }
            public SetShowFPSCounterParams build() {
                if (!values.containsKey("show")) throw new IllegalStateException("Missing required CDP field: show");
                return new SetShowFPSCounterParams(values);
            }
        }
    }
    /**
     * Requests that backend shows the FPS counter
     */
    public static final class SetShowFPSCounterResult extends CdpObject {
        private SetShowFPSCounterResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowFPSCounterResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowFPSCounterResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowFPSCounterResult build() {
                return new SetShowFPSCounterResult(values);
            }
        }
    }
    /**
     * Highlight multiple elements with the CSS Grid overlay.
     */
    public static final class SetShowGridOverlaysParams extends CdpObject {
        private SetShowGridOverlaysParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowGridOverlaysParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowGridOverlaysParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Overlay.GridNodeHighlightConfig> gridNodeHighlightConfigs() {
            return list(value("gridNodeHighlightConfigs"), element0 -> Overlay.GridNodeHighlightConfig.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An array of node identifiers and descriptors for the highlight appearance.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder gridNodeHighlightConfigs(@Nullable java.util.List<Overlay.GridNodeHighlightConfig> value) {
                if (value == null) values.remove("gridNodeHighlightConfigs");
                else values.put("gridNodeHighlightConfigs", jsonValue(value));
                return this;
            }
            public SetShowGridOverlaysParams build() {
                if (!values.containsKey("gridNodeHighlightConfigs")) throw new IllegalStateException("Missing required CDP field: gridNodeHighlightConfigs");
                return new SetShowGridOverlaysParams(values);
            }
        }
    }
    /**
     * Highlight multiple elements with the CSS Grid overlay.
     */
    public static final class SetShowGridOverlaysResult extends CdpObject {
        private SetShowGridOverlaysResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowGridOverlaysResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowGridOverlaysResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowGridOverlaysResult build() {
                return new SetShowGridOverlaysResult(values);
            }
        }
    }
    /**
     * Parameters for Overlay.setShowFlexOverlays.
     */
    public static final class SetShowFlexOverlaysParams extends CdpObject {
        private SetShowFlexOverlaysParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowFlexOverlaysParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowFlexOverlaysParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Overlay.FlexNodeHighlightConfig> flexNodeHighlightConfigs() {
            return list(value("flexNodeHighlightConfigs"), element0 -> Overlay.FlexNodeHighlightConfig.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An array of node identifiers and descriptors for the highlight appearance.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder flexNodeHighlightConfigs(@Nullable java.util.List<Overlay.FlexNodeHighlightConfig> value) {
                if (value == null) values.remove("flexNodeHighlightConfigs");
                else values.put("flexNodeHighlightConfigs", jsonValue(value));
                return this;
            }
            public SetShowFlexOverlaysParams build() {
                if (!values.containsKey("flexNodeHighlightConfigs")) throw new IllegalStateException("Missing required CDP field: flexNodeHighlightConfigs");
                return new SetShowFlexOverlaysParams(values);
            }
        }
    }
    /**
     * Result of Overlay.setShowFlexOverlays.
     */
    public static final class SetShowFlexOverlaysResult extends CdpObject {
        private SetShowFlexOverlaysResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowFlexOverlaysResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowFlexOverlaysResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowFlexOverlaysResult build() {
                return new SetShowFlexOverlaysResult(values);
            }
        }
    }
    /**
     * Parameters for Overlay.setShowScrollSnapOverlays.
     */
    public static final class SetShowScrollSnapOverlaysParams extends CdpObject {
        private SetShowScrollSnapOverlaysParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowScrollSnapOverlaysParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowScrollSnapOverlaysParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Overlay.ScrollSnapHighlightConfig> scrollSnapHighlightConfigs() {
            return list(value("scrollSnapHighlightConfigs"), element0 -> Overlay.ScrollSnapHighlightConfig.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An array of node identifiers and descriptors for the highlight appearance.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder scrollSnapHighlightConfigs(@Nullable java.util.List<Overlay.ScrollSnapHighlightConfig> value) {
                if (value == null) values.remove("scrollSnapHighlightConfigs");
                else values.put("scrollSnapHighlightConfigs", jsonValue(value));
                return this;
            }
            public SetShowScrollSnapOverlaysParams build() {
                if (!values.containsKey("scrollSnapHighlightConfigs")) throw new IllegalStateException("Missing required CDP field: scrollSnapHighlightConfigs");
                return new SetShowScrollSnapOverlaysParams(values);
            }
        }
    }
    /**
     * Result of Overlay.setShowScrollSnapOverlays.
     */
    public static final class SetShowScrollSnapOverlaysResult extends CdpObject {
        private SetShowScrollSnapOverlaysResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowScrollSnapOverlaysResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowScrollSnapOverlaysResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowScrollSnapOverlaysResult build() {
                return new SetShowScrollSnapOverlaysResult(values);
            }
        }
    }
    /**
     * Parameters for Overlay.setShowContainerQueryOverlays.
     */
    public static final class SetShowContainerQueryOverlaysParams extends CdpObject {
        private SetShowContainerQueryOverlaysParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowContainerQueryOverlaysParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowContainerQueryOverlaysParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Overlay.ContainerQueryHighlightConfig> containerQueryHighlightConfigs() {
            return list(value("containerQueryHighlightConfigs"), element0 -> Overlay.ContainerQueryHighlightConfig.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An array of node identifiers and descriptors for the highlight appearance.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder containerQueryHighlightConfigs(@Nullable java.util.List<Overlay.ContainerQueryHighlightConfig> value) {
                if (value == null) values.remove("containerQueryHighlightConfigs");
                else values.put("containerQueryHighlightConfigs", jsonValue(value));
                return this;
            }
            public SetShowContainerQueryOverlaysParams build() {
                if (!values.containsKey("containerQueryHighlightConfigs")) throw new IllegalStateException("Missing required CDP field: containerQueryHighlightConfigs");
                return new SetShowContainerQueryOverlaysParams(values);
            }
        }
    }
    /**
     * Result of Overlay.setShowContainerQueryOverlays.
     */
    public static final class SetShowContainerQueryOverlaysResult extends CdpObject {
        private SetShowContainerQueryOverlaysResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowContainerQueryOverlaysResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowContainerQueryOverlaysResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowContainerQueryOverlaysResult build() {
                return new SetShowContainerQueryOverlaysResult(values);
            }
        }
    }
    /**
     * Parameters for Overlay.setShowInspectedElementAnchor.
     */
    public static final class SetShowInspectedElementAnchorParams extends CdpObject {
        private SetShowInspectedElementAnchorParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowInspectedElementAnchorParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowInspectedElementAnchorParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Node identifier for which to show an anchor for.
         * @return the protocol field value
         */
        @Nullable public Overlay.InspectedElementAnchorConfig inspectedElementAnchorConfig() {
            return Overlay.InspectedElementAnchorConfig.fromMap(objectMap(value("inspectedElementAnchorConfig")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Node identifier for which to show an anchor for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder inspectedElementAnchorConfig(@Nullable Overlay.InspectedElementAnchorConfig value) {
                if (value == null) values.remove("inspectedElementAnchorConfig");
                else values.put("inspectedElementAnchorConfig", jsonValue(value));
                return this;
            }
            public SetShowInspectedElementAnchorParams build() {
                if (!values.containsKey("inspectedElementAnchorConfig")) throw new IllegalStateException("Missing required CDP field: inspectedElementAnchorConfig");
                return new SetShowInspectedElementAnchorParams(values);
            }
        }
    }
    /**
     * Result of Overlay.setShowInspectedElementAnchor.
     */
    public static final class SetShowInspectedElementAnchorResult extends CdpObject {
        private SetShowInspectedElementAnchorResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowInspectedElementAnchorResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowInspectedElementAnchorResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowInspectedElementAnchorResult build() {
                return new SetShowInspectedElementAnchorResult(values);
            }
        }
    }
    /**
     * Requests that backend shows paint rectangles
     */
    public static final class SetShowPaintRectsParams extends CdpObject {
        private SetShowPaintRectsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowPaintRectsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowPaintRectsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True for showing paint rectangles
         * @return the protocol field value
         */
        @Nullable public Boolean result() {
            return (Boolean) value("result");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True for showing paint rectangles
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable Boolean value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            public SetShowPaintRectsParams build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new SetShowPaintRectsParams(values);
            }
        }
    }
    /**
     * Requests that backend shows paint rectangles
     */
    public static final class SetShowPaintRectsResult extends CdpObject {
        private SetShowPaintRectsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowPaintRectsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowPaintRectsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowPaintRectsResult build() {
                return new SetShowPaintRectsResult(values);
            }
        }
    }
    /**
     * Requests that backend shows layout shift regions
     */
    public static final class SetShowLayoutShiftRegionsParams extends CdpObject {
        private SetShowLayoutShiftRegionsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowLayoutShiftRegionsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowLayoutShiftRegionsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True for showing layout shift regions
         * @return the protocol field value
         */
        @Nullable public Boolean result() {
            return (Boolean) value("result");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True for showing layout shift regions
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder result(@Nullable Boolean value) {
                if (value == null) values.remove("result");
                else values.put("result", jsonValue(value));
                return this;
            }
            public SetShowLayoutShiftRegionsParams build() {
                if (!values.containsKey("result")) throw new IllegalStateException("Missing required CDP field: result");
                return new SetShowLayoutShiftRegionsParams(values);
            }
        }
    }
    /**
     * Requests that backend shows layout shift regions
     */
    public static final class SetShowLayoutShiftRegionsResult extends CdpObject {
        private SetShowLayoutShiftRegionsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowLayoutShiftRegionsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowLayoutShiftRegionsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowLayoutShiftRegionsResult build() {
                return new SetShowLayoutShiftRegionsResult(values);
            }
        }
    }
    /**
     * Requests that backend shows scroll bottleneck rects
     */
    public static final class SetShowScrollBottleneckRectsParams extends CdpObject {
        private SetShowScrollBottleneckRectsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowScrollBottleneckRectsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowScrollBottleneckRectsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True for showing scroll bottleneck rects
         * @return the protocol field value
         */
        @Nullable public Boolean show() {
            return (Boolean) value("show");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True for showing scroll bottleneck rects
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder show(@Nullable Boolean value) {
                if (value == null) values.remove("show");
                else values.put("show", jsonValue(value));
                return this;
            }
            public SetShowScrollBottleneckRectsParams build() {
                if (!values.containsKey("show")) throw new IllegalStateException("Missing required CDP field: show");
                return new SetShowScrollBottleneckRectsParams(values);
            }
        }
    }
    /**
     * Requests that backend shows scroll bottleneck rects
     */
    public static final class SetShowScrollBottleneckRectsResult extends CdpObject {
        private SetShowScrollBottleneckRectsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowScrollBottleneckRectsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowScrollBottleneckRectsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowScrollBottleneckRectsResult build() {
                return new SetShowScrollBottleneckRectsResult(values);
            }
        }
    }
    /**
     * Deprecated, no longer has any effect.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetShowHitTestBordersParams extends CdpObject {
        private SetShowHitTestBordersParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowHitTestBordersParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowHitTestBordersParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * True for showing hit-test borders
         * @return the protocol field value
         */
        @Nullable public Boolean show() {
            return (Boolean) value("show");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * True for showing hit-test borders
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder show(@Nullable Boolean value) {
                if (value == null) values.remove("show");
                else values.put("show", jsonValue(value));
                return this;
            }
            public SetShowHitTestBordersParams build() {
                if (!values.containsKey("show")) throw new IllegalStateException("Missing required CDP field: show");
                return new SetShowHitTestBordersParams(values);
            }
        }
    }
    /**
     * Deprecated, no longer has any effect.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetShowHitTestBordersResult extends CdpObject {
        private SetShowHitTestBordersResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowHitTestBordersResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowHitTestBordersResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowHitTestBordersResult build() {
                return new SetShowHitTestBordersResult(values);
            }
        }
    }
    /**
     * Deprecated, no longer has any effect.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetShowWebVitalsParams extends CdpObject {
        private SetShowWebVitalsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowWebVitalsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowWebVitalsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the show field.
         * @return the protocol field value
         */
        @Nullable public Boolean show() {
            return (Boolean) value("show");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the show field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder show(@Nullable Boolean value) {
                if (value == null) values.remove("show");
                else values.put("show", jsonValue(value));
                return this;
            }
            public SetShowWebVitalsParams build() {
                if (!values.containsKey("show")) throw new IllegalStateException("Missing required CDP field: show");
                return new SetShowWebVitalsParams(values);
            }
        }
    }
    /**
     * Deprecated, no longer has any effect.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetShowWebVitalsResult extends CdpObject {
        private SetShowWebVitalsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowWebVitalsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowWebVitalsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowWebVitalsResult build() {
                return new SetShowWebVitalsResult(values);
            }
        }
    }
    /**
     * Paints viewport size upon main frame resize.
     */
    public static final class SetShowViewportSizeOnResizeParams extends CdpObject {
        private SetShowViewportSizeOnResizeParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowViewportSizeOnResizeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowViewportSizeOnResizeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Whether to paint size or not.
         * @return the protocol field value
         */
        @Nullable public Boolean show() {
            return (Boolean) value("show");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Whether to paint size or not.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder show(@Nullable Boolean value) {
                if (value == null) values.remove("show");
                else values.put("show", jsonValue(value));
                return this;
            }
            public SetShowViewportSizeOnResizeParams build() {
                if (!values.containsKey("show")) throw new IllegalStateException("Missing required CDP field: show");
                return new SetShowViewportSizeOnResizeParams(values);
            }
        }
    }
    /**
     * Paints viewport size upon main frame resize.
     */
    public static final class SetShowViewportSizeOnResizeResult extends CdpObject {
        private SetShowViewportSizeOnResizeResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowViewportSizeOnResizeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowViewportSizeOnResizeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowViewportSizeOnResizeResult build() {
                return new SetShowViewportSizeOnResizeResult(values);
            }
        }
    }
    /**
     * Add a dual screen device hinge
     */
    public static final class SetShowHingeParams extends CdpObject {
        private SetShowHingeParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowHingeParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowHingeParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * hinge data, null means hideHinge
         * @return the protocol field value
         */
        @Nullable public Overlay.HingeConfig hingeConfig() {
            return Overlay.HingeConfig.fromMap(objectMap(value("hingeConfig")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * hinge data, null means hideHinge
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder hingeConfig(@Nullable Overlay.HingeConfig value) {
                if (value == null) values.remove("hingeConfig");
                else values.put("hingeConfig", jsonValue(value));
                return this;
            }
            public SetShowHingeParams build() {
                return new SetShowHingeParams(values);
            }
        }
    }
    /**
     * Add a dual screen device hinge
     */
    public static final class SetShowHingeResult extends CdpObject {
        private SetShowHingeResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowHingeResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowHingeResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowHingeResult build() {
                return new SetShowHingeResult(values);
            }
        }
    }
    /**
     * Show elements in isolation mode with overlays.
     */
    public static final class SetShowIsolatedElementsParams extends CdpObject {
        private SetShowIsolatedElementsParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowIsolatedElementsParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowIsolatedElementsParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        @Nullable public java.util.List<Overlay.IsolatedElementHighlightConfig> isolatedElementHighlightConfigs() {
            return list(value("isolatedElementHighlightConfigs"), element0 -> Overlay.IsolatedElementHighlightConfig.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * An array of node identifiers and descriptors for the highlight appearance.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder isolatedElementHighlightConfigs(@Nullable java.util.List<Overlay.IsolatedElementHighlightConfig> value) {
                if (value == null) values.remove("isolatedElementHighlightConfigs");
                else values.put("isolatedElementHighlightConfigs", jsonValue(value));
                return this;
            }
            public SetShowIsolatedElementsParams build() {
                if (!values.containsKey("isolatedElementHighlightConfigs")) throw new IllegalStateException("Missing required CDP field: isolatedElementHighlightConfigs");
                return new SetShowIsolatedElementsParams(values);
            }
        }
    }
    /**
     * Show elements in isolation mode with overlays.
     */
    public static final class SetShowIsolatedElementsResult extends CdpObject {
        private SetShowIsolatedElementsResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowIsolatedElementsResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowIsolatedElementsResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowIsolatedElementsResult build() {
                return new SetShowIsolatedElementsResult(values);
            }
        }
    }
    /**
     * Show Window Controls Overlay for PWA
     */
    public static final class SetShowWindowControlsOverlayParams extends CdpObject {
        private SetShowWindowControlsOverlayParams(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowWindowControlsOverlayParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowWindowControlsOverlayParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Window Controls Overlay data, null means hide Window Controls Overlay
         * @return the protocol field value
         */
        @Nullable public Overlay.WindowControlsOverlayConfig windowControlsOverlayConfig() {
            return Overlay.WindowControlsOverlayConfig.fromMap(objectMap(value("windowControlsOverlayConfig")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Window Controls Overlay data, null means hide Window Controls Overlay
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder windowControlsOverlayConfig(@Nullable Overlay.WindowControlsOverlayConfig value) {
                if (value == null) values.remove("windowControlsOverlayConfig");
                else values.put("windowControlsOverlayConfig", jsonValue(value));
                return this;
            }
            public SetShowWindowControlsOverlayParams build() {
                return new SetShowWindowControlsOverlayParams(values);
            }
        }
    }
    /**
     * Show Window Controls Overlay for PWA
     */
    public static final class SetShowWindowControlsOverlayResult extends CdpObject {
        private SetShowWindowControlsOverlayResult(Map<String, Object> values) { super(values); }
        @Nullable public static SetShowWindowControlsOverlayResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new SetShowWindowControlsOverlayResult(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public SetShowWindowControlsOverlayResult build() {
                return new SetShowWindowControlsOverlayResult(values);
            }
        }
    }
    /**
     * Fired when the node should be inspected. This happens after call to {@code setInspectMode} or when user manually inspects an element.
     */
    public static final class InspectNodeRequestedEvent extends CdpObject {
        private InspectNodeRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static InspectNodeRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InspectNodeRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to inspect.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to inspect.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            public InspectNodeRequestedEvent build() {
                if (!values.containsKey("backendNodeId")) throw new IllegalStateException("Missing required CDP field: backendNodeId");
                return new InspectNodeRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when the node should be highlighted. This happens after call to {@code setInspectMode}.
     */
    public static final class NodeHighlightRequestedEvent extends CdpObject {
        private NodeHighlightRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static NodeHighlightRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new NodeHighlightRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        @Nullable public Long nodeId() {
            return numberAsLong(value("nodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the nodeId field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nodeId(@Nullable Long value) {
                if (value == null) values.remove("nodeId");
                else values.put("nodeId", jsonValue(value));
                return this;
            }
            public NodeHighlightRequestedEvent build() {
                if (!values.containsKey("nodeId")) throw new IllegalStateException("Missing required CDP field: nodeId");
                return new NodeHighlightRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when user asks to capture screenshot of some area on the page.
     */
    public static final class ScreenshotRequestedEvent extends CdpObject {
        private ScreenshotRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static ScreenshotRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new ScreenshotRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Viewport to capture, in device independent pixels (dip).
         * @return the protocol field value
         */
        @Nullable public Page.Viewport viewport() {
            return Page.Viewport.fromMap(objectMap(value("viewport")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Viewport to capture, in device independent pixels (dip).
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder viewport(@Nullable Page.Viewport value) {
                if (value == null) values.remove("viewport");
                else values.put("viewport", jsonValue(value));
                return this;
            }
            public ScreenshotRequestedEvent build() {
                if (!values.containsKey("viewport")) throw new IllegalStateException("Missing required CDP field: viewport");
                return new ScreenshotRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when user asks to show the Inspect panel.
     */
    public static final class InspectPanelShowRequestedEvent extends CdpObject {
        private InspectPanelShowRequestedEvent(Map<String, Object> values) { super(values); }
        @Nullable public static InspectPanelShowRequestedEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InspectPanelShowRequestedEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to show in the panel.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to show in the panel.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            public InspectPanelShowRequestedEvent build() {
                if (!values.containsKey("backendNodeId")) throw new IllegalStateException("Missing required CDP field: backendNodeId");
                return new InspectPanelShowRequestedEvent(values);
            }
        }
    }
    /**
     * Fired when user asks to restore the Inspected Element floating window.
     */
    public static final class InspectedElementWindowRestoredEvent extends CdpObject {
        private InspectedElementWindowRestoredEvent(Map<String, Object> values) { super(values); }
        @Nullable public static InspectedElementWindowRestoredEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InspectedElementWindowRestoredEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Id of the node to restore the floating window for.
         * @return the protocol field value
         */
        @Nullable public Long backendNodeId() {
            return numberAsLong(value("backendNodeId"));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Id of the node to restore the floating window for.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder backendNodeId(@Nullable Long value) {
                if (value == null) values.remove("backendNodeId");
                else values.put("backendNodeId", jsonValue(value));
                return this;
            }
            public InspectedElementWindowRestoredEvent build() {
                if (!values.containsKey("backendNodeId")) throw new IllegalStateException("Missing required CDP field: backendNodeId");
                return new InspectedElementWindowRestoredEvent(values);
            }
        }
    }
    /**
     * Fired when user cancels the inspect mode.
     */
    public static final class InspectModeCanceledEvent extends CdpObject {
        private InspectModeCanceledEvent(Map<String, Object> values) { super(values); }
        @Nullable public static InspectModeCanceledEvent fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new InspectModeCanceledEvent(values);
        }
        public static Builder builder() { return new Builder(); }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            public InspectModeCanceledEvent build() {
                return new InspectModeCanceledEvent(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disables domain notifications.
         * @return a stage completing with the command result
         */
        public CompletionStage<DisableResult> disable() {
            return client.call("Overlay.disable", null, DisableResult::fromMap);
        }
        /**
         * Enables domain notifications.
         * @return a stage completing with the command result
         */
        public CompletionStage<EnableResult> enable() {
            return client.call("Overlay.enable", null, EnableResult::fromMap);
        }
        /**
         * For testing.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetHighlightObjectForTestResult> getHighlightObjectForTest(GetHighlightObjectForTestParams params) {
            return client.call("Overlay.getHighlightObjectForTest", params, GetHighlightObjectForTestResult::fromMap);
        }
        /**
         * For Persistent Grid testing.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetGridHighlightObjectsForTestResult> getGridHighlightObjectsForTest(GetGridHighlightObjectsForTestParams params) {
            return client.call("Overlay.getGridHighlightObjectsForTest", params, GetGridHighlightObjectsForTestResult::fromMap);
        }
        /**
         * For Source Order Viewer testing.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetSourceOrderHighlightObjectForTestResult> getSourceOrderHighlightObjectForTest(GetSourceOrderHighlightObjectForTestParams params) {
            return client.call("Overlay.getSourceOrderHighlightObjectForTest", params, GetSourceOrderHighlightObjectForTestResult::fromMap);
        }
        /**
         * Hides any highlight.
         * @return a stage completing with the command result
         */
        public CompletionStage<HideHighlightResult> hideHighlight() {
            return client.call("Overlay.hideHighlight", null, HideHighlightResult::fromMap);
        }
        /**
         * Highlights owner element of the frame with given id. Deprecated: Doesn&#x27;t work reliably and cannot be fixed due to process separation (the owner node might be in a different process). Determine the owner node in the client and use highlightNode.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<HighlightFrameResult> highlightFrame(HighlightFrameParams params) {
            return client.call("Overlay.highlightFrame", params, HighlightFrameResult::fromMap);
        }
        /**
         * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<HighlightNodeResult> highlightNode(HighlightNodeParams params) {
            return client.call("Overlay.highlightNode", params, HighlightNodeResult::fromMap);
        }
        /**
         * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<HighlightQuadResult> highlightQuad(HighlightQuadParams params) {
            return client.call("Overlay.highlightQuad", params, HighlightQuadResult::fromMap);
        }
        /**
         * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport. Issue: the method does not handle device pixel ratio (DPR) correctly. The coordinates currently have to be adjusted by the client if DPR is not 1 (see crbug.com/437807128).
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<HighlightRectResult> highlightRect(HighlightRectParams params) {
            return client.call("Overlay.highlightRect", params, HighlightRectResult::fromMap);
        }
        /**
         * Highlights the source order of the children of the DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<HighlightSourceOrderResult> highlightSourceOrder(HighlightSourceOrderParams params) {
            return client.call("Overlay.highlightSourceOrder", params, HighlightSourceOrderResult::fromMap);
        }
        /**
         * Enters the &#x27;inspect&#x27; mode. In this mode, elements that user is hovering over are highlighted. Backend then generates &#x27;inspectNodeRequested&#x27; event upon element selection.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetInspectModeResult> setInspectMode(SetInspectModeParams params) {
            return client.call("Overlay.setInspectMode", params, SetInspectModeResult::fromMap);
        }
        /**
         * Highlights owner element of all frames detected to be ads.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowAdHighlightsResult> setShowAdHighlights(SetShowAdHighlightsParams params) {
            return client.call("Overlay.setShowAdHighlights", params, SetShowAdHighlightsResult::fromMap);
        }
        /**
         * Invokes Overlay.setPausedInDebuggerMessage.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetPausedInDebuggerMessageResult> setPausedInDebuggerMessage(SetPausedInDebuggerMessageParams params) {
            return client.call("Overlay.setPausedInDebuggerMessage", params, SetPausedInDebuggerMessageResult::fromMap);
        }
        /**
         * Requests that backend shows debug borders on layers
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowDebugBordersResult> setShowDebugBorders(SetShowDebugBordersParams params) {
            return client.call("Overlay.setShowDebugBorders", params, SetShowDebugBordersResult::fromMap);
        }
        /**
         * Requests that backend shows the FPS counter
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowFPSCounterResult> setShowFPSCounter(SetShowFPSCounterParams params) {
            return client.call("Overlay.setShowFPSCounter", params, SetShowFPSCounterResult::fromMap);
        }
        /**
         * Highlight multiple elements with the CSS Grid overlay.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowGridOverlaysResult> setShowGridOverlays(SetShowGridOverlaysParams params) {
            return client.call("Overlay.setShowGridOverlays", params, SetShowGridOverlaysResult::fromMap);
        }
        /**
         * Invokes Overlay.setShowFlexOverlays.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowFlexOverlaysResult> setShowFlexOverlays(SetShowFlexOverlaysParams params) {
            return client.call("Overlay.setShowFlexOverlays", params, SetShowFlexOverlaysResult::fromMap);
        }
        /**
         * Invokes Overlay.setShowScrollSnapOverlays.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowScrollSnapOverlaysResult> setShowScrollSnapOverlays(SetShowScrollSnapOverlaysParams params) {
            return client.call("Overlay.setShowScrollSnapOverlays", params, SetShowScrollSnapOverlaysResult::fromMap);
        }
        /**
         * Invokes Overlay.setShowContainerQueryOverlays.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowContainerQueryOverlaysResult> setShowContainerQueryOverlays(SetShowContainerQueryOverlaysParams params) {
            return client.call("Overlay.setShowContainerQueryOverlays", params, SetShowContainerQueryOverlaysResult::fromMap);
        }
        /**
         * Invokes Overlay.setShowInspectedElementAnchor.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowInspectedElementAnchorResult> setShowInspectedElementAnchor(SetShowInspectedElementAnchorParams params) {
            return client.call("Overlay.setShowInspectedElementAnchor", params, SetShowInspectedElementAnchorResult::fromMap);
        }
        /**
         * Requests that backend shows paint rectangles
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowPaintRectsResult> setShowPaintRects(SetShowPaintRectsParams params) {
            return client.call("Overlay.setShowPaintRects", params, SetShowPaintRectsResult::fromMap);
        }
        /**
         * Requests that backend shows layout shift regions
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowLayoutShiftRegionsResult> setShowLayoutShiftRegions(SetShowLayoutShiftRegionsParams params) {
            return client.call("Overlay.setShowLayoutShiftRegions", params, SetShowLayoutShiftRegionsResult::fromMap);
        }
        /**
         * Requests that backend shows scroll bottleneck rects
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowScrollBottleneckRectsResult> setShowScrollBottleneckRects(SetShowScrollBottleneckRectsParams params) {
            return client.call("Overlay.setShowScrollBottleneckRects", params, SetShowScrollBottleneckRectsResult::fromMap);
        }
        /**
         * Deprecated, no longer has any effect.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetShowHitTestBordersResult> setShowHitTestBorders(SetShowHitTestBordersParams params) {
            return client.call("Overlay.setShowHitTestBorders", params, SetShowHitTestBordersResult::fromMap);
        }
        /**
         * Deprecated, no longer has any effect.
         * @param params command parameters
         * @return a stage completing with the command result
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<SetShowWebVitalsResult> setShowWebVitals(SetShowWebVitalsParams params) {
            return client.call("Overlay.setShowWebVitals", params, SetShowWebVitalsResult::fromMap);
        }
        /**
         * Paints viewport size upon main frame resize.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowViewportSizeOnResizeResult> setShowViewportSizeOnResize(SetShowViewportSizeOnResizeParams params) {
            return client.call("Overlay.setShowViewportSizeOnResize", params, SetShowViewportSizeOnResizeResult::fromMap);
        }
        /**
         * Add a dual screen device hinge
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowHingeResult> setShowHinge(SetShowHingeParams params) {
            return client.call("Overlay.setShowHinge", params, SetShowHingeResult::fromMap);
        }
        /**
         * Show elements in isolation mode with overlays.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowIsolatedElementsResult> setShowIsolatedElements(SetShowIsolatedElementsParams params) {
            return client.call("Overlay.setShowIsolatedElements", params, SetShowIsolatedElementsResult::fromMap);
        }
        /**
         * Show Window Controls Overlay for PWA
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<SetShowWindowControlsOverlayResult> setShowWindowControlsOverlay(SetShowWindowControlsOverlayParams params) {
            return client.call("Overlay.setShowWindowControlsOverlay", params, SetShowWindowControlsOverlayResult::fromMap);
        }
        /**
         * Fired when the node should be inspected. This happens after call to {@code setInspectMode} or when user manually inspects an element.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInspectNodeRequested(Consumer<InspectNodeRequestedEvent> handler) {
            return client.on("Overlay.inspectNodeRequested", InspectNodeRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when the node should be highlighted. This happens after call to {@code setInspectMode}.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onNodeHighlightRequested(Consumer<NodeHighlightRequestedEvent> handler) {
            return client.on("Overlay.nodeHighlightRequested", NodeHighlightRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when user asks to capture screenshot of some area on the page.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onScreenshotRequested(Consumer<ScreenshotRequestedEvent> handler) {
            return client.on("Overlay.screenshotRequested", ScreenshotRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when user asks to show the Inspect panel.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInspectPanelShowRequested(Consumer<InspectPanelShowRequestedEvent> handler) {
            return client.on("Overlay.inspectPanelShowRequested", InspectPanelShowRequestedEvent::fromMap, handler);
        }
        /**
         * Fired when user asks to restore the Inspected Element floating window.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInspectedElementWindowRestored(Consumer<InspectedElementWindowRestoredEvent> handler) {
            return client.on("Overlay.inspectedElementWindowRestored", InspectedElementWindowRestoredEvent::fromMap, handler);
        }
        /**
         * Fired when user cancels the inspect mode.
         * @param handler event callback
         * @return a removable subscription
         */
        public CdpSubscription onInspectModeCanceled(Consumer<InspectModeCanceledEvent> handler) {
            return client.on("Overlay.inspectModeCanceled", InspectModeCanceledEvent::fromMap, handler);
        }
    }
}
