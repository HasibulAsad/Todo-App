package com.example.newtodoapp;

import java.util.Date;
import java.util.UUID;

public class Task {
    String id;
    String text;
    boolean isCompleted;
    Date Created;

    Task(String text) {
        id = UUID.randomUUID().toString();
        this.text = text;
        isCompleted = false;
        Created = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Date getCreated() {
        return Created;
    }

}
