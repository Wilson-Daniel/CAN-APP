package com.pushpendra.canapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pushpendra.canapp.Models.MainVerModel;
import com.pushpendra.canapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainVerAdapter extends RecyclerView.Adapter<MainVerAdapter.ViewHolder> {
    Context context;
    ArrayList<MainVerModel> list;


    public MainVerAdapter(Context context, ArrayList<MainVerModel> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MainVerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_vertical_items,parent,false));
        View v = LayoutInflater.from(context).inflate(R.layout.main_vertical_items,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.timing.setText(list.get(position).getTiming());
        holder.price.setText(list.get(position).getPrice());
        holder.description.setHint(list.get(position).getDescription());
        //holder.Quantity.setText(itemQuantity);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView,add,minus;
        TextView name, timing, price, description, Quantity;

        int itemQuantity = 0;

        public ViewHolder(@NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.main_vertical_image);
            name = itemView.findViewById(R.id.main_vertical_Name);
            timing = itemView.findViewById(R.id.main_vertical_timing);
            price = itemView.findViewById(R.id.main_vertical_price);
            description = itemView.findViewById(R.id.main_vertical_description);
            add = itemView.findViewById(R.id.main_vertical_add_buttton);
            Quantity = itemView.findViewById(R.id.main_vertical_quantity);
            minus = itemView.findViewById(R.id.main_vertical_item_minus_button);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemQuantity++;
                    Quantity.setText(String.valueOf(itemQuantity));
                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemQuantity>0) {
                        itemQuantity--;
                    }
                    Quantity.setText(String.valueOf(itemQuantity));
                }
            });
        }

        @Override
        public void onClick(View view) {

        }


    }

}
