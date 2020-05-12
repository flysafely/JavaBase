package JavaUtilConcurrent.ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyMainTask myMainTask = new MyMainTask(1,1000);
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myMainTask);
        System.out.println(myMainTask.get());
        forkJoinPool.shutdown();
    }
}
