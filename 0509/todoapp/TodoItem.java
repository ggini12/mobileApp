package com.example.todoapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TodoItem {
    private String task;
    private boolean isCompleted;
    private long id;
    private String datetime;

    public TodoItem(String task) {
        this.task = task;
        this.isCompleted = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));  // 한국 시간대로 설정
        this.id = System.currentTimeMillis();
        this.datetime = sdf.format(new Date());

    }
    public String getDate(){
        return this.datetime;
    }
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public long getId() {
        return id;
    }
}
