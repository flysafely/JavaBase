package JavaUtilConcurrent.ReentrantLock.ProducerAndCustomer;

import JavaUtilConcurrent.ReentrantLock.DirectBytebuffer;
import JavaUtilConcurrent.ReentrantLock.NonDirectBytebuffer;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class bcd {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        abc a = new abc();
//        new Thread(a::method,"AAAA").start();
//        new Thread(a::method2,"BBBB").start();

        FutureTask f1 = new FutureTask(new DirectBytebuffer());
        FutureTask f2 = new FutureTask(new NonDirectBytebuffer());
        new Thread(f1,"thread1").start();
        new Thread(f2,"thread2").start();
        System.out.println(f1.get());
        System.out.println(f2.get());

    }
}
