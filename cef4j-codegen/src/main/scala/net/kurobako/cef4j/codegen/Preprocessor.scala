package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path

object Preprocessor {

  private val WindowsTargetDefine = "OS_WIN"
  private val MacTargetDefine     = "OS_MAC"

  private lazy val windowsShimIncludeDir: Path = {
    val dir    = Files.createTempDirectory("cef4j-win-shim")
    val header = dir.resolve("windows.h")
    Files.writeString(header, windowsShimHeader)
    header.toFile.deleteOnExit()
    dir.toFile.deleteOnExit()
    dir
  }

  private lazy val linuxShimIncludeDir: Path = {
    val dir = Files.createTempDirectory("cef4j-linux-shim")
    for ((name, content) <- linuxShimHeaders) {
      val header = dir.resolve(name)
      Files.writeString(header, content)
      header.toFile.deleteOnExit()
    }
    dir.toFile.deleteOnExit()
    dir
  }

  private lazy val macShimIncludeDir: Path = {
    val dir    = Files.createTempDirectory("cef4j-mac-shim")
    val header = dir.resolve("TargetConditionals.h")
    Files.writeString(header, macShimHeader)
    header.toFile.deleteOnExit()
    dir.toFile.deleteOnExit()
    dir
  }

  private val windowsShimHeader: String =
    """#pragma once
      |#include <stdint.h>
      |
      |#ifndef CALLBACK
      |#define CALLBACK
      |#endif
      |
      |typedef void* HANDLE;
      |typedef void* HWND;
      |typedef void* HCURSOR;
      |typedef void* HINSTANCE;
      |typedef void* HMENU;
      |typedef unsigned long DWORD;
      |typedef unsigned int UINT;
      |typedef intptr_t LPARAM;
      |typedef uintptr_t WPARAM;
      |typedef long LONG;
      |
      |typedef struct tagPOINT {
      |  LONG x;
      |  LONG y;
      |} POINT;
      |
      |typedef struct tagMSG {
      |  HWND hwnd;
      |  UINT message;
      |  WPARAM wParam;
      |  LPARAM lParam;
      |  DWORD time;
      |  POINT pt;
      |  DWORD lPrivate;
      |} MSG;
      |""".stripMargin

  private val macShimHeader: String =
    """#pragma once
      |
      |#ifndef TARGET_OS_IPHONE
      |#define TARGET_OS_IPHONE 0
      |#endif
      |
      |#ifndef TARGET_OS_MAC
      |#define TARGET_OS_MAC 1
      |#endif
      |
      |#ifndef TARGET_OS_SIMULATOR
      |#define TARGET_OS_SIMULATOR 0
      |#endif
      |
      |#ifndef TARGET_OS_TV
      |#define TARGET_OS_TV 0
      |#endif
      |
      |#ifndef TARGET_OS_WATCH
      |#define TARGET_OS_WATCH 0
      |#endif
      |""".stripMargin

  // MSVC needs declaration-only POSIX shims for cross-preprocessing.
  private val linuxShimHeaders: List[(String, String)] = List(
    "unistd.h" -> "#pragma once\n",
    "pthread.h" -> "#pragma once\ntypedef unsigned long pthread_t;\ntypedef union { char __size[56]; long __align; } pthread_mutex_t;\n"
  )

  private def hasWindowsTarget(defines: Seq[String]): Boolean =
    defines.exists(d => d == WindowsTargetDefine || d.startsWith(s"$WindowsTargetDefine="))

  private def hasMacTarget(defines: Seq[String]): Boolean =
    defines.exists(d => d == MacTargetDefine || d.startsWith(s"$MacTargetDefine="))

  def preprocess(
      headerFile: Path,
      includeDirs: Seq[Path],
      compilerId: String,
      defines: Seq[String] = Nil
  ): String = {
    val cmd = compilerId match {
      case "msvc"          => msvcCommand(headerFile, includeDirs, defines)
      case "clang" | "gcc" => unixCommand(headerFile, includeDirs, defines)
      case other           => sys.error(s"Unknown compiler id: $other")
    }

    val proc = new ProcessBuilder(cmd*)
      .redirectErrorStream(true)
      .start()
    val out  = proc.getInputStream.readAllBytes()
    val exit = proc.waitFor()
    if (exit != 0) {
      sys.error(s"Preprocessor failed (exit $exit): ${new String(out, "UTF-8")}")
    }
    stripLineMarkers(new String(out, "UTF-8"))
  }

