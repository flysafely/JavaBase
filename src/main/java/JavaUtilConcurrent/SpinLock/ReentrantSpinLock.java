package JavaUtilConcurrent.SpinLock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 不可重入的自旋锁
 */
public class ReentrantSpinLock {
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();
    private int lockCount = 0;
    /**
     * 加锁方法
     */
    public void lock(){
        /**
         * 当前进入该方法的线程获取
         */
        Thread thread = Thread.currentThread();
        if (thread == atomicReference.get()){
            lockCount++;
            return;
        }
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
         * 也就是compareandset方法的期望线程为
         */
        if (thread == atomicReference.get()) {
            if (lockCount > 0) {
                lockCount--;
            } else {
                atomicReference.compareAndSet(thread, null);
                System.out.println(Thread.currentThread().getName() + "释放了锁！");
            }
        }
    }
}
