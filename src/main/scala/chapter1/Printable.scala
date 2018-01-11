package chapter1

trait Printable[A] {
  def format(value: A): String
}

object PrintableInstances {
  implicit val printableStringInst: Printable[String] = new Printable[String] {
    def format(value: String): String = value
  }

  implicit val printableIntInst: Printable[Int] = new Printable[Int] {
    def format(value: Int): String = value.toString
  }

  implicit val printableCatInst: Printable[Cat] = new Printable[Cat] {
    def format(value: Cat): String = s"${value.name} is a ${value.age} year-old ${value.color} cat."
  }
}

object Printable {
  def format[A](value: A)(implicit prn: Printable[A]): String =
    prn.format(value)

  def print[A](value: A)(implicit prn: Printable[A]): Unit =
    println(prn.format(value))
}

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit prn: Printable[A]): String =
      prn.format(value)

    def print(implicit prn: Printable[A]): Unit =
      println(prn.format(value))
  }
}