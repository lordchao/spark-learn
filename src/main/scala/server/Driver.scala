package core.server

import java.io.ObjectOutputStream
import java.net.Socket

object Driver {
  def main(args: Array[String]): Unit = {
    //连接服务器

    val client = new Socket("localhost", 9999)
    val out = client.getOutputStream
//
    val objectOut = new ObjectOutputStream(out)

    objectOut.writeObject(new Task())
    objectOut.flush()
    objectOut.close()
    client.close()
  }
}
