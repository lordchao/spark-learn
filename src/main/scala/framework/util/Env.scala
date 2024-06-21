package framework.util

import org.apache.spark.SparkContext

object Env {

  //thread local 只能共享数据不能解决线程冲突
  private val scLocal = new ThreadLocal[SparkContext]()

  def put(sc: SparkContext) = {
    scLocal.set(sc)
  }

  def take(): SparkContext = {
    scLocal.get()
  }

  def clear() = {
    scLocal.remove()
  }
}
