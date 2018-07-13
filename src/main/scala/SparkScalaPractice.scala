import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object SparkScalaPractice {

  class NotSerializable(i: Int){
    def apply(i: Int): Int = i + 1
  }

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Helloworld").setMaster("local[*]").set("spark.executer.memory","2g")
    val sc = new SparkContext(conf)

    sc.makeRDD(1 to 50, 1).map(i => (i, new NotSerializable(i)(_))).groupByKey().collect().foreach(println(_))



  }

}
