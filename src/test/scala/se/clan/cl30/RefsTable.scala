package se.clan.cl30

import org.scalatest._

class TestRefsTable extends FunSuite {

  // valid altitudes = 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000

  test("clampAltitude rounds to closest 1,000") {
    assert(RefsTable.clampAltitude(1000) == 1000)
    assert(RefsTable.clampAltitude(1300) == 1000)
    assert(RefsTable.clampAltitude(1600) == 2000)
  }

  test("clampAltitude round down to 10,000 for altitudes above 10,000") {
    assert(RefsTable.clampAltitude(16000) == 10000)
    assert(RefsTable.clampAltitude(10001) == 10000)
  }

  test("clampAltitude rounds up to 0 with negative altitude") {
    assert(RefsTable.clampAltitude(-1000) == 0)
    assert(RefsTable.clampAltitude(-1) == 0)
    assert(RefsTable.clampAltitude(-10000) == 0)
  }

  // valid weights = 28000, 30000, 32000, 34000, 36000, 38000, 38850

  test("clampWeight should round up to 28,000") {
    assert(RefsTable.clampWeight(20000) == 28000)
  }

  test("clampWeight should round down to 38,850") {
    assert(RefsTable.clampWeight(40000) == 38850)
  }

  test("clampWeight should round to closest weight") {
    assert(RefsTable.clampWeight(28100) == 28000)
    assert(RefsTable.clampWeight(28500) == 28000)
    assert(RefsTable.clampWeight(29001) == 30000)
    assert(RefsTable.clampWeight(29500) == 30000)
  }

  test("clampWeight should round up to 38,850") {
    assert(RefsTable.clampWeight(38426) == 38850)
  }
}
