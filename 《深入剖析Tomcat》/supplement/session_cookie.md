[toc]

## 会话跟踪技术

## cookie

- 优点
  - 持久保存
  - 不需要服务器
  - 简单，基于key-value
- 缺点
  - 大小受限制
  - 用户可以禁用cookie
  - 保存在本地，有安全风险

## URL重写

- 在URL中添加用户会话的信息作为请求的参数，或者将唯一的会话ID添加到URL结尾以标识一个会话。
- 优点：在cookie被禁用的时候依然可以使用。
- 缺点：必须对网站的URL重新编码，所有页面都必须动态生成，不能用预先记录下来的URL进行访问。

## 隐藏表单

- 优点：在Cookie被禁用时可以使用。
- 缺点：所有页面必须是表单提交之后的结果。

## session

- HttpSession放在服务器的内存中，因此不要将过大的对象放在里面。
- 添加到HttpSession中的值可以是任意Java对象，这个对象最好实现了Serializable接口，这样Servlet容器在必要的时候可以将其序列化到文件中，否则在序列化时就会出现异常。



## cookie和session的对比

- Cookie 一般用来保存用户信息 
  - 我们在 Cookie 中保存已经登录过得用户信息，下次访问网站的时候页面可以自动帮你登录的一些基本信息给填了
  - 一般的网站都会有保持登录也就是说下次你再访问网站的时候就不需要重新登录了，这是因为用户登录的时候我们可以存放了一个 Token 在 Cookie中，下次登录的时候只需要根据 Token 值来查找用户即可(为了安全考虑，重新登录一般要将 Token 写)；
  - 登录一次网站后访问网站其他页面不需要重新登录。Session 的主要作用就是通过服务端记录用户的状态。 典型的场景是购物车，当你要添加商品到购物车的时候，系统不知道是哪个用户操作的，因为 HTTP 协议是无状态的。服务端给特定的用户创建特定的 Session 之后就可以标识这个用户并且跟踪这个用户了。
- Cookie 数据保存在客户端(浏览器端)，Session 数据保存在服务器端。
- Cookie 存储在客户端中，而Session存储在服务器上，相对来说 Session 安全性更高。如果使用 Cookie的一些敏感信息不要写入Cookie 中，最好能将 Cookie 信息加密然后使用到的时候再去服务器端解密。