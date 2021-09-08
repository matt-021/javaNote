package com.tuling.gc;

/**
 * @author 白起老师
 */
public class ReferenceCountingGc {


    private  static Object obj = new Object();

    Object instance = null;

    public static void main(String[] args) {
        ReferenceCountingGc objA = new ReferenceCountingGc();
        ReferenceCountingGc objB = new ReferenceCountingGc();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
    }
}