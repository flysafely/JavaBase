package JavaUtilConcurrent.DeadLock;

public class DeadLockDemo {
    public final static String lock1 = "lock1";
    public final static String lock2 = "lock2";

    public void runDemo(int num){
        if (num == 1) {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + "线程获取" + lock1 + "试图获取lock2");
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + "线程获取到" + lock2);
                }
            }
        }else {
            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "线程获取" + lock1 + "试图获取lock2");
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + "线程获取到" + lock2);
                }
            }
        }
    }
    public static void main(String[] args) {
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        new Thread(()->{deadLockDemo.runDemo(1);},"AAA").start();
        new Thread(()->{deadLockDemo.runDemo(2);},"BBB").start();
    }
}
