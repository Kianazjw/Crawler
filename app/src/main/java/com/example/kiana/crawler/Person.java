package com.example.kiana.crawler;


public class Person {
    private String student_name;
    private String student_id;
    private String status;
    private String class_name;

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
    //构造函数
    public Person(String student_name, String student_id, String status, String class_name) {
        super();
        this.student_name = student_name;
        this.student_id = student_id;
        this.status = status;
        this.class_name = class_name;
    }

}
