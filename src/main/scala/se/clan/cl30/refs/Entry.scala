package se.clan.cl30.refs

import scodec._
import scodec.Codec
import scodec.codecs._

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
                  rwl: Long = 0) {
  def ::(that: Entry): Boolean =
    surface == that.surface && bleed == that.bleed &&
      aice == that.aice && flaps == that.flaps &&
      Refs.clampAltitude(altitude) == that.altitude &&
      Refs.clampWeight(weight) == that.weight
}

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