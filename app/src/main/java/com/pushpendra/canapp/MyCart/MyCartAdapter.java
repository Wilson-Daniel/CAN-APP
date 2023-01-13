package com.pushpendra.canapp.MyCart;

import android.content.Context;
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
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {
    Context context;
    List<MyCartModel> cartlist;

    public MyCartAdapter(Context context, List<MyCartModel> cartlist) {
        this.context = context;
        this.cartlist = cartlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {
        holder.mycart_name.setText(cartlist.get(position).getName());
        holder.mycart_price.setText(cartlist.get(position).getPrice());
        holder.mycart_quantity.setText(cartlist.get(position).getQuantity());
        try{
            Picasso.get().load(cartlist.get(position).getImage()).into(holder.mycart_imageView);
        }catch(Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return cartlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView mycart_imageView;
        TextView mycart_name,mycart_price,mycart_quantity;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mycart_imageView = itemView.findViewById(R.id.mycart_image);
            mycart_name = itemView.findViewById(R.id.mycart_Name);
            mycart_price = itemView.findViewById(R.id.mycart_price);
            mycart_quantity = itemView.findViewById(R.id.mycart_Quantity);

        }
    }
}
