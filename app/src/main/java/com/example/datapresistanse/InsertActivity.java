package com.example.datapresistanse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    EditText name , email ,country,phone ,password;
    Button insert;
    MyDatabase myDatabase;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        name=findViewById(R.id.insert_et_name);
        email=findViewById(R.id.insert_et_email);
        country=findViewById(R.id.insert_et_country);
        phone=findViewById(R.id.insert_et_phone);
        password=findViewById(R.id.insert_et_password);
        insert=findViewById(R.id.insert_btn);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na= name.getText().toString();
                String em= email.getText().toString();
                String co= country.getText().toString();
                String ph= phone.getText().toString();
                String pa= password.getText().toString();

                User user = new User(na,em,co,ph,pa);
                myDatabase = new MyDatabase(InsertActivity.this);
                boolean inserted = myDatabase.insertUser(user);
                if (inserted){
                    long n =myDatabase.getUsersCount();
                    Toast.makeText(InsertActivity.this, "Inserted Successfully "+n + " users", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    email.setText("");
                    country.setText("");
                    phone.setText("");
                    password.setText("");}
                else {
                    long n =myDatabase.getUsersCount();
                    Toast.makeText(InsertActivity.this, "Not Inserted !! "+n +" users", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}