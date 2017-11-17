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

import org.scalatest._
import se.clan.cl30.refs.Refs

class Refs extends FunSuite {

  // valid altitudes = 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000

  test("clampAltitude rounds to closest 1,000") {
    assert(Refs.clampAltitude(1000) == 1000)
    assert(Refs.clampAltitude(1300) == 1000)
    assert(Refs.clampAltitude(1600) == 2000)
  }

  test("clampAltitude round down to 10,000 for altitudes above 10,000") {
    assert(Refs.clampAltitude(16000) == 10000)
    assert(Refs.clampAltitude(10001) == 10000)
  }

  test("clampAltitude rounds up to 0 with negative altitude") {
    assert(Refs.clampAltitude(-1000) == 0)
    assert(Refs.clampAltitude(-1) == 0)
    assert(Refs.clampAltitude(-10000) == 0)
  }

  // valid weights = 28000, 30000, 32000, 34000, 36000, 38000, 38850

  test("clampWeight should round up to 28,000") {
    assert(Refs.clampWeight(20000) == 28000)
  }

  test("clampWeight should round down to 38,850") {
    assert(Refs.clampWeight(40000) == 38850)
  }

  test("clampWeight should round to closest weight") {
    assert(Refs.clampWeight(28100) == 28000)
    assert(Refs.clampWeight(28500) == 28000)
    assert(Refs.clampWeight(29001) == 30000)
    assert(Refs.clampWeight(29500) == 30000)
  }

  test("clampWeight should round up to 38,850") {
    assert(Refs.clampWeight(38426) == 38850)
  }
}
