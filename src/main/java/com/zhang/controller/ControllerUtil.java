package com.zhang.controller;

import com.github.pagehelper.PageInfo;
import com.zhang.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: King
 * @Create: 2019-08-27 23:47
 * @Desc:
 **/
public class ControllerUtil {
    private static final Logger log = LoggerFactory.getLogger(ControllerUtil.class);
    public static int DEFAULT_PAGE_SIZE = 10;
    public static int MAX_PAGE_SIZE = 500;

    public static String SUCCESS = "chenggong";
    public static String MESSAGE = "message";
    public static String DATA = "data";

    public static int checkPageNum(Integer pageNum){
        if(pageNum == null || pageNum <= 0){
            pageNum = 1;
        }

        return pageNum;
    }

    public static int checkPageSize(Integer pageSize){
        if(pageSize == null || pageSize <= 0){
            pageSize = ControllerUtil.DEFAULT_PAGE_SIZE;
        }else if(pageSize > ControllerUtil.MAX_PAGE_SIZE){
            throw new RuntimeException("pageSize太大。最大值为" + ControllerUtil.MAX_PAGE_SIZE);
        }
        return pageSize;
    }

    public static Map<String,Object> generateResponseMap(boolean success, Object message, Object data){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(SUCCESS,success);
        map.put(MESSAGE,message);
        map.put(DATA,data);
        return map;
    }
    /**
     * @Author:  King
     * @Date:    2018/5/23 18:53
     * @params:  Object obj
     * @return:  Map
     * @Method:  generateListMap
     * @Description:  试用于,不用分页的,比如一个车型下的所有座椅,
     */
    public static Map<String,Object> generateListMap(Object obj){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(SUCCESS,true);
        map.put(MESSAGE,"OK");
        map.put("rows",obj);
        return map;
    }
    /**
     * @Author:  King
     * @Date:    2018/5/23 18:50
     * @params:  PageInfo
     * @return:  Map
     * @Method:  generatePageInfoMap
     * @Description:  根据PageInfo 生成分页Json
     * @param pageInfo
     */
    public static Map<String,Object> generatePageInfoMap(PageInfo<User> pageInfo){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put(SUCCESS,true);
        map.put(MESSAGE,"OK");
        map.put("rows",pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        return map;
    }
    /**
     * @Author:  King
     * @Date:    2018/5/23 18:53
     * @params:  Object obj
     * @return:  Map
     * @Method:  generateComboBoxListMap
     * @Description:
     */
    public static Object generateComboBoxListMap(Object obj){

        return obj;
    }

    public static Map<String,Object> generateSuccessResponseMap(Object message,Object data){
        return generateResponseMap(true,message,data);
    }

    public static Map<String,Object> generateFailureResponseMap(Object message,Object data){
        return generateResponseMap(false,message,data);
    }

    public static Map<String,Object> generateSuccessResponseMap(Object message){
        return generateResponseMap(true,message,null);
    }

    public static Map<String,Object> generateFailureResponseMap(Object message){
        return generateResponseMap(false,message,null);
    }

}
