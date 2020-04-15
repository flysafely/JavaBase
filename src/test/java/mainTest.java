import Generic.Animal;
import Generic.Fruit;
import Generic.GenericClass;
import SelfIncreasingVariable.SelfIncreasingVariableTest;
import org.junit.Test;

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
}
