package misc.appl

import cats.instances.future._
import cats.syntax.apply._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/*
 * Show that the monadic `for` comprehension runs sequentially,
 * but the applicative constructs run in parallel.
 */

object Ex1 {

  implicit val ec = scala.concurrent.ExecutionContext.global

  def runAsync[T](block: => T): Future[T] = Future {
    println(s"Running in thread ${Thread.currentThread.getName}")
    Thread.sleep(3000)
    block
  }

  def runMonadic(): Int = {
    val future = for {
      x <- runAsync(3)
      y <- runAsync(13)
      z <- runAsync(17)
    } yield x * y * z

    Await.result(future, 10.seconds)
  }

  def runApplicative(): Int = {
    val future = (runAsync(3), runAsync(13), runAsync(17)).mapN {
      (x, y, z) => x * y * z
    }

    Await.result(future, 10.seconds)
  }
}


