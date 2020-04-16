package Singleton;

public class SingletonStaticInnerClassDemo {
    /**
     * 静态内部内，在没有显示调用的时候，不会加载内部类
     * 当需要用到单利的时候，在进行加载，就能通过jvm保证单利
     *
     */
    private static class SingletonHolder{
        private static final SingletonStaticInnerClassDemo singleton = new SingletonStaticInnerClassDemo();
    }

    private SingletonStaticInnerClassDemo(){}

    public final static SingletonStaticInnerClassDemo getInstance(){
        return SingletonHolder.singleton;
    }
}
