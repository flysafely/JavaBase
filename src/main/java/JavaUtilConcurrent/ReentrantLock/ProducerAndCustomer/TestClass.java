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
            while (flag != "A"){
                conditionA.await();
            }
            for (int i = 1; i < 3;i++){
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
            flag = "B";
            conditionB.signal();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
        }
    }

    public void method2(){
        lock.lock();
        try {
            while (flag != "B"){
                conditionB.await();
            }
            for (int i = 1; i < 5;i++){
                System.out.println(Thread.currentThread().getName() + ":" + i + "@@@@@@@");
            }
            flag = "A";
            conditionA.signal();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
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
            testClass.method();
        }},"Thread AAA").start();
        new Thread(()->{for (int i = 1; i < 5;i++){
            testClass.method2();
        }},"Thread BBB").start();

//        new Thread(testClass::methodA,"thread A").start();
//        new Thread(testClass::methodB,"thread B").start();
    }
}
