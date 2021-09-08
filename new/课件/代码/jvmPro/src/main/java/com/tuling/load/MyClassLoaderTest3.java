package com.tuling.load;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @author 白起老师
 *
 * 模拟实现Tomcat的webappClassLoader加载自己war包应用内不同版本类实现相互共存与隔离
 */
public class MyClassLoaderTest3 {

    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] getByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name
                    + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;

        }

        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException
        {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();


                    if (c == null) {
                        // If still not found, then invoke findClass in order
                        // to find the class.
                        long t1 = System.nanoTime();

                        if(!name.startsWith("com.tuling")){
                            c = this.getParent().loadClass(name);
                        }else{
                            c = findClass(name);
                        }

                        // this is the defining class loader; record the stats
                        sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                        sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                        sun.misc.PerfCounter.getFindClasses().increment();
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
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
        Object obj = clazz.newInstance();
        Method method= clazz.getDeclaredMethod("say", null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader());

        System.out.println();
        MyClassLoader classLoader1 = new MyClassLoader("D:/test1");
        Class clazz1 = classLoader1.loadClass("com.tuling.load.User");

        Object obj1 = clazz1.newInstance();
        Method method1= clazz1.getDeclaredMethod("say", null);
        method1.invoke(obj1, null);
        System.out.println(clazz1.getClassLoader());
    }
}
