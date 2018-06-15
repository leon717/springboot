package com.leo.jdk8;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.leo.domain.User.Gender;

public class testEnum {

	public static void main(String[] args){
		Class<?> type = Gender.class;
		String enumStr = "MALE";
		System.out.println(type.getSimpleName());
		if(type.isEnum()){
			try {
				Method method = type.getMethod("valueOf", String.class);
				Object enumObj = method.invoke(null, enumStr);
				System.out.println(enumObj);
			} catch (NoSuchMethodException e) {
				throw new RuntimeException("方法名错误");
			} catch (SecurityException e) {
				throw new RuntimeException("不能反射");
			} catch (IllegalAccessException e) {
				throw new RuntimeException("没有权限");
			} catch (IllegalArgumentException e) {
				throw new RuntimeException("参数错误");
			} catch (InvocationTargetException e) {
				throw new RuntimeException("缺少依赖");
			}
		}
	}
}
