package JavaUtilConcurrent.RunnableAndCallable;

import java.util.concurrent.Callable;

public class CallableTestClass implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("正在运行CallableTestClass中的call方法");
        return "call result!";
    }
}
