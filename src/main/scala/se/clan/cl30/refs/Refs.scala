package se.clan.cl30.refs

import cats.effect.IO
import scodec.stream.decode

object Refs {

  def load(path: String): Table = decode
    .once[Entry]
    .many
    .decodeMmap[IO](new java.io.FileInputStream(path).getChannel)
    .runLog
    .unsafeRunSync()

  def clampAltitude(alt: Long): Long = Math.min(
    Math.round(Math.max(alt, 0) / 1000.0) * 1000, 10000)

  def clampWeight(weight: Long): Long =
    Seq(28000L, 30000L, 32000L, 34000L, 36000L, 38000L, 38850L)
      .minBy(w => Math.abs(w - weight))

  def search(entry: Entry)(implicit table: Table): Option[Entry] =
    table.filter(_ :: entry) match {
      case IndexedSeq() => None
      case v => Some(v.minBy(e => Math.abs(e.temp - entry.temp)))
    }

}
