#!/usr/bin/env python3
"""Generate cef4j's map-backed Java CDP API from Chromium's canonical protocol JSON."""

import hashlib
import html
import json
import pathlib
import re
import shutil
import sys

browser_path, js_path, module_path, chromium_version, v8_revision = sys.argv[1:]
module = pathlib.Path(module_path)
package = "net.kurobako.cef4j.cdp.generated"
output = module / "src/main/java" / pathlib.Path(package.replace(".", "/"))
resources = module / "src/main/resources/META-INF/cef4j/cdp"

with open(browser_path, encoding="utf-8") as stream:
    browser = json.load(stream)
with open(js_path, encoding="utf-8") as stream:
    js = json.load(stream)
protocol = {"version": browser.get("version", {}), "domains": browser["domains"] + js["domains"]}
schema_bytes = (json.dumps(protocol, indent=2, ensure_ascii=False) + "\n").encode("utf-8")
fingerprint = hashlib.sha256(schema_bytes).hexdigest()

if output.exists():
    shutil.rmtree(output)
output.mkdir(parents=True)
resources.mkdir(parents=True, exist_ok=True)
(resources / "protocol.json").write_bytes(schema_bytes)
(resources / "schema.properties").write_text(
    "chromium.version=" + chromium_version + "\n"
    + "v8.revision=" + v8_revision + "\n"
    + "browser.source=https://chromium.googlesource.com/chromium/src/+/refs/tags/"
    + chromium_version
    + "/third_party/blink/public/devtools_protocol/\n"
    + "javascript.source=https://chromium.googlesource.com/v8/v8/+/"
    + v8_revision
    + "/include/js_protocol.pdl\n"
    + "source.license=BSD-3-Clause\n"
    + "schema.sha256=" + fingerprint + "\n",
    encoding="utf-8",
)

domains = {domain["domain"]: domain for domain in protocol["domains"]}
browser_domains = {domain["domain"] for domain in browser["domains"]}
types = {
    (domain["domain"], item["id"]): item
    for domain in protocol["domains"]
    for item in domain.get("types", [])
}
java_keywords = {
    "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class",
    "const", "continue", "default", "do", "double", "else", "enum", "extends", "final",
    "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int",
    "interface", "long", "native", "new", "package", "private", "protected", "public", "return",
    "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
    "transient", "try", "void", "volatile", "while", "true", "false", "null", "var", "record",
    "sealed", "permits", "yield",
}


def cap(name):
    value = re.sub(r"[^A-Za-z0-9_$]", "_", name)
    return value[:1].upper() + value[1:]


def ident(name):
    value = re.sub(r"[^A-Za-z0-9_$]", "_", name)
    if not value or value[0].isdigit():
        value = "_" + value
    return value + "Value" if value in java_keywords or value in {"notify", "notifyAll", "wait"} else value


def javadoc(spec, indent, fallback=None, params=None, returns=None):
    text = spec.get("description") or fallback or ""
    text = text.replace("*/", "*&#47;")
    paragraphs = [normalize_doc(part.strip()) for part in re.split(r"\n\s*\n", text) if part.strip()]
    lines = [indent + "/**"]
    if paragraphs:
        for index, paragraph in enumerate(paragraphs):
            prefix = "" if index == 0 else "<p>"
            lines.append(indent + " * " + prefix + paragraph.replace("\n", " "))
    if spec.get("experimental"):
        lines.append(indent + " * <p><b>Experimental:</b> this part of CDP may change without notice.")
    if params:
        for name, description in params:
            detail = normalize_doc(description.strip()) if description else "protocol value"
            lines.append(indent + " * @param " + name + " " + detail.replace("\n", " "))
    if returns:
        lines.append(indent + " * @return " + normalize_doc(returns))
    if spec.get("deprecated"):
        lines.append(indent + " * @deprecated Deprecated by the Chromium DevTools Protocol.")
    lines.append(indent + " */")
    return lines


