package com.leo.jdk8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

import com.leo.domain.User;
import com.leo.domain.User.Gender;

/**
 * Java8中的 Stream是对集合对象功能的增强，它专注于对集合对象进行各种非常便利、高效的聚合操作，或者大批量数据操作 
 * stream的特性(更像一个高级的Iterator，相同：单向，不同：可以并行)
	1.stream不存储数据
	2.stream不改变源数据
	3.stream的延迟执行特性
	+--------------------+       +------+   +------+   +---+   +-------+
	| stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect|
	+--------------------+       +------+   +------+   +---+   +-------+
 */
public class testStream {

	//封装
	@Test
	public void boxing(){
		// 1. Individual values
		Stream<String> stream = Stream.of("a", "b", "c");
		// 2. Arrays
		String [] strArray = new String[] {"a", "b", "c"};
		stream = Arrays.stream(strArray);
		// 3. Collections
		List<String> list = Arrays.asList(strArray);
		stream = list.stream();
		
		stream.forEach(System.out::println);
	}
	
	//对于基本数值型，目前有三种对应的包装类型 Stream：IntStream、LongStream、DoubleStream
	@Test
	public void IntStreamConstruction(){
		IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
		IntStream.range(1, 3).forEach(System.out::println);
		IntStream.rangeClosed(1, 3).forEach(System.out::println);
	}
	
	//还原
	@SuppressWarnings("unused")
	@Test
	public void unboxing(){
		// 1. Array
		Stream<String> stream = generate();
		String[] strArray1 = stream.toArray(String[]::new);
		// 2. Collection
		stream = generate();
		List<String> list1 = stream.collect(Collectors.toList());
		stream = generate();
		List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
		stream = generate();
		Set<String> set1 = stream.collect(Collectors.toSet());
		stream = generate();
		Stack<String> stack1 = stream.collect(Collectors.toCollection(Stack::new));
		// 3. String
		stream = generate();
		String str = stream.collect(Collectors.joining()).toString();
	}
	
	public Stream<String> generate(){
		return Stream.of("a", "b", "c");
	}
	
	/**
	 * 常见的操作可以归类如下：
		Intermediate：
			一个流可以后面跟随零个或多个 intermediate 操作(lazy)
			map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
		Terminal：
			一个流只能有一个 terminal 操作，当这个操作执行后，流就被使用“光”了，无法再被操作
			forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
		Short-circuiting：
			对于一个intermediate 操作，如果它接受的是一个无限大（infinite/unbounded）的Stream，但返回一个有限的新Stream。
			对于一个 terminal 操作，如果它接受的是一个无限大的 Stream，但能在有限的时间计算出结果。
			anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
	 */
	
	//map&flatMap
	@Test
	public void mapOperation(){
		//转大写
		List<String> output = Arrays.asList("a","b","c").stream()
				.map(String::toUpperCase)
				.collect(Collectors.toList());
		System.out.println(output);
		
		//平方数
		List<Integer> nums = Arrays.asList(1, 2, 3, 4);
		List<Integer> squareNums = nums.stream()
				.map(n -> n * n)
				.collect(Collectors.toList());
		System.out.println(squareNums);
		
		//flatMap 最常用的操作就是合并多个 Collection
		Stream<List<Integer>> inputStream = Stream.of(
			Arrays.asList(1),
			Arrays.asList(2, 3),
			Arrays.asList(4, 5, 6)
		);
		Stream<Integer> outputStream = inputStream
				.flatMap((childList) -> childList.stream());
		outputStream.forEach(System.out::println);
	}
	
	//filter
	@Test
	public void filterOperation() throws IOException{
		//留下偶数
		Integer[] sixNums = {1, 2, 3, 4, 5, 6};
		Integer[] evens = Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);
		Stream.of(evens).forEach(System.out::println);
			
