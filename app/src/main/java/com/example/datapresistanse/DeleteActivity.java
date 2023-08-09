package com.example.datapresistanse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {
    EditText name_s ,name_d , email_d ,country_d,phone_d ,password_d;
    Button search , delete;
    MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        name_s=findViewById(R.id.delete_et_sname);
        name_d=findViewById(R.id.delete_et_name);
        email_d=findViewById(R.id.delete_et_email);
        country_d=findViewById(R.id.delete_et_country);
        phone_d=findViewById(R.id.delete_et_phone);
        password_d=findViewById(R.id.delete_et_password);
        search=findViewById(R.id.delete_sbtn);
        delete = findViewById(R.id.delete_btn);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase = new MyDatabase(DeleteActivity.this);
                String n = name_s.getText().toString();
                ArrayList<User> users= myDatabase.searchUsers(n);
                User u = users.get(0);

                name_d.setText(u.getName());
                email_d.setText(u.getEmail());
                country_d.setText(u.getCountry());
                phone_d.setText(u.getPhone());
                password_d.setText(u.getPassword());

                name_d.setEnabled(false);
                email_d.setEnabled(false);
                country_d.setEnabled(false);
                phone_d.setEnabled(false);
                password_d.setEnabled(false);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDatabase = new MyDatabase(DeleteActivity.this);
                String na=name_d.getText().toString();
                boolean r =myDatabase.deleteUser(na);
//
                long n =myDatabase.getUsersCount();
                if (r){
                    Toast.makeText(DeleteActivity.this, "Deleted Successfully "+n + " users", Toast.LENGTH_SHORT).show();
                    name_d.setText("");
                    email_d.setText("");
                    country_d.setText("");
                    phone_d.setText("");
                    password_d.setText("");}
                else {
                    Toast.makeText(DeleteActivity.this, "Not Deleted !! "+n +" users", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}