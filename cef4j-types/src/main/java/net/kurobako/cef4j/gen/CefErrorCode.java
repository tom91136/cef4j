// GENERATED - do not edit. Regenerate via: ./mvnw generate-sources -pl cef4j-types
// -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213
package net.kurobako.cef4j.gen;

import javax.annotation.processing.Generated;
import net.kurobako.cef4j.policy.NullableBoundary;

/**
 * Supported error code values. For the complete list of error values see "include/base/internal/cef_net_error_list.h".
 *
 * <p>Definition generated from cef_types.h
 *
 * <pre>typedef enum {
 *   ERR_NONE = 0,
 *   ERR_IO_PENDING = -1,
 *   ERR_FAILED = -2,
 *   ERR_ABORTED = -3,
 *   ERR_INVALID_ARGUMENT = -4,
 *   ...
 * } cef_errorcode_t;</pre>
 *
 * <p>Possible values: {@link Kind#NONE}, {@link Kind#IO_PENDING}, {@link Kind#FAILED}, {@link Kind#ABORTED},
 * {@link Kind#INVALID_ARGUMENT}, {@link Kind#INVALID_HANDLE}, {@link Kind#FILE_NOT_FOUND}, {@link Kind#TIMED_OUT},
 * {@link Kind#FILE_TOO_BIG}, {@link Kind#UNEXPECTED}, {@link Kind#ACCESS_DENIED}, {@link Kind#NOT_IMPLEMENTED},
 * {@link Kind#INSUFFICIENT_RESOURCES}, {@link Kind#OUT_OF_MEMORY}, {@link Kind#UPLOAD_FILE_CHANGED},
 * {@link Kind#SOCKET_NOT_CONNECTED}, {@link Kind#FILE_EXISTS}, {@link Kind#FILE_PATH_TOO_LONG},
 * {@link Kind#FILE_NO_SPACE}, {@link Kind#FILE_VIRUS_INFECTED}, {@link Kind#BLOCKED_BY_CLIENT},
 * {@link Kind#NETWORK_CHANGED}, {@link Kind#BLOCKED_BY_ADMINISTRATOR}, {@link Kind#SOCKET_IS_CONNECTED},
 * {@link Kind#UPLOAD_STREAM_REWIND_NOT_SUPPORTED}, {@link Kind#CONTEXT_SHUT_DOWN}, {@link Kind#BLOCKED_BY_RESPONSE},
 * {@link Kind#CLEARTEXT_NOT_PERMITTED}, {@link Kind#BLOCKED_BY_CSP}, {@link Kind#BLOCKED_BY_ORB},
 * {@link Kind#NETWORK_ACCESS_REVOKED}, {@link Kind#BLOCKED_BY_FINGERPRINTING_PROTECTION},
 * {@link Kind#BLOCKED_IN_INCOGNITO_BY_ADMINISTRATOR}, {@link Kind#LOCAL_NETWORK_PERMISSION_MISSING},
 * {@link Kind#STRICT_ECH_REQUIRED}, {@link Kind#CONNECTION_CLOSED}, {@link Kind#CONNECTION_RESET},
 * {@link Kind#CONNECTION_REFUSED}, {@link Kind#CONNECTION_ABORTED}, {@link Kind#CONNECTION_FAILED},
 * {@link Kind#NAME_NOT_RESOLVED}, {@link Kind#INTERNET_DISCONNECTED}, {@link Kind#SSL_PROTOCOL_ERROR},
 * {@link Kind#ADDRESS_INVALID}, {@link Kind#ADDRESS_UNREACHABLE}, {@link Kind#SSL_CLIENT_AUTH_CERT_NEEDED},
 * {@link Kind#TUNNEL_CONNECTION_FAILED}, {@link Kind#SSL_VERSION_OR_CIPHER_MISMATCH},
 * {@link Kind#SSL_RENEGOTIATION_REQUESTED}, {@link Kind#PROXY_AUTH_UNSUPPORTED}, {@link Kind#BAD_SSL_CLIENT_AUTH_CERT},
 * {@link Kind#CONNECTION_TIMED_OUT}, {@link Kind#HOST_RESOLVER_QUEUE_TOO_LARGE}, {@link Kind#SOCKS_CONNECTION_FAILED},
 * {@link Kind#SOCKS_CONNECTION_HOST_UNREACHABLE}, {@link Kind#ALPN_NEGOTIATION_FAILED},
 * {@link Kind#SSL_NO_RENEGOTIATION}, {@link Kind#WINSOCK_UNEXPECTED_WRITTEN_BYTES},
 * {@link Kind#SSL_DECOMPRESSION_FAILURE_ALERT}, {@link Kind#SSL_BAD_RECORD_MAC_ALERT},
 * {@link Kind#PROXY_AUTH_REQUESTED}, {@link Kind#PROXY_CONNECTION_FAILED},
 * {@link Kind#MANDATORY_PROXY_CONFIGURATION_FAILED}, {@link Kind#PRECONNECT_MAX_SOCKET_LIMIT},
 * {@link Kind#SSL_CLIENT_AUTH_PRIVATE_KEY_ACCESS_DENIED}, {@link Kind#SSL_CLIENT_AUTH_CERT_NO_PRIVATE_KEY},
 * {@link Kind#PROXY_CERTIFICATE_INVALID}, {@link Kind#NAME_RESOLUTION_FAILED}, {@link Kind#NETWORK_ACCESS_DENIED},
 * {@link Kind#TEMPORARILY_THROTTLED}, {@link Kind#SSL_CLIENT_AUTH_SIGNATURE_FAILED}, {@link Kind#MSG_TOO_BIG},
 * {@link Kind#WS_PROTOCOL_ERROR}, {@link Kind#ADDRESS_IN_USE}, {@link Kind#SSL_PINNED_KEY_NOT_IN_CERT_CHAIN},
 * {@link Kind#CLIENT_AUTH_CERT_TYPE_UNSUPPORTED}, {@link Kind#SSL_DECRYPT_ERROR_ALERT},
 * {@link Kind#WS_THROTTLE_QUEUE_TOO_LARGE}, {@link Kind#SSL_SERVER_CERT_CHANGED},
 * {@link Kind#SSL_UNRECOGNIZED_NAME_ALERT}, {@link Kind#SOCKET_SET_RECEIVE_BUFFER_SIZE_ERROR},
 * {@link Kind#SOCKET_SET_SEND_BUFFER_SIZE_ERROR}, {@link Kind#SOCKET_RECEIVE_BUFFER_SIZE_UNCHANGEABLE},
 * {@link Kind#SOCKET_SEND_BUFFER_SIZE_UNCHANGEABLE}, {@link Kind#SSL_CLIENT_AUTH_CERT_BAD_FORMAT},
 * {@link Kind#ICANN_NAME_COLLISION}, {@link Kind#SSL_SERVER_CERT_BAD_FORMAT}, {@link Kind#CT_STH_PARSING_FAILED},
 * {@link Kind#CT_STH_INCOMPLETE}, {@link Kind#UNABLE_TO_REUSE_CONNECTION_FOR_PROXY_AUTH},
 * {@link Kind#CT_CONSISTENCY_PROOF_PARSING_FAILED}, {@link Kind#SSL_OBSOLETE_CIPHER}, {@link Kind#WS_UPGRADE},
 * {@link Kind#READ_IF_READY_NOT_IMPLEMENTED}, {@link Kind#NO_BUFFER_SPACE},
 * {@link Kind#SSL_CLIENT_AUTH_NO_COMMON_ALGORITHMS}, {@link Kind#EARLY_DATA_REJECTED},
 * {@link Kind#WRONG_VERSION_ON_EARLY_DATA}, {@link Kind#TLS13_DOWNGRADE_DETECTED},
 * {@link Kind#SSL_KEY_USAGE_INCOMPATIBLE}, {@link Kind#INVALID_ECH_CONFIG_LIST}, {@link Kind#ECH_NOT_NEGOTIATED},
 * {@link Kind#ECH_FALLBACK_CERTIFICATE_INVALID}, {@link Kind#PROXY_UNABLE_TO_CONNECT_TO_DESTINATION},
 * {@link Kind#PROXY_DELEGATE_CANCELED_CONNECT_REQUEST}, {@link Kind#PROXY_DELEGATE_CANCELED_CONNECT_RESPONSE},
 * {@link Kind#CERT_COMMON_NAME_INVALID}, {@link Kind#CERT_DATE_INVALID}, {@link Kind#CERT_AUTHORITY_INVALID},
 * {@link Kind#CERT_CONTAINS_ERRORS}, {@link Kind#CERT_NO_REVOCATION_MECHANISM},
 * {@link Kind#CERT_UNABLE_TO_CHECK_REVOCATION}, {@link Kind#CERT_REVOKED}, {@link Kind#CERT_INVALID},
 * {@link Kind#CERT_WEAK_SIGNATURE_ALGORITHM}, {@link Kind#CERT_NON_UNIQUE_NAME}, {@link Kind#CERT_WEAK_KEY},
 * {@link Kind#CERT_NAME_CONSTRAINT_VIOLATION}, {@link Kind#CERT_VALIDITY_TOO_LONG},
 * {@link Kind#CERTIFICATE_TRANSPARENCY_REQUIRED}, {@link Kind#CERT_KNOWN_INTERCEPTION_BLOCKED},
 * {@link Kind#CERT_SELF_SIGNED_LOCAL_NETWORK}, {@link Kind#CERT_END}, {@link Kind#INVALID_URL},
 * {@link Kind#DISALLOWED_URL_SCHEME}, {@link Kind#UNKNOWN_URL_SCHEME}, {@link Kind#INVALID_REDIRECT},
 * {@link Kind#TOO_MANY_REDIRECTS}, {@link Kind#UNSAFE_REDIRECT}, {@link Kind#UNSAFE_PORT},
 * {@link Kind#INVALID_RESPONSE}, {@link Kind#INVALID_CHUNKED_ENCODING}, {@link Kind#METHOD_NOT_SUPPORTED},
 * {@link Kind#UNEXPECTED_PROXY_AUTH}, {@link Kind#EMPTY_RESPONSE}, {@link Kind#RESPONSE_HEADERS_TOO_BIG},
 * {@link Kind#PAC_SCRIPT_FAILED}, {@link Kind#REQUEST_RANGE_NOT_SATISFIABLE}, {@link Kind#MALFORMED_IDENTITY},
 * {@link Kind#CONTENT_DECODING_FAILED}, {@link Kind#NETWORK_IO_SUSPENDED}, {@link Kind#NO_SUPPORTED_PROXIES},
 * {@link Kind#HTTP2_PROTOCOL_ERROR}, {@link Kind#INVALID_AUTH_CREDENTIALS}, {@link Kind#UNSUPPORTED_AUTH_SCHEME},
 * {@link Kind#ENCODING_DETECTION_FAILED}, {@link Kind#MISSING_AUTH_CREDENTIALS},
 * {@link Kind#UNEXPECTED_SECURITY_LIBRARY_STATUS}, {@link Kind#MISCONFIGURED_AUTH_ENVIRONMENT},
 * {@link Kind#UNDOCUMENTED_SECURITY_LIBRARY_STATUS}, {@link Kind#RESPONSE_BODY_TOO_BIG_TO_DRAIN},
 * {@link Kind#RESPONSE_HEADERS_MULTIPLE_CONTENT_LENGTH}, {@link Kind#INCOMPLETE_HTTP2_HEADERS},
 * {@link Kind#PAC_NOT_IN_DHCP}, {@link Kind#RESPONSE_HEADERS_MULTIPLE_CONTENT_DISPOSITION},
 * {@link Kind#RESPONSE_HEADERS_MULTIPLE_LOCATION}, {@link Kind#HTTP2_SERVER_REFUSED_STREAM},
 * {@link Kind#HTTP2_PING_FAILED}, {@link Kind#CONTENT_LENGTH_MISMATCH}, {@link Kind#INCOMPLETE_CHUNKED_ENCODING},
 * {@link Kind#QUIC_PROTOCOL_ERROR}, {@link Kind#RESPONSE_HEADERS_TRUNCATED}, {@link Kind#QUIC_HANDSHAKE_FAILED},
 * {@link Kind#HTTP2_INADEQUATE_TRANSPORT_SECURITY}, {@link Kind#HTTP2_FLOW_CONTROL_ERROR},
 * {@link Kind#HTTP2_FRAME_SIZE_ERROR}, {@link Kind#HTTP2_COMPRESSION_ERROR},
 * {@link Kind#PROXY_AUTH_REQUESTED_WITH_NO_CONNECTION}, {@link Kind#HTTP_1_1_REQUIRED},
 * {@link Kind#PROXY_HTTP_1_1_REQUIRED}, {@link Kind#PAC_SCRIPT_TERMINATED}, {@link Kind#INVALID_HTTP_RESPONSE},
 * {@link Kind#CONTENT_DECODING_INIT_FAILED}, {@link Kind#HTTP2_RST_STREAM_NO_ERROR_RECEIVED},
 * {@link Kind#TOO_MANY_RETRIES}, {@link Kind#HTTP2_STREAM_CLOSED}, {@link Kind#HTTP_RESPONSE_CODE_FAILURE},
 * {@link Kind#QUIC_CERT_ROOT_NOT_KNOWN}, {@link Kind#QUIC_GOAWAY_REQUEST_CAN_BE_RETRIED},
 * {@link Kind#TOO_MANY_ACCEPT_CH_RESTARTS}, {@link Kind#INCONSISTENT_IP_ADDRESS_SPACE},
 * {@link Kind#CACHED_IP_ADDRESS_SPACE_BLOCKED_BY_LOCAL_NETWORK_ACCESS_POLICY},
 * {@link Kind#BLOCKED_BY_LOCAL_NETWORK_ACCESS_CHECKS}, {@link Kind#ZSTD_WINDOW_SIZE_TOO_BIG},
 * {@link Kind#DICTIONARY_LOAD_FAILED}, {@link Kind#UNEXPECTED_CONTENT_DICTIONARY_HEADER}, {@link Kind#CACHE_MISS},
 * {@link Kind#CACHE_READ_FAILURE}, {@link Kind#CACHE_WRITE_FAILURE}, {@link Kind#CACHE_OPERATION_NOT_SUPPORTED},
 * {@link Kind#CACHE_OPEN_FAILURE}, {@link Kind#CACHE_CREATE_FAILURE}, {@link Kind#CACHE_RACE},
 * {@link Kind#CACHE_CHECKSUM_READ_FAILURE}, {@link Kind#CACHE_CHECKSUM_MISMATCH}, {@link Kind#CACHE_LOCK_TIMEOUT},
 * {@link Kind#CACHE_AUTH_FAILURE_AFTER_READ}, {@link Kind#CACHE_ENTRY_NOT_SUITABLE}, {@link Kind#CACHE_DOOM_FAILURE},
 * {@link Kind#CACHE_OPEN_OR_CREATE_FAILURE}, {@link Kind#CACHE_COMPRESSION_FAILURE}, {@link Kind#INSECURE_RESPONSE},
 * {@link Kind#NO_PRIVATE_KEY_FOR_CERT}, {@link Kind#ADD_USER_CERT_FAILED}, {@link Kind#INVALID_SIGNED_EXCHANGE},
 * {@link Kind#INVALID_WEB_BUNDLE}, {@link Kind#TRUST_TOKEN_OPERATION_FAILED},
 * {@link Kind#TRUST_TOKEN_OPERATION_SUCCESS_WITHOUT_SENDING_REQUEST}, {@link Kind#HTTPENGINE_PROVIDER_IN_USE},
 * {@link Kind#PKCS12_IMPORT_BAD_PASSWORD}, {@link Kind#PKCS12_IMPORT_FAILED}, {@link Kind#IMPORT_CA_CERT_NOT_CA},
 * {@link Kind#IMPORT_CERT_ALREADY_EXISTS}, {@link Kind#IMPORT_CA_CERT_FAILED}, {@link Kind#IMPORT_SERVER_CERT_FAILED},
 * {@link Kind#PKCS12_IMPORT_INVALID_MAC}, {@link Kind#PKCS12_IMPORT_INVALID_FILE},
 * {@link Kind#PKCS12_IMPORT_UNSUPPORTED}, {@link Kind#KEY_GENERATION_FAILED}, {@link Kind#PRIVATE_KEY_EXPORT_FAILED},
 * {@link Kind#SELF_SIGNED_CERT_GENERATION_FAILED}, {@link Kind#CERT_DATABASE_CHANGED},
 * {@link Kind#CERT_VERIFIER_CHANGED}, {@link Kind#DNS_MALFORMED_RESPONSE}, {@link Kind#DNS_SERVER_REQUIRES_TCP},
 * {@link Kind#DNS_TIMED_OUT}, {@link Kind#DNS_CACHE_MISS}, {@link Kind#DNS_SEARCH_EMPTY}, {@link Kind#DNS_SORT_ERROR},
 * {@link Kind#DNS_SECURE_RESOLVER_HOSTNAME_RESOLUTION_FAILED}, {@link Kind#DNS_NAME_HTTPS_ONLY},
 * {@link Kind#DNS_REQUEST_CANCELLED}, {@link Kind#DNS_NO_MATCHING_SUPPORTED_ALPN},
 * {@link Kind#DNS_SECURE_PROBE_RECORD_INVALID}, {@link Kind#DNS_CACHE_INVALIDATION_IN_PROGRESS},
 * {@link Kind#DNS_FORMAT_ERROR}, {@link Kind#DNS_SERVER_FAILURE}, {@link Kind#DNS_NOT_IMPLEMENTED},
 * {@link Kind#DNS_REFUSED}, {@link Kind#DNS_OTHER_FAILURE}, {@link Kind#BLOB_INVALID_CONSTRUCTION_ARGUMENTS},
 * {@link Kind#BLOB_OUT_OF_MEMORY}, {@link Kind#BLOB_FILE_WRITE_FAILED}, {@link Kind#BLOB_SOURCE_DIED_IN_TRANSIT},
 * {@link Kind#BLOB_DEREFERENCED_WHILE_BUILDING}, {@link Kind#BLOB_REFERENCED_BLOB_BROKEN},
 * {@link Kind#BLOB_REFERENCED_FILE_UNAVAILABLE}
 *
 * @see <a href="https://cef-builds.spotifycdn.com/docs/150.0/cef__types_8h.html">cef_types.h</a>
 */
