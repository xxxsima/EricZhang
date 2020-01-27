package com.zhang.Exception;

import com.zhang.model.Product;
import com.zhang.util.BaseBusinessException;

/**
 * @Author: King
 * @Create: 2019-11-30 23:45
 * @Desc:
 **/
public class ProductException extends BaseBusinessException {
    public ProductException(String message) {
        super(message);
    }
    public ProductException(String formatMessage,String... args) {
        super(formatMessage,args);
    }
}
