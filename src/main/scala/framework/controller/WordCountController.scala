package framework.controller

import framework.common.TController
import framework.service.WordCountService

class WordCountController extends TController{
  private val wordCountService = new WordCountService()

  override def execute = {
    val words = wordCountService.process
    words.foreach(println)
  }

}
