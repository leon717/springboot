package com.leo.boot.strategy.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.boot.strategy.domain.Employee;
import com.leo.boot.strategy.enumeration.EmployeeType;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@Test
	public void test() {
		Employee employee = new Employee();
		employee.setEmployeeType(EmployeeType.MANAGER);

		int amount = employeeService.payAmount(employee);
		TestCase.assertEquals(3, amount);
	}

}
