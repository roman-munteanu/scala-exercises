package fp.errorhandling

/**
  * Created by Roman Munteanu on 8/22/17.
  */
class ErrorHandlingMainSpec extends org.specs2.mutable.Specification {

  "ErrorHandlingMain" >> {
    import ErrorHandlingMain._

    "map22" >> {
      val expectedResult = Some("Result: 12")
      val actualResult = map22(Some(3), Some(4))( (x, y) => "Result: " + x*y )
      actualResult must beEqualTo(expectedResult)
    }

    "traverse" >> {
      val actualResult = traverse(List(1,2,3,4))( x => Some(x*x) )
      actualResult must beEqualTo(Some(List(1,4,9,16)))
    }
  }
}
