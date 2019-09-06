package com.zhang.controller;

import com.zhang.util.BaseBusinessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: King
 * @Create: 2019-09-06 22:09
 * @Desc:
 **/
@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseBusinessException {
    @RequestMapping("/teacherUI")
    public String teacherUI() {
        return "teacher/teacherUI";
    }
}
