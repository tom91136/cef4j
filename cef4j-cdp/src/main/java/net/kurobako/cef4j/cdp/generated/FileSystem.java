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
 * Chrome DevTools Protocol FileSystem domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/FileSystem.pdl">Pinned protocol source</a>
 */
@Generated("mvn generate-sources -pl cef4j-platform -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
public final class FileSystem {
    private FileSystem() {}
    /**
     */
    public static final class File extends CdpObject {
        public File() {}
        private File(Map<String, Object> values) { super(values); }
        public static File fromMap(Map<String, Object> values) {
            return new File(values);
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Timestamp
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch lastModified() {
            return new Network.TimeSinceEpoch(((Number) require("lastModified")).doubleValue());
        }
        /**
         * Size in bytes
         * @return the protocol field value
         */
        public double size() {
            return ((Number) require("size")).doubleValue();
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public String type() {
            return (String) require("type");
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public File name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Timestamp
         * @param lastModified field value
         * @return this model
         */
        public File lastModified(Network.TimeSinceEpoch lastModified) {
            set("lastModified", lastModified);
            return this;
        }
        /**
         * Size in bytes
         * @param size field value
         * @return this model
         */
        public File size(double size) {
            set("size", size);
            return this;
        }
        /**
         * Sets the type field.
         * @param type field value
         * @return this model
         */
        public File type(String type) {
            set("type", type);
            return this;
        }
    }
    /**
     */
    public static final class Directory extends CdpObject {
        public Directory() {}
        private Directory(Map<String, Object> values) { super(values); }
        public static Directory fromMap(Map<String, Object> values) {
            return new Directory(values);
        }
        /**
         * Returns the name field.
         * @return the protocol field value
         */
        public String name() {
            return (String) require("name");
        }
        /**
         * Returns the nestedDirectories field.
         * @return the protocol field value
         */
        public java.util.List<String> nestedDirectories() {
            return CdpObject.requireList(require("nestedDirectories"), element0 -> (String) element0);
        }
        /**
         * Files that are directly nested under this directory.
         * @return the protocol field value
         */
        public java.util.List<FileSystem.File> nestedFiles() {
            return CdpObject.requireList(require("nestedFiles"), element0 -> java.util.Objects.requireNonNull(FileSystem.File.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(element0)))));
        }
        /**
         * Sets the name field.
         * @param name field value
         * @return this model
         */
        public Directory name(String name) {
            set("name", name);
            return this;
        }
        /**
         * Sets the nestedDirectories field.
         * @param nestedDirectories field value
         * @return this model
         */
        public Directory nestedDirectories(java.util.List<String> nestedDirectories) {
            set("nestedDirectories", nestedDirectories);
            return this;
        }
        /**
         * Files that are directly nested under this directory.
         * @param nestedFiles field value
         * @return this model
         */
        public Directory nestedFiles(java.util.List<FileSystem.File> nestedFiles) {
            set("nestedFiles", nestedFiles);
            return this;
        }
    }
    /**
     */
    public static final class BucketFileSystemLocator extends CdpObject {
        public BucketFileSystemLocator() {}
        private BucketFileSystemLocator(Map<String, Object> values) { super(values); }
        public static BucketFileSystemLocator fromMap(Map<String, Object> values) {
            return new BucketFileSystemLocator(values);
        }
        /**
         * Storage key
         * @return the protocol field value
         */
        public Storage.SerializedStorageKey storageKey() {
            return new Storage.SerializedStorageKey((String) require("storageKey"));
        }
        /**
         * Bucket name. Not passing a {@code bucketName} will retrieve the default Bucket. (https://developer.mozilla.org/en-US/docs/Web/API/Storage_API#storage_buckets)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> bucketName() {
            return Optional.ofNullable((String) raw("bucketName"));
        }
        /**
         * Path to the directory using each path component as an array item.
         * @return the protocol field value
         */
        public java.util.List<String> pathComponents() {
            return CdpObject.requireList(require("pathComponents"), element0 -> (String) element0);
        }
        /**
         * Storage key
         * @param storageKey field value
         * @return this model
         */
        public BucketFileSystemLocator storageKey(Storage.SerializedStorageKey storageKey) {
            set("storageKey", storageKey);
            return this;
        }
        /**
         * Bucket name. Not passing a {@code bucketName} will retrieve the default Bucket. (https://developer.mozilla.org/en-US/docs/Web/API/Storage_API#storage_buckets)
         * @param bucketName field value; empty omits the value
         * @return this model
         */
        public BucketFileSystemLocator bucketName(Optional<String> bucketName) {
            set("bucketName", bucketName.orElse(null));
            return this;
        }
        /**
         * Bucket name. Not passing a {@code bucketName} will retrieve the default Bucket. (https://developer.mozilla.org/en-US/docs/Web/API/Storage_API#storage_buckets)
         * @param bucketName field value; null removes the value
         * @return this model
         */
        public BucketFileSystemLocator bucketName(String bucketName) {
            set("bucketName", bucketName);
            return this;
        }
        /**
         * Path to the directory using each path component as an array item.
         * @param pathComponents field value
         * @return this model
         */
        public BucketFileSystemLocator pathComponents(java.util.List<String> pathComponents) {
            set("pathComponents", pathComponents);
            return this;
        }
    }
    /**
     * Request parameters for FileSystem.getDirectory.
     */
    public static final class GetDirectoryRequest extends CdpObject {
        public GetDirectoryRequest() {}
        /**
         * Creates a new GetDirectoryRequest with all required parameters.
         * @param bucketFileSystemLocator protocol value
         */
        public GetDirectoryRequest(FileSystem.BucketFileSystemLocator bucketFileSystemLocator) {
            set("bucketFileSystemLocator", bucketFileSystemLocator);
        }
        public static GetDirectoryRequest fromMap(Map<String, Object> values) {
            GetDirectoryRequest instance_ = new GetDirectoryRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the bucketFileSystemLocator field.
         * @return the protocol field value
         */
        public FileSystem.BucketFileSystemLocator bucketFileSystemLocator() {
            return java.util.Objects.requireNonNull(FileSystem.BucketFileSystemLocator.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(require("bucketFileSystemLocator")))));
        }
        /**
         * Sets the bucketFileSystemLocator field.
         * @param bucketFileSystemLocator field value
         * @return this model
         */
        public GetDirectoryRequest bucketFileSystemLocator(FileSystem.BucketFileSystemLocator bucketFileSystemLocator) {
            set("bucketFileSystemLocator", bucketFileSystemLocator);
            return this;
        }
    }
    public static final class Client {
        private final CdpClient client;
        public Client(CdpClient client) { this.client = client; }
        /**
         * Invokes FileSystem.getDirectory.
         * @param bucketFileSystemLocator protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<FileSystem.Directory> getDirectory(FileSystem.BucketFileSystemLocator bucketFileSystemLocator) {
            Map<String, Object> params = new LinkedHashMap<>();
            params.put("bucketFileSystemLocator", CdpObject.json(bucketFileSystemLocator));
            return client.call("FileSystem.getDirectory", params, result_ -> java.util.Objects.requireNonNull(FileSystem.Directory.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("directory")))))));
        }
        /**
         * Invokes FileSystem.getDirectory with a request object.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<FileSystem.Directory> getDirectory(GetDirectoryRequest request) {
            return client.call("FileSystem.getDirectory", request == null ? null : request.toMap(), result_ -> java.util.Objects.requireNonNull(FileSystem.Directory.fromMap(java.util.Objects.requireNonNull(CdpObject.objectMap(java.util.Objects.requireNonNull(result_.get("directory")))))));
        }
    }
}
