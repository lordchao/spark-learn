package core

import org.apache.spark.{SparkConf, SparkContext}

object FlatMapDemo{

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("demo")
    val sc = new SparkContext(conf)

    sc.makeRDD(List(List(1, 2, 3, 4), List(1, 2, 3)))
      .flatMap(
        list => list
      )
      .collect()
      .foreach(println)

    sc.makeRDD(List("hello scala", "hello spark"))
      .flatMap(
        x => x.split(" ")
      )
      .collect()
      .foreach(println)

    sc.makeRDD(List(List(1, 23), 2, List(4, 5)))
      .flatMap {
        case list: List[_] => list
        case int: Int => List(int)
      }
      .collect()
      .foreach(println)

  }

}
