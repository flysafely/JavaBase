package Singleton;

public class SingletonLazySafeDemo {
    public static SingletonLazySafeDemo singletonLazySafeDemo;
    private SingletonLazySafeDemo(){
    }

    public static synchronized SingletonLazySafeDemo getSingletonLazySafeDemo(){
        if (singletonLazySafeDemo == null){
            singletonLazySafeDemo = new SingletonLazySafeDemo();
        }
        return singletonLazySafeDemo;
    }
}
