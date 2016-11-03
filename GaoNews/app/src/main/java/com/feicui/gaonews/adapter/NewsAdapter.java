package com.feicui.gaonews.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.feicui.gaonews.R;
import com.feicui.gaonews.activity.HomeActivity;
import com.feicui.gaonews.bean.News;

import java.util.ArrayList;

/**
 * Created by 高 on 2016/11/2.
 */

public class NewsAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<News> list;


    public NewsAdapter(HomeActivity homeActivity, ArrayList<News> datalist) {
        this.context = homeActivity;
        this.list = datalist;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.layout_news_listview_item, null);
            holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            holder.tv_summary = (TextView) view.findViewById(R.id.tv_summay);
            holder.im_icon = (ImageView) view.findViewById(R.id.im_icon);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        News news = list.get(i);
        holder.tv_title.setText(news.getTitle());
        holder.tv_summary.setText(news.getSummary());
        holder.im_icon.setImageResource(R.drawable.timeicon);

        return view;
    }


    class ViewHolder {
        TextView tv_title;
        TextView tv_summary;
        ImageView im_icon;

    }
}
