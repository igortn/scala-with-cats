package chapter2.custom

import simulacrum.{op, typeclass}

@typeclass trait Semigroup1[A] {
  @op("|+|") def combine(a1: A, a2: A): A
}

@typeclass trait Monoid1[A] extends Semigroup1[A] {
  def empty: A
}

object implicits {
  implicit val strMonoid1: Monoid1[String] = new Monoid1[String] {
    override def empty: String = ""
    override def combine(a1: String, a2: String): String = a1 ++ a2
  }

  implicit def sUnionMonoid1[A]: Monoid1[Set[A]] = new Monoid1[Set[A]] {
    override def empty: Set[A] = Set.empty[A]
    override def combine(a1: Set[A], a2: Set[A]): Set[A] = a1 union a2
  }
}

object Main1 {

  import implicits._
  import Semigroup1.ops._

  def foldSets[A](sets: Seq[Set[A]]): Set[A] =
    sets.foldLeft(Monoid1[Set[A]].empty)(_ |+| _)

}
