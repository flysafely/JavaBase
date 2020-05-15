package Reference;

import java.lang.ref.Reference;

public class ReferenceDemo {
  public static void main(String[] args) {
      /**
       * 默认 类名 变量名 = .... 都是定义的强引用
       */
    Object o1 = new Object();
    Object o2 = o1;

    o1 = null;

    System.out.println(o1);
    /**
     * 模拟内存不足执行GC
     */
    System.gc();
    /**
     * 强引用在任何情况下都不会释放
     * 内存满了也直接抛出OOM
     */
    System.out.println(o2);
  }
}
