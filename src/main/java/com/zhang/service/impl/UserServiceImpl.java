package com.zhang.service.impl;

import com.zhang.Exception.UserException;
import com.zhang.dao.IUserDao;
import com.zhang.dao.IUserDao;
import com.zhang.model.User;
import com.zhang.other.message.UserMessage;
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

       // String password1 = user.getPassword();


        if (user !=null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public List<User> fuzzyQuery(String fuzzy) {
        return userDao.fuzzyQuery(fuzzy);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public void insert(User entity) {
        /*if (entity!=null) {
            if (entity.getEmail()==null) {

            }
        }*/

        if (exitContactBy(entity.getUsername())) {
            throw  new UserException(UserMessage.NAME_VERIFY);
        }
        userDao.insert(entity);
    }
private boolean exitContactBy(String username) {
return  userDao.exitContactBy(username).size()>0;
}
    @Override
    public void update(User entity) {
userDao.update(entity);
    }

    @Override
    public void delete(Long id) {
userDao.delete(id);
    }
}
