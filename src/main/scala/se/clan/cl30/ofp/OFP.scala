package se.clan.cl30.ofp

import cats.effect.IO

import scala.xml._

class OFP(path: String) {

  private val ofpXml = IO { XML.loadFile(path) }

}

object OFP {
  def apply(path: String): OFP = new OFP(path)
}