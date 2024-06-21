package core.server

class SubTask extends Serializable{
  var data: List[Int] = List(1,2,3,4)
  var logic: Int=>Int = _*2

  def compute()= {
    data.map(logic)
  }

}
