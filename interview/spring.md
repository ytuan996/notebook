### 1. 介绍一下Spring？

Spring是Java分层应用一站式的轻量级开源框架，以IOC和AOP为内核，提供了展现层(Spring MVC)、持久层(Spring JDBC,DAO)和事物管理等一站式的企业级应用技术.

- IOC: (Inverse Of Control) 即控制反转。顾名思义，它包含控制和反转两个概念；"控制"是指调用类选择接口实现类的权利；"反转"则是在代码中移除这种权利交由第三方来决定。
在Spring中，则是由Spring容器借Bean的配置来控制。Martin Flower 提出的依赖注入(Dependency Injection):调用类对某一接口的实现类的依赖关系交由第三方注入，以
移除调用类对接口实现类的依赖。
- AOP: (Aspect Oriented Programing) 即面向切面编程。按照软件重构的思想理念，我们该把多个类中相同的代码抽取到父类，提高代码的重用性。但是对于记录日志和事物管理
包裹在核心业务周围的代码，按照传统思维我们却无法抽取。AOP的思路就是把这类包裹在核心业务周围的代码抽取为一个独立模块，而业务模块只完成功能，最终再把非业务代码切入到
核心业务中。感觉就像病毒一样。SpringAOP使用纯Java实现，无需专门的编译和特殊的类加载，而是在运行期间通过动态代理向目标类织入增强代码。

### 2. IOC和AOP的底层实现原理

- IOC Java反射机制
    
    1. 我们知道Java语言的运行需要编译为Class文件然后由类加载器装入JVM，在JVM中形成一份描述Class对象的元信息对象。同时Java允许通过程序访问该元信息对象获知Class
    结构信息，如构造函数，属性，方法等，从而间接控制该Class，这就是Java反射机制。在java.reflect包中，由与反射调用相关的类，这里主要看三个重要的：
    
类名 | 描述 |
-- | -- |
Constructor | 类的构造函数反射类，其中一个主要方法是 newInstance(Objects...initargs)创建对象类的实例，相当于new.
Method | 类方法的反射类，invoke(Object obj, Objects...args)调用对象的方法。
Field | 类的成员变量反射类。
    
    2. 类加载器
    
    ```java
    // 改天再写了
    
    ```
         
- AOP 动态代理 或者 cglib

    1. 动态代理允许开发者在运行期创建接口的代理实例，是AOP绝好的底层技术。主要涉及两个类：
    
类名 | 作用说明 |
-- | -- |
Proxy | 利用 InvocationHandler 动态地创建一个符合某一接口的实例，生成目标类的代理对象
InvocationHandler | 是一个接口，通过实现该接口定义横切逻辑，并通过反射机制调用目标类的代码，动态地将横切逻辑和业务逻辑编织在一起。


### 3. BeanFactory和ApplicationContext有什么区别？

接口| 说明|
-- | -- |
BeanFactory | Spring最低层的接口，运行时加载
ApplicationContext | BeanFactory的派生接口，以及实现其他功能的接口，更强大和易于使用，一次性加载，慢一点，但是提前检查错误。

### 4. 请解释Spring Bean的生命周期？

### 5. 解释Spring支持的几种bean的作用域类型。

类型 | 说明 |
-- | -- |
singleton | 在Spring容器中仅存在一个Bean实例，Bean以单实例的形式存在
prototype | 每次从容器中调用Bean时，都产生一个新的实例
request| 每次HTTP请求都创建一个Bean
session | 同一个Session共享一个Bean，不同的Session是不同的Bean实例
globalSession | 全局Session共享一个Bean

### 6. Spring框架中的单例Beans是线程安全的么？

Spring框架并没有对单例bean进行任何多线程的封装处理,但是实际上Spring的Bean并没有可变状态，多都是处理service，dao层，如果存在可变状态的话，
需要自行解决，最简单的方式就是作用域由“singleton”变更为“prototype”。

### 7. Spring如何处理线程并发问题？

采用ThreadLocal进行处理，解决线程安全问题。每个线程提供一个本地变量的副本，从而保证安全。以空间换时间。

### 8. Spring的自动装配：

默认按照类型在容器中查找匹配的Bean，@Autowired注解

### 9. Spring 框架中都用到了哪些设计模式？

### 10. Spring事务的实现方式和实现原理：

##### 1. spring事务的种类

##### 2. spring的事务传播行为：

##### 3. Spring中的隔离级别：

### 11.

### 12.