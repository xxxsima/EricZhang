package com.zhang.dao;

import com.zhang.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUserDao {

    User selectUser(long id);
    List<User> findAll();

    List<User> exitContactBy(@Param(value = "username") String username);

    User findUserName(String username);
    /*
    * insert
    * */
    void insert (User entity);
    /*
    * update
    * */
    void update (User entity);

    /*
    * delete
    * */
    void delete (User entity);

    /*
    * 注册用户
    * */
    //void  InsertUserNameAndPassWord(@Param("username")String  userName,@Param("password")String password);
   List<User> fuzzyQuery(Map map);
   User getById(Long id);

   List<User> checkUserName(@Param(value = "username") String username);

   Integer  findCount(@Param(value = "fuzzy") String fuzzy);

}
