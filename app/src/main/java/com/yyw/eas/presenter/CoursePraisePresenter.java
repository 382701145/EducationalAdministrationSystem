package com.yyw.eas.presenter;


import com.yyw.eas.callback.OnLoadCallback;
import com.yyw.eas.model.CoursePraiseModel;
import com.yyw.eas.model.ICoursePraiseModel;
import com.yyw.eas.utils.Constant;
import com.yyw.eas.utils.NetworkUtils;
import com.yyw.eas.view.ICoursePraiseView;

import java.util.Map;

public class CoursePraisePresenter implements ICoursePraisePresenter {

    private ICoursePraiseView coursePraiseView;
    private ICoursePraiseModel coursePraiseModel;

    public CoursePraisePresenter(ICoursePraiseView coursePraiseView) {
        this.coursePraiseView = coursePraiseView;
        this.coursePraiseModel = new CoursePraiseModel();
    }


    @Override
    public void getCourseId() {

        if (!NetworkUtils.isAvailable(coursePraiseView.getContext())) {
            coursePraiseView.onLoginFailed(Constant.Network.Network_ERROR);
        } else {
            coursePraiseModel.getCourseId(coursePraiseView.getContext(), new OnLoadCallback() {
                @Override
                public void beforeLoad() {
                    coursePraiseView.showLoading();
                }

                @Override
                public <T> void onSuccess(T t) {

                    if (t instanceof Map) {
                        Map map = (Map) t;
                        coursePraiseView.onSuccess(map);
                    }
                }

                @Override
                public void onFailed(int failedMessage) {
                    coursePraiseView.onLoginFailed(failedMessage);
                }

                @Override
                public void onLoadComplete() {
                    coursePraiseView.hideLoading();
                }
            });
        }
    }

    @Override
    public void getCourseInfo(int index) {

    }
}
