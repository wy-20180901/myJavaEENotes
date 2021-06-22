[toc]

# Servlet

## servlet特点

- Servlet主要负责接收用户请求 `HttpServletRequest` ,在`doGet()` , `doPost()` 中做相应的处理，并将回应`HttpServletResponse` 反馈给用户。
- Servlet 可以设置初始化参数，供Servlet内部使用。
- 一个Servlet类只会有一个实例，在它初始化时调用`init()` 方法，销毁时调用`destroy() `方法。
- Servlet可以设置多个URL访问。Servlet不是线程安全。

## Servlet的优点

- 只需要启动一个操作系统进程以及加载一个JVM，大大降低了系统的开销。
- 如果多个请求需要做同样处理的时候，这时候只需要加载一个类，这也大大降低了开销。
- 所有动态加载的类可以实现对网络协议以及请求解码的共享，大大降低了工作量。
- Servlet能直接和Web服务器交互，而普通的CGI程序不能。
- Servlet还能在各个程序之间共享数据，使数据库连接池之类的功能很容易实现。

## CGI的缺点

- 需要为每个请求启动一个操作CGI程序的系统进程。如果请求频繁，这将会带来很大的开销。
- 需要为每个请求加载和运行一个CGI程序，这将带来很大的开销
- 需要重复编写处理网络协议的代码以及编码，这些工作都是非常耗时的。

## Servlet的5个接口

- `void init(ServletConfig config) throws ServletException`
- `void service(ServletRequest req, ServletResponse resp) throws ServletException,java.io.IOException`
- `void destroy()`
- `java.lang.String getServletInfo()`
- `ServletConfig getServletConfig()`

前三个与Servlet生命周期有关。使用`init()`方法初始化。使用`service()`方法处理请求。使用`destory()`方法销毁。

其中`init()`和`destory()`只会执行一次，`service()`每次请求时都会执行。

## Get和Post

- 底层都是TCP链接。
- Get从服务器获得资源，Post向服务器提交数据。

## Servlet不是线程安全的

- 尽量不要定义`name`属性，而是要把`name`变量分别定义在`doGet()`和`doPost()`方法内。虽然使用`synchronized(name)
  {}`语句块可以解决问题，但是会造成线程的等待，不是很科学的办法。