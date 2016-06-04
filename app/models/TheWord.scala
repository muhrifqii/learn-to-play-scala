package models

/**
  * Created by muhrifqii on 6/4/2016.
  */
case class TheWord(word: String, reversed: String) {
  def print() = "%s -> %s".format(word, reversed)
}

