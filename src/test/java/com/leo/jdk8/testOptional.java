package com.leo.jdk8;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import com.leo.domain.User;

/**
 * Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象。
 * Optional 是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。
 * Optional 类的引入很好的解决空指针异常。
 */
public class testOptional {
	
	private final Log logger = LogFactory.getLog(getClass());
	
	/***********	创建 Optional实例		***********/
	
	@Test(expected = NoSuchElementException.class)
	public void whenCreateEmptyOptional_thenNull() {
	    Optional<User> emptyOpt = Optional.empty();
	    emptyOpt.get();
	}
	
	@SuppressWarnings("unused")
	@Test(expected = NullPointerException.class)
	public void whenCreateOfEmptyOptional_thenNullPointerException() {
		User user = null;
	    Optional<User> opt = Optional.of(user);
	}
	
	@SuppressWarnings("unused")
	@Test
	public void whenCreateOfEmptyOptional_withOfNullable() {
		User user = null;
	    Optional<User> opt = Optional.ofNullable(user);
	}
	
	/***********	访问 Optional对象的值		***********/
	
	@Test
	public void whenCreateOfNullableOptional_thenOk() {
	    String name = "John";
	    Optional<String> opt = Optional.ofNullable(name);
	    assertEquals("John", opt.get());
	}
	
	@Test
	public void whenCheckIfPresent_thenOk() {
	    User user = new User();
	    user.setEmail("12345@qq.com");
	    Optional<User> opt = Optional.ofNullable(user);
	    assertTrue(opt.isPresent());	//判断是否存在
	    assertEquals(user.getEmail(), opt.get().getEmail());
	    
	    //Lambda 表达式形式
	    opt.ifPresent( u -> assertEquals(user.getEmail(), u.getEmail()));
	}
	
	/***********	返回默认值(自定义异常)		***********/
	
	@Test
	public void whenEmptyValue_thenReturnDefault() {
	    User user = null;
	    User user2 = new User();
	    user2.setId("1234");
	    user2.setEmail("anna@gmail.com");
	    
	    //第一种方式
	    User result = Optional.ofNullable(user).orElse(user2);	//set default
	    assertEquals(user2.getEmail(), result.getEmail());
	    
	    //第二种方式
	    User result2 = Optional.ofNullable(user).orElseGet( () -> user2);	//set default
	    assertEquals(user2.getEmail(), result2.getEmail());
	}
	
	@SuppressWarnings("unused")
	@Test
	public void givenEmptyValue_whenCompare_thenOk() {
	    User user = null;
	    logger.debug("Using orElse");
	    User result = Optional.ofNullable(user).orElse(createNewUser());
	    logger.debug("Using orElseGet");
	    User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
	}
	
	@SuppressWarnings("unused")
	@Test
	public void givenPresentValue_whenCompare_thenOk() {
	    User user = new User();
	    logger.info("Using orElse");
	    //orElse() 方法仍然创建了 User 对象
	    User result = Optional.ofNullable(user).orElse(createNewUser());
	    logger.info("Using orElseGet");
	    //orElseGet() 方法不创建 User 对象
	    User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
	}

	private User createNewUser() {
	    logger.debug("Creating New User");
	    User user = new User();
	    user.setId("1234");
	    user.setEmail("anna@gmail.com");
	    return user;
	}
	
	@SuppressWarnings("unused")
	@Test(expected = IllegalArgumentException.class)
	public void whenThrowException_thenOk() {
		User user = null;
	    User result = Optional.ofNullable(user)
	      .orElseThrow( () -> new IllegalArgumentException());
	}
	
	//map() 对值应用(调用)作为参数的函数，然后将返回的值包装在 Optional中
	//flatMap 感觉不实用，处理返回Optional
	@Test
	public void whenMap_thenOk() {
		User user = new User();
	    user.setId("1234");
	    user.setEmail("anna@gmail.com");
	    String email = Optional.ofNullable(user)
	      .map(u -> u.getEmail()).orElse("default@gmail.com");

	    assertEquals(email, user.getEmail());
	}

	// 过滤值
	// filter() 接受一个 Predicate 参数，返回测试结果为 true 的值。如果测试结果为 false，会返回一个空的 Optional
	@Test
	public void whenFilter_thenOk() {
		User user = new User();
	    user.setId("1234");
	    user.setEmail("anna@gmail.com");
	    Optional<User> result = Optional.ofNullable(user)
	      .filter(u -> u.getEmail() != null && u.getEmail().contains("@"));

	    assertTrue(result.isPresent());
	}
	
}
