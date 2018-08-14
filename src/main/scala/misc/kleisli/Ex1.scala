package misc.kleisli

import cats.implicits._
import cats.data.Kleisli

import scala.util.Try

object Ex1 {

  /*
   * To be able to compose, there has to be an instance of FlatMap for Kleisli F (as in
   * Kleisli[F[_], A, B]). In this example, there is an implicit instance of FlatMap
   * for Option in scope.
   */

  val parseInt: String => Option[Int] =
    s => Try(s.toInt).toOption

  val reciprocal: Int => Option[Double] =
    n => 1.0 / n match {
      case Double.PositiveInfinity => None
      case Double.NegativeInfinity => None
      case m => Some(m)
    }

  val parseAndFlip: String => Option[Double] = {
    Kleisli(parseInt) andThen Kleisli(reciprocal)
  }.run
}
