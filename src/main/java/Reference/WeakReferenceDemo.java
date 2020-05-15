package Reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        /**
         * 弱引用也可以在其引用的对象被回收前
         * 将弱引用添加到队列中去
         */
        WeakReference<Object> weakReference = new WeakReference<>(o1,referenceQueue);
      /**
       * 将o1 引用对象置为null说明 o1这个变量不再指向堆区的某个对象了
       * 但是堆区的那个对象 现在是否还有其他的引用 需要等到GC的时候才能知道
       */
        o1 = null;
//        for (; ; ) {
//            /**
//             * 通过软引用来引用到new出来的对象
//             * 每次循环都增加堆内的内存占用直到执行GC
//             * 软引用的对象就会
//             */
//            SoftReference<Object> reference = new SoftReference<>(new byte[1 * 1024 * 1024]);
//            Thread.sleep(1000);
//            /**
//             * 逐步增加内存占用来触发GC
//             * 来判断虚应用
//             */
//            System.out.println(weakReference.get());
//        }

      System.gc();
      /**
       * GC前判断出对象不存在强引用
       * 所以弱引用也会释放其引用的对象，让其被回收掉
       */
      System.out.println(weakReference.get());
      System.out.println(referenceQueue.poll());
  }
}
