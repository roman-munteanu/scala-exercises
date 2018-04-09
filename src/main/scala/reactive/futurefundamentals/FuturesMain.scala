package reactive.futurefundamentals

import scala.concurrent.Future

object FuturesMain {
  def main(args: Array[String]): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global

    case class WSResponse(status: Int)

    val response: Future[WSResponse] = Future { WSResponse(200) }

    val siteIsOnline: Future[Boolean] = response.map(r => r.status == 200)

    siteIsOnline.foreach { isOnline =>
      println(s"Site is ${ if (isOnline) "UP" else "DOWN" }")
    }

    val siteMaybeAvailable: Future[Option[Boolean]] =
      response.map(r => Some(r.status == 200)) recover {
        case _: java.net.ConnectException => None
      }


  }
}
