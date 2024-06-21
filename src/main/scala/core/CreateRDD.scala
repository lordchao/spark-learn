package core

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object CreateRDD {
  def main(args: Array[String]): Unit = {
    val SparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("RDD")
    val sc = new SparkContext(SparkConf)
    val session = SparkSession.builder()
      .appName("app")
      .getOrCreate()

    //从内存中创建RDD
    val rdd = sc.parallelize(List(1, 2, 3, 4))
      .collect()
      .foreach(println)

    val rdd1 = sc.makeRDD(List(1,2,3,4))
      .collect()
      .foreach(println)

    sc.stop()

  }

}
