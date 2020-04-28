package JavaUtilConcurrent.ReentrantLock.ProducerAndCustomer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestClass {
    String flag = "A";
    Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    public void method(){
        lock.lock();

        try {
            System.out.println("线程A已进入Try");
            for (int i = 0; i < 3;i++){
                System.out.println("测试线程A在运行！");
            }
            while (flag != "A"){
                conditionA.await();
            }
            for (int i = 1; i < 3;i++){
                System.out.println(Thread.currentThread().getName() + "正在执行:" + i + "业务代码");
            }
            flag = "B";
            conditionB.signal();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
            System.out.println("线程A已经释放锁");
        }
    }

    public void method2(){
        lock.lock();
        try {
            System.out.println("线程B已进入Try");
            for (int i = 0; i < 3;i++){
                System.out.println("测试线程B在运行！");
            }
            while (flag != "B"){
                conditionB.await();
            }
            for (int i = 1; i < 5;i++){
                System.out.println(Thread.currentThread().getName() + "正在执行:" + i + "业务代码");
            }
            flag = "A";
            conditionA.signal();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
            System.out.println("线程B已经释放锁");
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
        TestClass testClass = new TestClass();
        new Thread(()->{for (int i = 1; i < 5;i++){
            System.out.println("线程A进第" + i + "轮循环");
            testClass.method();
        }},"Thread AAA").start();
        new Thread(()->{for (int i = 1; i < 5;i++){
            System.out.println("线程B进第" + i + "轮循环");
            testClass.method2();
        }},"Thread BBB").start();

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
