package com.tuling.load;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @author 白起老师
 *
 * 自定义类加载器示例二
 * 再来一个沙箱安全机制示例，尝试打破双亲委派机制，用自定义类加载器加载我们自己实现的 java.lang.String.class
 *
 */
public class MyClassLoaderTest2 {


    static class MyClassLoader extends ClassLoader {

        private String classPath;

        public MyClassLoader(String classPath) {

            this.classPath = classPath;
        }

        private byte[] getByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;

        }

        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = getByte(name);
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }




    }

    public static void main(String args[]) throws Exception {
        MyClassLoader classLoader = new MyClassLoader("D:/test");


        Class clazz = classLoader.loadClass("com.tuling.load.User");
        System.out.println(clazz.getClassLoader());

        //尝试用自己改写类加载机制去加载自己写的java.lang.String.class
        //Class clazz = classLoader.loadClass("java.lang.String");



    }

}
