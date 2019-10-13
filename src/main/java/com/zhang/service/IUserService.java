package com.zhang.service;

import com.zhang.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface IUserService {

    public User selectUser(long userId);
      List<User> findAll();
      User findUserName(String username,String password);

  List<User> fuzzyQuery(@Param(value = "fuzzy") String fuzzy,@Param(value = "pageNum") Integer pageNum,@Param(value = "pageSize") Integer pageSize);
      User getById(Long id);
      /*
      * insert
      * */
      void insert(User entity);
      /*
      * update
      * */
      void update(User entity);
      /*
      * delete
      * */
       void delete(User entity);

      // List<User> exitContactBy(@Param(value = "username") String username);

  List<User> checkUsername(@Param(value = "username") String username);

  Integer  findCount(@Param(value = "fuzzy") String fuzzy);

}
