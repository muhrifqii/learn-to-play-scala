package controllers

import models.TheWord
import play.api._
import play.api.mvc._

object Application extends Controller {

  implicit val app = Play.current

  def index = Action {
    val real = getThatText("sample1.txt")
    val result = doTheJobFun {
      real
    }
    Ok(views.html.index(real.mkString("\n"), result))

  }

  def test = Action {
    Ok(getThatText("sample1.txt").mkString("\n"))
  }

  def getThatText(fileName: String): List[String] = {
    val source = scala.io.Source.fromFile(app.getFile(fileName))("UTF-8")
    try source.getLines().toList
    catch {
      case e: Exception => e.printStackTrace(); null
    }
    finally source.close()
  }

  val doTheJobFun = (text: List[String]) => {
//    val hpr = text.view
    text.flatMap(_.split("[.?!:]"))
      .map(_.split("\\s+").head)
      .filter(!_.equals(""))
      .map { p => TheWord(p, p.reverse) }
    //      .map(_.split("\\s+"))
    //      .filter { p => !p.equals("") }
    //      .map { p => TheWord(p, p.reverse) }

    //    text.map(_.split("\\n+").mkString("."))
    //      .map(splitSentence)
    //      .map(_.map(firstWordFun))
    //      .map(p => TheWord(p, p.reverse))

  }

}