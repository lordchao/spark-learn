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
    val wordsRDD = sc.makeRDD(List("a","a","b"))

    wordsRDD.map(x=>(x,1)).reduceByKey(_ + _).collect()
      .foreach(println)

    val words = lines
      .flatMap(_.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
      .collect()

    println(words.mkString("Array(", ", ", ")"))

    sc.stop();

  }

}
