package framework.common

import framework.util.Env
import org.apache.spark.{SparkConf, SparkContext}

trait TApplication {
  def start(op: => Unit) = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("Demo")
    val sc = new SparkContext(conf)
    Env.put(sc)

    try {
      op
    } catch {
      case ex => println(ex.getMessage)
    }

    sc.stop()
    Env.clear()
  }
}
