package com.feicui.gaonews.activity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.feicui.gaonews.R;
import com.feicui.gaonews.adapter.NewsAdapter;
import com.feicui.gaonews.bean.News;
import com.feicui.gaonews.biz.ParserNews;
import com.feicui.gaonews.utils.HttpCilentGetOrPost;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private String url;
    private String data;
    private ListView lv_news;
    private NewsAdapter newsadapter;

    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            myHandleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        loadData();


    }

    private void loadData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<News> list = null;
                try {
                    data = HttpCilentGetOrPost.HttpGet(url);
                    ParserNews parserNews = new ParserNews(HomeActivity.this);
                    list = parserNews.parser(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Message msg = mhandler.obtainMessage();
                msg.what = 1;
                msg.obj = list;
                mhandler.sendMessage(msg);
            }
        }).start();
    }


    private void initView() {
        lv_news = (ListView) findViewById(R.id.lv_news);
        url = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";

    }

    protected void myHandleMessage(Message msg) {
        if (msg.what == 1) {
            ArrayList<News> datalist = (ArrayList<News>) msg.obj;

            newsadapter = new NewsAdapter(HomeActivity.this, datalist);
            lv_news.setAdapter(newsadapter);
        }
    }


}
