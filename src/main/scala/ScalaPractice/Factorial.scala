package ScalaPractice

import scala.annotation.tailrec

class Factorial (val n: Int){

  def findTheFactorial(): Int = {
    var finalNo = 1
    for(no <- 1 to n)
      finalNo = finalNo * no

    return finalNo
  }

  @tailrec
  final def recFact(n : Int, n1 :Int): Int = {
    if (n1 == 1 || n1 == 0) 1
    else
      recFact(n-1, n1 * (n1-1))
  }


}
