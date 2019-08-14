package com.leo.boot.cache.service;

public interface BusinessService {

	void set(Object object);
	
	Object get(String id);
	
	void remove(String id);
}
