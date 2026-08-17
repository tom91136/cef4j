// GENERATED - do not edit. Regenerate via: mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.cdp.CdpSubscription;

/**
 * Chrome DevTools Protocol FileSystem domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/FileSystem.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "JavaLangClash", "MissingSummary", "UnusedMethod"})
public final class FileSystem {
    private FileSystem() {}
    @Nullable private static Long numberAsLong(@Nullable Object value) { return value == null ? null : ((Number) value).longValue(); }
    @Nullable private static Double numberAsDouble(@Nullable Object value) { return value == null ? null : ((Number) value).doubleValue(); }
    /**
     */
    public static final class File extends CdpObject {
        private File(Map<String, Object> values) { super(values); }
        @Nullable public static File fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new File(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Timestamp
         * @return the protocol field value
         */
        @Nullable public Double lastModified() {
            return numberAsDouble(value("lastModified"));
        }
        /**
         * Size in bytes
         * @return the protocol field value
         */
        @Nullable public Double size() {
            return numberAsDouble(value("size"));
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        @Nullable public String type() {
            return (String) value("type");
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the name field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Timestamp
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder lastModified(@Nullable Double value) {
                if (value == null) values.remove("lastModified");
                else values.put("lastModified", jsonValue(value));
                return this;
            }
            /**
             * Size in bytes
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder size(@Nullable Double value) {
                if (value == null) values.remove("size");
                else values.put("size", jsonValue(value));
                return this;
            }
            /**
             * Sets the type field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder type(@Nullable String value) {
                if (value == null) values.remove("type");
                else values.put("type", jsonValue(value));
                return this;
            }
            public File build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("lastModified")) throw new IllegalStateException("Missing required CDP field: lastModified");
                if (!values.containsKey("size")) throw new IllegalStateException("Missing required CDP field: size");
                if (!values.containsKey("type")) throw new IllegalStateException("Missing required CDP field: type");
                return new File(values);
            }
        }
    }
    /**
     */
    public static final class Directory extends CdpObject {
        private Directory(Map<String, Object> values) { super(values); }
        @Nullable public static Directory fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new Directory(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        @Nullable public String name() {
            return (String) value("name");
        }
        /**
         * Returns the nestedDirectories field.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> nestedDirectories() {
            return list(value("nestedDirectories"), element0 -> (String) element0);
        }
        /**
         * Files that are directly nested under this directory.
         * @return the protocol field value
         */
        @Nullable public java.util.List<FileSystem.File> nestedFiles() {
            return list(value("nestedFiles"), element0 -> FileSystem.File.fromMap(objectMap(element0)));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the name field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder name(@Nullable String value) {
                if (value == null) values.remove("name");
                else values.put("name", jsonValue(value));
                return this;
            }
            /**
             * Sets the nestedDirectories field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nestedDirectories(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("nestedDirectories");
                else values.put("nestedDirectories", jsonValue(value));
                return this;
            }
            /**
             * Files that are directly nested under this directory.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder nestedFiles(@Nullable java.util.List<FileSystem.File> value) {
                if (value == null) values.remove("nestedFiles");
                else values.put("nestedFiles", jsonValue(value));
                return this;
            }
            public Directory build() {
                if (!values.containsKey("name")) throw new IllegalStateException("Missing required CDP field: name");
                if (!values.containsKey("nestedDirectories")) throw new IllegalStateException("Missing required CDP field: nestedDirectories");
                if (!values.containsKey("nestedFiles")) throw new IllegalStateException("Missing required CDP field: nestedFiles");
                return new Directory(values);
            }
        }
    }
    /**
     */
    public static final class BucketFileSystemLocator extends CdpObject {
        private BucketFileSystemLocator(Map<String, Object> values) { super(values); }
        @Nullable public static BucketFileSystemLocator fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new BucketFileSystemLocator(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Storage key
         * @return the protocol field value
         */
        @Nullable public String storageKey() {
            return (String) value("storageKey");
        }
        /**
         * Bucket name. Not passing a {@code bucketName} will retrieve the default Bucket. (https://developer.mozilla.org/en-US/docs/Web/API/Storage_API#storage_buckets)
         * @return the protocol field value
         */
        @Nullable public String bucketName() {
            return (String) value("bucketName");
        }
        /**
         * Path to the directory using each path component as an array item.
         * @return the protocol field value
         */
        @Nullable public java.util.List<String> pathComponents() {
            return list(value("pathComponents"), element0 -> (String) element0);
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Storage key
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder storageKey(@Nullable String value) {
                if (value == null) values.remove("storageKey");
                else values.put("storageKey", jsonValue(value));
                return this;
            }
            /**
             * Bucket name. Not passing a {@code bucketName} will retrieve the default Bucket. (https://developer.mozilla.org/en-US/docs/Web/API/Storage_API#storage_buckets)
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bucketName(@Nullable String value) {
                if (value == null) values.remove("bucketName");
                else values.put("bucketName", jsonValue(value));
                return this;
            }
            /**
             * Path to the directory using each path component as an array item.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder pathComponents(@Nullable java.util.List<String> value) {
                if (value == null) values.remove("pathComponents");
                else values.put("pathComponents", jsonValue(value));
                return this;
            }
            public BucketFileSystemLocator build() {
                if (!values.containsKey("storageKey")) throw new IllegalStateException("Missing required CDP field: storageKey");
                if (!values.containsKey("pathComponents")) throw new IllegalStateException("Missing required CDP field: pathComponents");
                return new BucketFileSystemLocator(values);
            }
        }
    }
    /**
     * Parameters for FileSystem.getDirectory.
     */
    public static final class GetDirectoryParams extends CdpObject {
        private GetDirectoryParams(Map<String, Object> values) { super(values); }
        @Nullable public static GetDirectoryParams fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDirectoryParams(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the bucketFileSystemLocator field.
         * @return the protocol field value
         */
        @Nullable public FileSystem.BucketFileSystemLocator bucketFileSystemLocator() {
            return FileSystem.BucketFileSystemLocator.fromMap(objectMap(value("bucketFileSystemLocator")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Sets the bucketFileSystemLocator field.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder bucketFileSystemLocator(@Nullable FileSystem.BucketFileSystemLocator value) {
                if (value == null) values.remove("bucketFileSystemLocator");
                else values.put("bucketFileSystemLocator", jsonValue(value));
                return this;
            }
            public GetDirectoryParams build() {
                if (!values.containsKey("bucketFileSystemLocator")) throw new IllegalStateException("Missing required CDP field: bucketFileSystemLocator");
                return new GetDirectoryParams(values);
            }
        }
    }
    /**
     * Result of FileSystem.getDirectory.
     */
    public static final class GetDirectoryResult extends CdpObject {
        private GetDirectoryResult(Map<String, Object> values) { super(values); }
        @Nullable public static GetDirectoryResult fromMap(@Nullable Map<String, Object> values) {
            return values == null ? null : new GetDirectoryResult(values);
        }
        public static Builder builder() { return new Builder(); }
        /**
         * Returns the directory object at the path.
         * @return the protocol field value
         */
        @Nullable public FileSystem.Directory directory() {
            return FileSystem.Directory.fromMap(objectMap(value("directory")));
        }
        public static final class Builder {
            private final Map<String, Object> values = new LinkedHashMap<>();
            /**
             * Returns the directory object at the path.
             * @param value field value; null removes an optional value
             * @return this builder
             */
            public Builder directory(@Nullable FileSystem.Directory value) {
                if (value == null) values.remove("directory");
                else values.put("directory", jsonValue(value));
                return this;
            }
            public GetDirectoryResult build() {
                if (!values.containsKey("directory")) throw new IllegalStateException("Missing required CDP field: directory");
                return new GetDirectoryResult(values);
            }
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Invokes FileSystem.getDirectory.
         * @param params command parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<GetDirectoryResult> getDirectory(GetDirectoryParams params) {
            return client.call("FileSystem.getDirectory", params, GetDirectoryResult::fromMap);
        }
    }
}
