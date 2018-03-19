package chapter3

import cats._
import cats.syntax.functor._

final case class Box[A](value: A)

object Box {
  implicit val boxFunctorInst: Functor[Box] =
    new Functor[Box] {
      override def map[A, B](fa: Box[A])(f: A => B): Box[B] =
        Box(f(fa.value))
    }
}

object Misc {

  /**
    * Apply a given function to an integer in a context.
    *
    * The caller has to provide the implicit either explicitly :),
    * or by importing a proper cats instances.
    * Ex: import cats.instances.option._
    */
  def f[T[_]](ctx: T[Int])(g: Int => Int)
             (implicit func: Functor[T]): T[Int] =
    ctx.map(g)

  // Use this function with Option, List and Box.
}
