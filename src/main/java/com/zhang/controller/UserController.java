package com.zhang.controller;

import com.zhang.model.User;
import com.zhang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


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

    @RequestMapping("/select")
    public ModelAndView selectUser() throws Exception {
        ModelAndView mv = new ModelAndView();
        User user = userService.selectUser(1);
        mv.addObject("user", user);
        mv.setViewName("user/user");
        return mv;
    }
}
