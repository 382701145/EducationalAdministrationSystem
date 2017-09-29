package com.yyw.eas.presenter;


import com.yyw.eas.bean.User;
import com.yyw.eas.callback.OnLoadCallback;
import com.yyw.eas.model.IUserLoginModel;
import com.yyw.eas.model.UserLoginModel;
import com.yyw.eas.utils.Constant;
import com.yyw.eas.utils.NetworkUtils;
import com.yyw.eas.view.IUserLoginView;

public class UserLoginPresenter implements IUserLoginPresenter {

    public IUserLoginView loginView;
    public IUserLoginModel userLoginModel;

    public UserLoginPresenter(IUserLoginView loginView) {

        this.loginView = loginView;
        this.userLoginModel = new UserLoginModel();
    }

    @Override
    public void login() {

        if (!NetworkUtils.isAvailable(loginView.getContext())) {
            loginView.onLoginFailed(Constant.Network.Network_ERROR);
        } else {

            User user = new User();
            user.setUsername(loginView.getUserName());
            user.setPassword(loginView.getPassword());

            if (!user.getUsername().equals("") && !user.getPassword().equals("")) {

                userLoginModel.login(loginView.getContext(), user, new OnLoadCallback() {

                    @Override
                    public void beforeLoad() {
                        loginView.showLoading();
                    }

                    @Override
                    public void onLoadComplete() {
                        loginView.hideLoading();
                    }

                    @Override
                    public void onSuccess() {
                        loginView.onLoginSuccess();
                    }

                    @Override
                    public void onFailed(int failedMessage) {
                        loginView.onLoginFailed(failedMessage);
                    }
                });
            } else {
                loginView.onLoginFailed(Constant.Login.EMPTY_USERNAME_OR_EMPTY_PASSWORD);
            }

        }
    }
}
