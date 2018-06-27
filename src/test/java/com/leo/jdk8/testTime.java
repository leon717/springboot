package com.leo.jdk8;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.Test;

/**
 * java.util.Date问题：
	非线程安全	java.util.Date 是非线程安全的，所有的日期类都是可变的，这是Java日期类最大的问题之一
	设计很差	在java.util和java.sql的包中都有日期类
	时区处理麻烦	日期类并不提供国际化，没有时区支持，因此Java引入了java.util.Calendar和java.util.TimeZone类，但他们同样存在上述所有的问题。
 */
/**
 * org.joda.time更好用，有toDate()方法
 */
public class testTime{
	
	/**
	 * Java 8中 java.util.Date类新增了两个方法，分别是from(Instant instant)和toInstant()方法
	 * 新的日期类转旧的也是如此，将新的先转成LocalDateTime，然后获取Instant，接着转成Date
	 */
	@Test
	public void LocalDateTime2Date(){
		LocalDateTime localDateTime = LocalDateTime.now();
		ZoneId zone = ZoneId.systemDefault();
	    Instant instant = localDateTime.atZone(zone).toInstant();
	    Date date = Date.from(instant);
	    System.out.println(date);
	}
	
	@Test
	public void Date2LocalDateTime(){
		Date date = new Date();
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = date.toInstant();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		System.out.println(localDateTime);
	}

	/**
	 * LocalDateTime
	 * LocalDate
	 * LocalTime
	 */
	@Test
	public void time(){
		//获取当前的日期时间	LocalDate、LocalTime同理
		LocalDateTime currentTime=LocalDateTime.now();
		System.out.println("当前时间:"+currentTime);
		
		LocalDate date1=currentTime.toLocalDate();
		System.out.println("date1:"+date1);
		
		//增加1天
		date1=date1.plusDays(1);
		System.out.println("date1:"+date1);
		
		Month month=currentTime.getMonth();
		int day=currentTime.getDayOfMonth();
		int seconds=currentTime.getSecond();
		
		System.out.println("月:"+month+",日:"+day+",秒:"+seconds);
		
		//改为2012年10月
		LocalDateTime date2=currentTime.withDayOfMonth(10).withYear(2012);
		System.out.println("date2:"+date2);
		
		//12december2014
		LocalDate date3=LocalDate.of(2014,Month.DECEMBER,12);
		System.out.println("date3:"+date3);
		
		//22小时15分钟
		LocalTime date4=LocalTime.of(22,15);
		System.out.println("date4:"+date4);
		
		//解析字符串
		LocalTime date5=LocalTime.parse("20:15:30");
		System.out.println("date5:"+date5);
	}
	
	/**
	 * 时区ZoneId
	 */
	@Test
	public void testZonedDateTime(){
    	// 获取当前时间日期
    	ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
    	System.out.println("date1: " + date1);
        
    	ZoneId id = ZoneId.of("Europe/Paris");
    	System.out.println("ZoneId: " + id);
        
    	ZoneId currentZone = ZoneId.systemDefault();
    	System.out.println("当期时区: " + currentZone);
   }
	
}
