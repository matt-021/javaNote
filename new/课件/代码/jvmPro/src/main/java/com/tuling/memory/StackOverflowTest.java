package com.tuling.memory;

/**
 * @author 白起老师
 */
// JVM设置  -Xss128k(默认1M)
public class StackOverflowTest {

    static int num = 0;

    static void repeat() {
        num++;
        repeat();
    }

    //num:23628

    public static void main(String[] args) {
        try {
            repeat();
        } catch (Throwable t) {
            t.printStackTrace();
            System.out.println("num:"+num);
        }
    }
}