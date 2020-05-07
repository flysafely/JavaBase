package JavaUtilConcurrent.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class Worker extends Thread {
    private String name;
    private long time;

    private CountDownLatch countDownLatch;

    public Worker(String name, long time, CountDownLatch countDownLatch) {
        this.name = name;
        this.time = time;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            /**
             * 第一阶段工作称为准备工作
             * 主线程只需要等到子线程完成了第一阶段工作后
             * 就可以退出阻塞开始执行后续工作
             */
            System.out.println(name+"正在完成第一阶段工作...");
            Thread.sleep(time);
            System.out.println(name+"第一阶段工作完成");

            countDownLatch.countDown();
            /**
             * countDownLatch.countDown();执行后如果值为0
             * main线程中await()之后的语句开始执行
             * 和join的区别也主要体现在这里，join是将整个线程工作都加入，需要等待执行.join()方法的线程完成工作后才能 执行后续代码
             */
            System.out.println(name+"正在完成第二阶段工作...");
            Thread.sleep(2000); //这里就姑且假设第二阶段工作都是要2秒完成
            System.out.println(name+"第二阶段工作完成");
            System.out.println(name+"工作完成，耗费时间="+(time+2000));

        } catch (InterruptedException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}
