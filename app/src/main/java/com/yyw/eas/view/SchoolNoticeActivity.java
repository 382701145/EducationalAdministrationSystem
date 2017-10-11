package com.yyw.eas.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yyw.eas.R;
import com.yyw.eas.adapter.SchoolNoticeAdapter;
import com.yyw.eas.bean.Article;
import com.yyw.eas.presenter.ISchoolNoticePresenter;
import com.yyw.eas.presenter.SchoolNoticePresenter;
import com.yyw.eas.utils.DateUtils;
import com.yyw.eas.utils.LogUtils;
import com.yyw.eas.widget.load.SpotsDialog;
import com.yyw.eas.widget.pulltofresh.XListView;

import java.util.List;

public class SchoolNoticeActivity extends AppCompatActivity implements ISchoolNoticeView, XListView.IXListViewListener {

    private ISchoolNoticePresenter schoolNoticePresenter;
    private int defaultIndex = 1;
    private AlertDialog loadDialog;
    private XListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_notice);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(getResources().getString(R.string.school_notice));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (XListView) findViewById(R.id.listView_schoolNotice);
        listView.setPullRefreshEnable(true);
        listView.setPullLoadEnable(true);
        listView.setAutoLoadEnable(true);
        listView.setXListViewListener(this);
        listView.setRefreshTime(DateUtils.getTime());

        loadDialog = new SpotsDialog(this);
        schoolNoticePresenter = new SchoolNoticePresenter(this);
        schoolNoticePresenter.getSchoolNotice(defaultIndex);
    }

    @Override
    public void showLoading() {
        loadDialog.show();
    }

    @Override
    public void hideLoading() {
        loadDialog.cancel();
    }

    @Override
    public void onLoginSuccess(Article article) {
        if (article != null) {
            onLoad();
            List<Article.Rows> rows = article.getRows();
            SchoolNoticeAdapter schoolNoticeAdapter = new SchoolNoticeAdapter(this, rows);
            listView.setAdapter(schoolNoticeAdapter);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        schoolNoticePresenter.getSchoolNotice(defaultIndex);
    }

    @Override
    public void onLoadMore() {
        schoolNoticePresenter.getSchoolNotice(defaultIndex);
    }

    private void onLoad() {
        listView.stopRefresh();
        listView.stopLoadMore();
        listView.setRefreshTime(DateUtils.getTime());
    }

}
