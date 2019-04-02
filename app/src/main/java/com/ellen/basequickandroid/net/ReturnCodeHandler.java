package com.ellen.basequickandroid.net;

public class ReturnCodeHandler{
    private ReturnCode returnCode;

    public ReturnCodeHandler(ReturnCode returnCode){
        this.returnCode = returnCode;
    }

    public void checkOuterCode(String url,int outerCode,OuterCodeHandlerCallback outerCodeHandlerCallback){
        outerCodeHandlerCallback.outerCodeHandlerCallback(returnCode.startCheckOuterCode(url,outerCode));
    }

    public void checkInternalCode(String url,Object internalCode,InternalCodeHandlerCallback internalCodeHandlerCallback){
        internalCodeHandlerCallback.internalCodeHandlerCallback(returnCode.startCheckInternalCode(url,internalCode));
    }

    public interface OuterCodeHandlerCallback{
        void outerCodeHandlerCallback(ReturnCode.ServerOuterCodeMessage serverOuterCodeMessage);
    }

    public interface InternalCodeHandlerCallback{
        void internalCodeHandlerCallback(ReturnCode.ServerInternalCodeMessage serverInternalCodeMessage);
    }

}
