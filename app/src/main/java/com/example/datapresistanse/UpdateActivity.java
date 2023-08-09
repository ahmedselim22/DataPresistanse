package com.example.datapresistanse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    EditText name_s ,name_u , email_u ,country_u,phone_u ,password_u;
    Button search , update;
    MyDatabase myDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_s=findViewById(R.id.search_et_name);
        name_u=findViewById(R.id.update_et_name);
        email_u=findViewById(R.id.update_et_email);
        country_u=findViewById(R.id.update_et_country);
        phone_u=findViewById(R.id.update_et_phone);
        password_u=findViewById(R.id.update_et_password);
        search=findViewById(R.id.search_btn);
        update = findViewById(R.id.update_btn);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase = new MyDatabase(UpdateActivity.this);
                String n = name_s.getText().toString();
                ArrayList<User> users= myDatabase.searchUsers(n);
                User u = users.get(0);

                name_u.setText(u.getName());
                email_u.setText(u.getEmail());
                country_u.setText(u.getCountry());
                phone_u.setText(u.getPhone());
                password_u.setText(u.getPassword());


            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na= name_u.getText().toString();
                String em= email_u.getText().toString();
                String co= country_u.getText().toString();
                String ph= phone_u.getText().toString();
                String pa= password_u.getText().toString();
//
                myDatabase = new MyDatabase(UpdateActivity.this);
                User user = new User(na,em,co,ph,pa);
                boolean r =myDatabase.updateUser(user);
//
                long n =myDatabase.getUsersCount();
                if (r){
                    Toast.makeText(UpdateActivity.this, "Updated Successfully "+n + " users", Toast.LENGTH_SHORT).show();
                    name_u.setText("");
                    email_u.setText("");
                    country_u.setText("");
                    phone_u.setText("");
                    password_u.setText("");}
                else {
                    Toast.makeText(UpdateActivity.this, "Not Updated !! "+n +" users", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}