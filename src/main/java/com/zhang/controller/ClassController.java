package com.zhang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.ws.client.sei.ValueSetter;
import com.zhang.Exception.ClassException;
import com.zhang.model.Class;
import com.zhang.service.ClassService;
import com.zhang.util.BaseBusinessException;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin2.message.Message;

import javax.xml.soap.Node;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2019-12-05 13:03
 * @Desc:
 **/
@Controller
@RequestMapping("/class")
public class ClassController extends ControllerUtil {
    @Autowired
    private ClassService classService;

    @RequestMapping("/listUI")
    public String listUI() throws Exception{
        return "class/listUI";
    }
    @RequestMapping("/listJson")
    @ResponseBody
    public Map<String,Object> listJson (Model model,
                                        @RequestParam(value = "page")Integer pageNum,
                                        @RequestParam(value = "rows")Integer pageSize,
                                        @RequestParam(value = "fuzzy") String fuzzy
                                        ) throws  Exception {
        try {
            pageNum=ControllerUtil.checkPageNum(pageNum);
            pageSize=ControllerUtil.checkPageSize(pageSize);
            fuzzy= StringEscapeUtils.escapeHtml(fuzzy);
            PageHelper.startPage(pageNum,pageSize);
            List<Class> classes=classService.fuzzyQuery(fuzzy, pageNum, pageSize);
            PageInfo<Class> pageInfo=new PageInfo<Class>(classes);
            Integer count=classService.findCount(fuzzy);
            pageInfo.setTotal(count);
            Map<String,Object> map=new HashMap<>();
            map.put(SUCCESS,true);
            map.put(MESSAGE ,"OK");
            map.put("rows",pageInfo.getList());
            map.put("total",pageInfo.getTotal());
            return map;
        } catch (Exception e) {
              if (e instanceof BaseBusinessException) {
                  return ControllerUtil.generateFailureResponseMap("失败");
              } else {
                  e.printStackTrace();
                  throw e;
              }
        }
    }
    @RequestMapping("/addUI")
    public String addUI() {
        return "class/saveUI";
    }
    @RequestMapping("/updateUI")
    public String updateUI(Model model,@RequestParam(value = "id") Long id ) {
    Class classs=classService.getById(id);

    model.addAttribute("c",classs);
    return "class/saveUI";
    }

    @RequestMapping("/saveJson")
    @ResponseBody
    public Map<String,Object> saveJson(@RequestBody() Class classs)  {
        try {
            if (classs.getId()==null) {
                classService.insert(classs);
            }
            return ControllerUtil.generateSuccessResponseMap("保存成功");
        } catch (BaseBusinessException e) {
            if (e instanceof ClassException) {
                return ControllerUtil.generateFailureResponseMap("保存失败");
            } else {
                e.printStackTrace();
                return ControllerUtil.generateFailureResponseMap("保存失败");
            }
        }
    }
    @RequestMapping("/updateJson")
    @ResponseBody
    public Map<String,Object> updateJson(@RequestBody() Class classs) {
      try {
        if (classs.getId()!=null) {
            classService.update(classs);
        }
        return ControllerUtil.generateSuccessResponseMap("修改成功");
    } catch (BaseBusinessException e) {
          e.printStackTrace();
          return ControllerUtil.generateFailureResponseMap("修改失败");
      }

    }

    @RequestMapping("/deleteJson")
    @ResponseBody
    public Map<String,Object> deleteJson(@RequestParam(value = "id")Long id)  throws Exception{
        if (id!=null) {
            try {


            classService.delete(id);
            return ControllerUtil.generateSuccessResponseMap("删除成功");
            } catch (BaseBusinessException e) {
                if (e instanceof RuntimeException) {
                    return ControllerUtil.generateFailureResponseMap("删除失败");
                }
                return ControllerUtil.generateFailureResponseMap("删除失败");
            }
        } else {
            return ControllerUtil.generateFailureResponseMap("No class Id was  obtained");

        }
    }
    }


