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

      - 编辑配置文件`/etc/systemd/system/multi-user.target.wants/docker.service`，在环境变量`ExecStart`后面加`-H tcp://0.0.0.0`，允许任意IP的客户端连接

        ![image-20210428192505622](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202104/28/192506-54.png)

      - 重启Docker daemon

        `systemctl daemon-reload`

        `systemctl restart docker.service`

      - 根据服务器端ip进行远程访问

        ![image-20210428192819801](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202104/28/192822-6735.png)

  - Docker 镜像: Image

    - Docker镜像可以看成只读模板，通过它可以创建docker容器。
    - 镜像生成方法
      - 从无到有开始创建镜像
      - 下载并使用别人创建好的镜像
      - 在现有的镜像上创建新的镜像

  - Register

    - 分为公有和私有两种，默认是`Docker Hub`

  - Docker 容器: Container

    - 对于应用软件，镜像是软件生命周期的构建和打包阶段，而容器则是启动和运行阶段。

  ![image-20210428191450268](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202104/28/192827-38951.png)

- 除了docker命令行工具外，用户也可以使用REST API与服务器通信。
- 一些命令
  - `docker images`查看下载到本地的镜像
  - `docker ps`查看正在运行的容器
  - `docker run`启动容器