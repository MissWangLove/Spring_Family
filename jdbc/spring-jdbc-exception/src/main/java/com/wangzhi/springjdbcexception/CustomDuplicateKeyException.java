package com.wangzhi.springjdbcexception;


import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

/**
 * @author : wz157
 * @date : 2020-05-21 20:54
 * @description : 自定义主键重复异常类
 * @path : com.wangzhi.springjdbcexception.CustomDuplicateKeyException
 * @modifiedBy : wz157
 * @modifyDate : 2020-05-21 20:54
 */

public class CustomDuplicateKeyException extends DuplicateKeyException {
    public CustomDuplicateKeyException(String msg) {
        super(msg);
    }

    public CustomDuplicateKeyException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
