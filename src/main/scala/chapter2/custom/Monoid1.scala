package chapter2.custom

import simulacrum.{op, typeclass}

@typeclass trait Semigroup1[A] {
  @op("|+|") def combine(a1: A, a2: A): A
}

@typeclass trait Monoid1[A] extends Semigroup1[A] {
  def empty: A
}

object Main1 {

  import implicits._
  import Semigroup1.ops._

  def foldSets[A](sets: Seq[Set[A]]): Set[A] =
    sets.foldLeft(Monoid1[Set[A]].empty)(_ |+| _)

  // remove
  def mergeMaps(m1: Map[String, Int], m2: Map[String, Int]): Map[String, Int] = {
    ???
  }
}
