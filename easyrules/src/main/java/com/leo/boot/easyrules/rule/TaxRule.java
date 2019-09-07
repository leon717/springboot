package com.leo.boot.easyrules.rule;

import com.leo.boot.easyrules.entity.Income;

public interface TaxRule {
	
	String INCOME = "INCOME";

	boolean suit(Income income);
	
	void addTax(Income income);
	
}
