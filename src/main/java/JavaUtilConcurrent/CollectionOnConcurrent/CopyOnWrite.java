package JavaUtilConcurrent.CollectionOnConcurrent;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 在JUC中提供了一个支持并发，多线程安全的集合类CopyOnWriteArrayList
 * 解决了ArrayList线程不安全的问题
 */
public class CopyOnWrite {
    public static void main(String[] args) {
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1; i < 30;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
