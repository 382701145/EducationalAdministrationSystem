package com.yyw.eas.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.yyw.eas.R;
import com.yyw.eas.utils.Constant;
import com.yyw.eas.utils.SPUtils;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView studentName = (TextView) findViewById(R.id.tv_student_name);
        studentName.setText((String) SPUtils.getPrefParams(this, Constant.StudentName.STUDENT_NAME, getResources().getString(R.string.student)));


    }


}
