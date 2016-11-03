package com.feicui.gaonews.biz;

import android.content.Context;

import com.feicui.gaonews.bean.News;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * JSON 数据解析
 */

public class ParserNews {
    private Context context;

    public ParserNews() {
    }

    public ParserNews(Context context) {

        this.context = context;
    }

    public ArrayList<News> parser(String dataStr) {

        ArrayList<News> newsList = new ArrayList<News>();
        try {
            JSONObject jsonObject = new JSONObject(dataStr);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                int nid = object.getInt("nid");
                String title = object.getString("title");
                String icon = object.getString("icon");
                String summary = object.getString("summary");
                String link = object.getString("link");
                int type = object.getInt("type");

                News news = new News(nid, title, summary, icon, link, type);
                newsList.add(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newsList;


    }
}
