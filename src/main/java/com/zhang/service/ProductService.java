package com.zhang.service;

import com.zhang.model.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: King
 * @Create: 2019-11-30 23:45
 * @Desc:
 **/
public interface ProductService {

    void insert(Product entity);

    void delete(Long id);

    void update(Product entity);

    Product getById(Long id);

    List<Product> fuzzyString(@Param(value = "fuzzy") String fuzzy,@Param(value = "pageNum") Integer pageNum,@Param(value = "pageSize") Integer pageSize);

    Integer findCont(@Param(value = "fuzzy")String fuzzy);
    List<Product> findAll();

}
