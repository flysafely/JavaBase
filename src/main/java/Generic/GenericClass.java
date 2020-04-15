package Generic;
/**
 * 这个类用来测试指定了泛型的类，它的方法中的泛型的限制情况
 * @author flysafely
 * @since 2020.4.14
 */
public class GenericClass<T> {
    public void show_1(T t){
        System.out.println("Method show_1 : " + t.toString());
    }

    public <E> void show_2(E e){
        System.out.println("Method show_2 : " + e.toString());
    }

    public <B> void show_3(B b){
        System.out.println("Method show_2 : " + b.toString());
    }
}
