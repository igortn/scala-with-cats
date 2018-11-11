package chapter2.custom

import cats.{Applicative, Foldable, Functor}
import simulacrum.typeclass

@typeclass
trait Traverse[T[_]] extends Foldable[T] with Functor[T] {
  def sequence[F[_]: Applicative, A](tfa: T[F[A]]): F[T[A]]
  def traverse[F[_]: Applicative, A, B](tfa: T[A])(f: A => F[B]): F[T[B]]
}
