package com.example.datapresistanse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DatabaseTest extends AppCompatActivity {
    Button insert ,update ,delete  , get_all , get_num;
    MyDatabase myDatabase;

//    DatabaseAccess databaseAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_test);

        insert=findViewById(R.id.btn_insert);
        update=findViewById(R.id.btn_update);
        delete=findViewById(R.id.btn_delete);
        get_all=findViewById(R.id.btn_getAll);
        get_num=findViewById(R.id.btn_getNum);
//        databaseAccess = DatabaseAccess.getInstance(this);
        myDatabase = new MyDatabase(DatabaseTest.this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DatabaseTest.this ,InsertActivity.class);
                startActivity(i);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DatabaseTest.this,UpdateActivity.class);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DatabaseTest.this,DeleteActivity.class);
                startActivity(i);
            }
        });

        get_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DatabaseTest.this,GetAllActivity.class);
                startActivity(i);
            }
        });
        long num = myDatabase.getUsersCount();
        get_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DatabaseTest.this, num+" users", Toast.LENGTH_SHORT).show();
            }
        });
    }
}