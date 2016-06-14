package com.example.kiana.crawler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//先把URL解析的json数据toString
//然后解析，构建一个Person类，然后把person添加到List<Person>中
//然后ListView.setAdapter(jsonAdapter).
//自己写一个jsonAdapter


public class MainActivity extends Activity {

    private ListView listView;
    private static final String urlPath = "http://api.ecjtu.org/func/jwc/student?class_id=201421100103";
    private List<Person> persons;
    private JsonParseAdapter jsonParseAdapter;
    private Button button;
    JsonParse jsonParse = new JsonParse();
    String JsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        button = (Button) findViewById(R.id.button);
        persons = new ArrayList<Person>();

        jsonParseAdapter = new JsonParseAdapter(this);//适配器

        getJson();//获得数据

        listView.setAdapter(jsonParseAdapter);//加载适配器

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                jsonParseAdapter.getData(persons);//列表加载数据
            }
        });

    }

    public void getJson() {
        //将获得的Json数据转化成对象  耗时操作 ，线程处理
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
                        Log.v("MainActivity", student_name);
                        String student_id = item.getString("student_id");
                        String status = item.getString("status");
                        String class_name = item.getString("class_name");

                        Person person = new Person(student_name, student_id, status, class_name);
                        //将类加到list中
                        persons.add(person);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
