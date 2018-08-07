package com.leo.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 1.synchronized绑定同一个对象，同一时刻最多仅仅有一个线程运行该段代码
 * 2.其他非synchronized代码，不受影响
 */
@Component
public class AsyncTask {

	//task1和task2共用1个锁，task3不受影响
	//synchronized方法
	@Async	
	public synchronized void task1() throws InterruptedException{
		System.out.println("线程-"+Thread.currentThread().getName()+":task1开始时间:"+System.currentTimeMillis());
		Thread.sleep(1000);
		System.out.println("线程-"+Thread.currentThread().getName()+":task1结束时间:"+System.currentTimeMillis());
	}
	
	//synchronized方法
	@Async	
	public synchronized void task2() throws InterruptedException{
		System.out.println("线程-"+Thread.currentThread().getName()+":task2开始时间:"+System.currentTimeMillis());
		Thread.sleep(1000);
		System.out.println("线程-"+Thread.currentThread().getName()+":task2结束时间:"+System.currentTimeMillis());
	}
	
	//无锁
	@Async	
	public void task3() throws InterruptedException{
		System.out.println("线程-"+Thread.currentThread().getName()+":task3开始时间:"+System.currentTimeMillis());
		Thread.sleep(1000);
		System.out.println("线程-"+Thread.currentThread().getName()+":task3结束时间:"+System.currentTimeMillis());
	}
	
	//和task1有共用1个锁
	//synchronized代码块，this锁
	@Async	
	public void task4() throws InterruptedException{
		synchronized (this) {
			System.out.println("线程-"+Thread.currentThread().getName()+":task4开始时间:"+System.currentTimeMillis());
			Thread.sleep(1000);
			System.out.println("线程-"+Thread.currentThread().getName()+":task4结束时间:"+System.currentTimeMillis());
		}
	}
	
	//和task7共用1个锁
	//synchronized代码块，当前类Class对象锁
	@Async
	public void task5() throws InterruptedException{
		synchronized (AsyncTask.class) {
			System.out.println("线程-"+Thread.currentThread().getName()+":task5开始时间:"+System.currentTimeMillis());
			Thread.sleep(1000);
			System.out.println("线程-"+Thread.currentThread().getName()+":task5结束时间:"+System.currentTimeMillis());
		}
	}
	
	//synchronized代码块，new新对象作为锁，不受其他影响
	@Async
	public void task6() throws InterruptedException{
		synchronized (new AsyncTask()) {
			System.out.println("线程-"+Thread.currentThread().getName()+":task6开始时间:"+System.currentTimeMillis());
			Thread.sleep(1000);
			System.out.println("线程-"+Thread.currentThread().getName()+":task6结束时间:"+System.currentTimeMillis());
		}
	}
	
	//synchronized静态方法
	@Async
	public static synchronized void task7() throws InterruptedException{
		System.out.println("线程-"+Thread.currentThread().getName()+":task7开始时间:"+System.currentTimeMillis());
		Thread.sleep(1000);
		System.out.println("线程-"+Thread.currentThread().getName()+":task7结束时间:"+System.currentTimeMillis());
	}
}