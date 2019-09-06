package com.zhang.Exception;

import com.zhang.util.BaseBusinessException;

/**
 * @Author: King
 * @Create: 2019-09-05 16:50
 * @Desc:
 **/
public class UserException extends BaseBusinessException {
    public UserException(String message) {
        super(message);
    }
    public UserException(String formatMessage, String... args){
        super(formatMessage,args);
    }
}