package com.zhang.service.impl;

import com.zhang.Exception.FunctionException;
import com.zhang.dao.FunctionDao;
import com.zhang.model.Function;
import com.zhang.other.message.FunctionMessage;
import com.zhang.service.FunctionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: King
 * @Create: 2019-11-21 22:00
 * @Desc:
 **/
@Service("functionService")
public class FunctionServiceImpl implements FunctionService {
    @Resource
    private FunctionDao functionDao;
    @Override
    public Function getById(Long id) {
        return functionDao.getById(id);
    }

    @Override
    public List<Function> fuzzyQuery(String fuzzy, Integer pageNum, Integer pageSize) {

        HashMap<String,Object> map=new HashMap<>();
        map.put("fuzzy",fuzzy);
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        return functionDao.fuzzyQuery(map);
    }

    @Override
    public List<Function> findAll() {
        return functionDao.findAll();
    }

    @Override
    public void insert(Function entity) {
  if (exitContactBy(entity.getName())) {
      throw new FunctionException(FunctionMessage.NAME_VERIFY);
  }
  functionDao.insert(entity);
    }

    @Override
    public void update(Function entity) {
        functionDao.update(entity);
    }

    @Override
    public void delete(Long id) {
       functionDao.delete(id);
    }

    @Override
    public Integer findCount(String fuzzy) {
        return functionDao.findCount(fuzzy);

    }

    private boolean exitContactBy(String name)
    {
        return functionDao.weight(name).size()>0;
    }
}
