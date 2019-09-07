package com.leo.boot.easyrules.rule;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.stereotype.Component;

import com.leo.boot.easyrules.entity.Income;

@Component
@Rule(name="rule3", description="income above 8000")
public class TaxRule3 implements TaxRule {

	@Condition
	@Override
	public boolean suit(@Fact(INCOME) Income income) {
		return income.getBefore()>8000;
	}

	@Action
	@Override
	public void addTax(@Fact(INCOME) Income income) {
		income.setTax((income.getBefore()-8000)*0.1+90);
	}
}
