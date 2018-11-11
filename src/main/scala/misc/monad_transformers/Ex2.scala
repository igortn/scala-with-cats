package misc.monad_transformers

/*
 * EitherT[F, A, B] is a wrapper around F[Either[A, B]].
 */

import cats.data.{EitherT, OptionT}
import cats.instances.future._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


class User
case class UserNotFound(msg: String) extends Exception(msg)

case class Item(state: String)
class ItemOrder
case class ItemNotFound(msg: String) extends Exception(msg)
case class InvalidItemState(msg: String) extends Exception(msg)

object Ex2 {

  def findUserById(userId: Long): OptionT[Future, User] = ???

  def ensureUserExists(userId: Long): EitherT[Future, UserNotFound, User] =
    findUserById(userId).toRight(left = UserNotFound(s"user not found, userId = $userId"))

  /******************************************************************/

  def findItemById(itemId: Long): OptionT[Future, Item] = ???

  def ensureItemExists(itemId: Long): EitherT[Future, ItemNotFound, Item] =
    findItemById(itemId).toRight(left = ItemNotFound(s"item not found, id: $itemId"))

  def ensureItemState(actual: String, expected: String): EitherT[Future, InvalidItemState, Unit] =
    EitherT.cond[Future](actual == expected, (), InvalidItemState(s"actual = $actual, expected = $expected"))

  def placeOrder(userId: Long, itemId: Long, count: Int): Future[ItemOrder] = ???

  def purchase(userId: Long, itemId: Long, count: Int): EitherT[Future, Exception, ItemOrder] =
    for {
      _ <- ensureUserExists(userId)
      item <- ensureItemExists(itemId)
      _ <- ensureItemState(item.state, "Available")
      order <- EitherT.liftF(placeOrder(userId, itemId, count))
    } yield order
}