  private def unixCommand(file: Path, includes: Seq[Path], defines: Seq[String]): List[String] = {
    val extraIncludes =
      (if (hasWindowsTarget(defines)) List(windowsShimIncludeDir) else Nil) ++
        (if (hasMacTarget(defines)) List(macShimIncludeDir) else Nil)
    // Explicit targets must override the host compiler's platform macros.
    val platformOverrides = {
      val hasLinux = defines.exists(d => d == "OS_LINUX" || d.startsWith("OS_LINUX="))
      val hasMac   = defines.exists(d => d == "OS_MAC" || d.startsWith("OS_MAC="))
      val hasWin   = defines.exists(d => d == "OS_WIN" || d.startsWith("OS_WIN="))
      if (!hasLinux && !hasMac && !hasWin) Nil
      else {
        val undefs =
          (if (!hasMac) List("-U__APPLE__", "-U__MACH__") else Nil) ++
            (if (!hasLinux) List("-U__linux__", "-U__linux", "-U__gnu_linux__") else Nil) ++
            (if (!hasWin) List("-U_WIN32", "-U_WIN64") else Nil)
        val defs =
          (if (hasLinux) List("-D__linux__", "-D__linux", "-D__gnu_linux__") else Nil) ++
            (if (hasMac) List("-D__APPLE__", "-D__MACH__") else Nil) ++
            (if (hasWin) List("-D_WIN32") else Nil)
        undefs ++ defs
      }
    }
    List("cc", "-E", "-x", "c", "-std=c11") ++
      defines.flatMap(d => List(s"-D$d")) ++
      platformOverrides ++
      extraIncludes.flatMap(d => List("-I", d.toString)) ++
      includes.flatMap(d => List("-I", d.toString)) ++
      List(file.toString)
  }

  private def msvcCommand(file: Path, includes: Seq[Path], defines: Seq[String]): List[String] = {
    val hasLinux      = defines.exists(d => d == "OS_LINUX" || d.startsWith("OS_LINUX="))
    val hasMac        = defines.exists(d => d == "OS_MAC" || d.startsWith("OS_MAC="))
    val extraIncludes =
      (if (hasLinux || hasMac) List(linuxShimIncludeDir) else Nil) ++
        (if (hasMac) List(macShimIncludeDir) else Nil)
    // MSVC built-ins must not leak into non-Windows target headers.
    val platformOverrides = {
      val hasWin = defines.exists(d => d == "OS_WIN" || d.startsWith("OS_WIN="))
      if (!hasLinux && !hasMac && !hasWin) Nil
      else {
        val undefs =
          (if (!hasWin) List("/U_WIN32", "/U_WIN64", "/U_MSC_VER") else Nil) ++
            (if (!hasMac) List("/U__APPLE__", "/U__MACH__") else Nil) ++
            (if (!hasLinux) List("/U__linux__", "/U__linux", "/U__gnu_linux__") else Nil)
        val defs =
          (if (hasLinux)
             List("/D__linux__", "/D__linux", "/D__gnu_linux__", "/D__GNUC__=4", "/D__WCHAR_MAX__=0x7fffffff")
           else Nil) ++
            (if (hasMac) List("/D__APPLE__", "/D__MACH__", "/D__GNUC__=4", "/D__WCHAR_MAX__=0x7fffffff") else Nil) ++
            (if (hasWin) List("/D_WIN32") else Nil)
        undefs ++ defs
      }
    }
    List("cl.exe", "/nologo", "/E", "/EP") ++
      defines.map(d => s"/D$d") ++
      platformOverrides ++
      extraIncludes.flatMap(d => List(s"/I$d")) ++
      includes.flatMap(d => List(s"/I$d")) ++
      List(file.toString)
  }

  private def stripLineMarkers(src: String): String =
    src.linesIterator
      .filterNot(l => l.startsWith("# ") || l.startsWith("#line "))
      .mkString("\n")
}
