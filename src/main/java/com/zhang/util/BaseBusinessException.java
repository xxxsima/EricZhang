package com.zhang.util;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @Author: King
 * @Create: 2019-08-28 22:03
 * @Desc:
 **/
public class BaseBusinessException extends RuntimeException {
    public BaseBusinessException() {

    }

    public BaseBusinessException(String message) {
        super(message);
        this.formatMessage = message;
    }

    public BaseBusinessException(String message,String... args){
        super(message);
        this.formatMessage = message;
        this.args = args;
    }


    /**
     * 未考虑国际化，不推荐使用
     * @return
     */
    @Deprecated
    @Override
    public String getMessage(){
        return super.getMessage();
    }

    /**
     * 考虑国际化
     * @param messageSource
     * @param locale
     * @return
     */
    public String getMessage(MessageSource messageSource, Locale locale){
        return messageSource.getMessage(this.formatMessage,this.args,locale);
    }

    private String formatMessage;
    private String[] args;

}

