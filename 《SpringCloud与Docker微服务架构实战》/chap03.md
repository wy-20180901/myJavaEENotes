[toc]

# 开始使用Spring Cloud实战微服务

- 可以使用下面命令将maven项目转化为gradle项目

  ```shell
  gradle init --type maven
  ```

- 配置文件部分

  ```yml
  server:
    port: 8000
  spring:
    application:
      name: microservice-provider-user
    jpa:
      generate-ddl: false
      show-sql: true
      hibernate:
        ddl-auto: none
    datasource:                           # 指定数据源
      platform: h2                        # 指定数据源类型
      schema: classpath:schema.sql        # 指定h2数据库的建表脚本
      data: classpath:data.sql            # 指定h2数据库的数据脚本
  logging:                                # 配置日志级别，让hibernate打印出执行的SQL
    level:
      root: INFO
      org.hibernate: INFO
      org.hibernate.type.descriptor.sql.BasicBinder: TRACE
      org.hibernate.type.descriptor.sql.BasicExtractor: TRACE
  eureka:
    client:
      serviceUrl:
        defaultZone: http://localhost:8761/eureka/
    instance:
      prefer-ip-address: true
  ```

- `@Bean`是一个方法注解，作用是实例化一个Bean并使用该方法的名称命名。

  ```java
  @Bean
  public RestTemplate restTemplate(){
  	return new RestTemplate;
  }
  ```

  等价于

  ```
  RestTemplate resttemplate = new RestTemplate();
  ```

- 