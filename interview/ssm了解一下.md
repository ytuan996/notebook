### Spring IOC容器的初始化和依赖注入

在IOC容器中，Bean的初始化和依赖注入和两个不同的步骤；需要首先初始化之后，才能完成依赖注入

Bean的初始化分为三个步骤：

    1. Resource定位：根据开发者的配置，进行资源定位
    2. Beandefintion的载入：Spring根据开发者的配置生成相应的pojo实例
    3. Beandefintion的注册：将第二步创建的pojo实例注册到spring IOC容器中，然后就可以使用getBean方法获取实例了。
    
Bean的初始化完成之后，还没有进行依赖注入，也就是配置的资源还没有注入到Bean中，此时的Bean还不可以完全使用。
还需要进行依赖注入。

依赖注入的三种方式：

    1. 构造函数注入
    2. setter方法注入
    3. 接口注入(用的比较少)

### Spring Bean的生命周期

### Spring Bean的配置

Spring Bean的三种配置方式：

    1. 在XML文件中显示配置
    2. 在Java的接口和类中实现配置
    3. 隐士Bean的发现机制和自动装配原则
    
配置方式的选择，首选隐士的自动装配机制，无法时仔使用Java配置，当我们使用第三类库时，使用XML显示配置。

- 使用XML配置Bean

```xml

<bean id="xxx" class="xxx.xx.xx">
    <property name="" value=""/>
    <property name="" ref=""/>
</bean>
```
- 使用注解配置Bean(提供了自动装配)

在Spring中，有两种方式可以发现注解配置的Bean：

    1. 组件扫描：通过定义资源的方式，让IOC容器扫描对应的包，从而加载Bean
    2. 通过注解的定义，使得某些依赖关系可以通过注解完成
    
使用@Component注解定义一个Bean,并使用@Value注入值

```java

@Component(value = "role")
public class Role {

	@Value("1")
	private Long id;

	@Value("ytuan")
	private String name;

	@Value("handsome")
	private String note;

	// setter/getter ...
}
```

除了在类上面加@Component注解外，还需要一个配置类并加注解@ComponentScan注解

- 自动装配——@Autowired

在SpringIOC容器中，是首先完成Bean的定义和生成，然后再去寻找需要注入的资源，也就是在Spring生成所有的Bean后，发现
@Autowired注解，它就在Bean中查找，找到对应类型的Bean(默认按类型查找),并将其注入进来，完成依赖注入。所谓的自动装配，
就是spring自己发现对应的Bean，自动完成装配的方式。

@Autowired 默认按照类型查找，但是在当一个接口有多个实现类的时候，就会发生歧义，为了解决这个问题，另外提供了两个注解，
@Primary 和 @Qualifier，@Primary 和@Autowired一起使用，表示查找的首要实例，@Qualifier和@Autowired一起使用，
则表示按照名称查找。

另外@Autowired注解还可以在构造方法的参数中使用。

@Component注解只能使用在类上，当我们使用第三方类库时，无法在类上加该注解，那么我们可以@Bean注解在方法，将该方法的返回值
作为一个Bean加入到SpringIOC容器中。

对于在Java类上加了注解的，我们不一定需要专门设置一个配置类，也可以在XML文件中定义扫描包即可

```xml

<context:component-scan base-package="com.ytuan.ssm.*"/>
```

### Spring加载外部属性配置文件

可以使用@PropertySource注解来加载外部属性文件，如果需要在代码中引用配置文件的属性，那么需要配置的一个Bean,
PropertySourcePlaceholderConfigurer,它饿作用就是让Spring能够解析属性文件值。重要，使用@Value引用即可。

```xml

<bean id="xxx" class="...PropertySourcePlaceholderConfiguer"/>
```

使用XML加载配置文件

```xml

<context:property-placeholder location=""/>
```

### Spring Bean的作用域

Singleton:

Prototype:

Request:

Session:

# SpringAOP  （这里介绍的太简单了，需要另外在看一看）

