package core.server

class Task extends Serializable {
  private val data = List(1,2,3,4)
  private val logic: Int=>Int = _ * 2

  def compute() = {
    data.map(logic)
  }

}
