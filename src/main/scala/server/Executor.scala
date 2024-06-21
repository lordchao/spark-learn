package core.server

import java.io.{ObjectInput, ObjectInputStream}
import java.net.ServerSocket

object Executor {
  def main(args: Array[String]): Unit = {
    //启动服务器接受数据
    val serverSocket = new ServerSocket(9999)

    //等待链接
    val client = serverSocket.accept()
    val in = client.getInputStream
    val ObjIn = new ObjectInputStream(in)

    val task = ObjIn.readObject().asInstanceOf[Task]

    val nums = task.compute()

//    val value = in.read()

    println("接收到客户端发送的数据"+nums)

    in.close()
    client.close()

  }
}
