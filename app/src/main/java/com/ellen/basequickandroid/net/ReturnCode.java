package com.ellen.basequickandroid.net;

import java.util.Map;

/**
 *  服务器外码内码状态定义 & 内码设置和状态对应定义
 * @param <T> 服务器外码对应的状态封装类型
 * @param <E> 服务器内码对应的类型
 * @param <F> 服务器内码对应的状态封装类型
 */
public class ReturnCode<T,E,F> {

    //定义外码 & 外码对应的状态
    private Map<Integer, T> actionOuterCodeMap;
    //定义内码 & 内码对应的状态
    private Map<E, F> actionInternalCodeMap;

    public ReturnCode(Map<Integer, T> actionOuterCodeMap,Map<E, F> actionInternalCodeMap){
        this.actionOuterCodeMap = actionOuterCodeMap;
        this.actionInternalCodeMap = actionInternalCodeMap;
    }

    public ServerOuterCodeMessage startCheckOuterCode(String url,int outerCode){
        return new ServerOuterCodeMessage(url,System.currentTimeMillis(),outerCode,actionOuterCodeMap.get(outerCode));
    }

    public ServerInternalCodeMessage startCheckInternalCode(String url,E e){
        return new ServerInternalCodeMessage(url,System.currentTimeMillis(),e,actionInternalCodeMap.get(e));
    }

    /**
     * 服务器外码封装类
     */
    public class ServerOuterCodeMessage {

        //记录url地址
        private String url;
        //记录处理的时间
        private long handlerTime;
        //记录外码
        private int outerCode;
        //记录外对应的状态
        private T handlerResult;

        public ServerOuterCodeMessage(String url, long handlerTime, int outerCode, T handlerResult) {
            this.url = url;
            this.handlerTime = handlerTime;
            this.outerCode = outerCode;
            this.handlerResult = handlerResult;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getHandlerTime() {
            return handlerTime;
        }

        public void setHandlerTime(long handlerTime) {
            this.handlerTime = handlerTime;
        }

        public int getOuterCode() {
            return outerCode;
        }

        public void setOuterCode(int outerCode) {
            this.outerCode = outerCode;
        }

        public T getHandlerResult() {
            return handlerResult;
        }

        public void setHandlerResult(T handlerResult) {
            this.handlerResult = handlerResult;
        }
    }

    /**
     * 服务器内码封装类
     */
    public class ServerInternalCodeMessage{

        //记录url地址
        private String url;
        //记录处理的时间
        private long handlerTime;
        //记录内码
        private E internalCode;
        //记录内码的状态
        private F handlerResult;

        public ServerInternalCodeMessage(String url, long handlerTime, E internalCode, F handlerResult) {
            this.url = url;
            this.handlerTime = handlerTime;
            this.internalCode = internalCode;
            this.handlerResult = handlerResult;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getHandlerTime() {
            return handlerTime;
        }

        public void setHandlerTime(long handlerTime) {
            this.handlerTime = handlerTime;
        }

        public E getInternalCode() {
            return internalCode;
        }

        public void setInternalCode(E internalCode) {
            this.internalCode = internalCode;
        }

        public F getHandlerResult() {
            return handlerResult;
        }

        public void setHandlerResult(F handlerResult) {
            this.handlerResult = handlerResult;
        }
    }

}
