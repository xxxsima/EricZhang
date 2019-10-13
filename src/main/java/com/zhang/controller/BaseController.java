package com.zhang.controller;

import com.zhang.util.BaseBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * @Author: King
 * @Create: 2019-10-06 13:55
 * @Desc:
 **/
public abstract class BaseController {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String MESSAGE = "message";

    @Autowired
    protected MessageSource messageSource;
    @Autowired
    protected LocaleResolver localeResolver;
    @Autowired
    protected HttpSession session;

    protected Locale getLocale(){
        // 消息的参数化和国际化配置
        Locale locale = (Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if(locale == null){
            locale = Locale.US;
        }

        return locale;
    }

    protected String getLocaleMessage(String message){
        return messageSource.getMessage(message,null,getLocale());
    }

    protected String getLocaleMessage(BaseBusinessException e){
        return e.getMessage(messageSource,getLocale());
    }

}
