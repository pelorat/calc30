package se.clan.cl30

object Calculator {

  def main(args: Array[String]): Unit = {

    val test = RefsTable("refstable.cpd")

    println(test.findRefs(RefsEntry(0,0,0,10,10000,28304,15)).toString)

  }

}
