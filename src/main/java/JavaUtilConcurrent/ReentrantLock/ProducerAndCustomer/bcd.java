package JavaUtilConcurrent.ReentrantLock.ProducerAndCustomer;

public class bcd {
    public static void main(String[] args) {
        abc a = new abc();
        new Thread(a::method,"AAAA").start();
        new Thread(a::method2,"BBBB").start();
    }
}
