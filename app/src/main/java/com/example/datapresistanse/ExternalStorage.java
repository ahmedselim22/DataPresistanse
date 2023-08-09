package com.example.datapresistanse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.Permission;
import java.security.Permissions;

public class ExternalStorage extends AppCompatActivity {
    EditText firstName,lastName,age,job;
    Button save , restore ;
    TextView show;
    File file , external;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        firstName =findViewById(R.id.external_et_fname);
        lastName =findViewById(R.id.external_et_lname);
        age =findViewById(R.id.external_et_age);
        job =findViewById(R.id.external_et_job);
        save =findViewById(R.id.external_btn_save);
        restore =findViewById(R.id.external_btn_restore);
        show =findViewById(R.id.external_tv_show);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname =firstName.getText().toString();
                String lname =lastName.getText().toString();
                String ag =age.getText().toString();
                String jo =job.getText().toString();

                try {
//                    FileOutputStream fos =new FileOutputStream(file,true);
                    FileOutputStream fos =openFileOutput("file",MODE_PRIVATE);
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

                Toast.makeText(ExternalStorage.this,fname+" , "+lname+" , "+ag+" , "+jo , Toast.LENGTH_SHORT).show();
            }
        });

        restore.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                try {
//                    FileInputStream fis = new FileInputStream(file);
                    FileInputStream fis = openFileInput("file");
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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            String[] permessions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this,permessions,123);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==123){
            if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                try {
                    external = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                    file= new File(external,"file");
                    file.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public boolean isWritable(){
        String state = Environment.getExternalStorageState();
        if (state==Environment.MEDIA_MOUNTED){
            return true;
        }
        return false;
    }

    public boolean isReadable(){
        String state = Environment.getExternalStorageState();
        if (state==Environment.MEDIA_MOUNTED || state==Environment.MEDIA_MOUNTED_READ_ONLY){
            return true;
        }
        return false;
    }

}
