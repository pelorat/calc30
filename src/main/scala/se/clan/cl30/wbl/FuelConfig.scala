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

/** A class for calculating a SeeGee 'index' value given a fuel weight
  * in pounds (lbs)
  *
  * @param lbs fuel weight in lbs
  */
class FuelConfig(lbs: Double) extends WeightDistribution {
  val weight: Double = lbs

  /** Stores the See Gee index value. The formula is a polynomial derived
    * from the Challenger 300 SeeGee chart.
    */
  val index: Double = (0.0000000606 * lbs - 0.001602) * lbs - 0.0494
}

/** FuelConfig companion object
  */
object FuelConfig {

  /** Creates a FuelConfig given a fuel weight
    *
    * @param lbs fuel weight in lbs
    * @return A FuelConfig object
    */
  def apply(lbs: Double): FuelConfig = new FuelConfig(lbs)
}
