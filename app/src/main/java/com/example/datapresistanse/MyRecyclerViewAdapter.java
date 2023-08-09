package com.example.datapresistanse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.DataViewHolder> {

    ArrayList<User> users ;

    public  MyRecyclerViewAdapter(ArrayList<User> users){
        this.users=users;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_templete,null,false);
        DataViewHolder dvh = new DataViewHolder(v);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        User user = users.get(position);
//        holder.id.setText(user.getId());
        holder.name.setText(user.getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder{
        TextView  name;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
//            id=itemView.findViewById(R.id.rv_temp_id);
            name=itemView.findViewById(R.id.rv_temp_name);
        }
    }
}
