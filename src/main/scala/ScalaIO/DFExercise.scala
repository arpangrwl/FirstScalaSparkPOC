package ScalaIO

import org.apache.spark.sql.SparkSession

object DFExercise {

  def main(args: Array[String]): Unit = {

    var spark = SparkSession.builder().appName("Dataframe POC").master("local[4]").getOrCreate()

    val df = spark.read.textFile("wtxn_rdl_true_conflict_report.csv")


  }

}
