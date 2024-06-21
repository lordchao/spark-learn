package framework.common

import framework.util.Env

trait TDao {
  def readFile(path: String) = {
    val sc = Env.take()
    sc.textFile(path)
  }
}
