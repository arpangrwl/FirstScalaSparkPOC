package ScalaPractice

object MapInScalaPractice {

  def main(args: Array[String]): Unit = {

    val mapValues : Map[Int, String] = Map( 12 -> "Arpan", 13 -> "Ram", 16 -> "Nipun", 10 -> "Rol" , 16 -> "Raju")

    print(mapValues.get(12).get)


    mapValues.keys.foreach(key => print("Results of key s :- " + key + "\n Reult of values are :- " + mapValues.get(key) ))
  }

}
