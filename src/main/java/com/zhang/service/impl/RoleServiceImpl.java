package com.zhang.service.impl;

import com.zhang.Exception.RoleException;
import com.zhang.dao.RoleDao;
import com.zhang.model.Role;
import com.zhang.other.message.RoleMessage;
import com.zhang.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2019-11-29 13:43
 * @Desc:
 **/
@Service("roleService")
public class RoleServiceImpl implements RoleService {
   @Resource
   private RoleDao roleDao;

    @Override
    public void insert(Role entity) {
        if (exitContactBy(entity.getName())) {
            throw  new RoleException(RoleMessage.NAME_VERIFY);
        }
        roleDao.insert(entity);
    }

    @Override
    public void delete(Long id) {
     roleDao.delete(id);
    }

    @Override
    public void update(Role entity) {
     roleDao.update(entity);
    }

    @Override
    public Role getById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    public List<Role> fuzzyQuery(String fuzzy, Integer pageNum, Integer pageSize) {
        HashMap<String,Object> map =new HashMap<>();
        map.put("fuzzy",fuzzy);
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);

        return roleDao.fuzzyQuery(map);
    }

    @Override
    public Integer findCount(String fuzzy) {
        return roleDao.findCount(fuzzy);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    private boolean exitContactBy(String name) {
        return roleDao.exitContactBy(name).size()>0;
    }
}
