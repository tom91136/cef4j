#!/usr/bin/env bash
set -euo pipefail

destination=${1:?destination directory is required}
mkdir -p "${destination}"

modules=(
  cef4j-api
  cef4j-inprocess-jfx
  cef4j-inprocess-swing
  cef4j-inprocess-webdriver
  cef4j-http
  cef4j-cdp
  cef4j-cdp-gson
  cef4j-cdp-jackson
  cef4j-remote-api
  cef4j-remote-cdp-gson
  cef4j-remote-cdp-jackson
  cef4j-remote-core
  cef4j-remote-recording-jackson
  cef4j-remote-recording-gson
  cef4j-remote-frame
  cef4j-remote-jfx
  cef4j-remote-swing
  cef4j-remote-webdriver
  cef4j-webdriver
  cef4j-webdriver-gson
  cef4j-webdriver-jackson
)

for module in "${modules[@]}"; do
  find "${module}/target" -maxdepth 1 -type f -name "${module}-*.jar" \
    ! -name '*-sources.jar' ! -name '*-javadoc.jar' ! -name '*-tests.jar' \
    -exec cp '{}' "${destination}/" ';'
done
