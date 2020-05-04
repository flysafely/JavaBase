package JavaUtilConcurrent.SynchronizedUsage;

public class testMain {
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        new Thread(()->{
            testClass.normalMethod();

        },"thread 1").start();
//        new Thread(testClass::method2,"thread 2").start();
        new Thread(()->{
            testClass.method();
        },"thread 3").start();
    }
}
