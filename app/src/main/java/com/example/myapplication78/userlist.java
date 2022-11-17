package com.example.myapplication78;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class userlist extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> Name, Description, Price;
    DBHelper DB;
    MyAdapter adapter;
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        DB = new DBHelper(this);
        Name = new ArrayList<>();
        Description = new ArrayList<>();
        Price = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        add = (FloatingActionButton) findViewById(R.id.fab);
        adapter = new MyAdapter(this,Name,Description,Price);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();

        add.setOnClickListener(add -> startActivity(new Intent(userlist.this, MainActivity.class)));
    }


    private void displaydata() {
        Cursor cursor = DB.getdata();
        if (cursor.getCount()==0){
            Toast.makeText(userlist.this, "No Entry Exists", Toast.LENGTH_SHORT).show();

//        }else{
//            Name.add(cursor.getString(0));
//            Description.add(cursor.getString(1));
//            Price.add(cursor.getString(2));
    }
}}