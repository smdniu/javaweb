package com.it.jdbc.demo2;

import org.junit.Test;

public class WrapperTest {
	@Test
	/**
	 * 装饰者模式:加强一个对象的方法waiter.server();
	 * 对这个方法不满意，1.改原类2.关键是不知道原类是什么，我这个对象不是new出来的，
	 * 这该怎么办呢，只好在写一个类，将这个对象传进去，然后重写方法
	 * 步骤：Waiter w = ...getW();
	 *     w = new ImpWaiter(w);
	 * 	   w.server();
	 */
	public void demo1(){
		Waiter waiter = new Waiteress();//获取的conn，不知道conn的具体类，又想加强conn
		waiter = new GirlWaiter(waiter);
		waiter.server();//想要加强这个方法
	}

	@Test 
	public void demo2(){
		/*Man man = new Man();
		man.run();//加强run,我要飞
		 */	
		Man man = new SuperMan(); 
		man.run();
		
	}
}
interface Waiter{
	public void server();
}

class Waiteress implements Waiter{

	public void server() {
		System.out.println("服务.....");
	}
	
}
class GirlWaiter implements Waiter{
	Waiter waiter;
	public GirlWaiter(Waiter waiter) {
		this.waiter = waiter;
	}
	public void server() {
		System.out.println("微笑服务......");
	}
	
}

class Man{
	public void run(){
		System.out.println("跑....");
	}
}
class SuperMan extends Man{
	@Override
	public void run() {
		System.out.println("飞.....");
	}
}

