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

- 