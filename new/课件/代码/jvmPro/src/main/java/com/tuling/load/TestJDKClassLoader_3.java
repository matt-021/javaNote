package com.tuling.load;

/**
 * @author 白起老师
 */
public class TestJDKClassLoader_3 {

    public static void main(String[] args) {

      /*  System.out.println(String.class.getClassLoader());
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getName());
        System.out.println(TestJDKClassLoader_3.class.getClassLoader().getClass().getName());

        System.out.println();*/

        //通过ClassLoader的getSystemClassLoader获取系统默认类加载器，默认是 sun.misc.Launcher$AppClassLoader 应用类加载器
       ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("the appClassLoader : " + appClassLoader);

        //获取应用类加载器的父加载器
        ClassLoader extClassloader = appClassLoader.getParent();
        System.out.println("the extClassloader : " + extClassloader);

        //获取扩展类加载器的父加载器
        ClassLoader bootstrapLoader = extClassloader.getParent();
        System.out.println("the bootstrapLoader : " + bootstrapLoader);





      /* try {
            Class clazz1 = appClassLoader.loadClass("com.tuling.load.User");

            Class clazz2 = appClassLoader.loadClass("com.tuling.load.User");
            System.out.println(clazz1 == clazz2);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

    }
}
