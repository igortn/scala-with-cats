/*
 * https://skillsmatter.com/skillscasts/11626-keynote-pushing-types-and-gazing-at-the-stars
 */

package misc.tpolecat

import cats.Eq
import cats.implicits._
import cats.kernel.CommutativeGroup

sealed abstract class Angle(val degrees: Int) {

  def +(that: Angle): Angle =
    Angle.fromDegrees(this.degrees + that.degrees)

  def -(that: Angle): Angle =
    Angle.fromDegrees(this.degrees - that.degrees)

  def toHourAngle: HourAngle =
    HourAngle.fromHours(degrees / 15)
}

object Angle {
  def fromDegrees(n: Int): Angle = new Angle(((n % 360) + 360) % 360) {}

  val zero: Angle = fromDegrees(0)

  implicit val EqAngle: Eq[Angle] =
    Eq.by(_.degrees)

  implicit val CommGroupAngle: CommutativeGroup[Angle] =
    new CommutativeGroup[Angle] {
      override def inverse(a: Angle): Angle = Angle.fromDegrees(- a.degrees)

      override def empty: Angle = zero

      override def combine(x: Angle, y: Angle): Angle = x + y
    }
}