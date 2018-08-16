package misc.variance

import java.util.Date

import cats._
import cats.implicits._

object Ex2 {

  val longToDate: Long => Date = new Date(_)

  val dateToLong: Date => Long = _.getTime

  /*
   * We cannot have a functor for Semigroup because we could map from Long to Date, but we would
   * not be able to `combine` dates. We need `contramap` to map from Date to Long and then combine
   * longs, but then we would need `map` to map the result back from Long to Date.
   * This means that we need to have both properties - covariance and contravariance - which means
   * that we need an `Invariant` instance.
   *
   * The fact that we can write the code below means that there is an implicit instance
   * of the converter from Semigroup to Invariant[Semigroup] in scope.
   *
   * Conclusion: Semigroup does form an invariant functor.
   */
  implicit val sgDate: Semigroup[Date] = Semigroup[Long].imap(longToDate)(dateToLong)

  val date: Date = longToDate(123456123L)
  val offset: Date = longToDate(123456L)
  val datePlusOffset: Date = date |+| offset

}
