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

class Challenger300(owe: Long = 23750, dmac: Double = 41.2) {

  /**
    * The minimum empty operating weight in lbs (2 pilots + supplies + crew)
    */
  val operatingWeightEmpty: Long = owe

  /**
    * The default %MAC at the minimum empty operating weight. The %MAC value
    * is the location of the center of gravity expressed as a percentage of
    * the MAC counted from the leading edge the wing.
    */
  val defaultMAC: Double = dmac
}

object Challenger300 {

  /**
    * The mean aerodynamic chord of the wing (in centimeters)
    */
  val meanAerodynamicChord = 290.0

  /**
    * The minimum operating weight (empty plane) in lbs
    */
  val minimumOperatingWeight = 23100.0

  /**
    * The maximum takeoff weight allowed (in lbs)
    */
  val maximumTakeoffWeight = 38850.0
  private val indexDegrees = 0.2

  def apply: Challenger300 = new Challenger300()
}
