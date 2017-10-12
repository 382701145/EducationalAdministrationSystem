package com.yyw.eas.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yyw.eas.R;

public class CoursePraiseActivity extends AppCompatActivity implements ICoursePraiseView{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_praise);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_course_praise);
        setTitle(getResources().getString(R.string.course_point_of_praise));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void flushView() {

    }
}
