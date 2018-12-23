package misc.tpolecat.laws

import cats.Group
import cats.laws._
import cats.syntax.group._

abstract class GroupHomomorphismLaws[A, B](f: A => B) {
  implicit val A: Group[A]
  implicit val B: Group[B]

  def combine(a1: A, a2: A): IsEq[B] =
    f(a1 |+| a2) <-> (f(a1) |+| f(a2))

  def empty: IsEq[B] = f(A.empty) <-> B.empty

  def inverse(a: A): IsEq[B] =
    f(a.inverse) <-> f(a).inverse
}
