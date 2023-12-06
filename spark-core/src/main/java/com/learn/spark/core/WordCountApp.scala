package com.learn.spark.core

import com.learn.spark.framework.common.TApplication
import com.learn.spark.framework.controller.WordCountController

object WordCountApp extends TApplication{

  val controller = new WordCountController()


  def main(args: Array[String]): Unit = {
    start{
      controller.execute
    }
  }
}
