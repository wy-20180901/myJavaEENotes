[toc]

# 深入Web请求过程

- B/S架构的好处

  - 基于统一的浏览器
  - 基于统一的HTTP

- CDN典型架构图

  ![image-20210605184239123](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202106/05/184240-989946.png)

- 一些不变的原则

  - 互联网上所有的资源都要用一个URL表示
  - 必须基于HTTP与服务端交互
  - 数据展示必须在浏览器中进行

- 发起一个HTTP请求的过程就是建立一个Socket的过程。

- 可以使用`HttpClient`处理HTTP请求。

- Linux下可以使用`curl`。

  ![image-20210605190234142](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202106/05/190237-31475.png)
  - 通过添加`-HI`选项可以增加HTTP头

  ![image-20210605190412104](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202106/05/190413-198028.png)

- 常见的HTTP请求头，响应头，状态码

  [参考文章](https://blog.csdn.net/en_joker/article/details/81280970)：https://blog.csdn.net/en_joker/article/details/81280970

- 使用ctrl + F5可以不使用缓存直接将目标URL发送到服务端。

- `Cache-Control`请求字段被各个浏览器支持的比较好，且优先级比较高，和其他一些字段同时出现时，会覆盖其他字段。

- 一般服务端在响应头中有一个`Last-Modified`字段，告诉浏览器这个页面的最后修改时间。浏览器再次请求增加一个`If-Modified-Since`字段，询问当前缓存页面是不是最新的。如果是最新的就返回304状态码，告诉浏览器是最新的，服务器也不会传输新的数据。

- DNS域名解析过程

  - 检查浏览器缓存
  - 检查操作系统缓存
    - Windows下在C:\Windows\System32\drivers\etc下的hosts文件中
    - Linux下在/etc/hosts文件中
  - 发送给LDNS
  - 发送到Root Server
  - 查询gTLD
  - 找到对应的Name Server
  - 返回对应的IP地址和TTL值

- 可以使用nslookup查询解析结果

  ![image-20210605192433056](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202106/05/192434-369945.png)

- 可以使用dig来查询DNS解析过程

  ![image-20210605192514708](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202106/05/192528-149652.png)
  - 添加`+trace`属性追踪过程

  ![image-20210605192638571](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202106/05/192714-470597.png)

- 请除缓存的域名
  - Windows下：`ipconfig/flushdns`
  - Linux下：`/etc/init.d/nscd restart`

>  注意：在Java应用中JVM也会缓存DNS解析的结果。这个缓存是在InetAddress类中完成的。有两种缓存策略：一种是正确的解析结果缓存，第一种是正确的解析结果，另一种是失败的解析结果。这两个缓存时间可以在%JAVA_HOME%\lib\security\java.security文件中的networkaddress.cache.ttl和networdaddress.cache.negative.ttl，默认分别是-1（永不失效）和10（缓存10秒） 
>

> 如果我们需要InetAddress来解析域名，必须是单例模式，不然会有严重的性能问题。如果每次都创建InetAddress实例，则每次都要进行一次完整的域名解析，非常耗时 
>

- 几种域名解析的方式
  - A记录，代表Address，用来指定对应域名的IP地址。A记录可以将多个域名解析到一个IP地址，但是不能将一个域名解析到多个IP地址。
  - MX记录：代表Mail Exchange。但是如果通过Web请求仍然会解析到A记录。
  - CNAME记录：别名解析。+
  - NS解析：为某个域名指定DNS解析服务器。
  - TXT记录：为某个主机名或域名设置说明。

- CDN是构筑在现有Internet上的一种先进的流量分布网络。其目的是在现有的Internet中增加一层新的网络架构。

  > 有一个比喻： CDN = Mirror + Cache + GSLB 
  >

  - CDN的几个目标
    - 可扩展
    - 安全性
    - 可靠性、响应和执行

- 负载均衡的三种架构

  - 链路负载均衡
    - 通过DNS解析成不同的IP，然后用户根据这个IP进行访问不同的服务器。
    - 优点是用户直接访问目标机，速度比较快
    - 但是因为DNS有缓存，如果某个Web Server挂掉了，就会带来问题。
  - 集群负载均衡
    - 硬件负载均衡
      - 性能好
      - 贵
      - 不易扩展
    - 软件负载均衡
      - 成本低
      - 一次访问往往需要多次代理，会增加延时
  - 操作系统负载均衡
    - 利用操作系统级别的软中断
    - 或者硬件中断
    - 例如可以设置多队列网卡

- CDN动态加速

  - 技术原理是在CDN的DNS解析中通过动态的链路探测来寻找回源最好的一条路径。
  - 示意图如下

  ![image-20210606093719805](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202106/06/093721-926334.png)