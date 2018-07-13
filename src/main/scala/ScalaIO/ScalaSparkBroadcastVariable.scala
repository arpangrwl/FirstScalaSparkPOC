package ScalaIO

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object ScalaSparkBroadcastVariable {


  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("RetailDataAnalysis").setMaster("local[4]").set("spark.executor.memory", "2g")
    val sc = new SparkContext(conf)

    val flights = sc.parallelize(List(
      ("SEA", "JFK", "DL", "418",  "7:00"),
      ("SFO", "LAX", "AA", "1250", "7:05"),
      ("SFO", "JFK", "VX", "12",   "7:05"),
      ("JFK", "LAX", "DL", "424",  "7:10"),
      ("LAX", "SEA", "DL", "5737", "7:10")))

    // Dimension table
    val airports = sc.parallelize(List(
      ("JFK", "John F. Kennedy International Airport", "New York", "NY"),
      ("LAX", "Los Angeles International Airport", "Los Angeles", "CA"),
      ("SEA", "Seattle-Tacoma International Airport", "Seattle", "WA"),
      ("SFO", "San Francisco International Airport", "San Francisco", "CA")))

    // Dimension table
    val airlines = sc.parallelize(List(
      ("AA", "American Airlines"),
      ("DL", "Delta Airlines"),
      ("VX", "Virgin America")))

    val airportsMap = sc.broadcast(airports.map{case(a, b, c, d) => (a, c)}.collectAsMap)

    airports.map{case(a, b, c, d) => (a, c)}.collectAsMap.mkString.foreach(println)


    val airlinesMap = sc.broadcast(airlines.collectAsMap)

    flights.map{case(a, b, c, d, e) =>
      (airportsMap.value.get(a).get,
        airportsMap.value.get(b).get,
        airlinesMap.value.get(c).get, d, e)}.collect().foreach(println)

  }
}
