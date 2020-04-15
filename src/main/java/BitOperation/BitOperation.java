package BitOperation;

/**
 * 本类用于测试位运算的几个运算符<<,>>,>>>
 * @author flysafely
 * @since 2020.4.15
 */
public class BitOperation {
    /**
     * 1.注意无符号位置操作只有  无符号右移 >>>
     *   意思是右移后，高位空缺的用0补充
     *   会出现负数右移后变成整数的情况
     *
     * 2.位移操作会出现超出显示范围的情况
     *   主要看源数据的类型
     *   int基础数据为32bit
     *   long为64bit
     */
    public static void BitOperationTest_1(){
        int leftBean = 8;
        int rightBean = 64;
        int rightBeanUnsigned = 64;
        int leftBeanUnsigned = -64;

        System.out.println(leftBean + "左移3位：" + (leftBean << 3));
        System.out.println(rightBean + "右移3位：" + (rightBean >> 3));
        System.out.println(rightBeanUnsigned + "无符号右移3位：" + (rightBeanUnsigned >>> 3));
        System.out.println(leftBeanUnsigned + "无符号右移3位：" + (leftBeanUnsigned >>> 3));
        //并没有无符号左移这个操作！！
//        System.out.println(leftBeanUnsigned + "无符号右移3位：" + (leftBeanUnsigned <<< 3));

    }
}
