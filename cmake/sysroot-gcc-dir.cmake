# Keep Clang from discovering the runner's newer GCC/libstdc++ installation.
if(DEFINED ENV{CMAKE_SYSROOT} AND NOT "$ENV{CMAKE_SYSROOT}" STREQUAL "")
    set(_cef4j_sysroot "$ENV{CMAKE_SYSROOT}")
    file(GLOB _cef4j_toolset
        "${_cef4j_sysroot}/opt/rh/gcc-toolset-*/root/usr/lib/gcc/*-redhat-linux*/[0-9]*")
    file(GLOB _cef4j_stock
        "${_cef4j_sysroot}/usr/lib/gcc/*-redhat-linux*/[0-9]*"
        "${_cef4j_sysroot}/usr/lib/gcc/*-linux-gnu/[0-9]*")
    list(SORT _cef4j_toolset ORDER DESCENDING)
    list(SORT _cef4j_stock ORDER DESCENDING)
    foreach(_cef4j_dir ${_cef4j_toolset} ${_cef4j_stock})
        if(EXISTS "${_cef4j_dir}/libstdc++.so" OR EXISTS "${_cef4j_dir}/libstdc++.a")
            set(CEF4J_SYSROOT_GCC_INSTALL_DIR "${_cef4j_dir}")
            message(STATUS "cef4j sysroot gcc-install-dir: ${_cef4j_dir}")
            break()
        endif()
    endforeach()
endif()
