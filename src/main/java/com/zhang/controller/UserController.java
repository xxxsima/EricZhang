package com.zhang.controller;

import com.zhang.model.User;
import com.zhang.service.IUserService;
import com.zhang.util.BaseBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.lang.StringEscapeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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

    @RequestMapping("/userUI")
    public String userUI() {
        return "user/user";
    }

}
