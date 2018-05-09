package com.leo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leo.domain.User;

/**
 * JpaRepository可以根据方法名来自动的生产SQL
 * @author Leonhardt
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    User findByUserNameOrEmail(String username, String email);
}
