package FunctionalInterface;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class MainTest {
    public static void main(String[] args) throws Exception {
        List<String> a = Arrays.asList("1","2","3");
        /**
         * 函数式接口的使用:
         *  等号右边是lambda表达式：
         *      ()中的入参数量必须跟函数式接口中的唯一方法的入参数量一致
         *          ->
         *      {}方法体中接收参数进行处理
         *  最后的lambda表达式一个整体 赋值给fi变量 相当于给了函数式接口FuncInterface中的method方法创建了一个具体的实现类
         *  实现类的实例对象就是fi，实现类中method的具体实现方法就是lambda表达式
         *  此后就可以通过调用fi对象的method方法来实现具体的操作
         */
        FuncInterfaceWithOneParam fi1 = (param1) -> {
            System.out.println(param1 + ":已处理");
            //FuncInterfaceWithOneParam 有返回值
            return param1 + ":已处理";
        };

        a.forEach((param1) -> {fi1.method1(param1);});
        //以下代码跟上一行一致
        /**
         * 需要注意的是：
         * 1.虽然fi1的method1方法是一个有返回值的lambda表达式
         *   但是对于forEach来说fi1::method1这样的简写就相当于
         *   a.forEach((param1) -> {
         *      fi1.method1(param1);
         *   });
         *   也是一个lambda表达式，虽然fi1.method1(param1)有返回值，但是在这一段lambda表达式中并没有return
         *   相当于定义了一个Consumer的匿名实现类
         *   (param1) -> {
         *     fi1.method1(param1);
         *   }
         *   以上这段lambda表达式就是这个匿名实现类的accept方法的具体实现
         */
        a.forEach(fi1::method1);

        FuncInterfaceWithTwoParam fi2 = (param1,param2) -> {
            String dealstring = param1 + "," + param2 +":已处理";
            System.out.println(dealstring);
        };

        /**
         * foreach方法中调用的方法 有异常抛出，不能通过jdk8的双冒号语法糖调用
         */

        fi2.method1("test!","a");
        TestClass testClass = new TestClass();
        FuncInterfaceWithOneParam funcInterfaceWithOneParam = testClass::method;
    }
}
