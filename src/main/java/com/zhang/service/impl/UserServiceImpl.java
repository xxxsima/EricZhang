package com.zhang.service.impl;

import com.zhang.dao.IUserDao;
import com.zhang.dao.IUserDao;
import com.zhang.model.User;
import com.zhang.service.IUserService;
import com.zhang.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    public User selectUser(long userId) {
        return userDao.selectUser(userId);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findUserName(String username, String password) {
        User user=userDao.findUserName(username);

        String password1 = user.getPassword();

        boolean equals1 = password1.equals(password);

        boolean equals = user.getPassword().equals(password);

        boolean b = user.getPassword() == password;

        if (user !=null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
