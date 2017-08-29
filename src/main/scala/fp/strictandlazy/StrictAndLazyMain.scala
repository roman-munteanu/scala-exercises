package fp.strictandlazy

import scala.annotation.tailrec

/**
  * Created by Roman Munteanu on 8/29/17.
  */
object StrictAndLazyMain {
  def main(args: Array[String]): Unit = {

    def if2[A](cond: Boolean, onTrue: () => A, onFalse: () => A): A =
      if(cond) onTrue() else onFalse()

    def if3[A](cond: Boolean, onTrue: => A, onFalse: => A): A =
      if(cond) onTrue else onFalse

    if2(true, () => println("A"), () => println("B"))

    val res = if3(false, sys.error("ERROR"), "BB")
    println(res)

    def maybeTwice2(cond: Boolean, i: => Int): Int = {
      lazy val j = i
      if(cond) j+j else 0
    }

    val res2 = maybeTwice2(true, {println("aside"); 25+13})
    println(res2)


    sealed trait MyStream[+A] {
      def toList: List[A] = {
        @tailrec
        def streamToList(st: MyStream[A], acc: List[A]): List[A] = st match {
          case Cons(head, tail) => streamToList(tail(), head() :: acc)
          case _ => acc
        }
        streamToList(this, Nil).reverse
      }

      def take(n: Int): MyStream[A] = ???

    }
    case object Empty extends MyStream[Nothing]
    case class Cons[+A](h: () => A, t: () => MyStream[A]) extends MyStream[A]

    object MyStream {

      def cons[A](hd: A, tl: MyStream[A]): MyStream[A] = {
        lazy val h = hd
        lazy val t = tl
        Cons(() => h, () => t)
      }

      def empty[A]: MyStream[A] = Empty

      def apply[A](as: A*): MyStream[A] = {
        if(as.isEmpty) empty else cons(as.head, apply(as.tail: _*))
      }

//      def headOption[A]: Option[A] = this match {
//        case Empty => None
//        case Cons(h, t) => Some(h())
//      }
    }


  }
}
