package Singleton;

/**
 * 饿汉式单例模式
 * @author flysafely
 * @since 2020.4.16
 */
public class SingletonHungryDemo {
    /**
     * 静态变量是在类被加载的时候就会执行赋值操作，这里也就是实例化单利赋值给singetonDemo
     * 因为类加载这个过程是又JVM控制的，一个类只会被加载一次，所以可以控制这里的实例化的对象只有一个。
     */
    private static SingletonHungryDemo singletonDemo = new SingletonHungryDemo();

    private SingletonHungryDemo(){
        /**
         * 构造器在new 创建对象后，开始初始化操作
         * 如果构造器中的初始化比较复杂，耗时较长
         * 会导致类只要被加载，就会执行复杂的初始化工作
         * 所以叫做饿汉式，比较浪费资源
         */
        System.out.println("我正在初始化！！！！！");
    }
    public static SingletonHungryDemo getSingletonDemo(){
        return singletonDemo;
    }
}
