package com.zhang.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import com.zhang.Exception.StudentException;
import com.zhang.model.Student;
import com.zhang.service.StudentService;
import com.zhang.util.BaseBusinessException;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2019-12-07 16:17
 * @Desc:
 **/
@Controller
@RequestMapping("/student")
public class StudentController extends ControllerUtil {
@Autowired
private StudentService studentService;

Long classId=null;

    @RequestMapping("/listUI1")
    public  String listUI1() {
        return "student/listUI";
    }
    @RequestMapping("/listUI")
    public String listUI(@RequestParam(value = "id") Long id) {
        System.out.println(id);
        classId=id;
        return "student/listUI";
    }

    @RequestMapping("/listJson")
    @ResponseBody
    public Map<String,Object> listJson(Model model,
                                       @RequestParam(value = "page",required = false) Integer pageNum,
                                       @RequestParam(value = "rows",required = false) Integer pageSize,
                                       @RequestParam(value = "fuzzy",required = false) String fuzzy
                                       ) throws  Exception {
        try {
            pageNum = ControllerUtil.checkPageNum(pageNum);
            pageSize = ControllerUtil.checkPageSize(pageSize);
            fuzzy = StringEscapeUtils.escapeHtml(fuzzy);
            PageHelper.startPage(pageNum, pageSize);
            List<Student> studentList = studentService.fuzzyQuery(fuzzy, pageNum, pageSize,classId);
            PageInfo<Student> pageInfo = new PageInfo<Student>(studentList);
            Integer count = studentService.findCount(fuzzy,classId);
            pageInfo.setTotal(count);
            Map<String, Object> map = new HashMap<>();
            map.put(SUCCESS, true);
            map.put(MESSAGE, "OK");
            map.put("rows", pageInfo.getList());
            map.put("total", pageInfo.getTotal());
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
    public String addUI()throws Exception {
         return "student/saveUI";
    }

    @RequestMapping("/updateUI")
    public String updateUI (Model model,@RequestParam(value = "id") Long id) throws Exception {
        Student student=studentService.getById(id);
        model.addAttribute("s",student);
        return "student/saveUI";
    }

    @RequestMapping("/saveJson")
    @ResponseBody
    public Map<String,Object> saveJson(@RequestBody() Student student) {
        try {
            if (student.getId()==null) {
                student.setClassId(classId);
                studentService.insert(student);
            }
            return ControllerUtil.generateSuccessResponseMap("保存成功");
        } catch (BaseBusinessException e) {
            if (e instanceof StudentException) {
                return ControllerUtil.generateFailureResponseMap("保存失败");
            } else {
                e.printStackTrace();
                return ControllerUtil.generateFailureResponseMap(e.getMessage());
            }
        }
    }

    @RequestMapping("/updateJson")
    @ResponseBody
    public Map<String,Object> updateJson(@RequestBody() Student student) {
        try {
            if (student.getId() !=null) {
                studentService.update(student);
            }
            return ControllerUtil.generateSuccessResponseMap("修改成功");
        } catch (BaseBusinessException e) {
            e.printStackTrace();
            return ControllerUtil.generateFailureResponseMap("修改失败");
        }
    }

    @RequestMapping("/deleteJson")
    @ResponseBody
    public Map<String,Object> deleteJson(@RequestParam(value = "id") Long id  ) throws Exception {
        if (id != null) {
            try {
                Student student=studentService.getById(id);
                studentService.delete(id);
                return ControllerUtil.generateSuccessResponseMap("删除成功");
            } catch (BaseBusinessException e) {
                if (e instanceof  RuntimeException) {
                    return ControllerUtil.generateFailureResponseMap("删除失败");
                }
                return ControllerUtil.generateFailureResponseMap("删除失败");
            }
        } else {
            return ControllerUtil.generateFailureResponseMap("No role Id was  obtained");
        }
    }
}
