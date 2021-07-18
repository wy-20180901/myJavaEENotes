[toc]

# 开发第一个应用程序

## 知识概要

- 启动类，这样可以把应用程序当作一个jar文件来运行 `java -jar xxx.jar`。

  ```java
  @SpringBootApplication
  public class DemoApplication {
      public static void main(String[] args) {
          SpringApplication.run(DemoApplication.class, args);
      }
  }
  ```

  - 其中`@SpringBootApplication`相当于以下三个注解
    - `@Configuration`：使用Spring基于Java的配置
    - `@ComponentScan`：启用组件扫描
    - `@EnableAutoConfiguration`：开启Spring Boot的自动配置。

- 测试Spring Boot应用程序

  ```java
  // 加载上下文
  @SpringBootTest
  // 添加 junit 支持
  @RunWith(SpringJUnit4ClassRunner.class)
  @WebAppConfiguration
  class DemoApplicationTests {
      
      @Test  // 测试加载的上下文
      void contextLoads() {
      }
  }
  ```

- 可以在`application.properties`中设置服务端口`server.port=8000`

- 如果想要打包成`jar`文件的行式

  ```xml
  <build>
      <plugins>
          <plugin>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-maven-plugin</artifactId>
              <version>2.5.2</version>
          </plugin>
      </plugins>
  </build>
  ```

- 使用`mvn dependency:tree`可以获取依赖树

- 可以在`<dependency>`中使用`<exculsions>`来排除传递依赖。

  ```xml
  <project>
    ...
    <dependencies>
      <dependency>
        <groupId>sample.ProjectB</groupId>
        <artifactId>Project-B</artifactId>
        <version>1.0</version>
        <scope>compile</scope>
        <exclusions>
          <exclusion>  <!-- declare the exclusion here -->
            <groupId>sample.ProjectC</groupId>
            <artifactId>Project-C</artifactId>
          </exclusion>
        </exclusions> 
      </dependency>
    </dependencies>
  </project>
  ```

- maven总是会使用最近的依赖，可以直接在`dependency`中写入新的依赖，则会覆盖掉原来的依赖。

## 分析

- 在向应用程序加入Spring Boot时，有个名为Spring-boot-autoconfigure的jar文件，其中包含了很多配置类。
- 用到了Spring4的条件配置。
- 可以实现`Condition`接口来实现字定义配置
- 可以使用`@Conditional(xxxx.class)`等注解来声明条件注解。
