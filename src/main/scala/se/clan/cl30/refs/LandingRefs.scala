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

import cats.effect.IO
import scodec.stream.decode

import scala.language.implicitConversions

object LandingRefs extends Refs {

  def load(path: String): LandingTable = decode
    .once[LandingEntry]
    .many
    .decodeMmap[IO](new java.io.FileInputStream(path).getChannel)
    .runLog
    .unsafeRunSync()

  def clampWeight(weight: Long): Long =
    Seq(28000L, 30000L, 32000L, 33750L, 34000L, 36000L, 38000L, 38850L)
      .minBy(w => Math.abs(w - weight))

  class SearchImpl(table: LandingTable) {
    def search(weight: Long, altitude: Long): Option[LandingEntry] =
      table.find(e => e.altitude == clampAltitude(altitude) && e.weight == clampWeight(weight))
  }

  implicit def landingSearch(table: LandingTable): SearchImpl = new SearchImpl(table)

}
