[toc]

# Docker的使用与管理

- Docker镜像的主要内容包含两个部分

  - 镜像层文件内容
  - 镜像json文件

- 本地镜像都保存在了`/var/lib/docker/`目录下

  ![image-20210505103025187](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/05/103026-33298.png)

- `Docker Hub`上显示的是压缩后的体积。`docker images`显示的是解压后的体积。

- `docker imgaes`列表中的镜像体积总和并非是所有镜像的实际硬盘消耗。由于Docker镜像是多层存储结构，并且可以继承、复用，因此不同的镜像可能会因为使用相同的基础镜像，从而拥有共同的层。由于Docker使用Union FS，相同的层只需要保存一份即可。因此实际硬盘占用空间很可能要比这个列表镜像大小的总和小很多。

- 标签为`<none>`的镜像被称为虚无镜像，可以使用`docker images -f dangling=true`来显示这类镜像

  一般而言，虚无镜像是没有价值的，可以使用`docker rmi $(docker images -q -f dangling=true)`

- 如果希望显示中间镜像，可以使用`docker images -a`

- 使用`docker images --format "{{.ID}}:{{.Repository}}"`可以只显示ID和仓库名

  ![image-20210505104135977](C:\Users\asus\AppData\Roaming\Typora\typora-user-images\image-20210505104135977.png)

  还可以自定义格式

  `docker images --format "table {{.ID}}\t{{.Repository}}\t{{.Tag}}"`

  ![image-20210505104431727](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/05/104433-674238.png)

- `docker search`的查找顺序：先查找本地，然后查找本地仓库，然后查找Docker Hub

- 使用`docker rmi`可以删除镜像

  例如`docker rmi 短ID|长ID|仓库名|标签名`等

  还可以结合`docker images -q`使用，例如，想要删除所有仓库名为`redis`的镜像

  `docker rmi $(docker images -q redis)`

