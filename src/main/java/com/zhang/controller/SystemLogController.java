package com.zhang.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.rngom.parse.host.Base;
import com.zhang.model.SystemLog;
import com.zhang.service.SystemLogService;
import com.zhang.util.BaseBusinessException;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2019-12-03 21:23
 * @Desc:
 **/
@Controller
@RequestMapping("/systemLog")
public class SystemLogController extends ControllerUtil {
@Autowired
    private SystemLogService systemLogService;

@RequestMapping("/listUI")
    public String listUI(){return "systemLog/listUI";}



@RequestMapping("/listJson")
@ResponseBody
public Map<String,Object>  listJson(Model model,
                                    @Param(value = "pageNum")Integer pageNum,
                                    @Param(value = "pageSize") Integer pageSize,
                                    @Param(value = "fuzzy")String fuzzy) throws Exception {
    try {
        HashMap <String ,Object> map=new HashMap<>();
        pageNum=ControllerUtil.checkPageNum(pageNum);
        pageSize=ControllerUtil.checkPageSize(pageSize);
        fuzzy= StringEscapeUtils.escapeHtml(fuzzy);
        PageHelper.startPage(pageNum,pageSize);
        List<SystemLog> systemLogs=systemLogService.fuzzyQuery(fuzzy, pageNum, pageSize);
        PageInfo<SystemLog> pageInfo=new PageInfo<SystemLog>(systemLogs);
        Integer count=systemLogService.findCount(fuzzy);
        pageInfo.setTotal(count);
        map.put(SUCCESS,true);
        map.put(MESSAGE,"OK");
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getTotal());
        return map;
    } catch (Exception e) {
        if (e instanceof BaseBusinessException) {


            return ControllerUtil.generateFailureResponseMap("失败");
        } else {
            e.printStackTrace();
            throw  e;
        }
    }
}
}