package com.zhang.dao;

import com.zhang.model.SystemLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2019-12-02 18:30
 * @Desc:
 **/
public interface SystemLogDao {

    void save(SystemLog entity);

    List<SystemLog> findAll();

    SystemLog getById(Long id);

    List<SystemLog> fuzzyQuery(Map map);
    Integer findCount(@Param(value = "fuzzy")String fuzzy);

}
