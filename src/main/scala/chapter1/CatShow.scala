package chapter1

import cats.Show
import cats.syntax.show._

object CatShow {

  implicit val catShowInst: Show[Cat] =
    Show.show(cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat.")

  def run(): Unit = {
    val c = Cat("Garfield", 7, "brown")
    println(c.show)
  }
}
