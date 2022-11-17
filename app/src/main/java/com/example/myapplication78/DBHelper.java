package com.example.myapplication78;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class DBHelper extends SQLiteOpenHelper {
    public static final String Col1 = "Name";
    public static final String Col2 = "Description";
    public static final String Col3 = "Price";
    public static final String Col4 = "Id";
    public static final String TableName = "ListView";

    public DBHelper( Context context) {
        super(context, "vithu", null, 1);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table "+TableName+"(Id Integer primary key autoincrement, name TEXT primary key, description Text, price Text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int ii) {
        sqLiteDatabase.execSQL("drop Table if exists "+TableName);
    }

    public Boolean insertuserdata(String name, String description, String price){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("description",description);
        contentValues.put("price",price);
        long result = sqLiteDatabase.insert(TableName,null,contentValues);
        if (result==-1){
            return false;
        } else
        {
            return true;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + TableName, null);
        return cursor;
    }

    public boolean updateData(String name, String description, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("description",description);
        contentValues.put("price",price);
        db.update(TableName,contentValues,"name=?",new String[] {name});
        return true;

    }

    public Integer deletedata (String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TableName, "name=?",new String[] {name});
    }
}