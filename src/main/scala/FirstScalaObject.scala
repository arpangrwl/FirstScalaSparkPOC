object FirstScalaObject {

  def totalSum(value1: Double, value2: Double): Double = {
    return value1 + value2;
  }

  def main(args: Array[String]): Unit = {


    lazy val y = {
      println("inside y")
      15 + 1 + 5;
    }
    var x = {
      println("inside x")
      15 + 20;
    }

   // x = x + 20;

    println(y)
    println(x)

  }
}