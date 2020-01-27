package com.zhang.service.impl;

import com.zhang.Exception.ProductException;
import com.zhang.dao.ProductDao;
import com.zhang.model.Product;
import com.zhang.other.message.ProductMessage;
import com.zhang.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: King
 * @Create: 2019-11-30 23:46
 * @Desc:
 **/
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDao productDao;
    @Override
    public void insert(Product entity) {
        if (exitContactBy(entity.getProductName())) {
            throw  new ProductException(ProductMessage.NAME_VERIFY);
        }
        productDao.insert(entity);
    }

    @Override
    public void delete(Long id) {
    productDao.delete(id);
    }

    @Override
    public void update(Product entity) {
    productDao.update(entity);
    }

    @Override
    public Product getById(Long id) {
        return productDao.getById(id);
    }

    @Override
    public List<Product> fuzzyString(String fuzzy,Integer pageNum,Integer pageSize) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("fuzzy",fuzzy);
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        return productDao.fuzzyString(map);
    }

    @Override
    public Integer findCont(String fuzzy) {
        return productDao.findCont(fuzzy);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    private boolean exitContactBy(String productName) {
        return productDao.exitContactBy(productName).size()>0;
    }
}
