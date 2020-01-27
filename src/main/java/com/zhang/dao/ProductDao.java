package com.zhang.dao;

import com.zhang.model.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2019-11-30 23:42
 * @Desc:
 **/
public interface ProductDao {
    void insert(Product entity);

    void delete(Long id);

    void update(Product entity);

    Product getById(Long id);

    List<Product> fuzzyString(Map map);

    List<Product> findAll();

    Integer findCont(@Param(value = "fuzzy")String fuzzy);

    List<Product> exitContactBy(@Param(value = "productName")String productName);

}
