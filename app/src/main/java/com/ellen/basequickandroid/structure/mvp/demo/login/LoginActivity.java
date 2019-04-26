package com.ellen.basequickandroid.structure.mvp.demo.login;

import android.view.View;
import android.widget.Button;

import com.ellen.basequickandroid.R;
import com.ellen.basequickandroid.base.BaseActivity;
import com.ellen.basequickandroid.structure.mvp.BaseMvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginAgreement.LoginAgreementView, BaseActivity.ButterKnifeInterface {

    @BindView(R.id.bt)
    Button btLogin;

    @OnClick(R.id.bt)
    void onClick(View view){
        login("ellen","1234");
    }

    @Override
    protected void setStatus() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void destory() {

    }

    @Override
    protected Boolean isSetVerticalScreen() {
        return null;
    }

    @Override
    public void login(String account, String password) {
         if(checkAccountPassword(account,password)){
             mPresenter.login(account,password);
         }
    }

    @Override
    public boolean checkAccountPassword(String account, String password) {
        return true;
    }

    @Override
    public void loginSuccess(String json) {
        btLogin.setText("登陆成功");
    }

    @Override
    public void loginFailure(String errMessage) {
        btLogin.setText("登陆失败");
    }

    @Override
    public void initButterKnife() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initMVP() {
        mPresenter = new LoginPresenter();
        mPresenter.mModel = new LoginModel();
        mPresenter.mView = this;
    }
}
