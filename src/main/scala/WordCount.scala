import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
object WordCount {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
      .setMaster("local")
      .setAppName("word count");
    val sc = new SparkContext(sparkConf)
    val lines: RDD[String] = sc.textFile(
      "src/main/resources/words.txt"
    )

    lines
      .flatMap(_.split(" "))
      .map(word => (word, 1)) //转换成key value pair之后才能用by key操作
      .reduceByKey((v1, v2) => v1 + v2)
      .sortBy(x => x._2, false)
      .collect()
      .foreach(println)
    sc.stop()

  }

}
