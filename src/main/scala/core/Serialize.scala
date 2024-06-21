package core

import org.apache.spark.{SparkConf, SparkContext}

object Serialize {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("app")
    val sc = new SparkContext(sparkConf)


    val value = sc.makeRDD(List(1, 2, 3))
    val user = new User
    // 算子外部的数据是在driver，内部的数据在executor
    // rdd算子中传递的函数会包含闭包操作，那就回进行检测
    value.foreach(
      num => println(1.toString + user.name)
    )

    sc.stop()
  }

  class User extends Serializable{
    var name = 13

  }

}
