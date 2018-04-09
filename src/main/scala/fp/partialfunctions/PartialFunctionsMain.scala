package fp.partialfunctions

object PartialFunctionsMain {
  def main(args: Array[String]): Unit = {

    def one: PartialFunction[Int, String] = { case 1 => "one" }

//    def two: Int => String = { case 2 => "two" }
    def two: PartialFunction[Int, String] = { case 2 => "two" }

    def wildcard: PartialFunction[Int, String] = {case _ => "undefined"}

    def partial: PartialFunction[Int, String] = one orElse two orElse wildcard

    println(partial(2))
    println(partial.isDefinedAt(3))

    // filter with a partial function
    case class Phone(brand: String, os: String)
    val phones = List(Phone("Samsung", "Android"), Phone("Lenovo", "WindowsMobile"))
    println(phones.filter { case Phone(_, os) => os == "Android" })

    val sample = 1 to 10
    def isEven: PartialFunction[Int, String] = { case x if x%2 == 0 => s"$x is even" }
    val evenNumbers = sample collect isEven
    println(evenNumbers)
  }
}
