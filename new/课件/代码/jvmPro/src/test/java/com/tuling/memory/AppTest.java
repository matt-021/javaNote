package com.tuling.memory;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    public static void main(String[] args) {

        Order s = new Order();

        try {
            s.getClass().getClassLoader().loadClass("com.tuling.memory.T");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
