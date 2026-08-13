#!/usr/bin/env bash
set -euo pipefail

usage() {
  echo "usage: $0 build|prepare <x86_64|aarch64|riscv64>" >&2
  exit 2
}

command=${1:-}
arch=${2:-}
[[ -n ${command} && -n ${arch} ]] || usage

script_dir=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)
out_dir=${script_dir}/out
container=${CONTAINER:-docker}

case ${arch} in
  x86_64)
    variant=al8
    dockerfile=Dockerfile
    platform=linux/amd64
    build_args=(--build-arg RHEL_TRIPLE=x86_64-redhat-linux
                --build-arg GNU_TRIPLE=x86_64-linux-gnu
                --build-arg EXTRA_PKGS=libquadmath)
    ;;
  aarch64)
    variant=al8
    dockerfile=Dockerfile
    platform=linux/arm64
    build_args=(--build-arg RHEL_TRIPLE=aarch64-redhat-linux
                --build-arg GNU_TRIPLE=aarch64-linux-gnu)
    ;;
  riscv64)
    variant=ubuntu20
    dockerfile=Dockerfile.ubuntu
    platform=linux/riscv64
    build_args=(--build-arg SYSBASE=docker.io/riscv64/ubuntu:20.04
                --build-arg GCC_MAJOR=10)
    ;;
  *) usage ;;
esac

tarball=${out_dir}/sysroot-${variant}-${arch}.tar.xz
prepared=${out_dir}/${arch}

prepare() {
  [[ -s ${tarball} ]] || {
    echo "missing sysroot tarball: ${tarball}" >&2
    exit 1
  }
  mkdir -p "${prepared}"
  find "${prepared}" -mindepth 1 -delete
  tar -xJf "${tarball}" -C "${prepared}"
  echo "sysroot ready at ${prepared} (from ${tarball})"
}

case ${command} in
  prepare)
    prepare
    ;;
  build)
    mkdir -p "${out_dir}"
    build_flags=()
    if [[ ${arch} != "$(uname -m)" ]]; then
      if [[ ${container} == podman ]]; then
        build_flags+=(--arch="${arch}")
      else
        build_flags+=(--platform="${platform}")
      fi
    fi
    image=cef4j-sysroot-${variant}:${arch}
    "${container}" build --pull "${build_flags[@]}" -f "${script_dir}/${dockerfile}" \
      "${build_args[@]}" -t "${image}" "${script_dir}"
    container_id=$("${container}" create "${image}")
    trap '"${container}" rm "${container_id}" >/dev/null 2>&1 || true' EXIT
    "${container}" export "${container_id}" | xz -T0 > "${tarball}"
    "${container}" rm "${container_id}" >/dev/null
    trap - EXIT
    prepare
    ;;
  *) usage ;;
esac
