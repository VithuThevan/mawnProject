package com.example.myapplication78;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList name_id, description_id, price_id;

    public MyAdapter(Context context, ArrayList name_id, ArrayList description_id, ArrayList price_id) {
        this.context = context;
        this.name_id = name_id;
        this.description_id = description_id;
        this.price_id = price_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name_id.setText(String.valueOf(name_id.get(position)));
        holder.description_id.setText(String.valueOf(description_id.get(position)));
        holder.price_id.setText(String.valueOf(price_id.get(position)));
    }

    @Override
    public int getItemCount() {
        return name_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_id, description_id, price_id;
        public MyViewHolder (@NonNull View itemView){
            super(itemView);
            name_id = itemView.findViewById(R.id.textname);
            description_id = itemView.findViewById(R.id.textdescription);
            price_id = itemView.findViewById(R.id.textprice);
        }
    }
}
