package com.it.jdbc.demo2;

import org.junit.Test;

public class WrapperTest {
	@Test
	/**
	 * װ����ģʽ:��ǿһ������ķ���waiter.server();
	 * ��������������⣬1.��ԭ��2.�ؼ��ǲ�֪��ԭ����ʲô�������������new�����ģ�
	 * �����ô���أ�ֻ����дһ���࣬��������󴫽�ȥ��Ȼ����д����
	 * ���裺Waiter w = ...getW();
	 *     w = new ImpWaiter(w);
	 * 	   w.server();
	 */
	public void demo1(){
		Waiter waiter = new Waiteress();//��ȡ��conn����֪��conn�ľ����࣬�����ǿconn
		waiter = new GirlWaiter(waiter);
		waiter.server();//��Ҫ��ǿ�������
	}

	@Test 
	public void demo2(){
		/*Man man = new Man();
		man.run();//��ǿrun,��Ҫ��
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
		System.out.println("����.....");
	}
	
}
class GirlWaiter implements Waiter{
	Waiter waiter;
	public GirlWaiter(Waiter waiter) {
		this.waiter = waiter;
	}
	public void server() {
		System.out.println("΢Ц����......");
	}
	
}

class Man{
	public void run(){
		System.out.println("��....");
	}
}
class SuperMan extends Man{
	@Override
	public void run() {
		System.out.println("��.....");
	}
}

