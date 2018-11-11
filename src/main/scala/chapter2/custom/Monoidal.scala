package chapter2.custom

import cats.Functor
import simulacrum.typeclass

// Monoidal Functor, aka Applicative

@typeclass trait Monoidal[F[_]] extends Functor[F] {
  def product[A, B](fa: F[A], fb: F[B]): F[(A, B)]
  def pure[A](a: A): F[A]
}