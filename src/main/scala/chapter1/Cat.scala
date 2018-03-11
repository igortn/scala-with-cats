package chapter1

import cats._
import cats.implicits._

final case class Cat(name: String, age: Int, color: String)

object CatImplicits {

  implicit val catShowInst: Show[Cat] =
    Show.show(cat => s"${cat.name} is a ${cat.age} year old ${cat.color} cat.")

  implicit val catEqInst: Eq[Cat] =
    Eq.instance(
      (c1,c2) => c1.name == c2.name
      && c1.age == c1.age
      && c1.color == c2.color
    )
}

object Main {
  import CatImplicits._

  def run(): Unit = {
    val c1 = Cat("Garfield", 7, "brown")
    println(c1.show)

    val c2 = Cat("Tom", 9, "black")
    println(c2.show)

    println(s"c1 === c2: ${c1 === c2}")
  }
}