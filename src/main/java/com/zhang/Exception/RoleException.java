package com.zhang.Exception;

import com.zhang.model.Role;
import com.zhang.util.BaseBusinessException;

/**
 * @Author: King
 * @Create: 2019-11-29 13:27
 * @Desc:
 **/
public class RoleException extends  BaseBusinessException {


        public RoleException(String message) {
            super(message);
        }
        public RoleException(String formatMessage, String... args){
            super(formatMessage,args);

    }
}
