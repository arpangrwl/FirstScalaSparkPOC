class Companion_Obj {

  def fn( abc : String) :Unit = {
     println( abc );
  }

def fnc(val4 :String) : Any = {

  if(val4.asInstanceOf[String] == "String")
    {
      return val4.toString.toUpperCase;
    }
//  else if(val4.asInstanceOf[Int] == Int){
//    return Integer.parseInt(val4.toString) * 10;
//  }
//  else if(val4.asInstanceOf[Boolean] == Boolean){
//    return true;
//  }

}


}

object Companion_Obj {
  def main(args: Array[String]): Unit = {

   val classdef : Companion_Obj = new Companion_Obj();

 //   println(classdef.fnc("Arpan"));


    val x: Any = ""

    println(f(x));


  }


  def f(v: Any) = v match {
    case _: Int    => "Int"
    case _: String => "String"
    case _         => "Unknown"
  }

}