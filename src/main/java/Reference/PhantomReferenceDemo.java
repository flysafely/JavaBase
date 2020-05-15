package Reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceDemo {
  public static void main(String[] args) {
	  Object o1 = new Object();
	  ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
	  PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);

//	  o1 = null;
	  System.out.println("===========未发生GC时===========");
		/**
		 * 虚引用并不真正的引用到对象
		 * 只是标明该对象被回收时候的特殊性
		 * 被标明虚引用 引用的对象在回收之前将虚引用添加到ReferenceQueue中去
		 */
		System.out.println("通过get()获取虚引用指向的对象:" + phantomReference.get());
    System.out.println("referenceQueue队列中的数据:" + referenceQueue.poll());

		System.out.println("===========发生GC后===========");
		System.gc();
		System.out.println("通过get()获取虚引用指向的对象:" + phantomReference.get());
		/**
		 * 添加到队列的是虚引用(也是一个对象Object)，而不是虚引用所引用的对象
		 */
		System.out.println("referenceQueue队列中的数据:" + referenceQueue.poll());
  }
}
