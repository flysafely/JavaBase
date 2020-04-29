package JavaUtilConcurrent.RunnableAndCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MainTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new CallableTestClass());
        new Thread(futureTask,"Tread1").start();
        System.out.println(futureTask.get());
    }
}
