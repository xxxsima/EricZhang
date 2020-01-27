package com.zhang.Exception;

import com.zhang.util.BaseBusinessException;

import javax.print.attribute.standard.MediaSize;

/**
 * @Author: King
 * @Create: 2019-12-05 12:42
 * @Desc:
 **/
public class ClassException extends BaseBusinessException {
    public ClassException(String message){super(message);}
    public ClassException(String formatMessage,String... args) {
        super(formatMessage,args);
    }
}
