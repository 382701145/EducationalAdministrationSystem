package com.yyw.eas.presenter;


import com.yyw.eas.bean.Article;
import com.yyw.eas.callback.OnLoadCallback;
import com.yyw.eas.model.ISchoolNoticeModel;
import com.yyw.eas.model.SchoolNoticeModel;
import com.yyw.eas.utils.Constant;
import com.yyw.eas.utils.NetworkUtils;
import com.yyw.eas.view.ISchoolNoticeView;

public class SchoolNoticePresenter implements ISchoolNoticePresenter {

    private ISchoolNoticeView schoolNoticeView;
    private ISchoolNoticeModel schoolNoticeModel;


    public SchoolNoticePresenter(ISchoolNoticeView schoolNoticeView) {
        this.schoolNoticeView = schoolNoticeView;
        this.schoolNoticeModel = new SchoolNoticeModel();
    }

    @Override
    public void getSchoolNotice(int index) {

        if (!NetworkUtils.isAvailable(schoolNoticeView.getContext())) {
            schoolNoticeView.onLoginFailed(Constant.Network.Network_ERROR);
        } else {
            schoolNoticeModel.getSchoolNotice(schoolNoticeView.getContext(), index, new OnLoadCallback() {
                @Override
                public void beforeLoad() {
                    schoolNoticeView.showLoading();
                }

                @Override
                public <T> void onSuccess(T t) {

                    if (t instanceof Article) {
                        Article article = (Article) t;
                        schoolNoticeView.onLoginSuccess(article);
                    }
                }

                @Override
                public void onFailed(int failedMessage) {
                    schoolNoticeView.onLoginFailed(failedMessage);
                }

                @Override
                public void onLoadComplete() {
                    schoolNoticeView.hideLoading();
                }
            });
        }
    }
}
