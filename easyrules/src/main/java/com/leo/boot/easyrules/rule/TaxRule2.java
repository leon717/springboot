package com.leo.boot.easyrules.rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.stereotype.Component;

import com.leo.boot.easyrules.entity.Income;

@Component
@Rule(name="rule2", description="income between 5000-8000")
public class TaxRule2 implements TaxRule {

	@Condition
	@Override
	public boolean suit(@Fact(INCOME) Income income) {
		return income.getBefore()>5000 && income.getBefore()<=8000;
	}

	@Action
	@Override
	public void addTax(@Fact(INCOME) Income income) {
		income.setTax((income.getBefore()-5000)*0.03);
	}
}
