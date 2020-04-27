package JavaUtilConcurrent.SynchronizedUsage.ProducerAndCustomer;

import javax.crypto.interfaces.PBEKey;
import javax.swing.*;

public class Switcher {
    public SwitcherStatus Status = SwitcherStatus.CLOSE;

    public synchronized void Open() throws InterruptedException {
        while (Status.name() != "CLOSE"){
            /**
             * 判断是否开关状态是否为关闭
             * 如果不是关闭状态就将进来线程阻塞在
             * this.wait()语句
             */
            this.wait();
        }
        System.out.println("原状态:" + Status.name());
        Status = SwitcherStatus.OPEN;
        System.out.println(Thread.currentThread().getName() + ":" + Status.name());
        this.notifyAll();
    }

    public synchronized void Close() throws InterruptedException {
        while (Status.name() != "OPEN"){

            this.wait();
        }
        System.out.println("原状态:" + Status.name());
        Status = SwitcherStatus.CLOSE;
        System.out.println(Thread.currentThread().getName() + ":" + Status.name());
        this.notifyAll();
    }

    public static void main(String[] args) {
        Switcher switcher = new Switcher();
        new Thread(()->{
            for (int i = 1; i < 10;i++){
                try {
                    Thread.sleep(400);
                    switcher.Open();
                }catch (Exception e){

                }
            }
        },"Thread 1").start();

        new Thread(()->{
            for (int i = 1; i < 10;i++){
                try {
                    Thread.sleep(400);
                    switcher.Close();
                }catch (Exception e){

                }
            }
        },"Thread 2").start();

        new Thread(()->{
            for (int i = 1; i < 10;i++){
                try {
                    Thread.sleep(400);
                    switcher.Close();
                }catch (Exception e){

                }
            }
        },"Thread 3").start();

        new Thread(()->{
            for (int i = 1; i < 10;i++){
                try {
                    Thread.sleep(400);
                    switcher.Close();
                }catch (Exception e){

                }
            }
        },"Thread 4").start();
    }
}
