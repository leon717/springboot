package com.leo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.google.common.base.CaseFormat;

@Service
public class NativeSqlService {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private BeanUtilsBean beanUtils;
	
	public <T> Page<T> getPage(StringBuilder sql, Map<String, String> params, Pageable pageable, Class<T> clazz){
		
		if(params==null){
			params=new HashMap<String, String>();
		}
		
		//查询总数
		String countSql = "select count(1) from ( "+sql+" ) count;";
		Long total = jdbcTemplate.queryForObject(countSql, params, Long.class);
		
		//添加排序
		Sort sort = pageable.getSort();
		if(sort!=null){
			sql.append(" order by ");
			for(Order order : sort){
				String property = order.getProperty();
				property = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, property);
				String direction = order.getDirection().toString();
				sql.append(property+" "+direction+",");
			}
			sql.deleteCharAt(sql.length()-1);
		}
		
		//添加分页
		int pageSize = pageable.getPageSize();
		int pageNumber = pageable.getPageNumber();
		sql.append(" limit "+pageSize+" offset "+pageSize*pageNumber);
		
		List<Map<String, Object>> resultMaps = jdbcTemplate.queryForList(sql.toString(), params);
		List<T> objs = DBMapsToObjects(resultMaps, clazz);
		PageImpl<T> page = new PageImpl<T>(objs, pageable, total);
		
		return page;
	}
	
	public <T> List<T> DBMapsToObjects(List<Map<String, Object>> dbMaps, Class<T> clazz){  
	    List<T> list = new ArrayList<T>();
	    
        for (Map<String, Object> dbMap : dbMaps) { 
        	//命名转换
        	HashMap<String, Object> objMap = new HashMap<String, Object>();
        	for(Entry<String, Object> entry : dbMap.entrySet()){
        		String key = entry.getKey();
        		key = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
        		objMap.put(key, entry.getValue());
        	}
        	
        	//生成obj
        	T obj = null;
			try {
				obj = clazz.newInstance();
				beanUtils.populate(obj, objMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
            list.add(obj);  
        }  
	    return list;  
	} 
	
}
