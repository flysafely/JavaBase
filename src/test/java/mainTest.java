import BitOperation.BitOperation;
import Generic.Animal;
import Generic.Fruit;
import Generic.GenericClass;
import GetClassObjectMethod.Person;
import JavaClassLoadingMechanism.MyClassLoader;
import JavaUtilConcurrent.ReentrantLock.Ticket;
import ParamterPassingValue.Bean;
import ParamterPassingValue.SwapTool;
import ProtectedModifier.ChildPackage.SonBean;
import SelfIncreasingVariable.SelfIncreasingVariableTest;
import Singleton.*;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class mainTest {
    @Test
    public void GenericTest_1(){
        GenericClass<Fruit> testClass = new GenericClass<>();
        Fruit fruit = new Fruit();
        Fruit apple = new Fruit();
        Animal dog = new Animal();
        System.out.println("---------------------------------");
        testClass.show_1(fruit);
        testClass.show_1(apple);
        /*
         GenericClass<Fruit> testClass = new GenericClass<Fruit>() 限定了传入类一定只能是Fruit类或者子类
         show_1方法中只能接受类型为Fruit的参数，所以传入其他类别会在编译之前报错
         */
//        testClass.show_1(dog);
        System.out.println("---------------------------------");
        testClass.show_2(fruit);
        testClass.show_2(apple);
        /*
         show_2方法前面的<T>是给这个方法级别指定泛型 是给这个方法重新设置了一个泛型要求，这里的泛型要求不会因为GenericClass类
         的泛型要求GenericClass<Fruit> testClass = new GenericClass<Fruit>() 为Fruit而无法接受其他类型的参数，可以理解
         为如果没有单独指定方法的泛型级别，方法中的泛型由类的泛型级别控制，如果有设置方法的泛型级别，则以方法的泛型级别为准！
         */
        testClass.show_2(dog);
        System.out.println("---------------------------------");
        testClass.show_3(fruit);
        testClass.show_3(apple);
        /*
         可以在使用 类中重设泛型级别的方法 的时候在方法名前<Animal>显示的指定传入方法的入参类型
         */
        testClass.<Animal>show_3(dog);
    }

    @Test
    public void SelfIncreasingVariable(){
        SelfIncreasingVariableTest.Test_1();
    }

    @Test
    public void BitOperation(){
        BitOperation.BitOperationTest_1();
    }

    @Test
    public void SingletonHungryTest(){
        SingletonHungryDemo object1 = SingletonHungryDemo.getSingletonDemo();
        SingletonHungryDemo object2 = SingletonHungryDemo.getSingletonDemo();
        System.out.println("对比一下object1等于object2：" + (object1 == object2));
    }

    @Test
    public void SingletonLazyTest(){
        SingletonLazyDemo object1 = SingletonLazyDemo.getSingletonLazyDemo();
        SingletonLazyDemo object2 = SingletonLazyDemo.getSingletonLazyDemo();
        System.out.println("对比一下object1等于object2：" + (object1 == object2));
    }

    /**
     * 使用多线程测试单利模式的线程安全性
     * 饿汉、懒汉、懒汉加锁，懒汉加双重校验，懒汉(静态内部类),枚举单例
     */
    @Test
    public void MutilThreadTest(){
        for (int i = 0; i < 1000 ; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "--->" + SingletonStaticInnerClassDemo.getInstance().toString().split("@")[1]);
            },"线程：" + i).start();
            new Thread(() -> {
                System.out.println("111");
            },"线程名称").start();
        }
    }



    /**
     * 通过反射机制来检测枚举单例模式
     */
    @Test
    public void SingletonReflectionTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        SingletonEnumDemo singleton1=SingletonEnumDemo.INSTANCE;
        SingletonEnumDemo singleton2=SingletonEnumDemo.INSTANCE;
        System.out.println("正常情况下，实例化两个实例是否相同："+(singleton1==singleton2));
        /**
         * 通过反射创建一个枚举类型的单例
         */
        Constructor<SingletonEnumDemo> constructor= null;
        constructor = SingletonEnumDemo.class.getDeclaredConstructor(String.class,int.class);//其父类的构造器
        constructor.setAccessible(true);
        SingletonEnumDemo singleton3= null;
        /**
         * 会直接报错
         * java.lang.IllegalArgumentException: Cannot reflectively create enum objects
         * 枚举类型的单例模式直接避免了反射攻击
         */
        singleton3 = constructor.newInstance("testInstance",66);
        System.out.println(singleton1+"\n"+singleton2+"\n"+singleton3);
        System.out.println("通过反射攻击单例模式情况下，实例化两个实例是否相同："+(singleton1==singleton3));
    }

    /**
     * 通过序列化来检测枚举单例模式
     */
    @Test
    public void SingletonSerializableTest() throws IOException, ClassNotFoundException {
        SingletonEnumDemo s = SingletonEnumDemo.INSTANCE;
        s.setContent("枚举单例序列化");
        System.out.println("枚举序列化前读取其中的内容："+s.getContent());
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("SerEnumSingleton.obj"));
        oos.writeObject(s);
        oos.flush();
        oos.close();

        FileInputStream fis = new FileInputStream("SerEnumSingleton.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        SingletonEnumDemo s1 = (SingletonEnumDemo)ois.readObject();
        ois.close();
        System.out.println(s+"\n"+s1);
        System.out.println("枚举序列化后读取其中的内容："+s1.getContent());
        System.out.println("枚举序列化前后两个是否同一个："+(s==s1));
    }

    @Test
    public void LoadingMechanismTest_1() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        MyClassLoader loader1 = new MyClassLoader("loader1");
        MyClassLoader loader2 = new MyClassLoader("loader2");
        /**
         * 这里是加载的Classpath下的类，实现类加载器是ApplicationClassloader
         */
        Class<?> clazz1 = loader1.loadClass("JavaClassLoadingMechanism.MyClass");
        /**
         * 这里是加载的Classpath下的类，实现类加载器是ApplicationClassloader
         * 同时AppClassloader是单例的，加载同一个类字节码最后生成的Class对象一定是相同的
         */
        Class<?> clazz2 = loader2.loadClass("JavaClassLoadingMechanism.MyClass");

        System.out.println( clazz1 == clazz2);

        Object object1 = clazz1.getDeclaredConstructor().newInstance();
        Object object2 = clazz2.getDeclaredConstructor().newInstance();

        Method method = clazz1.getMethod("setMyClass", Object.class);
        method.invoke(object1, object2);
    }

    /**
     * ProtectedModifier测试基类和子类不同包下protected修饰符的成员变量和方法的访问问题
     */
    @Test
    public void testProtected(){
        SonBean sonBean = new SonBean();
        /**
         * 基类和子类不在同一个package中
         * 子类的实例不能调用基类中的protected成员和方法！
         */
//        sonBean.protected_func();

    }

    /**
     * ParamterPassingValue参数到底是传值还是传引用
     */
    @Test
    public void testParamterPass(){
        Bean a = new Bean("BeanA", 1);
        Bean b = new Bean("BeanB",2);
        System.out.println("交换前\na:"+a+"\nb:"+b);
        SwapTool.swap(a, b);
        System.out.println("交换后\na:"+a+"\nb:"+b);
    }

    /**
     * 三种获取类Class对象的方法的区别
     * 特别注意：字面量.class的方式获取的类对象
     *         1.如果该类在其他地方已经加载过(类已经初始化)那么.class获取到的类对象就是已经初始化过的Class对象
     *         2.如果该类是第一次加载，这种方式只会执行加载、链接两个步骤，不会进行类的初始化工作
     *         Class.forName(false)的方式
     *         1.如果该类在其他地方已经加载过(类已经初始化)那么Class.forName(false)获取到的类对象就是已经初始化过的Class对象
     *         2.如果该类第一次加载，这种方式只会执行加载、链接(不执行解析)，不会进行类的初始化工作
     * @throws Exception
     */
    @Test
    public void testClassObjectMethod() throws Exception {
        /**
         * 不执行类的初始化
         */
        System.out.println("Class p = Person.class");
        Class<?> p = Person.class;
        /**
         * 不执行类的初始化
         */
        System.out.println("Class.forName(false)");
        Class<?> a = Class.forName("GetClassObjectMethod.Person",false,this.getClass().getClassLoader());
        /**
         * 执行类的初始化
         */
        System.out.println("Class.forName(true)");
        Class<?> b = Class.forName("GetClassObjectMethod.Person");
        /**
         * 不执行类的初始化
         */
        System.out.println("ClassLoader.loadClass");
        Class<?> c = this.getClass().getClassLoader().loadClass("Person");
    }

    @Test
    public void test(){
        /*** 输出字符串 ***/
        // %s表示输出字符串，也就是将后面的字符串替换模式中的%s
        System.out.printf("%s", new Integer(1212));
        // %n表示换行
        System.out.printf("%s%n", "end line");
        // 还可以支持多个参数
        System.out.printf("%s = %s%n", "Name", "Zhangsan");
        // %S将字符串以大写形式输出
        System.out.printf("%S = %s%n", "Name", "Zhangsan");
        // 支持多个参数时，可以在%s之间插入变量编号，1$表示第一个字符串，3$表示第3个字符串
        System.out.printf("%1$s = %3$s %2$s%n", "Name", "san", "Zhang");

        /*** 输出boolean类型 ***/
        System.out.printf("true = %b; false = ", true);
        System.out.printf("%b%n", false);

        /*** 输出整数类型***/
        Integer iObj = 342;
        // %d表示将整数格式化为10进制整数
        System.out.printf("%d; %d; %d%n", -500, 2343L, iObj);
        // %o表示将整数格式化为8进制整数
        System.out.printf("%o; %o; %o%n", -500, 2343L, iObj);
        // %x表示将整数格式化为16进制整数
        System.out.printf("%x; %x; %x%n", -500, 2343L, iObj);
        // %X表示将整数格式化为16进制整数，并且字母变成大写形式
        System.out.printf("%X; %X; %X%n", -500, 2343L, iObj);

        /*** 输出浮点类型***/
        Double dObj = 45.6d;
        // %e表示以科学技术法输出浮点数
        System.out.printf("%e; %e; %e%n", -756.403f, 7464.232641d, dObj);
        // %E表示以科学技术法输出浮点数，并且为大写形式
        System.out.printf("%E; %E; %E%n", -756.403f, 7464.232641d, dObj);
        // %f表示以十进制格式化输出浮点数
        System.out.printf("%f; %f; %f%n", -756.403f, 7464.232641d, dObj);
        // 还可以限制小数点后的位数
        // %m.nf m表示显示宽度(包含小数点)，实际数字宽度小于m，则左侧用空格补齐(默认是右对齐)
        System.out.printf("%.1f; %-13.3f; %f%n", -756.403f, 7464.232641d, dObj);

        /*** 输出日期类型***/
        // %t表示格式化日期时间类型，%T是时间日期的大写形式，在%t之后用特定的字母表示不同的输出格式
        Date date = new Date();
        long dataL = date.getTime();
        // 格式化年月日
        // %t之后用y表示输出日期的年份（2位数的年，如99）
        // %t之后用m表示输出日期的月份，%t之后用d表示输出日期的日号
        System.out.printf("%1$ty-%1$tm-%1$td; %2$ty-%2$tm-%2$td%n", date, dataL);
        // %t之后用Y表示输出日期的年份（4位数的年），
        // %t之后用B表示输出日期的月份的完整名， %t之后用b表示输出日期的月份的简称
        System.out.printf("%1$tY-%1$tB-%1$td; %2$tY-%2$tb-%2$td%n", date, dataL);

        // 以下是常见的日期组合
        // %t之后用D表示以 "%tm/%td/%ty"格式化日期
        System.out.printf("%1$tD%n", date);
        //%t之后用F表示以"%tY-%tm-%td"格式化日期
        System.out.printf("%1$tF%n", date);

        /*** 输出时间类型***/
        // 输出时分秒
        // %t之后用H表示输出时间的时（24进制），%t之后用I表示输出时间的时（12进制），
        // %t之后用M表示输出时间的分，%t之后用S表示输出时间的秒
        System.out.printf("%1$tH:%1$tM:%1$tS; %2$tI:%2$tM:%2$tS%n", date, dataL);
        // %t之后用L表示输出时间的秒中的毫秒
        System.out.printf("%1$tH:%1$tM:%1$tS %1$tL%n", date);
        // %t之后p表示输出时间的上午或下午信息
        System.out.printf("%1$tH:%1$tM:%1$tS %1$tL %1$tp%n", date);

        // 以下是常见的时间组合
        // %t之后用R表示以"%tH:%tM"格式化时间
        System.out.printf("%1$tR%n", date);
        // %t之后用T表示以"%tH:%tM:%tS"格式化时间
        System.out.printf("%1$tT%n", date);
        // %t之后用r表示以"%tI:%tM:%tS %Tp"格式化时间
        System.out.printf("%1$tr%n", date);

        /*** 输出星期***/
        // %t之后用A表示得到星期几的全称
        System.out.printf("%1$tF %1$tA%n", date);
        // %t之后用a表示得到星期几的简称
        System.out.printf("%1$tF %1$ta%n", date);

        // 输出时间日期的完整信息
        System.out.printf("%1$tc%n", date);
    }
    /**
     * JUC中的ReentrantLock锁的范例
     * 三个售票员
     */
    @Test
    public void testReentrantlLoak() throws InterruptedException {
        Ticket initTicket = new Ticket();
        new Thread(()->{for (int i = 1; i < 400;i++)initTicket.sale();},"售票员A").start();
        new Thread(()->{for (int i = 1; i < 400;i++)initTicket.sale();},"售票员B").start();
        new Thread(()->{for (int i = 1; i < 400;i++)initTicket.sale();},"售票员C").start();
        //
        Thread.sleep(10000);
    }

    /**
     * Collections集合工具中的支持并发，将线程不安全的Collection类转换成线程安全的
     */
    @Test
    public void testArrayList() throws InterruptedException {
//        Collection<Object> list = Collections.synchronizedCollection(new ArrayList<>());
        List<String> list = new ArrayList<>();
        for (int i = 300; i > 0;i--){
//            System.out.println(UUID.randomUUID().toString().substring(0,8));
            new Thread(()->{list.add(UUID.randomUUID().toString().substring(0,8));},String.valueOf(i)).start();
            System.out.println(list);
        }
        System.out.println(list);
    }
}
