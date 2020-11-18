学习笔记

- 第9节课后作业

1、(选做)使Java里的动态代理，实现一个简单的AOP。

2、(**必做**)写代码实现Spring Bean的装配，方式越多越好（XML、Annotation都可以），提交到Github。

XML方式 https://github.com/CHImPB/JAVA-000/tree/main/Week_05/springhomework5/src/test/java/com/zhanghongyu/XmlBeanTest.java

Annotion https://github.com/CHImPB/JAVA-000/tree/main/Week_05/springhomework5/src/test/java/com/zhanghongyu/AnnotationBeanTest.java

Autowire
  1) 直接使用@Autowired注解
  2）Autowire byName
  3) Autowire byType
  4) Autowire constructor
  
  https://github.com/CHImPB/JAVA-000/tree/main/Week_05/springhomework5/src/test/java/com/zhanghongyu/AutowireBeanTest.java

3、(选座)实现一个Spring XML自定义配置，配置一组Bean，例如Student/Klass/School。

4、（选做，会添加到高手附加题）
4.1 （挑战）讲网关的frontend/backend/filter/router/线程池都改造成Spring配置方式；
4.2 （挑战）基于AOP改造Netty网关，filter和router使用AOP方式实现；
4.3 （中级挑战）基于前述改造，将网关请求前后端分离，中级使用JMS传递消息；
4.4 （中级挑战）尝试使用ByteBuddy实现一个简单的基于类的AOP；
4.5 （超级挑战）尝试使用ByteBuddy与Instrument实现一个简单JavaAgent实现无侵入下的
AOP；

- 第10节课后作业

1. （选做）总结一下，单例的各种写法，比较它们的优劣。

2. （选做）maven/spring 的profile 机制，都有什么用法？

3. （**必做**）给前面课程提供的Student/Klass/School 实现自动配置和Starter。

https://github.com/CHImPB/JAVA-000/tree/main/Week_05/springhomework5/src/test/java/com/zhanghongyu/SchoolBeanTest.java

https://github.com/CHImPB/JAVA-000/tree/main/Week_05/springboot/src/main/java/com/zhanghongyu/springboot/starter

4. （选做）总结Hibernate 与MyBatis 的各方面异同点。

5. （选做）学习MyBatis-generator 的用法和原理，学会自定义TypeHandler 处理复杂类型。

6. （**必做**）研究一下JDBC 接口和数据库连接池，掌握它们的设计和用法：
  1）使用JDBC 原生接口，实现数据库的增删改查操作。
  
  https://github.com/CHImPB/JAVA-000/tree/main/Week_05/springboot/src/test/java/com/zhanghongyu/springboot/SpringbootApplicationTests.java
  
  2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
  
  https://github.com/CHImPB/JAVA-000/tree/main/Week_05/springboot/src/test/java/com/zhanghongyu/springboot/SpringbootApplicationTests.java
  
  3）配置Hikari 连接池，改进上述操作。提交代码到Github。
  
  https://github.com/CHImPB/JAVA-000/tree/main/Week_05/springboot/src/main/java/com/zhanghongyu/springboot/hikari/StudentController.java
  
  附加题（可以后面上完数据库的课再考虑做）：

7. (挑战)基于AOP 和自定义注解，实现@MyCache(60) 对于指定方法返回值缓存60秒。

8. (挑战)自定义实现一个数据库连接池，并整合Hibernate/Mybatis/Spring/SpringBoot。

9. (挑战)基于MyBatis 实现一个简单的分库分表+读写分离+分布式ID 生成方案。
