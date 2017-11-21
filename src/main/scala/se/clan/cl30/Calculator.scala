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

import se.clan.cl30.refs.{Entry, Refs}
import se.clan.cl30.refs.Refs._
import se.clan.cl30.wbl.{FuelConfig, PaxConfig}

object Calculator {

  def main(args: Array[String]): Unit = {

    val table = Refs.load("refstable.cpd")

    println(s"table.length = ${table.length}")

    val result = table.search(Entry(0,0,0,10,1244,29304,19))

    println(result.toString)

    val pax = PaxConfig(5, 77 * wbl.LbsPerKg)
    val fuel = FuelConfig(3000 * wbl.LbsPerKg)
    val paxMac = pax.index * wbl.fMAC(pax.weight)
    val fuelMac = fuel.index * wbl.fMAC(fuel.weight)
    val totalMac = (pax.index + fuel.index) * wbl.fMAC(pax.weight + fuel.weight)

    println(s"pax.weight = ${pax.weight}, pax.index = ${pax.index}, %MAC offset = $paxMac")
    println(s"fuel.weight = ${fuel.weight}, fuel.index = ${fuel.index}, %MAC offset = $fuelMac")
    println(s"total weight = ${pax.weight + fuel.weight}, total index = ${pax.index + fuel.index}")
    println(s"total %MAC offset = $totalMac")
  }

}
