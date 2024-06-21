package core.rdd

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

import scala.util.matching.Regex

object CreateRDD {
  def main(args: Array[String]): Unit = {
    val SparkConf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("RDD")
    val sc = new SparkContext(SparkConf)
    val session = SparkSession.builder()
      .appName("ji")
      .getOrCreate()

    //从内存中创建RDD
    val rdd = sc.parallelize(List(1, 2, 3, 4))
      .collect()
      .foreach(println)

    val rdd1 = sc.makeRDD(List(1,2,3,4))
      .collect()
      .foreach(println)


    //目标 从日志中入读ip 时间 请求方式 写入到mysql
    //RDD从一个分区内执行循序是有序的，不同分区之内是无序的
    val lines = sc.textFile("/Users/c-henry.liu/Downloads/web.log")
    val pattern = new Regex("\\[(.*?)\\]")
    val date =  lines.map(
      line => pattern findFirstIn line
    )

    val ip = lines.flatMap(_.split(" "))

    val data = List(date, ip)
    val column = List("date", "ip")

    case class Record(id:Int, date:String)

  }

}
