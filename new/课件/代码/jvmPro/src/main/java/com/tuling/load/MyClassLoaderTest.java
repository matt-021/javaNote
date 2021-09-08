package com.tuling.load;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @author 白起老师
 *
 * 自定义类加载器示例一
 * 自定义类加载器只需要继承 java.lang.ClassLoader 类，该类有两个核心方法，一个是loadClass(String, boolean)，实现了双亲委派机制，
 * 还有一个方法是findClass，默认实现是空方法，所以我们自定义类加载器主要是重写findClass方法。
 */
public class MyClassLoaderTest {

    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }


        //com.tuling.load.HelloWorld   ==>com/tuling/load/HelloWorld.class
        private byte[] getByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fileInputStream = new FileInputStream(classPath + "/" + name + ".class");
            int len = fileInputStream.available();
            byte[] data = new byte[len];
            fileInputStream.read(data);
            fileInputStream.close();
            return data;
        }

            protected Class<?> findClass(String name) throws ClassNotFoundException {
                    try {
                        byte[] data = getByte(name);
                        //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组。
                        return defineClass(name, data, 0, data.length);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new ClassNotFoundException();
                    }
        }

    }

    public static void main(String args[]) throws Exception {
        //初始化自定义类加载器，会先初始化父类ClassLoader，其中会把自定义类加载器的父加载器设置为应用程序类加载器AppClassLoader
        MyClassLoader classLoader = new MyClassLoader("D:/test");
        //D盘创建 test/com/tuling/jvm 几级目录，将User类的复制类User1.class丢入该目录
        Class clazz = classLoader.loadClass("com.tuling.load.User");
       // Object obj = clazz.newInstance();
        //调用类的say方法
        //Method method = clazz.getDeclaredMethod("say", null);
        //method.invoke(obj, null);
        System.out.println(clazz.getClassLoader().getClass().getName());
    }
}
