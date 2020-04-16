package Singleton;

public class SingletonLazyDemo {
    private static SingletonLazyDemo singletonLazyDemo = null;

    private SingletonLazyDemo(){

    }

    public static SingletonLazyDemo getSingletonLazyDemo(){
        if (singletonLazyDemo == null){
            singletonLazyDemo = new SingletonLazyDemo();
        }
        return singletonLazyDemo;
    }
}
