package FunctionalInterface;

public class TestClass {

    private int num;

    public static void methodStatic(){
        System.out.println("methodStatic invoke!");
    }

    public void methodWithOutReturn(){
        System.out.println("methodWithOutReturn invoke!");
    }

    public String method(String param){
        System.out.println("method invoke!");
        return param;
    }

    public TestClass() {
        System.out.println("TestClass无参构造器");
    }

    public TestClass(int num){
        this.num = num;
    }
}
