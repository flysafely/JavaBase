package JavaUtilConcurrent.SpinLock;

public class MainTest {

    public static void main(String[] args) {
        TestMethods testMethods = new TestMethods();
        new Thread(()->{testMethods.method1();},"AAA").start();
//        new Thread(()->{testMethods.method3();},"BBB").start();
    }

}
