// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import com.google.errorprone.annotations.Immutable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/** A named constant of a C enum, as declared by the generated {@code Kind} enums. */
@Immutable
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class EnumConstant {
    private final long value;
    private final String expr;
    private final String name;

    public EnumConstant(long value, String expr, String name) {
        this.value = value;
        this.expr = expr;
        this.name = name;
    }

    public long value() {
        return value;
    }

    public String expr() {
        return expr;
    }

    public String name() {
        return name;
    }
}
