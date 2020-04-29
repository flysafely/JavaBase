package JavaUtilConcurrent.ReentrantLock.ProducerAndCustomer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestClass {
    String flag = "A";
    /**
     * ReentrantLock无参构造器默认是创建一个非公平锁
     */
    Lock lock = new ReentrantLock(true);
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();
    public void method() throws InterruptedException {
        lock.lock();
        System.out.println("线程A获得锁");
        Thread.sleep(4000);
        try {
            while (flag != "A"){
                System.out.println("flag != A准备等待");
//                Thread.sleep(1000);
                conditionA.await();
                System.out.println("A被唤醒");
            }
            for (int i = 1; i < 2;i++){
                System.out.println(Thread.currentThread().getName() + "正在执行:" + i + "业务代码");
            }
            flag = "B";
            System.out.println("A唤醒B");
            conditionB.signal();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
            System.out.println("线程A已经释放锁");
        }
    }

    public void method2() throws InterruptedException {
        lock.lock();
        System.out.println("线程B获得锁");
        Thread.sleep(4000);
        try {
            while (flag != "B"){
                System.out.println("flag != B准备等待");
//                Thread.sleep(1000);
                conditionB.await();
                System.out.println("B被唤醒");
            }
            for (int i = 1; i < 2;i++){
                System.out.println(Thread.currentThread().getName() + "正在执行:" + i + "业务代码");
            }
            flag = "C";
            System.out.println("B唤醒C");
            conditionC.signal();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
            System.out.println("线程B已经释放锁");
        }
    }
    public void method3() throws InterruptedException {
        lock.lock();
        System.out.println("线程C获得锁");
        Thread.sleep(4000);
        try {
            while (flag != "C"){
                System.out.println("flag != C准备等待");
//                Thread.sleep(1000);
                conditionC.await();
                System.out.println("C被唤醒");
            }
            for (int i = 1; i < 2;i++){
                System.out.println(Thread.currentThread().getName() + "正在执行:" + i + "业务代码");
            }
            flag = "A";
            System.out.println("C唤醒A");
            conditionA.signal();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
            System.out.println("线程C已经释放锁");
        }
    }
//    public void methodA(){
//        lock.lock();
//        System.out.println("A INTO");
//        try {
//            for (int i = 1; i < 3;i++){
//                System.out.println(Thread.currentThread().getName() + ":" + i);
//            }
//        }catch (Exception e){
//            System.out.println(e);
//        }finally {
//            lock.unlock();
//        }
//    }
//
//    public void methodB(){
//        lock.lock();
//        System.out.println("B INTO");
//        try {
//            for (int i = 1; i < 3;i++){
//                System.out.println(Thread.currentThread().getName() + ":" + i);
//            }
//        }catch (Exception e){
//            System.out.println(e);
//        }finally {
//            lock.unlock();
//        }
//    }
    public static void main(String[] args) {
        /**
         * 当设置为公平锁
         * 会按照线程的请求顺序来进行获取锁
         */
        TestClass testClass = new TestClass();
        new Thread(()->{for (int i = 1; i < 5;i++){
//            System.out.println("线程B：" + i);
            try {
                testClass.method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }},"Thread BBB").start();

        new Thread(()->{for (int i = 1; i < 5;i++){
//            System.out.println("线程A：" + i);
            try {
                testClass.method();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }},"Thread AAA").start();



        new Thread(()->{for (int i = 1; i < 5;i++){
//            System.out.println("线程C：" + i);
            try {
                testClass.method3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }},"Thread CCC").start();

//        new Thread(testClass::methodA,"thread A").start();
//        new Thread(testClass::methodB,"thread B").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (this) {
//                    System.out.println("第1次获取锁，这个锁是：" + this);
//                    int index = 1;
//                    while (true) {
//                        synchronized (this) {
//                            System.out.println("第" + (++index) + "次获取锁，这个锁是：" + this);
//                        }
//                        if (index == 10) {
//                            break;
//                        }
//                    }
//                }
//            }
//        }).start();
    }
}
