package com.pushpendra.canapp.Adapter;

import static com.pushpendra.canapp.R.drawable.burger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.pushpendra.canapp.Models.MainHorModel;
import com.pushpendra.canapp.Models.MainVerModel;
import com.pushpendra.canapp.R;

import java.util.ArrayList;

public class MainHorAdapter extends RecyclerView.Adapter<MainHorAdapter.ViewHolder> {

    UpdateVerticalRec updateVerticalRec;
    Activity activity;
    ArrayList<MainHorModel> list;


    boolean check = true;
    boolean select = true;
    int row_index = -1;

    public MainHorAdapter(UpdateVerticalRec updateVerticalRec, Activity activity, ArrayList<MainHorModel> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public MainHorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_horizontal,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        if(check){
            ArrayList<MainVerModel> mainVerModels = new ArrayList<>();
            mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger","10:00 - 15:00 min","Rs.50","Aloo Burger"));
            mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger1","10:00 - 15:00 min","Rs.50","Aloo Burger"));
            mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger2","10:00 - 15:00 min","Rs.50","Aloo Burger"));
            mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger3","10:00 - 15:00 min","Rs.50","Aloo Burger"));
            mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger3","10:00 - 15:00 min","Rs.50","Aloo Burger"));
            mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger3","10:00 - 15:00 min","Rs.50","Aloo Burger"));
            mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger3","10:00 - 15:00 min","Rs.50","Aloo Burger"));
            mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger3","10:00 - 15:00 min","Rs.50","Aloo Burger"));
            mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger3","10:00 - 15:00 min","Rs.50","Aloo Burger"));

            updateVerticalRec.callBack(position,mainVerModels);
            check=false;
        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = holder.getAdapterPosition();
                notifyDataSetChanged();

                if(position==0){
                    ArrayList<MainVerModel> mainVerModels = new ArrayList<>();

                    mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger","10:00 - 15:00 min","Rs.50","Aloo Burger"));


                    updateVerticalRec.callBack(position,mainVerModels);
                }else if(position == 1){
                    ArrayList<MainVerModel> mainVerModels = new ArrayList<>();
                    mainVerModels.add(new MainVerModel(R.drawable.frappe,"Burger","10:00 - 15:00 min","Rs.50","Aloo Burger"));
                    mainVerModels.add(new MainVerModel(R.drawable.frappe,"Burger1","10:00 - 15:00 min","Rs.50","Aloo Burger"));


                    updateVerticalRec.callBack(position,mainVerModels);
                }else if(position == 2){
                    ArrayList<MainVerModel> mainVerModels = new ArrayList<>();
                    mainVerModels.add(new MainVerModel(R.drawable.cutlery,"Burger","10:00 - 15:00 min","Rs.50","Aloo Burger"));
                    mainVerModels.add(new MainVerModel(R.drawable.frappe,"Burger1","10:00 - 15:00 min","Rs.50","Aloo Burger"));
                    mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger2","10:00 - 15:00 min","Rs.50","Aloo Burger"));


                    updateVerticalRec.callBack(position,mainVerModels);
                }else if(position == 3){
                    ArrayList<MainVerModel> mainVerModels = new ArrayList<>();
                    mainVerModels.add(new MainVerModel(R.drawable.toaster,"Burger","10:00 - 15:00 min","Rs.50","Aloo Burger"));
                    mainVerModels.add(new MainVerModel(R.drawable.frappe,"Burger1","10:00 - 15:00 min","Rs.50","Aloo Burger"));
                    mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger2","10:00 - 15:00 min","Rs.50","Aloo Burger"));
                    mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger3","10:00 - 15:00 min","Rs.50","Aloo Burger"));

                    updateVerticalRec.callBack(position,mainVerModels);
                }else if(position == 4){
                    ArrayList<MainVerModel> mainVerModels = new ArrayList<>();
                    mainVerModels.add(new MainVerModel(R.drawable.frappe,"Burger","10:00 - 15:00 min","Rs.50","Aloo Burger"));
                    mainVerModels.add(new MainVerModel(R.drawable.frappe,"Burger1","10:00 - 15:00 min","Rs.50","Aloo Burger"));
                    mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger2","10:00 - 15:00 min","Rs.50","Aloo Burger"));
                    mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger3","10:00 - 15:00 min","Rs.50","Aloo Burger"));
                    mainVerModels.add(new MainVerModel(R.drawable.burgerburger,"Burger3","10:00 - 15:00 min","Rs.50","Aloo Burger"));

                    updateVerticalRec.callBack(position,mainVerModels);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;
        ConstraintLayout cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.main_card_hor_image);
            name = itemView.findViewById(R.id.main_card_hor_text);
            cardView = itemView.findViewById(R.id.main_horizontal_cardview);
        }
    }

}
