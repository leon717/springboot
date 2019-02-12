package com.leo.boot.easyrules.rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.stereotype.Component;

import com.leo.boot.easyrules.entity.Income;

@Component
@Rule(name="rule1", description="income between 0-5000")
public class TaxRule1 implements TaxRule {

	@Condition
	@Override
	public boolean suit(@Fact(INCOME) Income income) {
		return income.getBefore()>0 && income.getBefore()<=5000;
	}

	@Action
	@Override
	public void addTax(@Fact(INCOME) Income income) {
		income.setTax(0);
	}
	
	// 可配置多个action
	@Action
	public void fire(@Fact(INCOME) Income income) {
		System.out.println(income);
	}
}
