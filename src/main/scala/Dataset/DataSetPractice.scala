package Dataset

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SparkSession._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.Column
import org.bouncycastle.asn1.isismtt.x509.ProfessionInfo


object DataSetPractice {
  case class Person(name : String, prof : String , age : Int)

  case class deptt(deptName : String , depttNo: Int)
  case class emp(name : String , department : deptt)


  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("Some App name").master("local[*]").getOrCreate();

    import spark.implicits._

//    val s = Seq(Person("Arpan", "SSE", 30),
//      Person("Nipun", "SE", 25),
//      Person("Sanj", "SE", 60),
//      Person("Nishtha", "SE", 28),
//      Person("Mrid", "HW", 52))
//
//    val ds = s.toDS()
//    ds.printSchema()
//    ds.where( $"age" > 10 ).groupBy($"prof").agg(count($"prof") as "cnt").show()

    val employees = Seq(emp("Arpan", deptt("Comp_Science", 12))
    , emp("Nipun", deptt("IT", 13)),
      emp("Vig", deptt("ME", 14)))

    val empDS = employees.toDS()

    empDS.printSchema()
    empDS.where($"department".getField("depttNo") > 13).select($"name" as "Name of Emp", $"department".getField("deptName") as "Name of Department").show()



//and $"name".substr(0,2).equals("Ni")


  }

  def nameBeginsWithALetter(name : String , word: String) : Option[String] = {

    if(name.substring(0,word.length).equals(word))
      return Some(name)
    else
      return None
  }

}
