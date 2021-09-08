package com.tuling.memory;


/*
 * @author 白起老师
 * 创建线程对象
 * 继承Thread类，并重写 run方法，run方法是线程的执行方法，线程执行的时候默认会执行  run方法
 * 
 * */
public class MyThread extends Thread {

	//run方法是线程的执行体
	@Override
	public void run() {
		  
		    for(int i=0;i<50;i++) {
		    	//this.getName():获取线程名字   此处的this代表  MyThread对象   MyThread继承了  Thread类，Thread类中有getName方法用于获取线程名字
		    	System.out.println("当前线程名字:"+this.getName()+"---i:"+i);
		    }
	}

	
	//main方法是程序的入口方法    main方法就是一个线程（主线程）
	public static void main(String[] args) {
		
		  //创建一个对象对象
		   MyThread thread = new MyThread();
		   thread.setName("线程二");
		   //调用线程对象的start可以让线程就绪，相当于通知 CPU线程已经准备好了，Cpu就会选择一个合适时机调用该线程的run方法
		   thread.start();
		   
		
		  for(int j=0;j<50;j++) {
		    	System.out.println("当前线程名字:"+Thread.currentThread().getName()+"---j:"+j);
		  }
		  
	}
	   
}
