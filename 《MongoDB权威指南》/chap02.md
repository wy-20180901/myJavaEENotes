[toc]

# MongoDB基础知识

## 基本概念

- **文档**是MongoDB中数据的基本单元
- **集合**可以看作是一个拥有动态模式的表
- MongoDB的一个实例可以拥有多个相互独立的**数据库**，每一个数据库都有自己的集合。
- 每一个文档都有一个特殊的键`_id`，这个键在文档所属的集合中是唯一的。



## 文档

- 文档就是键值对的一个有序集。
- 一些规定
  - 键不能用`\0`，这个字符用来表示键的结尾
  - `.`和`$`作为保留符
  - 区分类型，区分大小写
  - 不能有重复的键
  - 文档中键值对是有序的

## 集合

- 集合就是一组文档

- 集合是动态模式的，也就是说一个集合里面的文档可以是各式各样的。

  例如`{"name","kingdeguo"}`和`{"age":18}`可以放到一个集合里面。

- 使用多个集合的原因

  - 便于管理
  - 有助于查询速度
  - 同类型的放在一起可以使数据更加集中
  - 同类型的放在一起可以更有效的创建索引

- 集合的命明

  - 不能是空串
  - 不能包含`\0`
  - 不能以`system.`开头
  - 不能包含`$`

- 子集合

  - 一般以`.`来分隔不同名命空间的子集合



## 数据库

- 多个集合可以组成数据库
- 每个数据库有0个或多个集合
- 命名上，基本只能是ACSII字符，不超过64字节（最终数据库会变成系统里面的文件，数据库名就是相应的文件名。
- 保留的数据库有`admin`，`local`，`config`



## MongoDB Shell

- MongoDB shell是一个功能完备的`js`解释器。

  ![image-20210503230502465](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/03/230918-681150.png)

- 使用`db`命令查看当前指向哪个数据库

  ![image-20210503230450344](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/03/230915-434900.png)

- 通过`db`变量可以访问其中的集合

  例如`db.today`可以访问数据库`today`中的集合

- Shell中的四个基本操作

  - 创建，使用`insert`

    ![image-20210503230903817](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/03/230911-931921.png)

  - 读取，使用`find`或者`findOne`

    ![image-20210503231113770](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/03/231115-667396.png)

  - 更新

  - 删除