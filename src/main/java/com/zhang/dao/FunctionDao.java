package com.zhang.dao;

import com.zhang.model.Function;
import org.apache.ibatis.annotations.Param;
import sun.awt.SunHints;

import java.util.List;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2019-11-21 20:49
 * @Desc:
 **/
public interface FunctionDao {
    /*
    * insert
    * */
    void insert(Function entity);
    /*
    * delete
    * */
    void delete(Long id);
    /*
    * update
    * */
    void update(Function entity);
    /*
    * getById
    * */
     Function getById(Long id);
     /*
     *fuzzyQuery
     * */
     List<Function> fuzzyQuery(Map map);
    /*
    *de-weight
    * */
    List<Function> weight(@Param("name")String name);
    /*
    * findAll
    * */
    List<Function> findAll();

    Integer  findCount(@Param(value = "fuzzy") String fuzzy);

}
