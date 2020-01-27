package com.zhang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.net.httpserver.Authenticator;
import com.sun.org.apache.xpath.internal.functions.FuncBoolean;
import com.zhang.Exception.FunctionException;
import com.zhang.model.Function;
import com.zhang.service.FunctionService;
import com.zhang.util.BaseBusinessException;
import org.apache.commons.lang.StringEscapeUtils;
import org.omg.CORBA.TRANSACTION_MODE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhang.controller.ControllerUtil.MESSAGE;
import static com.zhang.controller.ControllerUtil.SUCCESS;

/**
 * @Author: King
 * @Create: 2019-09-06 22:09
 * @Desc:
 **/
@Controller
@RequestMapping("/function")
public class FunctionController extends BaseBusinessException {


    @Autowired
   private FunctionService functionService;
    @RequestMapping("/functionUI")
    public String teacherUI() {
        return "function/functionUI";
    }
 @RequestMapping("/functionList")
 @ResponseBody
 public Map functionList(Model model,
                         @RequestParam(value = "page",required = false)Integer pageNum,
                         @RequestParam(value = "rows",required = false)Integer pageSize,
                         @RequestParam(value = "fuzzy",required = false)String fuzzy
                         )throws  Exception {
        try {
        Map<String,Object> resultMap=new HashMap<String, Object>();
        pageNum=ControllerUtil.checkPageNum(pageNum);
        pageSize=ControllerUtil.checkPageSize(pageSize);
        fuzzy= StringEscapeUtils.escapeHtml(fuzzy);
     PageHelper.startPage(pageNum,pageSize);
     List<Function> functions=functionService.fuzzyQuery(fuzzy,pageNum,pageSize);
     PageInfo<Function> pageInfo=new PageInfo<Function>(functions);
     Integer count=functionService.findCount(fuzzy);
     pageInfo.setTotal(count);
     Map<String,Object> map=new HashMap<String, Object>();
     map.put(SUCCESS, true);
     map.put(MESSAGE,"OK");
     map.put("rows",pageInfo.getList());
     map.put("total",pageInfo.getTotal());
     return map;
 }catch (Exception e) {
            if (e instanceof BaseBusinessException) {
                return ControllerUtil.generateFailureResponseMap("失败");
            }else {
                e.printStackTrace();
                throw  e;}
        }
    }

    @RequestMapping("/addUI")
    public String addUI() throws Exception {
        return "function/saveUI";
    }

    @RequestMapping("/updateUI")
    public String updateUI(Model model,
                           @RequestParam(value = "id") Long id) throws Exception {
        Function function=functionService.getById(id);
        model.addAttribute("f",function);
        return "function/saveUI";
    }

    @RequestMapping("/saveJson")
    @ResponseBody
    public Map<String,Object> saveJson(@RequestBody() Function function) {
        try {

            if (function.getId() == null) {
                functionService.insert(function);
            }
            return ControllerUtil.generateSuccessResponseMap("保存成功");
        } catch (BaseBusinessException e) {
            if (e instanceof FunctionException) {
                return ControllerUtil.generateFailureResponseMap("保存失败");
            }else {
          e.printStackTrace();
          return ControllerUtil.generateFailureResponseMap(e.getMessage());
            }
        }
        }

        @RequestMapping(value = "/udpateJson")
    @ResponseBody
    public Map<String,Object> updateJson(@RequestBody() Function function) {
        try {
            if (function.getId() != null) {
                functionService.update(function);
            }
            return ControllerUtil.generateSuccessResponseMap("修改成功");

        }
        catch (BaseBusinessException e) {
            e.printStackTrace();
            return  ControllerUtil.generateFailureResponseMap("修改失败");
        }
        }
        @RequestMapping("/deleteJson")
    @ResponseBody
    public  Map<String,Object> deleteJson(@RequestParam() Long id) throws Exception {
        if (id != null) {
            try {
                //Function function =functionService.getById(id);
                functionService.delete(id);
                return ControllerUtil.generateSuccessResponseMap("删除成功");
            } catch (BaseBusinessException e) {
                if (e instanceof  RuntimeException) {
                    return  ControllerUtil.generateFailureResponseMap("删除失败");
                }
                return ControllerUtil.generateFailureResponseMap("删除失败");
            }
        }else {
            return ControllerUtil.generateFailureResponseMap("No function Id was obtained");
        }
        }

}
