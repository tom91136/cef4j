// Minimal CEF subprocess helper.
// This executable is launched by CEF for renderer/GPU/utility processes.
// It just calls cef_execute_process and exits.

#include "include/capi/cef_app_capi.h"
#include "include/cef_api_hash.h"

#if defined(_WIN32)
#include <windows.h>
int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE, LPSTR, int) {
    cef_api_hash(CEF_API_VERSION, 0);
    cef_main_args_t args{};
    args.instance = hInstance;
    return cef_execute_process(&args, nullptr, nullptr);
}
#else
int main(int argc, char* argv[]) {
    cef_api_hash(CEF_API_VERSION, 0);
    cef_main_args_t args{};
    args.argc = argc;
    args.argv = argv;
    return cef_execute_process(&args, nullptr, nullptr);
}
#endif
