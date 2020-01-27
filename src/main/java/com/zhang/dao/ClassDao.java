package com.zhang.dao;

import com.zhang.model.Class;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2019-12-04 18:57
 * @Desc:
 **/
public interface ClassDao {

    void insert(Class entity);

    void delete(Long id);

    void update(Class entity);

    List<Class> findAll();

    Class getById(Long id);

    List<Class> fuzzyQuery(Map map);

    Integer findCount(@Param(value = "fuzzy")String fuzzy);

    List<Class> exitContactBy(@Param(value = "className") String className);


}
