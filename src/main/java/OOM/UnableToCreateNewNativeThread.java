package OOM;

/**
 * 存在过多的正在运行的线程
 * unable to create native thread: possibly out of memory or process/resource limits reached
 */
public class UnableToCreateNewNativeThread {
  public static void main(String[] args) {
		for (int i = 0; i < Integer.MAX_VALUE;i++){
      new Thread(
              () -> {
								try {
									System.out.println(Thread.currentThread().getName());
									Thread.sleep(Integer.MAX_VALUE);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							},
              String.valueOf(i))
          .start();
		}//
  }
}
