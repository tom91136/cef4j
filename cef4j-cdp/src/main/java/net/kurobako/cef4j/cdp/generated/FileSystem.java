// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.cdp.generated;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import javax.annotation.processing.Generated;
import net.kurobako.cef4j.cdp.CdpClient;
import net.kurobako.cef4j.cdp.CdpDomainClient;
import net.kurobako.cef4j.cdp.CdpField;
import net.kurobako.cef4j.cdp.CdpObject;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Chrome DevTools Protocol FileSystem domain.
 * <p><b>Experimental:</b> this part of CDP may change without notice.
 * @see <a href="https://chromium.googlesource.com/chromium/src/+/refs/tags/150.0.7871.213/third_party/blink/public/devtools_protocol/domains/FileSystem.pdl">Pinned protocol source</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-cdp -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@SuppressWarnings({"EscapedEntity", "InvalidParam", "JavaLangClash", "MissingSummary", "Unchecked", "UnusedMethod"})
@NullableBoundary("CDP wire objects use null for absent optional fields")
public final class FileSystem {
    private FileSystem() {}
    private static final CdpField<FileSystem.BucketFileSystemLocator> BUCKET_FILE_SYSTEM_LOCATOR = CdpField.of("bucketFileSystemLocator", value -> java.util.Objects.requireNonNull(FileSystem.BucketFileSystemLocator.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<FileSystem.Directory> DIRECTORY = CdpField.of("directory", value -> java.util.Objects.requireNonNull(FileSystem.Directory.fromMap(CdpObject.requireObjectMap(value))));
    private static final CdpField<Network.TimeSinceEpoch> LAST_MODIFIED = CdpField.of("lastModified", value -> new Network.TimeSinceEpoch(((Number) value).doubleValue()));
    private static final CdpField<java.util.List<String>> NESTED_DIRECTORIES = CdpField.of("nestedDirectories", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<java.util.List<FileSystem.File>> NESTED_FILES = CdpField.of("nestedFiles", value -> CdpObject.requireList(value, element0 -> java.util.Objects.requireNonNull(FileSystem.File.fromMap(CdpObject.requireObjectMap(element0)))));
    private static final CdpField<java.util.List<String>> PATH_COMPONENTS = CdpField.of("pathComponents", value -> CdpObject.requireList(value, element0 -> (String) element0));
    private static final CdpField<Storage.SerializedStorageKey> STORAGE_KEY = CdpField.of("storageKey", value -> new Storage.SerializedStorageKey((String) value));
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
            return requireString("name");
        }
        /**
         * Timestamp
         * @return the protocol field value
         */
        public Network.TimeSinceEpoch lastModified() {
            return require(LAST_MODIFIED);
        }
        /**
         * Size in bytes
         * @return the protocol field value
         */
        public double size() {
            return requireDouble("size");
        }
        /**
         * Returns the type field.
         * @return the protocol field value
         */
        public String type() {
            return requireString("type");
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
            return requireString("name");
        }
        /**
         * Returns the nestedDirectories field.
         * @return the protocol field value
         */
        public java.util.List<String> nestedDirectories() {
            return require(NESTED_DIRECTORIES);
        }
        /**
         * Files that are directly nested under this directory.
         * @return the protocol field value
         */
        public java.util.List<FileSystem.File> nestedFiles() {
            return require(NESTED_FILES);
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
            return require(STORAGE_KEY);
        }
        /**
         * Bucket name. Not passing a {@code bucketName} will retrieve the default Bucket. (https://developer.mozilla.org/en-US/docs/Web/API/Storage_API#storage_buckets)
         * @return the protocol field value, empty when absent
         */
        public Optional<String> bucketName() {
            return optionalString("bucketName");
        }
        /**
         * Path to the directory using each path component as an array item.
         * @return the protocol field value
         */
        public java.util.List<String> pathComponents() {
            return require(PATH_COMPONENTS);
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
         * @param bucketName field value
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
        public static GetDirectoryRequest fromMap(Map<String, Object> values) {
            final GetDirectoryRequest instance_ = new GetDirectoryRequest();
            if (values != null) instance_.values.putAll(values);
            return instance_;
        }
        /**
         * Returns the bucketFileSystemLocator field.
         * @return the protocol field value
         */
        public FileSystem.BucketFileSystemLocator bucketFileSystemLocator() {
            return require(BUCKET_FILE_SYSTEM_LOCATOR);
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
    public static final class Client extends CdpDomainClient {
        public Client(CdpClient client) { super(client); }
        /**
         * Invokes FileSystem.getDirectory.
         * @param bucketFileSystemLocator protocol value
         * @return a stage completing with the command result
         */
        public CompletionStage<FileSystem.Directory> getDirectory(FileSystem.BucketFileSystemLocator bucketFileSystemLocator) {
            return call("FileSystem.getDirectory", params("bucketFileSystemLocator", bucketFileSystemLocator), DIRECTORY::require);
        }
        /**
         * Invokes FileSystem.getDirectory with a request object.
         * @param request request parameters
         * @return a stage completing with the command result
         */
        public CompletionStage<FileSystem.Directory> getDirectory(GetDirectoryRequest request) {
            return call("FileSystem.getDirectory", request, DIRECTORY::require);
        }
    }
}
