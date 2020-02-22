package com.zhang.service;

import com.zhang.model.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: King
 * @Create: 2020-01-29 12:34
 * @Desc:
 **/
public interface CustomerService {
    void insert(Customer entity);

    void delete(Long id);

    void update(Customer entity);

    List<Customer> findAll();

    Customer getById(Long id);

    List<Customer> fuzzyQuery(@Param(value = "fuzzy")String fuzzy,@Param(value = "pageNum") Integer  pageNum,@Param(value = "pageSize") Integer pageSize);

    Integer findCount(@Param(value = "fuzzy") String fuzzy);

}