		//获取文件所有单词
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/java/com/leo/jdk8/test.txt")));
		List<String> output = reader.lines().
				 flatMap(line -> Stream.of(line.split(" "))).
				 filter(word -> word.length() > 0).
				 collect(Collectors.toList());
		reader.close();
		Stream.of(output).forEach(System.out::println);
	}
	
	//forEach
	@Test
	public void forEachOperation(){
		List<User> male = Stream.generate(() -> new User()).limit(10).collect(Collectors.toList());
		male.stream().forEach(u -> u.setGender(Gender.MALE));
		male.forEach(u -> System.out.println(u.getGender()));
	}
	
	//peek	和forEach类似，但peek是Intermediate类型的
	@Test
	public void forPeekOperation(){
		List<User> male = Stream.generate(() -> new User()).limit(10)
				.peek((u -> u.setGender(Gender.MALE)))
				.collect(Collectors.toList());
		male.forEach(u -> System.out.println(u.getGender()));
	}
	
	//findFirst 它总是返回 Stream 的第一个元素，或者空(Optional)
	//Stream 中的 findAny、max/min、reduce 等方法等返回 Optional 值。还有例如 IntStream.average() 返回 OptionalDouble 等等
	@Test
	public void forfindFirstOperation(){
		List<User> male = Stream.generate(() -> new User()).limit(10)
				.peek((u -> u.setGender(Gender.MALE)))
				.collect(Collectors.toList());
		System.out.println(male.stream().filter(u -> u.getGender()==Gender.FEMALE).findFirst().isPresent());
	
		// min
		double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).min((p1, p2) -> p1.compareTo(p2)).get();
		System.out.println("minValue:"+minValue);
		// max
		double maxValue = Stream.of(-1.5, 1.0, -3.0, -2.0).max((p1, p2) -> p1.compareTo(p2)).get();
		System.out.println("maxValue:"+maxValue);
	}
	
	//reduce 这个方法的主要作用是把 Stream元素组合起来
	@Test
	public void reduceOperation(){
		// 字符串连接，concat = "ABCD"
		String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat); 
		System.out.println("concat:"+concat);
		// 求最小值，minValue = -3.0
		double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min); 
		System.out.println("minValue:"+minValue);
		// 求和，sumValue = 10, 有起始值
		int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
		System.out.println("maxValue:"+sumValue);
		// 求和，sumValue = 10, 无起始值
		sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
		System.out.println("maxValue:"+sumValue);
		// 过滤，字符串连接，concat = "ace"
		concat = Stream.of("a", "B", "c", "D", "e", "F").filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);	
		System.out.println("concat:"+concat);
	}
	
	//limit&skip limit返回 Stream的前面 n个元素；skip则是扔掉前 n个元素
	//sorted min/max/distinct等
	@Test
	public void limitAndskipOperation(){
		List<User> users = Stream.generate(() -> new User()).limit(10).collect(Collectors.toList());
		IntStream.range(0, users.size()).forEach(idx -> users.get(idx).setId("user-"+idx));
		users.stream().skip(5).forEach(u -> System.out.println(u.getId()));
	}
	
	//Match
	//	allMatch：Stream 中全部元素符合传入的 predicate，返回 true
	//	anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
	//	noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
	@Test
	public void MatchOperation(){
		List<User> users = Stream.generate(() -> new User()).limit(10).collect(Collectors.toList());
		IntStream.range(0, users.size()).forEach(idx -> users.get(idx).setId("user-"+idx));
		
		System.out.println(users.stream().allMatch(u -> u.getId().equals("user-6")));
		System.out.println(users.stream().anyMatch(u -> u.getId().equals("user-6")));
	}
	
	/**********生成流（无限流，要用limit限制）***********/
	
	//generate
	//iterate
	@Test
	public void generateAndIterateOperation(){
		Stream.generate(() -> new Integer(6)).limit(10).forEach(x -> System.out.print(x + " "));
		System.out.println();
		Stream.iterate(0,t->t+3).limit(10).forEach(x -> System.out.print(x + " "));
	}
	
	//groupingBy
	//partitioningBy 其实是一种特殊的 groupingBy，它依照条件测试的是否两种结果(true，flase)来构造返回的数据结构
	@Test
	public void groupingByAndPartitioningByOperation(){
		List<User> users = Stream.generate(() -> new User()).limit(10).collect(Collectors.toList());
		IntStream.range(0, users.size()).forEach(idx -> users.get(idx).setId(Integer.toString(idx)));
		
		System.out.println(users.stream().collect(Collectors.groupingBy(User::getId)));
		System.out.println(users.stream().collect(Collectors.partitioningBy(u->u.getId().equals("5"))));
	}
	
	//parallelStream	需要注意线程安全
	@Test
	public void parallelStream(){
		List<Integer> l = Stream.iterate(0,t->t+3).limit(100).collect(Collectors.toList());
		l.parallelStream().forEach(x -> System.out.println(Thread.currentThread()+":"+x));
	}
}
