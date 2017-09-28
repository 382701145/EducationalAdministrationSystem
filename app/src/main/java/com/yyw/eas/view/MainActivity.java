package com.yyw.eas.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.yyw.eas.R;
import com.yyw.eas.utils.Constant;
import com.yyw.eas.utils.SPUtils;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView studentName = (TextView) findViewById(R.id.tv_student_name);
        studentName.setText((String) SPUtils.getPrefParams(this, Constant.StudentName.STUDENT_NAME, getResources().getString(R.string.student)));


        ImageButton teacherPraise = (ImageButton) findViewById(R.id.ib_teacher_praise);
        teacherPraise.setOnClickListener(this);
        ImageButton coursePraise = (ImageButton) findViewById(R.id.ib_course_praise);
        coursePraise.setOnClickListener(this);
        ImageButton schoolNotice = (ImageButton) findViewById(R.id.ib_school_notice);
        schoolNotice.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        Intent intent = new Intent();

        switch (view.getId()) {
            case R.id.ib_course_praise:
                intent.setClass(this, CoursePraiseActivity.class);
                break;
            case R.id.ib_school_notice:
                intent.setClass(this, SchoolNoticeActivity.class);
                break;
            case R.id.ib_teacher_praise:
                intent.setClass(this, TeacherPraiseActivity.class);
                break;
        }
        startActivity(intent);
    }
}
