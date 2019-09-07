package com.leo.boot.easyrules.config;

import java.util.Map;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;

import com.leo.boot.easyrules.entity.Income;
import com.leo.boot.easyrules.rule.TaxRule;

import lombok.Builder;

@Builder
public class TaxRulesEngine {
	
	private RulesEngine rulesEngine;
	
	private Rules rules;
	
	public void fire(Income income) {
		Facts facts = new Facts();
		facts.put(TaxRule.INCOME, income);
		rulesEngine.fire(this.rules, facts);
	}

	public Map<Rule, Boolean> check(Income income) {
		Facts facts = new Facts();
		facts.put(TaxRule.INCOME, income);
		return rulesEngine.check(this.rules, facts);
	}

}
