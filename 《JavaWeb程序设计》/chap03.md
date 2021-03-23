[toc]

# JavaScript基础

- 一些基础知识

  ```js
  Number()函数可以把字符串转化为数字
  String()函数可以把数字转化成字符串
  ```

- 内置对象

  - window
    - window.alert();
    - window.confirm();
    - window.prompt();
    - window.open();
    - newWindow = window.open("window1.html","new1", "width=300, height=300,top=500,left=500");
    - newWindow.close();
    - 第三个属性可以是toolbar, location, status, menubar, scrollbars, resizeable, width, left, top
    - timer = setTimeout("需要运行的函数","间隔的毫秒数");
    - clearTimeout(timer);
  - document
    - document从属于window
    - document.writeln();
    - document.title
    - document.location
    - document.元素名.子元素名
  - history
    - history();
    - history.forward();
    - window.history.go(n);
  - location