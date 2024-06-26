package interview

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

object Zoominfo {
  case class User(userId: Long, userName: String)
  case class UserActivity(userId: Long, activityTypeId: Int, timestampEpochSec: Long)
  val LoginActivityTypeId = 0
  val LogoutActivityTypeId = 1


  private def readUserData(sparkSession: SparkSession): RDD[User] = {
    sparkSession.sparkContext.parallelize(
      Array(
        User(1, "Doe, John"),
        User(2, "Doe, Jane"),
        User(3, "X, Mr."))
    )
  }

  private def readUserActivityData(sparkSession: SparkSession): RDD[UserActivity] = {
    sparkSession.sparkContext.parallelize(
      Array(
        UserActivity(1, LoginActivityTypeId, 1514764800L),
        UserActivity(2, LoginActivityTypeId, 1514808000L),
        UserActivity(1, LogoutActivityTypeId, 1514829600L),
        UserActivity(1, LoginActivityTypeId, 1514894400L))
    )
  }

  def calculate(sparkSession: SparkSession): Unit = {
    val userRdd: RDD[(Long, User)] =
      readUserData(sparkSession).map(e => (e.userId, e))
    val userActivityRdd: RDD[(Long, UserActivity)] =
      readUserActivityData(sparkSession).map(e => (e.userId, e))

    val result = userRdd
      .leftOuterJoin(userActivityRdd)
      .filter(e => e._2._2.isDefined && e._2._2.get.activityTypeId == LoginActivityTypeId)
      .map(e => (e._2._1.userName, e._2._2.get.timestampEpochSec))
      .reduceByKey((a, b) => if (a < b) a else b)

    result
      .foreach(e => println(s"${e._1}: ${e._2}"))
  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("app")
    val sc = SparkSession.builder().config(conf).getOrCreate()

    calculate(sc)
  }
}
