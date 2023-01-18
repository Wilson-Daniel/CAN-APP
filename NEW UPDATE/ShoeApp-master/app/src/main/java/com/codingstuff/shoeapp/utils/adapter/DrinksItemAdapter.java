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

import java.util.List;

public class DrinksItemAdapter extends RecyclerView.Adapter<DrinksItemAdapter.DrinksItemViewHolder>{
    List<ShoeItem> DrinksItemList;
    private ShoeClickedListeners drinksClickedListeners;

    public DrinksItemAdapter(ShoeClickedListeners drinksClickedListeners) {
        this.drinksClickedListeners = drinksClickedListeners;
    }
    public void setShoeItemList(List<ShoeItem> DrinksItemList){
        this.DrinksItemList = DrinksItemList;
    }
    @NonNull
    @Override
    public DrinksItemAdapter.DrinksItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_shoe , parent , false);
        return new DrinksItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksItemAdapter.DrinksItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (DrinksItemList == null){
            return 0;
        }else{
            return DrinksItemList.size();
        }
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
    public interface ShoeClickedListeners{
        void onCardClicked(ShoeItem shoe);
        void onAddToCartBtnClicked(ShoeItem shoeItem);
    }
}