AOP是通过动态代理模式，掌控各个对象的切面环境，管理包括日志，数据库事物等操作；在执行原有对象方法之前，正常返回，异常返回
时插入自己代码逻辑的能力，甚至取代原有方法，

### AOP的术语理解

- 切面：通俗的说就是在一个什么样的环境下工作。比如数据库的事务就是一个切面，能够在方法执行之前，之后，正常返回，异常
返回的时候切入自己的代码。更像是一个拦截器。

- 通知：是在切面中执行的代码逻辑；通知分为一下几种：

    1. 前置通知：在执行方法前或者环绕通知前执行的逻辑
    2. 后置通知：在方法执行后或者环绕通知后执行的逻辑
    3. 返回通知：在方法返回或者环绕通知后执行的逻辑
    4. 异常通知：在方法中执行发生异常时或者环绕通知产生异常后执行的逻辑
    5. 环绕通知：它可以直接取代拦截对象的方法。

- 引入

在现有的对象中添加类和方法

- 切点

被切面拦截的方法就是一个切点，切面可以将切点和方法织入到代码逻辑中

- 连接点

连接点是一个判断条件，指定哪些是切点，Spring只会对满足条件的切点进行拦截

- 织入

织入是生成代理对象的过程，将增强的代码逻辑加入到原始方法对应的切入点

### Spring对AOP的支持

SpringAOP只支持方法层级的拦截，在Spring中有四种方式去实现AOP的拦截功能：

    1. 使用ProxyFactoryBean和对应的接口实现AOP (很少使用)
    2. 使用XML配置AOP  (次要使用)
    3. 使用@Aspectj注解配置切面  (重点使用)
    4. 使用Aspectj注入切面   (很少使用)
    
- 使用@Aspectj注解开发Spring AOP

在Spring AOP中，我们主要是以某个类的某个方法作为切点，加入横切的逻辑

- 创建一个切面

就是创建一个类声明在满足条件的方法上执行的代码逻辑；使用@Aspectj注解；
(@before,@after,@afterReturning,@afterThrowing)    

- 连接点的配置

使用@pointcut配置满足条件的切入点

### Spring和数据库编程

- Spring配置数据库资源

对于数据库连接池而言，可以选择Spring自身提供的实现，也可以使用第三方提供的数据库连接池实现(alibaba的druid)

使用Spring本身的实现进行配置

```xml

    <!--Spring本身实现的数据库连接池-->
    <bean id="dataSourceSpring" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
        <property name="driverClass" value=""/>
        <property name="url" value=""/>
        <property name="username" value=""/>
        <property name="password" value=""/>
    </bean>
```

使用第三方的数据库连接池

```xml

    <!--Druid数据库连接池-->
    <bean id="dataSourceDruid" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="driverClass" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        <property name="maxActive" value=""/>
        <property name="maxIdle" value=""/>
        <property name="maxWait" value=""/>
    </bean>
```

### Spring和Mybatis的整合项目 Mybatis-Spring

目前主流的互联网项目，都是基于Spring+SpringMVC+Mybatis搭建的，使用SpringIoc可以有效的管理Java中的各类资源，实现
即插即拔的功能；通过SpringAOP，可以将数据库事务委托给Spring，可以很大程度地消除事务代码。而Mybatis则提供可配置，可优化SQL
的高效访问数据库；三者结合可构建高性能的互联网项目。

- 配置Mybatis-Spring项目

    1. 配置数据源
    2. 配置SqlSessionFactory
    3. 可选择地配置SqlSessionTemplate
    4. 配置Mapper，可以配置单个Mapper，也可以使用扫描生成Mapper
    5. 配置事务管理
    
1. 配置数据源
2. 配置SqlSessionFactory

SqlSessionFactory是产生SqlSession的基础，在Mybatis-Spring项目中提供了SqlSessionFactoryBean支持

```xml

    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--mybatis的全局配置, 可以在这里指定的文件中(sqlMapConfig.xml)配置mapper所在的位置-->
        <property name="configLoction" value="classpath:sqlMapConfig.xml"/>
    </bean>
```

