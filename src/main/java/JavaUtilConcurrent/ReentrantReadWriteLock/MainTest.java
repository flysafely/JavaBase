package JavaUtilConcurrent.ReentrantReadWriteLock;

public class MainTest {
    public static void main(String[] args) {
        SourcesClass sourcesClass = new SourcesClass();

        new Thread(()->{
            for (int i = 0; i < 31;i++){
                sourcesClass.put("第" + i + "个值", i*100);
            }
        },"AAA").start();

        new Thread(()->{
            for (int i = 0; i < 31;i++){
                sourcesClass.get("第" + i + "个值");
            }
        },"BBB").start();
    }
}
