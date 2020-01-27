package com.zhang.service;

import com.zhang.model.Class;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: King
 * @Create: 2019-12-05 12:47
 * @Desc:
 **/
public interface ClassService {
    void insert(Class entity);

    void delete(Long id);

    void update(Class entity);

    List<Class> findAll();

    Class getById(Long id);

    List<Class> fuzzyQuery(@Param(value = "fuzzy")String fuzzy,@Param(value = "pageNum") Integer pageNum,@Param(value = "pageSize") Integer pageSize);

    Integer findCount(@Param(value = "fuzzy") String fuzzy);
}
