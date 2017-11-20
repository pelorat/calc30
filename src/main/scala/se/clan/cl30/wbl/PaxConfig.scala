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

package se.clan.cl30.wbl

class PaxConfig(weights: Array[Double])
  extends WeightDistribution {

  /**
    * Data table for converting cabin row weights into index offset
    * on the SeeGee Challenger 300 chart. Passengers and baggage can
    * be loaded into seven different rows.
    */
  private lazy val degreesPerRow = Array(
    -15/500.0,  -10/500.0, -6.7/500.0, -2.7/300.0, -2/500.0, 0.0, 13/750.0
  )

  /**
    * The row order in which to fill the plane
    */
  private lazy val loadOrder = Array(2, 2, 5, 1, 1, 3, 3, 5, 4, 6, 7, 7)

  val weight: Double = weights.sum

  val index: Double = 0.2 * (loadOrder, weights)
    .zipped
    .map((row, lbs) => degreesPerRow(row) * lbs)
    .sum
}

object PaxConfig {
  def apply(pax: Int, avg: Double, baggage: Double = 0.0): PaxConfig =
    new PaxConfig(
      Array.tabulate(10) {
        x => if (x < pax) avg else 0.0
      } ++ Array.fill(2) {
        baggage / 2
      })
}
