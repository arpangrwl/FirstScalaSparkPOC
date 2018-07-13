package ScalaIO

import scala.io._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.Column
import org.apache.spark.sql.functions._

object IOScalaPOC {

  case class TrueFalse(flag: String,types: String, rdl_amount_usd: Double, wtxn_amount_usd: Double, diff_amount_usd: Double)

  def main(args: Array[String]): Unit = {

    //   println(Source.fromFile("C:/Users/arpagarwal/Desktop/wtxn_rdl_true_conflict_report.csv"))

    //    val s1 = Source.fromFile("C:/Users/arpagarwal/Desktop/wtxn_rdl_true_conflict_report.csv").mkString
    //   val s2: Array[String] = s1.split("\\n")
    //    1-Party|A|#|CR|284073441.2572438|0|-284073441.2572438
    //    1-Party|F|#|CR|150488069389.0316283|80100.2952242|-150487989288.7364041

    val diffFunc = udf {(val1 : Double, val2 : Double ) => val2 - val1 }
    val filterFunc = udf {(val1 : String) => val1.substring(0,1) }

    val spark = SparkSession.builder()
      .appName("True-False-Conflict")
      .master("local[4]")
      .getOrCreate()

    import spark.implicits._
    // s2.foreach(println)

    val df = spark.read.textFile("wtxn_rdl_true_conflict_report.csv")
    val df1 = df.map(x => x.split("\\|"))
    val df2 = df1.map(x => (TrueFalse(x(0).toString, x(1).toString, x(4).toDouble, x(5).toDouble, x(6).toDouble))).toDF()

    //df2.select("flag", "types", "rdl_amount_usd", "wtxn_amount_usd", "diff_amount_usd").groupBy($"flag", $"types")
    val df3 = df2.groupBy($"flag", $"types").agg( sum($"rdl_amount_usd") as "total_rdl", sum($"wtxn_amount_usd") as "total_wtxn", sum($"diff_amount_usd") as "Calculated_diff")
  //  df3.select($"flag", $"types", $"total_rdl", $"total_wtxn", diffFunc($"total_rdl", $"total_wtxn") as "newly_cal_diff"  , $"Calculated_diff" ).show()

    df2.createGlobalTempView("TrueFalseConf")
    spark.sql("desc formatted global_temp.TrueFalseConf").show()
    spark.sql("select flag, types, sum(rdl_amount_usd) as ttl_rdl , sum(wtxn_amount_usd) as ttl_wtx, sum(diff_amount_usd) as ttl_diff from global_temp.TrueFalseConf where flag = \"1-Party\" group by flag, types").show()

    println("-----------------creating SQL query ------------------------")

    df2.printSchema()
    df2.createGlobalTempView("true_conflict")
    spark.udf.register("filterFunc", (val1 : String) => val1.substring(0,1))
    spark.sql("select filterFunc(flag) as new_type , types, sum(rdl_amount_usd) as ttl_rdl , sum(wtxn_amount_usd) as ttl_wtx, sum(diff_amount_usd) as ttl_diff from global_temp.true_conflict group by flag, types").show()





  }
}
