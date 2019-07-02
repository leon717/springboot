package com.leo.boot.strategy.service;

import org.springframework.stereotype.Component;

import com.leo.boot.strategy.domain.Employee;

@Component
public class Engineer implements Strategy {

	@Override
	public int payAmount(Employee employee) {
		System.out.println("pay for Engineer");
		return 1;
	}

}
