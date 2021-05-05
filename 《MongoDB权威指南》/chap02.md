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

  - 更新，使用`update`

    `update`接受（至少）两个参数，一个是限定条件（用于匹配待更新的文档），第二个是新的文档。

    ![image-20210503231856981](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/03/231923-751421.png)

  - 删除，使用`remove`

    ![image-20210503232002752](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/03/232041-178470.png)

## 基本数据类型

- null
  - `{"X": null}`
- 布尔
  - true
  - false
  - `{"x": true}`, `{"y": flase}`
- 数值
  - 默认使用64位浮点型数值
  - 对于整性，可以使用`NumberInt`, `NumberLong`
  - `{"x": 3.14}`, `{"y": 3}`
- 字符串
  - 使用`UTF-8`
  - `{"x": "kingdeguo"}`
- 日期
  - 自新纪元以来的毫秒数
  - `{"x": new Date()}`
- 正则表达式
  - `{"x": /foobar/i}`
- 数组
  - `{"x": ["a", "b", "c"]}`
- 内嵌文档
  - `{"x":{"foo":"bar"}}`
- 对象`id`
  - `{"x": ObjectId()}`
- 二进制数据
- 代码
  - `{"x":function (){/* */} }`

## 关于日期

- 使用`new Date()`而不是`Date()`，因为后者返回的是日期的字符串表示方式，而不是日期对象。

## _id和ObjectId

- MongoDB中存储的文档必须有一个`_id`的键，这个键的值可以是任何类型的，默认是`ObjectId`对象。

- ObjectId使用12字节的存储空间，是一个由24个十六进制数字组成的字符串。

- 创建方式如下

  ```
  0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 
    时间戳       |  机器      |  PID  |    计数器
  ```

  - 时间戳与后面的5字节提供了秒级别的唯一性
  - 为了确保同一台机器上并发的多个进程产生的ObjectId是唯一的，接下来的两个字节来自产生ObjectId的进程的进程标识符。
  - 前9个字节保证了同一秒不同机器上不同进程产生的ObjectId是唯一的。最后3个字节是一个自动增加的计数器，确保相同进程同一秒产生的ObjectId也是不一样的。一秒钟最多允许进程拥有256^3^个不同的ObjectId。

## 连接MongoDB Shell

- 方式1

  - `mongo some-host:30000/myDD`

- 方式2

  - 在启动mongo shell时不启动任何mongod，通过`--nodb`参数来实现。

  - 启动之后

    ```js
    conn = new Mongo("some-host:30000");
    db = conn.getDB("myDB");
    ```

  - 展示

    ![image-20210505202956606](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/05/202957-622345.png)

## 使用Shell脚本

- 使用`help`可以查看相应帮助

  ![image-20210505203055185](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/05/203055-6675.png)

- 使用`--quilt`可以不显示`MongoDB shell version`

- 使用`mongo script1.js script2.js `可以执行脚本，然后执行完毕后退出。

- 也可以使用`load()函数`

  - `load("script1.js")`

- 其他命令

  - `show dbs`

  - `show collections`

    - 可以在shell中使用`run()`来执行命令程序

      `run("pwd")`

      `run("ls","-l","home/zd/dir/")`



## 修改.mongorc.js文件

- 如果某些脚本会被频繁加载，可以将它们添加到`.mongorc.js`文件中，这个文件会在启动`shell`时自动运行。

  利用这个特性可以创建全局变量等。

- 可以使用`--norc`参数来防止加载该文件。

- 可以通过设置`EDITOR`变量的值来调用编辑器

  ```js
  EDITOR="/usr/bin/vim"
  var name="kingdeguo"
  edit name
  ```

- `version`

  ![image-20210505205248013](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202105/05/205248-468732.png)