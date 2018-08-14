package misc.appl

import cats.Functor


/**
  * Small subset of `Apply` that has only `product`.
  */
trait Appl[F[_]] extends Functor[F] {

  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]

  def pure[A](a: A): F[A]

}

object Appl {

  def apply[F[_]](implicit inst: Appl[F]): Appl[F] = inst

  implicit val applOption: Appl[Option] =
    new Appl[Option] {

      def pure[A](a: A): Option[A] = Some(a)

      def product[A, B](fa: Option[A], fb: Option[B]): Option[(A, B)] = (fa, fb) match {
        case (Some(a), Some(b)) => Some((a, b))
        case _ => None
      }

      def map[A, B](fa: Option[A])(f: A => B): Option[B] =
        fa.map(f)
    }

}

object Main extends App {

  val x = Appl[Option].product(Appl[Option].pure(3), Some(7))
  println(x)

  val y = Appl[Option].product(Some(3), None)
  println(y)
}