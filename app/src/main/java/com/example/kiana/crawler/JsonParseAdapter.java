package com.example.kiana.crawler;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 *
 * 列表适配器
 *
 */

public class JsonParseAdapter extends BaseAdapter {

    List<Person> persons = new ArrayList<Person>();
    private LayoutInflater inflater;
    private Context context;

    public JsonParseAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void getData(List<Person> persons) {
        this.persons = persons;
        this.notifyDataSetChanged();//加载后要更新列表用的  不然会异常
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

    private TextView student_name;//姓名
    private TextView student_id;//学号
    private TextView status;//状态
    private TextView class_name;//班级名称


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
        class_name.setText("班级： " + persons.get(position).getClass_name());

        return view;
    }

}
