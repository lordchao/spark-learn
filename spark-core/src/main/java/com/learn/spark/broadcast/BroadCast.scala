package com.learn.spark.broadcast

import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable

object BroadCast {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("app")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(("a", 1), ("b", 23), ("c", 2)))
    val rdd2 = sc.makeRDD(List(("a", 10), ("b", 23), ("c", 2)))

    //join会导致数据量变多，中间有shuffle，不推荐使用
    val joinRDD = rdd1.join(rdd2)

    val map = mutable.Map(("a",10),("b", 23),("c",2))

    //封装广播变量
    val bc = sc.broadcast(map)

    // 中间没有shuffle
    rdd1.map {
      case (w, c) => {
        //获取广播变量
        val l:Int = bc.value.getOrElse(w, 0)
        (w,(c,l))
      }
    }.collect().foreach(println)

    //joinRDD.collect().foreach(println)

    sc.stop()
  }

}
