[toc]

# 中文乱码问题

- 将编码改为gb2312

  ```java
  str = new String(str.getBytes("ISO-8859-1"),"gb2312");
  ```

- 修改request的编码

  ```java
  request.setCharacterEncoding("gb2312");
  ```

- 使用过滤器

  