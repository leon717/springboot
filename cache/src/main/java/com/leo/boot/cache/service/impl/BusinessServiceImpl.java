package com.leo.boot.cache.service.impl;

import java.io.File;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.boot.cache.domain.Info;
import com.leo.boot.cache.service.BusinessService;

import lombok.SneakyThrows;

@Service
public class BusinessServiceImpl implements BusinessService {

	private ObjectMapper om = new ObjectMapper();

	private String path = "info.json";

	@CachePut(value = "business.cache", key = "#object.id")
	@SneakyThrows
	@Override
	public void set(Object object) {
		om.writeValue(new File(path), object);
	}

	// value相当于名称空间，key相当于名称空间下唯一标示
	@Cacheable(value = "business.cache", key = "#id")
	@SneakyThrows
	@Override
	public Info get(String id) {
		return om.readValue(new File(path), Info.class);
	}

	@CacheEvict(value = "business.cache", key = "#id")
	@Override
	public void remove(String id) {
		new File(path).delete();
	}

}
