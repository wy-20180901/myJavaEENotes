[toc]

# 自定义配置

- 两种影响自定义配置的方式
  - 显式配置 进行覆盖
  - 属性配置 进行精细化

## 使用spring security

- 添加maven依赖

  ```xml
  <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security -->
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-security</artifactId>
          <version>2.5.2</version>
      </dependency>
  ```

- 启动程序之后会添加登录选项

  ![image-20210718093307794](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202107/18/093308-845588.png)
  - 此处是用户名是 user

    密码在日志中

    ![image-20210718093358323](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202107/18/093359-430545.png)

- 这种方式并不是很友好，我们可以覆盖自动配置

  ```java
  @Configuration
  // 扩展WebSecurityConfigurerAdapter接口
  public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
    @Autowired
    private ReaderRepository readerRepository;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
        .authorizeRequests()
          // 指明只有经过身份认证并且拥有READER角色的用户才能访问
          .antMatchers("/").access("hasRole('READER')")
          // 其他请求路径开放给所有用户
          .antMatchers("/**").permitAll()
        .and()
        .formLogin()
          .loginPage("/login")
          .failureUrl("/login?error=true");
    }
    
    @Override
    protected void configure(
                AuthenticationManagerBuilder auth) throws Exception {
      auth
        .userDetailsService(new UserDetailsService() {
          @Override
          public UserDetails loadUserByUsername(String username)
              throws UsernameNotFoundException {
              // 用于查找指定用户名的用户
            UserDetails userDetails = readerRepository.findOne(username);
            if (userDetails != null) {
              return userDetails;
            }
            throw new UsernameNotFoundException("User '" + username + "' not found.");
          }
        });
    }
  
  }
  ```

- 在定义JPS实体时，通过覆盖方法设置部分信息

  ```java
  @Entity
  public class Reader implements UserDetails {
  
    private static final long serialVersionUID = 1L;
  
    @Id
    private String username;
    
    private String fullname;
    private String password;
    
    public String getUsername() {
      return username;
    }
    
    public void setUsername(String username) {
      this.username = username;
    }
    
    public String getFullname() {
      return fullname;
    }
    
    public void setFullname(String fullname) {
      this.fullname = fullname;
    }
    
    public String getPassword() {
      return password;
    }
    
    public void setPassword(String password) {
      this.password = password;
    }
  
    // 授予READER权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return Arrays.asList(new SimpleGrantedAuthority("ROLE_READER"));
    }
  
    // 不过期
    @Override
    public boolean isAccountNonExpired() {
      return true;
    }
  
    // 不加锁
    @Override
    public boolean isAccountNonLocked() {
      return true;
    }
  
    // 不禁用
    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }
  
    @Override
    public boolean isEnabled() {
      return true;
    }
  
  }
  ```

## 在命令行中指定参数

- 禁用banner `java -jar xxx.jar --spring.main.show-banner=false`

## 使用环境变量

- `export spring_main_show_banner=false`

## 通过属性文件外置配置

- 创建`application.properties`文件

  ```properties`
  spring.main.show-banner=false
  ```

- 创建`application.yml`

  ```yaml
  spring:
  	main:
  		show-banner: false
  ```

## Spring Boot能读取属性的地方

- 命令行参数
- `java:comp/env`里的`JNDI`属性
- JVM系统属性
- 操作系统环境变量
- 随机生成的带`random.*`前缀的属性，例如`${random.long}$`
- 应用程序以外的`application.properties`或者`application.yml`
- 通过`@PropertySource`标注的属性源
- 默认属性

## 优先级

### 不同位置的优先级

- 外置，在相对于应用程序运行目录的`/config`子目录里
- 外置，在应用程序运行的目录里
- 内置，在`config`包内
- 内置，在`Classpath`根目录

### 同一位置的优先级

- `application.yml` > `application.properties`

## 设置HTTPS服务

- 首先使用`jdk`的`keytool`工具创建按一个密钥

  ```shell
  keytool -keystore mykeys.jks -genkey -alias tomcat -keyalg RSA
  ```

- 写入配置文件中

  ```yml
  server:
    port: 8443
    ssl:
      key-store: classpath:mykeys.jks
      key-store-password: 123456
      key-password: 123456
      keyAlias: tomcat
  ```

## 日志

- 默认情况下，Spring Boot会使用logback.xml来记录日志

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <configuration scan="true" scanPeriod="30 seconds" debug="false">
  
      <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
          <encoder>
              <pattern>[%-5level %d{yyyy-MM-dd HH:mm:ss} %c{0}] - %m%n</pattern>
          </encoder>
      </appender>
  
      <logger name="root" level="INFO">
          <appender-ref ref="STDOUT" />
      </logger>
  
  </configuration>
  ```

  或者在`application.yml`中配置

  ```yml
  logging:
    level:
      root: WARN
      org.springframework.security: DEBUG
    file:
      path: /logs/demo.log
  ```

  也可以自定义配置文件

  ```yml
  logging:
  	config:
  		classpath: demo-config.xml
  ```

- 如果想要使用其他日志，只需要修改依赖，引入对新日志的起步依赖，同时排除掉logback.xml

  - 排除依赖

    ```xml
    <dependency>
    	<groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter</artifactId>
    	<exclusions>
    		<!-- 排除自带的logback依赖 -->
    		<exclusion>
    			<groupId>org.springframework.boot</groupId>
    			<artifactId>spring-boot-starter-logging</artifactId>
    		</exclusion>
    	</exclusions>
    </dependency>
    ```

  - 添加新的依赖

    ```xml
    <!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.14.1</version>
    </dependency>
    ```

## 配置数据源

- 配置JDBC

  ```yml
  spring:
    datasource:
      driver-class-name: com.mysql.jdbc.Driver
      url: mysql://localhost/demo
      username: root
      password: root
  ```

- 一旦设置了`spring.datasource.jndi-name`属性，其他数据源连接属性都会被忽略，除非没有设置特别的数据源链接属性。

## 配置bean

- 使用`@ConfigurationProperties("kingdeguo")`

  通过这个注解可以加载所有关于`kingdeguo`的属性

  ```yml
  kingdeguo:
  	site: www.kingdeguo.com
  	age: 18
  ```

  ```java
  @ConfigurationProperties(prefix="kingdeguo")
  // 或
  @ConfigurationProperties("kingdeguo")
  ```

## Profile

- 是一种条件化配置，基于运行时激活的profile，会使用或者忽略不同的Bean或者配置类

- 在Java中可以使用配置

  ```java
  @Profile("dev")
  ```

  当配置文件中写

  ```yml
  spring:
  	prifiles:
  		active: dev
  ```

  则会激活此此配置

- 使用多 profile YAML 来进行配置

  ```yml
  logging:
  	...
  
  ---
  
  spring:
    profiles:
      active: dev
      
  ---
  
  spring:
    profiles:
      active: prod
  
  ```

  每一组使用`---`进行分隔

  第一段没有指定，那么会对所有生效。