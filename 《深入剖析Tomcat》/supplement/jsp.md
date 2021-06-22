[toc]

# JSP

## 特点

- Servlet是一个特殊的Java程序，它运行于服务器的JVM中，能够依靠服务器的支持向浏览器提供显示内容。
- SP本质上是Servlet的一种简易形式，JSP会被服务器处理成一个类似于Servlet的Java程序，可以简化页面内容的生成。
- Servlet的应用逻辑是在Java文件中，并且完全从表示层中的HTML分离开来。而JSP的情况是Java和HTML可以组合
  成一个扩展名为.jsp的文件。
- JSP侧重于视图，Servlet更侧重于控制逻辑，在MVC架构模式中，JSP适合充当视图（view）而Servlet适合充当控制器（controller）。

- JSP是一种Servlet，但是与HttpServlet的工作方式不太一样。HttpServlet是先由源代码编译为class文件后部署到服务器下，为先编译后部署。而JSP则是先部署后编译。JSP会在客户端第一次请求JSP文件时被编译为HttpJspPage类（接口Servlet的一个子类）。该类会被服务器临时存放在服务器工作目录里面。
- 开发Web程序时经常需要修改JSP。Tomcat能够自动检测到JSP程序的改动。如果检测到JSP源代码发生了改动。Tomcat会在下次客户端请求JSP时重新编译JSP，而不需要重启Tomcat。这种自动检测功能是默认开启的，检测改动会消耗少量的时间，在部署Web应用的时候可以在web.xml中将它关掉。

## 内置对象

- request：封装客户端的请求，其中包含来自GET或POST请求的参数；
- response：封装服务器对客户端的响应；
- pageContext：通过该对象可以获取其他对象；
- session：封装用户会话的对象；
- application：封装服务器运行环境的对象；
- out：输出服务器响应的输出流对象；
- config：Web应用的配置对象；
- page：JSP页面本身（相当于Java程序中的this）；
- exception：封装页面抛出异常的对象

## Request对象的主要方法有哪些

- setAttribute(String name,Object)：设置名字为name的request 的参数值
- getAttribute(String name)：返回由name指定的属性值
- getAttributeNames()：返回request 对象所有属性的名字集合，结果是一个枚举的实例
- getCookies()：返回客户端的所有 Cookie 对象，结果是一个Cookie 数组
- getCharacterEncoding() ：返回请求中的字符编码方式 = getContentLength() ：返回请求的 Body
  的长度
- getHeader(String name) ：获得HTTP协议定义的文件头信息
- getHeaders(String name) ：返回指定名字的request Header 的所有值，结果是一个枚举的实例
- getHeaderNames() ：返回所以request Header 的名字，结果是一个枚举的实例
- getInputStream() ：返回请求的输入流，用于获得请求中的数据
- getMethod() ：获得客户端向服务器端传送数据的方法
- getParameter(String name) ：获得客户端传送给服务器端的有 name指定的参数值
- getParameterNames() ：获得客户端传送给服务器端的所有参数的名字，结果是一个枚举的实例
- getParameterValues(String name)：获得有name指定的参数的所有值
- getProtocol()：获取客户端向服务器端传送数据所依据的协议名称
- getQueryString() ：获得查询字符串
- getRequestURI() ：获取发出请求字符串的客户端地址
- getRemoteAddr()：获取客户端的 IP 地址
- getRemoteHost() ：获取客户端的名字
- getSession([Boolean create]) ：返回和请求相关 Session
- getServerName() ：获取服务器的名字
- getServletPath()：获取客户端所请求的脚本文件的路径
- getServerPort()：获取服务器的端口号
- removeAttribute(String name)：删除请求中的一个属性

## request.getAttribute()和 request.getParameter()有何区别
从获取方向来看：
	getParameter() 是获取 POST/GET 传递的参数值；
	getAttribute() 是获取对象容器中的数据值；
从用途来看：
	getParameter() 用于客户端重定向时，即点击了链接或提交按扭时传值用，即用于在用表单或url重定向传值时接收数据用。
	getAttribute() 用于服务器端重定向时，即在 sevlet 中使用了 forward 函数,或 struts 中使用了mapping.findForward。 getAttribute 只能收到程序用 setAttribute 传过来的值。

另外，可以用 setAttribute() , getAttribute() 发送接收对象.而 getParameter() 显然只能传字符串。setAttribute() 是应用服务器把这个对象放在该页面所对应的一块内存中去，当你的页面服务器重定向到另一个页面时，应用服务器会把这块内存拷贝另一个页面所对应的内存中。这样getAttribute()就能取得你所设下的值，当然这种方法可以传对象。session也一样，只是对象在内存中的生命周期不一样而已。getParameter() 只是应用服务器在分析你送上来的 request页面的文本时，取得你设在表单或 url 重定向时的值。

总结：
getParameter() 返回的是String,用于读取提交的表单中的值;（获取之后会根据实际需要转换为自己需要的相应类型，比如整型，日期类型啊等等。getAttribute() 返回的是Object，需进行转换,可用setAttribute() 设置成任意对象，使用很灵活，可随时用

## include指令include的行为的区别
**include指令**： JSP可以通过include指令来包含其他文件。被包含的文件可以是JSP文件、HTML文件或文本文件。包含的文件就好像是该JSP文件的一部分，会被同时编译执行。 语法格式如下：`<%@ include file="文件相对 url 地址" %>`
**include动作**： <jsp:include> 动作元素用来包含静态和动态的文件。该动作把指定文件插入正在生成的页面。语法格式如下：`<jsp:include page="相对 URL 地址" flush="true" />`

## 四种作用域

- page代表与一个页面相关的对象和属性。
- request代表与Web客户机发出的一个请求相关的对象和属性。一个请求可能跨越多个页面，涉及多个Web组件；需要在页面显示的临时数据可以置于此作用域。
- session代表与某个用户与服务器建立的一次会话相关的对象和属性。跟某个用户相关的数据应该放在用户自己的session中。
- application代表与整个Web应用程序相关的对象和属性，它实质上是跨越整个Web应用程序，包括多个页面、请求和会话的一个全局作用域。

