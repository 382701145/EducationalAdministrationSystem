package com.yyw.eas.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.yyw.eas.R;

public class TeacherPraiseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_praise);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_teacher_praise);
        setTitle(getResources().getString(R.string.teacher_point_of_praise));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
