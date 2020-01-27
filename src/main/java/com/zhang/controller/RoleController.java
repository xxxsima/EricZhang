package com.zhang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.tools.classfile.ConstantPool;
import com.zhang.Exception.RoleException;
import com.zhang.dao.RoleDao;
import com.zhang.model.Role;
import com.zhang.model.SystemLog;
import com.zhang.model.User;
import com.zhang.service.RoleService;
import com.zhang.service.SystemLogService;
import com.zhang.util.BaseBusinessException;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.sql.ParameterMetaData;
import java.util.*;

/**
 * @Author: King
 * @Create: 2019-11-29 14:06
 * @Desc:
 **/
@Controller
@RequestMapping("/role")
public class RoleController extends  ControllerUtil {
    @Autowired
    private RoleService roleService;
    @Autowired
    private SystemLogService systemLogService;

    @Autowired
    private HttpSession session;

    Role role=new Role();
    @RequestMapping("/roleUI")
    public String roleUI() {
        return "role/listUI";
    }



    @RequestMapping("/listJson")
    @ResponseBody
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
            List<Role> roles=roleService.fuzzyQuery(fuzzy,pageNum,pageSize);
            PageInfo<Role> pageInfo=new PageInfo<Role>(roles);
            Integer count=roleService.findCount(fuzzy);
            pageInfo.setTotal(count);
            Map<String,Object> map=new HashMap<>();
            map.put(SUCCESS,true);
            map.put(MESSAGE,"OK");
            map.put("rows",pageInfo.getList());
            map.put("total",pageInfo.getTotal());
            return map;
        }catch (Exception e) {
            if (e instanceof BaseBusinessException) {
                return ControllerUtil.generateFailureResponseMap("失败");
            } else  {
                e.printStackTrace();
                throw e;
            }
        }
    }

    @RequestMapping("/addUI")
    public String addUI () throws Exception {
        return "role/saveUI";
    }

    @RequestMapping("/updateUI")
    public  String updateUI(Model model,@RequestParam(value = "id")Long id) throws  Exception {
        Role role=roleService.getById(id);
        model.addAttribute("r",role);
        return  "role/saveUI";
    }

@RequestMapping("/saveJson")
    @ResponseBody
    public Map<String,Object> saveJson(@RequestBody() Role role) {
        try {
            if (role.getId()==null)
            {

                User user = (User) session.getAttribute("user");
                //String sa=String.valueOf(role.getId());
               /* SystemLog systemLog=new SystemLog("用户列表",SystemLog.KEY2_ADD,user.getUsername(),new Date(),"增加角色,角色名为:"+role.getName());
                systemLogService.save(systemLog);*/
                roleService.insert(role);

            }
            return ControllerUtil.generateSuccessResponseMap("保存成功");
        } catch (BaseBusinessException e) {
            if (e instanceof RoleException) {
                return ControllerUtil.generateFailureResponseMap("保存失败");
            } else {
                e.printStackTrace();
                return ControllerUtil.generateFailureResponseMap(e.getMessage());
            }
        }
}

@RequestMapping("/updateJson")
    @ResponseBody
    public Map<String,Object> updateJson(@RequestBody() Role role) {
        try {
            if (role.getId() != null) {
                roleService.update(role);
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
                Role role = roleService.getById(id);
                roleService.delete(id);
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
