package JavaUtilConcurrent.CyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()-> System.out.println("人员到齐!"));

    public static void main(String[] args) {
        for (int i = 0; i < 8;i++){
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + "已经到了!");
                    /**
                     * 主要实现dowait()方法
                         * 每调用一次await()
                         * 就会将构造函数中设置的parties值-1
                         * 也是直到Index==0了，开始执行cyclicbarrier设置的runnable接口的run方法
                     */
                    cyclicBarrier.await();
                    /**
                     * 如果index != 0 子线程后续的代码也不会执行，子线程阻塞
                     */
                    System.out.println(Thread.currentThread().getName() + "已落座开会...");
                }catch (Exception e){
                    System.out.println(e);
                }
            },"第" + i + "个人").start();
        }
    }

}
