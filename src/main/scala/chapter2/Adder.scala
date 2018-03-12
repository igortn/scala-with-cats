package chapter2

import cats._

final case class Order(total: Double, quantity: Int)

object Order {
  def apply(): Order = new Order(0, 0)

  implicit val monoidInst: Monoid[Order] =
    new Monoid[Order] {
      def empty: Order = Order()

      def combine(x: Order, y: Order): Order =
        Order(x.total + y.total, x.quantity + y.quantity)
    }

  val addOrders: List[Order] => Order =
    items => Adder.add(items)
}

object Adder {
  /** Fold list of Ints. **************************/
  //  def add(items: List[Int]): Int =
  //    items.foldLeft(0)(_+_)

  /** Fold list of A's using implicit Monoid[A]. **/
  //  def add[A](items: List[A])
  //            (implicit m: Monoid[A]): A =
  //    m.combineAll(items)

  /** Same as above using context bound syntax. ***/
  //  def add[A: Monoid](items: List[A]): A =
  //    items.foldLeft(Monoid.empty[A])(_ |+| _)

  /** Same as above using Monoid API. *************/
  def add[A: Monoid](items: List[A]): A =
    Monoid[A].combineAll(items)
}
