package com.ellen.basequickandroid.base.exception;

/**
 * 非运行时异常的基类
 */
public class BaseNoRuntimeException extends Exception{

    private String errorCode;
    private String errorMessage;

    public BaseNoRuntimeException(String errorCode,String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorCode+""+errorMessage;
    }

}
