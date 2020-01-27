package com.zhang.service.impl;

import com.zhang.Exception.ClassException;
import com.zhang.dao.ClassDao;
import com.zhang.model.Class;
import com.zhang.other.message.ClassMessage;
import com.zhang.service.ClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: King
 * @Create: 2019-12-05 12:52
 * @Desc:
 **/
@Service("classService")
public class ClassServiceImpl  implements ClassService {
    @Resource
    private ClassDao classDao;
    @Override
    public void insert(Class entity) {
        if ((entity.getId()==null && exitContactBy(entity.getClassName()))) {
            throw  new ClassException(ClassMessage.NAME_VERIFY);
        }
        classDao.insert(entity);
    }

    @Override
    public void delete(Long id) {
     classDao.delete(id);
    }

    @Override
    public void update(Class entity) {
     if (entity.getId()!=null && entity.getId()!= ' ') {
         classDao.update(entity);
     }
    }

    @Override
    public List<Class> findAll() {
        return classDao.findAll();
    }

    @Override
    public Class getById(Long id) {
        return classDao.getById(id);
    }

    @Override
    public List<Class> fuzzyQuery(String fuzzy, Integer pageNum, Integer pageSize) {
        HashMap<String,Object> map =new HashMap<>();
        map.put("fuzzy",fuzzy);
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        return classDao.fuzzyQuery(map);
    }

    @Override
    public Integer findCount(String fuzzy) {
        return classDao.findCount(fuzzy);
    }

    public boolean exitContactBy(String className) {
        return classDao.exitContactBy(className).size()>0;
    }
}


