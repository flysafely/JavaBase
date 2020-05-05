package JavaUtilConcurrent.ReentrantReadWriteLock;

import JMM.AtomicClass.ObjectClass;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SourcesClass {
    private Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void put(String key , Object value){
        rwlock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "线程正在写入--->" + key + "：" + value.toString());
            map.put(key,value);
            /**
             * 模拟操作时延，能够正常打印完整的写入情况
             */
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(Thread.currentThread().getName() + "线程已经写入完成！");
        }catch (Exception e){
            System.out.println(e);
        }finally {
            rwlock.writeLock().unlock();
        }

    }

    public void get(String key){
        rwlock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "线程正在读取--->" + key);
            Object result = map.get(key);
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(Thread.currentThread().getName() + "线程已经读取--->" + key + ":" + result.toString());
        }catch (Exception e){
            System.out.println(e);
        }finally {
            rwlock.readLock().unlock();
        }
    }
}
