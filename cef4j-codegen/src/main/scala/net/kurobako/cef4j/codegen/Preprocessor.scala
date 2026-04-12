package net.kurobako.cef4j.codegen

import java.nio.file.Files
import java.nio.file.Path

object Preprocessor {

  private val WindowsTargetDefine = "OS_WIN"

  private lazy val windowsShimIncludeDir: Path = {
    val dir    = Files.createTempDirectory("cef4j-win-shim")
    val header = dir.resolve("windows.h")
    Files.writeString(header, windowsShimHeader)
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

  private def hasWindowsTarget(defines: Seq[String]): Boolean =
    defines.exists(d => d == WindowsTargetDefine || d.startsWith(s"$WindowsTargetDefine="))

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
      if (hasWindowsTarget(defines)) List(windowsShimIncludeDir) else Nil
    List("cc", "-E", "-x", "c", "-std=c11") ++
      defines.flatMap(d => List(s"-D$d")) ++
      extraIncludes.flatMap(d => List("-I", d.toString)) ++
      includes.flatMap(d => List("-I", d.toString)) ++
      List(file.toString)
  }

  private def msvcCommand(file: Path, includes: Seq[Path], defines: Seq[String]): List[String] =
    List("cl.exe", "/nologo", "/E", "/EP") ++
      defines.map(d => s"/D$d") ++
      includes.flatMap(d => List(s"/I$d")) ++
      List(file.toString)

  private def stripLineMarkers(src: String): String =
    src.linesIterator
      .filterNot(l => l.startsWith("# ") || l.startsWith("#line "))
      .mkString("\n")
}
