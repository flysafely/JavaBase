package JMM.AtomicClass;

import java.util.concurrent.atomic.AtomicInteger;

public class IntegerAtomic {
    /**
     * 只有一个共享变量的情况下可以使用CAS原理实现的原子类来保证原子性操作
     */
    AtomicInteger atomicInteger = new AtomicInteger(100);
    public static void main(String[] args) {
        IntegerAtomic integerAtomic = new IntegerAtomic();

        new Thread(()->{
            for (int i = 1; i < 101;i++){
                integerAtomic.atomicInteger.getAndIncrement();
            } },"AAA").start();
        new Thread(()->{
            for (int i = 1; i < 101;i++){
                integerAtomic.atomicInteger.getAndIncrement();
            } },"BBB").start();

        System.out.println(integerAtomic.atomicInteger);
    }

    /**
     * CAS原理
     * 这段代码是getAndIncrement()中getAndAddInt()方法的源代码
     * 主要是实现原子整形类的自增操作
     * @param o  代表了实例对象
     * @param offset 代表了实例对象在内存中的偏移量，也就是内存地址
     * @param delta 代表了自增值
     * @return
     */
    /*
    public final int getAndAddInt(Object o, long offset, int delta) {
        int v;
        do {
            //获取到主内存中实例对象在内存地址上的实际值v
            v = getIntVolatile(o, offset);
            //weakCompareAndSetInt 底层是通过compareAndSetInt的一个本地方法原子性的进行比较后再交换的操作
              同时只能有一个线程操作，每个线程在获取到v的时候，调用到compareAndSetInt方法，在多线程的环境下
              可能存在其他B线程先于A程执行到了compareAndSetInt，并且将增加后的值成功写入了主内存中，此时A线程
              拿着自己获取的v，执行compareAndSetInt方法和主内存的值比较发现不一致，则回到循环中继续获取最新的v
              再进行compareAndSetInt操作，直到更新主内存的值成功
        } while (!weakCompareAndSetInt(o, offset, v, v + delta));
        return v;
    }
     */

}
