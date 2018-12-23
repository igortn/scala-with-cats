package misc.tpolecat

import cats.kernel.laws.discipline.{CommutativeGroupTests, EqTests}
import cats.tests.CatsSuite
import misc.tpolecat.laws.discipline.GroupHomomorphismTests
import org.scalacheck.Arbitrary._
import org.scalacheck.{Arbitrary, Cogen}

class AngleSuite extends CatsSuite {

  implicit val ArbAngle: Arbitrary[Angle] =
    Arbitrary(arbitrary[Int].map(Angle.fromDegrees))

  implicit val CogAngle: Cogen[Angle] =
    Cogen[Int].contramap(_.degrees)

  implicit val ArbHourAngle: Arbitrary[HourAngle] =
    Arbitrary(arbitrary[Int].map(HourAngle.fromHours))

  implicit val CogHourAngle: Cogen[HourAngle] =
    Cogen[Int].contramap(_.hours)

  checkAll("EqAngle", EqTests[Angle].eqv)
  checkAll("EqHourAngle", EqTests[HourAngle].eqv)
  checkAll("CommGroupAngle", CommutativeGroupTests[Angle].commutativeGroup)

  /*
   * The homomorphism from HourAngle to Angle is injective, the opposite way is surjective, and
   * the laws for mapping Angle to HourAngle will break. Different laws for the pair of monomorphism
   * and epimorphism are needed.
   */
  checkAll("GroupHomomorphism", GroupHomomorphismTests[HourAngle, Angle](_.toAngle).groupHomomorphism)
}
