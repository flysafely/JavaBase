package SelfIncreasingVariable;

/**
 * 测试自增变量中计算过程的相关问题
 * @author flysafely
 * @since 2020.4.15
 */
public class SelfIncreasingVariableTest {

    public static void Test_1(){
        //注意运行时栈帧、操作数栈，局部变量表的操作情况
        /**
         *  以下是计算过程的字节码
         *  先将常量1复制给局部变量中的i(是局部变量中的第一个变量下标为0)
         *  0 iconst_1
         *  1 istore_0
         *  等号右边的先计算，于是先将局部变量表中的i的值load到操作数栈中，然后局部变量中的
         *  i自增(此时i=2)，然后又将操作数栈的栈顶数从新存会到局部变量表中的第一个i变量中(此时i又被赋值为1)
         *  2 iload_0
         *  3 iinc 0 by 1
         *  6 istore_0
         *  将i的值加载到操作数栈中，局部变量表中的i自增为2，此时操作数栈的栈顶数为1，重新赋值给局部变量表中的
         *  第二位变量j=1
         *  7 iload_0
         *  8 iinc 0 by 1
         * 11 istore_1
         * 将i的值加载到操作数栈中，i此时为2，局部变量表中的i自增为3，又将i的值加载到操作数栈中，此时i为3，
         * 操作数栈中有两个数据2和3(栈顶)
         * 12 iload_0
         * 13 iinc 0 by 1
         * 16 iload_0
         * 将局部变量表中i=3的值加载到操作数栈中，此时操作数栈中有2、3、3三个值，局部变量表中的i又自增为4
         * 17 iload_0
         * 18 iinc 0 by 1
         * 栈顶两个数据(3,3)弹出进行乘法运算并把结果(3*3=9)又压入栈顶
         * 21 imul
         * 又将栈顶两个数2，9弹出，进行加法运算(2+9=11)后,将结果压入栈顶，最后将操作数栈中的栈顶值11重新赋值给
         * 局部变量表中的第三个变量k
         * 22 iadd
         * 23 istore_2
         */
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println("i= " + i);
        System.out.println("j= " + j);
        System.out.println("k= " + k);
    }
}
