// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalLong;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nonnull;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;
import net.kurobako.cef4j.cdp.CdpValue;

/**
 * This domain provides various functionality related to drawing atop the inspected page.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/Overlay.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
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
            return java.util.Objects.requireNonNull(DOM.RGBA.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("parentOutlineColor")))));
        }
        /**
         * the color to outline the child elements in.
         * @return the protocol field value
         */
        public DOM.RGBA childOutlineColor() {
            return java.util.Objects.requireNonNull(DOM.RGBA.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("childOutlineColor")))));
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
            return Optional.ofNullable((Boolean) raw("showGridExtensionLines"));
        }
        /**
         * Show Positive line number labels (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showPositiveLineNumbers() {
            return Optional.ofNullable((Boolean) raw("showPositiveLineNumbers"));
        }
        /**
         * Show Negative line number labels (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showNegativeLineNumbers() {
            return Optional.ofNullable((Boolean) raw("showNegativeLineNumbers"));
        }
        /**
         * Show area name labels (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showAreaNames() {
            return Optional.ofNullable((Boolean) raw("showAreaNames"));
        }
        /**
         * Show line name labels (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showLineNames() {
            return Optional.ofNullable((Boolean) raw("showLineNames"));
        }
        /**
         * Show track size labels (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showTrackSizes() {
            return Optional.ofNullable((Boolean) raw("showTrackSizes"));
        }
        /**
         * The grid container border highlight color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> gridBorderColor() {
            return Optional.ofNullable(raw("gridBorderColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("gridBorderColor")))));
        }
        /**
         * The cell border color (default: transparent). Deprecated, please use rowLineColor and columnLineColor instead.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<DOM.RGBA> cellBorderColor() {
            return Optional.ofNullable(raw("cellBorderColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("cellBorderColor")))));
        }
        /**
         * The row line color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> rowLineColor() {
            return Optional.ofNullable(raw("rowLineColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("rowLineColor")))));
        }
        /**
         * The column line color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> columnLineColor() {
            return Optional.ofNullable(raw("columnLineColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("columnLineColor")))));
        }
        /**
         * Whether the grid border is dashed (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> gridBorderDash() {
            return Optional.ofNullable((Boolean) raw("gridBorderDash"));
        }
        /**
         * Whether the cell border is dashed (default: false). Deprecated, please us rowLineDash and columnLineDash instead.
         * @return the protocol field value, empty when absent
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public Optional<Boolean> cellBorderDash() {
            return Optional.ofNullable((Boolean) raw("cellBorderDash"));
        }
        /**
         * Whether row lines are dashed (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> rowLineDash() {
            return Optional.ofNullable((Boolean) raw("rowLineDash"));
        }
        /**
         * Whether column lines are dashed (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> columnLineDash() {
            return Optional.ofNullable((Boolean) raw("columnLineDash"));
        }
        /**
         * The row gap highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> rowGapColor() {
            return Optional.ofNullable(raw("rowGapColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("rowGapColor")))));
        }
        /**
         * The row gap hatching fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> rowHatchColor() {
            return Optional.ofNullable(raw("rowHatchColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("rowHatchColor")))));
        }
        /**
         * The column gap highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> columnGapColor() {
            return Optional.ofNullable(raw("columnGapColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("columnGapColor")))));
        }
        /**
         * The column gap hatching fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> columnHatchColor() {
            return Optional.ofNullable(raw("columnHatchColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("columnHatchColor")))));
        }
        /**
         * The named grid areas border color (Default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> areaBorderColor() {
            return Optional.ofNullable(raw("areaBorderColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("areaBorderColor")))));
        }
        /**
         * The grid container background color (Default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> gridBackgroundColor() {
            return Optional.ofNullable(raw("gridBackgroundColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("gridBackgroundColor")))));
        }
        /**
         * Whether the extension lines from grid cells to the rulers should be shown (default: false).
         * @param showGridExtensionLines field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig showGridExtensionLines(Optional<Boolean> showGridExtensionLines) {
            set("showGridExtensionLines", showGridExtensionLines.orElse(null));
            return this;
        }
        /**
         * Whether the extension lines from grid cells to the rulers should be shown (default: false).
         * @param showGridExtensionLines field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig showGridExtensionLines(Boolean showGridExtensionLines) {
            set("showGridExtensionLines", showGridExtensionLines);
            return this;
        }
        /**
         * Show Positive line number labels (default: false).
         * @param showPositiveLineNumbers field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig showPositiveLineNumbers(Optional<Boolean> showPositiveLineNumbers) {
            set("showPositiveLineNumbers", showPositiveLineNumbers.orElse(null));
            return this;
        }
        /**
         * Show Positive line number labels (default: false).
         * @param showPositiveLineNumbers field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig showPositiveLineNumbers(Boolean showPositiveLineNumbers) {
            set("showPositiveLineNumbers", showPositiveLineNumbers);
            return this;
        }
        /**
         * Show Negative line number labels (default: false).
         * @param showNegativeLineNumbers field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig showNegativeLineNumbers(Optional<Boolean> showNegativeLineNumbers) {
            set("showNegativeLineNumbers", showNegativeLineNumbers.orElse(null));
            return this;
        }
        /**
         * Show Negative line number labels (default: false).
         * @param showNegativeLineNumbers field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig showNegativeLineNumbers(Boolean showNegativeLineNumbers) {
            set("showNegativeLineNumbers", showNegativeLineNumbers);
            return this;
        }
        /**
         * Show area name labels (default: false).
         * @param showAreaNames field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig showAreaNames(Optional<Boolean> showAreaNames) {
            set("showAreaNames", showAreaNames.orElse(null));
            return this;
        }
        /**
         * Show area name labels (default: false).
         * @param showAreaNames field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig showAreaNames(Boolean showAreaNames) {
            set("showAreaNames", showAreaNames);
            return this;
        }
        /**
         * Show line name labels (default: false).
         * @param showLineNames field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig showLineNames(Optional<Boolean> showLineNames) {
            set("showLineNames", showLineNames.orElse(null));
            return this;
        }
        /**
         * Show line name labels (default: false).
         * @param showLineNames field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig showLineNames(Boolean showLineNames) {
            set("showLineNames", showLineNames);
            return this;
        }
        /**
         * Show track size labels (default: false).
         * @param showTrackSizes field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig showTrackSizes(Optional<Boolean> showTrackSizes) {
            set("showTrackSizes", showTrackSizes.orElse(null));
            return this;
        }
        /**
         * Show track size labels (default: false).
         * @param showTrackSizes field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig showTrackSizes(Boolean showTrackSizes) {
            set("showTrackSizes", showTrackSizes);
            return this;
        }
        /**
         * The grid container border highlight color (default: transparent).
         * @param gridBorderColor field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig gridBorderColor(Optional<DOM.RGBA> gridBorderColor) {
            set("gridBorderColor", gridBorderColor.orElse(null));
            return this;
        }
        /**
         * The grid container border highlight color (default: transparent).
         * @param gridBorderColor field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig gridBorderColor(DOM.RGBA gridBorderColor) {
            set("gridBorderColor", gridBorderColor);
            return this;
        }
        /**
         * The cell border color (default: transparent). Deprecated, please use rowLineColor and columnLineColor instead.
         * @param cellBorderColor field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public GridHighlightConfig cellBorderColor(Optional<DOM.RGBA> cellBorderColor) {
            set("cellBorderColor", cellBorderColor.orElse(null));
            return this;
        }
        /**
         * The cell border color (default: transparent). Deprecated, please use rowLineColor and columnLineColor instead.
         * @param cellBorderColor field value; null removes the value
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
         * @param rowLineColor field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig rowLineColor(Optional<DOM.RGBA> rowLineColor) {
            set("rowLineColor", rowLineColor.orElse(null));
            return this;
        }
        /**
         * The row line color (default: transparent).
         * @param rowLineColor field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig rowLineColor(DOM.RGBA rowLineColor) {
            set("rowLineColor", rowLineColor);
            return this;
        }
        /**
         * The column line color (default: transparent).
         * @param columnLineColor field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig columnLineColor(Optional<DOM.RGBA> columnLineColor) {
            set("columnLineColor", columnLineColor.orElse(null));
            return this;
        }
        /**
         * The column line color (default: transparent).
         * @param columnLineColor field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig columnLineColor(DOM.RGBA columnLineColor) {
            set("columnLineColor", columnLineColor);
            return this;
        }
        /**
         * Whether the grid border is dashed (default: false).
         * @param gridBorderDash field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig gridBorderDash(Optional<Boolean> gridBorderDash) {
            set("gridBorderDash", gridBorderDash.orElse(null));
            return this;
        }
        /**
         * Whether the grid border is dashed (default: false).
         * @param gridBorderDash field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig gridBorderDash(Boolean gridBorderDash) {
            set("gridBorderDash", gridBorderDash);
            return this;
        }
        /**
         * Whether the cell border is dashed (default: false). Deprecated, please us rowLineDash and columnLineDash instead.
         * @param cellBorderDash field value; empty omits the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public GridHighlightConfig cellBorderDash(Optional<Boolean> cellBorderDash) {
            set("cellBorderDash", cellBorderDash.orElse(null));
            return this;
        }
        /**
         * Whether the cell border is dashed (default: false). Deprecated, please us rowLineDash and columnLineDash instead.
         * @param cellBorderDash field value; null removes the value
         * @return this model
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public GridHighlightConfig cellBorderDash(Boolean cellBorderDash) {
            set("cellBorderDash", cellBorderDash);
            return this;
        }
        /**
         * Whether row lines are dashed (default: false).
         * @param rowLineDash field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig rowLineDash(Optional<Boolean> rowLineDash) {
            set("rowLineDash", rowLineDash.orElse(null));
            return this;
        }
        /**
         * Whether row lines are dashed (default: false).
         * @param rowLineDash field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig rowLineDash(Boolean rowLineDash) {
            set("rowLineDash", rowLineDash);
            return this;
        }
        /**
         * Whether column lines are dashed (default: false).
         * @param columnLineDash field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig columnLineDash(Optional<Boolean> columnLineDash) {
            set("columnLineDash", columnLineDash.orElse(null));
            return this;
        }
        /**
         * Whether column lines are dashed (default: false).
         * @param columnLineDash field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig columnLineDash(Boolean columnLineDash) {
            set("columnLineDash", columnLineDash);
            return this;
        }
        /**
         * The row gap highlight fill color (default: transparent).
         * @param rowGapColor field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig rowGapColor(Optional<DOM.RGBA> rowGapColor) {
            set("rowGapColor", rowGapColor.orElse(null));
            return this;
        }
        /**
         * The row gap highlight fill color (default: transparent).
         * @param rowGapColor field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig rowGapColor(DOM.RGBA rowGapColor) {
            set("rowGapColor", rowGapColor);
            return this;
        }
        /**
         * The row gap hatching fill color (default: transparent).
         * @param rowHatchColor field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig rowHatchColor(Optional<DOM.RGBA> rowHatchColor) {
            set("rowHatchColor", rowHatchColor.orElse(null));
            return this;
        }
        /**
         * The row gap hatching fill color (default: transparent).
         * @param rowHatchColor field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig rowHatchColor(DOM.RGBA rowHatchColor) {
            set("rowHatchColor", rowHatchColor);
            return this;
        }
        /**
         * The column gap highlight fill color (default: transparent).
         * @param columnGapColor field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig columnGapColor(Optional<DOM.RGBA> columnGapColor) {
            set("columnGapColor", columnGapColor.orElse(null));
            return this;
        }
        /**
         * The column gap highlight fill color (default: transparent).
         * @param columnGapColor field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig columnGapColor(DOM.RGBA columnGapColor) {
            set("columnGapColor", columnGapColor);
            return this;
        }
        /**
         * The column gap hatching fill color (default: transparent).
         * @param columnHatchColor field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig columnHatchColor(Optional<DOM.RGBA> columnHatchColor) {
            set("columnHatchColor", columnHatchColor.orElse(null));
            return this;
        }
        /**
         * The column gap hatching fill color (default: transparent).
         * @param columnHatchColor field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig columnHatchColor(DOM.RGBA columnHatchColor) {
            set("columnHatchColor", columnHatchColor);
            return this;
        }
        /**
         * The named grid areas border color (Default: transparent).
         * @param areaBorderColor field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig areaBorderColor(Optional<DOM.RGBA> areaBorderColor) {
            set("areaBorderColor", areaBorderColor.orElse(null));
            return this;
        }
        /**
         * The named grid areas border color (Default: transparent).
         * @param areaBorderColor field value; null removes the value
         * @return this model
         */
        public GridHighlightConfig areaBorderColor(DOM.RGBA areaBorderColor) {
            set("areaBorderColor", areaBorderColor);
            return this;
        }
        /**
         * The grid container background color (Default: transparent).
         * @param gridBackgroundColor field value; empty omits the value
         * @return this model
         */
        public GridHighlightConfig gridBackgroundColor(Optional<DOM.RGBA> gridBackgroundColor) {
            set("gridBackgroundColor", gridBackgroundColor.orElse(null));
            return this;
        }
        /**
         * The grid container background color (Default: transparent).
         * @param gridBackgroundColor field value; null removes the value
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
            return Optional.ofNullable(raw("containerBorder") == null ? null : Overlay.LineStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("containerBorder")))));
        }
        /**
         * The style of the separator between lines
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> lineSeparator() {
            return Optional.ofNullable(raw("lineSeparator") == null ? null : Overlay.LineStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("lineSeparator")))));
        }
        /**
         * The style of the separator between items
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> itemSeparator() {
            return Optional.ofNullable(raw("itemSeparator") == null ? null : Overlay.LineStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("itemSeparator")))));
        }
        /**
         * Style of content-distribution space on the main axis (justify-content).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.BoxStyle> mainDistributedSpace() {
            return Optional.ofNullable(raw("mainDistributedSpace") == null ? null : Overlay.BoxStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("mainDistributedSpace")))));
        }
        /**
         * Style of content-distribution space on the cross axis (align-content).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.BoxStyle> crossDistributedSpace() {
            return Optional.ofNullable(raw("crossDistributedSpace") == null ? null : Overlay.BoxStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("crossDistributedSpace")))));
        }
        /**
         * Style of empty space caused by row gaps (gap/row-gap).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.BoxStyle> rowGapSpace() {
            return Optional.ofNullable(raw("rowGapSpace") == null ? null : Overlay.BoxStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("rowGapSpace")))));
        }
        /**
         * Style of empty space caused by columns gaps (gap/column-gap).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.BoxStyle> columnGapSpace() {
            return Optional.ofNullable(raw("columnGapSpace") == null ? null : Overlay.BoxStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("columnGapSpace")))));
        }
        /**
         * Style of the self-alignment line (align-items).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> crossAlignment() {
            return Optional.ofNullable(raw("crossAlignment") == null ? null : Overlay.LineStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("crossAlignment")))));
        }
        /**
         * The style of the container border
         * @param containerBorder field value; empty omits the value
         * @return this model
         */
        public FlexContainerHighlightConfig containerBorder(Optional<Overlay.LineStyle> containerBorder) {
            set("containerBorder", containerBorder.orElse(null));
            return this;
        }
        /**
         * The style of the container border
         * @param containerBorder field value; null removes the value
         * @return this model
         */
        public FlexContainerHighlightConfig containerBorder(Overlay.LineStyle containerBorder) {
            set("containerBorder", containerBorder);
            return this;
        }
        /**
         * The style of the separator between lines
         * @param lineSeparator field value; empty omits the value
         * @return this model
         */
        public FlexContainerHighlightConfig lineSeparator(Optional<Overlay.LineStyle> lineSeparator) {
            set("lineSeparator", lineSeparator.orElse(null));
            return this;
        }
        /**
         * The style of the separator between lines
         * @param lineSeparator field value; null removes the value
         * @return this model
         */
        public FlexContainerHighlightConfig lineSeparator(Overlay.LineStyle lineSeparator) {
            set("lineSeparator", lineSeparator);
            return this;
        }
        /**
         * The style of the separator between items
         * @param itemSeparator field value; empty omits the value
         * @return this model
         */
        public FlexContainerHighlightConfig itemSeparator(Optional<Overlay.LineStyle> itemSeparator) {
            set("itemSeparator", itemSeparator.orElse(null));
            return this;
        }
        /**
         * The style of the separator between items
         * @param itemSeparator field value; null removes the value
         * @return this model
         */
        public FlexContainerHighlightConfig itemSeparator(Overlay.LineStyle itemSeparator) {
            set("itemSeparator", itemSeparator);
            return this;
        }
        /**
         * Style of content-distribution space on the main axis (justify-content).
         * @param mainDistributedSpace field value; empty omits the value
         * @return this model
         */
        public FlexContainerHighlightConfig mainDistributedSpace(Optional<Overlay.BoxStyle> mainDistributedSpace) {
            set("mainDistributedSpace", mainDistributedSpace.orElse(null));
            return this;
        }
        /**
         * Style of content-distribution space on the main axis (justify-content).
         * @param mainDistributedSpace field value; null removes the value
         * @return this model
         */
        public FlexContainerHighlightConfig mainDistributedSpace(Overlay.BoxStyle mainDistributedSpace) {
            set("mainDistributedSpace", mainDistributedSpace);
            return this;
        }
        /**
         * Style of content-distribution space on the cross axis (align-content).
         * @param crossDistributedSpace field value; empty omits the value
         * @return this model
         */
        public FlexContainerHighlightConfig crossDistributedSpace(Optional<Overlay.BoxStyle> crossDistributedSpace) {
            set("crossDistributedSpace", crossDistributedSpace.orElse(null));
            return this;
        }
        /**
         * Style of content-distribution space on the cross axis (align-content).
         * @param crossDistributedSpace field value; null removes the value
         * @return this model
         */
        public FlexContainerHighlightConfig crossDistributedSpace(Overlay.BoxStyle crossDistributedSpace) {
            set("crossDistributedSpace", crossDistributedSpace);
            return this;
        }
        /**
         * Style of empty space caused by row gaps (gap/row-gap).
         * @param rowGapSpace field value; empty omits the value
         * @return this model
         */
        public FlexContainerHighlightConfig rowGapSpace(Optional<Overlay.BoxStyle> rowGapSpace) {
            set("rowGapSpace", rowGapSpace.orElse(null));
            return this;
        }
        /**
         * Style of empty space caused by row gaps (gap/row-gap).
         * @param rowGapSpace field value; null removes the value
         * @return this model
         */
        public FlexContainerHighlightConfig rowGapSpace(Overlay.BoxStyle rowGapSpace) {
            set("rowGapSpace", rowGapSpace);
            return this;
        }
        /**
         * Style of empty space caused by columns gaps (gap/column-gap).
         * @param columnGapSpace field value; empty omits the value
         * @return this model
         */
        public FlexContainerHighlightConfig columnGapSpace(Optional<Overlay.BoxStyle> columnGapSpace) {
            set("columnGapSpace", columnGapSpace.orElse(null));
            return this;
        }
        /**
         * Style of empty space caused by columns gaps (gap/column-gap).
         * @param columnGapSpace field value; null removes the value
         * @return this model
         */
        public FlexContainerHighlightConfig columnGapSpace(Overlay.BoxStyle columnGapSpace) {
            set("columnGapSpace", columnGapSpace);
            return this;
        }
        /**
         * Style of the self-alignment line (align-items).
         * @param crossAlignment field value; empty omits the value
         * @return this model
         */
        public FlexContainerHighlightConfig crossAlignment(Optional<Overlay.LineStyle> crossAlignment) {
            set("crossAlignment", crossAlignment.orElse(null));
            return this;
        }
        /**
         * Style of the self-alignment line (align-items).
         * @param crossAlignment field value; null removes the value
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
            return Optional.ofNullable(raw("baseSizeBox") == null ? null : Overlay.BoxStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("baseSizeBox")))));
        }
        /**
         * Style of the border around the box representing the item&#x27;s base size
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> baseSizeBorder() {
            return Optional.ofNullable(raw("baseSizeBorder") == null ? null : Overlay.LineStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("baseSizeBorder")))));
        }
        /**
         * Style of the arrow representing if the item grew or shrank
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> flexibilityArrow() {
            return Optional.ofNullable(raw("flexibilityArrow") == null ? null : Overlay.LineStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("flexibilityArrow")))));
        }
        /**
         * Style of the box representing the item&#x27;s base size
         * @param baseSizeBox field value; empty omits the value
         * @return this model
         */
        public FlexItemHighlightConfig baseSizeBox(Optional<Overlay.BoxStyle> baseSizeBox) {
            set("baseSizeBox", baseSizeBox.orElse(null));
            return this;
        }
        /**
         * Style of the box representing the item&#x27;s base size
         * @param baseSizeBox field value; null removes the value
         * @return this model
         */
        public FlexItemHighlightConfig baseSizeBox(Overlay.BoxStyle baseSizeBox) {
            set("baseSizeBox", baseSizeBox);
            return this;
        }
        /**
         * Style of the border around the box representing the item&#x27;s base size
         * @param baseSizeBorder field value; empty omits the value
         * @return this model
         */
        public FlexItemHighlightConfig baseSizeBorder(Optional<Overlay.LineStyle> baseSizeBorder) {
            set("baseSizeBorder", baseSizeBorder.orElse(null));
            return this;
        }
        /**
         * Style of the border around the box representing the item&#x27;s base size
         * @param baseSizeBorder field value; null removes the value
         * @return this model
         */
        public FlexItemHighlightConfig baseSizeBorder(Overlay.LineStyle baseSizeBorder) {
            set("baseSizeBorder", baseSizeBorder);
            return this;
        }
        /**
         * Style of the arrow representing if the item grew or shrank
         * @param flexibilityArrow field value; empty omits the value
         * @return this model
         */
        public FlexItemHighlightConfig flexibilityArrow(Optional<Overlay.LineStyle> flexibilityArrow) {
            set("flexibilityArrow", flexibilityArrow.orElse(null));
            return this;
        }
        /**
         * Style of the arrow representing if the item grew or shrank
         * @param flexibilityArrow field value; null removes the value
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
            @Nonnull public String value() { return value; }
            public static PatternValues of(@Nonnull String value) {
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
            return Optional.ofNullable(raw("color") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("color")))));
        }
        /**
         * The line pattern (default: solid)
         * @return the protocol field value, empty when absent
         */
        public Optional<LineStyle.PatternValues> pattern() {
            return Optional.ofNullable(raw("pattern") == null ? null : LineStyle.PatternValues.of((String) raw("pattern")));
        }
        /**
         * The color of the line (default: transparent)
         * @param color field value; empty omits the value
         * @return this model
         */
        public LineStyle color(Optional<DOM.RGBA> color) {
            set("color", color.orElse(null));
            return this;
        }
        /**
         * The color of the line (default: transparent)
         * @param color field value; null removes the value
         * @return this model
         */
        public LineStyle color(DOM.RGBA color) {
            set("color", color);
            return this;
        }
        /**
         * The line pattern (default: solid)
         * @param pattern field value; empty omits the value
         * @return this model
         */
        public LineStyle pattern(Optional<LineStyle.PatternValues> pattern) {
            set("pattern", pattern.orElse(null));
            return this;
        }
        /**
         * The line pattern (default: solid)
         * @param pattern field value; null removes the value
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
            return Optional.ofNullable(raw("fillColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("fillColor")))));
        }
        /**
         * The hatching color for the box (default: transparent)
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> hatchColor() {
            return Optional.ofNullable(raw("hatchColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("hatchColor")))));
        }
        /**
         * The background color for the box (default: transparent)
         * @param fillColor field value; empty omits the value
         * @return this model
         */
        public BoxStyle fillColor(Optional<DOM.RGBA> fillColor) {
            set("fillColor", fillColor.orElse(null));
            return this;
        }
        /**
         * The background color for the box (default: transparent)
         * @param fillColor field value; null removes the value
         * @return this model
         */
        public BoxStyle fillColor(DOM.RGBA fillColor) {
            set("fillColor", fillColor);
            return this;
        }
        /**
         * The hatching color for the box (default: transparent)
         * @param hatchColor field value; empty omits the value
         * @return this model
         */
        public BoxStyle hatchColor(Optional<DOM.RGBA> hatchColor) {
            set("hatchColor", hatchColor.orElse(null));
            return this;
        }
        /**
         * The hatching color for the box (default: transparent)
         * @param hatchColor field value; null removes the value
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
        @Nonnull public String value() { return value; }
        public static ContrastAlgorithm of(@Nonnull String value) {
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
            return Optional.ofNullable((Boolean) raw("showInfo"));
        }
        /**
         * Whether the node styles in the tooltip (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showStyles() {
            return Optional.ofNullable((Boolean) raw("showStyles"));
        }
        /**
         * Whether the rulers should be shown (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showRulers() {
            return Optional.ofNullable((Boolean) raw("showRulers"));
        }
        /**
         * Whether the a11y info should be shown (default: true).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showAccessibilityInfo() {
            return Optional.ofNullable((Boolean) raw("showAccessibilityInfo"));
        }
        /**
         * Whether the extension lines from node to the rulers should be shown (default: false).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showExtensionLines() {
            return Optional.ofNullable((Boolean) raw("showExtensionLines"));
        }
        /**
         * The content box highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> contentColor() {
            return Optional.ofNullable(raw("contentColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("contentColor")))));
        }
        /**
         * The padding highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> paddingColor() {
            return Optional.ofNullable(raw("paddingColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("paddingColor")))));
        }
        /**
         * The border highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> borderColor() {
            return Optional.ofNullable(raw("borderColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("borderColor")))));
        }
        /**
         * The margin highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> marginColor() {
            return Optional.ofNullable(raw("marginColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("marginColor")))));
        }
        /**
         * The event target element highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> eventTargetColor() {
            return Optional.ofNullable(raw("eventTargetColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("eventTargetColor")))));
        }
        /**
         * The shape outside fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> shapeColor() {
            return Optional.ofNullable(raw("shapeColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("shapeColor")))));
        }
        /**
         * The shape margin fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> shapeMarginColor() {
            return Optional.ofNullable(raw("shapeMarginColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("shapeMarginColor")))));
        }
        /**
         * The grid layout color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> cssGridColor() {
            return Optional.ofNullable(raw("cssGridColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("cssGridColor")))));
        }
        /**
         * The color format used to format color styles (default: hex).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.ColorFormat> colorFormat() {
            return Optional.ofNullable(raw("colorFormat") == null ? null : Overlay.ColorFormat.of((String) raw("colorFormat")));
        }
        /**
         * The grid layout highlight configuration (default: all transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.GridHighlightConfig> gridHighlightConfig() {
            return Optional.ofNullable(raw("gridHighlightConfig") == null ? null : Overlay.GridHighlightConfig.fromMap(java.util.Objects.requireNonNull(objectMap(raw("gridHighlightConfig")))));
        }
        /**
         * The flex container highlight configuration (default: all transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.FlexContainerHighlightConfig> flexContainerHighlightConfig() {
            return Optional.ofNullable(raw("flexContainerHighlightConfig") == null ? null : Overlay.FlexContainerHighlightConfig.fromMap(java.util.Objects.requireNonNull(objectMap(raw("flexContainerHighlightConfig")))));
        }
        /**
         * The flex item highlight configuration (default: all transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.FlexItemHighlightConfig> flexItemHighlightConfig() {
            return Optional.ofNullable(raw("flexItemHighlightConfig") == null ? null : Overlay.FlexItemHighlightConfig.fromMap(java.util.Objects.requireNonNull(objectMap(raw("flexItemHighlightConfig")))));
        }
        /**
         * The contrast algorithm to use for the contrast ratio (default: aa).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.ContrastAlgorithm> contrastAlgorithm() {
            return Optional.ofNullable(raw("contrastAlgorithm") == null ? null : Overlay.ContrastAlgorithm.of((String) raw("contrastAlgorithm")));
        }
        /**
         * The container query container highlight configuration (default: all transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.ContainerQueryContainerHighlightConfig> containerQueryContainerHighlightConfig() {
            return Optional.ofNullable(raw("containerQueryContainerHighlightConfig") == null ? null : Overlay.ContainerQueryContainerHighlightConfig.fromMap(java.util.Objects.requireNonNull(objectMap(raw("containerQueryContainerHighlightConfig")))));
        }
        /**
         * Whether the node info tooltip should be shown (default: false).
         * @param showInfo field value; empty omits the value
         * @return this model
         */
        public HighlightConfig showInfo(Optional<Boolean> showInfo) {
            set("showInfo", showInfo.orElse(null));
            return this;
        }
        /**
         * Whether the node info tooltip should be shown (default: false).
         * @param showInfo field value; null removes the value
         * @return this model
         */
        public HighlightConfig showInfo(Boolean showInfo) {
            set("showInfo", showInfo);
            return this;
        }
        /**
         * Whether the node styles in the tooltip (default: false).
         * @param showStyles field value; empty omits the value
         * @return this model
         */
        public HighlightConfig showStyles(Optional<Boolean> showStyles) {
            set("showStyles", showStyles.orElse(null));
            return this;
        }
        /**
         * Whether the node styles in the tooltip (default: false).
         * @param showStyles field value; null removes the value
         * @return this model
         */
        public HighlightConfig showStyles(Boolean showStyles) {
            set("showStyles", showStyles);
            return this;
        }
        /**
         * Whether the rulers should be shown (default: false).
         * @param showRulers field value; empty omits the value
         * @return this model
         */
        public HighlightConfig showRulers(Optional<Boolean> showRulers) {
            set("showRulers", showRulers.orElse(null));
            return this;
        }
        /**
         * Whether the rulers should be shown (default: false).
         * @param showRulers field value; null removes the value
         * @return this model
         */
        public HighlightConfig showRulers(Boolean showRulers) {
            set("showRulers", showRulers);
            return this;
        }
        /**
         * Whether the a11y info should be shown (default: true).
         * @param showAccessibilityInfo field value; empty omits the value
         * @return this model
         */
        public HighlightConfig showAccessibilityInfo(Optional<Boolean> showAccessibilityInfo) {
            set("showAccessibilityInfo", showAccessibilityInfo.orElse(null));
            return this;
        }
        /**
         * Whether the a11y info should be shown (default: true).
         * @param showAccessibilityInfo field value; null removes the value
         * @return this model
         */
        public HighlightConfig showAccessibilityInfo(Boolean showAccessibilityInfo) {
            set("showAccessibilityInfo", showAccessibilityInfo);
            return this;
        }
        /**
         * Whether the extension lines from node to the rulers should be shown (default: false).
         * @param showExtensionLines field value; empty omits the value
         * @return this model
         */
        public HighlightConfig showExtensionLines(Optional<Boolean> showExtensionLines) {
            set("showExtensionLines", showExtensionLines.orElse(null));
            return this;
        }
        /**
         * Whether the extension lines from node to the rulers should be shown (default: false).
         * @param showExtensionLines field value; null removes the value
         * @return this model
         */
        public HighlightConfig showExtensionLines(Boolean showExtensionLines) {
            set("showExtensionLines", showExtensionLines);
            return this;
        }
        /**
         * The content box highlight fill color (default: transparent).
         * @param contentColor field value; empty omits the value
         * @return this model
         */
        public HighlightConfig contentColor(Optional<DOM.RGBA> contentColor) {
            set("contentColor", contentColor.orElse(null));
            return this;
        }
        /**
         * The content box highlight fill color (default: transparent).
         * @param contentColor field value; null removes the value
         * @return this model
         */
        public HighlightConfig contentColor(DOM.RGBA contentColor) {
            set("contentColor", contentColor);
            return this;
        }
        /**
         * The padding highlight fill color (default: transparent).
         * @param paddingColor field value; empty omits the value
         * @return this model
         */
        public HighlightConfig paddingColor(Optional<DOM.RGBA> paddingColor) {
            set("paddingColor", paddingColor.orElse(null));
            return this;
        }
        /**
         * The padding highlight fill color (default: transparent).
         * @param paddingColor field value; null removes the value
         * @return this model
         */
        public HighlightConfig paddingColor(DOM.RGBA paddingColor) {
            set("paddingColor", paddingColor);
            return this;
        }
        /**
         * The border highlight fill color (default: transparent).
         * @param borderColor field value; empty omits the value
         * @return this model
         */
        public HighlightConfig borderColor(Optional<DOM.RGBA> borderColor) {
            set("borderColor", borderColor.orElse(null));
            return this;
        }
        /**
         * The border highlight fill color (default: transparent).
         * @param borderColor field value; null removes the value
         * @return this model
         */
        public HighlightConfig borderColor(DOM.RGBA borderColor) {
            set("borderColor", borderColor);
            return this;
        }
        /**
         * The margin highlight fill color (default: transparent).
         * @param marginColor field value; empty omits the value
         * @return this model
         */
        public HighlightConfig marginColor(Optional<DOM.RGBA> marginColor) {
            set("marginColor", marginColor.orElse(null));
            return this;
        }
        /**
         * The margin highlight fill color (default: transparent).
         * @param marginColor field value; null removes the value
         * @return this model
         */
        public HighlightConfig marginColor(DOM.RGBA marginColor) {
            set("marginColor", marginColor);
            return this;
        }
        /**
         * The event target element highlight fill color (default: transparent).
         * @param eventTargetColor field value; empty omits the value
         * @return this model
         */
        public HighlightConfig eventTargetColor(Optional<DOM.RGBA> eventTargetColor) {
            set("eventTargetColor", eventTargetColor.orElse(null));
            return this;
        }
        /**
         * The event target element highlight fill color (default: transparent).
         * @param eventTargetColor field value; null removes the value
         * @return this model
         */
        public HighlightConfig eventTargetColor(DOM.RGBA eventTargetColor) {
            set("eventTargetColor", eventTargetColor);
            return this;
        }
        /**
         * The shape outside fill color (default: transparent).
         * @param shapeColor field value; empty omits the value
         * @return this model
         */
        public HighlightConfig shapeColor(Optional<DOM.RGBA> shapeColor) {
            set("shapeColor", shapeColor.orElse(null));
            return this;
        }
        /**
         * The shape outside fill color (default: transparent).
         * @param shapeColor field value; null removes the value
         * @return this model
         */
        public HighlightConfig shapeColor(DOM.RGBA shapeColor) {
            set("shapeColor", shapeColor);
            return this;
        }
        /**
         * The shape margin fill color (default: transparent).
         * @param shapeMarginColor field value; empty omits the value
         * @return this model
         */
        public HighlightConfig shapeMarginColor(Optional<DOM.RGBA> shapeMarginColor) {
            set("shapeMarginColor", shapeMarginColor.orElse(null));
            return this;
        }
        /**
         * The shape margin fill color (default: transparent).
         * @param shapeMarginColor field value; null removes the value
         * @return this model
         */
        public HighlightConfig shapeMarginColor(DOM.RGBA shapeMarginColor) {
            set("shapeMarginColor", shapeMarginColor);
            return this;
        }
        /**
         * The grid layout color (default: transparent).
         * @param cssGridColor field value; empty omits the value
         * @return this model
         */
        public HighlightConfig cssGridColor(Optional<DOM.RGBA> cssGridColor) {
            set("cssGridColor", cssGridColor.orElse(null));
            return this;
        }
        /**
         * The grid layout color (default: transparent).
         * @param cssGridColor field value; null removes the value
         * @return this model
         */
        public HighlightConfig cssGridColor(DOM.RGBA cssGridColor) {
            set("cssGridColor", cssGridColor);
            return this;
        }
        /**
         * The color format used to format color styles (default: hex).
         * @param colorFormat field value; empty omits the value
         * @return this model
         */
        public HighlightConfig colorFormat(Optional<Overlay.ColorFormat> colorFormat) {
            set("colorFormat", colorFormat.orElse(null));
            return this;
        }
        /**
         * The color format used to format color styles (default: hex).
         * @param colorFormat field value; null removes the value
         * @return this model
         */
        public HighlightConfig colorFormat(Overlay.ColorFormat colorFormat) {
            set("colorFormat", colorFormat);
            return this;
        }
        /**
         * The grid layout highlight configuration (default: all transparent).
         * @param gridHighlightConfig field value; empty omits the value
         * @return this model
         */
        public HighlightConfig gridHighlightConfig(Optional<Overlay.GridHighlightConfig> gridHighlightConfig) {
            set("gridHighlightConfig", gridHighlightConfig.orElse(null));
            return this;
        }
        /**
         * The grid layout highlight configuration (default: all transparent).
         * @param gridHighlightConfig field value; null removes the value
         * @return this model
         */
        public HighlightConfig gridHighlightConfig(Overlay.GridHighlightConfig gridHighlightConfig) {
            set("gridHighlightConfig", gridHighlightConfig);
            return this;
        }
        /**
         * The flex container highlight configuration (default: all transparent).
         * @param flexContainerHighlightConfig field value; empty omits the value
         * @return this model
         */
        public HighlightConfig flexContainerHighlightConfig(Optional<Overlay.FlexContainerHighlightConfig> flexContainerHighlightConfig) {
            set("flexContainerHighlightConfig", flexContainerHighlightConfig.orElse(null));
            return this;
        }
        /**
         * The flex container highlight configuration (default: all transparent).
         * @param flexContainerHighlightConfig field value; null removes the value
         * @return this model
         */
        public HighlightConfig flexContainerHighlightConfig(Overlay.FlexContainerHighlightConfig flexContainerHighlightConfig) {
            set("flexContainerHighlightConfig", flexContainerHighlightConfig);
            return this;
        }
        /**
         * The flex item highlight configuration (default: all transparent).
         * @param flexItemHighlightConfig field value; empty omits the value
         * @return this model
         */
        public HighlightConfig flexItemHighlightConfig(Optional<Overlay.FlexItemHighlightConfig> flexItemHighlightConfig) {
            set("flexItemHighlightConfig", flexItemHighlightConfig.orElse(null));
            return this;
        }
        /**
         * The flex item highlight configuration (default: all transparent).
         * @param flexItemHighlightConfig field value; null removes the value
         * @return this model
         */
        public HighlightConfig flexItemHighlightConfig(Overlay.FlexItemHighlightConfig flexItemHighlightConfig) {
            set("flexItemHighlightConfig", flexItemHighlightConfig);
            return this;
        }
        /**
         * The contrast algorithm to use for the contrast ratio (default: aa).
         * @param contrastAlgorithm field value; empty omits the value
         * @return this model
         */
        public HighlightConfig contrastAlgorithm(Optional<Overlay.ContrastAlgorithm> contrastAlgorithm) {
            set("contrastAlgorithm", contrastAlgorithm.orElse(null));
            return this;
        }
        /**
         * The contrast algorithm to use for the contrast ratio (default: aa).
         * @param contrastAlgorithm field value; null removes the value
         * @return this model
         */
        public HighlightConfig contrastAlgorithm(Overlay.ContrastAlgorithm contrastAlgorithm) {
            set("contrastAlgorithm", contrastAlgorithm);
            return this;
        }
        /**
         * The container query container highlight configuration (default: all transparent).
         * @param containerQueryContainerHighlightConfig field value; empty omits the value
         * @return this model
         */
        public HighlightConfig containerQueryContainerHighlightConfig(Optional<Overlay.ContainerQueryContainerHighlightConfig> containerQueryContainerHighlightConfig) {
            set("containerQueryContainerHighlightConfig", containerQueryContainerHighlightConfig.orElse(null));
            return this;
        }
        /**
         * The container query container highlight configuration (default: all transparent).
         * @param containerQueryContainerHighlightConfig field value; null removes the value
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
        @Nonnull public String value() { return value; }
        public static ColorFormat of(@Nonnull String value) {
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
            return java.util.Objects.requireNonNull(Overlay.GridHighlightConfig.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("gridHighlightConfig")))));
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
            return java.util.Objects.requireNonNull(Overlay.FlexContainerHighlightConfig.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("flexContainerHighlightConfig")))));
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
            return Optional.ofNullable(raw("snapportBorder") == null ? null : Overlay.LineStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("snapportBorder")))));
        }
        /**
         * The style of the snap area border (default: transparent)
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> snapAreaBorder() {
            return Optional.ofNullable(raw("snapAreaBorder") == null ? null : Overlay.LineStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("snapAreaBorder")))));
        }
        /**
         * The margin highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> scrollMarginColor() {
            return Optional.ofNullable(raw("scrollMarginColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("scrollMarginColor")))));
        }
        /**
         * The padding highlight fill color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> scrollPaddingColor() {
            return Optional.ofNullable(raw("scrollPaddingColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("scrollPaddingColor")))));
        }
        /**
         * The style of the snapport border (default: transparent)
         * @param snapportBorder field value; empty omits the value
         * @return this model
         */
        public ScrollSnapContainerHighlightConfig snapportBorder(Optional<Overlay.LineStyle> snapportBorder) {
            set("snapportBorder", snapportBorder.orElse(null));
            return this;
        }
        /**
         * The style of the snapport border (default: transparent)
         * @param snapportBorder field value; null removes the value
         * @return this model
         */
        public ScrollSnapContainerHighlightConfig snapportBorder(Overlay.LineStyle snapportBorder) {
            set("snapportBorder", snapportBorder);
            return this;
        }
        /**
         * The style of the snap area border (default: transparent)
         * @param snapAreaBorder field value; empty omits the value
         * @return this model
         */
        public ScrollSnapContainerHighlightConfig snapAreaBorder(Optional<Overlay.LineStyle> snapAreaBorder) {
            set("snapAreaBorder", snapAreaBorder.orElse(null));
            return this;
        }
        /**
         * The style of the snap area border (default: transparent)
         * @param snapAreaBorder field value; null removes the value
         * @return this model
         */
        public ScrollSnapContainerHighlightConfig snapAreaBorder(Overlay.LineStyle snapAreaBorder) {
            set("snapAreaBorder", snapAreaBorder);
            return this;
        }
        /**
         * The margin highlight fill color (default: transparent).
         * @param scrollMarginColor field value; empty omits the value
         * @return this model
         */
        public ScrollSnapContainerHighlightConfig scrollMarginColor(Optional<DOM.RGBA> scrollMarginColor) {
            set("scrollMarginColor", scrollMarginColor.orElse(null));
            return this;
        }
        /**
         * The margin highlight fill color (default: transparent).
         * @param scrollMarginColor field value; null removes the value
         * @return this model
         */
        public ScrollSnapContainerHighlightConfig scrollMarginColor(DOM.RGBA scrollMarginColor) {
            set("scrollMarginColor", scrollMarginColor);
            return this;
        }
        /**
         * The padding highlight fill color (default: transparent).
         * @param scrollPaddingColor field value; empty omits the value
         * @return this model
         */
        public ScrollSnapContainerHighlightConfig scrollPaddingColor(Optional<DOM.RGBA> scrollPaddingColor) {
            set("scrollPaddingColor", scrollPaddingColor.orElse(null));
            return this;
        }
        /**
         * The padding highlight fill color (default: transparent).
         * @param scrollPaddingColor field value; null removes the value
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
            return java.util.Objects.requireNonNull(Overlay.ScrollSnapContainerHighlightConfig.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("scrollSnapContainerHighlightConfig")))));
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
            return java.util.Objects.requireNonNull(DOM.Rect.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("rect")))));
        }
        /**
         * The content box highlight fill color (default: a dark color).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> contentColor() {
            return Optional.ofNullable(raw("contentColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("contentColor")))));
        }
        /**
         * The content box highlight outline color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> outlineColor() {
            return Optional.ofNullable(raw("outlineColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("outlineColor")))));
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
         * @param contentColor field value; empty omits the value
         * @return this model
         */
        public HingeConfig contentColor(Optional<DOM.RGBA> contentColor) {
            set("contentColor", contentColor.orElse(null));
            return this;
        }
        /**
         * The content box highlight fill color (default: a dark color).
         * @param contentColor field value; null removes the value
         * @return this model
         */
        public HingeConfig contentColor(DOM.RGBA contentColor) {
            set("contentColor", contentColor);
            return this;
        }
        /**
         * The content box highlight outline color (default: transparent).
         * @param outlineColor field value; empty omits the value
         * @return this model
         */
        public HingeConfig outlineColor(Optional<DOM.RGBA> outlineColor) {
            set("outlineColor", outlineColor.orElse(null));
            return this;
        }
        /**
         * The content box highlight outline color (default: transparent).
         * @param outlineColor field value; null removes the value
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
            return java.util.Objects.requireNonNull(Overlay.ContainerQueryContainerHighlightConfig.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("containerQueryContainerHighlightConfig")))));
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
            return Optional.ofNullable(raw("containerBorder") == null ? null : Overlay.LineStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("containerBorder")))));
        }
        /**
         * The style of the descendants&#x27; borders.
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.LineStyle> descendantBorder() {
            return Optional.ofNullable(raw("descendantBorder") == null ? null : Overlay.LineStyle.fromMap(java.util.Objects.requireNonNull(objectMap(raw("descendantBorder")))));
        }
        /**
         * The style of the container border.
         * @param containerBorder field value; empty omits the value
         * @return this model
         */
        public ContainerQueryContainerHighlightConfig containerBorder(Optional<Overlay.LineStyle> containerBorder) {
            set("containerBorder", containerBorder.orElse(null));
            return this;
        }
        /**
         * The style of the container border.
         * @param containerBorder field value; null removes the value
         * @return this model
         */
        public ContainerQueryContainerHighlightConfig containerBorder(Overlay.LineStyle containerBorder) {
            set("containerBorder", containerBorder);
            return this;
        }
        /**
         * The style of the descendants&#x27; borders.
         * @param descendantBorder field value; empty omits the value
         * @return this model
         */
        public ContainerQueryContainerHighlightConfig descendantBorder(Optional<Overlay.LineStyle> descendantBorder) {
            set("descendantBorder", descendantBorder.orElse(null));
            return this;
        }
        /**
         * The style of the descendants&#x27; borders.
         * @param descendantBorder field value; null removes the value
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
            return java.util.Objects.requireNonNull(Overlay.IsolationModeHighlightConfig.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("isolationModeHighlightConfig")))));
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
            return Optional.ofNullable(raw("resizerColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("resizerColor")))));
        }
        /**
         * The fill color for resizer handles (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> resizerHandleColor() {
            return Optional.ofNullable(raw("resizerHandleColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("resizerHandleColor")))));
        }
        /**
         * The fill color for the mask covering non-isolated elements (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> maskColor() {
            return Optional.ofNullable(raw("maskColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("maskColor")))));
        }
        /**
         * The fill color of the resizers (default: transparent).
         * @param resizerColor field value; empty omits the value
         * @return this model
         */
        public IsolationModeHighlightConfig resizerColor(Optional<DOM.RGBA> resizerColor) {
            set("resizerColor", resizerColor.orElse(null));
            return this;
        }
        /**
         * The fill color of the resizers (default: transparent).
         * @param resizerColor field value; null removes the value
         * @return this model
         */
        public IsolationModeHighlightConfig resizerColor(DOM.RGBA resizerColor) {
            set("resizerColor", resizerColor);
            return this;
        }
        /**
         * The fill color for resizer handles (default: transparent).
         * @param resizerHandleColor field value; empty omits the value
         * @return this model
         */
        public IsolationModeHighlightConfig resizerHandleColor(Optional<DOM.RGBA> resizerHandleColor) {
            set("resizerHandleColor", resizerHandleColor.orElse(null));
            return this;
        }
        /**
         * The fill color for resizer handles (default: transparent).
         * @param resizerHandleColor field value; null removes the value
         * @return this model
         */
        public IsolationModeHighlightConfig resizerHandleColor(DOM.RGBA resizerHandleColor) {
            set("resizerHandleColor", resizerHandleColor);
            return this;
        }
        /**
         * The fill color for the mask covering non-isolated elements (default: transparent).
         * @param maskColor field value; empty omits the value
         * @return this model
         */
        public IsolationModeHighlightConfig maskColor(Optional<DOM.RGBA> maskColor) {
            set("maskColor", maskColor.orElse(null));
            return this;
        }
        /**
         * The fill color for the mask covering non-isolated elements (default: transparent).
         * @param maskColor field value; null removes the value
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
        @Nonnull public String value() { return value; }
        public static InspectMode of(@Nonnull String value) {
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
            return Optional.ofNullable(raw("nodeId") == null ? null : new DOM.NodeId(((Number) raw("nodeId")).longValue()));
        }
        /**
         * Identifier of the backend node to highlight.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return Optional.ofNullable(raw("backendNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("backendNodeId")).longValue()));
        }
        /**
         * Identifier of the node to highlight.
         * @param nodeId field value; empty omits the value
         * @return this model
         */
        public InspectedElementAnchorConfig nodeId(Optional<DOM.NodeId> nodeId) {
            set("nodeId", nodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the node to highlight.
         * @param nodeId field value; null removes the value
         * @return this model
         */
        public InspectedElementAnchorConfig nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node to highlight.
         * @param backendNodeId field value; empty omits the value
         * @return this model
         */
        public InspectedElementAnchorConfig backendNodeId(Optional<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the backend node to highlight.
         * @param backendNodeId field value; null removes the value
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
            set("nodeId", nodeId);
        }
        public static GetHighlightObjectForTestRequest fromMap(Map<String, Object> values) {
            GetHighlightObjectForTestRequest instance_ = new GetHighlightObjectForTestRequest();
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
            return Optional.ofNullable((Boolean) raw("includeDistance"));
        }
        /**
         * Whether to include style info.
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> includeStyle() {
            return Optional.ofNullable((Boolean) raw("includeStyle"));
        }
        /**
         * The color format to get config with (default: hex).
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.ColorFormat> colorFormat() {
            return Optional.ofNullable(raw("colorFormat") == null ? null : Overlay.ColorFormat.of((String) raw("colorFormat")));
        }
        /**
         * Whether to show accessibility info (default: true).
         * @return the protocol field value, empty when absent
         */
        public Optional<Boolean> showAccessibilityInfo() {
            return Optional.ofNullable((Boolean) raw("showAccessibilityInfo"));
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
         * @param includeDistance field value; empty omits the value
         * @return this model
         */
        public GetHighlightObjectForTestRequest includeDistance(Optional<Boolean> includeDistance) {
            set("includeDistance", includeDistance.orElse(null));
            return this;
        }
        /**
         * Whether to include distance info.
         * @param includeDistance field value; null removes the value
         * @return this model
         */
        public GetHighlightObjectForTestRequest includeDistance(Boolean includeDistance) {
            set("includeDistance", includeDistance);
            return this;
        }
        /**
         * Whether to include style info.
         * @param includeStyle field value; empty omits the value
         * @return this model
         */
        public GetHighlightObjectForTestRequest includeStyle(Optional<Boolean> includeStyle) {
            set("includeStyle", includeStyle.orElse(null));
            return this;
        }
        /**
         * Whether to include style info.
         * @param includeStyle field value; null removes the value
         * @return this model
         */
        public GetHighlightObjectForTestRequest includeStyle(Boolean includeStyle) {
            set("includeStyle", includeStyle);
            return this;
        }
        /**
         * The color format to get config with (default: hex).
         * @param colorFormat field value; empty omits the value
         * @return this model
         */
        public GetHighlightObjectForTestRequest colorFormat(Optional<Overlay.ColorFormat> colorFormat) {
            set("colorFormat", colorFormat.orElse(null));
            return this;
        }
        /**
         * The color format to get config with (default: hex).
         * @param colorFormat field value; null removes the value
         * @return this model
         */
        public GetHighlightObjectForTestRequest colorFormat(Overlay.ColorFormat colorFormat) {
            set("colorFormat", colorFormat);
            return this;
        }
        /**
         * Whether to show accessibility info (default: true).
         * @param showAccessibilityInfo field value; empty omits the value
         * @return this model
         */
        public GetHighlightObjectForTestRequest showAccessibilityInfo(Optional<Boolean> showAccessibilityInfo) {
            set("showAccessibilityInfo", showAccessibilityInfo.orElse(null));
            return this;
        }
        /**
         * Whether to show accessibility info (default: true).
         * @param showAccessibilityInfo field value; null removes the value
         * @return this model
         */
        public GetHighlightObjectForTestRequest showAccessibilityInfo(Boolean showAccessibilityInfo) {
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
            set("nodeIds", nodeIds);
        }
        public static GetGridHighlightObjectsForTestRequest fromMap(Map<String, Object> values) {
            GetGridHighlightObjectsForTestRequest instance_ = new GetGridHighlightObjectsForTestRequest();
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
            set("nodeId", nodeId);
        }
        public static GetSourceOrderHighlightObjectForTestRequest fromMap(Map<String, Object> values) {
            GetSourceOrderHighlightObjectForTestRequest instance_ = new GetSourceOrderHighlightObjectForTestRequest();
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
            set("frameId", frameId);
        }
        public static HighlightFrameRequest fromMap(Map<String, Object> values) {
            HighlightFrameRequest instance_ = new HighlightFrameRequest();
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
            return Optional.ofNullable(raw("contentColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("contentColor")))));
        }
        /**
         * The content box highlight outline color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> contentOutlineColor() {
            return Optional.ofNullable(raw("contentOutlineColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("contentOutlineColor")))));
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
         * @param contentColor field value; empty omits the value
         * @return this model
         */
        public HighlightFrameRequest contentColor(Optional<DOM.RGBA> contentColor) {
            set("contentColor", contentColor.orElse(null));
            return this;
        }
        /**
         * The content box highlight fill color (default: transparent).
         * @param contentColor field value; null removes the value
         * @return this model
         */
        public HighlightFrameRequest contentColor(DOM.RGBA contentColor) {
            set("contentColor", contentColor);
            return this;
        }
        /**
         * The content box highlight outline color (default: transparent).
         * @param contentOutlineColor field value; empty omits the value
         * @return this model
         */
        public HighlightFrameRequest contentOutlineColor(Optional<DOM.RGBA> contentOutlineColor) {
            set("contentOutlineColor", contentOutlineColor.orElse(null));
            return this;
        }
        /**
         * The content box highlight outline color (default: transparent).
         * @param contentOutlineColor field value; null removes the value
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
            set("highlightConfig", highlightConfig);
        }
        public static HighlightNodeRequest fromMap(Map<String, Object> values) {
            HighlightNodeRequest instance_ = new HighlightNodeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * A descriptor for the highlight appearance.
         * @return the protocol field value
         */
        public Overlay.HighlightConfig highlightConfig() {
            return java.util.Objects.requireNonNull(Overlay.HighlightConfig.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("highlightConfig")))));
        }
        /**
         * Identifier of the node to highlight.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return Optional.ofNullable(raw("nodeId") == null ? null : new DOM.NodeId(((Number) raw("nodeId")).longValue()));
        }
        /**
         * Identifier of the backend node to highlight.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return Optional.ofNullable(raw("backendNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("backendNodeId")).longValue()));
        }
        /**
         * JavaScript object id of the node to be highlighted.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return Optional.ofNullable(raw("objectId") == null ? null : new Runtime.RemoteObjectId((String) raw("objectId")));
        }
        /**
         * Selectors to highlight relevant nodes.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> selector() {
            return Optional.ofNullable((String) raw("selector"));
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
         * @param nodeId field value; empty omits the value
         * @return this model
         */
        public HighlightNodeRequest nodeId(Optional<DOM.NodeId> nodeId) {
            set("nodeId", nodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the node to highlight.
         * @param nodeId field value; null removes the value
         * @return this model
         */
        public HighlightNodeRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node to highlight.
         * @param backendNodeId field value; empty omits the value
         * @return this model
         */
        public HighlightNodeRequest backendNodeId(Optional<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the backend node to highlight.
         * @param backendNodeId field value; null removes the value
         * @return this model
         */
        public HighlightNodeRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node to be highlighted.
         * @param objectId field value; empty omits the value
         * @return this model
         */
        public HighlightNodeRequest objectId(Optional<Runtime.RemoteObjectId> objectId) {
            set("objectId", objectId.orElse(null));
            return this;
        }
        /**
         * JavaScript object id of the node to be highlighted.
         * @param objectId field value; null removes the value
         * @return this model
         */
        public HighlightNodeRequest objectId(Runtime.RemoteObjectId objectId) {
            set("objectId", objectId);
            return this;
        }
        /**
         * Selectors to highlight relevant nodes.
         * @param selector field value; empty omits the value
         * @return this model
         */
        public HighlightNodeRequest selector(Optional<String> selector) {
            set("selector", selector.orElse(null));
            return this;
        }
        /**
         * Selectors to highlight relevant nodes.
         * @param selector field value; null removes the value
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
            set("quad", quad);
        }
        public static HighlightQuadRequest fromMap(Map<String, Object> values) {
            HighlightQuadRequest instance_ = new HighlightQuadRequest();
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
            return Optional.ofNullable(raw("color") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("color")))));
        }
        /**
         * The highlight outline color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> outlineColor() {
            return Optional.ofNullable(raw("outlineColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("outlineColor")))));
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
         * @param color field value; empty omits the value
         * @return this model
         */
        public HighlightQuadRequest color(Optional<DOM.RGBA> color) {
            set("color", color.orElse(null));
            return this;
        }
        /**
         * The highlight fill color (default: transparent).
         * @param color field value; null removes the value
         * @return this model
         */
        public HighlightQuadRequest color(DOM.RGBA color) {
            set("color", color);
            return this;
        }
        /**
         * The highlight outline color (default: transparent).
         * @param outlineColor field value; empty omits the value
         * @return this model
         */
        public HighlightQuadRequest outlineColor(Optional<DOM.RGBA> outlineColor) {
            set("outlineColor", outlineColor.orElse(null));
            return this;
        }
        /**
         * The highlight outline color (default: transparent).
         * @param outlineColor field value; null removes the value
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
            set("x", x);
            set("y", y);
            set("width", width);
            set("height", height);
        }
        public static HighlightRectRequest fromMap(Map<String, Object> values) {
            HighlightRectRequest instance_ = new HighlightRectRequest();
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
            return Optional.ofNullable(raw("color") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("color")))));
        }
        /**
         * The highlight outline color (default: transparent).
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.RGBA> outlineColor() {
            return Optional.ofNullable(raw("outlineColor") == null ? null : DOM.RGBA.fromMap(java.util.Objects.requireNonNull(objectMap(raw("outlineColor")))));
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
         * @param color field value; empty omits the value
         * @return this model
         */
        public HighlightRectRequest color(Optional<DOM.RGBA> color) {
            set("color", color.orElse(null));
            return this;
        }
        /**
         * The highlight fill color (default: transparent).
         * @param color field value; null removes the value
         * @return this model
         */
        public HighlightRectRequest color(DOM.RGBA color) {
            set("color", color);
            return this;
        }
        /**
         * The highlight outline color (default: transparent).
         * @param outlineColor field value; empty omits the value
         * @return this model
         */
        public HighlightRectRequest outlineColor(Optional<DOM.RGBA> outlineColor) {
            set("outlineColor", outlineColor.orElse(null));
            return this;
        }
        /**
         * The highlight outline color (default: transparent).
         * @param outlineColor field value; null removes the value
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
            set("sourceOrderConfig", sourceOrderConfig);
        }
        public static HighlightSourceOrderRequest fromMap(Map<String, Object> values) {
            HighlightSourceOrderRequest instance_ = new HighlightSourceOrderRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * A descriptor for the appearance of the overlay drawing.
         * @return the protocol field value
         */
        public Overlay.SourceOrderConfig sourceOrderConfig() {
            return java.util.Objects.requireNonNull(Overlay.SourceOrderConfig.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("sourceOrderConfig")))));
        }
        /**
         * Identifier of the node to highlight.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.NodeId> nodeId() {
            return Optional.ofNullable(raw("nodeId") == null ? null : new DOM.NodeId(((Number) raw("nodeId")).longValue()));
        }
        /**
         * Identifier of the backend node to highlight.
         * @return the protocol field value, empty when absent
         */
        public Optional<DOM.BackendNodeId> backendNodeId() {
            return Optional.ofNullable(raw("backendNodeId") == null ? null : new DOM.BackendNodeId(((Number) raw("backendNodeId")).longValue()));
        }
        /**
         * JavaScript object id of the node to be highlighted.
         * @return the protocol field value, empty when absent
         */
        public Optional<Runtime.RemoteObjectId> objectId() {
            return Optional.ofNullable(raw("objectId") == null ? null : new Runtime.RemoteObjectId((String) raw("objectId")));
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
         * @param nodeId field value; empty omits the value
         * @return this model
         */
        public HighlightSourceOrderRequest nodeId(Optional<DOM.NodeId> nodeId) {
            set("nodeId", nodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the node to highlight.
         * @param nodeId field value; null removes the value
         * @return this model
         */
        public HighlightSourceOrderRequest nodeId(DOM.NodeId nodeId) {
            set("nodeId", nodeId);
            return this;
        }
        /**
         * Identifier of the backend node to highlight.
         * @param backendNodeId field value; empty omits the value
         * @return this model
         */
        public HighlightSourceOrderRequest backendNodeId(Optional<DOM.BackendNodeId> backendNodeId) {
            set("backendNodeId", backendNodeId.orElse(null));
            return this;
        }
        /**
         * Identifier of the backend node to highlight.
         * @param backendNodeId field value; null removes the value
         * @return this model
         */
        public HighlightSourceOrderRequest backendNodeId(DOM.BackendNodeId backendNodeId) {
            set("backendNodeId", backendNodeId);
            return this;
        }
        /**
         * JavaScript object id of the node to be highlighted.
         * @param objectId field value; empty omits the value
         * @return this model
         */
        public HighlightSourceOrderRequest objectId(Optional<Runtime.RemoteObjectId> objectId) {
            set("objectId", objectId.orElse(null));
            return this;
        }
        /**
         * JavaScript object id of the node to be highlighted.
         * @param objectId field value; null removes the value
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
            set("mode", mode);
        }
        public static SetInspectModeRequest fromMap(Map<String, Object> values) {
            SetInspectModeRequest instance_ = new SetInspectModeRequest();
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
            return Optional.ofNullable(raw("highlightConfig") == null ? null : Overlay.HighlightConfig.fromMap(java.util.Objects.requireNonNull(objectMap(raw("highlightConfig")))));
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
         * @param highlightConfig field value; empty omits the value
         * @return this model
         */
        public SetInspectModeRequest highlightConfig(Optional<Overlay.HighlightConfig> highlightConfig) {
            set("highlightConfig", highlightConfig.orElse(null));
            return this;
        }
        /**
         * A descriptor for the highlight appearance of hovered-over nodes. May be omitted if {@code enabled == false}.
         * @param highlightConfig field value; null removes the value
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
            set("show", show);
        }
        public static SetShowAdHighlightsRequest fromMap(Map<String, Object> values) {
            SetShowAdHighlightsRequest instance_ = new SetShowAdHighlightsRequest();
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
            SetPausedInDebuggerMessageRequest instance_ = new SetPausedInDebuggerMessageRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * The message to display, also triggers resume and step over controls.
         * @return the protocol field value, empty when absent
         */
        public Optional<String> message() {
            return Optional.ofNullable((String) raw("message"));
        }
        /**
         * The message to display, also triggers resume and step over controls.
         * @param message field value; empty omits the value
         * @return this model
         */
        public SetPausedInDebuggerMessageRequest message(Optional<String> message) {
            set("message", message.orElse(null));
            return this;
        }
        /**
         * The message to display, also triggers resume and step over controls.
         * @param message field value; null removes the value
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
            set("show", show);
        }
        public static SetShowDebugBordersRequest fromMap(Map<String, Object> values) {
            SetShowDebugBordersRequest instance_ = new SetShowDebugBordersRequest();
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
            set("show", show);
        }
        public static SetShowFPSCounterRequest fromMap(Map<String, Object> values) {
            SetShowFPSCounterRequest instance_ = new SetShowFPSCounterRequest();
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
            set("gridNodeHighlightConfigs", gridNodeHighlightConfigs);
        }
        public static SetShowGridOverlaysRequest fromMap(Map<String, Object> values) {
            SetShowGridOverlaysRequest instance_ = new SetShowGridOverlaysRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        public java.util.List<Overlay.GridNodeHighlightConfig> gridNodeHighlightConfigs() {
            return CdpObject.requireList(require("gridNodeHighlightConfigs"), element0 -> java.util.Objects.requireNonNull(Overlay.GridNodeHighlightConfig.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            set("flexNodeHighlightConfigs", flexNodeHighlightConfigs);
        }
        public static SetShowFlexOverlaysRequest fromMap(Map<String, Object> values) {
            SetShowFlexOverlaysRequest instance_ = new SetShowFlexOverlaysRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        public java.util.List<Overlay.FlexNodeHighlightConfig> flexNodeHighlightConfigs() {
            return CdpObject.requireList(require("flexNodeHighlightConfigs"), element0 -> java.util.Objects.requireNonNull(Overlay.FlexNodeHighlightConfig.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            set("scrollSnapHighlightConfigs", scrollSnapHighlightConfigs);
        }
        public static SetShowScrollSnapOverlaysRequest fromMap(Map<String, Object> values) {
            SetShowScrollSnapOverlaysRequest instance_ = new SetShowScrollSnapOverlaysRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        public java.util.List<Overlay.ScrollSnapHighlightConfig> scrollSnapHighlightConfigs() {
            return CdpObject.requireList(require("scrollSnapHighlightConfigs"), element0 -> java.util.Objects.requireNonNull(Overlay.ScrollSnapHighlightConfig.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            set("containerQueryHighlightConfigs", containerQueryHighlightConfigs);
        }
        public static SetShowContainerQueryOverlaysRequest fromMap(Map<String, Object> values) {
            SetShowContainerQueryOverlaysRequest instance_ = new SetShowContainerQueryOverlaysRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        public java.util.List<Overlay.ContainerQueryHighlightConfig> containerQueryHighlightConfigs() {
            return CdpObject.requireList(require("containerQueryHighlightConfigs"), element0 -> java.util.Objects.requireNonNull(Overlay.ContainerQueryHighlightConfig.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            set("inspectedElementAnchorConfig", inspectedElementAnchorConfig);
        }
        public static SetShowInspectedElementAnchorRequest fromMap(Map<String, Object> values) {
            SetShowInspectedElementAnchorRequest instance_ = new SetShowInspectedElementAnchorRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Node identifier for which to show an anchor for.
         * @return the protocol field value
         */
        public Overlay.InspectedElementAnchorConfig inspectedElementAnchorConfig() {
            return java.util.Objects.requireNonNull(Overlay.InspectedElementAnchorConfig.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("inspectedElementAnchorConfig")))));
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
            set("result", result);
        }
        public static SetShowPaintRectsRequest fromMap(Map<String, Object> values) {
            SetShowPaintRectsRequest instance_ = new SetShowPaintRectsRequest();
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
            set("result", result);
        }
        public static SetShowLayoutShiftRegionsRequest fromMap(Map<String, Object> values) {
            SetShowLayoutShiftRegionsRequest instance_ = new SetShowLayoutShiftRegionsRequest();
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
            set("show", show);
        }
        public static SetShowScrollBottleneckRectsRequest fromMap(Map<String, Object> values) {
            SetShowScrollBottleneckRectsRequest instance_ = new SetShowScrollBottleneckRectsRequest();
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
            set("show", show);
        }
        public static SetShowHitTestBordersRequest fromMap(Map<String, Object> values) {
            SetShowHitTestBordersRequest instance_ = new SetShowHitTestBordersRequest();
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
            set("show", show);
        }
        public static SetShowWebVitalsRequest fromMap(Map<String, Object> values) {
            SetShowWebVitalsRequest instance_ = new SetShowWebVitalsRequest();
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
            set("show", show);
        }
        public static SetShowViewportSizeOnResizeRequest fromMap(Map<String, Object> values) {
            SetShowViewportSizeOnResizeRequest instance_ = new SetShowViewportSizeOnResizeRequest();
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
            SetShowHingeRequest instance_ = new SetShowHingeRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * hinge data, null means hideHinge
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.HingeConfig> hingeConfig() {
            return Optional.ofNullable(raw("hingeConfig") == null ? null : Overlay.HingeConfig.fromMap(java.util.Objects.requireNonNull(objectMap(raw("hingeConfig")))));
        }
        /**
         * hinge data, null means hideHinge
         * @param hingeConfig field value; empty omits the value
         * @return this model
         */
        public SetShowHingeRequest hingeConfig(Optional<Overlay.HingeConfig> hingeConfig) {
            set("hingeConfig", hingeConfig.orElse(null));
            return this;
        }
        /**
         * hinge data, null means hideHinge
         * @param hingeConfig field value; null removes the value
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
            set("isolatedElementHighlightConfigs", isolatedElementHighlightConfigs);
        }
        public static SetShowIsolatedElementsRequest fromMap(Map<String, Object> values) {
            SetShowIsolatedElementsRequest instance_ = new SetShowIsolatedElementsRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * An array of node identifiers and descriptors for the highlight appearance.
         * @return the protocol field value
         */
        public java.util.List<Overlay.IsolatedElementHighlightConfig> isolatedElementHighlightConfigs() {
            return CdpObject.requireList(require("isolatedElementHighlightConfigs"), element0 -> java.util.Objects.requireNonNull(Overlay.IsolatedElementHighlightConfig.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
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
            SetShowWindowControlsOverlayRequest instance_ = new SetShowWindowControlsOverlayRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Window Controls Overlay data, null means hide Window Controls Overlay
         * @return the protocol field value, empty when absent
         */
        public Optional<Overlay.WindowControlsOverlayConfig> windowControlsOverlayConfig() {
            return Optional.ofNullable(raw("windowControlsOverlayConfig") == null ? null : Overlay.WindowControlsOverlayConfig.fromMap(java.util.Objects.requireNonNull(objectMap(raw("windowControlsOverlayConfig")))));
        }
        /**
         * Window Controls Overlay data, null means hide Window Controls Overlay
         * @param windowControlsOverlayConfig field value; empty omits the value
         * @return this model
         */
        public SetShowWindowControlsOverlayRequest windowControlsOverlayConfig(Optional<Overlay.WindowControlsOverlayConfig> windowControlsOverlayConfig) {
            set("windowControlsOverlayConfig", windowControlsOverlayConfig.orElse(null));
            return this;
        }
        /**
         * Window Controls Overlay data, null means hide Window Controls Overlay
         * @param windowControlsOverlayConfig field value; null removes the value
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
            return java.util.Objects.requireNonNull(Page.Viewport.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("viewport")))));
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
            return client.call("Overlay.disable", null, result_ -> null);
        }
        /**
         * Enables domain notifications.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> enable() {
            return client.call("Overlay.enable", null, result_ -> null);
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
        public CompletionStage<java.util.Map<String, Object>> getHighlightObjectForTest(DOM.NodeId nodeId, Optional<Boolean> includeDistance, Optional<Boolean> includeStyle, Optional<Overlay.ColorFormat> colorFormat, Optional<Boolean> showAccessibilityInfo) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            includeDistance.ifPresent(value_ -> params.put("includeDistance", value_));
            includeStyle.ifPresent(value_ -> params.put("includeStyle", value_));
            colorFormat.ifPresent(value_ -> params.put("colorFormat", CdpObject.json(value_)));
            showAccessibilityInfo.ifPresent(value_ -> params.put("showAccessibilityInfo", value_));
            return client.call("Overlay.getHighlightObjectForTest", params, result_ -> java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("highlight")))));
        }
        /**
         * For testing.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getHighlightObjectForTest(DOM.NodeId nodeId) {
            return getHighlightObjectForTest(nodeId, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * For testing.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getHighlightObjectForTest(GetHighlightObjectForTestRequest request) {
            return client.call("Overlay.getHighlightObjectForTest", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("highlight")))));
        }
        /**
         * For Persistent Grid testing.
         * @param nodeIds protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getGridHighlightObjectsForTest(java.util.List<DOM.NodeId> nodeIds) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeIds", CdpObject.json(nodeIds));
            return client.call("Overlay.getGridHighlightObjectsForTest", params, result_ -> java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("highlights")))));
        }
        /**
         * For Persistent Grid testing.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getGridHighlightObjectsForTest(GetGridHighlightObjectsForTestRequest request) {
            return client.call("Overlay.getGridHighlightObjectsForTest", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("highlights")))));
        }
        /**
         * For Source Order Viewer testing.
         * @param nodeId protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getSourceOrderHighlightObjectForTest(DOM.NodeId nodeId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("nodeId", CdpObject.json(nodeId));
            return client.call("Overlay.getSourceOrderHighlightObjectForTest", params, result_ -> java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("highlight")))));
        }
        /**
         * For Source Order Viewer testing.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<java.util.Map<String, Object>> getSourceOrderHighlightObjectForTest(GetSourceOrderHighlightObjectForTestRequest request) {
            return client.call("Overlay.getSourceOrderHighlightObjectForTest", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("highlight")))));
        }
        /**
         * Hides any highlight.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> hideHighlight() {
            return client.call("Overlay.hideHighlight", null, result_ -> null);
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
        public CompletionStage<Void> highlightFrame(Page.FrameId frameId, Optional<DOM.RGBA> contentColor, Optional<DOM.RGBA> contentOutlineColor) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("frameId", CdpObject.json(frameId));
            contentColor.ifPresent(value_ -> params.put("contentColor", CdpObject.json(value_)));
            contentOutlineColor.ifPresent(value_ -> params.put("contentOutlineColor", CdpObject.json(value_)));
            return client.call("Overlay.highlightFrame", params, result_ -> null);
        }
        /**
         * Highlights owner element of the frame with given id. Deprecated: Doesn&#x27;t work reliably and cannot be fixed due to process separation (the owner node might be in a different process). Determine the owner node in the client and use highlightNode.
         * @param frameId protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> highlightFrame(Page.FrameId frameId) {
            return highlightFrame(frameId, Optional.empty(), Optional.empty());
        }
        /**
         * Highlights owner element of the frame with given id. Deprecated: Doesn&#x27;t work reliably and cannot be fixed due to process separation (the owner node might be in a different process). Determine the owner node in the client and use highlightNode.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> highlightFrame(HighlightFrameRequest request) {
            return client.call("Overlay.highlightFrame", request == null ? null : request.toMap(), result_ -> null);
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
        public CompletionStage<Void> highlightNode(Overlay.HighlightConfig highlightConfig, Optional<DOM.NodeId> nodeId, Optional<DOM.BackendNodeId> backendNodeId, Optional<Runtime.RemoteObjectId> objectId, Optional<String> selector) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("highlightConfig", CdpObject.json(highlightConfig));
            nodeId.ifPresent(value_ -> params.put("nodeId", CdpObject.json(value_)));
            backendNodeId.ifPresent(value_ -> params.put("backendNodeId", CdpObject.json(value_)));
            objectId.ifPresent(value_ -> params.put("objectId", CdpObject.json(value_)));
            selector.ifPresent(value_ -> params.put("selector", CdpObject.json(value_)));
            return client.call("Overlay.highlightNode", params, result_ -> null);
        }
        /**
         * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param highlightConfig protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightNode(Overlay.HighlightConfig highlightConfig) {
            return highlightNode(highlightConfig, Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Highlights DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightNode(HighlightNodeRequest request) {
            return client.call("Overlay.highlightNode", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
         * @param quad protocol value
         * @param color protocol value
         * @param outlineColor protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightQuad(java.util.List<Double> quad, Optional<DOM.RGBA> color, Optional<DOM.RGBA> outlineColor) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("quad", CdpObject.json(quad));
            color.ifPresent(value_ -> params.put("color", CdpObject.json(value_)));
            outlineColor.ifPresent(value_ -> params.put("outlineColor", CdpObject.json(value_)));
            return client.call("Overlay.highlightQuad", params, result_ -> null);
        }
        /**
         * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
         * @param quad protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightQuad(java.util.List<Double> quad) {
            return highlightQuad(quad, Optional.empty(), Optional.empty());
        }
        /**
         * Highlights given quad. Coordinates are absolute with respect to the main frame viewport.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightQuad(HighlightQuadRequest request) {
            return client.call("Overlay.highlightQuad", request == null ? null : request.toMap(), result_ -> null);
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
        public CompletionStage<Void> highlightRect(long x, long y, long width, long height, Optional<DOM.RGBA> color, Optional<DOM.RGBA> outlineColor) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("x", CdpObject.json(x));
            params.put("y", CdpObject.json(y));
            params.put("width", CdpObject.json(width));
            params.put("height", CdpObject.json(height));
            color.ifPresent(value_ -> params.put("color", CdpObject.json(value_)));
            outlineColor.ifPresent(value_ -> params.put("outlineColor", CdpObject.json(value_)));
            return client.call("Overlay.highlightRect", params, result_ -> null);
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
            return highlightRect(x, y, width, height, Optional.empty(), Optional.empty());
        }
        /**
         * Highlights given rectangle. Coordinates are absolute with respect to the main frame viewport. Issue: the method does not handle device pixel ratio (DPR) correctly. The coordinates currently have to be adjusted by the client if DPR is not 1 (see crbug.com/437807128).
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightRect(HighlightRectRequest request) {
            return client.call("Overlay.highlightRect", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Highlights the source order of the children of the DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param sourceOrderConfig protocol value
         * @param nodeId protocol value
         * @param backendNodeId protocol value
         * @param objectId protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightSourceOrder(Overlay.SourceOrderConfig sourceOrderConfig, Optional<DOM.NodeId> nodeId, Optional<DOM.BackendNodeId> backendNodeId, Optional<Runtime.RemoteObjectId> objectId) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("sourceOrderConfig", CdpObject.json(sourceOrderConfig));
            nodeId.ifPresent(value_ -> params.put("nodeId", CdpObject.json(value_)));
            backendNodeId.ifPresent(value_ -> params.put("backendNodeId", CdpObject.json(value_)));
            objectId.ifPresent(value_ -> params.put("objectId", CdpObject.json(value_)));
            return client.call("Overlay.highlightSourceOrder", params, result_ -> null);
        }
        /**
         * Highlights the source order of the children of the DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param sourceOrderConfig protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightSourceOrder(Overlay.SourceOrderConfig sourceOrderConfig) {
            return highlightSourceOrder(sourceOrderConfig, Optional.empty(), Optional.empty(), Optional.empty());
        }
        /**
         * Highlights the source order of the children of the DOM node with given id or with the given JavaScript object wrapper. Either nodeId or objectId must be specified.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> highlightSourceOrder(HighlightSourceOrderRequest request) {
            return client.call("Overlay.highlightSourceOrder", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Enters the &#x27;inspect&#x27; mode. In this mode, elements that user is hovering over are highlighted. Backend then generates &#x27;inspectNodeRequested&#x27; event upon element selection.
         * @param mode protocol value
         * @param highlightConfig protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInspectMode(Overlay.InspectMode mode, Optional<Overlay.HighlightConfig> highlightConfig) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("mode", CdpObject.json(mode));
            highlightConfig.ifPresent(value_ -> params.put("highlightConfig", CdpObject.json(value_)));
            return client.call("Overlay.setInspectMode", params, result_ -> null);
        }
        /**
         * Enters the &#x27;inspect&#x27; mode. In this mode, elements that user is hovering over are highlighted. Backend then generates &#x27;inspectNodeRequested&#x27; event upon element selection.
         * @param mode protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInspectMode(Overlay.InspectMode mode) {
            return setInspectMode(mode, Optional.empty());
        }
        /**
         * Enters the &#x27;inspect&#x27; mode. In this mode, elements that user is hovering over are highlighted. Backend then generates &#x27;inspectNodeRequested&#x27; event upon element selection.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setInspectMode(SetInspectModeRequest request) {
            return client.call("Overlay.setInspectMode", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Highlights owner element of all frames detected to be ads.
         * @param show protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowAdHighlights(boolean show) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("show", CdpObject.json(show));
            return client.call("Overlay.setShowAdHighlights", params, result_ -> null);
        }
        /**
         * Highlights owner element of all frames detected to be ads.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowAdHighlights(SetShowAdHighlightsRequest request) {
            return client.call("Overlay.setShowAdHighlights", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes Overlay.setPausedInDebuggerMessage.
         * @param message protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPausedInDebuggerMessage(Optional<String> message) {
            Map<String, Object> params = new LinkedHashMap<>();
            message.ifPresent(value_ -> params.put("message", CdpObject.json(value_)));
            return client.call("Overlay.setPausedInDebuggerMessage", params, result_ -> null);
        }
        /**
         * Invokes Overlay.setPausedInDebuggerMessage with default parameters.
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPausedInDebuggerMessage() {
            return setPausedInDebuggerMessage(Optional.empty());
        }
        /**
         * Invokes Overlay.setPausedInDebuggerMessage with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setPausedInDebuggerMessage(SetPausedInDebuggerMessageRequest request) {
            return client.call("Overlay.setPausedInDebuggerMessage", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Requests that backend shows debug borders on layers
         * @param show protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowDebugBorders(boolean show) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("show", CdpObject.json(show));
            return client.call("Overlay.setShowDebugBorders", params, result_ -> null);
        }
        /**
         * Requests that backend shows debug borders on layers
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowDebugBorders(SetShowDebugBordersRequest request) {
            return client.call("Overlay.setShowDebugBorders", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Requests that backend shows the FPS counter
         * @param show protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowFPSCounter(boolean show) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("show", CdpObject.json(show));
            return client.call("Overlay.setShowFPSCounter", params, result_ -> null);
        }
        /**
         * Requests that backend shows the FPS counter
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowFPSCounter(SetShowFPSCounterRequest request) {
            return client.call("Overlay.setShowFPSCounter", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Highlight multiple elements with the CSS Grid overlay.
         * @param gridNodeHighlightConfigs protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowGridOverlays(java.util.List<Overlay.GridNodeHighlightConfig> gridNodeHighlightConfigs) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("gridNodeHighlightConfigs", CdpObject.json(gridNodeHighlightConfigs));
            return client.call("Overlay.setShowGridOverlays", params, result_ -> null);
        }
        /**
         * Highlight multiple elements with the CSS Grid overlay.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowGridOverlays(SetShowGridOverlaysRequest request) {
            return client.call("Overlay.setShowGridOverlays", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes Overlay.setShowFlexOverlays.
         * @param flexNodeHighlightConfigs protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowFlexOverlays(java.util.List<Overlay.FlexNodeHighlightConfig> flexNodeHighlightConfigs) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("flexNodeHighlightConfigs", CdpObject.json(flexNodeHighlightConfigs));
            return client.call("Overlay.setShowFlexOverlays", params, result_ -> null);
        }
        /**
         * Invokes Overlay.setShowFlexOverlays with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowFlexOverlays(SetShowFlexOverlaysRequest request) {
            return client.call("Overlay.setShowFlexOverlays", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes Overlay.setShowScrollSnapOverlays.
         * @param scrollSnapHighlightConfigs protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowScrollSnapOverlays(java.util.List<Overlay.ScrollSnapHighlightConfig> scrollSnapHighlightConfigs) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("scrollSnapHighlightConfigs", CdpObject.json(scrollSnapHighlightConfigs));
            return client.call("Overlay.setShowScrollSnapOverlays", params, result_ -> null);
        }
        /**
         * Invokes Overlay.setShowScrollSnapOverlays with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowScrollSnapOverlays(SetShowScrollSnapOverlaysRequest request) {
            return client.call("Overlay.setShowScrollSnapOverlays", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes Overlay.setShowContainerQueryOverlays.
         * @param containerQueryHighlightConfigs protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowContainerQueryOverlays(java.util.List<Overlay.ContainerQueryHighlightConfig> containerQueryHighlightConfigs) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("containerQueryHighlightConfigs", CdpObject.json(containerQueryHighlightConfigs));
            return client.call("Overlay.setShowContainerQueryOverlays", params, result_ -> null);
        }
        /**
         * Invokes Overlay.setShowContainerQueryOverlays with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowContainerQueryOverlays(SetShowContainerQueryOverlaysRequest request) {
            return client.call("Overlay.setShowContainerQueryOverlays", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Invokes Overlay.setShowInspectedElementAnchor.
         * @param inspectedElementAnchorConfig protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowInspectedElementAnchor(Overlay.InspectedElementAnchorConfig inspectedElementAnchorConfig) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("inspectedElementAnchorConfig", CdpObject.json(inspectedElementAnchorConfig));
            return client.call("Overlay.setShowInspectedElementAnchor", params, result_ -> null);
        }
        /**
         * Invokes Overlay.setShowInspectedElementAnchor with a request object.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowInspectedElementAnchor(SetShowInspectedElementAnchorRequest request) {
            return client.call("Overlay.setShowInspectedElementAnchor", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Requests that backend shows paint rectangles
         * @param result protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowPaintRects(boolean result) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("result", CdpObject.json(result));
            return client.call("Overlay.setShowPaintRects", params, result_ -> null);
        }
        /**
         * Requests that backend shows paint rectangles
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowPaintRects(SetShowPaintRectsRequest request) {
            return client.call("Overlay.setShowPaintRects", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Requests that backend shows layout shift regions
         * @param result protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowLayoutShiftRegions(boolean result) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("result", CdpObject.json(result));
            return client.call("Overlay.setShowLayoutShiftRegions", params, result_ -> null);
        }
        /**
         * Requests that backend shows layout shift regions
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowLayoutShiftRegions(SetShowLayoutShiftRegionsRequest request) {
            return client.call("Overlay.setShowLayoutShiftRegions", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Requests that backend shows scroll bottleneck rects
         * @param show protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowScrollBottleneckRects(boolean show) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("show", CdpObject.json(show));
            return client.call("Overlay.setShowScrollBottleneckRects", params, result_ -> null);
        }
        /**
         * Requests that backend shows scroll bottleneck rects
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowScrollBottleneckRects(SetShowScrollBottleneckRectsRequest request) {
            return client.call("Overlay.setShowScrollBottleneckRects", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Deprecated, no longer has any effect.
         * @param show protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setShowHitTestBorders(boolean show) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("show", CdpObject.json(show));
            return client.call("Overlay.setShowHitTestBorders", params, result_ -> null);
        }
        /**
         * Deprecated, no longer has any effect.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setShowHitTestBorders(SetShowHitTestBordersRequest request) {
            return client.call("Overlay.setShowHitTestBorders", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Deprecated, no longer has any effect.
         * @param show protocol value
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setShowWebVitals(boolean show) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("show", CdpObject.json(show));
            return client.call("Overlay.setShowWebVitals", params, result_ -> null);
        }
        /**
         * Deprecated, no longer has any effect.
         * @param request request parameters
         * @return a stage completing when the command completes
         * @deprecated Deprecated by the Chromium DevTools Protocol.
         */
        @Deprecated
        public CompletionStage<Void> setShowWebVitals(SetShowWebVitalsRequest request) {
            return client.call("Overlay.setShowWebVitals", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Paints viewport size upon main frame resize.
         * @param show protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowViewportSizeOnResize(boolean show) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("show", CdpObject.json(show));
            return client.call("Overlay.setShowViewportSizeOnResize", params, result_ -> null);
        }
        /**
         * Paints viewport size upon main frame resize.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowViewportSizeOnResize(SetShowViewportSizeOnResizeRequest request) {
            return client.call("Overlay.setShowViewportSizeOnResize", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Add a dual screen device hinge
         * @param hingeConfig protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowHinge(Optional<Overlay.HingeConfig> hingeConfig) {
            Map<String, Object> params = new LinkedHashMap<>();
            hingeConfig.ifPresent(value_ -> params.put("hingeConfig", CdpObject.json(value_)));
            return client.call("Overlay.setShowHinge", params, result_ -> null);
        }
        /**
         * Add a dual screen device hinge
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowHinge() {
            return setShowHinge(Optional.empty());
        }
        /**
         * Add a dual screen device hinge
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowHinge(SetShowHingeRequest request) {
            return client.call("Overlay.setShowHinge", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Show elements in isolation mode with overlays.
         * @param isolatedElementHighlightConfigs protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowIsolatedElements(java.util.List<Overlay.IsolatedElementHighlightConfig> isolatedElementHighlightConfigs) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("isolatedElementHighlightConfigs", CdpObject.json(isolatedElementHighlightConfigs));
            return client.call("Overlay.setShowIsolatedElements", params, result_ -> null);
        }
        /**
         * Show elements in isolation mode with overlays.
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowIsolatedElements(SetShowIsolatedElementsRequest request) {
            return client.call("Overlay.setShowIsolatedElements", request == null ? null : request.toMap(), result_ -> null);
        }
        /**
         * Show Window Controls Overlay for PWA
         * @param windowControlsOverlayConfig protocol value
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowWindowControlsOverlay(Optional<Overlay.WindowControlsOverlayConfig> windowControlsOverlayConfig) {
            Map<String, Object> params = new LinkedHashMap<>();
            windowControlsOverlayConfig.ifPresent(value_ -> params.put("windowControlsOverlayConfig", CdpObject.json(value_)));
            return client.call("Overlay.setShowWindowControlsOverlay", params, result_ -> null);
        }
        /**
         * Show Window Controls Overlay for PWA
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowWindowControlsOverlay() {
            return setShowWindowControlsOverlay(Optional.empty());
        }
        /**
         * Show Window Controls Overlay for PWA
         * @param request request parameters
         * @return a stage completing when the command completes
         */
        public CompletionStage<Void> setShowWindowControlsOverlay(SetShowWindowControlsOverlayRequest request) {
            return client.call("Overlay.setShowWindowControlsOverlay", request == null ? null : request.toMap(), result_ -> null);
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
