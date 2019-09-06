package com.zhang.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
import sun.nio.cs.ext.GB18030;

import javax.management.relation.Role;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String login() {
        return "home/index";
    }

    @RequestMapping("/findUserName")
    @ResponseBody
    public ModelAndView checkLogin(User user,Model model) {
        System.out.println(user.getPassword());
       // List<User>  user2=  userService.findAll();
        //System.out.println(user2);
        String username = user.getUsername();
        String password = user.getPassword();

        user=userService.findUserName(username,password);
        ModelAndView modelAndView=new ModelAndView();

        if (user !=null) {
           // model.addAttribute("user",user);
                      //modelAndView.addObject("user",user);
            modelAndView.setViewName("home/home");
            return modelAndView;
        }
        modelAndView.setViewName("defaut/defaut");
        return modelAndView;
    }

/*
    @RequestMapping("/select")
    public ModelAndView selectUser() throws Exception {
        ModelAndView mv = new ModelAndView();
        User user = (User) userService.selectUser(1);
        mv.addObject("user", user);
        mv.setViewName("user/user");
        return mv;
*/
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
            List<User> users = userService.fuzzyQuery(fuzzy);
            PageInfo<User> pageInfo = new PageInfo<User>(users);
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
            userService.insert(user);
        }
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
/*
* delete
* User
* */
@ResponseBody
public  Map<String,Object> deleteJson(@RequestParam() Long id) throws Exception {
    if (id !=null) {
        try {
            userService.delete(id);
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
}
