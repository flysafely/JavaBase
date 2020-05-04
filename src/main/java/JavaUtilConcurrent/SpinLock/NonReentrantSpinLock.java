package JavaUtilConcurrent.SpinLock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 不可重入的自旋锁
 */
public class NonReentrantSpinLock {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * 加锁方法
     */
    public void lock(){
        /**
         * 当前进入该方法的线程获取
         */
        Thread thread = Thread.currentThread();
        do{
        } while (!atomicReference.compareAndSet(null,thread));
        System.out.println(Thread.currentThread().getName() + "获得了锁！");
    }

    /**
     * 解锁方法
     */
    public void unlock(){
        Thread thread = Thread.currentThread();
        /**
         * 解铃还须系铃人
         * 要让当时加锁的线程，来解锁
         * compareandset方法的期望线程(当前调用unlock方法的线程)和atomicReference中存储的线程一致
         * 则解锁成功，否则，解锁失败
         */
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName() + "释放了锁！");
    }

}
