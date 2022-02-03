package com.example.newtodoapp;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DBHelper {
    SharedPreferences taskDatabase;
    Gson gson;


    public DBHelper(Context context) {
        taskDatabase = context.getSharedPreferences("taskDatabase", Context.MODE_PRIVATE);
        gson = new Gson();
    }


    public void saveTasks(ArrayList<Task> tasks) {
        SharedPreferences.Editor editor = taskDatabase.edit();
        editor.putString("tasks", gson.toJson(tasks));
        editor.apply();
    }

    public ArrayList<Task> getTasks() {
        String taskString = taskDatabase.getString("tasks", null);

        Type taskListType = new TypeToken<ArrayList<Task>>() {
        }.getType();
        ArrayList<Task> tasks = gson.fromJson(taskString, taskListType);
        if (tasks!= null)
            return tasks;
        else
            return new ArrayList<>();

    }
}
