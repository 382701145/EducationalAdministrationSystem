package com.yyw.eas.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.yyw.eas.bean.Course;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */

public class CoursePraiseAdapter extends BaseExpandableListAdapter {

    private List<Course> courseList;
    private Context context;

    public CoursePraiseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(context);
        textView.setText(courseList.get(groupPosition).getCourseInfoList().get(childPosition).getName());
        textView.setTextColor(Color.RED);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        return textView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(context);
        textView.setText(courseList.get(groupPosition).getSchoolYear());
        textView.setTextColor(Color.RED);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        return textView;
    }

    @Override
    public Course getGroup(int i) {
        return courseList.get(i);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return courseList.get(groupPosition).getCourseInfoList().get(childPosition).getName();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public int getChildrenCount(int i) {
        return courseList.get(i).getCourseInfoList().size();
    }

    @Override
    public int getGroupCount() {
        return courseList.size();
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
