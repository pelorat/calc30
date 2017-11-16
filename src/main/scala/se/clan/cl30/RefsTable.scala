package se.clan.cl30

import scodec._
import scodec.Codec
import scodec.codecs._
import scodec.stream.decode

import cats.effect._


case class RefsEntry(
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
                  rwl: Long = 0) {
  def ::(that: RefsEntry): Boolean =
    surface == that.surface && bleed == that.bleed &&
      aice == that.aice && flaps == that.flaps &&
      RefsTable.clampAltitude(altitude) == that.altitude &&
      RefsTable.clampWeight(weight) == that.weight
}

object RefsEntry {
  implicit val codec: Codec[RefsEntry] = {
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
  }.as[RefsEntry]

  def apply(surface: Int,
            bleed: Int,
            aice: Int,
            flaps: Int,
            altitude: Long,
            weight: Long,
            temp: Int):
  RefsEntry = new RefsEntry(surface, bleed, aice, flaps, altitude, weight, temp)
}

class RefsTable(path: String) {

  case class Result(v1: Int, vr: Int, v2: Int, rwl: Int)

  object TempOrdering extends Ordering[RefsEntry] {
    override def compare(x: RefsEntry, y: RefsEntry): Int = x.temp - y.temp
  }

  private val frames = decode.once[RefsEntry].many

  private val stream: fs2.Stream[IO, RefsEntry] =
    frames.decodeMmap[IO](new java.io.FileInputStream(path).getChannel)

  private val refs: Vector[RefsEntry] = stream.runLog.unsafeRunSync()

  def findRefs(entry: RefsEntry): Option[RefsEntry] =
    refs.filter(_ :: entry) match {
      case IndexedSeq() => None
      case v => Some(v.minBy(e => Math.abs(e.temp - entry.temp)))
    }
}

object RefsTable {
  def apply(path: String): RefsTable = new RefsTable(path)

  def clampAltitude(alt: Long): Long = Math.min(
    Math.round(Math.max(alt, 0) / 1000.0) * 1000, 10000)

  def clampWeight(weight: Long): Long =
    Seq(28000L, 30000L, 32000L, 34000L, 36000L, 38000L, 38850L)
      .minBy(w => Math.abs(w - weight))
}
