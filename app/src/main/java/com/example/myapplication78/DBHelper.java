package com.example.myapplication78;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
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
        return result != -1;
    }

    public Cursor getdata(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery("Select * from " + TableName, null);
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