3. 配置MapperFactoryBean，这个配置需要为每个MapperInterface提供配置，比较麻烦


4. 为了解决第3种情况中为每个mapper配置的麻烦，可以使用MapperScannerCongfigurer扫描指定的包

配置MapperScannerCongfigurer的属性有：
    
    1. basepackage: 扫描mapper所在的包
    2. annoationClass: 扫描包下类使用的注解，一般DAO层使用 @Repository
    3. SqlSessionfactoryBeanName: sqlSessionFactoryBean的id

5. 配置事务管理，下面马上配置

### Spring 数据库事务管理

Spring中的数据库事务管理通过 PlatformTransactionManager 管理的，它是一个接口，只定义了两个方法，commit(),
rollback().Spring提供了许多该接口的实现，其中我们最常使用的是 DataSourceTransactionManager；

- 事务管理器的配置（XML配置）

```xml

    <!--配置数据库的事务管理器-->
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="dataSource"/>
    </bean>
```    

### Spring的声明式事务配置 (@Transactional 和XML配置任选一个)

关于事务，我们通常的原则是执行的过程中如果没有发生异常，那么就提交事务，否则回滚事务。声明式事务类似：
如果业务方法不发生异常，或者发生异常但该异常是我们允许的，那么就提交该事务，否则回滚。

- @Transactional注解的配置项

配置项 | 含义| 说明 
-- | -- | --
value/transactionManager| 定义事务管理器 |它就是之前配置的在SpringIoc中的一个bean id 
isolation | 事务的隔离级别 | 默认是数据库默认的隔离级别
propagation | 事务的传播行为| 方法之间调用的问题；不同方法是否需要开启不同的事务
timeout| 超市时间 | 如果子事务的执行过程中，超出时间，那么将会抛异常并且回滚事务
readOly | 是否开启只读事务|  默认为false
rollbackFor/rollbackForClassName|需要回滚事务的异常类|发生该类异常时回滚事务
noRollbackFor/noRollbackForClassName| 发生异常也不需要回滚的异常定义| 发生异常也不需要回滚的异常定义

使用声明式事务需要配置注解驱动

```xml

    <!--声明式事务的注解驱动-->
    <tx:annotation-driven transaction-manager="transactionManager"/>
```

使用xml配置声明式事务，需要配置一个事务拦截器TransactionInterceptor.

```xml

    <!--使用xml配置事务拦截器-->
    <bean id="transactionInterceptor" class="org.springframework.transaction.interceptor.TransactionInterceptor">
        <property name="transactionManager" value="transactionManager"/>
        <property name="transactionAttributes">
            <props>
                <!--key是业务方法的正则匹配， 而value就是对应的事务策略-->
                <prop key="insert*">PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED</prop>
                <prop key="save*">PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED</prop>
                <prop key="add*">PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED</prop>
                <prop key="select*">PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED</prop>
                <prop key="get*">PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED</prop>
                <prop key="find*">PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED</prop>
                <prop key="remove*">PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED</prop>
                <prop key="update*">PROPAGATION_REQUIRED,ISOLATION_READ_UNCOMMITTED</prop>
            </props>
        </property>
    </bean>
```

使用xml配置声明式事务,除了配置不同的方法使用不同的策略之外，还需要配置拦截哪些类

```xml

    <!--配置transactionInterceptor拦截哪些类-->
    <bean id="autoProxyCreator" class="org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator">
        <property name="beanNames">
            <list>
                <value>*ServiceImpl</value>
            </list>
        </property>
        <property name="interceptorNames">
            <list>
                <value>transactionInterceptor</value>
            </list>
        </property>
    </bean>
```

### 数据库的事务相关理论

