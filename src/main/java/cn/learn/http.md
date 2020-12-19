###HTTP不安全
1. 窃听风险
2. 篡改风险
3. 冒充风险

###安全通信的四大原则
1. 机密性：对数据进行加密，防止中间人窃听
2. 完整性：传输过程中没有被篡改
3. 身份认证：确认对方身份
4. 不可否认：不可否认已发生的事实

###https通信原理概述
对称加密，使用同一把锁（密钥）来进行解密
非对称加密，解决单向密钥传输问题
数字证书，解决公钥被篡改问题
###单例模式
1. 单例类只能有一个构造函数,并且是私有的
2. 单例类必须自己创建自己的唯一实例
3. 单例类必须给所有其他对象提供这一实例。
```Java
//饿汉模式
public class HungrySingleton {
	//私有的构造函数
    private HungrySingleton() { }
    //自己创建自己的实例
    private static final HungrySingleton hungrySingleton = new HungrySingleton();
    //向其他对象提供这一实例
    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }
}
```
```java
//懒汉模式
public class LazySimpleSingleton {
    //私有的构造函数
    private LazySimpleSingleton() {}

    private static LazySimpleSingleton lazy = null;
    //实例化对象并提供这一实例
    public static LazySimpleSingleton getInstance() {
        if (lazy == null) {
            lazy = new LazySimpleSingleton();
        }
        return lazy;
    }
}
```
```java
//双重检查锁
public class LazyDoubleCheckSingleton {

    private volatile static LazyDoubleCheckSingleton lazy = null;

    private LazyDoubleCheckSingleton() { }

    public static LazyDoubleCheckSingleton getInstance() {
        if (lazy == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (lazy == null) {
                    lazy = new LazyDoubleCheckSingleton();
                }
            }
        }
        return lazy;
    }
}

```
