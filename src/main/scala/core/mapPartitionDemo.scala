package core.rdd

import org.apache.spark.{SparkConf, SparkContext}

object mapPartitionDemo{
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("demo")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List(1,2,3,4))

    //一次行把一个分区的数据取出
    rdd.mapPartitions(
      iter => iter.map(_*2)
    )
      .collect()
      .foreach(println)
  }
}
