package JavaUtilConcurrent.SynchronizedUsage;

public class TestClass{
    byte[] lock = new byte[0];

    /**
     * 类对象锁和对象锁是锁的两个不同的对象，
     * 一个是锁的TestClass的Class对象，一个是锁的new 出来的TestClass类的实例对象
     * 所以不能保持同步，不会有竞态条件
     */
    public static synchronized void staticMethod(){
        for (int i = 0; i < 30;i++){
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

    public synchronized void normalMethod(){
        for (int i = 0; i < 30;i++){
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

    public void method(){
        synchronized (lock){
            for (int i = 0; i < 30;i++){
                System.out.println(Thread.currentThread().getName() + "正在运行:" + i);
            }
        }
    }

    public void method2(){
        for (int i = 0; i < 30;i++){
            System.out.println(Thread.currentThread().getName() + "正在运行:" + i + "~~~~");
        }
    }

    /**
     * 锁对象与锁对象的非静态成员变量并不会形成同步，两者并没有包含关系。
     * 即使this里包含content成员，但对于synchronized()来讲，
     * 是两个不同的输入对象或者说参数，二者不会同步
     *
     */
    public void method3(){
        synchronized (this) {
            for (int i = 0; i < 30; i++) {
                System.out.println(Thread.currentThread().getName() + "正在运行:" + i + "****");
            }
        }
    }
}
