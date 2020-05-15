package Reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class SoftReferenceDemo {
  public static void main(String[] args) throws InterruptedException {
    /**
     * 默认Object o1 这种方式是强引用
     * o1变量存放的是  new Object()分配的内存空间的地址
     */
    Object o1 = new Object();
    ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

    /**
     * 使用弱引用的方式
     * reference变量存放的是new SoftReference<>()括号中对象的内存地址
     * 是以一种特殊的方式记录
     * 在heap空间不足的时候reference变量会被置空，也就是释放
     */
    /**
     * 软引用也可以在其引用的对象被回收前
     * 将软引用添加到队列中去
     */
    SoftReference<Object> reference = new SoftReference<>(o1,referenceQueue);
    /**
     * o1不再引用对象
     * 使得只有一个软引用reference 引用了对象
     */
    o1 = null;
    /**
     * 强行调用垃圾回收模拟内存不足的情况
     */
    System.gc();
    /** 观察现在只有reference 引用的对象是否被回收 */
//    for(int i = 3 ;i > Integer.MIN_VALUE;i--){
//
//      System.out.println("******************");
//      Object reference2 = new byte[i * 1024 * 1024];
//      Thread.sleep(1000);
//      System.out.println(reference.get());
//    }
    System.out.println(reference.get());
    System.out.println(referenceQueue.poll());
  }
}
