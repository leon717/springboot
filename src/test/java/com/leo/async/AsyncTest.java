package com.leo.async;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTest {
	
	@Autowired
	private AsyncTask asyncTask;
	
	@Test
	public void doTask() throws InterruptedException{
		long currentTimeMillis = System.currentTimeMillis();
		asyncTask.task1();
		asyncTask.task1();
		asyncTask.task1();
		asyncTask.task1();
		asyncTask.task1();
		asyncTask.task2();
		asyncTask.task3();
		long currentTimeMillis1 = System.currentTimeMillis();
		System.out.println("task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
		Thread.sleep(6000);
	}
	
	@Test
	public void doTask2() throws InterruptedException{
		long currentTimeMillis = System.currentTimeMillis();
		asyncTask.task1();
		asyncTask.task5();
		Thread.sleep(100);
		asyncTask.task6();
		AsyncTask.task7();
		long currentTimeMillis1 = System.currentTimeMillis();
		System.out.println("task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
		Thread.sleep(6000);
	}

}