@Generated("./mvnw generate-sources -pl cef4j-types -Dcef.version=150.0.18+gdb11278+chromium-150.0.7871.213")
@NullableBoundary("generated external ABI")
public final class CefErrorCode implements CefEnum<CefErrorCode> {

    /** Known constants for {@link CefErrorCode}. */
    public enum Kind {
        NONE(new EnumConstant(0, "0", "ERR_NONE")),
        IO_PENDING(new EnumConstant(-1, "- 1", "ERR_IO_PENDING")),
        FAILED(new EnumConstant(-2, "- 2", "ERR_FAILED")),
        ABORTED(new EnumConstant(-3, "- 3", "ERR_ABORTED")),
        INVALID_ARGUMENT(new EnumConstant(-4, "- 4", "ERR_INVALID_ARGUMENT")),
        INVALID_HANDLE(new EnumConstant(-5, "- 5", "ERR_INVALID_HANDLE")),
        FILE_NOT_FOUND(new EnumConstant(-6, "- 6", "ERR_FILE_NOT_FOUND")),
        TIMED_OUT(new EnumConstant(-7, "- 7", "ERR_TIMED_OUT")),
        FILE_TOO_BIG(new EnumConstant(-8, "- 8", "ERR_FILE_TOO_BIG")),
        UNEXPECTED(new EnumConstant(-9, "- 9", "ERR_UNEXPECTED")),
        ACCESS_DENIED(new EnumConstant(-10, "- 10", "ERR_ACCESS_DENIED")),
        NOT_IMPLEMENTED(new EnumConstant(-11, "- 11", "ERR_NOT_IMPLEMENTED")),
        INSUFFICIENT_RESOURCES(new EnumConstant(-12, "- 12", "ERR_INSUFFICIENT_RESOURCES")),
        OUT_OF_MEMORY(new EnumConstant(-13, "- 13", "ERR_OUT_OF_MEMORY")),
        UPLOAD_FILE_CHANGED(new EnumConstant(-14, "- 14", "ERR_UPLOAD_FILE_CHANGED")),
        SOCKET_NOT_CONNECTED(new EnumConstant(-15, "- 15", "ERR_SOCKET_NOT_CONNECTED")),
        FILE_EXISTS(new EnumConstant(-16, "- 16", "ERR_FILE_EXISTS")),
        FILE_PATH_TOO_LONG(new EnumConstant(-17, "- 17", "ERR_FILE_PATH_TOO_LONG")),
        FILE_NO_SPACE(new EnumConstant(-18, "- 18", "ERR_FILE_NO_SPACE")),
        FILE_VIRUS_INFECTED(new EnumConstant(-19, "- 19", "ERR_FILE_VIRUS_INFECTED")),
        BLOCKED_BY_CLIENT(new EnumConstant(-20, "- 20", "ERR_BLOCKED_BY_CLIENT")),
        NETWORK_CHANGED(new EnumConstant(-21, "- 21", "ERR_NETWORK_CHANGED")),
        BLOCKED_BY_ADMINISTRATOR(new EnumConstant(-22, "- 22", "ERR_BLOCKED_BY_ADMINISTRATOR")),
        SOCKET_IS_CONNECTED(new EnumConstant(-23, "- 23", "ERR_SOCKET_IS_CONNECTED")),
        UPLOAD_STREAM_REWIND_NOT_SUPPORTED(new EnumConstant(-25, "- 25", "ERR_UPLOAD_STREAM_REWIND_NOT_SUPPORTED")),
        CONTEXT_SHUT_DOWN(new EnumConstant(-26, "- 26", "ERR_CONTEXT_SHUT_DOWN")),
        BLOCKED_BY_RESPONSE(new EnumConstant(-27, "- 27", "ERR_BLOCKED_BY_RESPONSE")),
        CLEARTEXT_NOT_PERMITTED(new EnumConstant(-29, "- 29", "ERR_CLEARTEXT_NOT_PERMITTED")),
        BLOCKED_BY_CSP(new EnumConstant(-30, "- 30", "ERR_BLOCKED_BY_CSP")),
        BLOCKED_BY_ORB(new EnumConstant(-32, "- 32", "ERR_BLOCKED_BY_ORB")),
        NETWORK_ACCESS_REVOKED(new EnumConstant(-33, "- 33", "ERR_NETWORK_ACCESS_REVOKED")),
        BLOCKED_BY_FINGERPRINTING_PROTECTION(new EnumConstant(-34, "- 34", "ERR_BLOCKED_BY_FINGERPRINTING_PROTECTION")),
        BLOCKED_IN_INCOGNITO_BY_ADMINISTRATOR(
                new EnumConstant(-35, "- 35", "ERR_BLOCKED_IN_INCOGNITO_BY_ADMINISTRATOR")),
        LOCAL_NETWORK_PERMISSION_MISSING(new EnumConstant(-36, "- 36", "ERR_LOCAL_NETWORK_PERMISSION_MISSING")),
        STRICT_ECH_REQUIRED(new EnumConstant(-37, "- 37", "ERR_STRICT_ECH_REQUIRED")),
        CONNECTION_CLOSED(new EnumConstant(-100, "- 100", "ERR_CONNECTION_CLOSED")),
        CONNECTION_RESET(new EnumConstant(-101, "- 101", "ERR_CONNECTION_RESET")),
        CONNECTION_REFUSED(new EnumConstant(-102, "- 102", "ERR_CONNECTION_REFUSED")),
        CONNECTION_ABORTED(new EnumConstant(-103, "- 103", "ERR_CONNECTION_ABORTED")),
        CONNECTION_FAILED(new EnumConstant(-104, "- 104", "ERR_CONNECTION_FAILED")),
        NAME_NOT_RESOLVED(new EnumConstant(-105, "- 105", "ERR_NAME_NOT_RESOLVED")),
        INTERNET_DISCONNECTED(new EnumConstant(-106, "- 106", "ERR_INTERNET_DISCONNECTED")),
        SSL_PROTOCOL_ERROR(new EnumConstant(-107, "- 107", "ERR_SSL_PROTOCOL_ERROR")),
        ADDRESS_INVALID(new EnumConstant(-108, "- 108", "ERR_ADDRESS_INVALID")),
        ADDRESS_UNREACHABLE(new EnumConstant(-109, "- 109", "ERR_ADDRESS_UNREACHABLE")),
        SSL_CLIENT_AUTH_CERT_NEEDED(new EnumConstant(-110, "- 110", "ERR_SSL_CLIENT_AUTH_CERT_NEEDED")),
        TUNNEL_CONNECTION_FAILED(new EnumConstant(-111, "- 111", "ERR_TUNNEL_CONNECTION_FAILED")),
        SSL_VERSION_OR_CIPHER_MISMATCH(new EnumConstant(-113, "- 113", "ERR_SSL_VERSION_OR_CIPHER_MISMATCH")),
        SSL_RENEGOTIATION_REQUESTED(new EnumConstant(-114, "- 114", "ERR_SSL_RENEGOTIATION_REQUESTED")),
        PROXY_AUTH_UNSUPPORTED(new EnumConstant(-115, "- 115", "ERR_PROXY_AUTH_UNSUPPORTED")),
        BAD_SSL_CLIENT_AUTH_CERT(new EnumConstant(-117, "- 117", "ERR_BAD_SSL_CLIENT_AUTH_CERT")),
        CONNECTION_TIMED_OUT(new EnumConstant(-118, "- 118", "ERR_CONNECTION_TIMED_OUT")),
        HOST_RESOLVER_QUEUE_TOO_LARGE(new EnumConstant(-119, "- 119", "ERR_HOST_RESOLVER_QUEUE_TOO_LARGE")),
        SOCKS_CONNECTION_FAILED(new EnumConstant(-120, "- 120", "ERR_SOCKS_CONNECTION_FAILED")),
        SOCKS_CONNECTION_HOST_UNREACHABLE(new EnumConstant(-121, "- 121", "ERR_SOCKS_CONNECTION_HOST_UNREACHABLE")),
        ALPN_NEGOTIATION_FAILED(new EnumConstant(-122, "- 122", "ERR_ALPN_NEGOTIATION_FAILED")),
        SSL_NO_RENEGOTIATION(new EnumConstant(-123, "- 123", "ERR_SSL_NO_RENEGOTIATION")),
        WINSOCK_UNEXPECTED_WRITTEN_BYTES(new EnumConstant(-124, "- 124", "ERR_WINSOCK_UNEXPECTED_WRITTEN_BYTES")),
        SSL_DECOMPRESSION_FAILURE_ALERT(new EnumConstant(-125, "- 125", "ERR_SSL_DECOMPRESSION_FAILURE_ALERT")),
        SSL_BAD_RECORD_MAC_ALERT(new EnumConstant(-126, "- 126", "ERR_SSL_BAD_RECORD_MAC_ALERT")),
        PROXY_AUTH_REQUESTED(new EnumConstant(-127, "- 127", "ERR_PROXY_AUTH_REQUESTED")),
        PROXY_CONNECTION_FAILED(new EnumConstant(-130, "- 130", "ERR_PROXY_CONNECTION_FAILED")),
        MANDATORY_PROXY_CONFIGURATION_FAILED(
                new EnumConstant(-131, "- 131", "ERR_MANDATORY_PROXY_CONFIGURATION_FAILED")),
        PRECONNECT_MAX_SOCKET_LIMIT(new EnumConstant(-133, "- 133", "ERR_PRECONNECT_MAX_SOCKET_LIMIT")),
        SSL_CLIENT_AUTH_PRIVATE_KEY_ACCESS_DENIED(
                new EnumConstant(-134, "- 134", "ERR_SSL_CLIENT_AUTH_PRIVATE_KEY_ACCESS_DENIED")),
        SSL_CLIENT_AUTH_CERT_NO_PRIVATE_KEY(new EnumConstant(-135, "- 135", "ERR_SSL_CLIENT_AUTH_CERT_NO_PRIVATE_KEY")),
        PROXY_CERTIFICATE_INVALID(new EnumConstant(-136, "- 136", "ERR_PROXY_CERTIFICATE_INVALID")),
        NAME_RESOLUTION_FAILED(new EnumConstant(-137, "- 137", "ERR_NAME_RESOLUTION_FAILED")),
        NETWORK_ACCESS_DENIED(new EnumConstant(-138, "- 138", "ERR_NETWORK_ACCESS_DENIED")),
        TEMPORARILY_THROTTLED(new EnumConstant(-139, "- 139", "ERR_TEMPORARILY_THROTTLED")),
        SSL_CLIENT_AUTH_SIGNATURE_FAILED(new EnumConstant(-141, "- 141", "ERR_SSL_CLIENT_AUTH_SIGNATURE_FAILED")),
        MSG_TOO_BIG(new EnumConstant(-142, "- 142", "ERR_MSG_TOO_BIG")),
        WS_PROTOCOL_ERROR(new EnumConstant(-145, "- 145", "ERR_WS_PROTOCOL_ERROR")),
        ADDRESS_IN_USE(new EnumConstant(-147, "- 147", "ERR_ADDRESS_IN_USE")),
        SSL_PINNED_KEY_NOT_IN_CERT_CHAIN(new EnumConstant(-150, "- 150", "ERR_SSL_PINNED_KEY_NOT_IN_CERT_CHAIN")),
        CLIENT_AUTH_CERT_TYPE_UNSUPPORTED(new EnumConstant(-151, "- 151", "ERR_CLIENT_AUTH_CERT_TYPE_UNSUPPORTED")),
        SSL_DECRYPT_ERROR_ALERT(new EnumConstant(-153, "- 153", "ERR_SSL_DECRYPT_ERROR_ALERT")),
        WS_THROTTLE_QUEUE_TOO_LARGE(new EnumConstant(-154, "- 154", "ERR_WS_THROTTLE_QUEUE_TOO_LARGE")),
        SSL_SERVER_CERT_CHANGED(new EnumConstant(-156, "- 156", "ERR_SSL_SERVER_CERT_CHANGED")),
        SSL_UNRECOGNIZED_NAME_ALERT(new EnumConstant(-159, "- 159", "ERR_SSL_UNRECOGNIZED_NAME_ALERT")),
        SOCKET_SET_RECEIVE_BUFFER_SIZE_ERROR(
                new EnumConstant(-160, "- 160", "ERR_SOCKET_SET_RECEIVE_BUFFER_SIZE_ERROR")),
        SOCKET_SET_SEND_BUFFER_SIZE_ERROR(new EnumConstant(-161, "- 161", "ERR_SOCKET_SET_SEND_BUFFER_SIZE_ERROR")),
        SOCKET_RECEIVE_BUFFER_SIZE_UNCHANGEABLE(
                new EnumConstant(-162, "- 162", "ERR_SOCKET_RECEIVE_BUFFER_SIZE_UNCHANGEABLE")),
        SOCKET_SEND_BUFFER_SIZE_UNCHANGEABLE(
                new EnumConstant(-163, "- 163", "ERR_SOCKET_SEND_BUFFER_SIZE_UNCHANGEABLE")),
        SSL_CLIENT_AUTH_CERT_BAD_FORMAT(new EnumConstant(-164, "- 164", "ERR_SSL_CLIENT_AUTH_CERT_BAD_FORMAT")),
        ICANN_NAME_COLLISION(new EnumConstant(-166, "- 166", "ERR_ICANN_NAME_COLLISION")),
        SSL_SERVER_CERT_BAD_FORMAT(new EnumConstant(-167, "- 167", "ERR_SSL_SERVER_CERT_BAD_FORMAT")),
        CT_STH_PARSING_FAILED(new EnumConstant(-168, "- 168", "ERR_CT_STH_PARSING_FAILED")),
        CT_STH_INCOMPLETE(new EnumConstant(-169, "- 169", "ERR_CT_STH_INCOMPLETE")),
        UNABLE_TO_REUSE_CONNECTION_FOR_PROXY_AUTH(
                new EnumConstant(-170, "- 170", "ERR_UNABLE_TO_REUSE_CONNECTION_FOR_PROXY_AUTH")),
        CT_CONSISTENCY_PROOF_PARSING_FAILED(new EnumConstant(-171, "- 171", "ERR_CT_CONSISTENCY_PROOF_PARSING_FAILED")),
        SSL_OBSOLETE_CIPHER(new EnumConstant(-172, "- 172", "ERR_SSL_OBSOLETE_CIPHER")),
        WS_UPGRADE(new EnumConstant(-173, "- 173", "ERR_WS_UPGRADE")),
        READ_IF_READY_NOT_IMPLEMENTED(new EnumConstant(-174, "- 174", "ERR_READ_IF_READY_NOT_IMPLEMENTED")),
        NO_BUFFER_SPACE(new EnumConstant(-176, "- 176", "ERR_NO_BUFFER_SPACE")),
        SSL_CLIENT_AUTH_NO_COMMON_ALGORITHMS(
                new EnumConstant(-177, "- 177", "ERR_SSL_CLIENT_AUTH_NO_COMMON_ALGORITHMS")),
        EARLY_DATA_REJECTED(new EnumConstant(-178, "- 178", "ERR_EARLY_DATA_REJECTED")),
        WRONG_VERSION_ON_EARLY_DATA(new EnumConstant(-179, "- 179", "ERR_WRONG_VERSION_ON_EARLY_DATA")),
        TLS13_DOWNGRADE_DETECTED(new EnumConstant(-180, "- 180", "ERR_TLS13_DOWNGRADE_DETECTED")),
        SSL_KEY_USAGE_INCOMPATIBLE(new EnumConstant(-181, "- 181", "ERR_SSL_KEY_USAGE_INCOMPATIBLE")),
        INVALID_ECH_CONFIG_LIST(new EnumConstant(-182, "- 182", "ERR_INVALID_ECH_CONFIG_LIST")),
        ECH_NOT_NEGOTIATED(new EnumConstant(-183, "- 183", "ERR_ECH_NOT_NEGOTIATED")),
        ECH_FALLBACK_CERTIFICATE_INVALID(new EnumConstant(-184, "- 184", "ERR_ECH_FALLBACK_CERTIFICATE_INVALID")),
        PROXY_UNABLE_TO_CONNECT_TO_DESTINATION(
                new EnumConstant(-186, "- 186", "ERR_PROXY_UNABLE_TO_CONNECT_TO_DESTINATION")),
        PROXY_DELEGATE_CANCELED_CONNECT_REQUEST(
                new EnumConstant(-187, "- 187", "ERR_PROXY_DELEGATE_CANCELED_CONNECT_REQUEST")),
        PROXY_DELEGATE_CANCELED_CONNECT_RESPONSE(
                new EnumConstant(-188, "- 188", "ERR_PROXY_DELEGATE_CANCELED_CONNECT_RESPONSE")),
        CERT_COMMON_NAME_INVALID(new EnumConstant(-200, "- 200", "ERR_CERT_COMMON_NAME_INVALID")),
        CERT_DATE_INVALID(new EnumConstant(-201, "- 201", "ERR_CERT_DATE_INVALID")),
        CERT_AUTHORITY_INVALID(new EnumConstant(-202, "- 202", "ERR_CERT_AUTHORITY_INVALID")),
        CERT_CONTAINS_ERRORS(new EnumConstant(-203, "- 203", "ERR_CERT_CONTAINS_ERRORS")),
        CERT_NO_REVOCATION_MECHANISM(new EnumConstant(-204, "- 204", "ERR_CERT_NO_REVOCATION_MECHANISM")),
        CERT_UNABLE_TO_CHECK_REVOCATION(new EnumConstant(-205, "- 205", "ERR_CERT_UNABLE_TO_CHECK_REVOCATION")),
        CERT_REVOKED(new EnumConstant(-206, "- 206", "ERR_CERT_REVOKED")),
        CERT_INVALID(new EnumConstant(-207, "- 207", "ERR_CERT_INVALID")),
        CERT_WEAK_SIGNATURE_ALGORITHM(new EnumConstant(-208, "- 208", "ERR_CERT_WEAK_SIGNATURE_ALGORITHM")),
        CERT_NON_UNIQUE_NAME(new EnumConstant(-210, "- 210", "ERR_CERT_NON_UNIQUE_NAME")),
        CERT_WEAK_KEY(new EnumConstant(-211, "- 211", "ERR_CERT_WEAK_KEY")),
        CERT_NAME_CONSTRAINT_VIOLATION(new EnumConstant(-212, "- 212", "ERR_CERT_NAME_CONSTRAINT_VIOLATION")),
        CERT_VALIDITY_TOO_LONG(new EnumConstant(-213, "- 213", "ERR_CERT_VALIDITY_TOO_LONG")),
        CERTIFICATE_TRANSPARENCY_REQUIRED(new EnumConstant(-214, "- 214", "ERR_CERTIFICATE_TRANSPARENCY_REQUIRED")),
        CERT_KNOWN_INTERCEPTION_BLOCKED(new EnumConstant(-217, "- 217", "ERR_CERT_KNOWN_INTERCEPTION_BLOCKED")),
        CERT_SELF_SIGNED_LOCAL_NETWORK(new EnumConstant(-219, "- 219", "ERR_CERT_SELF_SIGNED_LOCAL_NETWORK")),
        CERT_END(new EnumConstant(-220, "- 220", "ERR_CERT_END")),
        INVALID_URL(new EnumConstant(-300, "- 300", "ERR_INVALID_URL")),
        DISALLOWED_URL_SCHEME(new EnumConstant(-301, "- 301", "ERR_DISALLOWED_URL_SCHEME")),
        UNKNOWN_URL_SCHEME(new EnumConstant(-302, "- 302", "ERR_UNKNOWN_URL_SCHEME")),
        INVALID_REDIRECT(new EnumConstant(-303, "- 303", "ERR_INVALID_REDIRECT")),
        TOO_MANY_REDIRECTS(new EnumConstant(-310, "- 310", "ERR_TOO_MANY_REDIRECTS")),
        UNSAFE_REDIRECT(new EnumConstant(-311, "- 311", "ERR_UNSAFE_REDIRECT")),
        UNSAFE_PORT(new EnumConstant(-312, "- 312", "ERR_UNSAFE_PORT")),
        INVALID_RESPONSE(new EnumConstant(-320, "- 320", "ERR_INVALID_RESPONSE")),
        INVALID_CHUNKED_ENCODING(new EnumConstant(-321, "- 321", "ERR_INVALID_CHUNKED_ENCODING")),
        METHOD_NOT_SUPPORTED(new EnumConstant(-322, "- 322", "ERR_METHOD_NOT_SUPPORTED")),
        UNEXPECTED_PROXY_AUTH(new EnumConstant(-323, "- 323", "ERR_UNEXPECTED_PROXY_AUTH")),
        EMPTY_RESPONSE(new EnumConstant(-324, "- 324", "ERR_EMPTY_RESPONSE")),
        RESPONSE_HEADERS_TOO_BIG(new EnumConstant(-325, "- 325", "ERR_RESPONSE_HEADERS_TOO_BIG")),
        PAC_SCRIPT_FAILED(new EnumConstant(-327, "- 327", "ERR_PAC_SCRIPT_FAILED")),
        REQUEST_RANGE_NOT_SATISFIABLE(new EnumConstant(-328, "- 328", "ERR_REQUEST_RANGE_NOT_SATISFIABLE")),
        MALFORMED_IDENTITY(new EnumConstant(-329, "- 329", "ERR_MALFORMED_IDENTITY")),
        CONTENT_DECODING_FAILED(new EnumConstant(-330, "- 330", "ERR_CONTENT_DECODING_FAILED")),
        NETWORK_IO_SUSPENDED(new EnumConstant(-331, "- 331", "ERR_NETWORK_IO_SUSPENDED")),
        NO_SUPPORTED_PROXIES(new EnumConstant(-336, "- 336", "ERR_NO_SUPPORTED_PROXIES")),
        HTTP2_PROTOCOL_ERROR(new EnumConstant(-337, "- 337", "ERR_HTTP2_PROTOCOL_ERROR")),
        INVALID_AUTH_CREDENTIALS(new EnumConstant(-338, "- 338", "ERR_INVALID_AUTH_CREDENTIALS")),
        UNSUPPORTED_AUTH_SCHEME(new EnumConstant(-339, "- 339", "ERR_UNSUPPORTED_AUTH_SCHEME")),
        ENCODING_DETECTION_FAILED(new EnumConstant(-340, "- 340", "ERR_ENCODING_DETECTION_FAILED")),
        MISSING_AUTH_CREDENTIALS(new EnumConstant(-341, "- 341", "ERR_MISSING_AUTH_CREDENTIALS")),
        UNEXPECTED_SECURITY_LIBRARY_STATUS(new EnumConstant(-342, "- 342", "ERR_UNEXPECTED_SECURITY_LIBRARY_STATUS")),
        MISCONFIGURED_AUTH_ENVIRONMENT(new EnumConstant(-343, "- 343", "ERR_MISCONFIGURED_AUTH_ENVIRONMENT")),
        UNDOCUMENTED_SECURITY_LIBRARY_STATUS(
                new EnumConstant(-344, "- 344", "ERR_UNDOCUMENTED_SECURITY_LIBRARY_STATUS")),
        RESPONSE_BODY_TOO_BIG_TO_DRAIN(new EnumConstant(-345, "- 345", "ERR_RESPONSE_BODY_TOO_BIG_TO_DRAIN")),
        RESPONSE_HEADERS_MULTIPLE_CONTENT_LENGTH(
                new EnumConstant(-346, "- 346", "ERR_RESPONSE_HEADERS_MULTIPLE_CONTENT_LENGTH")),
        INCOMPLETE_HTTP2_HEADERS(new EnumConstant(-347, "- 347", "ERR_INCOMPLETE_HTTP2_HEADERS")),
        PAC_NOT_IN_DHCP(new EnumConstant(-348, "- 348", "ERR_PAC_NOT_IN_DHCP")),
        RESPONSE_HEADERS_MULTIPLE_CONTENT_DISPOSITION(
                new EnumConstant(-349, "- 349", "ERR_RESPONSE_HEADERS_MULTIPLE_CONTENT_DISPOSITION")),
        RESPONSE_HEADERS_MULTIPLE_LOCATION(new EnumConstant(-350, "- 350", "ERR_RESPONSE_HEADERS_MULTIPLE_LOCATION")),
        HTTP2_SERVER_REFUSED_STREAM(new EnumConstant(-351, "- 351", "ERR_HTTP2_SERVER_REFUSED_STREAM")),
        HTTP2_PING_FAILED(new EnumConstant(-352, "- 352", "ERR_HTTP2_PING_FAILED")),
        CONTENT_LENGTH_MISMATCH(new EnumConstant(-354, "- 354", "ERR_CONTENT_LENGTH_MISMATCH")),
        INCOMPLETE_CHUNKED_ENCODING(new EnumConstant(-355, "- 355", "ERR_INCOMPLETE_CHUNKED_ENCODING")),
        QUIC_PROTOCOL_ERROR(new EnumConstant(-356, "- 356", "ERR_QUIC_PROTOCOL_ERROR")),
        RESPONSE_HEADERS_TRUNCATED(new EnumConstant(-357, "- 357", "ERR_RESPONSE_HEADERS_TRUNCATED")),
        QUIC_HANDSHAKE_FAILED(new EnumConstant(-358, "- 358", "ERR_QUIC_HANDSHAKE_FAILED")),
        HTTP2_INADEQUATE_TRANSPORT_SECURITY(new EnumConstant(-360, "- 360", "ERR_HTTP2_INADEQUATE_TRANSPORT_SECURITY")),
        HTTP2_FLOW_CONTROL_ERROR(new EnumConstant(-361, "- 361", "ERR_HTTP2_FLOW_CONTROL_ERROR")),
        HTTP2_FRAME_SIZE_ERROR(new EnumConstant(-362, "- 362", "ERR_HTTP2_FRAME_SIZE_ERROR")),
        HTTP2_COMPRESSION_ERROR(new EnumConstant(-363, "- 363", "ERR_HTTP2_COMPRESSION_ERROR")),
        PROXY_AUTH_REQUESTED_WITH_NO_CONNECTION(
                new EnumConstant(-364, "- 364", "ERR_PROXY_AUTH_REQUESTED_WITH_NO_CONNECTION")),
        HTTP_1_1_REQUIRED(new EnumConstant(-365, "- 365", "ERR_HTTP_1_1_REQUIRED")),
        PROXY_HTTP_1_1_REQUIRED(new EnumConstant(-366, "- 366", "ERR_PROXY_HTTP_1_1_REQUIRED")),
        PAC_SCRIPT_TERMINATED(new EnumConstant(-367, "- 367", "ERR_PAC_SCRIPT_TERMINATED")),
        INVALID_HTTP_RESPONSE(new EnumConstant(-370, "- 370", "ERR_INVALID_HTTP_RESPONSE")),
        CONTENT_DECODING_INIT_FAILED(new EnumConstant(-371, "- 371", "ERR_CONTENT_DECODING_INIT_FAILED")),
        HTTP2_RST_STREAM_NO_ERROR_RECEIVED(new EnumConstant(-372, "- 372", "ERR_HTTP2_RST_STREAM_NO_ERROR_RECEIVED")),
        TOO_MANY_RETRIES(new EnumConstant(-375, "- 375", "ERR_TOO_MANY_RETRIES")),
        HTTP2_STREAM_CLOSED(new EnumConstant(-376, "- 376", "ERR_HTTP2_STREAM_CLOSED")),
        HTTP_RESPONSE_CODE_FAILURE(new EnumConstant(-379, "- 379", "ERR_HTTP_RESPONSE_CODE_FAILURE")),
        QUIC_CERT_ROOT_NOT_KNOWN(new EnumConstant(-380, "- 380", "ERR_QUIC_CERT_ROOT_NOT_KNOWN")),
        QUIC_GOAWAY_REQUEST_CAN_BE_RETRIED(new EnumConstant(-381, "- 381", "ERR_QUIC_GOAWAY_REQUEST_CAN_BE_RETRIED")),
        TOO_MANY_ACCEPT_CH_RESTARTS(new EnumConstant(-382, "- 382", "ERR_TOO_MANY_ACCEPT_CH_RESTARTS")),
        INCONSISTENT_IP_ADDRESS_SPACE(new EnumConstant(-383, "- 383", "ERR_INCONSISTENT_IP_ADDRESS_SPACE")),
        CACHED_IP_ADDRESS_SPACE_BLOCKED_BY_LOCAL_NETWORK_ACCESS_POLICY(
                new EnumConstant(-384, "- 384", "ERR_CACHED_IP_ADDRESS_SPACE_BLOCKED_BY_LOCAL_NETWORK_ACCESS_POLICY")),
        BLOCKED_BY_LOCAL_NETWORK_ACCESS_CHECKS(
                new EnumConstant(-385, "- 385", "ERR_BLOCKED_BY_LOCAL_NETWORK_ACCESS_CHECKS")),
        ZSTD_WINDOW_SIZE_TOO_BIG(new EnumConstant(-386, "- 386", "ERR_ZSTD_WINDOW_SIZE_TOO_BIG")),
        DICTIONARY_LOAD_FAILED(new EnumConstant(-387, "- 387", "ERR_DICTIONARY_LOAD_FAILED")),
        UNEXPECTED_CONTENT_DICTIONARY_HEADER(
                new EnumConstant(-388, "- 388", "ERR_UNEXPECTED_CONTENT_DICTIONARY_HEADER")),
        CACHE_MISS(new EnumConstant(-400, "- 400", "ERR_CACHE_MISS")),
        CACHE_READ_FAILURE(new EnumConstant(-401, "- 401", "ERR_CACHE_READ_FAILURE")),
        CACHE_WRITE_FAILURE(new EnumConstant(-402, "- 402", "ERR_CACHE_WRITE_FAILURE")),
        CACHE_OPERATION_NOT_SUPPORTED(new EnumConstant(-403, "- 403", "ERR_CACHE_OPERATION_NOT_SUPPORTED")),
        CACHE_OPEN_FAILURE(new EnumConstant(-404, "- 404", "ERR_CACHE_OPEN_FAILURE")),
        CACHE_CREATE_FAILURE(new EnumConstant(-405, "- 405", "ERR_CACHE_CREATE_FAILURE")),
        CACHE_RACE(new EnumConstant(-406, "- 406", "ERR_CACHE_RACE")),
        CACHE_CHECKSUM_READ_FAILURE(new EnumConstant(-407, "- 407", "ERR_CACHE_CHECKSUM_READ_FAILURE")),
        CACHE_CHECKSUM_MISMATCH(new EnumConstant(-408, "- 408", "ERR_CACHE_CHECKSUM_MISMATCH")),
        CACHE_LOCK_TIMEOUT(new EnumConstant(-409, "- 409", "ERR_CACHE_LOCK_TIMEOUT")),
        CACHE_AUTH_FAILURE_AFTER_READ(new EnumConstant(-410, "- 410", "ERR_CACHE_AUTH_FAILURE_AFTER_READ")),
        CACHE_ENTRY_NOT_SUITABLE(new EnumConstant(-411, "- 411", "ERR_CACHE_ENTRY_NOT_SUITABLE")),
        CACHE_DOOM_FAILURE(new EnumConstant(-412, "- 412", "ERR_CACHE_DOOM_FAILURE")),
        CACHE_OPEN_OR_CREATE_FAILURE(new EnumConstant(-413, "- 413", "ERR_CACHE_OPEN_OR_CREATE_FAILURE")),
        CACHE_COMPRESSION_FAILURE(new EnumConstant(-414, "- 414", "ERR_CACHE_COMPRESSION_FAILURE")),
        INSECURE_RESPONSE(new EnumConstant(-501, "- 501", "ERR_INSECURE_RESPONSE")),
        NO_PRIVATE_KEY_FOR_CERT(new EnumConstant(-502, "- 502", "ERR_NO_PRIVATE_KEY_FOR_CERT")),
        ADD_USER_CERT_FAILED(new EnumConstant(-503, "- 503", "ERR_ADD_USER_CERT_FAILED")),
        INVALID_SIGNED_EXCHANGE(new EnumConstant(-504, "- 504", "ERR_INVALID_SIGNED_EXCHANGE")),
        INVALID_WEB_BUNDLE(new EnumConstant(-505, "- 505", "ERR_INVALID_WEB_BUNDLE")),
        TRUST_TOKEN_OPERATION_FAILED(new EnumConstant(-506, "- 506", "ERR_TRUST_TOKEN_OPERATION_FAILED")),
        TRUST_TOKEN_OPERATION_SUCCESS_WITHOUT_SENDING_REQUEST(
                new EnumConstant(-507, "- 507", "ERR_TRUST_TOKEN_OPERATION_SUCCESS_WITHOUT_SENDING_REQUEST")),
        HTTPENGINE_PROVIDER_IN_USE(new EnumConstant(-508, "- 508", "ERR_HTTPENGINE_PROVIDER_IN_USE")),
        PKCS12_IMPORT_BAD_PASSWORD(new EnumConstant(-701, "- 701", "ERR_PKCS12_IMPORT_BAD_PASSWORD")),
        PKCS12_IMPORT_FAILED(new EnumConstant(-702, "- 702", "ERR_PKCS12_IMPORT_FAILED")),
        IMPORT_CA_CERT_NOT_CA(new EnumConstant(-703, "- 703", "ERR_IMPORT_CA_CERT_NOT_CA")),
        IMPORT_CERT_ALREADY_EXISTS(new EnumConstant(-704, "- 704", "ERR_IMPORT_CERT_ALREADY_EXISTS")),
        IMPORT_CA_CERT_FAILED(new EnumConstant(-705, "- 705", "ERR_IMPORT_CA_CERT_FAILED")),
        IMPORT_SERVER_CERT_FAILED(new EnumConstant(-706, "- 706", "ERR_IMPORT_SERVER_CERT_FAILED")),
        PKCS12_IMPORT_INVALID_MAC(new EnumConstant(-707, "- 707", "ERR_PKCS12_IMPORT_INVALID_MAC")),
        PKCS12_IMPORT_INVALID_FILE(new EnumConstant(-708, "- 708", "ERR_PKCS12_IMPORT_INVALID_FILE")),
        PKCS12_IMPORT_UNSUPPORTED(new EnumConstant(-709, "- 709", "ERR_PKCS12_IMPORT_UNSUPPORTED")),
        KEY_GENERATION_FAILED(new EnumConstant(-710, "- 710", "ERR_KEY_GENERATION_FAILED")),
        PRIVATE_KEY_EXPORT_FAILED(new EnumConstant(-712, "- 712", "ERR_PRIVATE_KEY_EXPORT_FAILED")),
        SELF_SIGNED_CERT_GENERATION_FAILED(new EnumConstant(-713, "- 713", "ERR_SELF_SIGNED_CERT_GENERATION_FAILED")),
        CERT_DATABASE_CHANGED(new EnumConstant(-714, "- 714", "ERR_CERT_DATABASE_CHANGED")),
        CERT_VERIFIER_CHANGED(new EnumConstant(-716, "- 716", "ERR_CERT_VERIFIER_CHANGED")),
        DNS_MALFORMED_RESPONSE(new EnumConstant(-800, "- 800", "ERR_DNS_MALFORMED_RESPONSE")),
        DNS_SERVER_REQUIRES_TCP(new EnumConstant(-801, "- 801", "ERR_DNS_SERVER_REQUIRES_TCP")),
        DNS_TIMED_OUT(new EnumConstant(-803, "- 803", "ERR_DNS_TIMED_OUT")),
        DNS_CACHE_MISS(new EnumConstant(-804, "- 804", "ERR_DNS_CACHE_MISS")),
        DNS_SEARCH_EMPTY(new EnumConstant(-805, "- 805", "ERR_DNS_SEARCH_EMPTY")),
        DNS_SORT_ERROR(new EnumConstant(-806, "- 806", "ERR_DNS_SORT_ERROR")),
        DNS_SECURE_RESOLVER_HOSTNAME_RESOLUTION_FAILED(
                new EnumConstant(-808, "- 808", "ERR_DNS_SECURE_RESOLVER_HOSTNAME_RESOLUTION_FAILED")),
        DNS_NAME_HTTPS_ONLY(new EnumConstant(-809, "- 809", "ERR_DNS_NAME_HTTPS_ONLY")),
        DNS_REQUEST_CANCELLED(new EnumConstant(-810, "- 810", "ERR_DNS_REQUEST_CANCELLED")),
        DNS_NO_MATCHING_SUPPORTED_ALPN(new EnumConstant(-811, "- 811", "ERR_DNS_NO_MATCHING_SUPPORTED_ALPN")),
        DNS_SECURE_PROBE_RECORD_INVALID(new EnumConstant(-814, "- 814", "ERR_DNS_SECURE_PROBE_RECORD_INVALID")),
        DNS_CACHE_INVALIDATION_IN_PROGRESS(new EnumConstant(-815, "- 815", "ERR_DNS_CACHE_INVALIDATION_IN_PROGRESS")),
        DNS_FORMAT_ERROR(new EnumConstant(-816, "- 816", "ERR_DNS_FORMAT_ERROR")),
        DNS_SERVER_FAILURE(new EnumConstant(-817, "- 817", "ERR_DNS_SERVER_FAILURE")),
        DNS_NOT_IMPLEMENTED(new EnumConstant(-818, "- 818", "ERR_DNS_NOT_IMPLEMENTED")),
        DNS_REFUSED(new EnumConstant(-819, "- 819", "ERR_DNS_REFUSED")),
        DNS_OTHER_FAILURE(new EnumConstant(-820, "- 820", "ERR_DNS_OTHER_FAILURE")),
        BLOB_INVALID_CONSTRUCTION_ARGUMENTS(new EnumConstant(-900, "- 900", "ERR_BLOB_INVALID_CONSTRUCTION_ARGUMENTS")),
        BLOB_OUT_OF_MEMORY(new EnumConstant(-901, "- 901", "ERR_BLOB_OUT_OF_MEMORY")),
        BLOB_FILE_WRITE_FAILED(new EnumConstant(-902, "- 902", "ERR_BLOB_FILE_WRITE_FAILED")),
        BLOB_SOURCE_DIED_IN_TRANSIT(new EnumConstant(-903, "- 903", "ERR_BLOB_SOURCE_DIED_IN_TRANSIT")),
        BLOB_DEREFERENCED_WHILE_BUILDING(new EnumConstant(-904, "- 904", "ERR_BLOB_DEREFERENCED_WHILE_BUILDING")),
        BLOB_REFERENCED_BLOB_BROKEN(new EnumConstant(-905, "- 905", "ERR_BLOB_REFERENCED_BLOB_BROKEN")),
        BLOB_REFERENCED_FILE_UNAVAILABLE(new EnumConstant(-906, "- 906", "ERR_BLOB_REFERENCED_FILE_UNAVAILABLE"));

