package com.leo.service;

import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.leo.domain.User;
import com.leo.domain.User.Gender;
import com.leo.service.NativeSqlService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TsetNativeSqlService {
	
	@Autowired
	private BeanUtilsBean beanUtils;

	@Autowired
	private NativeSqlService service;
	
	@Test
	public void testBeanUtils(){
		User user = new User();
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userName", "张三");
		//是MALE而不是MAN
		params.put("gender", "MALE");
		
		try {
			beanUtils.populate(user, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(user);
	}
	
	@Test
	public void testSql(){
		Pageable pageable = new PageRequest(0, 3, new Sort(Direction.DESC, "userName"));
		
		StringBuilder sb = new StringBuilder("select t.* from t_user t left join address a on t.id=a.user_id "
				+ "where a.code =:code");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("code", "1");
		
		Page<User> result = null;
		try {
			result = service.getPage(sb, params, pageable, User.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(result);
	}

	public static void main(String[] args) {
		//是MALE而不是MAN
		Gender gender = Gender.valueOf("MALE");
		System.out.println(gender);
	}
}
