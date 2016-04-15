package com.example.kiana.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MyJsonParse {

    private StringBuffer sb;

    public String getString(String url) {

        try {
            URL httpurl = new URL(url);
            try {
                HttpURLConnection connection = (HttpURLConnection) httpurl.openConnection();
                connection.setReadTimeout(5000);// 等待时间
                connection.setRequestMethod("GET");// GET方式请求数据

                sb = new StringBuffer();
                // 在java中,可以使用InputStream对文件进行读取,就是字节流的输入
                InputStream inputStream = connection.getInputStream();
                // InputStreamReader 将字节流转换为字符流是字节流通向字符流的桥梁
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                // BufferedReader包装字符流，将字符流放入缓存里
                BufferedReader reader = new BufferedReader(inputStreamReader);

                // BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String str;

                while ((str = reader.readLine()) != null) {
                    sb.append(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return sb.toString();

    }
}

