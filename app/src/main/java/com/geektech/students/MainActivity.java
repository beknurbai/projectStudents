package com.geektech.students;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements onClick{
    /*Домашнее задание:
    Создать приложение для учета студентов - должны
    быть возможности создания, изменения. Обязательные поля
    имя, фамилия, группа. Сами можете добавить дополнительные
    поля (год рождения, адрес, оценки и т.д.). На главном экране
    отобразить только имя и фамилию. При выборе студента показать
    полную информацию с возможностью редактирования*/
    private Button btn_add_students;
    private static final int REQUEST_KOD = 1;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private Students students;
    private ArrayList<Students> list = new ArrayList<>();
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add_students = findViewById(R.id.btn_add_students);
        recyclerView = findViewById(R.id.recycler_view_ma);
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
        adapter.onClick = this;

        btn_add_students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }

    @Override
    public void click(int pos , Students students) {
        list.remove(pos);
        Intent intent = new Intent(this,CreateStudentsActivity.class);
        intent.putExtra("result", students);
        startActivityForResult(intent,REQUEST_KOD);
    }



    public void start() {
        Intent intent = new Intent(MainActivity.this, CreateStudentsActivity.class);
        startActivityForResult(intent, REQUEST_KOD);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_KOD && resultCode == RESULT_OK && data != null) {
            students = (Students) data.getSerializableExtra(CreateStudentsActivity.EXTRA_KEY);
            list.add(position,students);
            adapter.update(list);
            adapter.notifyDataSetChanged();
        }
    }

}
