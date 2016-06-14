package com.example.kiana.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class JsonParse {

    private StringBuffer sb;

    public String getString(String url) {

        try {
            URL httpUrl = new URL(url);
            try {
                HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
                connection.setReadTimeout(5000);// 等待时间
                connection.setRequestMethod("GET");// GET方式请求数据

                // 在java中,可以使用InputStream对文件进行读取,就是字节流的输入
                InputStream inputStream = connection.getInputStream();
                // InputStreamReader 将字节流转换为字符流是字节流通向字符流的桥梁
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                // BufferedReader包装字符流，将字符流放入缓存里
                BufferedReader reader = new BufferedReader(inputStreamReader);

//                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                sb = new StringBuffer();
                String str;

                while ((str = reader.readLine()) != null) {
                    sb.append(str); //连接一个字符串到末尾
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
