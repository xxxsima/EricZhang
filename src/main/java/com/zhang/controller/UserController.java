package com.zhang.controller;

import com.zhang.Exception.UserException;
import com.zhang.model.User;
import com.zhang.service.IUserService;
import com.zhang.util.BaseBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang.StringEscapeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/user")
public class UserController  extends ControllerUtil{
    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String login() {
        return "home/index";
    }

    /*
    * 注册
    * */
    @RequestMapping("/regist")

    public String regist(Model model) {
                return "user/regist";
    }

    @RequestMapping("/findUserName")
    @ResponseBody
    public ModelAndView checkLogin( User user,Model model) {
        System.out.println(user.getPassword());
        String username = user.getUsername();
        String password = user.getPassword();

        user=userService.findUserName(username,password);
        ModelAndView modelAndView=new ModelAndView();

        if (user !=null) {
            modelAndView.setViewName("home/home");
            return modelAndView;
        }
        modelAndView.setViewName("defaut/defaut");
        return modelAndView;
    }

    @RequestMapping("/userUI")
    public String userUI() {
        return "user/user";
    }



    @RequestMapping("/select")
    @ResponseBody
    public Map  selectUser(Model model,
                           @RequestParam(value = "page",required = false)Integer pageNum,
                           @RequestParam(value = "rows",required = false)Integer pageSize,
                           @RequestParam(value = "fuzzy",required = false)String fuzzy) throws Exception {

        try {


            Map<String, Object> resultMap = new HashMap<String, Object>();
            pageNum = ControllerUtil.checkPageNum(pageNum);
            pageSize = ControllerUtil.checkPageSize(pageSize);
            fuzzy = StringEscapeUtils.escapeHtml(fuzzy);
            PageHelper.startPage(pageNum, pageSize);
            List<User> users = userService.fuzzyQuery(fuzzy,pageNum,pageSize);
            PageInfo<User> pageInfo = new PageInfo<User>(users);
             Integer count=userService.findCount(fuzzy);
            pageInfo.setTotal(count);
            return ControllerUtil.generatePageInfoMap(pageInfo);
        }
        catch (Exception e)
        {
if (e instanceof BaseBusinessException) {
    return ControllerUtil.generateFailureResponseMap("失败");
}else {
    e.printStackTrace();
    throw  e;
}
        }
    }

/*
* addUI
* */

@RequestMapping("/addUI")
    public String addUI()  throws Exception{
    return "user/saveUI";
}

/*
* 主页
* */
/*
* updateUI
* */
@RequestMapping("/updateUI")
    public String updateUI(Model model,@RequestParam(value = "id")Long id) throws Exception {
User user=userService.getById(id);
model.addAttribute("u",user);
return "user/saveUI";
}
/*
* 增加和修改
* 用户
* */
@RequestMapping( "/saveJson")
@ResponseBody
    public Map<String,Object> saveJson(@RequestBody() User user) {
    System.out.println(user);
    try {
        if (user.getId() == null) {
            user.setInsertTime(new Date());
            user.setUpdateTime(new Date());
            userService.insert(user);
        }
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("home/index");

        return ControllerUtil.generateSuccessResponseMap("保存成功");

    }catch (BaseBusinessException e) {
        if (e instanceof UserException) {
            return ControllerUtil.generateFailureResponseMap("保存失败");
        }
        else {
            e.printStackTrace();
            return ControllerUtil.generateFailureResponseMap(e.getMessage());
        }


    }
}

@RequestMapping(value ="/updateJson")
/*
* update
* user
* */
@ResponseBody
    public Map<String,Object> updateJson(@RequestBody() User user) {
    try {
        if (user.getId() != null) {

            user.setUpdateTime(new Date());
            userService.update(user);
        }
        return ControllerUtil.generateSuccessResponseMap("修改成功");
    }
    catch (BaseBusinessException e) {
        e.printStackTrace();
        return ControllerUtil.generateFailureResponseMap("修改失败");

    }
}

@RequestMapping("/deleteJson")

@ResponseBody
public  Map<String,Object> deleteJson(@RequestParam() Long id) throws Exception {
    if (id !=null) {
        try {
            User user=userService.getById(id);
            user.setValid(0);
            userService.update(user);
            return ControllerUtil.generateSuccessResponseMap("删除成功");
        }
        catch (BaseBusinessException e) {
            if (e instanceof  RuntimeException) {
                return ControllerUtil.generateFailureResponseMap("删除失败");
            }
            return ControllerUtil.generateFailureResponseMap("删除失败");
        }
    }
    else  {
        return ControllerUtil.generateFailureResponseMap("No user Id was obtained ");
    }
}

@RequestMapping(value = "/checkJson")

    @ResponseBody
    public  Map<String,Object> checkJson(@RequestParam()String username) throws Exception {

    System.out.println(username);

    if (username !=null) {
       List<User> users= userService.checkUsername(username);
       if (users.size()>0) {
           Map<String,Object> map = new HashMap<String, Object>();
           map.put("result",false);
           map.put("message","用户名已存在!");
           return map;
       } else {
           Map<String,Object> map = new HashMap<String, Object>();
           map.put("result",true);
           map.put("message","用户名通过验证!");
           return map;
       }
    }
    return null;
}
}
