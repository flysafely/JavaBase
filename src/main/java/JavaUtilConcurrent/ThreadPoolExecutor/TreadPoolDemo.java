package JavaUtilConcurrent.ThreadPoolExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 固定线程数量的线程池
         */
        ExecutorService FixedThreadPool = Executors.newFixedThreadPool(5);
        /**
         * 同时只能有5个线程工作
         * 其他线程池中的线程如果还没有释放，后续的线程就只能阻塞等待有线程池有空闲出来
         */
//        for (int i = 0; i < 30;i++){
//            int finalI = i;
//            FixedThreadPool.execute(()->{
//                try {
//                    System.out.println(Thread.currentThread().getName() + "线程:Start Working...");
//                    TimeUnit.MILLISECONDS.sleep(finalI *100);
//                    System.out.println(Thread.currentThread().getName() + "线程:Done!...");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    FixedThreadPool.shutdown();
//                }
//            });
//        }
        /**
         * 该线程池中只有一个可用线程
         * 其他为获取到线程池中资源的线程都阻塞
         */
//        ExecutorService SingleThreadExecutor = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 30;i++){
//            SingleThreadExecutor.execute(()->{
//                try {
//                    System.out.println(Thread.currentThread().getName() + "线程:Start Working...");
//                    TimeUnit.MILLISECONDS.sleep(1000);
//                    System.out.println(Thread.currentThread().getName() + "线程:Done!...");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    SingleThreadExecutor.shutdown();
//                }
//            });
//        }
        /**
         * 该线程池中可以存在最大上限Integer.MAX_VALUE个的线程
         * 可以在当前线程池中线程资源不够的情况下，扩容
         * 同时又可以在线程资源空闲的情况下收缩复用
         * 例如需要启动30个线程，每个线程需要执行的时长不一样
         * 当前面部分线程已经获取线程池资源后开始运行后，线程执行完毕后会释放线程池资源出来，
         * 后续需要启动的线程会重新获取释放出来的线程资源
         * 如果突然30是个线程资源都被占用，又来了新的线程需要执行，那么cache线程池又会继续扩容
         */
        ExecutorService CachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 30;i++){
            TimeUnit.MILLISECONDS.sleep(100);
            CachedThreadPool.execute(()->{
                System.out.println(Thread.currentThread().getName() + "线程:Start Working...");
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "线程:Done!...");
            });
        }

    }
}
