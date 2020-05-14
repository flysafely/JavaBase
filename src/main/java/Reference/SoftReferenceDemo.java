package Reference;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {
  public static void main(String[] args) {
    Object o1 = new Object();
    SoftReference<Object> reference = new SoftReference<>(new Object());
    System.out.println(reference.get());
    System.out.println(o1);
  }
}
