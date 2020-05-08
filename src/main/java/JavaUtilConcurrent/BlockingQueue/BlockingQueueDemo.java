package JavaUtilConcurrent.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(3);
//        bq.add("a");
//        bq.add("b");
//        bq.add("c");
        System.out.println(bq.peek());
    }
}
