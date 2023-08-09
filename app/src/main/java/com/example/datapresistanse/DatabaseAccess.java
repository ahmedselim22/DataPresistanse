package com.example.datapresistanse;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class DatabaseAccess {
    private SQLiteDatabase myDatabase;
    private SQLiteOpenHelper openHelper;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context){
        this.openHelper = new MyDatabase(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if (instance==null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.myDatabase = this.openHelper.getWritableDatabase();
    }

    public void close(){
        if (this.myDatabase != null){
            this.myDatabase.close();
        }
    }

    public boolean insertUser(User user){
        ContentValues values = new ContentValues();
        values.put(MyDatabase.TB_COL_NAME,user.getName());
        values.put(MyDatabase.TB_COL_EMAIL,user.getEmail());
        values.put(MyDatabase.TB_COL_COUNTRY,user.getCountry());
        values.put(MyDatabase.TB_COL_PHONE,user.getPhone());
        values.put(MyDatabase.TB_COL_PASSWORD,user.getPassword());
        long result = myDatabase.insert(MyDatabase.DB_TABLE_NAME,null,values);
        return result != -1;
    }

    public boolean updateUser(User user){
        ContentValues values = new ContentValues();
        values.put(MyDatabase.TB_COL_NAME,user.getName());
        values.put(MyDatabase.TB_COL_EMAIL,user.getEmail());
        values.put(MyDatabase.TB_COL_COUNTRY,user.getCountry());
        values.put(MyDatabase.TB_COL_PHONE,user.getPhone());
        values.put(MyDatabase.TB_COL_PASSWORD,user.getPassword());
        String[] args = {user.getName()};
        int result = myDatabase.update(MyDatabase.DB_TABLE_NAME,values,MyDatabase.TB_COL_NAME+" =?",args);
        return result > 0;
    }

    public long getUsersCount(){
        return DatabaseUtils.queryNumEntries(myDatabase,MyDatabase.DB_TABLE_NAME);
    }

    public boolean deleteUser(String name){
        String[] args = {name};
        int result =myDatabase.delete(MyDatabase.DB_TABLE_NAME,MyDatabase.TB_COL_NAME+"=?",args);
        return result > 0;
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor= myDatabase.rawQuery("SELECT * FROM "+MyDatabase.DB_TABLE_NAME,null);
        if (cursor != null && cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String name =cursor.getString(cursor.getColumnIndex(MyDatabase.TB_COL_NAME));
                @SuppressLint("Range") String email =cursor.getString(cursor.getColumnIndex(MyDatabase.TB_COL_EMAIL));
                @SuppressLint("Range") String country =cursor.getString(cursor.getColumnIndex(MyDatabase.TB_COL_COUNTRY));
                @SuppressLint("Range") String phone =cursor.getString(cursor.getColumnIndex(MyDatabase.TB_COL_PHONE));
                @SuppressLint("Range") String password =cursor.getString(cursor.getColumnIndex(MyDatabase.TB_COL_PASSWORD));
                User user = new User(name,email,country,phone,password);
                users.add(user);

            }while (cursor.moveToNext());
        }
        return users;
    }

    public ArrayList<User> searchUsers(String n){
        ArrayList<User> users = new ArrayList<>();
        Cursor cursor= myDatabase.rawQuery("SELECT * FROM "+MyDatabase.DB_TABLE_NAME+" WHERE "+MyDatabase.TB_COL_NAME+"=?",new String[] {n});
        if (cursor != null && cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String name =cursor.getString(cursor.getColumnIndex(MyDatabase.TB_COL_NAME));
                @SuppressLint("Range") String email =cursor.getString(cursor.getColumnIndex(MyDatabase.TB_COL_EMAIL));
                @SuppressLint("Range") String country =cursor.getString(cursor.getColumnIndex(MyDatabase.TB_COL_COUNTRY));
                @SuppressLint("Range") String phone =cursor.getString(cursor.getColumnIndex(MyDatabase.TB_COL_PHONE));
                @SuppressLint("Range") String password =cursor.getString(cursor.getColumnIndex(MyDatabase.TB_COL_PASSWORD));
                User user = new User(name,email,country,phone,password);
                users.add(user);

            }while (cursor.moveToNext());
        }
        return users;
    }
}
