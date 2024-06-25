package core

import org.apache.spark.{SparkConf, SparkContext}

object ByKeyOperations {
  val SparkConf = new SparkConf()
    .setMaster("local[*]")
    .setAppName("RDD")
  val sc = new SparkContext(SparkConf)

  private def aggregate(): Unit = {
    /**
     * aggregateByKey返回的结果可以跟RDD中不同
     * 需要提供
     *  1.zeroValue, 类型可以跟(K,V)中的V不同
     *  2.分区内合并方法，返回值必须跟zeroValue同类型
     *  3.分区间合并方法，参数和返回值都必须跟zeroValue同类型
     */
    val rdd = sc.makeRDD(List(('a',1),('a',2),('b', 2)))
    rdd.aggregateByKey("a")(
      (x,y)=>x+math.pow(y,2).toString, //y是Value
      (x,y)=>x+y //分区间拼接字符串 是分区间不是分组间
    ).collect().foreach(println)
  }

  private def groupBy(): Unit = {
    val rdd = sc.makeRDD(List(1,2,3,4))
    rdd.groupBy(x => x%2).collect().foreach(println)
  }

  private def reduceBy(): Unit = {
    val rdd = sc.makeRDD(List(("k1",1),("k2",2),("k1",2)))
    rdd.reduceByKey(_+_).collect.foreach(println)
  }


  def main(args: Array[String]): Unit = {
    aggregate()
    // groupBy()
    reduceBy()
    sc.stop()
  }
}
