package com.learn.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

import scala.util.matching.Regex

object basic {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName("sql")

    val spark = SparkSession
      .builder()
      .config(conf)
      .getOrCreate()

    import spark.implicits._ //因为需要隐式的encoder

    val pattern = new Regex("\\[([^\\[\\]]*?)\\]")

    val df = spark.read
      .textFile("/Users/c-henry.liu/Downloads/web.log")
      .map(row => { //需要一个function不是method
        val ip = row.split(" ")(0)
        val date = pattern.findAllIn(row).matchData.map(_.group(1)).mkString
        val request =
          "\"(.*?)\"".r.findAllIn(row).matchData.map(_.group(1)).mkString
        Request(ip, request, date) //可以不new因为case class会用自带的apply方法自动创建对象，必须使用case class因为class没有序列化
      })

//    val jdbc = "jdbc:mysql://localhost:3307/spark_learn"
//    val connectionProperties = new Properties()
//    connectionProperties.setProperty("user", "root")
//    connectionProperties.setProperty("password", "password")

    df.write
      .format("jdbc")
      .option("url", "jdbc:mysql://localhost:3307/spark_learn")
      .option("user","root")
      .option("password", "password")
      .option("dbtable", "weblog")
      .mode("overwrite")
      .save()

    spark.close()

  }

}
