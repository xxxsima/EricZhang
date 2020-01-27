package com.zhang.service.impl;

import com.zhang.dao.SystemLogDao;
import com.zhang.model.SystemLog;
import com.zhang.service.SystemLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhang.controller.ControllerUtil.MESSAGE;
import static com.zhang.controller.ControllerUtil.SUCCESS;

/**
 * @Author: King
 * @Create: 2019-12-03 21:05
 * @Desc:
 **/
@Service("systemLogService")
public class SystemLogServiceIml implements SystemLogService {
    @Resource
 private    SystemLogDao systemLogDao;
    @Override
    public void save(SystemLog entity) {

         systemLogDao.save(entity);

    }

    @Override
    public List<SystemLog> findAll() {
        return systemLogDao.findAll();
    }

    @Override
    public SystemLog getById(Long id) {
        return systemLogDao.getById(id);
    }

    @Override
    public List<SystemLog> fuzzyQuery(String fuzzy, Integer pageNum, Integer pageSize) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("fuzzy",fuzzy);
        map.put(SUCCESS,true);
        map.put(MESSAGE,"OK");

        return systemLogDao.fuzzyQuery( map);
    }

    @Override
    public Integer findCount(String fuzzy) {
        return systemLogDao.findCount(fuzzy);
    }
}
