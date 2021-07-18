[toc]

# 服务治理：Spring Cloud Eureka

- Eureka服务端：注册中心
- Eureka客户端：主要处理服务的注册与发现

## 示例

- 服务端

  - 首先添加依赖

  ```xml
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-eureka-server</artifactId>
      <version>1.4.7.RELEASE</version>
  </dependency>
  
  <dependencyManagement>
      <dependencies>
          <!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-dependencies -->
          <dependency>
              <groupId>org.springframework.cloud</groupId>
              <artifactId>spring-cloud-dependencies</artifactId>
              <version>Hoxton.SR12</version>
              <type>pom</type>
              <scope>import</scope>
          </dependency>
      </dependencies>
  </dependencyManagement>
  ```

  - 然后再主类上添加`@EnableEurekaServer`注解

  ```java
  @EnableEurekaServer
  @SpringBootApplication
  public class DemoApplication {
      public static void main(String[] args) {
          SpringApplication.run(DemoApplication.class, args);
      }
  }
  ```

  - 默认情况下，该服务注册中心也会讲自己作为客户端来尝试注册，可以在配置文件中关闭

  ```yml
  eureka:
    instance:
      hostname: localhost
    client:
    	# 不向注册中心注册自己
      register-with-eureka: false
      # 注册中心就是维护服务实例，不需要去检索服务，所以为 false
      fetch-registry: false
      service-url:
        defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
  ```

- 客户端

  - 加入依赖

  ```xml
  <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>1.3.7.RELEASE</version>
      <relativePath/> <!-- lookup parent from repository -->
  </parent>
  ```

  - 改造请求

  - 主类中添加注解：`@EnableDiscoveryClient`
  - 修改配置文件

  ```yml
  spring:
  	application:
  		name: hello-world
  
  server:
  	port: 8001
  
  euraka:
  	client:
  		service-url:
  			# 这里的地址是之前注册中心的地址
        		defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
  ```

## 改造为高可用注册中心

- 其实就是将自己作为服务向其他注册中心注册自己，这样久可以行成一组相互注册的服务注册中心，以实现服务清单的互相同步。

- peer1 指向 peer2

  ```yml
  spring:
  	application:
  		name: eureka-peer1
  
  eureka:
    instance:
      hostname: localhost
    client:
      service-url:
        defaultZone: http://peer2/eureka/
  ```

- peer2指向peer1

  ```yml
  spring:
  	application:
  		name: eureka-peer2
  
  eureka:
    instance:
      hostname: localhost
    client:
      service-url:
        defaultZone: http://peer1/eureka/
  ```

- 在host文件中添加如何映射

  ```
  127.0.0.1 peer1
  127.0.0.1 peer2
  ```

- 分别启动 peer1 和 peer2

- 服务提供方如果想要注册到集群，可以按照下方格式写

  ```yml
  spring:
  	application:
  		name: hello-service
  
  eureka:
    client:
      service-url:
        defaultZone: http://peer1/eureka/,http://peer1/eureka/
  ```

## Eureka详解

- 基础架构
  - 服务注册中心
    - 失效剔除
    - 自我保护
  - 服务提供者
    - 服务注册
    - 服务同步
    - 服务续约
  - 服务消费者
    - 获取服务
    - 服务调用
    - 服务下线