// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
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
 * This domain provides various functionality related to drawing atop the inspected page.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Overlay.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class Overlay {
    private Overlay() {}
    /**
     * Configuration data for drawing the source order of an elements children.
     */
    public static final class SourceOrderConfig extends CdpObject {
        public SourceOrderConfig() {}
        private SourceOrderConfig(Map<String, Object> values) { super(values); }
        public static SourceOrderConfig fromMap(Map<String, Object> values) {
            return new SourceOrderConfig(values);
        }
        /**
         * the color to outline the given element in.
         * @return the protocol field value
         */
        public DOM.RGBA parentOutlineColor() {
            return java.util.Objects.requireNonNull(DOM.RGBA.fromMap(CdpObject.requireObjectMap(require("parentOutlineColor"))));
        }
        /**
         * the color to outline the child elements in.
         * @return the protocol field value
         */
        public DOM.RGBA childOutlineColor() {
            return java.util.Objects.requireNonNull(DOM.RGBA.fromMap(CdpObject.requireObjectMap(require("childOutlineColor"))));
        }
        /**
         * the color to outline the given element in.
         * @param parentOutlineColor field value
         * @return this model
         */
        public SourceOrderConfig parentOutlineColor(DOM.RGBA parentOutlineColor) {
            set("parentOutlineColor", parentOutlineColor);
            return this;
        }
        /**
         * the color to outline the child elements in.
         * @param childOutlineColor field value
         * @return this model
         */
        public SourceOrderConfig childOutlineColor(DOM.RGBA childOutlineColor) {
            set("childOutlineColor", childOutlineColor);
            return this;
        }
    }
    /**
     * Configuration data for the highlighting of Grid elements.
     */
    public static final class GridHighlightConfig extends CdpObject {
        public GridHighlightConfig() {}
        private GridHighlightConfig(Map<String, Object> values) { super(values); }
        public static GridHighlightConfig fromMap(Map<String, Object> values) {
            return new GridHighlightConfig(values);
        }
        /**
         * Whether the extension lines from grid cells to the rulers should be shown (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showGridExtensionLines() {
            return raw("showGridExtensionLines").map(value -> (Boolean) value);
        }
        /**
         * Show Positive line number labels (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showPositiveLineNumbers() {
            return raw("showPositiveLineNumbers").map(value -> (Boolean) value);
        }
        /**
         * Show Negative line number labels (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showNegativeLineNumbers() {
            return raw("showNegativeLineNumbers").map(value -> (Boolean) value);
        }
        /**
         * Show area name labels (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showAreaNames() {
            return raw("showAreaNames").map(value -> (Boolean) value);
        }
        /**
         * Show line name labels (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showLineNames() {
            return raw("showLineNames").map(value -> (Boolean) value);
        }
        /**
         * Show track size labels (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showTrackSizes() {
            return raw("showTrackSizes").map(value -> (Boolean) value);
        }
        /**
         * The grid container border highlight color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> gridBorderColor() {
            return raw("gridBorderColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The cell border color (default: transparent). Deprecated, please use rowLineColor and columnLineColor instead.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<DOM.RGBA> cellBorderColor() {
            return raw("cellBorderColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The row line color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> rowLineColor() {
            return raw("rowLineColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The column line color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> columnLineColor() {
            return raw("columnLineColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * Whether the grid border is dashed (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> gridBorderDash() {
            return raw("gridBorderDash").map(value -> (Boolean) value);
        }
        /**
         * Whether the cell border is dashed (default: false). Deprecated, please us rowLineDash and columnLineDash instead.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Boolean> cellBorderDash() {
            return raw("cellBorderDash").map(value -> (Boolean) value);
        }
        /**
         * Whether row lines are dashed (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> rowLineDash() {
            return raw("rowLineDash").map(value -> (Boolean) value);
        }
        /**
         * Whether column lines are dashed (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> columnLineDash() {
            return raw("columnLineDash").map(value -> (Boolean) value);
        }
        /**
         * The row gap highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> rowGapColor() {
            return raw("rowGapColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The row gap hatching fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> rowHatchColor() {
            return raw("rowHatchColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The column gap highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> columnGapColor() {
            return raw("columnGapColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The column gap hatching fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> columnHatchColor() {
            return raw("columnHatchColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The named grid areas border color (Default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> areaBorderColor() {
            return raw("areaBorderColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The grid container background color (Default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> gridBackgroundColor() {
            return raw("gridBackgroundColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * Whether the extension lines from grid cells to the rulers should be shown (default: false).
         * @param showGridExtensionLines field value
         * @return this model
         */
        public GridHighlightConfig showGridExtensionLines(boolean showGridExtensionLines) {
            set("showGridExtensionLines", showGridExtensionLines);
            return this;
        }
        /**
         * Show Positive line number labels (default: false).
         * @param showPositiveLineNumbers field value
         * @return this model
         */
        public GridHighlightConfig showPositiveLineNumbers(boolean showPositiveLineNumbers) {
            set("showPositiveLineNumbers", showPositiveLineNumbers);
            return this;
        }
        /**
         * Show Negative line number labels (default: false).
         * @param showNegativeLineNumbers field value
         * @return this model
         */
        public GridHighlightConfig showNegativeLineNumbers(boolean showNegativeLineNumbers) {
            set("showNegativeLineNumbers", showNegativeLineNumbers);
            return this;
        }
        /**
         * Show area name labels (default: false).
         * @param showAreaNames field value
         * @return this model
         */
        public GridHighlightConfig showAreaNames(boolean showAreaNames) {
            set("showAreaNames", showAreaNames);
            return this;
        }
        /**
         * Show line name labels (default: false).
         * @param showLineNames field value
         * @return this model
         */
        public GridHighlightConfig showLineNames(boolean showLineNames) {
            set("showLineNames", showLineNames);
            return this;
        }
        /**
         * Show track size labels (default: false).
         * @param showTrackSizes field value
         * @return this model
         */
        public GridHighlightConfig showTrackSizes(boolean showTrackSizes) {
            set("showTrackSizes", showTrackSizes);
            return this;
        }
        /**
         * The grid container border highlight color (default: transparent).
         * @param gridBorderColor field value
         * @return this model
         */
        public GridHighlightConfig gridBorderColor(DOM.RGBA gridBorderColor) {
            set("gridBorderColor", gridBorderColor);
            return this;
        }
        /**
         * The cell border color (default: transparent). Deprecated, please use rowLineColor and columnLineColor instead.
         * @param cellBorderColor field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public GridHighlightConfig cellBorderColor(DOM.RGBA cellBorderColor) {
            set("cellBorderColor", cellBorderColor);
            return this;
        }
        /**
         * The row line color (default: transparent).
         * @param rowLineColor field value
         * @return this model
         */
        public GridHighlightConfig rowLineColor(DOM.RGBA rowLineColor) {
            set("rowLineColor", rowLineColor);
            return this;
        }
        /**
         * The column line color (default: transparent).
         * @param columnLineColor field value
         * @return this model
         */
        public GridHighlightConfig columnLineColor(DOM.RGBA columnLineColor) {
            set("columnLineColor", columnLineColor);
            return this;
        }
        /**
         * Whether the grid border is dashed (default: false).
         * @param gridBorderDash field value
         * @return this model
         */
        public GridHighlightConfig gridBorderDash(boolean gridBorderDash) {
            set("gridBorderDash", gridBorderDash);
            return this;
        }
        /**
         * Whether the cell border is dashed (default: false). Deprecated, please us rowLineDash and columnLineDash instead.
         * @param cellBorderDash field value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public GridHighlightConfig cellBorderDash(boolean cellBorderDash) {
            set("cellBorderDash", cellBorderDash);
            return this;
        }
        /**
         * Whether row lines are dashed (default: false).
         * @param rowLineDash field value
         * @return this model
         */
        public GridHighlightConfig rowLineDash(boolean rowLineDash) {
            set("rowLineDash", rowLineDash);
            return this;
        }
        /**
         * Whether column lines are dashed (default: false).
         * @param columnLineDash field value
         * @return this model
         */
        public GridHighlightConfig columnLineDash(boolean columnLineDash) {
            set("columnLineDash", columnLineDash);
            return this;
        }
        /**
         * The row gap highlight fill color (default: transparent).
         * @param rowGapColor field value
         * @return this model
         */
        public GridHighlightConfig rowGapColor(DOM.RGBA rowGapColor) {
            set("rowGapColor", rowGapColor);
            return this;
        }
        /**
         * The row gap hatching fill color (default: transparent).
         * @param rowHatchColor field value
         * @return this model
         */
        public GridHighlightConfig rowHatchColor(DOM.RGBA rowHatchColor) {
            set("rowHatchColor", rowHatchColor);
            return this;
        }
        /**
         * The column gap highlight fill color (default: transparent).
         * @param columnGapColor field value
         * @return this model
         */
        public GridHighlightConfig columnGapColor(DOM.RGBA columnGapColor) {
            set("columnGapColor", columnGapColor);
            return this;
        }
        /**
         * The column gap hatching fill color (default: transparent).
         * @param columnHatchColor field value
         * @return this model
         */
        public GridHighlightConfig columnHatchColor(DOM.RGBA columnHatchColor) {
            set("columnHatchColor", columnHatchColor);
            return this;
        }
        /**
         * The named grid areas border color (Default: transparent).
         * @param areaBorderColor field value
         * @return this model
         */
        public GridHighlightConfig areaBorderColor(DOM.RGBA areaBorderColor) {
            set("areaBorderColor", areaBorderColor);
            return this;
        }
        /**
         * The grid container background color (Default: transparent).
         * @param gridBackgroundColor field value
         * @return this model
         */
        public GridHighlightConfig gridBackgroundColor(DOM.RGBA gridBackgroundColor) {
            set("gridBackgroundColor", gridBackgroundColor);
            return this;
        }
    }
    /**
     * Configuration data for the highlighting of Flex container elements.
     */
    public static final class FlexContainerHighlightConfig extends CdpObject {
        public FlexContainerHighlightConfig() {}
        private FlexContainerHighlightConfig(Map<String, Object> values) { super(values); }
        public static FlexContainerHighlightConfig fromMap(Map<String, Object> values) {
            return new FlexContainerHighlightConfig(values);
        }
        /**
         * The style of the container border
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> containerBorder() {
            return raw("containerBorder").flatMap(CdpObject::objectMap).map(Overlay.LineStyle::fromMap);
        }
        /**
         * The style of the separator between lines
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> lineSeparator() {
            return raw("lineSeparator").flatMap(CdpObject::objectMap).map(Overlay.LineStyle::fromMap);
        }
        /**
         * The style of the separator between items
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> itemSeparator() {
            return raw("itemSeparator").flatMap(CdpObject::objectMap).map(Overlay.LineStyle::fromMap);
        }
        /**
         * Style of content-distribution space on the main axis (justify-content).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.BoxStyle> mainDistributedSpace() {
            return raw("mainDistributedSpace").flatMap(CdpObject::objectMap).map(Overlay.BoxStyle::fromMap);
        }
        /**
         * Style of content-distribution space on the cross axis (align-content).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.BoxStyle> crossDistributedSpace() {
            return raw("crossDistributedSpace").flatMap(CdpObject::objectMap).map(Overlay.BoxStyle::fromMap);
        }
        /**
         * Style of empty space caused by row gaps (gap/row-gap).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.BoxStyle> rowGapSpace() {
            return raw("rowGapSpace").flatMap(CdpObject::objectMap).map(Overlay.BoxStyle::fromMap);
        }
        /**
         * Style of empty space caused by columns gaps (gap/column-gap).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.BoxStyle> columnGapSpace() {
            return raw("columnGapSpace").flatMap(CdpObject::objectMap).map(Overlay.BoxStyle::fromMap);
        }
        /**
         * Style of the self-alignment line (align-items).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> crossAlignment() {
            return raw("crossAlignment").flatMap(CdpObject::objectMap).map(Overlay.LineStyle::fromMap);
        }
        /**
         * The style of the container border
         * @param containerBorder field value
         * @return this model
         */
        public FlexContainerHighlightConfig containerBorder(Overlay.LineStyle containerBorder) {
            set("containerBorder", containerBorder);
            return this;
        }
        /**
         * The style of the separator between lines
         * @param lineSeparator field value
         * @return this model
         */
        public FlexContainerHighlightConfig lineSeparator(Overlay.LineStyle lineSeparator) {
            set("lineSeparator", lineSeparator);
            return this;
        }
        /**
         * The style of the separator between items
         * @param itemSeparator field value
         * @return this model
         */
        public FlexContainerHighlightConfig itemSeparator(Overlay.LineStyle itemSeparator) {
            set("itemSeparator", itemSeparator);
            return this;
        }
        /**
         * Style of content-distribution space on the main axis (justify-content).
         * @param mainDistributedSpace field value
         * @return this model
         */
        public FlexContainerHighlightConfig mainDistributedSpace(Overlay.BoxStyle mainDistributedSpace) {
            set("mainDistributedSpace", mainDistributedSpace);
            return this;
        }
        /**
         * Style of content-distribution space on the cross axis (align-content).
         * @param crossDistributedSpace field value
         * @return this model
         */
        public FlexContainerHighlightConfig crossDistributedSpace(Overlay.BoxStyle crossDistributedSpace) {
            set("crossDistributedSpace", crossDistributedSpace);
            return this;
        }
        /**
         * Style of empty space caused by row gaps (gap/row-gap).
         * @param rowGapSpace field value
         * @return this model
         */
        public FlexContainerHighlightConfig rowGapSpace(Overlay.BoxStyle rowGapSpace) {
            set("rowGapSpace", rowGapSpace);
            return this;
        }
        /**
         * Style of empty space caused by columns gaps (gap/column-gap).
         * @param columnGapSpace field value
         * @return this model
         */
        public FlexContainerHighlightConfig columnGapSpace(Overlay.BoxStyle columnGapSpace) {
            set("columnGapSpace", columnGapSpace);
            return this;
        }
        /**
         * Style of the self-alignment line (align-items).
         * @param crossAlignment field value
         * @return this model
         */
        public FlexContainerHighlightConfig crossAlignment(Overlay.LineStyle crossAlignment) {
            set("crossAlignment", crossAlignment);
            return this;
        }
    }
    /**
     * Configuration data for the highlighting of Flex item elements.
     */
    public static final class FlexItemHighlightConfig extends CdpObject {
        public FlexItemHighlightConfig() {}
        private FlexItemHighlightConfig(Map<String, Object> values) { super(values); }
        public static FlexItemHighlightConfig fromMap(Map<String, Object> values) {
            return new FlexItemHighlightConfig(values);
        }
        /**
         * Style of the box representing the item&#x27;s base size
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.BoxStyle> baseSizeBox() {
            return raw("baseSizeBox").flatMap(CdpObject::objectMap).map(Overlay.BoxStyle::fromMap);
        }
        /**
         * Style of the border around the box representing the item&#x27;s base size
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> baseSizeBorder() {
            return raw("baseSizeBorder").flatMap(CdpObject::objectMap).map(Overlay.LineStyle::fromMap);
        }
        /**
         * Style of the arrow representing if the item grew or shrank
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> flexibilityArrow() {
            return raw("flexibilityArrow").flatMap(CdpObject::objectMap).map(Overlay.LineStyle::fromMap);
        }
        /**
         * Style of the box representing the item&#x27;s base size
         * @param baseSizeBox field value
         * @return this model
         */
        public FlexItemHighlightConfig baseSizeBox(Overlay.BoxStyle baseSizeBox) {
            set("baseSizeBox", baseSizeBox);
            return this;
        }
        /**
         * Style of the border around the box representing the item&#x27;s base size
         * @param baseSizeBorder field value
         * @return this model
         */
        public FlexItemHighlightConfig baseSizeBorder(Overlay.LineStyle baseSizeBorder) {
            set("baseSizeBorder", baseSizeBorder);
            return this;
        }
        /**
         * Style of the arrow representing if the item grew or shrank
         * @param flexibilityArrow field value
         * @return this model
         */
        public FlexItemHighlightConfig flexibilityArrow(Overlay.LineStyle flexibilityArrow) {
            set("flexibilityArrow", flexibilityArrow);
            return this;
        }
    }
    /**
     * Style information for drawing a line.
     */
    public static final class LineStyle extends CdpObject {
        public LineStyle() {}
        private LineStyle(Map<String, Object> values) { super(values); }
        public static LineStyle fromMap(Map<String, Object> values) {
            return new LineStyle(values);
        }
        /**
         * The line pattern (default: solid)
         */
        public enum PatternValues implements CdpValue<String> {
            DASHED("dashed"),
            DOTTED("dotted");
            public final String value;
            PatternValues(String value) { this.value = value; }
            @Override public String value() { return value; }
            public static PatternValues of(String value) {
                for (PatternValues constant : values()) {
                    if (constant.value.equals(value)) return constant;
                }
                throw new IllegalArgumentException("Unknown PatternValues value: " + value);
            }
        }
        /**
         * The color of the line (default: transparent)
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> color() {
            return raw("color").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The line pattern (default: solid)
         * @return the protocol field value, empty when absent
         */
        public Optional<LineStyle.PatternValues> pattern() {
            return raw("pattern").map(value -> LineStyle.PatternValues.of((String) value));
        }
        /**
         * The color of the line (default: transparent)
         * @param color field value
         * @return this model
         */
        public LineStyle color(DOM.RGBA color) {
            set("color", color);
            return this;
        }
        /**
         * The line pattern (default: solid)
         * @param pattern field value
         * @return this model
         */
        public LineStyle pattern(LineStyle.PatternValues pattern) {
            set("pattern", pattern);
            return this;
        }
    }
    /**
     * Style information for drawing a box.
     */
    public static final class BoxStyle extends CdpObject {
        public BoxStyle() {}
        private BoxStyle(Map<String, Object> values) { super(values); }
        public static BoxStyle fromMap(Map<String, Object> values) {
            return new BoxStyle(values);
        }
        /**
         * The background color for the box (default: transparent)
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> fillColor() {
            return raw("fillColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The hatching color for the box (default: transparent)
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> hatchColor() {
            return raw("hatchColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The background color for the box (default: transparent)
         * @param fillColor field value
         * @return this model
         */
        public BoxStyle fillColor(DOM.RGBA fillColor) {
            set("fillColor", fillColor);
            return this;
        }
        /**
         * The hatching color for the box (default: transparent)
         * @param hatchColor field value
         * @return this model
         */
        public BoxStyle hatchColor(DOM.RGBA hatchColor) {
            set("hatchColor", hatchColor);
            return this;
        }
    }
    /**
     * Wire values for ContrastAlgorithm.
     */
    public enum ContrastAlgorithm implements CdpValue<String> {
        AA("aa"),
        AAA("aaa"),
        APCA("apca");
        public final String value;
        ContrastAlgorithm(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ContrastAlgorithm of(String value) {
            for (ContrastAlgorithm constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ContrastAlgorithm value: " + value);
        }
    }
    /**
     * Configuration data for the highlighting of page elements.
     */
    public static final class HighlightConfig extends CdpObject {
        public HighlightConfig() {}
        private HighlightConfig(Map<String, Object> values) { super(values); }
        public static HighlightConfig fromMap(Map<String, Object> values) {
            return new HighlightConfig(values);
        }
        /**
         * Whether the node info tooltip should be shown (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showInfo() {
            return raw("showInfo").map(value -> (Boolean) value);
        }
        /**
         * Whether the node styles in the tooltip (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showStyles() {
            return raw("showStyles").map(value -> (Boolean) value);
        }
        /**
         * Whether the rulers should be shown (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showRulers() {
            return raw("showRulers").map(value -> (Boolean) value);
        }
        /**
         * Whether the a11y info should be shown (default: true).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showAccessibilityInfo() {
            return raw("showAccessibilityInfo").map(value -> (Boolean) value);
        }
        /**
         * Whether the extension lines from node to the rulers should be shown (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showExtensionLines() {
            return raw("showExtensionLines").map(value -> (Boolean) value);
        }
        /**
         * The content box highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> contentColor() {
            return raw("contentColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The padding highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> paddingColor() {
            return raw("paddingColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The border highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> borderColor() {
            return raw("borderColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The margin highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> marginColor() {
            return raw("marginColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The event target element highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> eventTargetColor() {
            return raw("eventTargetColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The shape outside fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> shapeColor() {
            return raw("shapeColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The shape margin fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> shapeMarginColor() {
            return raw("shapeMarginColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The grid layout color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> cssGridColor() {
            return raw("cssGridColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The color format used to format color styles (default: hex).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.ColorFormat> colorFormat() {
            return raw("colorFormat").map(value -> Overlay.ColorFormat.of((String) value));
        }
        /**
         * The grid layout highlight configuration (default: all transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.GridHighlightConfig> gridHighlightConfig() {
            return raw("gridHighlightConfig").flatMap(CdpObject::objectMap).map(Overlay.GridHighlightConfig::fromMap);
        }
        /**
         * The flex container highlight configuration (default: all transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.FlexContainerHighlightConfig> flexContainerHighlightConfig() {
            return raw("flexContainerHighlightConfig").flatMap(CdpObject::objectMap).map(Overlay.FlexContainerHighlightConfig::fromMap);
        }
        /**
         * The flex item highlight configuration (default: all transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.FlexItemHighlightConfig> flexItemHighlightConfig() {
            return raw("flexItemHighlightConfig").flatMap(CdpObject::objectMap).map(Overlay.FlexItemHighlightConfig::fromMap);
        }
        /**
         * The contrast algorithm to use for the contrast ratio (default: aa).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.ContrastAlgorithm> contrastAlgorithm() {
            return raw("contrastAlgorithm").map(value -> Overlay.ContrastAlgorithm.of((String) value));
        }
        /**
         * The container query container highlight configuration (default: all transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.ContainerQueryContainerHighlightConfig> containerQueryContainerHighlightConfig() {
            return raw("containerQueryContainerHighlightConfig").flatMap(CdpObject::objectMap).map(Overlay.ContainerQueryContainerHighlightConfig::fromMap);
        }
        /**
         * Whether the node info tooltip should be shown (default: false).
         * @param showInfo field value
         * @return this model
         */
        public HighlightConfig showInfo(boolean showInfo) {
            set("showInfo", showInfo);
            return this;
        }
        /**
         * Whether the node styles in the tooltip (default: false).
         * @param showStyles field value
         * @return this model
         */
        public HighlightConfig showStyles(boolean showStyles) {
            set("showStyles", showStyles);
            return this;
        }
        /**
         * Whether the rulers should be shown (default: false).
         * @param showRulers field value
         * @return this model
         */
        public HighlightConfig showRulers(boolean showRulers) {
            set("showRulers", showRulers);
            return this;
        }
        /**
         * Whether the a11y info should be shown (default: true).
         * @param showAccessibilityInfo field value
         * @return this model
         */
        public HighlightConfig showAccessibilityInfo(boolean showAccessibilityInfo) {
            set("showAccessibilityInfo", showAccessibilityInfo);
            return this;
        }
        /**
         * Whether the extension lines from node to the rulers should be shown (default: false).
         * @param showExtensionLines field value
         * @return this model
         */
        public HighlightConfig showExtensionLines(boolean showExtensionLines) {
            set("showExtensionLines", showExtensionLines);
            return this;
        }
        /**
         * The content box highlight fill color (default: transparent).
         * @param contentColor field value
         * @return this model
         */
        public HighlightConfig contentColor(DOM.RGBA contentColor) {
            set("contentColor", contentColor);
            return this;
        }
        /**
         * The padding highlight fill color (default: transparent).
         * @param paddingColor field value
         * @return this model
         */
        public HighlightConfig paddingColor(DOM.RGBA paddingColor) {
            set("paddingColor", paddingColor);
            return this;
        }
        /**
         * The border highlight fill color (default: transparent).
         * @param borderColor field value
         * @return this model
         */
        public HighlightConfig borderColor(DOM.RGBA borderColor) {
            set("borderColor", borderColor);
            return this;
        }
        /**
         * The margin highlight fill color (default: transparent).
         * @param marginColor field value
         * @return this model
         */
        public HighlightConfig marginColor(DOM.RGBA marginColor) {
            set("marginColor", marginColor);
            return this;
        }
        /**
         * The event target element highlight fill color (default: transparent).
         * @param eventTargetColor field value
         * @return this model
         */
        public HighlightConfig eventTargetColor(DOM.RGBA eventTargetColor) {
            set("eventTargetColor", eventTargetColor);
            return this;
        }
        /**
         * The shape outside fill color (default: transparent).
         * @param shapeColor field value
         * @return this model
         */
        public HighlightConfig shapeColor(DOM.RGBA shapeColor) {
            set("shapeColor", shapeColor);
            return this;
        }
        /**
         * The shape margin fill color (default: transparent).
         * @param shapeMarginColor field value
         * @return this model
         */
        public HighlightConfig shapeMarginColor(DOM.RGBA shapeMarginColor) {
            set("shapeMarginColor", shapeMarginColor);
            return this;
        }
        /**
         * The grid layout color (default: transparent).
         * @param cssGridColor field value
         * @return this model
         */
        public HighlightConfig cssGridColor(DOM.RGBA cssGridColor) {
            set("cssGridColor", cssGridColor);
            return this;
        }
        /**
         * The color format used to format color styles (default: hex).
         * @param colorFormat field value
         * @return this model
         */
        public HighlightConfig colorFormat(Overlay.ColorFormat colorFormat) {
            set("colorFormat", colorFormat);
            return this;
        }
        /**
         * The grid layout highlight configuration (default: all transparent).
         * @param gridHighlightConfig field value
         * @return this model
         */
        public HighlightConfig gridHighlightConfig(Overlay.GridHighlightConfig gridHighlightConfig) {
            set("gridHighlightConfig", gridHighlightConfig);
            return this;
        }
        /**
         * The flex container highlight configuration (default: all transparent).
         * @param flexContainerHighlightConfig field value
         * @return this model
         */
        public HighlightConfig flexContainerHighlightConfig(Overlay.FlexContainerHighlightConfig flexContainerHighlightConfig) {
            set("flexContainerHighlightConfig", flexContainerHighlightConfig);
            return this;
        }
        /**
         * The flex item highlight configuration (default: all transparent).
         * @param flexItemHighlightConfig field value
         * @return this model
         */
        public HighlightConfig flexItemHighlightConfig(Overlay.FlexItemHighlightConfig flexItemHighlightConfig) {
            set("flexItemHighlightConfig", flexItemHighlightConfig);
            return this;
        }
        /**
         * The contrast algorithm to use for the contrast ratio (default: aa).
         * @param contrastAlgorithm field value
         * @return this model
         */
        public HighlightConfig contrastAlgorithm(Overlay.ContrastAlgorithm contrastAlgorithm) {
            set("contrastAlgorithm", contrastAlgorithm);
            return this;
        }
        /**
         * The container query container highlight configuration (default: all transparent).
         * @param containerQueryContainerHighlightConfig field value
         * @return this model
         */
        public HighlightConfig containerQueryContainerHighlightConfig(Overlay.ContainerQueryContainerHighlightConfig containerQueryContainerHighlightConfig) {
            set("containerQueryContainerHighlightConfig", containerQueryContainerHighlightConfig);
            return this;
        }
    }
    /**
     * Wire values for ColorFormat.
     */
    public enum ColorFormat implements CdpValue<String> {
        RGB("rgb"),
        HSL("hsl"),
        HWB("hwb"),
        HEX("hex");
        public final String value;
        ColorFormat(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static ColorFormat of(String value) {
            for (ColorFormat constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown ColorFormat value: " + value);
        }
    }
    /**
     * Configurations for Persistent Grid Highlight
     */
    public static final class GridNodeHighlightConfig extends CdpObject {
        public GridNodeHighlightConfig() {}
        private GridNodeHighlightConfig(Map<String, Object> values) { super(values); }
        public static GridNodeHighlightConfig fromMap(Map<String, Object> values) {
            return new GridNodeHighlightConfig(values);
        }
        /**
         * A descriptor for the highlight appearance.
         * @return the protocol field value
         */
        public Overlay.GridHighlightConfig gridHighlightConfig() {
            return java.util.Objects.requireNonNull(Overlay.GridHighlightConfig.fromMap(CdpObject.requireObjectMap(require("gridHighlightConfig"))));
        }
        /**
         * Identifier of the node to highlight.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * A descriptor for the highlight appearance.
         * @param gridHighlightConfig field value
         * @return this model
         */
        public GridNodeHighlightConfig gridHighlightConfig(Overlay.GridHighlightConfig gridHighlightConfig) {
            set("gridHighlightConfig", gridHighlightConfig);
            return this;
        }
        /**
         * Identifier of the node to highlight.
         * @param nodeId field value
         * @return this model
         */
        public GridNodeHighlightConfig nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     */
    public static final class FlexNodeHighlightConfig extends CdpObject {
        public FlexNodeHighlightConfig() {}
        private FlexNodeHighlightConfig(Map<String, Object> values) { super(values); }
        public static FlexNodeHighlightConfig fromMap(Map<String, Object> values) {
            return new FlexNodeHighlightConfig(values);
        }
        /**
         * A descriptor for the highlight appearance of flex containers.
         * @return the protocol field value
         */
        public Overlay.FlexContainerHighlightConfig flexContainerHighlightConfig() {
            return java.util.Objects.requireNonNull(Overlay.FlexContainerHighlightConfig.fromMap(CdpObject.requireObjectMap(require("flexContainerHighlightConfig"))));
        }
        /**
         * Identifier of the node to highlight.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * A descriptor for the highlight appearance of flex containers.
         * @param flexContainerHighlightConfig field value
         * @return this model
         */
        public FlexNodeHighlightConfig flexContainerHighlightConfig(Overlay.FlexContainerHighlightConfig flexContainerHighlightConfig) {
            set("flexContainerHighlightConfig", flexContainerHighlightConfig);
            return this;
        }
        /**
         * Identifier of the node to highlight.
         * @param nodeId field value
         * @return this model
         */
        public FlexNodeHighlightConfig nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     */
    public static final class ScrollSnapContainerHighlightConfig extends CdpObject {
        public ScrollSnapContainerHighlightConfig() {}
        private ScrollSnapContainerHighlightConfig(Map<String, Object> values) { super(values); }
        public static ScrollSnapContainerHighlightConfig fromMap(Map<String, Object> values) {
            return new ScrollSnapContainerHighlightConfig(values);
        }
        /**
         * The style of the snapport border (default: transparent)
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> snapportBorder() {
            return raw("snapportBorder").flatMap(CdpObject::objectMap).map(Overlay.LineStyle::fromMap);
        }
        /**
         * The style of the snap area border (default: transparent)
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> snapAreaBorder() {
            return raw("snapAreaBorder").flatMap(CdpObject::objectMap).map(Overlay.LineStyle::fromMap);
        }
        /**
         * The margin highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> scrollMarginColor() {
            return raw("scrollMarginColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The padding highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> scrollPaddingColor() {
            return raw("scrollPaddingColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The style of the snapport border (default: transparent)
         * @param snapportBorder field value
         * @return this model
         */
        public ScrollSnapContainerHighlightConfig snapportBorder(Overlay.LineStyle snapportBorder) {
            set("snapportBorder", snapportBorder);
            return this;
        }
        /**
         * The style of the snap area border (default: transparent)
         * @param snapAreaBorder field value
         * @return this model
         */
        public ScrollSnapContainerHighlightConfig snapAreaBorder(Overlay.LineStyle snapAreaBorder) {
            set("snapAreaBorder", snapAreaBorder);
            return this;
        }
        /**
         * The margin highlight fill color (default: transparent).
         * @param scrollMarginColor field value
         * @return this model
         */
        public ScrollSnapContainerHighlightConfig scrollMarginColor(DOM.RGBA scrollMarginColor) {
            set("scrollMarginColor", scrollMarginColor);
            return this;
        }
        /**
         * The padding highlight fill color (default: transparent).
         * @param scrollPaddingColor field value
         * @return this model
         */
        public ScrollSnapContainerHighlightConfig scrollPaddingColor(DOM.RGBA scrollPaddingColor) {
            set("scrollPaddingColor", scrollPaddingColor);
            return this;
        }
    }
    /**
     */
    public static final class ScrollSnapHighlightConfig extends CdpObject {
        public ScrollSnapHighlightConfig() {}
        private ScrollSnapHighlightConfig(Map<String, Object> values) { super(values); }
        public static ScrollSnapHighlightConfig fromMap(Map<String, Object> values) {
            return new ScrollSnapHighlightConfig(values);
        }
        /**
         * A descriptor for the highlight appearance of scroll snap containers.
         * @return the protocol field value
         */
        public Overlay.ScrollSnapContainerHighlightConfig scrollSnapContainerHighlightConfig() {
            return java.util.Objects.requireNonNull(Overlay.ScrollSnapContainerHighlightConfig.fromMap(CdpObject.requireObjectMap(require("scrollSnapContainerHighlightConfig"))));
        }
        /**
         * Identifier of the node to highlight.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * A descriptor for the highlight appearance of scroll snap containers.
         * @param scrollSnapContainerHighlightConfig field value
         * @return this model
         */
        public ScrollSnapHighlightConfig scrollSnapContainerHighlightConfig(Overlay.ScrollSnapContainerHighlightConfig scrollSnapContainerHighlightConfig) {
            set("scrollSnapContainerHighlightConfig", scrollSnapContainerHighlightConfig);
            return this;
        }
        /**
         * Identifier of the node to highlight.
         * @param nodeId field value
         * @return this model
         */
        public ScrollSnapHighlightConfig nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Configuration for dual screen hinge
     */
    public static final class HingeConfig extends CdpObject {
        public HingeConfig() {}
        private HingeConfig(Map<String, Object> values) { super(values); }
        public static HingeConfig fromMap(Map<String, Object> values) {
            return new HingeConfig(values);
        }
        /**
         * A rectangle represent hinge
         * @return the protocol field value
         */
        public DOM.Rect rect() {
            return java.util.Objects.requireNonNull(DOM.Rect.fromMap(CdpObject.requireObjectMap(require("rect"))));
        }
        /**
         * The content box highlight fill color (default: a dark color).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> contentColor() {
            return raw("contentColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The content box highlight outline color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> outlineColor() {
            return raw("outlineColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * A rectangle represent hinge
         * @param rect field value
         * @return this model
         */
        public HingeConfig rect(DOM.Rect rect) {
            set("rect", rect);
            return this;
        }
        /**
         * The content box highlight fill color (default: a dark color).
         * @param contentColor field value
         * @return this model
         */
        public HingeConfig contentColor(DOM.RGBA contentColor) {
            set("contentColor", contentColor);
            return this;
        }
        /**
         * The content box highlight outline color (default: transparent).
         * @param outlineColor field value
         * @return this model
         */
        public HingeConfig outlineColor(DOM.RGBA outlineColor) {
            set("outlineColor", outlineColor);
            return this;
        }
    }
    /**
     * Configuration for Window Controls Overlay
     */
    public static final class WindowControlsOverlayConfig extends CdpObject {
        public WindowControlsOverlayConfig() {}
        private WindowControlsOverlayConfig(Map<String, Object> values) { super(values); }
        public static WindowControlsOverlayConfig fromMap(Map<String, Object> values) {
            return new WindowControlsOverlayConfig(values);
        }
        /**
         * Whether the title bar CSS should be shown when emulating the Window Controls Overlay.
         * @return the protocol field value
         */
        public boolean showCSS() {
            return (Boolean) require("showCSS");
        }
        /**
         * Selected platforms to show the overlay.
         * @return the protocol field value
         */
        public String selectedPlatform() {
            return (String) require("selectedPlatform");
        }
        /**
         * The theme color defined in app manifest.
         * @return the protocol field value
         */
        public String themeColor() {
            return (String) require("themeColor");
        }
        /**
         * Whether the title bar CSS should be shown when emulating the Window Controls Overlay.
         * @param showCSS field value
         * @return this model
         */
        public WindowControlsOverlayConfig showCSS(boolean showCSS) {
            set("showCSS", showCSS);
            return this;
        }
        /**
         * Selected platforms to show the overlay.
         * @param selectedPlatform field value
         * @return this model
         */
        public WindowControlsOverlayConfig selectedPlatform(String selectedPlatform) {
            set("selectedPlatform", selectedPlatform);
            return this;
        }
        /**
         * The theme color defined in app manifest.
         * @param themeColor field value
         * @return this model
         */
        public WindowControlsOverlayConfig themeColor(String themeColor) {
            set("themeColor", themeColor);
            return this;
        }
    }
    /**
     */
    public static final class ContainerQueryHighlightConfig extends CdpObject {
        public ContainerQueryHighlightConfig() {}
        private ContainerQueryHighlightConfig(Map<String, Object> values) { super(values); }
        public static ContainerQueryHighlightConfig fromMap(Map<String, Object> values) {
            return new ContainerQueryHighlightConfig(values);
        }
        /**
         * A descriptor for the highlight appearance of container query containers.
         * @return the protocol field value
         */
        public Overlay.ContainerQueryContainerHighlightConfig containerQueryContainerHighlightConfig() {
            return java.util.Objects.requireNonNull(Overlay.ContainerQueryContainerHighlightConfig.fromMap(CdpObject.requireObjectMap(require("containerQueryContainerHighlightConfig"))));
        }
        /**
         * Identifier of the container node to highlight.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * A descriptor for the highlight appearance of container query containers.
         * @param containerQueryContainerHighlightConfig field value
         * @return this model
         */
        public ContainerQueryHighlightConfig containerQueryContainerHighlightConfig(Overlay.ContainerQueryContainerHighlightConfig containerQueryContainerHighlightConfig) {
            set("containerQueryContainerHighlightConfig", containerQueryContainerHighlightConfig);
            return this;
        }
        /**
         * Identifier of the container node to highlight.
         * @param nodeId field value
         * @return this model
         */
        public ContainerQueryHighlightConfig nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     */
    public static final class ContainerQueryContainerHighlightConfig extends CdpObject {
        public ContainerQueryContainerHighlightConfig() {}
        private ContainerQueryContainerHighlightConfig(Map<String, Object> values) { super(values); }
        public static ContainerQueryContainerHighlightConfig fromMap(Map<String, Object> values) {
            return new ContainerQueryContainerHighlightConfig(values);
        }
        /**
         * The style of the container border.
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> containerBorder() {
            return raw("containerBorder").flatMap(CdpObject::objectMap).map(Overlay.LineStyle::fromMap);
        }
        /**
         * The style of the descendants&#x27; borders.
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> descendantBorder() {
            return raw("descendantBorder").flatMap(CdpObject::objectMap).map(Overlay.LineStyle::fromMap);
        }
        /**
         * The style of the container border.
         * @param containerBorder field value
         * @return this model
         */
        public ContainerQueryContainerHighlightConfig containerBorder(Overlay.LineStyle containerBorder) {
            set("containerBorder", containerBorder);
            return this;
        }
        /**
         * The style of the descendants&#x27; borders.
         * @param descendantBorder field value
         * @return this model
         */
        public ContainerQueryContainerHighlightConfig descendantBorder(Overlay.LineStyle descendantBorder) {
            set("descendantBorder", descendantBorder);
            return this;
        }
    }
    /**
     */
    public static final class IsolatedElementHighlightConfig extends CdpObject {
        public IsolatedElementHighlightConfig() {}
        private IsolatedElementHighlightConfig(Map<String, Object> values) { super(values); }
        public static IsolatedElementHighlightConfig fromMap(Map<String, Object> values) {
            return new IsolatedElementHighlightConfig(values);
        }
        /**
         * A descriptor for the highlight appearance of an element in isolation mode.
         * @return the protocol field value
         */
        public Overlay.IsolationModeHighlightConfig isolationModeHighlightConfig() {
            return java.util.Objects.requireNonNull(Overlay.IsolationModeHighlightConfig.fromMap(CdpObject.requireObjectMap(require("isolationModeHighlightConfig"))));
        }
        /**
         * Identifier of the isolated element to highlight.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * A descriptor for the highlight appearance of an element in isolation mode.
         * @param isolationModeHighlightConfig field value
         * @return this model
         */
        public IsolatedElementHighlightConfig isolationModeHighlightConfig(Overlay.IsolationModeHighlightConfig isolationModeHighlightConfig) {
            set("isolationModeHighlightConfig", isolationModeHighlightConfig);
            return this;
        }
        /**
         * Identifier of the isolated element to highlight.
         * @param nodeId field value
         * @return this model
         */
        public IsolatedElementHighlightConfig nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     */
    public static final class IsolationModeHighlightConfig extends CdpObject {
        public IsolationModeHighlightConfig() {}
        private IsolationModeHighlightConfig(Map<String, Object> values) { super(values); }
        public static IsolationModeHighlightConfig fromMap(Map<String, Object> values) {
            return new IsolationModeHighlightConfig(values);
        }
        /**
         * The fill color of the resizers (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> resizerColor() {
            return raw("resizerColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The fill color for resizer handles (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> resizerHandleColor() {
            return raw("resizerHandleColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The fill color for the mask covering non-isolated elements (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> maskColor() {
            return raw("maskColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The fill color of the resizers (default: transparent).
         * @param resizerColor field value
         * @return this model
         */
        public IsolationModeHighlightConfig resizerColor(DOM.RGBA resizerColor) {
            set("resizerColor", resizerColor);
            return this;
        }
        /**
         * The fill color for resizer handles (default: transparent).
         * @param resizerHandleColor field value
         * @return this model
         */
        public IsolationModeHighlightConfig resizerHandleColor(DOM.RGBA resizerHandleColor) {
            set("resizerHandleColor", resizerHandleColor);
            return this;
        }
        /**
         * The fill color for the mask covering non-isolated elements (default: transparent).
         * @param maskColor field value
         * @return this model
         */
        public IsolationModeHighlightConfig maskColor(DOM.RGBA maskColor) {
            set("maskColor", maskColor);
            return this;
        }
    }
    /**
     * Wire values for InspectMode.
     */
    public enum InspectMode implements CdpValue<String> {
        SEARCHFORNODE("searchForNode"),
        SEARCHFORUASHADOWDOM("searchForUAShadowDOM"),
        CAPTUREAREASCREENSHOT("captureAreaScreenshot"),
        NONE("none");
        public final String value;
        InspectMode(String value) { this.value = value; }
        @Override public String value() { return value; }
        public static InspectMode of(String value) {
            for (InspectMode constant : values()) {
                if (constant.value.equals(value)) return constant;
            }
            throw new IllegalArgumentException("Unknown InspectMode value: " + value);
        }
    }
    /**
     */
    public static final class InspectedElementAnchorConfig extends CdpObject {
        public InspectedElementAnchorConfig() {}
        private InspectedElementAnchorConfig(Map<String, Object> values) { super(values); }
        public static InspectedElementAnchorConfig fromMap(Map<String, Object> values) {
            return new InspectedElementAnchorConfig(values);
        }
        /**
         * Identifier of the node to highlight.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return raw("nodeId").map(value -> new DOM.NodeId(((Number) value).longValue()));
        }
        /**
         * Identifier of the backend node to highlight.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return raw("backendNodeId").map(value -> new DOM.BackendNodeId(((Number) value).longValue()));
        }
        /**
         * Identifier of the node to highlight.
         * @param nodeId field value
         * @return this model
         */
        public InspectedElementAnchorConfig nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node to highlight.
         * @param backendNodeId field value
         * @return this model
         */
        public InspectedElementAnchorConfig backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
    }
    /**
     * For testing.
     */
    public static final class GetHighlightObjectForTestRequest extends CdpObject {
        public GetHighlightObjectForTestRequest() {}
        /**
         * For testing.
         * @param nodeId protocol value
         */
        public GetHighlightObjectForTestRequest(DOM.NodeId nodeId) {
            nodeId(nodeId);
        }
        public static GetHighlightObjectForTestRequest fromMap(Map<String, Object> values) {
            final GetHighlightObjectForTestRequest instance_ = new GetHighlightObjectForTestRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to get highlight object for.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Whether to include distance info.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeDistance() {
            return raw("includeDistance").map(value -> (Boolean) value);
        }
        /**
         * Whether to include style info.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeStyle() {
            return raw("includeStyle").map(value -> (Boolean) value);
        }
        /**
         * The color format to get config with (default: hex).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.ColorFormat> colorFormat() {
            return raw("colorFormat").map(value -> Overlay.ColorFormat.of((String) value));
        }
        /**
         * Whether to show accessibility info (default: true).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showAccessibilityInfo() {
            return raw("showAccessibilityInfo").map(value -> (Boolean) value);
        }
        /**
         * Id of the node to get highlight object for.
         * @param nodeId field value
         * @return this model
         */
        public GetHighlightObjectForTestRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Whether to include distance info.
         * @param includeDistance field value
         * @return this model
         */
        public GetHighlightObjectForTestRequest includeDistance(boolean includeDistance) {
            set("includeDistance", includeDistance);
            return this;
        }
        /**
         * Whether to include style info.
         * @param includeStyle field value
         * @return this model
         */
        public GetHighlightObjectForTestRequest includeStyle(boolean includeStyle) {
            set("includeStyle", includeStyle);
            return this;
        }
        /**
         * The color format to get config with (default: hex).
         * @param colorFormat field value
         * @return this model
         */
        public GetHighlightObjectForTestRequest colorFormat(Overlay.ColorFormat colorFormat) {
            set("colorFormat", colorFormat);
            return this;
        }
        /**
         * Whether to show accessibility info (default: true).
         * @param showAccessibilityInfo field value
         * @return this model
         */
        public GetHighlightObjectForTestRequest showAccessibilityInfo(boolean showAccessibilityInfo) {
            set("showAccessibilityInfo", showAccessibilityInfo);
            return this;
        }
    }
    /**
     * For Persistent Grid testing.
     */
    public static final class GetGridHighlightObjectsForTestRequest extends CdpObject {
        public GetGridHighlightObjectsForTestRequest() {}
        /**
         * For Persistent Grid testing.
         * @param nodeIds protocol value
         */
        public GetGridHighlightObjectsForTestRequest(java.util.List<DOM.NodeId> nodeIds) {
            nodeIds(nodeIds);
        }
        public static GetGridHighlightObjectsForTestRequest fromMap(Map<String, Object> values) {
            final GetGridHighlightObjectsForTestRequest instance_ = new GetGridHighlightObjectsForTestRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Ids of the node to get highlight object for.
         * @return the protocol field value
         */
        public java.util.List<DOM.NodeId> nodeIds() {
            return CdpObject.requireList(require("nodeIds"), element0 -> new DOM.NodeId(((Number) element0).longValue()));
        }
        /**
         * Ids of the node to get highlight object for.
         * @param nodeIds field value
         * @return this model
         */
        public GetGridHighlightObjectsForTestRequest nodeIds(java.util.List<DOM.NodeId> nodeIds) {
            set("nodeIds", nodeIds);
            return this;
        }
    }
    /**
     * For Source Order Viewer testing.
     */
    public static final class GetSourceOrderHighlightObjectForTestRequest extends CdpObject {
        public GetSourceOrderHighlightObjectForTestRequest() {}
        /**
         * For Source Order Viewer testing.
         * @param nodeId protocol value
         */
        public GetSourceOrderHighlightObjectForTestRequest(DOM.NodeId nodeId) {
            nodeId(nodeId);
        }
        public static GetSourceOrderHighlightObjectForTestRequest fromMap(Map<String, Object> values) {
            final GetSourceOrderHighlightObjectForTestRequest instance_ = new GetSourceOrderHighlightObjectForTestRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Id of the node to highlight.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Id of the node to highlight.
         * @param nodeId field value
         * @return this model
         */
        public GetSourceOrderHighlightObjectForTestRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Highlights owner element of the frame with given id. Deprecated: Doesn&#x27;t work reliably and cannot be fixed due to process separation (the owner node might be in a different process). Determine the owner node in the client and use highlightNode.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class HighlightFrameRequest extends CdpObject {
        public HighlightFrameRequest() {}
        /**
         * Highlights owner element of the frame with given id. Deprecated: Doesn&#x27;t work reliably and cannot be fixed due to process separation (the owner node might be in a different process). Determine the owner node in the client and use highlightNode.
         * @param frameId protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public HighlightFrameRequest(Page.FrameId frameId) {
            frameId(frameId);
        }
        public static HighlightFrameRequest fromMap(Map<String, Object> values) {
            final HighlightFrameRequest instance_ = new HighlightFrameRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Identifier of the frame to highlight.
         * @return the protocol field value
         */
        public Page.FrameId frameId() {
            return new Page.FrameId((String) require("frameId"));
        }
        /**
         * The content box highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> contentColor() {
            return raw("contentColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The content box highlight outline color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> contentOutlineColor() {
            return raw("contentOutlineColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * Identifier of the frame to highlight.
         * @param frameId field value
         * @return this model
         */
        public HighlightFrameRequest frameId(Page.FrameId frameId) {
            set("frameId", frameId);
            return this;
        }
        /**
         * The content box highlight fill color (default: transparent).
         * @param contentColor field value
         * @return this model
         */
        public HighlightFrameRequest contentColor(DOM.RGBA contentColor) {
            set("contentColor", contentColor);
            return this;
        }
        /**
         * The content box highlight outline color (default: transparent).
         * @param contentOutlineColor field value
         * @return this model
         */
        public HighlightFrameRequest contentOutlineColor(DOM.RGBA contentOutlineColor) {
            set("contentOutlineColor", contentOutlineColor);
            return this;
        }
    }
    /**
     * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
     */
    public static final class HighlightNodeRequest extends CdpObject {
        public HighlightNodeRequest() {}
        /**
         * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param highlightConfig protocol value
         */
        public HighlightNodeRequest(Overlay.HighlightConfig highlightConfig) {
            highlightConfig(highlightConfig);
        }
        public static HighlightNodeRequest fromMap(Map<String, Object> values) {
            final HighlightNodeRequest instance_ = new HighlightNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * A descriptor for the highlight appearance.
         * @return the protocol field value
         */
        public Overlay.HighlightConfig highlightConfig() {
            return java.util.Objects.requireNonNull(Overlay.HighlightConfig.fromMap(CdpObject.requireObjectMap(require("highlightConfig"))));
        }
        /**
         * Identifier of the node to highlight.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return raw("nodeId").map(value -> new DOM.NodeId(((Number) value).longValue()));
        }
        /**
         * Identifier of the backend node to highlight.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return raw("backendNodeId").map(value -> new DOM.BackendNodeId(((Number) value).longValue()));
        }
        /**
         * JavaScript object id of the node to be highlighted.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return raw("objectId").map(value -> new Runtime.RemoteObjectId((String) value));
        }
        /**
         * Selectors to highlight relevant nodes.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> selector() {
            return raw("selector").map(value -> (String) value);
        }
        /**
         * A descriptor for the highlight appearance.
         * @param highlightConfig field value
         * @return this model
         */
        public HighlightNodeRequest highlightConfig(Overlay.HighlightConfig highlightConfig) {
            set("highlightConfig", highlightConfig);
            return this;
        }
        /**
         * Identifier of the node to highlight.
         * @param nodeId field value
         * @return this model
         */
        public HighlightNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node to highlight.
         * @param backendNodeId field value
         * @return this model
         */
        public HighlightNodeRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node to be highlighted.
         * @param objectId field value
         * @return this model
         */
        public HighlightNodeRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * Selectors to highlight relevant nodes.
         * @param selector field value
         * @return this model
         */
        public HighlightNodeRequest selector(String selector) {
            set("selector", selector);
            return this;
        }
    }
    /**
     * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
     */
    public static final class HighlightQuadRequest extends CdpObject {
        public HighlightQuadRequest() {}
        /**
         * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
         * @param quad protocol value
         */
        public HighlightQuadRequest(java.util.List<Double> quad) {
            quad(quad);
        }
        public static HighlightQuadRequest fromMap(Map<String, Object> values) {
            final HighlightQuadRequest instance_ = new HighlightQuadRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Quad to highlight
         * @return the protocol field value
         */
        public java.util.List<Double> quad() {
            return CdpObject.requireList(require("quad"), element0 -> ((Number) element0).doubleValue());
        }
        /**
         * The highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> color() {
            return raw("color").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The highlight outline color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> outlineColor() {
            return raw("outlineColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * Quad to highlight
         * @param quad field value
         * @return this model
         */
        public HighlightQuadRequest quad(java.util.List<Double> quad) {
            set("quad", quad);
            return this;
        }
        /**
         * The highlight fill color (default: transparent).
         * @param color field value
         * @return this model
         */
        public HighlightQuadRequest color(DOM.RGBA color) {
            set("color", color);
            return this;
        }
        /**
         * The highlight outline color (default: transparent).
         * @param outlineColor field value
         * @return this model
         */
        public HighlightQuadRequest outlineColor(DOM.RGBA outlineColor) {
            set("outlineColor", outlineColor);
            return this;
        }
    }
    /**
     * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport. Issue: the method does not handle device pixel ratio (DPR) correctly. The coordinates currently have to be adjusted by the client if DPR is not 1 (see crbug.com/437807128).
     */
    public static final class HighlightRectRequest extends CdpObject {
        public HighlightRectRequest() {}
        /**
         * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport. Issue: the method does not handle device pixel ratio (DPR) correctly. The coordinates currently have to be adjusted by the client if DPR is not 1 (see crbug.com/437807128).
         * @param x protocol value
         * @param y protocol value
         * @param width protocol value
         * @param height protocol value
         */
        public HighlightRectRequest(long x, long y, long width, long height) {
            x(x).y(y).width(width).height(height);
        }
        public static HighlightRectRequest fromMap(Map<String, Object> values) {
            final HighlightRectRequest instance_ = new HighlightRectRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * X coordinate
         * @return the protocol field value
         */
        public long x() {
            return ((Number) require("x")).longValue();
        }
        /**
         * Y coordinate
         * @return the protocol field value
         */
        public long y() {
            return ((Number) require("y")).longValue();
        }
        /**
         * Rectangle width
         * @return the protocol field value
         */
        public long width() {
            return ((Number) require("width")).longValue();
        }
        /**
         * Rectangle height
         * @return the protocol field value
         */
        public long height() {
            return ((Number) require("height")).longValue();
        }
        /**
         * The highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> color() {
            return raw("color").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * The highlight outline color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> outlineColor() {
            return raw("outlineColor").flatMap(CdpObject::objectMap).map(DOM.RGBA::fromMap);
        }
        /**
         * X coordinate
         * @param x field value
         * @return this model
         */
        public HighlightRectRequest x(long x) {
            set("x", x);
            return this;
        }
        /**
         * Y coordinate
         * @param y field value
         * @return this model
         */
        public HighlightRectRequest y(long y) {
            set("y", y);
            return this;
        }
        /**
         * Rectangle width
         * @param width field value
         * @return this model
         */
        public HighlightRectRequest width(long width) {
            set("width", width);
            return this;
        }
        /**
         * Rectangle height
         * @param height field value
         * @return this model
         */
        public HighlightRectRequest height(long height) {
            set("height", height);
            return this;
        }
        /**
         * The highlight fill color (default: transparent).
         * @param color field value
         * @return this model
         */
        public HighlightRectRequest color(DOM.RGBA color) {
            set("color", color);
            return this;
        }
        /**
         * The highlight outline color (default: transparent).
         * @param outlineColor field value
         * @return this model
         */
        public HighlightRectRequest outlineColor(DOM.RGBA outlineColor) {
            set("outlineColor", outlineColor);
            return this;
        }
    }
    /**
     * Highlights the source order of the children of the DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
     */
    public static final class HighlightSourceOrderRequest extends CdpObject {
        public HighlightSourceOrderRequest() {}
        /**
         * Highlights the source order of the children of the DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param sourceOrderConfig protocol value
         */
        public HighlightSourceOrderRequest(Overlay.SourceOrderConfig sourceOrderConfig) {
            sourceOrderConfig(sourceOrderConfig);
        }
        public static HighlightSourceOrderRequest fromMap(Map<String, Object> values) {
            final HighlightSourceOrderRequest instance_ = new HighlightSourceOrderRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * A descriptor for the appearance of the overlay drawing.
         * @return the protocol field value
         */
        public Overlay.SourceOrderConfig sourceOrderConfig() {
            return java.util.Objects.requireNonNull(Overlay.SourceOrderConfig.fromMap(CdpObject.requireObjectMap(require("sourceOrderConfig"))));
        }
        /**
         * Identifier of the node to highlight.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return raw("nodeId").map(value -> new DOM.NodeId(((Number) value).longValue()));
        }
        /**
         * Identifier of the backend node to highlight.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return raw("backendNodeId").map(value -> new DOM.BackendNodeId(((Number) value).longValue()));
        }
        /**
         * JavaScript object id of the node to be highlighted.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return raw("objectId").map(value -> new Runtime.RemoteObjectId((String) value));
        }
        /**
         * A descriptor for the appearance of the overlay drawing.
         * @param sourceOrderConfig field value
         * @return this model
         */
        public HighlightSourceOrderRequest sourceOrderConfig(Overlay.SourceOrderConfig sourceOrderConfig) {
            set("sourceOrderConfig", sourceOrderConfig);
            return this;
        }
        /**
         * Identifier of the node to highlight.
         * @param nodeId field value
         * @return this model
         */
        public HighlightSourceOrderRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node to highlight.
         * @param backendNodeId field value
         * @return this model
         */
        public HighlightSourceOrderRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node to be highlighted.
         * @param objectId field value
         * @return this model
         */
        public HighlightSourceOrderRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
    }
    /**
     * Enters the &#x27;inspect&#x27; mode. In this mode, elements that user is hovering over are highlighted. Backend then generates &#x27;inspectNodeRequested&#x27; event upon element selection.
     */
    public static final class SetInspectModeRequest extends CdpObject {
        public SetInspectModeRequest() {}
        /**
         * Enters the &#x27;inspect&#x27; mode. In this mode, elements that user is hovering over are highlighted. Backend then generates &#x27;inspectNodeRequested&#x27; event upon element selection.
         * @param mode protocol value
         */
        public SetInspectModeRequest(Overlay.InspectMode mode) {
            mode(mode);
        }
        public static SetInspectModeRequest fromMap(Map<String, Object> values) {
            final SetInspectModeRequest instance_ = new SetInspectModeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Set an inspection mode.
         * @return the protocol field value
         */
        public Overlay.InspectMode mode() {
            return Overlay.InspectMode.of((String) require("mode"));
        }
        /**
         * A descriptor for the highlight appearance of hovered-over nodes. May be omitted if {@code enabled == false}.
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.HighlightConfig> highlightConfig() {
            return raw("highlightConfig").flatMap(CdpObject::objectMap).map(Overlay.HighlightConfig::fromMap);
        }
        /**
         * Set an inspection mode.
         * @param mode field value
         * @return this model
         */
        public SetInspectModeRequest mode(Overlay.InspectMode mode) {
            set("mode", mode);
            return this;
        }
        /**
         * A descriptor for the highlight appearance of hovered-over nodes. May be omitted if {@code enabled == false}.
         * @param highlightConfig field value
         * @return this model
         */
        public SetInspectModeRequest highlightConfig(Overlay.HighlightConfig highlightConfig) {
            set("highlightConfig", highlightConfig);
            return this;
        }
    }
    /**
     * Highlights owner element of all frames detected to be ads.
     */
    public static final class SetShowAdHighlightsRequest extends CdpObject {
        public SetShowAdHighlightsRequest() {}
        /**
         * Highlights owner element of all frames detected to be ads.
         * @param show protocol value
         */
        public SetShowAdHighlightsRequest(boolean show) {
            show(show);
        }
        public static SetShowAdHighlightsRequest fromMap(Map<String, Object> values) {
            final SetShowAdHighlightsRequest instance_ = new SetShowAdHighlightsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * True for showing ad highlights
         * @return the protocol field value
         */
        public boolean show() {
            return (Boolean) require("show");
        }
        /**
         * True for showing ad highlights
         * @param show field value
         * @return this model
         */
        public SetShowAdHighlightsRequest show(boolean show) {
            set("show", show);
            return this;
        }
    }
    /**
     * Request parameters for Overlay.setPausedInDebuggerMessage.
     */
    public static final class SetPausedInDebuggerMessageRequest extends CdpObject {
        public SetPausedInDebuggerMessageRequest() {}
        public static SetPausedInDebuggerMessageRequest fromMap(Map<String, Object> values) {
            final SetPausedInDebuggerMessageRequest instance_ = new SetPausedInDebuggerMessageRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The message to display, also triggers resume and step over controls.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> message() {
            return raw("message").map(value -> (String) value);
        }
        /**
         * The message to display, also triggers resume and step over controls.
         * @param message field value
         * @return this model
         */
        public SetPausedInDebuggerMessageRequest message(String message) {
            set("message", message);
            return this;
        }
    }
    /**
     * Requests that backend shows debug borders on layers
     */
    public static final class SetShowDebugBordersRequest extends CdpObject {
        public SetShowDebugBordersRequest() {}
        /**
         * Requests that backend shows debug borders on layers
         * @param show protocol value
         */
        public SetShowDebugBordersRequest(boolean show) {
            show(show);
        }
        public static SetShowDebugBordersRequest fromMap(Map<String, Object> values) {
            final SetShowDebugBordersRequest instance_ = new SetShowDebugBordersRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * True for showing debug borders
         * @return the protocol field value
         */
        public boolean show() {
            return (Boolean) require("show");
        }
        /**
         * True for showing debug borders
         * @param show field value
         * @return this model
         */
        public SetShowDebugBordersRequest show(boolean show) {
            set("show", show);
            return this;
        }
    }
    /**
     * Requests that backend shows the FPS counter
     */
    public static final class SetShowFPSCounterRequest extends CdpObject {
        public SetShowFPSCounterRequest() {}
        /**
         * Requests that backend shows the FPS counter
         * @param show protocol value
         */
        public SetShowFPSCounterRequest(boolean show) {
            show(show);
        }
        public static SetShowFPSCounterRequest fromMap(Map<String, Object> values) {
            final SetShowFPSCounterRequest instance_ = new SetShowFPSCounterRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * True for showing the FPS counter
         * @return the protocol field value
         */
        public boolean show() {
            return (Boolean) require("show");
        }
        /**
         * True for showing the FPS counter
         * @param show field value
         * @return this model
         */
        public SetShowFPSCounterRequest show(boolean show) {
            set("show", show);
            return this;
        }
    }
    /**
     * Highlight multiple elements with the CSS Grid overlay.
     */
    public static final class SetShowGridOverlaysRequest extends CdpObject {
        public SetShowGridOverlaysRequest() {}
        /**
         * Highlight multiple elements with the CSS Grid overlay.
         * @param gridNodeHighlightConfigs protocol value
         */
        public SetShowGridOverlaysRequest(java.util.List<Overlay.GridNodeHighlightConfig> gridNodeHighlightConfigs) {
            gridNodeHighlightConfigs(gridNodeHighlightConfigs);
        }
        public static SetShowGridOverlaysRequest fromMap(Map<String, Object> values) {
            final SetShowGridOverlaysRequest instance_ = new SetShowGridOverlaysRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        public java.util.List<Overlay.GridNodeHighlightConfig> gridNodeHighlightConfigs() {
            return CdpObject.requireList(require("gridNodeHighlightConfigs"), element0 -> java.util.Objects.requireNonNull(Overlay.GridNodeHighlightConfig.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @param gridNodeHighlightConfigs field value
         * @return this model
         */
        public SetShowGridOverlaysRequest gridNodeHighlightConfigs(java.util.List<Overlay.GridNodeHighlightConfig> gridNodeHighlightConfigs) {
            set("gridNodeHighlightConfigs", gridNodeHighlightConfigs);
            return this;
        }
    }
    /**
     * Request parameters for Overlay.setShowFlexOverlays.
     */
    public static final class SetShowFlexOverlaysRequest extends CdpObject {
        public SetShowFlexOverlaysRequest() {}
        /**
         * Creates a new SetShowFlexOverlaysRequest with all required parameters.
         * @param flexNodeHighlightConfigs protocol value
         */
        public SetShowFlexOverlaysRequest(java.util.List<Overlay.FlexNodeHighlightConfig> flexNodeHighlightConfigs) {
            flexNodeHighlightConfigs(flexNodeHighlightConfigs);
        }
        public static SetShowFlexOverlaysRequest fromMap(Map<String, Object> values) {
            final SetShowFlexOverlaysRequest instance_ = new SetShowFlexOverlaysRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        public java.util.List<Overlay.FlexNodeHighlightConfig> flexNodeHighlightConfigs() {
            return CdpObject.requireList(require("flexNodeHighlightConfigs"), element0 -> java.util.Objects.requireNonNull(Overlay.FlexNodeHighlightConfig.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @param flexNodeHighlightConfigs field value
         * @return this model
         */
        public SetShowFlexOverlaysRequest flexNodeHighlightConfigs(java.util.List<Overlay.FlexNodeHighlightConfig> flexNodeHighlightConfigs) {
            set("flexNodeHighlightConfigs", flexNodeHighlightConfigs);
            return this;
        }
    }
    /**
     * Request parameters for Overlay.setShowScrollSnapOverlays.
     */
    public static final class SetShowScrollSnapOverlaysRequest extends CdpObject {
        public SetShowScrollSnapOverlaysRequest() {}
        /**
         * Creates a new SetShowScrollSnapOverlaysRequest with all required parameters.
         * @param scrollSnapHighlightConfigs protocol value
         */
        public SetShowScrollSnapOverlaysRequest(java.util.List<Overlay.ScrollSnapHighlightConfig> scrollSnapHighlightConfigs) {
            scrollSnapHighlightConfigs(scrollSnapHighlightConfigs);
        }
        public static SetShowScrollSnapOverlaysRequest fromMap(Map<String, Object> values) {
            final SetShowScrollSnapOverlaysRequest instance_ = new SetShowScrollSnapOverlaysRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        public java.util.List<Overlay.ScrollSnapHighlightConfig> scrollSnapHighlightConfigs() {
            return CdpObject.requireList(require("scrollSnapHighlightConfigs"), element0 -> java.util.Objects.requireNonNull(Overlay.ScrollSnapHighlightConfig.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @param scrollSnapHighlightConfigs field value
         * @return this model
         */
        public SetShowScrollSnapOverlaysRequest scrollSnapHighlightConfigs(java.util.List<Overlay.ScrollSnapHighlightConfig> scrollSnapHighlightConfigs) {
            set("scrollSnapHighlightConfigs", scrollSnapHighlightConfigs);
            return this;
        }
    }
    /**
     * Request parameters for Overlay.setShowContainerQueryOverlays.
     */
    public static final class SetShowContainerQueryOverlaysRequest extends CdpObject {
        public SetShowContainerQueryOverlaysRequest() {}
        /**
         * Creates a new SetShowContainerQueryOverlaysRequest with all required parameters.
         * @param containerQueryHighlightConfigs protocol value
         */
        public SetShowContainerQueryOverlaysRequest(java.util.List<Overlay.ContainerQueryHighlightConfig> containerQueryHighlightConfigs) {
            containerQueryHighlightConfigs(containerQueryHighlightConfigs);
        }
        public static SetShowContainerQueryOverlaysRequest fromMap(Map<String, Object> values) {
            final SetShowContainerQueryOverlaysRequest instance_ = new SetShowContainerQueryOverlaysRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        public java.util.List<Overlay.ContainerQueryHighlightConfig> containerQueryHighlightConfigs() {
            return CdpObject.requireList(require("containerQueryHighlightConfigs"), element0 -> java.util.Objects.requireNonNull(Overlay.ContainerQueryHighlightConfig.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @param containerQueryHighlightConfigs field value
         * @return this model
         */
        public SetShowContainerQueryOverlaysRequest containerQueryHighlightConfigs(java.util.List<Overlay.ContainerQueryHighlightConfig> containerQueryHighlightConfigs) {
            set("containerQueryHighlightConfigs", containerQueryHighlightConfigs);
            return this;
        }
    }
    /**
     * Request parameters for Overlay.setShowInspectedElementAnchor.
     */
    public static final class SetShowInspectedElementAnchorRequest extends CdpObject {
        public SetShowInspectedElementAnchorRequest() {}
        /**
         * Creates a new SetShowInspectedElementAnchorRequest with all required parameters.
         * @param inspectedElementAnchorConfig protocol value
         */
        public SetShowInspectedElementAnchorRequest(Overlay.InspectedElementAnchorConfig inspectedElementAnchorConfig) {
            inspectedElementAnchorConfig(inspectedElementAnchorConfig);
        }
        public static SetShowInspectedElementAnchorRequest fromMap(Map<String, Object> values) {
            final SetShowInspectedElementAnchorRequest instance_ = new SetShowInspectedElementAnchorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Node identifier for which to show an anchor for.
         * @return the protocol field value
         */
        public Overlay.InspectedElementAnchorConfig inspectedElementAnchorConfig() {
            return java.util.Objects.requireNonNull(Overlay.InspectedElementAnchorConfig.fromMap(CdpObject.requireObjectMap(require("inspectedElementAnchorConfig"))));
        }
        /**
         * Node identifier for which to show an anchor for.
         * @param inspectedElementAnchorConfig field value
         * @return this model
         */
        public SetShowInspectedElementAnchorRequest inspectedElementAnchorConfig(Overlay.InspectedElementAnchorConfig inspectedElementAnchorConfig) {
            set("inspectedElementAnchorConfig", inspectedElementAnchorConfig);
            return this;
        }
    }
    /**
     * Requests that backend shows paint rectangles
     */
    public static final class SetShowPaintRectsRequest extends CdpObject {
        public SetShowPaintRectsRequest() {}
        /**
         * Requests that backend shows paint rectangles
         * @param result protocol value
         */
        public SetShowPaintRectsRequest(boolean result) {
            result(result);
        }
        public static SetShowPaintRectsRequest fromMap(Map<String, Object> values) {
            final SetShowPaintRectsRequest instance_ = new SetShowPaintRectsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * True for showing paint rectangles
         * @return the protocol field value
         */
        public boolean result() {
            return (Boolean) require("result");
        }
        /**
         * True for showing paint rectangles
         * @param result field value
         * @return this model
         */
        public SetShowPaintRectsRequest result(boolean result) {
            set("result", result);
            return this;
        }
    }
    /**
     * Requests that backend shows layout shift regions
     */
    public static final class SetShowLayoutShiftRegionsRequest extends CdpObject {
        public SetShowLayoutShiftRegionsRequest() {}
        /**
         * Requests that backend shows layout shift regions
         * @param result protocol value
         */
        public SetShowLayoutShiftRegionsRequest(boolean result) {
            result(result);
        }
        public static SetShowLayoutShiftRegionsRequest fromMap(Map<String, Object> values) {
            final SetShowLayoutShiftRegionsRequest instance_ = new SetShowLayoutShiftRegionsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * True for showing layout shift regions
         * @return the protocol field value
         */
        public boolean result() {
            return (Boolean) require("result");
        }
        /**
         * True for showing layout shift regions
         * @param result field value
         * @return this model
         */
        public SetShowLayoutShiftRegionsRequest result(boolean result) {
            set("result", result);
            return this;
        }
    }
    /**
     * Requests that backend shows scroll bottleneck rects
     */
    public static final class SetShowScrollBottleneckRectsRequest extends CdpObject {
        public SetShowScrollBottleneckRectsRequest() {}
        /**
         * Requests that backend shows scroll bottleneck rects
         * @param show protocol value
         */
        public SetShowScrollBottleneckRectsRequest(boolean show) {
            show(show);
        }
        public static SetShowScrollBottleneckRectsRequest fromMap(Map<String, Object> values) {
            final SetShowScrollBottleneckRectsRequest instance_ = new SetShowScrollBottleneckRectsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * True for showing scroll bottleneck rects
         * @return the protocol field value
         */
        public boolean show() {
            return (Boolean) require("show");
        }
        /**
         * True for showing scroll bottleneck rects
         * @param show field value
         * @return this model
         */
        public SetShowScrollBottleneckRectsRequest show(boolean show) {
            set("show", show);
            return this;
        }
    }
    /**
     * Deprecated, no longer has any effect.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetShowHitTestBordersRequest extends CdpObject {
        public SetShowHitTestBordersRequest() {}
        /**
         * Deprecated, no longer has any effect.
         * @param show protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetShowHitTestBordersRequest(boolean show) {
            show(show);
        }
        public static SetShowHitTestBordersRequest fromMap(Map<String, Object> values) {
            final SetShowHitTestBordersRequest instance_ = new SetShowHitTestBordersRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * True for showing hit-test borders
         * @return the protocol field value
         */
        public boolean show() {
            return (Boolean) require("show");
        }
        /**
         * True for showing hit-test borders
         * @param show field value
         * @return this model
         */
        public SetShowHitTestBordersRequest show(boolean show) {
            set("show", show);
            return this;
        }
    }
    /**
     * Deprecated, no longer has any effect.
     * @deprecated Deprecated by the Chromium DevTools Protocol.
     */
    @Deprecated
    public static final class SetShowWebVitalsRequest extends CdpObject {
        public SetShowWebVitalsRequest() {}
        /**
         * Deprecated, no longer has any effect.
         * @param show protocol value
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public SetShowWebVitalsRequest(boolean show) {
            show(show);
        }
        public static SetShowWebVitalsRequest fromMap(Map<String, Object> values) {
            final SetShowWebVitalsRequest instance_ = new SetShowWebVitalsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the show field.
         * @return the protocol field value
         */
        public boolean show() {
            return (Boolean) require("show");
        }
        /**
         * Sets the show field.
         * @param show field value
         * @return this model
         */
        public SetShowWebVitalsRequest show(boolean show) {
            set("show", show);
            return this;
        }
    }
    /**
     * Paints viewport size upon main frame resize.
     */
    public static final class SetShowViewportSizeOnResizeRequest extends CdpObject {
        public SetShowViewportSizeOnResizeRequest() {}
        /**
         * Paints viewport size upon main frame resize.
         * @param show protocol value
         */
        public SetShowViewportSizeOnResizeRequest(boolean show) {
            show(show);
        }
        public static SetShowViewportSizeOnResizeRequest fromMap(Map<String, Object> values) {
            final SetShowViewportSizeOnResizeRequest instance_ = new SetShowViewportSizeOnResizeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Whether to paint size or not.
         * @return the protocol field value
         */
        public boolean show() {
            return (Boolean) require("show");
        }
        /**
         * Whether to paint size or not.
         * @param show field value
         * @return this model
         */
        public SetShowViewportSizeOnResizeRequest show(boolean show) {
            set("show", show);
            return this;
        }
    }
    /**
     * Add a dual screen device hinge
     */
    public static final class SetShowHingeRequest extends CdpObject {
        public SetShowHingeRequest() {}
        public static SetShowHingeRequest fromMap(Map<String, Object> values) {
            final SetShowHingeRequest instance_ = new SetShowHingeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * hinge data, null means hideHinge
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.HingeConfig> hingeConfig() {
            return raw("hingeConfig").flatMap(CdpObject::objectMap).map(Overlay.HingeConfig::fromMap);
        }
        /**
         * hinge data, null means hideHinge
         * @param hingeConfig field value
         * @return this model
         */
        public SetShowHingeRequest hingeConfig(Overlay.HingeConfig hingeConfig) {
            set("hingeConfig", hingeConfig);
            return this;
        }
    }
    /**
     * Show elements in isolation mode with overlays.
     */
    public static final class SetShowIsolatedElementsRequest extends CdpObject {
        public SetShowIsolatedElementsRequest() {}
        /**
         * Show elements in isolation mode with overlays.
         * @param isolatedElementHighlightConfigs protocol value
         */
        public SetShowIsolatedElementsRequest(java.util.List<Overlay.IsolatedElementHighlightConfig> isolatedElementHighlightConfigs) {
            isolatedElementHighlightConfigs(isolatedElementHighlightConfigs);
        }
        public static SetShowIsolatedElementsRequest fromMap(Map<String, Object> values) {
            final SetShowIsolatedElementsRequest instance_ = new SetShowIsolatedElementsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        public java.util.List<Overlay.IsolatedElementHighlightConfig> isolatedElementHighlightConfigs() {
            return CdpObject.requireList(require("isolatedElementHighlightConfigs"), element0 -> java.util.Objects.requireNonNull(Overlay.IsolatedElementHighlightConfig.fromMap(CdpObject.requireObjectMap(element0))));
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @param isolatedElementHighlightConfigs field value
         * @return this model
         */
        public SetShowIsolatedElementsRequest isolatedElementHighlightConfigs(java.util.List<Overlay.IsolatedElementHighlightConfig> isolatedElementHighlightConfigs) {
            set("isolatedElementHighlightConfigs", isolatedElementHighlightConfigs);
            return this;
        }
    }
    /**
     * Show Window Controls Overlay for PWA
     */
    public static final class SetShowWindowControlsOverlayRequest extends CdpObject {
        public SetShowWindowControlsOverlayRequest() {}
        public static SetShowWindowControlsOverlayRequest fromMap(Map<String, Object> values) {
            final SetShowWindowControlsOverlayRequest instance_ = new SetShowWindowControlsOverlayRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Window Controls Overlay data, null means hide Window Controls Overlay
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.WindowControlsOverlayConfig> windowControlsOverlayConfig() {
            return raw("windowControlsOverlayConfig").flatMap(CdpObject::objectMap).map(Overlay.WindowControlsOverlayConfig::fromMap);
        }
        /**
         * Window Controls Overlay data, null means hide Window Controls Overlay
         * @param windowControlsOverlayConfig field value
         * @return this model
         */
        public SetShowWindowControlsOverlayRequest windowControlsOverlayConfig(Overlay.WindowControlsOverlayConfig windowControlsOverlayConfig) {
            set("windowControlsOverlayConfig", windowControlsOverlayConfig);
            return this;
        }
    }
    /**
     * Fired when the node should be inspected. This happens after call to {@code setInspectMode} or when user manually inspects an element.
     */
    public static final class InspectNodeRequestedEvent extends CdpObject {
        public InspectNodeRequestedEvent() {}
        private InspectNodeRequestedEvent(Map<String, Object> values) { super(values); }
        public static InspectNodeRequestedEvent fromMap(Map<String, Object> values) {
            return new InspectNodeRequestedEvent(values);
        }
        /**
         * Id of the node to inspect.
         * @return the protocol field value
         */
        public DOM.BackendNodeId backendNodeId() {
            return new DOM.BackendNodeId(((Number) require("backendNodeId")).longValue());
        }
        /**
         * Id of the node to inspect.
         * @param backendNodeId field value
         * @return this model
         */
        public InspectNodeRequestedEvent backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
    }
    /**
     * Fired when the node should be highlighted. This happens after call to {@code setInspectMode}.
     */
    public static final class NodeHighlightRequestedEvent extends CdpObject {
        public NodeHighlightRequestedEvent() {}
        private NodeHighlightRequestedEvent(Map<String, Object> values) { super(values); }
        public static NodeHighlightRequestedEvent fromMap(Map<String, Object> values) {
            return new NodeHighlightRequestedEvent(values);
        }
        /**
         * Returns the nodeId field.
         * @return the protocol field value
         */
        public DOM.NodeId nodeId() {
            return new DOM.NodeId(((Number) require("nodeId")).longValue());
        }
        /**
         * Sets the nodeId field.
         * @param nodeId field value
         * @return this model
         */
        public NodeHighlightRequestedEvent nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
    }
    /**
     * Fired when user asks to capture screenshot of some area on the page.
     */
    public static final class ScreenshotRequestedEvent extends CdpObject {
        public ScreenshotRequestedEvent() {}
        private ScreenshotRequestedEvent(Map<String, Object> values) { super(values); }
        public static ScreenshotRequestedEvent fromMap(Map<String, Object> values) {
            return new ScreenshotRequestedEvent(values);
        }
        /**
         * Viewport to capture, in device independent pixels (dip).
         * @return the protocol field value
         */
        public Page.Viewport viewport() {
            return java.util.Objects.requireNonNull(Page.Viewport.fromMap(CdpObject.requireObjectMap(require("viewport"))));
        }
        /**
         * Viewport to capture, in device independent pixels (dip).
         * @param viewport field value
         * @return this model
         */
        public ScreenshotRequestedEvent viewport(Page.Viewport viewport) {
            set("viewport", viewport);
            return this;
        }
    }
    /**
     * Fired when user asks to show the Inspect panel.
     */
    public static final class InspectPanelShowRequestedEvent extends CdpObject {
        public InspectPanelShowRequestedEvent() {}
        private InspectPanelShowRequestedEvent(Map<String, Object> values) { super(values); }
        public static InspectPanelShowRequestedEvent fromMap(Map<String, Object> values) {
            return new InspectPanelShowRequestedEvent(values);
        }
        /**
         * Id of the node to show in the panel.
         * @return the protocol field value
         */
        public DOM.BackendNodeId backendNodeId() {
            return new DOM.BackendNodeId(((Number) require("backendNodeId")).longValue());
        }
        /**
         * Id of the node to show in the panel.
         * @param backendNodeId field value
         * @return this model
         */
        public InspectPanelShowRequestedEvent backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
    }
    /**
     * Fired when user asks to restore the Inspected Element floating window.
     */
    public static final class InspectedElementWindowRestoredEvent extends CdpObject {
        public InspectedElementWindowRestoredEvent() {}
        private InspectedElementWindowRestoredEvent(Map<String, Object> values) { super(values); }
        public static InspectedElementWindowRestoredEvent fromMap(Map<String, Object> values) {
            return new InspectedElementWindowRestoredEvent(values);
        }
        /**
         * Id of the node to restore the floating window for.
         * @return the protocol field value
         */
        public DOM.BackendNodeId backendNodeId() {
            return new DOM.BackendNodeId(((Number) require("backendNodeId")).longValue());
        }
        /**
         * Id of the node to restore the floating window for.
         * @param backendNodeId field value
         * @return this model
         */
        public InspectedElementWindowRestoredEvent backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
    }
    /**
     * Fired when user cancels the inspect mode.
     */
    public static final class InspectModeCanceledEvent extends CdpObject {
        public InspectModeCanceledEvent() {}
        private InspectModeCanceledEvent(Map<String, Object> values) { super(values); }
        public static InspectModeCanceledEvent fromMap(Map<String, Object> values) {
            return new InspectModeCanceledEvent(values);
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Disables domain notifications.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> disable() {
            return client.call("Overlay.disable", null);
        }
        /**
         * Enables domain notifications.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Overlay.enable", null);
        }
        /**
         * For testing.
         * @param nodeId protocol value
         * @param includeDistance protocol value
         * @param includeStyle protocol value
         * @param colorFormat protocol value
         * @param showAccessibilityInfo protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getHighlightObjectForTest(DOM.NodeId nodeId, @Nullable Boolean includeDistance, @Nullable Boolean includeStyle, Overlay.@Nullable ColorFormat colorFormat, @Nullable Boolean showAccessibilityInfo) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            if (includeDistance != null) params.put("includeDistance", CdpObject.json(includeDistance));
            if (includeStyle != null) params.put("includeStyle", CdpObject.json(includeStyle));
            if (colorFormat != null) params.put("colorFormat", CdpObject.json(colorFormat));
            if (showAccessibilityInfo != null) params.put("showAccessibilityInfo", CdpObject.json(showAccessibilityInfo));
            return client.call("Overlay.getHighlightObjectForTest", params, result_ -> CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("highlight"))));
        }
        /**
         * For testing.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getHighlightObjectForTest(DOM.NodeId nodeId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("Overlay.getHighlightObjectForTest", params, result_ -> CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("highlight"))));
        }
        /**
         * For testing.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getHighlightObjectForTest(GetHighlightObjectForTestRequest request) {
            return client.call("Overlay.getHighlightObjectForTest", request == null ? null : request.toMap(), result_ -> CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("highlight"))));
        }
        /**
         * For Persistent Grid testing.
         * @param nodeIds protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getGridHighlightObjectsForTest(java.util.List<DOM.NodeId> nodeIds) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeIds", CdpObject.json(nodeIds));
            return client.call("Overlay.getGridHighlightObjectsForTest", params, result_ -> CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("highlights"))));
        }
        /**
         * For Persistent Grid testing.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getGridHighlightObjectsForTest(GetGridHighlightObjectsForTestRequest request) {
            return client.call("Overlay.getGridHighlightObjectsForTest", request == null ? null : request.toMap(), result_ -> CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("highlights"))));
        }
        /**
         * For Source Order Viewer testing.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getSourceOrderHighlightObjectForTest(DOM.NodeId nodeId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("Overlay.getSourceOrderHighlightObjectForTest", params, result_ -> CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("highlight"))));
        }
        /**
         * For Source Order Viewer testing.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getSourceOrderHighlightObjectForTest(GetSourceOrderHighlightObjectForTestRequest request) {
            return client.call("Overlay.getSourceOrderHighlightObjectForTest", request == null ? null : request.toMap(), result_ -> CdpObject.requireObjectMap(java.util.Objects.requireNonNull(result_.get("highlight"))));
        }
        /**
         * Hides any highlight.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> hideHighlight() {
            return client.call("Overlay.hideHighlight", null);
        }
        /**
         * Highlights owner element of the frame with given id. Deprecated: Doesn&#x27;t work reliably and cannot be fixed due to process separation (the owner node might be in a different process). Determine the owner node in the client and use highlightNode.
         * @param frameId protocol value
         * @param contentColor protocol value
         * @param contentOutlineColor protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> highlightFrame(Page.FrameId frameId, DOM.@Nullable RGBA contentColor, DOM.@Nullable RGBA contentOutlineColor) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            if (contentColor != null) params.put("contentColor", CdpObject.json(contentColor));
            if (contentOutlineColor != null) params.put("contentOutlineColor", CdpObject.json(contentOutlineColor));
            return client.call("Overlay.highlightFrame", params);
        }
        /**
         * Highlights owner element of the frame with given id. Deprecated: Doesn&#x27;t work reliably and cannot be fixed due to process separation (the owner node might be in a different process). Determine the owner node in the client and use highlightNode.
         * @param frameId protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> highlightFrame(Page.FrameId frameId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            return client.call("Overlay.highlightFrame", params);
        }
        /**
         * Highlights owner element of the frame with given id. Deprecated: Doesn&#x27;t work reliably and cannot be fixed due to process separation (the owner node might be in a different process). Determine the owner node in the client and use highlightNode.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> highlightFrame(HighlightFrameRequest request) {
            return client.call("Overlay.highlightFrame", request == null ? null : request.toMap());
        }
        /**
         * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param highlightConfig protocol value
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @param selector protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightNode(Overlay.HighlightConfig highlightConfig, DOM.@Nullable NodeId nodeId, DOM.@Nullable BackendNodeId backendNodeId, Runtime.@Nullable RemoteObjectId objectId, @Nullable String selector) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("highlightConfig", CdpObject.json(highlightConfig));
            if (nodeId != null) params.put("nodeId", CdpObject.json(nodeId));
            if (backendNodeId != null) params.put("backendNodeId", CdpObject.json(backendNodeId));
            if (objectId != null) params.put("objectId", CdpObject.json(objectId));
            if (selector != null) params.put("selector", CdpObject.json(selector));
            return client.call("Overlay.highlightNode", params);
        }
        /**
         * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param highlightConfig protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightNode(Overlay.HighlightConfig highlightConfig) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("highlightConfig", CdpObject.json(highlightConfig));
            return client.call("Overlay.highlightNode", params);
        }
        /**
         * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightNode(HighlightNodeRequest request) {
            return client.call("Overlay.highlightNode", request == null ? null : request.toMap());
        }
        /**
         * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
         * @param quad protocol value
         * @param color protocol value
         * @param outlineColor protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightQuad(java.util.List<Double> quad, DOM.@Nullable RGBA color, DOM.@Nullable RGBA outlineColor) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("quad", CdpObject.json(quad));
            if (color != null) params.put("color", CdpObject.json(color));
            if (outlineColor != null) params.put("outlineColor", CdpObject.json(outlineColor));
            return client.call("Overlay.highlightQuad", params);
        }
        /**
         * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
         * @param quad protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightQuad(java.util.List<Double> quad) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("quad", CdpObject.json(quad));
            return client.call("Overlay.highlightQuad", params);
        }
        /**
         * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightQuad(HighlightQuadRequest request) {
            return client.call("Overlay.highlightQuad", request == null ? null : request.toMap());
        }
        /**
         * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport. Issue: the method does not handle device pixel ratio (DPR) correctly. The coordinates currently have to be adjusted by the client if DPR is not 1 (see crbug.com/437807128).
         * @param x protocol value
         * @param y protocol value
         * @param width protocol value
         * @param height protocol value
         * @param color protocol value
         * @param outlineColor protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightRect(long x, long y, long width, long height, DOM.@Nullable RGBA color, DOM.@Nullable RGBA outlineColor) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("x", CdpObject.json(x));
            params.put("y", CdpObject.json(y));
            params.put("width", CdpObject.json(width));
            params.put("height", CdpObject.json(height));
            if (color != null) params.put("color", CdpObject.json(color));
            if (outlineColor != null) params.put("outlineColor", CdpObject.json(outlineColor));
            return client.call("Overlay.highlightRect", params);
        }
        /**
         * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport. Issue: the method does not handle device pixel ratio (DPR) correctly. The coordinates currently have to be adjusted by the client if DPR is not 1 (see crbug.com/437807128).
         * @param x protocol value
         * @param y protocol value
         * @param width protocol value
         * @param height protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightRect(long x, long y, long width, long height) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("x", CdpObject.json(x));
            params.put("y", CdpObject.json(y));
            params.put("width", CdpObject.json(width));
            params.put("height", CdpObject.json(height));
            return client.call("Overlay.highlightRect", params);
        }
        /**
         * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport. Issue: the method does not handle device pixel ratio (DPR) correctly. The coordinates currently have to be adjusted by the client if DPR is not 1 (see crbug.com/437807128).
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightRect(HighlightRectRequest request) {
            return client.call("Overlay.highlightRect", request == null ? null : request.toMap());
        }
        /**
         * Highlights the source order of the children of the DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param sourceOrderConfig protocol value
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightSourceOrder(Overlay.SourceOrderConfig sourceOrderConfig, DOM.@Nullable NodeId nodeId, DOM.@Nullable BackendNodeId backendNodeId, Runtime.@Nullable RemoteObjectId objectId) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("sourceOrderConfig", CdpObject.json(sourceOrderConfig));
            if (nodeId != null) params.put("nodeId", CdpObject.json(nodeId));
            if (backendNodeId != null) params.put("backendNodeId", CdpObject.json(backendNodeId));
            if (objectId != null) params.put("objectId", CdpObject.json(objectId));
            return client.call("Overlay.highlightSourceOrder", params);
        }
        /**
         * Highlights the source order of the children of the DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param sourceOrderConfig protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightSourceOrder(Overlay.SourceOrderConfig sourceOrderConfig) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("sourceOrderConfig", CdpObject.json(sourceOrderConfig));
            return client.call("Overlay.highlightSourceOrder", params);
        }
        /**
         * Highlights the source order of the children of the DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightSourceOrder(HighlightSourceOrderRequest request) {
            return client.call("Overlay.highlightSourceOrder", request == null ? null : request.toMap());
        }
        /**
         * Enters the &#x27;inspect&#x27; mode. In this mode, elements that user is hovering over are highlighted. Backend then generates &#x27;inspectNodeRequested&#x27; event upon element selection.
         * @param mode protocol value
         * @param highlightConfig protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInspectMode(Overlay.InspectMode mode, Overlay.HighlightConfig highlightConfig) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("mode", CdpObject.json(mode));
            params.put("highlightConfig", CdpObject.json(highlightConfig));
            return client.call("Overlay.setInspectMode", params);
        }
        /**
         * Enters the &#x27;inspect&#x27; mode. In this mode, elements that user is hovering over are highlighted. Backend then generates &#x27;inspectNodeRequested&#x27; event upon element selection.
         * @param mode protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInspectMode(Overlay.InspectMode mode) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("mode", CdpObject.json(mode));
            return client.call("Overlay.setInspectMode", params);
        }
        /**
         * Enters the &#x27;inspect&#x27; mode. In this mode, elements that user is hovering over are highlighted. Backend then generates &#x27;inspectNodeRequested&#x27; event upon element selection.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInspectMode(SetInspectModeRequest request) {
            return client.call("Overlay.setInspectMode", request == null ? null : request.toMap());
        }
        /**
         * Highlights owner element of all frames detected to be ads.
         * @param show protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowAdHighlights(boolean show) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("show", CdpObject.json(show));
            return client.call("Overlay.setShowAdHighlights", params);
        }
        /**
         * Highlights owner element of all frames detected to be ads.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowAdHighlights(SetShowAdHighlightsRequest request) {
            return client.call("Overlay.setShowAdHighlights", request == null ? null : request.toMap());
        }
        /**
         * Invokes Overlay.setPausedInDebuggerMessage.
         * @param message protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPausedInDebuggerMessage(String message) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("message", CdpObject.json(message));
            return client.call("Overlay.setPausedInDebuggerMessage", params);
        }
        /**
         * Invokes Overlay.setPausedInDebuggerMessage with default parameters.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPausedInDebuggerMessage() {
            return client.call("Overlay.setPausedInDebuggerMessage", null);
        }
        /**
         * Invokes Overlay.setPausedInDebuggerMessage with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPausedInDebuggerMessage(SetPausedInDebuggerMessageRequest request) {
            return client.call("Overlay.setPausedInDebuggerMessage", request == null ? null : request.toMap());
        }
        /**
         * Requests that backend shows debug borders on layers
         * @param show protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowDebugBorders(boolean show) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("show", CdpObject.json(show));
            return client.call("Overlay.setShowDebugBorders", params);
        }
        /**
         * Requests that backend shows debug borders on layers
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowDebugBorders(SetShowDebugBordersRequest request) {
            return client.call("Overlay.setShowDebugBorders", request == null ? null : request.toMap());
        }
        /**
         * Requests that backend shows the FPS counter
         * @param show protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowFPSCounter(boolean show) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("show", CdpObject.json(show));
            return client.call("Overlay.setShowFPSCounter", params);
        }
        /**
         * Requests that backend shows the FPS counter
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowFPSCounter(SetShowFPSCounterRequest request) {
            return client.call("Overlay.setShowFPSCounter", request == null ? null : request.toMap());
        }
        /**
         * Highlight multiple elements with the CSS Grid overlay.
         * @param gridNodeHighlightConfigs protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowGridOverlays(java.util.List<Overlay.GridNodeHighlightConfig> gridNodeHighlightConfigs) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("gridNodeHighlightConfigs", CdpObject.json(gridNodeHighlightConfigs));
            return client.call("Overlay.setShowGridOverlays", params);
        }
        /**
         * Highlight multiple elements with the CSS Grid overlay.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowGridOverlays(SetShowGridOverlaysRequest request) {
            return client.call("Overlay.setShowGridOverlays", request == null ? null : request.toMap());
        }
        /**
         * Invokes Overlay.setShowFlexOverlays.
         * @param flexNodeHighlightConfigs protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowFlexOverlays(java.util.List<Overlay.FlexNodeHighlightConfig> flexNodeHighlightConfigs) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("flexNodeHighlightConfigs", CdpObject.json(flexNodeHighlightConfigs));
            return client.call("Overlay.setShowFlexOverlays", params);
        }
        /**
         * Invokes Overlay.setShowFlexOverlays with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowFlexOverlays(SetShowFlexOverlaysRequest request) {
            return client.call("Overlay.setShowFlexOverlays", request == null ? null : request.toMap());
        }
        /**
         * Invokes Overlay.setShowScrollSnapOverlays.
         * @param scrollSnapHighlightConfigs protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowScrollSnapOverlays(java.util.List<Overlay.ScrollSnapHighlightConfig> scrollSnapHighlightConfigs) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("scrollSnapHighlightConfigs", CdpObject.json(scrollSnapHighlightConfigs));
            return client.call("Overlay.setShowScrollSnapOverlays", params);
        }
        /**
         * Invokes Overlay.setShowScrollSnapOverlays with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowScrollSnapOverlays(SetShowScrollSnapOverlaysRequest request) {
            return client.call("Overlay.setShowScrollSnapOverlays", request == null ? null : request.toMap());
        }
        /**
         * Invokes Overlay.setShowContainerQueryOverlays.
         * @param containerQueryHighlightConfigs protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowContainerQueryOverlays(java.util.List<Overlay.ContainerQueryHighlightConfig> containerQueryHighlightConfigs) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("containerQueryHighlightConfigs", CdpObject.json(containerQueryHighlightConfigs));
            return client.call("Overlay.setShowContainerQueryOverlays", params);
        }
        /**
         * Invokes Overlay.setShowContainerQueryOverlays with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowContainerQueryOverlays(SetShowContainerQueryOverlaysRequest request) {
            return client.call("Overlay.setShowContainerQueryOverlays", request == null ? null : request.toMap());
        }
        /**
         * Invokes Overlay.setShowInspectedElementAnchor.
         * @param inspectedElementAnchorConfig protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowInspectedElementAnchor(Overlay.InspectedElementAnchorConfig inspectedElementAnchorConfig) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("inspectedElementAnchorConfig", CdpObject.json(inspectedElementAnchorConfig));
            return client.call("Overlay.setShowInspectedElementAnchor", params);
        }
        /**
         * Invokes Overlay.setShowInspectedElementAnchor with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowInspectedElementAnchor(SetShowInspectedElementAnchorRequest request) {
            return client.call("Overlay.setShowInspectedElementAnchor", request == null ? null : request.toMap());
        }
        /**
         * Requests that backend shows paint rectangles
         * @param result protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowPaintRects(boolean result) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("result", CdpObject.json(result));
            return client.call("Overlay.setShowPaintRects", params);
        }
        /**
         * Requests that backend shows paint rectangles
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowPaintRects(SetShowPaintRectsRequest request) {
            return client.call("Overlay.setShowPaintRects", request == null ? null : request.toMap());
        }
        /**
         * Requests that backend shows layout shift regions
         * @param result protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowLayoutShiftRegions(boolean result) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("result", CdpObject.json(result));
            return client.call("Overlay.setShowLayoutShiftRegions", params);
        }
        /**
         * Requests that backend shows layout shift regions
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowLayoutShiftRegions(SetShowLayoutShiftRegionsRequest request) {
            return client.call("Overlay.setShowLayoutShiftRegions", request == null ? null : request.toMap());
        }
        /**
         * Requests that backend shows scroll bottleneck rects
         * @param show protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowScrollBottleneckRects(boolean show) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("show", CdpObject.json(show));
            return client.call("Overlay.setShowScrollBottleneckRects", params);
        }
        /**
         * Requests that backend shows scroll bottleneck rects
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowScrollBottleneckRects(SetShowScrollBottleneckRectsRequest request) {
            return client.call("Overlay.setShowScrollBottleneckRects", request == null ? null : request.toMap());
        }
        /**
         * Deprecated, no longer has any effect.
         * @param show protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setShowHitTestBorders(boolean show) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("show", CdpObject.json(show));
            return client.call("Overlay.setShowHitTestBorders", params);
        }
        /**
         * Deprecated, no longer has any effect.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setShowHitTestBorders(SetShowHitTestBordersRequest request) {
            return client.call("Overlay.setShowHitTestBorders", request == null ? null : request.toMap());
        }
        /**
         * Deprecated, no longer has any effect.
         * @param show protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setShowWebVitals(boolean show) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("show", CdpObject.json(show));
            return client.call("Overlay.setShowWebVitals", params);
        }
        /**
         * Deprecated, no longer has any effect.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setShowWebVitals(SetShowWebVitalsRequest request) {
            return client.call("Overlay.setShowWebVitals", request == null ? null : request.toMap());
        }
        /**
         * Paints viewport size upon main frame resize.
         * @param show protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowViewportSizeOnResize(boolean show) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("show", CdpObject.json(show));
            return client.call("Overlay.setShowViewportSizeOnResize", params);
        }
        /**
         * Paints viewport size upon main frame resize.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowViewportSizeOnResize(SetShowViewportSizeOnResizeRequest request) {
            return client.call("Overlay.setShowViewportSizeOnResize", request == null ? null : request.toMap());
        }
        /**
         * Add a dual screen device hinge
         * @param hingeConfig protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowHinge(Overlay.HingeConfig hingeConfig) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("hingeConfig", CdpObject.json(hingeConfig));
            return client.call("Overlay.setShowHinge", params);
        }
        /**
         * Add a dual screen device hinge
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowHinge() {
            return client.call("Overlay.setShowHinge", null);
        }
        /**
         * Add a dual screen device hinge
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowHinge(SetShowHingeRequest request) {
            return client.call("Overlay.setShowHinge", request == null ? null : request.toMap());
        }
        /**
         * Show elements in isolation mode with overlays.
         * @param isolatedElementHighlightConfigs protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowIsolatedElements(java.util.List<Overlay.IsolatedElementHighlightConfig> isolatedElementHighlightConfigs) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("isolatedElementHighlightConfigs", CdpObject.json(isolatedElementHighlightConfigs));
            return client.call("Overlay.setShowIsolatedElements", params);
        }
        /**
         * Show elements in isolation mode with overlays.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowIsolatedElements(SetShowIsolatedElementsRequest request) {
            return client.call("Overlay.setShowIsolatedElements", request == null ? null : request.toMap());
        }
        /**
         * Show Window Controls Overlay for PWA
         * @param windowControlsOverlayConfig protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowWindowControlsOverlay(Overlay.WindowControlsOverlayConfig windowControlsOverlayConfig) {
            final Map<String, Object> params = new LinkedHashMap<>();
            params.put("windowControlsOverlayConfig", CdpObject.json(windowControlsOverlayConfig));
            return client.call("Overlay.setShowWindowControlsOverlay", params);
        }
        /**
         * Show Window Controls Overlay for PWA
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowWindowControlsOverlay() {
            return client.call("Overlay.setShowWindowControlsOverlay", null);
        }
        /**
         * Show Window Controls Overlay for PWA
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowWindowControlsOverlay(SetShowWindowControlsOverlayRequest request) {
            return client.call("Overlay.setShowWindowControlsOverlay", request == null ? null : request.toMap());
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
