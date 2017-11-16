package se.clan.cl30

import se.clan.cl30.refs.{Entry, Refs}

object Calculator {

  def main(args: Array[String]): Unit = {

    implicit val refTable = Refs.load("refstable.cpd")
    val result = Refs.search(Entry(0,0,0,10,1244,29304,19))
    println(result.toString)

  }

}