def normalize_doc(text):
    """Convert PDL prose to safe, useful Javadoc while preserving its wording."""
    escaped = html.escape(text).replace("@", "&#64;").replace("\n", " ")
    # Chromium's PDL descriptions use Markdown-style backticks for identifiers.
    escaped = re.sub(r"`([^`]+)`", r"{@code \1}", escaped)
    return re.sub(r"\s+", " ", escaped).strip()


def protocol_source(domain_name):
    if domain_name in browser_domains:
        return (
            "https://chromium.googlesource.com/chromium/src/+/refs/tags/"
            + chromium_version
            + "/third_party/blink/public/devtools_protocol/domains/"
            + domain_name
            + ".pdl"
        )
    return "https://chromium.googlesource.com/v8/v8/+/" + v8_revision + "/include/js_protocol.pdl"


def deprecated_annotation(spec, indent):
    return [indent + "@Deprecated"] if spec.get("deprecated") else []


def enum_constants(name, values, indent="    ", spec=None):
    metadata = {} if spec is None else spec
    lines = javadoc(metadata, indent, "Wire values for " + name + ".")
    lines += deprecated_annotation(metadata, indent)
    lines += [indent + "public static final class " + name + " {", indent + "    private " + name + "() {}"]
    used = set()
    for value in values:
        constant = re.sub(r"[^A-Za-z0-9]", "_", value).upper().strip("_") or "EMPTY"
        if constant[0].isdigit():
            constant = "_" + constant
        candidate = constant
        suffix = 2
        while candidate in used:
            candidate = constant + "_" + str(suffix)
            suffix += 1
        used.add(candidate)
        lines.append(indent + "    public static final String " + candidate + " = " + json.dumps(value) + ";")
    lines += [indent + "}"]
    return lines


def model_name(domain, name):
    return cap(name) + "Value" if cap(name) == domain else cap(name)


def resolve(domain, spec, seen=None):
    seen = set() if seen is None else seen
    if "$ref" in spec:
        parts = spec["$ref"].split(".", 1)
        key = (parts[0], parts[1]) if len(parts) == 2 else (domain, parts[0])
        target = types.get(key)
        if target is None:
            return ("Object", "any")
        if key in seen:
            return (key[0] + "." + model_name(key[0], key[1]), "model")
        if target.get("type") == "object" and target.get("properties"):
            return (key[0] + "." + model_name(key[0], key[1]), "model")
        return resolve(key[0], target, seen | {key})
    kind = spec.get("type", "any")
    if kind == "array":
        inner, inner_kind = resolve(domain, spec.get("items", {}), seen)
        return ("java.util.List<" + inner + ">", "list:" + inner_kind + ":" + inner)
    return {
        "string": ("String", "string"),
        "binary": ("String", "string"),
        "integer": ("Long", "integer"),
        "number": ("Double", "number"),
        "boolean": ("Boolean", "boolean"),
        "object": ("java.util.Map<String, Object>", "object"),
        "any": ("Object", "any"),
    }.get(kind, ("Object", "any"))


def decode_expr(domain, spec, source, depth=0, seen=None):
    seen = set() if seen is None else seen
    if "$ref" in spec:
        parts = spec["$ref"].split(".", 1)
        key = (parts[0], parts[1]) if len(parts) == 2 else (domain, parts[0])
        target = types.get(key)
        if target is None:
            return source
        if target.get("type") == "object" and target.get("properties"):
            java_type, unused = resolve(domain, spec)
            return java_type + ".fromMap(objectMap(" + source + "))"
        if key in seen:
            return source
        return decode_expr(key[0], target, source, depth, seen | {key})
    java_type, kind = resolve(domain, spec)
    if kind == "string":
        return "(String) " + source
    if kind == "boolean":
        return "(Boolean) " + source
    if kind == "integer":
        return "numberAsLong(" + source + ")"
    if kind == "number":
        return "numberAsDouble(" + source + ")"
    if kind == "object":
        return "objectMap(" + source + ")"
    if spec.get("type") == "array":
        item_spec = spec.get("items", {})
        variable = "element" + str(depth)
        return "list(" + source + ", " + variable + " -> " + decode_expr(domain, item_spec, variable, depth + 1, seen) + ")"
    return source


