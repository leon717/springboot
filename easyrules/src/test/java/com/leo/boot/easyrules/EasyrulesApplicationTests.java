package com.leo.boot.easyrules;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.boot.easyrules.config.TaxRulesEngine;
import com.leo.boot.easyrules.entity.Income;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyrulesApplicationTests {
	
	@Autowired
	private TaxRulesEngine taxRulesEngine;
	
	@Test
	public void loadFromApp() {
		for (int i = 1; i < 10; i++) {
			Income income = new Income().setBefore(1000*i);
			taxRulesEngine.fire(income);
			System.out.println(income);
		}
	}

}
