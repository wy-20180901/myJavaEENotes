[toc]

# 容器核心知识概述

- 容器有两部分组成

  - 应用程序本身
  - 依赖

- 容器和虚拟机的区别

  ![image-20210428190516783](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202104/28/190518-61943.png)

- `Docker`将集装箱的思想运用到软件打包上，为代码提供了一个基于容器的标准化运输系统。

- 容器的优势

  - 对于开发人员：`Build Once, Run Everywhere`
  - 对于运维人员：`Configure Once, Run Anything`

- Docker的核心组件

  - Docker 客户端: Client

  - Docker 服务端: Docker daemon

    - 是服务器组件，以Linux后台服务的方式运行

      `systemctl status docker.service`

      ![image-20210428191741269](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202104/28/191742-625179.png)

    - 如果要允许远程客户端访问，需要在配置文件中打开TCP监听

      - 编辑配置文件
      - 重启Docker daemon
      - 根据服务器端ip进行远程访问

  - Docker 镜像: Image

  - Register

  - Docker 容器: Container

  ![image-20210428191450268](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202104/28/191451-314706.png)

- 除了docker命令行工具外，用户也可以使用REST API与服务器通信。
- 