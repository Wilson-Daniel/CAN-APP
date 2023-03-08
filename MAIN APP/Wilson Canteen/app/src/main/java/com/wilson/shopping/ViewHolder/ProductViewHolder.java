package com.wilson.shopping.ViewHolder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wilson.shopping.Interface.ItemClickListner;
import com.wilson.shopping.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ImageView imageView;
    public ItemClickListner listner;
    public ProductViewHolder(View itemView)
    {
        super(itemView);


        imageView = (ImageView) itemView.findViewById(R.id.product_image);
        txtProductName = (TextView) itemView.findViewById(R.id.product_name1);
        txtProductDescription = (TextView) itemView.findViewById(R.id.product_description1);
        txtProductPrice = (TextView) itemView.findViewById(R.id.product_price1);
    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View view)
    {
        listner.onClick(view, getAdapterPosition(), false);
    }
}

