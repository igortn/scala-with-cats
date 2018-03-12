package chapter2.custom

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]) =
    monoid
}

/** ********************************************/
/** Different Boolean monoid instances.     **/
/** You cannot import them simultaneously.  **/
/** ********************************************/

object OrMonoid {
  implicit val instance: Monoid[Boolean] =
    new Monoid[Boolean] {

      def empty: Boolean = false

      def combine(x: Boolean, y: Boolean): Boolean =
        x || y
    }
}

object AndMonoid {
  implicit val instance: Monoid[Boolean] =
    new Monoid[Boolean] {

      def empty: Boolean = true

      def combine(x: Boolean, y: Boolean): Boolean =
        x && y
    }
}

object XorMonoid {
  implicit val instance: Monoid[Boolean] =
    new Monoid[Boolean] {

      def empty: Boolean = false

      def combine(x: Boolean, y: Boolean): Boolean =
        x ^ y
    }
}

object XnorMonoid {
  implicit val instance: Monoid[Boolean] =
    new Monoid[Boolean] {

      def empty: Boolean = true

      def combine(x: Boolean, y: Boolean): Boolean =
        !(x ^ y)
    }
}

/** ********************************************/
/** Monoids and semigroups for sets.      **/
/** ********************************************/

object SetUnionMonoid {
  implicit def instance[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {

      def empty: Set[A] = Set.empty[A]

      def combine(x: Set[A], y: Set[A]): Set[A] =
        x union y
    }
}

// This cannot be a monoid because there is no
// empty element.
object SetIntersectionSemigroup {
  implicit def instance[A]: Semigroup[Set[A]] =
    new Semigroup[Set[A]] {
      def combine(x: Set[A], y: Set[A]): Set[A] =
        x intersect y
    }
}

/***********************************************/

object Main {

  import SetUnionMonoid._

  def run(): Unit = {

    val s = Monoid[Set[Int]] combine(
      Set(1, 2, 3), Set(7, 8)
    )

    assert(s == Set(1,2,3,7,8))
  }
}
