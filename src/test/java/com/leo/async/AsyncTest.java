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
		asyncTask.task2();
		asyncTask.task3();
		long currentTimeMillis1 = System.currentTimeMillis();
		System.out.println("task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms");
	}

}
