package JavaUtilConcurrent.ForkJoin;

import java.util.concurrent.RecursiveTask;

public class MyMainTask extends RecursiveTask<Integer> {
    private static final Integer ADJUST_VALUE = 10;
    private int begin;
    private int end;
    private int result;

    public MyMainTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (this.end - this.begin <= ADJUST_VALUE){
            for (int i = begin; i < end;i++){
                result = result + i;
            }
        }else {
            int middle = (end + begin)/2;
            MyMainTask task01 = new MyMainTask(begin,middle);
            MyMainTask task02 = new MyMainTask(middle + 1,end);
            task01.fork();
            task02.fork();
            result = task01.join() + task02.join();
        }
        return result;
    }
}