- 数据库事务的四个特性

    1. 原子性(Atomicity)在一个事务的执行中，要么全部成功，要么全部失败
    2. 一致性(Consistency)事务的执行前后，数据从一个一致性变到另外一个一致性
    3. 隔离性(Isolation) 两个事务之间的执行应该相互不影响。(容易造成事务之间的数据丢失)
    4. 持久性(Durability)事务执行完成后，数据不会因为外界的原因而使数据丢失
    
- 丢失更新

在数据库中存在多个事务，多个事务执行的数据更新交错导致有的数据丢失了。为了解决这个问题，我们需要提高事务之间的隔离级别。

- 事务的隔离级别

隔离级别|可能造成的问题|说明|
--|--|--
读未提交|脏读|一个事务可以读取到另一个事务中么有提交的数据
读已提交|不可重复读|一个事务只能读取另一个事务已经提交的数据
可重复读|幻读|同一个事务中在不同时间查询结果总是一致的(只针对数据库中的同一条记录)
序列化|基本ok|同一个事务中在不同时间查询结果总是一致的(可重复读是针对同一条记录，而这里不是同一条记录)

#### 如何在非序列化隔离级别下，保证数据的一致性？？？

### Spring中的事务传播行为

![传播行为表]()

### Spring 中使用事务的注意事项

- @Transactional的失效情况(敲黑板，划重点)

@Transactional的底层实现的是AOP，而AOP的实现原理是动态代理，那么意味这对于静态方法和非public方法，@Transactional
是失效的；还有很重要的一点，那就是在同一个类的不同方法之间相互调用，那么@Transactional也会失效，原因在于这样的自调用
不存在代理对象的生成。解决这个问题的最有效方法就是设置两个类，让它们相互调用，而不是在一个类中调用。

- 错误使用带事务的service

- 避免长时间占用事务，因为数据库的连接资源是有限的

- 错误的捕获异常：根据Spring事务的约定流程，如果我们在调用service时把产生的异常捕获了，那么Spring将不在会收到异常，
那么也就不会回滚。正确的做法应该处理完异常之后在手动抛出一个运行时异常同时Spring回滚事务。

## SpringMVC

MVC其实是一种设计思想，它仅仅存在于JavaWeb中，包括前端，PHP，.next等。它的目的在解耦合，提高代码的重用。
在早期，我们的Java Web项目采用JSP+Bean的模式，也叫做Model1模式，这种模式的主要问题就是JSP和javaBean严重的耦合
在一起，使前端和后端相互依赖太严重，不利于测试，代码的复用也不高。所以后来又出现了Servlet+JSP+Java Bean。
用户的请求到达Servlet，然后Servlet调用Java Bean，操作数据库，然后把结果放到JSP中，最终JSP生成html返回到客户端。
这样前后端得到了分离，而Java代码也可以得到重用了。后来也出现了Struts和Hibernate等框架；随着互联网的发展，web页面的
请求大多采用了ajax请求，只需要返回json数据即可。此时更利用使用的SpringMVC来了。

SpringMVC是围绕这DispatcherServlet工作的，它是一个Servlet，可以拦截HTTP请求；在servlet初始化(调用init方法)的时候，
会根据配置信息获取URL和处理器之间的映射关系，以及拦截器和处理之间的关系，并根据配置的上下文视图解析器定位视图，然后将
将数据渲染到视图中，并返回到客户端。

### SpringMVC的初始化流程

- 首先初始化Spring Ioc上下文

Java Web容器的生命周期中提供了一个ServletContextListener接口，这个接口可以在web容器初始化和结束的时候执行一定的逻辑，
Spring MVC中对应的实现类是ContextLoaderListener。它可以使的在DispatcherServlet初始化之前初始化Spring Ioc容器，
也可以在结束之前销毁容器。

- 初始化映射请求上下文

映射请求上下文的初始化是通过DispatcherServlet的，DispatcherServlet的继承关系：
DispatcherServlet -> FrameworkServlet -> HttpServletBean -> HttpServlet; 
它和普通的Servlet一样，需要自己配置启动的顺序.

