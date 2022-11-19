package com.example.myapplication78;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
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
    Button Update, Delete,edit,insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.userentry);

        DB = new DBHelper(this);

        Name = new ArrayList<>();
        Description = new ArrayList<>();
        Price = new ArrayList<>();
        add = (FloatingActionButton) findViewById(R.id.fab);

        edit = (Button) findViewById(R.id.btnedit);
        Update = (Button) findViewById(R.id.btnupdate);
        Delete = (Button) findViewById(R.id.btndelete);
        insert = (Button) findViewById(R.id.btninsert);

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new MyAdapter(this,Name,Description,Price);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        displaydata();
        //hiding the update and view buttons and changing it to fill the form
        add.setOnClickListener(view -> startActivity(new Intent(userlist.this, MainActivity.class)));

        edit.setOnClickListener(view -> {
            startActivity(new Intent(userlist.this, MainActivity.class));
            Update.setVisibility(View.VISIBLE);
            Delete.setVisibility(View.VISIBLE);
            insert.setVisibility(View.GONE);
        });
    }

    //the method to show all the details in card
    private void displaydata() {
        Cursor cursor = DB.getdata();
        System.out.print(cursor);
        System.out.print("working2 ......");
        if (cursor.getCount()==0){
            Toast.makeText(userlist.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
        }else{
            if (cursor .moveToFirst()) {
                do {
                    Name.add(cursor.getString(0));
                    Description.add(cursor.getString(1));
                    Price.add(cursor.getString(2));
                } while (cursor .moveToNext());
            }
            cursor.close();
    }
        System.out.print("working3 ......");
}}