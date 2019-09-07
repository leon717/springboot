package com.leo.boot.easyrules;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mvel2.MVEL;

import com.leo.boot.easyrules.entity.Income;

public class MVELTests {
	
	private Map<String, Object> params;
	
	private Map<String, Object> generate(String key, Object value) {
		Map<String, Object> params = new HashMap<>();
		params.put(key, value);
		return params;
	}

	@Test
	public void conditionTest() {
		Income income = new Income().setBefore(1000);
		params = generate("income", income);
		
		Object result = MVEL.eval("income.before == 1000", params);
		assertTrue((boolean) result);
		
		result = MVEL.eval("income.before > 0 && income.before < 10000", params);
		assertTrue((boolean) result);
	}
	
	@Test
	public void value() {
		Income income = new Income().setBefore(1000);
		params = generate("income", income);
		
		Object result = MVEL.eval("income.before = 100", params);
		assertTrue((double) result == 100);		//返回设置值
	}
	
	@Test
	public void valueOfTest() {
		Income income = new Income().setBefore(1000);
		params = generate("income", income);
		
		// 相当于return
		Object result = MVEL.eval("income.before = 100;income;", params);
		assertTrue(income.getBefore() == 100);	// 能改变原对象的值
		assertTrue(income == result);			// 返回原对象
		
		// 相当于return
		result = MVEL.eval("with(income){before = 100, tax = 10};income;", params);
		assertTrue(income.getAfter() == 90);	//表达式是调用set方法
	}
	
}
