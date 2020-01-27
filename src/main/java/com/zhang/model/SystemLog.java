package com.zhang.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: King
 * @Create: 2019-12-02 18:27
 * @Desc:
 **/
public class SystemLog {
    private Long id;
    private String modules;
    private String types;
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date actionTime;
    private String actionContext;


    public SystemLog( String modules, String types, String userName, Date actionTime, String actionContext) {

        this.modules = modules;
        this.types = types;
        this.userName = userName;
        this.actionTime = actionTime;
        this.actionContext = actionContext;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getActionContext() {
        return actionContext;
    }

    public void setActionContext(String actionContext) {
        if (actionContext.length()>1000) {
            this.actionContext =actionContext.substring(0,1000)+"...";
        } else {
        this.actionContext = actionContext;
    }
    }
    public static final String KEY2_ADD = "Add";
    public static final String KEY2_UPDATE = "Update";
    public static final String KEY2_DELETE = "Delete";
    public static final String KEY2_COPY = "Copy";
    public static final String KEY2_COMPARE = "Compare";
    public static final String KEY2_EXPORT = "Export";
    public static final String KEY2_IMPORT = "Import";

    /*public static  void setValue(String property, Object oldEntity, Object entity, SystemLog systemLog)
    throws NoSuchFieldException,SecurityException,IllegalArgumentException,IllegalAccessException {
        Field field = oldEntity.getClass().getDeclaredField(property);
        field.setAccessible(true);
        Object oldValue = field.get(oldEntity);
        Object newValue = field.get(entity);
        //判断是否有更新
        boolean change = false;
        if (oldValue == null) {
            if (newValue != null) {
                change = true;
            }
        } else {
            if (newValue == null) {
                change = true;
            } else {
                if (oldValue instanceof String) {
                    if (!oldValue.equals(newValue)) {
                        change = true;
                    }
                else if (oldEntity instanceof Date) {
                    Date oldDate = (Date) oldValue;
                    Date newDate = (Date) newValue;
                    if (oldDate.compareTo(newDate) != 0) {
                        change = true;
                    }
                 else if (oldValue instanceof BigDecimal) {
                    BigDecimal oldB = (BigDecimal) oldValue;
                    BigDecimal newB = (BigDecimal) newValue;
                    if (oldB.compareTo(newB) != 0) {
                        change = true;
                    }
                } else if (!oldValue.equals(newValue)) {
                    change = true;
                }
            }
        }
        }
    }if(!change) {
            return;
        }
        *//*if (oldValue instanceof Date) {

        }*//*
     }*/
}



