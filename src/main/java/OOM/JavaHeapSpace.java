package OOM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JavaHeapSpace {
  public static void main(String[] args) {
  	int i = 0;
  	List<Object> list = new ArrayList<>();
		try {
			while (true){
				list.add(String.valueOf(i).intern());//
//				Thread.sleep(100);
			}
		} catch (Exception e) {
      System.out.println("执行了" + i);
      e.printStackTrace();
		}
  }
}
