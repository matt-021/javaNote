package com.tuling.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 白起老师
 */
public class MemoryTest {

     byte[] arr = new byte[1024 * 25];//25KB

    public static void main(String[] args) throws InterruptedException {
        List<MemoryTest>  ms = new ArrayList<>();

        while(true){
            ms.add(new MemoryTest());
           Thread.sleep(20);
        }
    }
}
