package com.zhang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhang.Exception.ProductException;
import com.zhang.model.Product;
import com.zhang.model.Role;
import com.zhang.service.ProductService;
import com.zhang.util.BaseBusinessException;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2019-11-30 23:46
 * @Desc:
 **/
@Controller
@RequestMapping("/product")
public class ProductController extends ControllerUtil {
    @Autowired
    private ProductService productService;

    @RequestMapping("/productUI")
    public String productUI () {
        return "product/listUI";
    }
    @RequestMapping("/listJson")
    @ResponseBody
    public Map listJson(Model model,
                        @RequestParam(value = "page",required = false)Integer pageNum,
                        @RequestParam(value = "rows",required = false)Integer pageSize,
                        @RequestParam(value = "fuzzy",required = false)String fuzzy
                        ) throws  Exception {
       try{
           pageNum=ControllerUtil.checkPageNum(pageNum);
           pageSize=ControllerUtil.checkPageSize(pageSize);
           fuzzy= StringEscapeUtils.escapeHtml(fuzzy);
           PageHelper.startPage(pageNum,pageSize);
           List<Product> products=productService.fuzzyString(fuzzy, pageNum, pageSize);
           PageInfo<Product> pageInfo=new PageInfo<Product>(products);
           Integer count=productService.findCont(fuzzy);
           pageInfo.setTotal(count);
           Map <String,Object> map=new HashMap<>();
           map.put(SUCCESS,true);
           map.put(MESSAGE,"OK");
           map.put("rows",pageInfo.getList());
           map.put("total",pageInfo.getTotal());
           return map;
       } catch (Exception e) {
           if (e instanceof  BaseBusinessException) {
               return ControllerUtil.generateFailureResponseMap("失败");
           } else  {
               e.printStackTrace();
               throw e;
           }
       }
    }

    @RequestMapping("/addUI")
    public String addUI  () throws Exception {
        return "product/saveUI";
    }
    @RequestMapping("/updateUI")
    public String updateUI(Model model,@RequestParam(value = "id")Long id) throws  Exception {
        Product product=productService.getById(id);
        model.addAttribute("p",product);
        return "product/saveUI";
    }
    @RequestMapping("/saveJson")
    @ResponseBody
    public  Map<String,Object> saveJson(@RequestBody() Product product) {
        try {
            if (product.getId()==null) {
                product.setCreateTime(new Date());
                product.setUpdateTime(new Date());
                productService.insert(product);
            }
            return ControllerUtil.generateSuccessResponseMap("新增成功");

        }catch (BaseBusinessException e) {
            if (e instanceof ProductException) {
                return ControllerUtil.generateFailureResponseMap("保存失败");
            }else {
                e.printStackTrace();
                return ControllerUtil.generateFailureResponseMap(e.getMessage());
            }
        }
    }

    @RequestMapping("/updateJson")
    @ResponseBody
    public Map<String,Object> updateJson(@RequestBody() Product product) {
        try {
            if (product.getId() != null) {
                Product product1=new Product();

                product.setUpdateTime(new Date());
                productService.update(product);
            }
            return ControllerUtil.generateSuccessResponseMap("修改成功");
        } catch (BaseBusinessException e) {
            e.printStackTrace();
            return ControllerUtil.generateFailureResponseMap("修改失败");
        }
    }




    @RequestMapping("/deleteJson")
    @ResponseBody
    public Map<String,Object> deleteJson(@RequestParam()Long id) throws Exception {
        if (id !=null) {
            try {
                Product product = productService.getById(id);
                productService.delete(id);
                return ControllerUtil.generateSuccessResponseMap("删除成功");
            } catch (BaseBusinessException e) {
                if (e instanceof  RuntimeException) {
                    return ControllerUtil.generateFailureResponseMap("删除失败");
                }
                return ControllerUtil.generateFailureResponseMap("删除失败  ");
            }
        } else {
            return ControllerUtil.generateFailureResponseMap("No role Id was  obtained");
        }

    }
}
