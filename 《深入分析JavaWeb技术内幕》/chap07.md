[toc]

# JVM体系结构与工作方式

- 以计算为中心，计算机体系结构可分为如下几个部分

  - 指令集
  - 计算单元
  - 寻址方式
  - 寄存器定义
  - 存储单元

- 主流的指令集有RISC（精简指令集）和CISC（复杂指令集）

- 指令集和可以直接被机器识别的机器码，也就是它必须以二进制格式存储在计算机中。

- JVM的特点

  - 一个抽象规范

    约束了JVM到底是什么（The Java Virtual Machine Specification）

  - 一个具体实现

    不同厂商按照规范结合软硬件在不同平台上的实现

  - 一个运行中的实例

    每个运行中的Java程序都是一个JVM实例

- JVM体系结构详解

  ![image-20210606095339107](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202106/06/095341-719752.png)

  - 类加载引擎
    - 在JVM启动时后者类运行时将需要的class加载到JVM中。
  - 执行引擎
    - 负责执行class文件中的字节码指令。
  - 内存区
    - 模拟实际计算机中的存储。
  - 本地方法调用
    - 调用C或C++实现的本地方法的代码返回结果