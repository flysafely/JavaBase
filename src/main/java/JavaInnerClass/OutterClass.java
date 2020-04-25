package JavaInnerClass;

public class OutterClass {
    public static String OutterName;
    public String Non_staticName;
    public static void OutterFunc(){
        /**
         * 局部内部类
         */
        int num = 0;
        class LocalInnerClass{
            public void localInnerFunc(){
                //JDK8之前内部类不能访问外部类方法中的非final的修饰的变量，JDK8之后就可以了
                System.out.println(num);
            }
        }

        /**
         * 匿名类：最好的范例就是new 一个接口
         *       本身interface不能直接new，但是如果使用了new 接口名(){重写方法}
         *       花括号中就会要求将抽象的方法予以实现，此时new 接口名(){重写方法、自定义方法}
         *       这个整体就是一个没有手动定义名称也就是匿名的一个接口的实现类的实例对象，也就是匿名类的实例对象
         */
        new AnonymousClass(){
            @Override
            public void info() {
                System.out.println("匿名类");
            }
        }.info();//这里要求结尾为分号，new是一个执行语句，后面部分
    }
    /**
     * 成员内部类
     */
    class inner{
        public String innerName;
    }

    /**
     * 静态内部类
     * 只能获取外部类的静态成员
     */
    static class staticinner{
        void staticinnerFunc(){
            System.out.println(OutterName);
            OutterFunc();
            //不能访问非static的成员
//            System.out.println(Non_staticName);
        }
    }


}
