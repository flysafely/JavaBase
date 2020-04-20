import BitOperation.BitOperation;
import Generic.Animal;
import Generic.Fruit;
import Generic.GenericClass;
import JavaClassLoadingMechanism.MyClassLoader;
import ParamterPassingValue.Bean;
import ParamterPassingValue.SwapTool;
import ProtectedModifier.ChildPackage.SonBean;
import SelfIncreasingVariable.SelfIncreasingVariableTest;
import Singleton.*;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
}
