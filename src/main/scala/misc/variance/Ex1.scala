package misc.variance

import cats._
import cats.implicits._

case class Money(amount: Int)

case class Salary(size: Money)

object Ex1 {

  implicit val showMoney: Show[Money] = Show.show(m => s"$$${m.amount}")

  /*
   * We cannot map Money to Salary, but we can map Salary to Money.
   * Therefore, if we want to build the instance of Show[Salary] from the instance of Show[Money],
   * then we need to use the Show's contravariant property and call `contramap`
   * on the Show[Money] instance.
   */
  implicit val showSalary: Show[Salary] = showMoney.contramap(_.size)

}
