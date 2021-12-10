package com.wmz.test;

import java.io.Serializable;

/**
 * @Author wangmingzhen
 * @Date 21/12/10 15:57
 */
public class KeyWord {

    private int num0=0;

    /**
     * static关键字，静态
     * 静态变量：可跨代阿门访问
     * 静态方法：可跨代阿门访问
     * 代码块：只能定义在类顶一下，在被加载时执行
     * 内部类：该类定义可以由外部类名引用
     */
    static int num1 = 5;

    /**
     * final关键字，不可变
     * 属性：值不可变，修饰对象属性时，表示引用不可变，对象不可变
     * 方法：方法不可覆盖
     * 类：类不可被继承（不能在派生出新的子类）
     */
    final int num2 = 5;

    /**
     * volatile关键字，直接访问
     * 仅修饰会被多线程访问的属性，表示直接操作内存，不经过cache拷贝（使其他CPU核心里引用了该地址的数据编程脏数据）
     * 实现基于内存栅栏（Memory Barrier），读取时jvm会多执行一个Read-Barrier指令，如果该数据已变脏，会从主存中重新获取数据
     */
    volatile int num3 = 5;

    /**
     * transient关键字，不会被序列化
     */
    transient int num4 = 5;

}

class SubKeyWord extends KeyWord implements Serializable {

    transient int num5 = 5;
    private int num6 = 5;
    public int num7 = 5;
}
