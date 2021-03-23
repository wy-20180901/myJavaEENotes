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

- 