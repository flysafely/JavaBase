package Singleton;

public class SingletonLazyDoubleCheckDemo {
    /**
     * 这里的静态成员需要被volatile修饰
     * 因为避免instance != null 成立
     * 但是instance指向的空间中没有初始化好的实例对象
     * 造成这种情况的原因是
     * singletonLazyDoubleCheckDemo = new SingletonLazyDoubleCheckDemo();
     * 实际的执行过程是：分配空间、初始化对象放入空间、变量指向分配的空间
     * 但是后面两个步骤没有数据依赖关系，在多线程模式下JVM可以对指令进行重排
     * 从而可能有几率导致先将singletonLazyDoubleCheckDemo指向了空间地址，但是空间中并没有存入初始化好的对象
     * 就直接将一个没有实例对象的内存地址return了，从而在其他地方导致调用单例上的问题
     */
    private static SingletonLazyDoubleCheckDemo singletonLazyDoubleCheckDemo;
    private SingletonLazyDoubleCheckDemo(){}

    public static SingletonLazyDoubleCheckDemo getSingletonLazyDoubleCheckDemo(){
        /**
         * 这里为什么需要单独再判断一下的原因：
         * 可以极大的提高获取单例的效率，因为创建单例只有一次 一个线程执行就可以了，后续单例的获取
         * 就不需要再进到加锁的代码块中执行了
         */
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
