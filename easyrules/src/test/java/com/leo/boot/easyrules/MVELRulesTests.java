package com.leo.boot.easyrules;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRule;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.leo.boot.easyrules.entity.Income;

public class MVELRulesTests {
	
	// 可配置在数据库
	@Test
	public void loadFromMVEL() {
		Rule rule = new MVELRule()
			.name("tax1")
			.description("between 0-5000")
			.when("income.before>5000 && income.before<=8000")
			.then("income.tax = (income.before-5000)*0.03");	//anction可配置多个
		Rules rules = new Rules();
		rules.register(rule);
		RulesEngine rulesEngine = new DefaultRulesEngine();
		
		for (int i = 1; i < 10; i++) {
			Income income = new Income().setBefore(1000*i);
			Facts facts = new Facts();
			facts.put("income", income);
			rulesEngine.fire(rules, facts);
			System.out.println(income);
		}
	}
	
	// 配置在配置文件
	@Test
	public void loadFromMVELFile() throws IOException, URISyntaxException {
		Rules rules = MVELRuleFactory
				.createRulesFrom(Files.newBufferedReader(Paths.get(new ClassPathResource("tax-rule.yml").getURI())));
		RulesEngine rulesEngine = new DefaultRulesEngine();
		
		for (int i = 1; i < 10; i++) {
			Income income = new Income().setBefore(1000*i);
			Facts facts = new Facts();
			facts.put("income", income);
			rulesEngine.fire(rules, facts);
			System.out.println(income);
		}
	}
	
}
