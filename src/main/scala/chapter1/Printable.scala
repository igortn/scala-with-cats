package chapter1

trait Printable[A] {
  def format(value: A): String
}

object PrintableInstances {
  implicit val prStringInstance: Printable[String] = new Printable[String] {
    def format(value: String): String = value
  }

  implicit val prIntInstance: Printable[Int] = new Printable[Int] {
    def format(value: Int): String = value.toString
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