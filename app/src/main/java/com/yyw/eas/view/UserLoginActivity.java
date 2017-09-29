package com.yyw.eas.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yyw.eas.R;
import com.yyw.eas.presenter.IUserLoginPresenter;
import com.yyw.eas.presenter.UserLoginPresenter;
import com.yyw.eas.utils.Constant;
import com.yyw.eas.utils.SPUtils;
import com.yyw.eas.widget.CustomToast;
import com.yyw.eas.widget.load.SpotsDialog;

public class UserLoginActivity extends Activity implements IUserLoginView {

    private EditText username;
    private EditText password;
    private IUserLoginPresenter userLoginPresenter;
    private AlertDialog loadDialog;
    private final static int FAILED_MESSAGE = 1;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case FAILED_MESSAGE:
                    String message = (String) msg.obj;
                    CustomToast.showToast(UserLoginActivity.this, message);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(loginOnClickListener);
        userLoginPresenter = new UserLoginPresenter(this);
        loadDialog = new SpotsDialog(this);

        String spUsername = (String) SPUtils.getPrefParams(this, Constant.User.USERNAME, "");
        String spPassword = (String) SPUtils.getPrefParams(this, Constant.User.PASSWORD, "");

        if (!spUsername.equals("") && !spPassword.equals("")) {
            username.setText(spUsername);
            password.setText(spPassword);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    View.OnClickListener loginOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            userLoginPresenter.login();
        }
    };


    @Override
    public String getUserName() {
        return username.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password.getText().toString().trim();
    }

    @Override
    public void onLoginSuccess() {
        // TODO 开始查询用户详细数据,查询成功之后进入进入主界面
        loadDialog.cancel();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailed(int failedMessage) {
        String message = "";
        if (failedMessage == Constant.Login.EMPTY_USERNAME_OR_EMPTY_PASSWORD) {
            message = getString(R.string.the_user_name_or_password_can_not_be_empty);
        } else if (failedMessage == Constant.Login.INVALID_PASSWORD) {
            message = getString(R.string.invalid_password);
        } else if (failedMessage == Constant.Login.INVALID_USERNAME) {
            message = getString(R.string.invalid_username);
        } else if (failedMessage == Constant.Connect.ERROR) {
            //TODO 登录过程中出错
            message = getString(R.string.unable_to_connect_to_network_please_check_settings);
        } else if (failedMessage == Constant.Network.Network_ERROR) {
            message = getString(R.string.unable_to_connect_to_network_please_check_settings);
        }

        Message msg = Message.obtain();
        msg.what = FAILED_MESSAGE;
        msg.obj = message;
        handler.sendMessage(msg);
    }

    @Override
    public void clearUserName() {

    }

    @Override
    public void clearPassword() {

    }

    @Override
    public void showLoading() {
        loadDialog.show();
    }

    @Override
    public void hideLoading() {
        loadDialog.cancel();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
