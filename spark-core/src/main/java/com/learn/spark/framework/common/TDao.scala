package com.learn.spark.framework.common

import com.learn.spark.framework.util.Env

trait TDao {
  def readFile(path: String) = {
    val sc = Env.take()
    sc.textFile(path)
  }
}
