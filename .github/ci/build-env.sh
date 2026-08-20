#!/usr/bin/env bash

normalize_java_home() {
    case "$(uname -s)" in
        MINGW*|MSYS*|CYGWIN*) cygpath --unix "$1" ;;
        *) printf '%s\n' "$1" ;;
    esac
}

java_specification_version() {
    sed -n 's/^[[:space:]]*java.specification.version = //p' | tr -d '\r'
}
