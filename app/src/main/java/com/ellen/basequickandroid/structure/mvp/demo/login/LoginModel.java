package com.ellen.basequickandroid.structure.mvp.demo.login;

public class LoginModel implements LoginAgreement.LoginAgreementModel {

    @Override
    public boolean login(String account, String password) {
        return true;
    }
}
