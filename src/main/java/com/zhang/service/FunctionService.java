package com.zhang.service;

import com.zhang.model.Function;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: King
 * @Create: 2019-11-21 21:38
 * @Desc:
 **/
public interface FunctionService {
    Function getById(Long id);
    List<Function>fuzzyQuery(@Param(value = "fuzzy")String fuzzy,@Param(value = "pageNum")Integer pageNum,@Param(value = "pageSize")Integer pageSize);
    List<Function> findAll();
    void insert (Function entity);
    void update(Function entity);
    void delete (Long id);
    Integer  findCount(@Param(value = "fuzzy") String fuzzy);
}
