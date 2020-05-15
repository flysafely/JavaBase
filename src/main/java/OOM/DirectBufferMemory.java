package OOM;

import java.nio.ByteBuffer;

public class DirectBufferMemory {
  public static void main(String[] args) {
		/**
		 * 在堆中创建一个字节buffer受GC管理
		 */
		ByteBuffer b = ByteBuffer.allocate(6*1024*1024);
    System.out.println(b);
		/**
		 * 在直接内存中创建一个buffer不受GC管理
		 */
		ByteBuffer bb = ByteBuffer.allocateDirect(6*1024*1024);
  }


}
