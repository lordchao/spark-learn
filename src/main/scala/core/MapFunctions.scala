package core

import org.apache.spark.{SparkConf, SparkContext}

object MapFunctions{
  val conf = new SparkConf()
    .setMaster("local")
    .setAppName("demo")
  val sc = new SparkContext(conf)

  private def mapPartitionsDemo(): Unit = {
    /**
     * 一次性处理分区内的所有数据，分区所有数据会先放到内存中
     */
    val rdd = sc.makeRDD(List(1,2,3,4))
    rdd.mapPartitions(
        iter => iter.map(_*2)
      )
      .collect()
      .foreach(println)
  }

  private def mapDemo(): Unit = {
    /**
     * 分区内的数据一条一条处理
     */
    val rdd = sc.makeRDD(List(1,2,3,4))
    rdd.map(_*2).collect.foreach(println)
  }

  private def flatMapDemo(): Unit = {
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

  def main(args: Array[String]): Unit = {
    // mapPartitionsDemo()
    mapDemo()
  }

}
