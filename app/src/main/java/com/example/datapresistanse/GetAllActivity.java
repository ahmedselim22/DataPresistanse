package com.example.datapresistanse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class GetAllActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all);

        recyclerView=findViewById(R.id.recyclerView);
        myDatabase = new MyDatabase(GetAllActivity.this);

        ArrayList<User> list = myDatabase.getAllUsers();
        Toast.makeText(this, list.size()+" users ", Toast.LENGTH_SHORT).show();

        MyRecyclerViewAdapter recyclerViewAdapter = new MyRecyclerViewAdapter(list);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(recyclerViewAdapter);


    }
}