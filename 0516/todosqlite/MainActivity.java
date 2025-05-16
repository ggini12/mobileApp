package com.example.todolistsqlite;  // 실제 패키지명으로 변경

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTask;
    Button btnAdd;
    ListView listViewTasks;

    DBHelper dbHelper;
    ArrayList<String> taskList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTask = findViewById(R.id.editTask);
        btnAdd = findViewById(R.id.btnAdd);
        listViewTasks = findViewById(R.id.listViewTasks);

        dbHelper = new DBHelper(this);

        loadTasks();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task = editTask.getText().toString().trim();

                if (task.isEmpty()) {
                    Toast.makeText(MainActivity.this, "할 일을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean inserted = dbHelper.addTask(task);
                if (inserted) {
                    Toast.makeText(MainActivity.this, "할 일이 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    editTask.setText("");
                    loadTasks();
                } else {
                    Toast.makeText(MainActivity.this, "추가 실패", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 리스트 아이템 길게 누르면 삭제
        listViewTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                String taskToDelete = taskList.get(position);

                boolean deleted = dbHelper.deleteTask(taskToDelete);
                if (deleted) {
                    Toast.makeText(MainActivity.this, "할 일이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                    loadTasks();
                } else {
                    Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    // DB에서 할 일 불러와 리스트에 출력
    private void loadTasks() {
        taskList = dbHelper.getAllTasks();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listViewTasks.setAdapter(adapter);
    }
}
