package misc

import cats.kernel.Monoid

// `Aux` pattern.

trait Foo[A] {
  type B
  def value: B
}

object Foo {

  implicit val fi: Foo[Int] =
    new Foo[Int] {
      type B = String
      def value: String = "Foo"
    }

  implicit val fs: Foo[String] =
    new Foo[String] {
      type B = Boolean
      def value: Boolean = false
    }

  // Return type depends on the dependent type.
  def foo[T](t: T)(implicit f: Foo[T]): f.B =
    f.value


//  Scala does not allow this.holderName
//  This is where the `Aux` pattern comes to rescue.
//
//  def baz[T](t: T)(implicit f: Foo[T], m: Monoid[f.B]): f.B =
//    m.empty


  type Aux[A0, B0] = Foo[A0] { type B = B0 }

  def baz[T, R](t: T)(implicit f: Foo.Aux[T, R], m: Monoid[R]): R =
    m.empty
}
