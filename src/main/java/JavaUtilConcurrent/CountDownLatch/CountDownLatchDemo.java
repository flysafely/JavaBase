package JavaUtilConcurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
//    public static CountDownLatch countDownLatch = new CountDownLatch(2);
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Worker worker0 = new Worker("worker0", (long) (Math.random()*2000+3000), countDownLatch);
        Worker worker1 = new Worker("worker1", (long) (Math.random()*2000+3000), countDownLatch);
        Worker worker2 = new Worker("worker2", (long) (Math.random()*2000+3000), countDownLatch);

        worker0.start();
        worker1.start();
        countDownLatch.await();

        System.out.println("准备工作就绪");
        worker2.start();

//        new Thread(()->{
//            try {
//                System.out.println(Thread.currentThread().getName() + "线程开始准备工作...");
//                TimeUnit.SECONDS.sleep(3);
//                countDownLatch.countDown();
//                System.out.println(Thread.currentThread().getName() + "线程完成工作!");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"AAAA").start();
//
//        new Thread(()->{
//            try {
//                System.out.println(Thread.currentThread().getName() + "线程开始准备工作...");
//                TimeUnit.SECONDS.sleep(3);
//                countDownLatch.countDown();
//                /**
//                 * countDownLatch.countDown();执行后已经为0
//                 * countDownLatch.await();后的代码可以开始执行
//                 * 所以可能出现 main线程执行！ 再打印出 BBBB线程完成工作!
//                 */
//                System.out.println(Thread.currentThread().getName() + "线程完成工作!");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"BBBB").start();
//
//        countDownLatch.await();
//        System.out.println(Thread.currentThread().getName() + "线程执行！");
    }
}
