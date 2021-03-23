[toc]

# JSP基本语法

- 一个代码示例

  ```html
  <%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <html>
  <head>
      <title>Title</title>
  </head>
  <body>
      <%
          out.print("Hello World");
      %>
  </body>
  </html>
  ```

  在客户端显示查看源代码显示如下

  ![image-20210323224315087](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202103/23/224316-420145.png)

- 注释的方式

  - JSP注释`<%-- --%>`
  - Java注释`//` 或`/**/`
  - HTML注释`<!-- -->`

- JSP表达式

  ```java
  <%
      String name = "kingdeguo";
  %>
  <%=name%>
  ```

- 不能在JSP代码中定义函数

- JSP声明只能作定义，不能实现控制逻辑

- URL传值

  - 传输的只能是字符串
  - 内容可以在地址栏被看到

  ```java
  // 获取参数
  String str = request.getParameter("m");
  // 将字符串转化为数字
  int number = Integer.parseInt(str);
  ```

- JSP指令

  - page
  - include 
  - taglib

  ```java
  // 导入包
  <%@ page import="packageName.className" %>
  // 设置字符集
  <%@ page pageEncoding="编码类型" %>
  // 设置错误界面
  <%@ page isErrorPage="true" %>
  <%@ page errorPage = "error.jsp" %>
  // 设定MIME类型和字符编码
  <%@ page contentType="MIME类型;charset=字符编码" %>
  // 插入外部文件
  <%@ include file="demo.jsp" %>
  ```

- JSP动作

  - 使用XML语法格式的标记来控制服务器的行为
  - include动作还会自动检测被包含文件的变化

  ```java
  jsp:include 当前页面被请求的时候引入一个文件
  jsp:forword 将请求转到另外一个页面
  jsp:useBean 获得JavaBean实例
  jsp:setProperty 设置JavaBean属性
  jsp:getProperty 获得JavaBean属性
  jsp:plugin 根据浏览器的类型为Java插件生成OBJECT和EMBED两种标记。
  ```

  

