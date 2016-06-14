package com.example.kiana.crawler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表适配器
 */

public class JsonParseAdapter extends BaseAdapter {

    List<Person> persons = new ArrayList<Person>();
    private LayoutInflater inflater;
    private Context context;

    public JsonParseAdapter(Context context) {
        this.context = context;
//                                                                      取得xml里定义的view
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LayoutInflater这个类的作用类似于findViewById()。
//        不同点是LayoutInflater是用来找res/layout/下的xml布局文件，并且实例化
//        而findViewById()是找xml布局文件下的具体widget控件(如Button、TextView等)。

//        学习 http://blog.csdn.net/guolin_blog/article/details/12921889
    }

    public void getData(List<Person> persons) {
        this.persons = persons;
        this.notifyDataSetChanged(); //更新UI
    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private TextView student_name;
    private TextView student_id;
    private TextView status;
    private TextView class_name;

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = inflater.inflate(R.layout.data_item, null);

        student_name = (TextView) view.findViewById(R.id.student_name);
        student_id = (TextView) view.findViewById(R.id.student_id);
        status = (TextView) view.findViewById(R.id.status);
        class_name = (TextView) view.findViewById(R.id.class_name);

        student_name.setText("姓名： " + persons.get(position).getStudent_name());
        student_id.setText("学号： " + persons.get(position).getStudent_id());
        status.setText("状态： " + persons.get(position).getStatus());
        class_name.setText("专业： " + persons.get(position).getClass_name());

        return view;
    }

}
