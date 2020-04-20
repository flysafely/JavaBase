package JavaClassLoadingMechanism;

public class ClassLoading {
    public static String name = "flysafely";
    public static int age = 33;

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(ClassLoading.class.getClassLoader().getParent());
    }
}
