package com.example.datapresistanse;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "users_db";
    public static final String DB_TABLE_NAME = "users";
    public static final String TB_COL_ID = "id";
    public static final String TB_COL_NAME = "name";
    public static final String TB_COL_EMAIL = "email";
    public static final String TB_COL_COUNTRY = "country";
    public static final String TB_COL_PHONE = "phone";
    public static final String TB_COL_PASSWORD = "password";
    public static final int DATABASE_VERSION = 3;

    public MyDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DB_TABLE_NAME+" ("+ TB_COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT , "+TB_COL_NAME+" TEXT UNIQUE , "+TB_COL_EMAIL+" TEXT , "+TB_COL_COUNTRY+" TEXT , "+TB_COL_PHONE+" TEXT , " + TB_COL_PASSWORD + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+DB_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertUser(User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_COL_NAME,user.getName());
        values.put(TB_COL_EMAIL,user.getEmail());
        values.put(TB_COL_COUNTRY,user.getCountry());
        values.put(TB_COL_PHONE,user.getPhone());
        values.put(TB_COL_PASSWORD,user.getPassword());
        long result = sqLiteDatabase.insert(DB_TABLE_NAME,null,values);
        return result != -1;
    }

    public boolean updateUser(User user){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TB_COL_NAME,user.getName());
        values.put(TB_COL_EMAIL,user.getEmail());
        values.put(TB_COL_COUNTRY,user.getCountry());
        values.put(TB_COL_PHONE,user.getPhone());
        values.put(TB_COL_PASSWORD,user.getPassword());
        String[] args = {user.getName()};
        int result = sqLiteDatabase.update(DB_TABLE_NAME,values,TB_COL_NAME+" =?",args);
        return result > 0;
    }

    public long getUsersCount(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return DatabaseUtils.queryNumEntries(sqLiteDatabase,DB_TABLE_NAME);
    }

    public boolean deleteUser(String name){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {name};
        int result =db.delete(DB_TABLE_NAME,TB_COL_NAME+"=?",args);
        return result > 0;
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM "+DB_TABLE_NAME,null);
        if (cursor != null && cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String name =cursor.getString(cursor.getColumnIndex(TB_COL_NAME));
                @SuppressLint("Range") String email =cursor.getString(cursor.getColumnIndex(TB_COL_EMAIL));
                @SuppressLint("Range") String country =cursor.getString(cursor.getColumnIndex(TB_COL_COUNTRY));
                @SuppressLint("Range") String phone =cursor.getString(cursor.getColumnIndex(TB_COL_PHONE));
                @SuppressLint("Range") String password =cursor.getString(cursor.getColumnIndex(TB_COL_PASSWORD));
                User user = new User(name,email,country,phone,password);
                users.add(user);

            }while (cursor.moveToNext());
        }
        return users;
    }

    public ArrayList<User> searchUsers(String n){
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor= db.rawQuery("SELECT * FROM "+DB_TABLE_NAME+" WHERE "+TB_COL_NAME+"=?",new String[] {n});
        if (cursor != null && cursor.moveToFirst()){
            do {
                @SuppressLint("Range") String name =cursor.getString(cursor.getColumnIndex(TB_COL_NAME));
                @SuppressLint("Range") String email =cursor.getString(cursor.getColumnIndex(TB_COL_EMAIL));
                @SuppressLint("Range") String country =cursor.getString(cursor.getColumnIndex(TB_COL_COUNTRY));
                @SuppressLint("Range") String phone =cursor.getString(cursor.getColumnIndex(TB_COL_PHONE));
                @SuppressLint("Range") String password =cursor.getString(cursor.getColumnIndex(TB_COL_PASSWORD));
                User user = new User(name,email,country,phone,password);
                users.add(user);

            }while (cursor.moveToNext());
        }
        return users;
    }





}
