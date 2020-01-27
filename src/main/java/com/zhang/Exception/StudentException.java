package com.zhang.Exception;

import com.zhang.util.BaseBusinessException;

/**
 * @Author: King
 * @Create: 2019-12-07 16:55
 * @Desc:
 **/
public class StudentException extends BaseBusinessException {
    public StudentException(String message) {super(message);}

    public StudentException(String formatMessage,String... args) {
        super(formatMessage,args);
    }
}
