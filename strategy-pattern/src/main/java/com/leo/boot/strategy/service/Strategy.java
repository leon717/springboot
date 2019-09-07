package com.leo.boot.strategy.service;

import com.leo.boot.strategy.domain.Employee;
import com.leo.boot.strategy.enumeration.EmployeeType;

public interface Strategy {

	int payAmount(Employee employee);

	default EmployeeType getType() {
		return EmployeeType.valueOf(getClass().getSimpleName().toUpperCase());
	}
}
