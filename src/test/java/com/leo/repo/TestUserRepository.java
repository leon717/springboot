package com.leo.repo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import com.leo.domain.Address;
import com.leo.domain.User;
import com.leo.domain.User.Gender;
import com.leo.domain.UserInfo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserRepository {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testInsert() {
		//insert
		userRepository.save(new User("张三", "123456", "sanzhang@leo.com","zs",new Date()));
		userRepository.save(new User("李四", "123456", "sili@leo.com","ls",new Date()));
		userRepository.save(new User("王五", "123456", "wuwang@leo.com","ww",new Date()));
		userRepository.save(new User("王六", "123456", "wuwang6@leo.com","ww6",new Date()));
		userRepository.save(new User("王七", "123456", "wuwang7@leo.com","ww7",new Date()));
		userRepository.save(new User("王八", "123456", "wuwang8@leo.com","ww8",new Date()));
	}

	@Test
	public void testUpdate() {
		//update
		User user = userRepository.findByUserName("王五");
		user.setNickName("new");
		user.setGender(Gender.MALE);
		userRepository.save(user);
		
		//hql update
		User user2 = userRepository.findByUserName("王五");
		userRepository.modifyByIdAndUserId("王九", user2.getId());
	}
	
	@Test
	public void testDelete() {
		userRepository.delete(userRepository.findByUserName("张三"));
		userRepository.delete(userRepository.findByUserNameOrEmail(null, "sili@leo.com"));
	}
	
	@Test
	public void testJPADelete() {
		userRepository.deleteByUserName("王八");
	}
	
	@Test
	public void testQuery() {
		//findAll
		List<User> userList = userRepository.findAll();
		System.out.println(userList);
		//分页
		int page = 0;
		int size = 3;
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC,"id"));
		Page<User> users = userRepository.findAll(pageable);
		Page<User> users2 = userRepository.findByOrderByIdDesc(pageable);//和上面等效
		System.out.println(users.getContent());
		System.out.println(users2.getContent());
		
		//hql
		User user2 = userRepository.findByEmailAddress("wuwang6@leo.com");
		System.out.println(user2);
		
		//sql
		List<User> list = userRepository.FindByNickNameBySql("new");
		System.out.println(list);
	}
	
	//测试多条件查询
	@Test
	public void testConditionsQuery() {
		User user = userRepository.findByUserNameAndNickNameAndEmail("张三", null, null);
		Assert.assertNull(user);
	}
	
	//测试动态查询
	@Test
	public void testDynamiQuery() {
		User user = new User();
		user.setUserName("王");
		
		String genderStr = "MALE";
		
		int page = 0;
		int size = 3;
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC,"id"));
		
		Page<User> list = userRepository.findAll(new Specification<User>() {
			
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				ArrayList<Predicate> predicates = new ArrayList<Predicate>();
				
				if(!StringUtils.isEmpty(user.getUserName())){
					predicates.add(cb.like(root.get("userName"), "%"+user.getUserName()+"%"));
				}
				if(!StringUtils.isEmpty(user.getNickName())){
					predicates.add(cb.like(root.get("nickName"), "%"+user.getNickName()+"%"));
				}
				
				//eunm字符串
				Class<?> type = root.get("gender").getJavaType();
				if(type.isEnum()){
					Object enumObj = null;
					String name = type.getSimpleName();
					try {
						Method method = type.getMethod("valueOf", String.class);
						enumObj = method.invoke(null, genderStr);
					} catch (Exception e) {
						throw new RuntimeException(name+"参数错误");
					}
					predicates.add(cb.equal(root.get("gender"), enumObj));
				}
				
				//子查询
				Subquery<User> subquery = query.subquery(User.class);
				Root<UserInfo> addressRoot = subquery.from(UserInfo.class);
				Path<Object> path = root.get("id");					//匹配子查询字段
				subquery.select(addressRoot.<User>get("userId"));	//匹配父查询字段
				
				//子查询条件
				subquery.where(cb.like(addressRoot.get("info"), "%123%"));
				predicates.add(cb.in(path).value(subquery));
				
				query.where(predicates.toArray(new Predicate[predicates.size()]));
				
				return null;
			}
		}, pageable);
		
		System.out.println(list);
	}
	
	@Test
	public void testOnetoOne(){
		User user = userRepository.findByAddress_Code("1");
		System.out.println(user);
		User user2 = userRepository.findByByAddressCode("1");
		System.out.println(user2);
	}
	
	//测试多表动态查询
	@Test
	public void testDynamiJoinQuery() {
		User user = new User();
		user.setUserName("张");
		user.setAddress(new Address("1",null));
		
		int page = 0;
		int size = 3;
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC,"id"));
		
		Page<User> list = userRepository.findAll(new Specification<User>() {
			
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				ArrayList<Predicate> predicates = new ArrayList<Predicate>();
				
				if(!StringUtils.isEmpty(user.getUserName())){
					predicates.add(cb.like(root.get("userName"), "%"+user.getUserName()+"%"));
				}
				if(!StringUtils.isEmpty(user.getNickName())){
					predicates.add(cb.like(root.get("nickName"), "%"+user.getNickName()+"%"));
				}
				//多表查询
				if(!StringUtils.isEmpty(user.getAddress())){
					if(!StringUtils.isEmpty(user.getAddress().getCode())){
						Join<User,Address> join = root.join("address", JoinType.LEFT);
						predicates.add(cb.equal(join.get("code"), user.getAddress().getCode()));
					}
				}
				
				query.where(predicates.toArray(new Predicate[predicates.size()]));
				
				return null;
			}
		}, pageable);
		
		System.out.println(list);
	}
	
	@Test
	public void testQueryEnum(){
		List<String> userName = userRepository.findUserNameByGender(Gender.MALE);
		System.out.println(userName);
	}
	
	@Test
	public void testExample(){
		User user = new User();
		user.setGender(Gender.MALE);
		List<User> list = userRepository.findAll(Example.of(user));
		System.out.println(list);
	}

	@Test
	public void testAll() throws Exception {
		
		testInsert();
		
		testUpdate();
		
		testQuery();

		testDelete();
	}
	
}