package com.example.myapplication78;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class userlist extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> Name, Description, Price;
    DBHelper DB;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        DB = new DBHelper(this);
        Name = new ArrayList<>();
        Description = new ArrayList<>();
        Price = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this,Name,Description,Price);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
    }

    private void displaydata() {
        Cursor cursor = DB.getdata();
//        if (cursor.getCount()==0){
//            Toast.makeText(userlist.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
//
//        }else{
//            Name.add(cursor.getString(1));
//            Description.add(cursor.getString(2));
//            Price.add(cursor.getString(3));
        //
    }
}