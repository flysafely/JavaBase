package JavaUtilConcurrent.SynchronizedUsage;

public class testMain {
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        new Thread(TestClass::staticMethod,"thread 1").start();
//        new Thread(testClass::method2,"thread 2").start();
        new Thread(testClass::normalMethod,"thread 3").start();
    }
}
