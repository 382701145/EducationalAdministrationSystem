package com.yyw.eas.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import com.yyw.eas.R;
import com.yyw.eas.adapter.CoursePraiseAdapter;
import com.yyw.eas.bean.Course;
import com.yyw.eas.presenter.CoursePraisePresenter;
import com.yyw.eas.presenter.ICoursePraisePresenter;
import com.yyw.eas.widget.CustomToast;
import com.yyw.eas.widget.load.SpotsDialog;

import java.util.List;
import java.util.Map;

public class CoursePraiseActivity extends AppCompatActivity implements ICoursePraiseView {

    private AlertDialog loadDialog;
    private ICoursePraisePresenter coursePraisePresenter;
    private ExpandableListView expandableListView;
    private CoursePraiseAdapter coursePraiseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_praise);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_course_praise);
        setTitle(getResources().getString(R.string.course_point_of_praise));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        loadDialog = new SpotsDialog(this);
        coursePraisePresenter = new CoursePraisePresenter(this);
        coursePraisePresenter.getCourseId();
    }

    @Override
    public void onSuccess(List<Course> courseList) {
        CustomToast.showToast(this, "size:" + courseList.size());

        if (coursePraiseAdapter == null) {
            coursePraiseAdapter = new CoursePraiseAdapter(this, courseList);
            expandableListView.setAdapter(coursePraiseAdapter);
        }
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
