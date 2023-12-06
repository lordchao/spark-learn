package com.learn.spark.framework.service

import com.learn.spark.framework.common.TService
import com.learn.spark.framework.dao.WordCountDao

class WordCountService extends TService{

  private val wordCountDao = new WordCountDao()

  override def process = {

    val lines = wordCountDao.readFile("/Users/c-henry.liu/Documents/hulu/spark-learn/spark-core/src/main/resources/words.txt")

    val words = lines
      .flatMap(_.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
      .collect()

    words
  }
}
