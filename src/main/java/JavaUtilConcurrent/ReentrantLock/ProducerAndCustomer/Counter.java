package JavaUtilConcurrent.ReentrantLock.ProducerAndCustomer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    /**
     * 货架上现在有的商品
     */
    private Product ProductOnShelf;
    /**
     * CallBell就是柜台上的呼唤，一次只能有一个顾客按下
     * 一个店员一次也只能服务一个顾客
     */
    private Lock CallBell = new ReentrantLock();
    /**
     * PagerForBread就是店员发给需要购买面包的呼唤器
     * 等到面包考好，会呼唤顾客到柜台取面包
     */
    private Condition PagerForBread = CallBell.newCondition();
    /**
     * PagerForCafe就是店员发给需要购买咖啡的呼唤器
     * 等到咖啡泡好，会呼唤顾客到柜台取咖啡
     */
    private Condition PagerForCafe = CallBell.newCondition();

    /**
     * 初始化一个货架上的默认已经上架的商品
     * @param productOnShelf
     */
    public Counter(Product productOnShelf) {
        ProductOnShelf = productOnShelf;
    }

    public void saleBread(){
        /**
         * 店员开始接待需要购买面包的顾客
         */
        CallBell.lock();
        try {
            while(ProductOnShelf.name() != "BREAD"){
                PagerForBread.await();
            }
            System.out.println(Thread.currentThread().getName() + "买到了面包！");
            /**
             * 一定要先将咖啡上架，再出发呼唤器呼叫需要买咖啡的顾客
             */
            ProductOnShelf = Product.CAFE;
            /**
             * 咖啡已上架，呼唤需要买咖啡的顾客
             */
            PagerForCafe.signal();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            CallBell.unlock();
        }
    }

    public void saleCafe(){
        /**
         * 店员开始接待需要购买咖啡的顾客
         */
        CallBell.lock();
        try {
            while(ProductOnShelf.name() != "CAFE"){
                PagerForCafe.await();
            }
            System.out.println(Thread.currentThread().getName() + "买到了咖啡！");
            /**
             * 一定要先将面包上架，再出发呼唤器呼叫需要买面包的顾客
             */
            ProductOnShelf = Product.BREAD;
            /**
             * 面包已上架，呼唤需要买面包的顾客
             */
            PagerForBread.signal();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            CallBell.unlock();
        }
    }
}
