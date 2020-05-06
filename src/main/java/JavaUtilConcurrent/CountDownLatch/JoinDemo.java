package JavaUtilConcurrent.CountDownLatch;

import java.util.ArrayList;
import java.util.List;

public class JoinDemo implements Runnable{
    @Override
    public void run() {
        try {
            System.out.println("线程" + Thread.currentThread().getName() + "正在执行!");
            Thread.sleep(2000);
            System.out.println("线程" + Thread.currentThread().getName() + "执行完毕!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * 新建多线程对象
         */
        List<Thread> thlist = new ArrayList<>();
        for (int i = 0; i < 30;i++){
            thlist.add(new Thread(new JoinDemo(), String.valueOf(i)));
        }


        /**
         * start方法是实例方法没有异常抛出，可以直接使用双冒号调用
         *
         * 两种写法等价
         * foreach中接收一个Consumer<? super E> action的入参
         *     Consumer函数式接口中的抽象方法accept(E e)的参数就是lambda表达式中参数th的类型所限制的
         *     又因为th的类型就是 E，所以Consumer<? super E>的含义就是 一个Consumer函数式接口的accept方法所需要的参数是Thread类或者其父类
         *     双冒号的写法前后含义就是：
         *        Thread代表foreach中的每个对象的类型
         *        starat代表foreach中每个对象的实例方法
         *        不是一个静态方法的引用
         */
        thlist.forEach((th)->{th.start();});
//        thlist.forEach(Thread::start);
        /**
         * 两种写法等价
         * 注意和上面的区别
         *     一个foreach遍历的对象作为System.out.println()方法中的参数
         *     上面foreach遍历的对象作为需要被调用start实例方法的对象
         *     双冒号的写法前后含义就是：
         *        System.out代表这个类
         *        println代表了System.out的一个静态方法
         *        是一个正常的静态方法的引用
         *
         */
        thlist.forEach((th)->{ System.out.println(th);});
//        thlist.forEach(System.out::println);


        /**
         * 需要抛出异常的方法，不能使用双冒号的方式
         */
        thlist.forEach((th) -> {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        System.out.println("main线程执行完毕!");
    }
}
