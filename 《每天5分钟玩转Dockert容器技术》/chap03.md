[toc]

# Docker镜像

## 最小的镜像

- `docker pull hell-world`

  ![image-20210504104255614](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/04/104256-529683.png)

  ​	可以用这个来验证docker是否安装成功。

  - 对应的dockerfile如下

    ```docker
    from scratch    # 从0开始构建
    copy hello/     # 将文件"hello"复制到镜像的根目录
    cmd["/hello"]   # 容器启动时，执行/hello
    ```


## base镜像

- 含义

  - 不依赖其他镜像
  - 其他镜像可以以此为基础进行扩展

- 关于rootfs相关

  ![每天5分钟玩转Docker容器技术（三） - 知乎](https://pic2.zhimg.com/80/v2-e7bf9fbb488309f38864cac909a022a5_1440w.jpg)
  - 