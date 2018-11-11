package misc.monad_transformers

/*
 * OptionT[F, A] is a light wrapper around F[Option[A]] with some
 * convenience methods.
 */

import cats.data.OptionT
import cats.instances.future._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Ex1 {

  class User {
    val accountId: Long = ???
  }
  class Account
  class Money

  def findUserById(userId: Long): OptionT[Future, User] = ???

  def findAccountById(accountId: Long): OptionT[Future, Account] = ???

  def getAccountFunds(account: Account): OptionT[Future, Money] = ???

  def getUserFunds(userId: Long): OptionT[Future, Money] = for {
    user <- findUserById(userId)
    account <- findAccountById(user.accountId)
    funds <- getAccountFunds(account)
  } yield funds
}
