package misc.tpolecat

import cats.Eq
import cats.implicits._
import cats.kernel.CommutativeGroup

sealed abstract class HourAngle(val hours: Int) {
  def +(that: HourAngle): HourAngle =
    HourAngle.fromHours(this.hours + that.hours)

  def -(that: HourAngle): HourAngle =
    HourAngle.fromHours(this.hours - that.hours)

  def toAngle: Angle =
    Angle.fromDegrees(hours * 15)
}

object HourAngle {
  def fromHours(n: Int): HourAngle = new HourAngle(((n % 24) + 24) % 24) {}

  val zero: HourAngle = fromHours(0)

  implicit val EqHourAngle: Eq[HourAngle] =
    Eq.by(_.hours)

  implicit val CommutativeGroupHourAngle: CommutativeGroup[HourAngle] =
    new CommutativeGroup[HourAngle] {
      def inverse(a: HourAngle): HourAngle =
        HourAngle.fromHours(- a.hours)

      def empty: HourAngle = zero

      override def combine(x: HourAngle, y: HourAngle): HourAngle =
        HourAngle.fromHours(x.hours + y.hours)
    }
}
