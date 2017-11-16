package se.clan.cl30

import scodec._
import scodec.Codec
import scodec.codecs._
import scodec.stream.decode

import cats.effect._

class RefsTable(path: String) {

  case class Result(v1: Int, vr: Int, v2: Int, rwl: Int)

  case class Entry(
    surface: Int,
    bleed: Int,
    aice: Int,
    flaps: Int,
    altitude: Long,
    weight: Long,
    temp: Int,
    v1: Int = 0,
    vr: Int = 0,
    v2: Int = 0,
    rwl: Long = 0)

  object Entry {
    implicit val codec: Codec[Entry] = {
      ("surface" | uint8L) ::
      ("bleed" | uint8L) ::
      ("aice" | uint8L) ::
      ("flaps" | uint8L) ::
      ("altitude" | uint32L) ::
      ("weight" | uint32L) ::
      ("temp" | int8L) ::
      ("v1" | uint8L) ::
      ("vr" | uint8L) ::
      ("v2" | uint8L) ::
      ("rwl" | uint32L)
    }.as[Entry]

    def apply(surface: Int,
               bleed: Int,
               aice: Int,
               flaps: Int,
               altitude: Long,
               weight: Long,
               temp: Int):
    Entry = new Entry(surface, bleed, aice, flaps, altitude, weight, temp)
  }

  private val frames = decode.once[Entry].many

  private val stream: fs2.Stream[IO, Entry] =
    frames.decodeMmap[IO](new java.io.FileInputStream(path).getChannel)

  private val refs: Vector[Entry] = stream.runLog.unsafeRunSync()

  private def clampAltitude(alt: Double) = Math.min(Math.round(
    Math.max(alt, 0.0) / 1000) * 1000, 10000).toDouble

  def findRefs(surface: Int,
             bleed: Int,
             aice: Int,
             flaps: Int,
             altitude: Long,
             weight: Long,
             temp: Int): Int = {


  }
}

object RefsTable {
  def apply(path: String): RefsTable = new RefsTable(path)
}