def model(name, domain, properties, spec=None, fallback=None):
    metadata = {} if spec is None else spec
    lines = javadoc(metadata, "    ", fallback)
    lines += deprecated_annotation(metadata, "    ")
    lines += [
        "    public static final class " + name + " extends CdpObject {",
        "        private " + name + "(Map<String, Object> values) { super(values); }",
        "        @Nullable public static " + name + " fromMap(@Nullable Map<String, Object> values) {",
        "            return values == null ? null : new " + name + "(values);",
        "        }",
        "        public static Builder builder() { return new Builder(); }",
    ]
    for prop in properties:
        java_type, unused = resolve(domain, prop)
        accessor = ident(prop["name"])
        lines += javadoc(prop, "        ", "Returns the " + prop["name"] + " field.", returns="the protocol field value")
        lines += deprecated_annotation(prop, "        ")
        lines += [
            "        @Nullable public " + java_type + " " + accessor + "() {",
            "            return " + decode_expr(domain, prop, 'value("' + prop["name"] + '")') + ";",
            "        }",
        ]
        if prop.get("enum"):
            lines += enum_constants(cap(prop["name"]) + "Values", prop["enum"], "        ", prop)
    lines += [
        "        public static final class Builder {",
        "            private final Map<String, Object> values = new LinkedHashMap<>();",
    ]
    for prop in properties:
        java_type, unused = resolve(domain, prop)
        method = ident(prop["name"])
        lines += javadoc(
            prop,
            "            ",
            "Sets the " + prop["name"] + " field.",
            params=[("value", "field value; null removes an optional value")],
            returns="this builder",
        )
        lines += deprecated_annotation(prop, "            ")
        lines += [
            "            public Builder " + method + "(@Nullable " + java_type + " value) {",
            "                if (value == null) values.remove(\"" + prop["name"] + "\");",
            "                else values.put(\"" + prop["name"] + "\", jsonValue(value));",
            "                return this;",
            "            }",
        ]
    lines.append("            public " + name + " build() {")
    for prop in properties:
        if not prop.get("optional", False):
            lines.append(
                "                if (!values.containsKey(\"" + prop["name"] + "\")) "
                + "throw new IllegalStateException(\"Missing required CDP field: " + prop["name"] + "\");"
            )
    lines += ["                return new " + name + "(values);", "            }", "        }", "    }"]
    return lines


