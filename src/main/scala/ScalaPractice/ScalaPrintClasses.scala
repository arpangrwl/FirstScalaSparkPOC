package ScalaPractice

object ScalaPrintClasses {


  def main(args: Array[String]): Unit = {

    var factObj = new Factorial(5)
    val value = factObj.findTheFactorial()
    print( value)

    val value1 = factObj.recFact(5,5)
    print(value1)
  }

}
