package com.zhang.Exception;

import com.zhang.model.SystemLog;
import com.zhang.util.BaseBusinessException;
import org.springframework.cglib.core.MethodWrapper;

import java.text.MessageFormat;

/**
 * @Author: King
 * @Create: 2019-12-03 21:00
 * @Desc:
 **/
public class SystemLogException  extends BaseBusinessException {
   public SystemLogException(String message) {super(message);}
   public SystemLogException(String formatMessage,String... args) {
       super(formatMessage,args);
   }
}
