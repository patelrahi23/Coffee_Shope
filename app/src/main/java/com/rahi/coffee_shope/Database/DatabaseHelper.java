package com.rahi.coffee_shope.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileInputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    FileInputStream fis;
    byte[] image;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "CoffeeDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table CoffeeDetails(title TEXT , price FLOAT, counter INTEGER, item_size TEXT)");
        db.execSQL("create Table AccountDetails(name TEXT, password TEXT, email Text, image BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists CoffeeDetails");
    }

    public int DeleteItem(String title){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
        return sqLiteDatabase.delete("CoffeeDetails","title=?",new String[]{title});

    }

    public boolean SaveAccountDetails(String name, String password, String email, String imagePath){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long result = 0;
        try {
            fis = new FileInputStream(imagePath);
            image = new byte[fis.available()];
            fis.read(image);

            contentValues.put("name",name);
            contentValues.put("password",password);
            contentValues.put("email",email);
            contentValues.put("image",image);

            Log.e("Database_Data", "InsertData:"+contentValues.toString());
            result = db.insert("AccountDetails",null,contentValues);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean InsertData(String title, double price, int counter, String size){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long result = 0;
        contentValues.put("title",title);
        contentValues.put("price",price);
        contentValues.put("counter",counter);
        contentValues.put("item_size",size);
        result = sqLiteDatabase.insert("CoffeeDetails",null,contentValues);
        return result != -1;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from CoffeeDetails",null);
        return cursor;
    }

    public boolean UpdateUser(String title, double price, int counter, String size){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long result = 0;
        contentValues.put("title",title);
        contentValues.put("price",price);
        contentValues.put("counter",counter);
        contentValues.put("item_size",size);
        result = db.update("CoffeeDetails",contentValues,"title=?",new String[]{title});
        return result != -1;
    }

}
