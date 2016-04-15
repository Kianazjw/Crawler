package com.example.kiana.crawler;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListView listView;
    private static final String urlPath = "http://api.ecjtu.org/func/jwc/student?class_id=201421100103";
    private List<Person> persons;
    private JsonParseAdapter adapter;
    private Button button ;
    MyJsonParse jsonParse = new MyJsonParse();
    String JsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.button);
        persons = new ArrayList<Person>();
        adapter = new JsonParseAdapter(this);//适配器
        getJson();//获得数据

        listView.setAdapter(adapter);//加载适配器
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                adapter.getData(persons);//列表加载数据

            }
        });

    }

    public void getJson() {//将获得的Json数据转化成对象  耗时操作 ，线程处理
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    JsonString = jsonParse.getString(urlPath);//通过URL获得数据  String类型的数据
                    JSONArray jsonArray = new JSONArray(JsonString);
                    //循环添加到列表
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        String student_name = item.getString("student_name");
                        System.out.println("student_name----" + student_name);
                        String student_id = item.getString("student_id");
                        String status = item.getString("status");
                        String class_name = item.getString("class_name");

                        Person person = new Person(student_name, student_id,
                                status, class_name);
                        persons.add(person);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
