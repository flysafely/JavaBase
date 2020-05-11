package JavaUtilSteam.Steam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SteamDemo {
    public static void main(String[] args) {
        User u1 = new User("fly",1,23);
        User u2 = new User("abc",2,24);
        User u3 = new User("cde",3,25);
        User u4 = new User("myn",4,26);
        User u5 = new User("mny",5,27);

        List<User> userList = Arrays.asList(u1,u2,u3,u4,u5);
        /**
         * 集合数组可以转换为流
         */
        List<String> list2 = new ArrayList<>();
        list2 = userList.stream()
                /**
                 * filter()方法是通过Predicate()函数式接口中的具体方法
                 * 筛选出符合条件的流数据中的数据(此时筛选后的结果是User对象)
                 */
                .filter(user -> {return user.getId() % 2 == 0;})
                .filter(user -> {return user.getAge() >= 24;})
                /**
                 * map()方法执行后只剩下getName得到的具体属性数据在流里面了
                 */
                .map(un -> {return un.getName().toUpperCase();})
                /**
                 * sorted()中需要一个Comparator的函数式接口，其中有一个抽象方法int compare(T o)
                 * 函数式接口可以通过lambda表达式来指定一个带方法实现的匿名实现类
                 * {}中的函数体就是compare()的具体实现方法
                 * 调用String类型的compareTo方法可以返回一个int返回值用于字符串比较
                 */
                .sorted((o1,o2) -> {return o2.compareTo(o1);})
                /**
                 * 可以将流再转换成list对象
                 */
                .collect(Collectors.toList());

//                .forEach(System.out::println);
        System.out.println(list2);
    }
}
