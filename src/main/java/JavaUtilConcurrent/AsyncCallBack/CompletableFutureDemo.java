package JavaUtilConcurrent.AsyncCallBack;

import java.sql.Time;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 异步回调
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        /**
         * Void 是void的包装类型，是一个类
         * CompletableFuture<Void>代表了没有返回值
         */
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            System.out.println(Thread.currentThread().getName() + "线程是没有返回值");
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        /**
         * Integer 代表了completableFuture2的返回值类型为Integer
         */
        CompletableFuture<Integer> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "线程:is working!!!");
                TimeUnit.SECONDS.sleep(6);//模拟main线程中completableFuture1执行中还没有得到结果，随后main线程中启动completableFuture2
                System.out.println(Thread.currentThread().getName() + "线程:执行完毕!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1024;
        });

        //第二个异步回调在main线程中被调用启动
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "线程:is working!!!");
            for (int i = 0; i < 3;i++){
                try {
                    System.out.println(Thread.currentThread().getName() + "线程:正在执行第" + i +"步");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int num = 10/0;
            return 1024;
        });

        /**
         *  BiConsumer<? super T, ? super Throwable>是一个Consumer式的函数式接口
         *  它是一个需要两个参数的函数式接口：
         *      第一个参数类型是CompletableFuture<T>泛型T指定的返回值类型
         *      第二个参数类型是一个Thorwable类型
         *      .whenComplete() 直接在括号里面写BiConsumer函数式接口的lambda表达式
         *
         *     public CompletableFuture<T> whenComplete(
         *         BiConsumer<? super T, ? super Throwable> action) {
         *         return uniWhenCompleteStage(null, action);
         *     }
         */
        System.out.println(completableFuture2.whenComplete((r, t) -> {
            /**
             * 10/0运行时异常
             * .whenComplete()方法需要两个参数
             *      第一个是completableFuture2正常运行后的结果返回值
             *      第二个是获取异常对象
             *    注意:
             *      1.如果成功获取了运行后返回值，就不会抛出异常对象
             *      2.如果未能成功运行，出现异常后，就会抛出异常对象，但是此时没有处理异常的相关方法来接收异常使得程序能够正常结束
             *        此时调用.get()方法会直接终止程序抛出异常
             * .exceptionally()方法就是将抛出的异常对象放入到一个Function函数式接口中进行处理后，将一个处理后的返回结果
             *  交给completableFuture2，可以通过get()获取
             *
             */
            System.out.println("*****result:" + r);//返回值就是用形参r来接收
            System.out.println("*****thorwbable:" + t);//抛出类型就是用形参t来接收
        }).exceptionally(a -> {
            System.out.println(a.getMessage());
            return 404;
        }).get());
        //尝试获取completablefuture1的结果
        try {
            System.out.println("*******" + completableFuture1.whenComplete((r, t) -> {
                System.out.println("completableFuture1结果是:" + r);
                System.out.println("completableFuture1异常是:" + t);
            }).exceptionally(t -> {
                System.out.println("出现异常了！"+ t);
                return 404;
            }).get(9,TimeUnit.SECONDS));
        }catch (Exception e){
            System.out.println("等待超时!");
        }
    }
}
