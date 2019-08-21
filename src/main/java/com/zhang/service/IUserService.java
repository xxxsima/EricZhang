package com.zhang.service;

import com.zhang.model.User;

import java.util.List;

public interface IUserService {

    public User selectUser(long userId);
      List<User> findAll();
      User findUserName(String username,String password);
}
