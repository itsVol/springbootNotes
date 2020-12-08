Spring Boot特点：
1. 构建一个独立的Spring应用
2. 内置tomcat，无需配置war包
3. 简化maven配置
4. 自动配置Spring应用和springMVC，没有xml

@springBootApplication注解，第一个参数：入口类对象，第二个参数：main参数
项目默认没有项目名，必须在配置文件中加server。context-path参数
### 相关注解
1. @SpringBootApplication 标示入口类。
（
    @SpringBootConfiguration ：标示注解 标示这是个springboot入口
    @EnableAutoConfiguration ：核心注解 自动配置spring以及自己第三方技术配置
    @ComponentScan           ：组件扫描 默认扫描的是入口类的包，以及其子包
 ）
2. main函数 ：
   启动内嵌tomcat服务器。
   参数1:传入入口类的目的是让@EnableAutoConfiguration自动配置Spring时将CompontentScan扫描到注解创建对象一并放入工厂
   参数2:指定外部虚拟机覆盖
   
3. RestController
    修饰范围：类。所有控制器
   作用，将控制中所有方法返回值转化为Jason 并响应到前端
#### 元注解：用来修饰注解的注解
@Target    ：指定注解的修饰范围
@Retention ：指定注解的生效时机
@Documented
@Inherited

### 配置文件拆分：
    将公共配置放入主配置文件中，不同配置放入在不同环境配置中

 spring.profiles.active


1. 创建单个自定义对象： // 一般自己写的类用这种方法
    注解方式：Component： 通用组件对象创建的注解
            @Service 标示业务层组件创建、@Controller控制器对象创建 @Repository：DAO组件注解

2. 一次性创建多个组件对象包含复杂对象 @Configuration  修饰在类上，工厂中每一个方法代表一个对象的创建
   // 使用第三方代码用这种，或者复杂对象。 
   

### springBoot注入方式
1. @value注解进行属性注入 @Value("${name}") name 写在yml中

2. 直接注入对象中属性：前缀注入方式：@ConfigurationProperties(prefix=""user")

 ### AOP:
1. 开发通知，前置通知，后置通知，环绕通知，异常通知
2. 面向切面编程的注解：
    @Aspect  用在类上，代表这个类是切面的配置类（先引入starter-aop依赖）
    @Before  前置通知 修饰在方法上，代表这个方法是个通知方法。value属性：用来书写当前通知对应切入点表达式
    @Around  执行目标前后都到环绕通知中，需要加入ProceedingJoinPoint，.proceed()：放行
    @After

   @After和@Before若想要获取运行时的信息，要在自定义的通知方法中加入 joinPoint参数：获取当前方法对象和方法参数信息。

###拦截器 
    类似于过滤器，但不是，
    1.请求到达会经过拦截器，响应回来也会经过
    2.拦截器只能拦截控制器的请求，不能拦截jsp请求
    3。中断请求轨迹

作用：将多个控制器相同的代码放到拦截器里，减少冗余。
实现拦截器类， 