package JMM.Visibility;

/**
 * 测试主内存中(对象中的属性的值)对象的可见性
 * ResourceClass类中的num属性所指向的值，存在于堆中(共享)
 */
public class VisibilityDemo {
    public static void main(String[] args) {
        /**
         * 创建资源类
         */
        ResourcesClass resourcesClass = new ResourcesClass();
        /**
         * A线程将resourcesClass中的num修改
         */
        new Thread(()->{
            /**
             * 该线程先暂停，等待B线程确定已经跑起
             */
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /**
             * 修改资源类的中的属性值
             */
            resourcesClass.ChangeNum(1024);
            System.out.println(Thread.currentThread().getName() + "已经将num修改为1024！");
            },"A线程").start();
        new Thread(()->{
            /**
             * 因为线程中的操作对象变量是通过先复制到线程私有的工作内存中来
             * 只要B线程启动，resourcesClass.num这句话就会从对象resourcesClass中复制
             * 如果A线程改变了resourcesClass中num的值但是，B线程中还是拿着的原来复制的值
             * 并没有发生改变
             *
             * 如果ResoucesClass类中的num属性通过volatile关键字修饰
             * 作用相当于将num属性置为多线程中可见，num变化后，B线程就能知道num发生了变化
             */
            while (resourcesClass.num == 10){

            }
            System.out.println(Thread.currentThread().getName() + "已经获取到num的值已经为" + resourcesClass.num + "的消息!");
        },"B线程").start();

    }

}
