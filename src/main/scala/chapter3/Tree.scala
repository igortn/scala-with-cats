package chapter3

import cats.Functor

sealed trait Tree[A]

final case class Branch[A](left: Tree[A], right: Tree[A])
  extends Tree[A]

final case class Leaf[A](value: A) extends Tree[A]

object Tree {
  implicit val treeFunctorInst: Functor[Tree] =
    new Functor[Tree] {
      override def map[A, B](ta: Tree[A])(f: A => B): Tree[B] =
        ta match {
          case Branch(left, right) =>
            Branch(map(left)(f), map(right)(f))

          case Leaf(value) =>
            Leaf(f(value))
        }
    }
}
