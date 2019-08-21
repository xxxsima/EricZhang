package com.zhang.dao;

import com.zhang.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserDao {

    User selectUser(long id);
    List<User> findAll();

    User findUserName(String username);

    void  InsertUserNameAndPassWord(@Param("username")String  userName,@Param("password")String password);
}
