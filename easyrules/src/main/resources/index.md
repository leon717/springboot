# easyrules
1.将逻辑判断分离出来，以表达式形式展现，分离流程判断和主线代码
2.将逻辑用java代码表示，感觉和责任链模式的filter类似

我觉得只执行判断逻辑，记录结果状态，其他复杂操作还是在java里完成比较好

使用：
支持MVEL表达式
1.new RulesEngine
2.rules register rule
3.fact.put(key, value)
4.RulesEngine.fire(rules, fact)