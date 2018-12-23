package misc.appl

import cats.Functor

trait Appl1[F[_]] extends Functor[F] {

  def ap[A, B](fab: F[A => B])(fa: F[A]): F[B]

  def pure[A](a: A): F[A]

  def product[A, B](fa: F[A])(fb: F[B]): F[(A, B)] =
    ap(map(fa)(a => (b: B) => (a, b)))(fb)
}

object Appl1 {

  def sequence[F[_], G[_] : Appl1, A](xs: F[G[A]]): G[F[A]] = ???

}
