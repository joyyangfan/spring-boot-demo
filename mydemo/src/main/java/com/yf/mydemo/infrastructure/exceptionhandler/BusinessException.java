package com.yf.mydemo.infrastructure.exceptionhandler;

import com.yf.mydemo.infrastructure.resp.RespCode;
import com.yf.mydemo.infrastructure.resp.ResultCode;

import java.io.Serializable;

/**
 * 为应用层提供基础异常 传递消息
 * 简单业务异常 此类异常一般为有明确业务码的已知异常 默认不爬取堆栈信息
 */
public class BusinessException extends StateCodeException {


    private final Serializable data;

    public BusinessException() {
        this(RespCode.FAIL);
    }

    public BusinessException(String code, String message) {
        this(code, message, null, false);
    }

    public BusinessException(ResultCode resultCode) {
        this(resultCode, false);
    }

    public BusinessException(String code, String message, Serializable data) {
        this(code, message, data, false);
    }


    public BusinessException(ResultCode resultCode, boolean writableStackTrace) {
        this(resultCode.getCode(), resultCode.getMessage(), null, writableStackTrace);
    }

    public BusinessException(String code, String message, Serializable data, boolean writableStackTrace) {
        super(code, message, writableStackTrace);
        this.data = data;
    }

    public Serializable getData() {
        return data;
    }
}
