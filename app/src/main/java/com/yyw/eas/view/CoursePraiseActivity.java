package com.yyw.eas.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yyw.eas.R;
import com.yyw.eas.bean.Course;
import com.yyw.eas.presenter.CoursePraisePresenter;
import com.yyw.eas.presenter.ICoursePraisePresenter;
import com.yyw.eas.presenter.SchoolNoticePresenter;
import com.yyw.eas.widget.load.SpotsDialog;

import java.util.List;
import java.util.Map;

public class CoursePraiseActivity extends AppCompatActivity implements ICoursePraiseView{

    private AlertDialog loadDialog;
    private ICoursePraisePresenter coursePraisePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_praise);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_course_praise);
        setTitle(getResources().getString(R.string.course_point_of_praise));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        loadDialog = new SpotsDialog(this);
        coursePraisePresenter = new CoursePraisePresenter(this);
        coursePraisePresenter.getCourseId();
    }

    @Override
    public void onSuccess(Map<String, List<Course>> courseMap) {

    }

    @Override
    public void onLoginFailed(int failedMessage) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {
        if (loadDialog != null) {
            loadDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (loadDialog != null) {
            loadDialog.cancel();
            ((SpotsDialog) loadDialog).setEnable(false);
        }
    }

    @Override
    public void flushView() {

    }
}
