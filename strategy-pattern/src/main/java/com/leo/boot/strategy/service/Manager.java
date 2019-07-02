package com.leo.boot.strategy.service;

import org.springframework.stereotype.Component;

import com.leo.boot.strategy.domain.Employee;

@Component
public class Manager implements Strategy {

	@Override
	public int payAmount(Employee employee) {
		System.out.println("pay for Manager");
		return 3;
	}

}
