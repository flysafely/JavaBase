package Generic;

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
