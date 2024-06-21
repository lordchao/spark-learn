package core.rdd

import org.apache.spark.{SparkConf, SparkContext}

object ByKeyOperations {
  val SparkConf = new SparkConf()
    .setMaster("local[*]")
    .setAppName("RDD")
  val sc = new SparkContext(SparkConf)

  private def aggregate(): Unit = {
    val rdd = sc.makeRDD(List(('a',1),('a',2),('b', 2)))
    rdd.aggregateByKey(0)(
      (x,y)=>math.max(x,y),
      (x,y)=>x+y
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
    // aggregate()
    // groupBy()
    reduceBy()
    sc.stop()
  }
}
