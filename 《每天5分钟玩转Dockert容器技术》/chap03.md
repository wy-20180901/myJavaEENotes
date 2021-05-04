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
  - 内核空间是kernal，Linux刚启动的时候会加载bootfs文件系统，之后bootfs会被卸载掉。
  - 对于base镜像来说，底层直接用Host的kernal就可以了，自己只需要提供rootfs就可以了。
  - 所有容器都共用host的kernal，在容器中无法对kernal进行升级，因此如果容器对kernal版本有要求，则不建议使用容器，这种场景虚拟机更合使。



## Docker采用分层的结构

- 最好的好处就是共享资源，采用`copy-on-write`特性

- 可写的容器层

  ![docker image management foundation](https://www.fatalerrors.org/images/blog/692bc9846ec5c0514b4e73c26bd7f319.jpg)

  - 当容器启动时，一个新的可写层被加载到镜像的顶部
    - 这一层被称为容器层，容器层之下是镜像层。
    - 所有对容器的改动，无论是添加、删除、还是修改文件都是只发生在容器层中。

## 构建镜像

- 两种构建镜像的方法

  - docker commit

    - 运行容器

    - 修改容器

    - 将容器保存为新的镜像

      `docker commit old_name new_name`

  - Dockerfile

    - 首先创建`Dockerfile`

      ![image-20210504204203831](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/04/204204-397109.png)

    - 然后执行`docker build`命令

      ```
      docker build -t the_name .
      ```

      ![image-20210504204036191](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/04/204047-539848.png)

    - 可以使用`docker history`显示镜像的构建历史

      ![image-20210504204339621](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/04/204341-542300.png)

    - docker会缓存已有镜像的镜像层，构建新镜像时，如果某镜像层已经存在，就直接使用无需创建。

      如果不希望使用缓存，就使用`--no-cache`参数

- 调试镜像

  可以运行中间的状态码。

  例如：`docker run -it 22d31cc52b3e`



## Dockerfile常用指令

