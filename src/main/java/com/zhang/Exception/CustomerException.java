package com.zhang.Exception;

import com.zhang.util.BaseBusinessException;

/**
 * @Author: King
 * @Create: 2020-01-29 12:28
 * @Desc:
 **/
public class CustomerException  extends BaseBusinessException {
    public CustomerException(String message) {
        super(message);
    }
    public  CustomerException(String formatMessage,String... args) {
        super(formatMessage,args);
    }
}
