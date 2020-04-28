package JavaUtilConcurrent.ReentrantLock.ProducerAndCustomer;

public class Service {
    public static void main(String[] args) {
        Counter counter = new Counter(Product.CAFE);
        for (int i = 1; i < 5;i++){
            new Thread(counter::saleBread,"第" + i +"个要买面包的顾客").start();
        }
        for (int i = 1; i < 5;i++){
            new Thread(counter::saleCafe,"第" + i +"个要买咖啡的顾客").start();
        }

    }
}
