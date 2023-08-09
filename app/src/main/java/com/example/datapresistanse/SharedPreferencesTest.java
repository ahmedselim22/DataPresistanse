package com.example.datapresistanse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPreferencesTest extends AppCompatActivity {
    EditText firstName,lastName,age,job;
    Button save , restore , go_internal, go_external;
    TextView show;
    SharedPreferences sp ;
    SharedPreferences.Editor e;
//    SharedPreferences sp = getSharedPreferences("users",MODE_PRIVATE);
//    SharedPreferences sp = getPreferences(MODE_PRIVATE);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        firstName =findViewById(R.id.shared_et_fname);
        lastName =findViewById(R.id.shared_et_lname);
        age =findViewById(R.id.shared_et_age);
        job =findViewById(R.id.shared_et_job);
        save =findViewById(R.id.shared_btn_save);
        restore =findViewById(R.id.shared_btn_restore);
        show =findViewById(R.id.shared_tv_show);
        go_internal =findViewById(R.id.shared_btn_goInternal);
        go_external =findViewById(R.id.shared_btn_goExternal);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname =firstName.getText().toString();
                String lname =lastName.getText().toString();
                String ag =age.getText().toString();
                String jo =job.getText().toString();

                  sp =PreferenceManager.getDefaultSharedPreferences(SharedPreferencesTest.this);
//                sp = getSharedPreferences("users",MODE_PRIVATE);
//                sp = getPreferences(MODE_PRIVATE);
                e = sp.edit();
                e.putString("fname",fname);
                e.putString("lname",lname);
                e.putString("age",ag);
                e.putString("job",jo);
                e.apply();
                Toast.makeText(SharedPreferencesTest.this,fname+" , "+lname+" , "+ag+" , "+jo , Toast.LENGTH_SHORT).show();
            }
        });

        restore.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String fname = sp.getString("fname","Null");
                String lname = sp.getString("lname","Null");
                String age = sp.getString("age","Null");
                String job = sp.getString("job","Null");

                show.setText(fname+" , "+lname+" , "+age+" , "+job);
            }
        });

        go_internal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SharedPreferencesTest.this,InternalStorage.class);
                startActivity(i);
            }
        });
        go_external.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SharedPreferencesTest.this,ExternalStorage.class);
                startActivity(i);
            }
        });
    }
}