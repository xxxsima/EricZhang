package com.zhang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.Exception.CustomerException;
import com.zhang.model.Customer;
import com.zhang.service.CustomerService;
import com.zhang.util.BaseBusinessException;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2020-01-29 13:22
 * @Desc:
 **/
@Controller
@RequestMapping("/customer")
public class CustomerController extends ControllerUtil {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/listUI")
    public String listUI(){
        return "customer/listUI";
    }

    @RequestMapping("/listJson")
    public Map listJson(Model model,
                        @RequestParam(value = "page",required = false) Integer pageNum,
                        @RequestParam(value = "rows",required = false) Integer pageSize,
                        @RequestParam(value = "fuzzy",required = false) String fuzzy
                        ) throws  Exception {
        try {
            pageNum=ControllerUtil.checkPageNum(pageNum);
            pageSize=ControllerUtil.checkPageSize(pageSize);
            fuzzy= StringEscapeUtils.escapeHtml(fuzzy);
            PageHelper.startPage(pageNum,pageSize);
            List<Customer> customers=customerService.fuzzyQuery(fuzzy,pageNum,pageSize);
             PageInfo<Customer> pageInfo=new PageInfo<Customer>(customers);
            Integer count=customerService.findCount(fuzzy);
            pageInfo.setTotal(count);
            Map<String,Object> map=new HashMap<>();
            map.put(SUCCESS,true);
            map.put(MESSAGE,"OK");
            map.put("rows",pageInfo.getList());
            map.put("total",pageInfo.getTotal());
            return map;
        } catch (Exception e) {
            if (e instanceof BaseBusinessException) {
                return ControllerUtil.generateFailureResponseMap("失败");
            } else {
                e.printStackTrace();
                throw  e;
            }
        }
    }
    @RequestMapping("/addUI")
    public String addUI() throws Exception {
        return "customer/saveUI";
    }

    @RequestMapping("/updateUI")
    public String updateUI(Model model,
                           @RequestParam(value = "id")Long id) {
        Customer customer=customerService.getById(id);
        model.addAttribute(customer);
        return "customer/saveUI";
    }
    @RequestMapping("/saveJson")
    @ResponseBody
    public Map<String,Object> saveJson(@RequestBody() Customer customer) {
        try {
            if (customer.getId()==null) {
                customerService.insert(customer);
            }
            return ControllerUtil.generateSuccessResponseMap("保存成功");
        } catch (BaseBusinessException e) {
            if (e instanceof CustomerException) {
                return ControllerUtil.generateFailureResponseMap("保存失败");
            } else {
                e.printStackTrace();
                return ControllerUtil.generateFailureResponseMap(e.getMessage());
            }
        }
    }

    @RequestMapping("/updateJson")
    @ResponseBody
    public Map<String,Object> updateJson(@RequestBody() Customer customer) {
        try {
            if (customer.getId() !=null) {
                customerService.update(customer);
            }
            return  ControllerUtil.generateSuccessResponseMap("修改成功");
        } catch (BaseBusinessException e) {
            e.printStackTrace();
            return ControllerUtil.generateFailureResponseMap("修改失败");
        }
    }
    @RequestMapping("/deleteJson")
    @ResponseBody
    public  Map<String,Object> deleteJson(@RequestParam() Long id) throws  Exception {
        if (id!= null) {
            try {
               Customer customer=customerService.getById(id);
               customerService.delete(id);
               return ControllerUtil.generateSuccessResponseMap("删除成功");
            }catch (BaseBusinessException e) {
                if (e instanceof RuntimeException) {
                    return ControllerUtil.generateFailureResponseMap("删除失败");
                }
                return ControllerUtil.generateFailureResponseMap("删除失败");
            }
        }else {
            return ControllerUtil.generateFailureResponseMap("No customer Id was obtained");
        }
    }

}
