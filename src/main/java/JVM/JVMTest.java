package JVM;

import java.util.Random;

public class JVMTest {
    public static void main(String[] args) {

        System.out.println("堆最大内存:" + Runtime.getRuntime().maxMemory()/1024/1024 + "MB");
        System.out.println("堆默认内存:" + Runtime.getRuntime().totalMemory()/1024/1024 + "MB");

        /**
         * 直接创建一个超出设定内存的对象
         */
        byte[] bytes = new byte[40 * 1024 * 1024];

        /**
         * 通过不停累加字符串达到设定的内存大小
         */
        String str = "flysafely";
        while (true){
            str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999);
        }
        

    }
}
