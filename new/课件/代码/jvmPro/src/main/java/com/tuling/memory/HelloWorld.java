package com.tuling.memory;

/**
 * @author 白起老师
 */
public class HelloWorld {

    public static  final   int   initPra = 1;
    public static Order order = new Order();

    public  int calc(){
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;

        return c;
    }

    public static void main(String[] args) {

        HelloWorld hw = new HelloWorld();
        int c = hw.calc();
        System.out.println(c);



    }
}
