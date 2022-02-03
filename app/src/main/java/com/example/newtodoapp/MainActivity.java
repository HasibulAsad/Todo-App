package com.example.newtodoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Task> tasks = new ArrayList<>();
    Adapter adapter;
    DBHelper dbHelper;
    EditText editText;
    Button addbtn;
    OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void OnitemClick(int position) {
            Toast.makeText(getApplicationContext(), "Position : " + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void OnitemCheaked(int position, boolean checked) {
            tasks.get(position).setCompleted(checked);
            dbHelper.saveTasks(tasks);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        tasks = dbHelper.getTasks();

        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.idedittext);
        addbtn = findViewById(R.id.btnadd);


        addbtn.setOnClickListener(v -> addtasks());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(tasks, onClickListener);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_menu) {
            Iterator<Task> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().isCompleted) {
                    iterator.remove();
                }
                adapter.notifyDataSetChanged();
                dbHelper.saveTasks(tasks);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void addtasks() {
        tasks.add(0, new Task(editText.getText().toString()));
        editText.setText(null);
        adapter.notifyItemInserted(0);
        dbHelper.saveTasks(tasks);
    }
}