web 容器对于Servlet的初始化，是调用它的init方法，DispatcherServlet的init方法在HttpServletBean中，在其中调用了
initBean方法，而initBean的实现在其子类FrameworkServlet中，在该方法中会初始化Spring Ioc容器，如果已经初始化了那么就沿用
它，否则调用onRefresh初始化容器；在onRefresh方法中，会初始化很多易用的组件，这也是Spring MVC方便好用的体现。
包括文件解析，主题解析，处理器映射，处理器的适配器，异常处理适配器，视图解析器等。

### 控制器的开发流程

- 获取请求的参数

    1. @RequestParam 获取请求的参数
    2. @PathVariable 获取路径的请求参数
    3. @SessionAttribute 获取Session中的属性值；
    
- 处理业务逻辑

调用service处理业务逻辑，操作数据库。

- 绑定视图

通过ModelAndView设置数据和视图名称，通过视图解析器定位到视图并渲染数据。也可以设置返回json数据；
modelAndView.setView(new MappingJackson2JsonView());(这不是唯一的方式，还可以通过注解)。

### Spring MVC接收参数详解

- 普通的少量参数接收：

对于一两个请求参数的接收，保证处理方法的参数名和请求的参数名保持一致即可，该方式允许参数为空；

- 大量的参数接收：

当参数比较多时，那么在方法的参数中一一对应就麻烦了，此时需要创建一个pojo类，和请求的参数保持字段一致即可。

- 不能保证前后端参数名称一致时，就需要使用注解进行转换了； 比如@RequestParam

比如前端的参数传递是 role_name,那么后端的接收就是:
 public String getParam(@RequestParam("role_name") String roleName);注意参数不能为空，否则将会抛出异常。
 
 - 在URL的路径后面追加参数(Restful风格)
 
 当我们使用restful风格的传递参数时，使用注解@PathVariable
 
```
@ResponseBody
@RequestMapping("/getUser/{userId}") 
public User getUser(@PathVarianle("userId") String userId){
    return new User()
};
```

- 传递Json参数

前端传递json类型的请求参数，然后后端需要封装一个java类对应json的每个字段，并使用注解@RequestBody 接收。

- 表单序列化

前端将表单的数据序列化为json字符串后传递到后台；这种参数的接收方法也是保证参数的名称一致即可。

- 接收request范围内的属性值： @RequestAttribute

- 接收session范围内的属性之： @SessionAttribute  、 @SessionAttributes

- 获取请求头的值 @RequestHeader

- 获取Cookie的值 @CookieValue

### Spring MVC的拦截器开发

拦截器的作用就不多说了，Spring MVC在启动期间就会解析URL和处理器之间的关系，以及处理器和拦截器之间的关系，然后形成一条
执行链。在Spring MVC中，拦截器的开发需要实现org.springframework.web.servlet.HandlerInterceptor接口，该接口定义了
三个方法，分别在请求前，请求后，视图渲染后执行。

除了实现接口之外，还需要在xml中配置对应的拦截路径

```xml

   <!--配置拦截器-->
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="role/getRole"/>
            <bean class="com.ytuan.ssm.interceptor.RoleInterceptor"/>
        </mvc:interceptor>
    </mvc:interceptors>
```

拦截器的执行顺序是按照配置的顺序执行的，比如配置的顺序是123， 那么执行的顺序就是 123， 返回时执行的顺序就是321；

### Spring MVC验证表单

### Spring MVC上传文件

需要在spring的xml配置文件中配置相应的解析器

```xml

    <bean id="multipartResolver" class="org.springframework.web.multipart.support.StandardServletMultipartResolver"/>
```

另外还有一些关于文件上传的参数，需要在web.xml中配置

```xml

  <servlet>
    <servlet-name>dispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring/application-dispatcherServlet.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
    <multipart-config>
      <location>/user/ytuan/upload</location>
      <file-size-threshold>0</file-size-threshold>
      <max-file-size>2048</max-file-size>
      <max-request-size>1024</max-request-size>
    </multipart-config>
  </servlet>
```

