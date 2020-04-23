package GetClassObjectMethod;

public class Person {
    public static       String staticParam_1     =        String.valueOf(1);
    public static       int    staticParam_2     =        Integer.MAX_VALUE;
    public static final String staticParam_3     =        "staticParam_3";
    public        final String staticParam_4;
    public              String NonStaticParam_1  =        String.valueOf(1);

    static {
        System.out.println("staticParam_1 已经初始化");
    }

    static class innerClass{
        static {
            System.out.println("innerClass static!");
        }

        {
            System.out.println("innerClass block!");
        }
    }

    public Person(String staticParam_4) {
        this.staticParam_4 = staticParam_4;
    }
}
