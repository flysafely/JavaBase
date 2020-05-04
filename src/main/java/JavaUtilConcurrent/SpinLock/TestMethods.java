package JavaUtilConcurrent.SpinLock;

public class TestMethods {
    ReentrantSpinLock reentrantSpinLock = new ReentrantSpinLock();

    public void method1(){
        reentrantSpinLock.lock();
        for (int i = 0; i < 30;i++){
            System.out.println(Thread.currentThread().getName() + "线程执行method1:" + i);
        }
        method2();
        reentrantSpinLock.unlock();
    }

    public void method2(){
        reentrantSpinLock.lock();
        for (int i = 0; i < 30;i++){
            System.out.println(Thread.currentThread().getName() + "线程执行method2:" + i);
        }
    }

    NonReentrantSpinLock nonReentrantSpinLock = new NonReentrantSpinLock();

    public void method3(){
        nonReentrantSpinLock.lock();
        for (int i = 0; i < 30;i++){
            System.out.println(Thread.currentThread().getName() + "线程执行method3:" + i);
        }
        method4();
        nonReentrantSpinLock.unlock();
    }

    public void method4(){
        nonReentrantSpinLock.lock();
        for (int i = 0; i < 30;i++){
            System.out.println(Thread.currentThread().getName() + "线程执行method4:" + i);
        }
        nonReentrantSpinLock.unlock();
    }
}
