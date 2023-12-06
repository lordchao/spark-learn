package com.learn.spark.framework.controller

import com.learn.spark.framework.common.TController
import com.learn.spark.framework.service.WordCountService

class WordCountController extends TController{
  private val wordCountService = new WordCountService()

  override def execute = {
    val words = wordCountService.process
    words.foreach(println)
  }

}
