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
package se.clan.cl30

package object wbl {

  /**
    * The Mean Aerodynamic Chord (MAC) of the wing in centimeters.
    * See https://en.wikipedia.org/wiki/Chord_(aeronautics)
    */
  val MeanAerodynamicChord = 290.0

  /**
    * The minimum operating weight (empty plane) in lbs
    */
  val MinimumOperatingWeight = 23100.0

  /**
    * The maximum takeoff weight allowed (in lbs)
    */
  val MaximumTakeoffWeight = 38850.0

  /**
    * Pounds per Kilogram
    */
  val LbsPerKg = 2.20462262

  /**
    * Default %MAC at Empty Operating Weight
    */
  val dMAC = 41.2

  /**
    * The minimum empty operating weight in lbs (2 pilots + supplies )
    */
  val OperatingWeightEmpty = 23750.0

  /** Given a delta relative to the empty weight, returns a multiplier for
    * converting a SeeGee index value to a %MAC offset.
    *
    * @param lbs The weight in pounds (lbs)
    * @return %MAC offset
    */
  def fMAC(lbs: Double): Double = (0.000000000673 * lbs - 0.0000392) * lbs + 1.15

  /** Returns the optimal stabilizer trim setting for take-off given the current
    * flap setting (10,20), the current %MAC and the aircraft weight.
    *
    * @param flap Integer representing the current flap setting (10,20).
    * @param pmac Double representing the current %MAC.
    * @param weight Double representing the current weight in lbs.
    * @return A double with the calculated trim setting.
    */
  def fTrim(flap: Int, pmac: Double, weight: Double): Double = Math
    .max(2.7, flap match {
      case 10 => weight * (0.000488333 - 0.00000715 * pmac) + 0.0506 * pmac - 4.11
      case 20 => weight * (0.000488333 - 0.00000715 * pmac) + 0.0466 * pmac - 4.22
      case _ => 0.0
    })

}
