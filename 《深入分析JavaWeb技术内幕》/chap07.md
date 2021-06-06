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
    - 每个被JVM装载的类型都有一个对应的`java.lang.Class`类的实例来表示该类型，该实例可以唯一表示被JVM装在的class类。这个实例和其他类的实例一样，是存放在堆中的。
  - 执行引擎
    - 负责执行class文件中的字节码指令。
    - 在《Java虚拟机规范》中详细定义了执行引擎遇到每条字节码指令时应该处理什么以及得到什么结果，但并没有规定才去何种行式以及如何实现。例如`Hotspot`是基于栈的执行引擎，`Dalvik`是基于寄存器的执行引擎。
    - 每个Java线程就是一个执行引擎的实例。
  - 内存区
    - 模拟实际计算机中的存储。
    - 方法区和Java堆是所有线程共享的。
    - 每个新的执行引擎实例被创建时会为这个执行引擎创建一个Java栈和一个PC寄存器。
  - 本地方法调用
    - 调用C或C++实现的本地方法的代码返回结果。

> 不管是何种指令集都只有几种基本的元素，如加、减、乘、除、取余等，这些运算又可以进一步分解为二进制位运算：与、或、异或等。这些指令又通过指令来完成，而指令的核心目的就是确定运算的种类和运算需要的数据，以及从哪里区操作数，将结果存放在哪里。相应的指令集会有对应的架构出现，如基于栈的或者基于寄存器的。
>

- Java为何选择基于栈的架构
  - 与平台无关
  - 指令紧凑
- 每当调用一个新方法时，会在这个栈上面创建一个新的栈帧数据结构，这个栈帧会保留这个方法的一些元信息，如在这个方法中定义的局部变量、一些用来支持常量池的解析、正常方法返回及异常处理机制等。

![img](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202106/06/101342-26042.jpeg)

- 执行引擎的执行过程

  ```java
  package aaatoday.demo;
  
  public class Demo {
      public static void main(String[] args) {
          int a = 3;
          int b = 2;
          int c = (a + b) * 10;
      }
  }
  ```

  使用`javap -verbose`命令之后

  ```java
  Classfile /D:/Myhub/Myhub_java/MyTest/out/production/MyTest/aaatoday/demo/Demo.class
    Last modified 2021-6-6; size 452 bytes
    MD5 checksum bdf94bec10d8ee4b30226e8c058327c8
    Compiled from "Demo.java"
  public class aaatoday.demo.Demo
    minor version: 0
    major version: 55
    flags: ACC_PUBLIC, ACC_SUPER
  Constant pool:
     #1 = Methodref          #3.#21         // java/lang/Object."<init>":()V
     #2 = Class              #22            // aaatoday/demo/Demo
     #3 = Class              #23            // java/lang/Object
     #4 = Utf8               <init>
     #5 = Utf8               ()V
     #6 = Utf8               Code
     #7 = Utf8               LineNumberTable
     #8 = Utf8               LocalVariableTable
     #9 = Utf8               this
    #10 = Utf8               Laaatoday/demo/Demo;
    #11 = Utf8               main
    #12 = Utf8               ([Ljava/lang/String;)V
    #13 = Utf8               args
    #14 = Utf8               [Ljava/lang/String;
    #15 = Utf8               a
    #16 = Utf8               I
    #17 = Utf8               b
    #18 = Utf8               c
    #19 = Utf8               SourceFile
    #20 = Utf8               Demo.java
    #21 = NameAndType        #4:#5          // "<init>":()V
    #22 = Utf8               aaatoday/demo/Demo
    #23 = Utf8               java/lang/Object
  {
    public aaatoday.demo.Demo();
      descriptor: ()V
      flags: ACC_PUBLIC
      Code:
        stack=1, locals=1, args_size=1
           0: aload_0
           1: invokespecial #1                  // Method java/lang/Object."<init>":()V
           4: return
        LineNumberTable:
          line 3: 0
        LocalVariableTable:
          Start  Length  Slot  Name   Signature
              0       5     0  this   Laaatoday/demo/Demo;
  
    public static void main(java.lang.String[]);
      descriptor: ([Ljava/lang/String;)V
      flags: ACC_PUBLIC, ACC_STATIC
      Code:
        stack=2, locals=4, args_size=1
           0: iconst_3
           1: istore_1
           2: iconst_2
           3: istore_2
           4: iload_1
           5: iload_2
           6: iadd
           7: bipush        10
           9: imul
          10: istore_3
          11: return
        LineNumberTable:
          line 5: 0
          line 6: 2
          line 7: 4
          line 8: 11
        LocalVariableTable:
          Start  Length  Slot  Name   Signature
              0      12     0  args   [Ljava/lang/String;
              2      10     1     a   I
              4       8     2     b   I
             11       1     3     c   I
  }
  SourceFile: "Demo.java"
  
  Process finished with exit code 0
  ```

  执行内存图

  ![image-20210606102407421](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202106/06/102409-35461.png)

- 在执行方法调用时，会先将参数存储在对应方法的局部变量区中，然后将参数压入栈中。
- 当执行`invokestatic`指令时又创建了一个新的栈帧。
- 执行`return`时，栈帧会撤销。