package chapter1

final case class Cat(name: String, age: Int, color: String)

object Main {
  import PrintableInstances._
  import PrintableSyntax._

  def run(): Unit = {
    val c = Cat("Garfield", 7, "brown")
    c.print
  }
}