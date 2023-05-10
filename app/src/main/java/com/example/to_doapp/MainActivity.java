package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button add_task;
    EditText txt_task;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    List<String> list;

    DatabaseHelper dbh;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_task  =(Button) findViewById(R.id.btn_addtask);
        txt_task =  (EditText) findViewById(R.id.txt_task);
        listView = (ListView) findViewById(R.id.list_view);

        list = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this,R.layout.list_view_layout,list);
        listView.setAdapter(arrayAdapter);

        dbh = new DatabaseHelper(getApplicationContext());


        viewData();
        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String task = txt_task.getText().toString();
               if (task.isEmpty()){
                   Toast.makeText(MainActivity.this, "Enter Something", Toast.LENGTH_SHORT).show();
               }else {
                   dbh.insertData(task);
                   Toast.makeText(MainActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                   txt_task.setText("");
                   list.clear();
                   viewData();
               }
            }
        });




    }

    private void viewData(){
        Cursor cursor = dbh.viewData();

        if (cursor.getCount()==0){
            Toast.makeText(this,"No Data Exists" , Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                list.add(cursor.getString(1));
                listView.setAdapter(arrayAdapter);
            }
        }
    }



}