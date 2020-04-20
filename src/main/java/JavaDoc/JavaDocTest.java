package JavaDoc;

/**
 * 这个类用来测试javadoc文档解析功能
 * @author flysafely
 * @since 2020.4.15
 */
public class JavaDocTest {

    private String param_1;
    private static final String param_2 = "final static string!";
    protected void test(){};
    /**
     * javaDocTest_1方法是JavaDocTest类中的一个测试方法
     */
    public void javaDocTest_1(){
        System.out.println("这是一个测试方法!");
    }

    /**
     * javaDocStaticTest_1方法是JavaDoc中的一个静态方法
     */
    public static void javaDocStaticTest_1(){
        System.out.println("这是一个静态类方法!");
    }

}