def emit_domain(domain):
    domain_name = domain["domain"]
    lines = [
        "// GENERATED - do not edit. Run scripts/update-cdp-schema.sh.",
        "package " + package + ";",
        "",
        "import java.util.LinkedHashMap;",
        "import java.util.Map;",
        "import java.util.concurrent.CompletionStage;",
        "import java.util.function.Consumer;",
        "import javax.annotation.Nullable;",
        "import net.kurobako.cef4j.cdp.CdpClient;",
        "import net.kurobako.cef4j.cdp.CdpObject;",
        "import net.kurobako.cef4j.cdp.CdpSubscription;",
        "",
    ]
    lines += javadoc(domain, "", "Chrome DevTools Protocol " + domain_name + " domain.")
    lines.insert(len(lines) - 1, " * @see <a href=\"" + protocol_source(domain_name) + "\">Pinned protocol source</a>")
    lines += deprecated_annotation(domain, "")
    lines += [
        "@SuppressWarnings({\"JavaLangClash\", \"UnusedMethod\"})",
        "public final class " + domain_name + " {",
        "    private " + domain_name + "() {}",
        "    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }",
        "    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }",
    ]
    for item in domain.get("types", []):
        if item.get("type") == "object" and item.get("properties"):
            lines += model(model_name(domain_name, item["id"]), domain_name, item["properties"], item)
        elif item.get("enum"):
            lines += enum_constants(model_name(domain_name, item["id"]), item["enum"], spec=item)
    for command in domain.get("commands", []):
        operation = domain_name + "." + command["name"]
        lines += model(cap(command["name"]) + "Params", domain_name, command.get("parameters", []), command,
                       "Parameters for " + operation + ".")
        lines += model(cap(command["name"]) + "Result", domain_name, command.get("returns", []), command,
                       "Result of " + operation + ".")
    for event in domain.get("events", []):
        lines += model(cap(event["name"]) + "Event", domain_name, event.get("parameters", []), event,
                       "Payload of the " + domain_name + "." + event["name"] + " event.")
    lines += [
        "    public static final class Client {",
        "        private final CdpClient client;",
        "        public Client(CdpClient client) { this.client = client; }",
    ]
    for command in domain.get("commands", []):
        method = ident(command["name"])
        prefix = cap(command["name"])
        lines += javadoc(
            command,
            "        ",
            "Invokes " + domain_name + "." + command["name"] + ".",
            params=[("params", "command parameters")] if command.get("parameters") else None,
            returns="a stage completing with the command result",
        )
        lines += deprecated_annotation(command, "        ")
        if command.get("parameters"):
            lines += [
                "        public CompletionStage<" + prefix + "Result> " + method + "(" + prefix + "Params params) {",
                "            return client.call(\"" + domain_name + "." + command["name"] + "\", params, " + prefix + "Result::fromMap);",
                "        }",
            ]
        else:
            lines += [
                "        public CompletionStage<" + prefix + "Result> " + method + "() {",
                "            return client.call(\"" + domain_name + "." + command["name"] + "\", null, " + prefix + "Result::fromMap);",
                "        }",
            ]
    for event in domain.get("events", []):
        method = "on" + cap(event["name"])
        event_type = cap(event["name"]) + "Event"
        lines += javadoc(
            event,
            "        ",
            "Subscribes to " + domain_name + "." + event["name"] + ".",
            params=[("handler", "event callback")],
            returns="a removable subscription",
        )
        lines += deprecated_annotation(event, "        ")
        lines += [
            "        public CdpSubscription " + method + "(Consumer<" + event_type + "> handler) {",
            "            return client.on(\"" + domain_name + "." + event["name"] + "\", " + event_type + "::fromMap, handler);",
            "        }",
        ]
    lines += ["    }", "}", ""]
    (output / (domain_name + ".java")).write_text("\n".join(lines), encoding="utf-8")


for domain in protocol["domains"]:
    emit_domain(domain)

domain_lines = [
    "// GENERATED - do not edit. Run scripts/update-cdp-schema.sh.",
    "package " + package + ";",
    "",
    "import net.kurobako.cef4j.cdp.CdpClient;",
    "",
    "public final class CdpDomains {",
]
for domain in protocol["domains"]:
    name = domain["domain"]
    field = ident(name[:1].lower() + name[1:])
    domain_lines.append("    private final " + name + ".Client " + field + ";")
domain_lines += ["    public CdpDomains(CdpClient client) {"]
for domain in protocol["domains"]:
    name = domain["domain"]
    field = ident(name[:1].lower() + name[1:])
    domain_lines.append("        " + field + " = new " + name + ".Client(client);")
domain_lines += ["    }"]
for domain in protocol["domains"]:
    name = domain["domain"]
    field = ident(name[:1].lower() + name[1:])
    domain_lines.append("    public " + name + ".Client " + field + "() { return " + field + "; }")
domain_lines += ["}", ""]
(output / "CdpDomains.java").write_text("\n".join(domain_lines), encoding="utf-8")
