package com.leo.boot.easyrules.config;

import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.leo.boot.easyrules.rule.TaxRule;

@Configuration
public class RuleConfig {

	@Bean("taxRulesEngine")
	public TaxRulesEngine taxRulesEngine(ApplicationContext context) {
		Rules rules = new Rules();
		context.getBeansOfType(TaxRule.class)
			.values()
			.forEach(rules::register);
		return TaxRulesEngine.builder()
				.rulesEngine(new DefaultRulesEngine())
				.rules(rules)
				.build();
	}
	
}
