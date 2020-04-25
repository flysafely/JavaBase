package JavaUtilConcurrent.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket {
    private int number = 30;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if (number > 0){
                System.out.println(Thread.currentThread().getName() + "\t卖出第：" + (number--) + "\t还剩下：" + number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 在单元测试中的多线程任务会出现，多线程执行异常的问题
     * 需要在单线程中阻止TestRunner启动的主线程直接结束线程的情况
     * 让其等待其他子线程执行完毕
     *
     */
    public static void main(String[] args) {
        Ticket initTicket = new Ticket();
        new Thread(()->{for (int i = 1; i < 100;i++)initTicket.sale();},"售票员A").start();
        new Thread(()->{for (int i = 1; i < 100;i++)initTicket.sale();},"售票员B").start();
        new Thread(()->{for (int i = 1; i < 100;i++)initTicket.sale();},"售票员C").start();
    }
}
