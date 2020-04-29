package JavaUtilConcurrent.ReentrantLock.ProducerAndCustomer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class abc {
    Lock lock = new ReentrantLock();
    public void method(){
        lock.lock();
        for (int i = 1; i < 100;i++){
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
        lock.unlock();
    }

    public void method2(){
        lock.lock();
        for (int i = 1; i < 100;i++){
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
        lock.unlock();
    }
}
