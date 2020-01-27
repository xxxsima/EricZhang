package com.zhang.Exception;

import com.zhang.util.BaseBusinessException;

/**
 * @Author: King
 * @Create: 2019-11-21 22:15
 * @Desc:
 **/
public class FunctionException extends BaseBusinessException {
    public FunctionException(String message) {
        super(message);
    }
    public FunctionException(String formatMessage,String ... args){
        super(formatMessage,args);
    }
}
