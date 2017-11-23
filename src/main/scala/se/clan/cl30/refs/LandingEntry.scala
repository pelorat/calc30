/*
Copyright 2017, calc30-team

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package se.clan.cl30.refs

import scodec._
import scodec.Codec
import scodec.codecs._

case class LandingEntry(
  weight: Int,
  altitude: Int,
  vref: Int,
  vga: Int,
  ald: Int,
  fld: Int,
  fld_wet: Int)

object LandingEntry {
  implicit val codec: Codec[LandingEntry] = {
    ("weight" | uint16L) ::
    ("altitude" | uint16L) ::
    ("vref" | uint8L) ::
    ("vga" | uint8L) ::
    ("ald" | uint16L) ::
    ("fld" | uint16L) ::
    ("fld_wet" | uint16L)
  }.as[LandingEntry]

  def apply(
     weight: Int,
     altitude: Int,
     vref: Int,
     vga: Int,
     ald: Int,
     fld: Int,
     fld_wet: Int)
  : LandingEntry = new LandingEntry(weight, altitude, vref, vga, ald, fld, fld_wet)
}
