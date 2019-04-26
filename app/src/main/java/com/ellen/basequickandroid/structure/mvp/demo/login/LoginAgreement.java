package com.ellen.basequickandroid.structure.mvp.demo.login;

import com.ellen.basequickandroid.structure.mvp.basemvp.BaseModel;
import com.ellen.basequickandroid.structure.mvp.basemvp.BasePresenter;
import com.ellen.basequickandroid.structure.mvp.basemvp.BaseView;

public interface LoginAgreement {

    //协议化M层
    interface LoginAgreementModel extends BaseModel{
        boolean login(String account,String password);
    }

    //协议化View层
    interface LoginAgreementView extends BaseView{
        //登陆的时候回调
        void login(String account,String password);
        //验证账号密码的规范
        boolean checkAccountPassword(String account,String password);
        //登陆成功回调,json为服务器返回的Json
        void loginSuccess(String json);
        //登陆失败回调,errMessage为错误信息
        void loginFailure(String errMessage);
    }

    abstract class LoginAgreementPresenter extends BasePresenter<LoginAgreementModel,LoginAgreementView>{
        protected abstract void login(String account,String password);
    }
}
