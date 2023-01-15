package com.pushpendra.canapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.pushpendra.canapp.DATABASE.AppDatabase;
import com.pushpendra.canapp.DATABASE.Product;
import com.pushpendra.canapp.DATABASE.ProductDao;
import com.pushpendra.canapp.Models.MainVerModel;
import com.pushpendra.canapp.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

import p32929.androideasysql_library.Column;
import p32929.androideasysql_library.EasyDB;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        //holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.timing.setText(list.get(position).getTiming());
        holder.price.setText(list.get(position).getPrice());
        holder.description.setHint(list.get(position).getDescription());
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"cart_db").allowMainThreadQueries().build();
                ProductDao productDao = db.ProductDao();
                Boolean check = productDao.is_exist(list.get(position).getName().charAt(0));
                if(check == false){
                    int pid = (int)list.get(position).getName().charAt(0);
                    String pname = list.get(position).getName();
                    int price = Integer.parseInt(list.get(position).getPrice());
                    int qnt = 1;
                    productDao.insertrecord(new Product(pid,pname,price,qnt));
                    Toast.makeText(context, "Product Added Successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Product Already Added", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(context, "YESS", Toast.LENGTH_SHORT).show();
            }
        });



    }
//    public int itemid = 1;
//    private void addtoCart(String Name, String Image, String price, String Quantity){
//
//        Random random = new Random();
//        itemid = random.nextInt(200000);
//
//        EasyDB easyDB = EasyDB.init(context, "ITEM_DB");
//        easyDB.setTableName("ITEM_TABLE");
//        easyDB.addColumn(new Column("item_id", new String[]{"text", "notnull"}));
//        easyDB.addColumn(new Column("item_name", new String[]{"text", "notnull"}));
//        easyDB.addColumn(new Column("item_image", new String[]{"text", "notnull"}));
//        easyDB.addColumn(new Column("item_price", new String[]{"text", "notnull"}));
//        easyDB.addColumn(new Column("item_Quantity", new String[]{"text", "notnull"}));
//        easyDB.doneTableColumn();
//
//        Boolean b  = easyDB.addData("item_id",itemid)
//                .addData("item_name",Name)
//                .addData("item_image",Image)
//                .addData("item_price",price)
//                .addData("item_Quantity",Quantity)
//                .doneDataAdding();
//
//        Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView name, timing, price, description, add;

        public ViewHolder(@NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.main_vertical_image);
            name = itemView.findViewById(R.id.main_vertical_Name);
            timing = itemView.findViewById(R.id.main_vertical_timing);
            price = itemView.findViewById(R.id.main_vertical_price);
            description = itemView.findViewById(R.id.main_vertical_description);
            add = itemView.findViewById(R.id.add_button_constraint);
        }

        @Override
        public void onClick(View view) {

        }



    }


}
