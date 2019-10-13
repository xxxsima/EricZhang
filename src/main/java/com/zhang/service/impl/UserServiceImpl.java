package com.zhang.service.impl;

import com.zhang.Exception.UserException;
import com.zhang.dao.IUserDao;
import com.zhang.dao.IUserDao;
import com.zhang.model.User;
import com.zhang.other.message.UserMessage;
import com.zhang.service.IUserService;
import com.zhang.service.IUserService;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.NewThreadAction;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (user !=null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public List<User> fuzzyQuery(String fuzzy,Integer pageNum,Integer pageSize) {
        HashMap<String,Object> map= new HashMap();
        map.put("fuzzy",fuzzy);
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);

        return userDao.fuzzyQuery(map);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }

    @Override
    public void insert(User entity) {
        if (exitContactBy(entity.getUsername())) {
            throw  new UserException(UserMessage.NAME_VERIFY);
        }


        //entity.setValid(1);
       // entity.setInsertTime(new Date());
        userDao.insert(entity);
    }
private boolean exitContactBy(String username) {
return  userDao.exitContactBy(username).size()>0;
}
    @Override
    public void update(User entity) {
       // entity.setUpdateTime(new Date());
        userDao.update(entity);
    }

    @Override


    public void delete(User entity) {
       // entity.setValid(2);
        userDao.update(entity);
    }

    @Override
    public List<User> checkUsername(String username) {
        return userDao.checkUserName(username);
    }

    @Override
    public Integer findCount(String fuzzy) {
        return userDao.findCount(fuzzy);
    }


}
