package com.codingstuff.shoeapp.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.codingstuff.shoeapp.R;
import com.codingstuff.shoeapp.utils.model.ShoeItem;
import com.codingstuff.shoeapp.utils.model.ShoeItem2;
import com.codingstuff.shoeapp.views.MainActivity;

import java.util.List;

public class DrinksItemAdapter extends RecyclerView.Adapter<DrinksItemAdapter.DrinksItemViewHolder>{
    List<ShoeItem> DrinksItemList;
    private DrinksClickedListeners drinksClickedListeners;

    public DrinksItemAdapter(DrinksClickedListeners drinksClickedListeners) {
        this.drinksClickedListeners = drinksClickedListeners;
    }

    public void setDrinksItemList(List<ShoeItem> DrinksItemList){
        this.DrinksItemList = DrinksItemList;
    }
    @NonNull
    @Override
    public DrinksItemAdapter.DrinksItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_drink , parent , false);
        return new DrinksItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksItemAdapter.DrinksItemViewHolder holder, int position) {
        ShoeItem shoeItem1 = DrinksItemList.get(position);
        holder.drinkNameTv.setText(shoeItem1.getShoeName());
        holder.drinkBrandNameTv.setText(shoeItem1.getShoeBrandName());
        holder.drinkPriceTv.setText(String.valueOf(shoeItem1.getShoePrice()));
        holder.drinkImageView.setImageResource(shoeItem1.getShoeImage());

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                drinksClickedListeners.onCardClicked(shoeItem1);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return DrinksItemList.size();
    }
    public class DrinksItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView drinkImageView;
        private TextView drinkNameTv, drinkBrandNameTv, drinkPriceTv;
        private CardView cardView;
        public DrinksItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.eachDrinkCardView);
            drinkNameTv = itemView.findViewById(R.id.eachDrinkName);
            drinkBrandNameTv = itemView.findViewById(R.id.eachDrinkBrandNameTv);
            drinkPriceTv = itemView.findViewById(R.id.eachDrinkPriceTv);
            drinkImageView = itemView.findViewById(R.id.eachDrinkImage);


        }
    }
    public interface DrinksClickedListeners{
        void onCardClicked(ShoeItem shoe);

        void onCardClicked(ShoeItem2 shoe1);

        void onAddToCartBtnClicked(ShoeItem shoeItem);
    }
}
