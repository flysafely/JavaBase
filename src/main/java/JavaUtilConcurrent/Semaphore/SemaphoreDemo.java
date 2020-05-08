package JavaUtilConcurrent.Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(3);
        for (int i = 0; i < 8;i++){
            new Thread(()->{
                try {
                    /**
                     * 如果是要限制线程后续全部操作
                     * 需要放在操作代码前
                     * 如果acquire()尝试获取失败
                     * 当前线程就会进入循环获取状态
                     * 而不会执行后续的操作
                     */
                    s.acquire();
                    System.out.println(Thread.currentThread().getName() + "：获取到1个资源位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "：准备释放1个资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    s.release();
                }
            },"线程" + i).start();
        }
    }
}
