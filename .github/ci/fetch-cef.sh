#!/usr/bin/env bash
# Fetch one immutable CEF archive before Maven runs. Keeping only this archive in
# Actions cache avoids storing the much larger unpacked distribution.
set -euo pipefail

version=${1:?CEF version is required}
platform=${2:?CEF platform is required}
cache_dir=${3:-.cef-dist}
archive_name="cef_binary_${version}_${platform}_minimal.tar.bz2"
archive="${cache_dir}/${archive_name}"
url="https://cef-builds.spotifycdn.com/${archive_name}"

if [[ -f ${archive} ]]; then
    echo "Using cached ${archive_name}"
    exit 0
fi

mkdir -p "${cache_dir}"
partial="${archive}.part"
trap 'rm -f "${partial}"' EXIT

# curl retries transient connection and HTTP failures. The temporary path plus
# tar listing ensures a failed download can never be persisted as a valid cache.
curl --fail --location --retry 5 --retry-all-errors \
    --connect-timeout 30 --output "${partial}" "${url}"
tar -tjf "${partial}" >/dev/null
mv "${partial}" "${archive}"
trap - EXIT
