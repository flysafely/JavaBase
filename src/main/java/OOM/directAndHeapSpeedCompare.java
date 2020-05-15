package OOM;


import java.nio.ByteBuffer;

public class directAndHeapSpeedCompare{

	private static void directExecuteTime(int length){
		long startTime=System.currentTimeMillis();
		ByteBuffer[] byteBufferArray = new ByteBuffer[length];
		for (int i = 0; i <length ; i++) {
			byteBufferArray[i]=ByteBuffer.allocateDirect(1024);
		}
		long endTime=System.currentTimeMillis();
		System.out.println("创建"+length+"个DirectByteBuffer所消耗的时间："+(endTime-startTime));
	}
	private static void heapExecuteTime(int length){
		long startTime=System.currentTimeMillis();
		ByteBuffer[] byteBufferArray = new ByteBuffer[length];
		for (int i = 0; i <length ; i++) {
			byteBufferArray[i]=ByteBuffer.allocate(1024);
		}
		long endTime=System.currentTimeMillis();
		System.out.println("创建"+length+"个HeapByteBuffer所消耗的时间："+(endTime-startTime));
	}
  public static void main(String[] args) {

		int length=1000000;
		directExecuteTime(length);
		heapExecuteTime(length);//
  }
}
