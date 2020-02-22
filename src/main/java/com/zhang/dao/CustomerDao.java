package com.zhang.dao;

import com.zhang.model.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2020-01-29 12:16
 * @Desc:
 **/
public interface CustomerDao {
    void insert(Customer entity);

    void delete(Long id);

    void update(Customer entity);

    Customer getById(Long id);

    List<Customer> findAll();

    List<Customer> fuzzyQuery(Map map);

    Integer findCount(@Param(value = "fuzzy") String fuzzy);

    List<Customer> exitContactBy(@Param(value = "customerName") String customerName);
}
