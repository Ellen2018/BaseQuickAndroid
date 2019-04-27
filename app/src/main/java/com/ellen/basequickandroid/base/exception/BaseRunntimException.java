package com.ellen.basequickandroid.base.exception;

/**
 * 运行时异常的基类
 */
public class BaseRunntimException extends RuntimeException {

    private String errorCode;
    private String errorMessage;

    public BaseRunntimException(String errorCode,String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorCode+""+errorMessage;
    }

}
