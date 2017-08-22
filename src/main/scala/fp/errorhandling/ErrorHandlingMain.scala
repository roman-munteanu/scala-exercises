package fp.errorhandling

import scala.util.Try

object ErrorHandlingMain {
  def main(args: Array[String]): Unit = {

    /*
    trait Option[+A] {
      def map[B](f: A => B): Option[B] = this match {
        case Some(a) => Some(f(a))
        case None => None
      }

      def flatMap[B](f: A => Option[B]): Option[B] = this match {
        case Some(a) => f(a)
        case None => None
      }

      def getOrElse[B >: A](default: => B): B = this match {
        case Some(b) => b
        case None => default
      }

      def orElse[B >: A](ob: => Option[B]): Option[B] =
        if (this.isDefined) this
        else ob

      def filter(f: A => Boolean): Option[A] = this match {
        case Some(a) if f(a) => this
        case None => None
      }
    }
    */

    def mean(xs: Seq[Double]): Option[Double] =
      if (xs.isEmpty) None
      else Some(xs.sum / xs.size)

    def variance(xs: Seq[Double]): Option[Double] =
      mean(xs) flatMap { m => mean(xs.map(x => math.pow(x - m, 2))) }

    def lift[A, B](f: A => B): Option[A] => Option[B] = _ map f


    def insuranceRateQuote(age: Int, numberOfSpeedingTickets: Int): Double = ???

    object CustomTry {
      def apply[A](a: A): Option[A] =
        try {
          Some(a)
        } catch {
          case ex: Exception => None
        }
    }

    def parseInsuranceRateQuote(
                                 age: String,
                                 numberOfSpeedingTickets: String): Option[Double] = {
      val optAge: Option[Int] = CustomTry(age.toInt)
      val optTickets: Option[Int] = CustomTry(numberOfSpeedingTickets.toInt)
      map2(optAge, optTickets)(insuranceRateQuote)
    }

    def map2[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
      a.flatMap(aa => b.map(bb => f(aa, bb)) )

    def map22[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
      for {
        aa <- a
        bb <- b
      } yield f(aa, bb)


    def sequence[A](a: List[Option[A]]): Option[List[A]] =
      if (a.contains(None)) None
      else Some(a.map(_.get))


    def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] =
      Some(for {
        aa <- a
        b <- f(aa)
      } yield b)

  }
}
