[toc]

# 微服务注册与发现

- Eureka

  - 架构图

    ![image-20210718180952197](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202107/18/181024-586357.png)

  - Region与Availablity Zone

    ![image-20210718181211077](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202107/18/181812-914910.png)

- 可以添加一个HTTP认证

  ```xml
  <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security -->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
      <version>2.5.2</version>
  </dependency>
  ```

  然后是配置项

  ```yml
  security:
  	basic:
  		# 开启基于HTTP basic的认证
  		enabled: true
  	user:
  		name: user
  		# 配置密码，否则是随机生成的密码，在日志中可以找到
  		password: pass
  ```

- 多环境下的IP选择

  - 忽略指定名称的网卡

    ```yml
    spring:
    	cloud:
    		inetutils:
    			ignored-interfaces:
    				- docker0
    				- veth.*
    eureka:
    	instance:
    		prefer-ip-address: true
    ```

  - 使用正则表达式，指定使用的网络地址

    ```yml
    Spring:
    	cloud:
    		inetutils:
    			preferredNetworks:
    				- 192.168
    				- 10.0
    
    eureka:
    	instance:
    		prefer-ip-address: true
    ```

  - 只使用站点本地地址

    ```yml
    Spring:
    	cloud:
    		inetutils:
    			useOnlySiteLocalInterfaces: true
    
    eureka:
    	instance:
    		perfer-ip-address: true
    ```

  - 手动指定IP

    ```yml
    eureka:
    	instance:
    		prefer-ip-address: true
    		ip-address: 127.0.0.1
    ```

- Eureka Server的REST端点

  Eureka Server提供了一些REST端点，非JVM的微服务可以使用这些REST端点操作Eureka，从而实现注册与发现