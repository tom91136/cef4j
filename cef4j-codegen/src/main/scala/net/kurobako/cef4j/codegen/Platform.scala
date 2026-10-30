package net.kurobako.cef4j.codegen

object Platform {
  def parse(value: String): Option[Platform] =
    value.toLowerCase match {
      case "linux"            => Some(Platform.Linux)
      case "linux64"          => Some(Platform.Linux)
      case "linuxarm64"       => Some(Platform.Linux)
      case "mac" | "macos"    => Some(Platform.Mac)
      case "macosx64"         => Some(Platform.Mac)
      case "macosarm64"       => Some(Platform.Mac)
      case "win" | "windows"  => Some(Platform.Windows)
      case "windows64"        => Some(Platform.Windows)
      case "windowsarm64"     => Some(Platform.Windows)
      case "auto" | "current" => Some(detectCurrent)
      case _                  => None
    }

  def detectCurrent: Platform = {
    val os = System.getProperty("os.name", "").toLowerCase
    if (os.contains("win")) Platform.Windows
    else if (os.contains("mac")) Platform.Mac
    else Platform.Linux
  }
}

enum Platform {
  case Linux
  case Mac
  case Windows

  def id: String =
    this match {
      case Linux   => "linux"
      case Mac     => "mac"
      case Windows => "win"
    }

  def cefTypesHeader: String =
    this match {
      case Linux   => "cef_types_linux.h"
      case Mac     => "cef_types_mac.h"
      case Windows => "cef_types_win.h"
    }

  def osMacro: String =
    this match {
      case Linux   => "OS_LINUX"
      case Mac     => "OS_MAC"
      case Windows => "OS_WIN"
    }

  def preprocessorDefines: List[String] =
    this match {
      case Linux   => List("OS_LINUX=1")
      case Mac     => List("OS_MAC=1")
      case Windows => List("OS_WIN=1")
    }
}