        private static final Kind[] VALUES = Kind.values();

        private final EnumConstant constant;

        Kind(EnumConstant constant) {
            this.constant = constant;
        }

        /** The underlying C enum numeric value. */
        public long value() {
            return constant.value();
        }

        /** The original C expression/literal (e.g. {@code "1 << 3"}), or the numeric string. */
        public String expr() {
            return constant.expr();
        }

        /** The C constant name (e.g., {@code "cef_errorcode_t"}). */
        public String cName() {
            return constant.name();
        }

        @Override
        public String toString() {
            return cName() + "(expr=" + expr() + ", value=" + value() + ")";
        }
    }

    /** The underlying C enum numeric value. This may not correspond to any known {@link Kind}. */
    public final long value;

    private CefErrorCode(long value) {
        this.value = value;
    }

    /** {@inheritDoc} */
    @Override
    public long value() {
        return value;
    }

    /** {@inheritDoc} */
    @Override
    public String expr() {
        return kind().map(Kind::expr).orElse(String.valueOf(value));
    }

    /** {@inheritDoc} */
    @Override
    public String name() {
        return kind().map(Kind::cName).orElse("UNKNOWN(" + value + ")");
    }

    /**
     * Returns the {@link Kind} matching this value, or empty for unknown/composite values. Use this for exhaustive
     * switch over known constants.
     */
    public java.util.Optional<Kind> kind() {
        for (Kind k : Kind.VALUES) {
            if (k.value() == value) return java.util.Optional.of(k);
        }
        return java.util.Optional.empty();
    }

    /** Returns an instance for the given raw value, use {@link #kind} to resolve to a concrete enum. */
    public static CefErrorCode of(long v) {
        return new CefErrorCode(v);
    }

    /** Returns an instance for the given known constant. */
    public static CefErrorCode of(Kind k) {
        return new CefErrorCode(k.value());
    }

    @Override
    public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CefErrorCode)) return false;
        return this.value == ((CefErrorCode) obj).value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return kind().map(Kind::toString).orElse("UNKNOWN(value=" + value + ")");
    }
}
