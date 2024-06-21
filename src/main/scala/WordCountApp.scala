
import framework.common.TApplication
import framework.controller.WordCountController

object WordCountApp extends TApplication{

  val controller = new WordCountController()


  def main(args: Array[String]): Unit = {
    start{
      controller.execute
    }
  }
}
