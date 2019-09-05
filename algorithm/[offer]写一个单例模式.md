### 写一个单例模式

- 只能用于单线程的写法：

将构造函数私有化，并将 Singleton 作为类的static属性，调用getInstance()获取实例时，
只有instance为null才创建对象以避免重复创建。

```java

class Singleton{
    
    private Singleton() {
        
    }
    
    public static Singleton instance = null;
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

- 多线程下但效率不高的代码：

将创建对象实例的代码同步起来

```java

class Singleton{
    
    private Singleton() {
        
    }
    
    public static Singleton instance = null;
    private static Object lock = new Object();
    
    public static Singleton getInstance() {
        synchronized (lock) {
           if (instance == null) {
               instance = new Singleton();
           } 
        }
        return instance;
    }
}
```

- 可行的解法

在instance=null的情况下才同步创建实例的代码

```java

class Singleton{
    
    private Singleton() {
        
    }
    
    public static Singleton instance = null;
    private static Object lock = new Object();
    
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (lock) {
               if (instance == null) {
                   instance = new Singleton();
               } 
            }
        }

        return instance;
    }
}
```

- 静态内部类的实现方式

```java

class Singleton{
    private Singleton(){}
    
    private static class SingletonClass {
        Singleton instance = new Singleton();
    }
    
    public static Singleton getInstance() {
        return SingletonClass.instance;
    }
}
```