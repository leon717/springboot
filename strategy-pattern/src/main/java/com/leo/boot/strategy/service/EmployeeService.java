package com.leo.boot.strategy.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.boot.strategy.domain.Employee;
import com.leo.boot.strategy.enumeration.EmployeeType;

@Service
public class EmployeeService {

	private Map<EmployeeType, Strategy> strategyMap = new ConcurrentHashMap<>(3);

	@Autowired
	private EmployeeService(Collection<Strategy> strategys) {
		for (Strategy strategy : strategys) {
			if (strategyMap.containsKey(strategy.getType())) {
				throw new RuntimeException("duplicate strategy for " + strategy.getType());
			}
			strategyMap.put(strategy.getType(), strategy);
		}
	}

	public int payAmount(Employee employee) {
		return strategyMap.get(employee.getEmployeeType()).payAmount(employee);
	}

}
