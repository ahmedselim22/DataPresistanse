package com.example.datapresistanse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class InternalStorage extends AppCompatActivity {
    EditText firstName,lastName,age,job;
    Button save , restore ;
    TextView show;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal_storage);
        firstName =findViewById(R.id.internal_et_fname);
        lastName =findViewById(R.id.internal_et_lname);
        age =findViewById(R.id.internal_et_age);
        job =findViewById(R.id.internal_et_job);
        save =findViewById(R.id.internal_btn_save);
        restore =findViewById(R.id.internal_btn_restore);
        show =findViewById(R.id.internal_tv_show);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname =firstName.getText().toString();
                String lname =lastName.getText().toString();
                String ag =age.getText().toString();
                String jo =job.getText().toString();

                try {
                    FileOutputStream fos =openFileOutput("data",MODE_PRIVATE);
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println(fname);
                    pw.println(lname);
                    pw.println(ag);
                    pw.println(jo);
                    pw.close();
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Toast.makeText(InternalStorage.this,fname+" , "+lname+" , "+ag+" , "+jo , Toast.LENGTH_SHORT).show();
            }
        });

        restore.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput("data");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    String temp="";
                    String allData = "";
                    while ((temp = br.readLine())!=null){
                        allData += temp+" ";
                    }
                    br.close();
                    isr.close();
                    fis.close();
                    show.setText(allData);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}