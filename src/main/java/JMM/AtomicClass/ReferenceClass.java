package JMM.AtomicClass;

import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ReferenceClass {
    public static AtomicReference<ObjectClass> atomicReference = new AtomicReference<>();

    public static void main(String[] args) throws InterruptedException {
        ObjectClass flysafely = new ObjectClass("flysafely",11);
        ObjectClass Mafioso = new ObjectClass("Mafioso",13);
        atomicReference.set(flysafely);
        new Thread(()->{
            try {
                atomicReference.compareAndExchange(flysafely,Mafioso);
                System.out.println(Thread.currentThread().getName() + "将原子引用由flysafely改为Mafioso");
                Thread.sleep(4000);
                System.out.println(Thread.currentThread().getName() + "将原子引用由Mafioso改为flysafely");
                atomicReference.compareAndExchange(Mafioso,flysafely);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();
        new Thread(()->{
            while (!atomicReference.weakCompareAndSetVolatile(flysafely, Mafioso)){
            };
            System.out.println(Thread.currentThread().getName() + "将原子引用由flysafely改为Mafioso");
        },"BBB").start();
        Thread.sleep(5000);
        System.out.println(atomicReference.get().name);
    }
}
