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
    System.gc();

    System.out.println(o2);
  }
}
