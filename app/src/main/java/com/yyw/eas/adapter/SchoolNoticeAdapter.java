package com.yyw.eas.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyw.eas.R;
import com.yyw.eas.bean.Article;
import com.yyw.eas.utils.DateUtils;
import com.yyw.eas.utils.LogUtils;
import com.yyw.eas.utils.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */

public class SchoolNoticeAdapter extends BaseAdapter {


    private List<Article.Rows> rows;
    private Context context;

    public SchoolNoticeAdapter(Context context, List<Article.Rows> rows) {
        this.context = context;
        this.rows = rows;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_school_notice, null);
            holder = new ViewHolder();
            holder.title = (TextView)convertView.findViewById(R.id.tv_school_notice_title);
            holder.time = (TextView)convertView.findViewById(R.id.tv_school_notice_time);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        holder.title.setText(rows.get(i).getA_Title().trim());
        String dateTime = rows.get(i).getA_DateTime();
        String numberFromString = StringUtils.getNumberFromString(dateTime);
        String time = DateUtils.getTime(numberFromString);
        holder.time.setText(time);
        return convertView;
    }

    @Override
    public Object getItem(int i) {
        return rows.get(i);
    }

    @Override
    public int getCount() {
        return rows.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class ViewHolder
    {
        public TextView title;
        public TextView time;
    }
}
