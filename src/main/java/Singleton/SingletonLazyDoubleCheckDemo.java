package Singleton;

public class SingletonLazyDoubleCheckDemo {
    private static SingletonLazyDoubleCheckDemo singletonLazyDoubleCheckDemo;
    private SingletonLazyDoubleCheckDemo(){}

    public static SingletonLazyDoubleCheckDemo getSingletonLazyDoubleCheckDemo(){
        if (singletonLazyDoubleCheckDemo == null){
            /**
             * 给类上锁，多个线程，同时只能有一个线程获取这个类的锁，也就只有一个线程能够初始化对象并赋值
             * 然后释放锁，其他线程再进来后，判断成员变量已经不为null，直接获取对象即可，保证了单例
             */
            synchronized (SingletonLazyDoubleCheckDemo.class){
                if (singletonLazyDoubleCheckDemo == null) {
                    singletonLazyDoubleCheckDemo = new SingletonLazyDoubleCheckDemo();
                }
            }
        }
        return singletonLazyDoubleCheckDemo;
    }
}
