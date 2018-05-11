package com.leo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.leo.domain.User;

/**
 * JpaRepository可以根据方法名来自动的生产SQL
 * @author Leonhardt
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	
	//加条件（JPA通过方法名可以添加各种条件）
    User findByUserName(String userName);
    User findByUserNameOrEmail(String username, String email);

    //分页
    Page<User> findByOrderByIdDesc(Pageable pageable);
    
    //hql查询
    @Transactional(timeout = 10)
    @Query("select u from User u where u.email = ?")
    User findByEmailAddress(String email);
    
    //hql增删改（DML）需要@Modifying,事务
    @Transactional
    @Modifying
    @Query("update User u set u.userName = ? where u.id = ?")
    int modifyByIdAndUserId(String  userName, int id);
    
    //sql查询
    @Query(value = "select * from user b where b.nick_name=?", nativeQuery = true)
    List<User> FindByNickNameBySql(String nickName);
}
