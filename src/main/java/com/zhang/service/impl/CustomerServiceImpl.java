package com.zhang.service.impl;

import com.zhang.Exception.CustomerException;
import com.zhang.dao.CustomerDao;
import com.zhang.model.Customer;
import com.zhang.other.message.CustomerMessage;
import com.zhang.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: King
 * @Create: 2020-01-29 12:39
 * @Desc:
 **/
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
   @Resource
   private CustomerDao customerDao;
    @Override
    public void insert(Customer entity) {
        if ((entity.getId()==null && exitContactBy(entity.getCustomerName()))) {
            throw  new CustomerException(CustomerMessage.NAME_VERIFY);
        }
        customerDao.insert(entity);
    }

    @Override
    public void delete(Long id) {
       if (id !=null) {
           customerDao.delete(id);
       }
    }

    @Override
    public void update(Customer entity) {
     if (entity.getId() !=null && entity.getId()!=' ') {

         customerDao.update(entity);
     }
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public Customer getById(Long id) {
        return customerDao.getById(id);
    }

    @Override
    public List<Customer> fuzzyQuery(String fuzzy, Integer pageNum, Integer pageSize) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("fuzzy",fuzzy);
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        return customerDao.fuzzyQuery(map);
    }

    @Override
    public Integer findCount(String fuzzy) {
        return customerDao.findCount(fuzzy);
    }

    private boolean exitContactBy(String customerName) {
        return customerDao.exitContactBy(customerName).size()>0;
    }
}
