package JavaUtilConcurrent.CountDownLatch;

import java.util.ArrayList;
import java.util.List;

public class JoinDemo implements Runnable{
    @Override
    public void run() {
        try {
            System.out.println("线程" + Thread.currentThread().getName() + "正在执行!");
            Thread.sleep(2000);
            System.out.println("线程" + Thread.currentThread().getName() + "执行完毕!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * 新建多线程对象
         */
        List<Thread> thlist = new ArrayList<>();
        for (int i = 0; i < 30;i++){
            thlist.add(new Thread(new JoinDemo(), String.valueOf(i)));
        }

        /**
         * start方法是实例方法没有异常抛出，可以直接使用双冒号调用
         */
        thlist.forEach(Thread::start);
        /**
         * 需要抛出异常的方法，不能使用双冒号的方式
         */
        thlist.forEach((th) -> {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        System.out.println("main线程执行完毕!");
    }
}
