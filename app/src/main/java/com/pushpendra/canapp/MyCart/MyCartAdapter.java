package com.pushpendra.canapp.MyCart;

import android.content.pm.LabeledIntent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pushpendra.canapp.R;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    List<MyCartModel> list;

    public MyCartAdapter(List<MyCartModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {
        holder.mycart_imageView.setImageResource(list.get(position).getImage());
        holder.mycart_name.setText(list.get(position).getName());
        holder.mycart_price.setText(list.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mycart_imageView;
        TextView mycart_name,mycart_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mycart_imageView = itemView.findViewById(R.id.mycart_image);
            mycart_name = itemView.findViewById(R.id.mycart_Name);
            mycart_price = itemView.findViewById(R.id.mycart_price);


        }
    }
}
