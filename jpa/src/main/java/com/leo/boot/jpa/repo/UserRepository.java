package com.leo.boot.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.leo.boot.jpa.domain.User;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    User findByName(String name);

    User findByNameOrNick(String name, String nick);

    User findByNameAndNick(String name, String nick);

    @Transactional
    void deleteByName(String name);

    /******************** hql begin ********************/
    @Query("select u from User u where u.name = ?")
    User findByNameByHQL(String name);

    @Transactional
    @Modifying
    @Query("update User u set u.nick = ? where u.name = ?")
    void modifyNickByNameByHQL(String nick, String name);
    /******************** hql end ********************/

    /******************** native sql begin ********************/
    @Query(value = "select * from t_user where name = ?", nativeQuery = true)
    User findByNameByNative(String name);

    @Transactional
    @Modifying
    @Query(value = "update t_user set nick = ? where name = ?", nativeQuery = true)
    void modifyNickByNameByNative(String nick, String name);
    /******************** native sql end ********************/

}