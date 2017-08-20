package fp.errorhandling

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


  }
}
