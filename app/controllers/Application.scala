package controllers

import models.TheWord
import play.api._
import play.api.mvc._

import scala.collection.mutable

object Application extends Controller {

  implicit val app = Play.current

  def index = Action {
    val real = getThatText("sample1.txt")
    val result = doTheJobFun {
      mutable.Seq(real: _*)
    }
    Ok(views.html.index(real.mkString("\n"), result))

  }

  def test = Action {
    Ok(getThatText("sample1.txt").mkString("\n"))
  }

  def getThatText(fileName: String) = {
//    val source = scala.io.Source.fromFile(app.getFile(fileName))("UTF-8")
    val source = scala.io.Source.fromFile(Play.application.path.getAbsolutePath + "/" + fileName)("UTF-8")
    try source.getLines().toList
    catch {
      case e: Exception => e.printStackTrace(); null
    }
    finally source.close()
  }

  val doTheJobFun = (text: mutable.Seq[String]) => {
    text.flatMap(_.split("[.?!:]"))
      .map(_.split("\\s+").find(_.nonEmpty).getOrElse(""))
      .filter(_.matches("[a-zA-Z].*"))
      .filter(!_.equals(""))
      .map { p => TheWord(p, p.reverse) }
      .toList
  }

}