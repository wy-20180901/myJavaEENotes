[TOC]

# 微服务构建：Spring Boot

- 使用 MockMvc进行测试

  ```java
  @SpringBootApplication
  // 加入对 Junit4 的支持
  @RunWith(SpringJUnit4ClassRunner.class)
  // 开启Web应用的配置， 用于模拟ServletContext
  @WebAppConfiguration
  public class HelloTest {
      // 用于模拟调用Controller的接口发起请求
      private MockMvc mvc;
      
      // 在使用用例Test之前预加载的内容
      @Before
      public void setUp(){
          mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
      }
      
      @Test
      public void hello() throws Exception {
          // perform执行一次请求调用
          mvc.perform(MockMvcRequestBuilders
                      // accept 定义接收数据类型
                      .get("/")).accept(MediaType.APPLICATION_JSON)
              		.andExcept(status().isOk())
              		.andExcept(content().string(equalTo("hello world"));
      }
  }
  ```

- 配置部分

  - 可以使用`spring.application.name=hello`来指定应用名。该名字会被注册为Spring Cloud中的服务名。

  - YAML目前还不能通过`@PropertySource`注解加载配置。

  - YAML加载是有序的，所以当配置文件中的信息需要具备顺序含义时，比`properties`更有优势。

  - 可以在配置文件中自定义参数，然后使用注解加载

    ```
    author.name=kingdeguo
    ```

    ```java
    @Value("${book.name}")
    ```

  - 加载随机数

    ```
    com.kingdeguo.blog.value=$(random.value)
    # 还有其他用法
    # ${random.int}
    # ${random.long}
    # ${random.int(10)}
    # ${random.int[10,20]}
    ```

- 监控与管理

  引入依赖

  ```xml
  <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator -->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
      <version>2.5.2</version>
  </dependency>
  ```

  该模块已经实现了一些原生端点，根据作用，大概可分三类

  - 应用配置类
    - 获取加载的应用配置、环境变量、自动化配置等
    - 查看
      - `/autoconfig`
      - `/beans`
      - `/configprops`
      - `/env`
      - `/mappings`
      - `/info`
      - `/metrics`
      - `/health`
      - `/dump`
      - `/trace`
  - 度量指标类
    - 内存信息、线程池信息、HTTP请求等
  - 操作控制类
    - 对应用关闭等操作