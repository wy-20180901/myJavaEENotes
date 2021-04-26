[toc]

# 一个简单的Web服务器

- TCP协议默认使用TCP 80端口

## Socket类

- 套接字是网络连接的端点。
- 套接字使应用程序可以从网络中读数据，可以向网络中写数据。
- 要发送字节流，需要调用`Socket`类的`getOutputStream()`方法。
- 要发送文本到远程应用程序，通常需要使用`PrintWriter`对象。
- 若想从连接的另一端接受字节流，需要调用`Socket`的`getInputStream()`方法。

```java
import java.io.*;
import java.net.Socket;

public class SockerDemo {
    public static void main(String[] args) {
        try {
            // 建立连接
            Socket socket = new Socket("localhost", 8080);
            OutputStream os = socket.getOutputStream();
            boolean autoFlush = true;
            PrintWriter out = new PrintWriter(socket.getOutputStream(), autoFlush);
            BufferedReader in = new BufferedReader(
                	new InputStreamReader(socket.getInputStream()
				));
            // 发送一个报文
            out.println("GET /Demo4_war_exploded//index.jsp HTTP/1.1");
            out.println("Host: localhost:8080");
            out.println("Connection: Close");
            out.println();
            // 读取响应报文
            boolean loop = true;
            StringBuffer sb = new StringBuffer(8096);
            while (loop) {
                if (in.ready()) {
                    int i = 0;
                    while (i != -1) {
                        i = in.read();
                        sb.append((char) i);
                    }
                    loop = false;
                }
                Thread.currentThread().sleep(50);

            }

            System.out.println(sb.toString());
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```





## ServerSocket

- 连接到远程服务器的应用程序时创建的套接字。
- 创建了`ServerSocket`之后，可以使其等待传入的连接请求。
- 这些工作可以通过调用`ServerSocket`的`accept`方法完成。

```java
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class ServerSocketDemo {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(
                    8080, 1, InetAddress.getByName("localhost")
            );
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```



## 应用程序

