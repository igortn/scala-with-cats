package misc.tpolecat.laws.discipline

import cats.{Eq, Group}
import cats.laws.discipline._
import misc.tpolecat.laws.GroupHomomorphismLaws
import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll
import org.typelevel.discipline.Laws

trait GroupHomomorphismTests[A, B] extends Laws {
  val laws: GroupHomomorphismLaws[A, B]

  def groupHomomorphism(implicit aa: Arbitrary[A], eq: Eq[B]): RuleSet =
    new SimpleRuleSet(
      "groupHomomorphism",
      "combine" -> forAll((a1: A, a2: A) => laws.combine(a1, a2)),
      "empty" -> laws.empty,
      "inverse" -> forAll((a: A) => laws.inverse(a))
    )
}

object GroupHomomorphismTests {
  def apply[A, B](f: A => B)(implicit ga: Group[A], gb: Group[B]): GroupHomomorphismTests[A, B] =
    new GroupHomomorphismTests[A, B] {
      val laws = new GroupHomomorphismLaws[A, B](f) {
        val A = ga
        val B = gb
      }
    }
}
