package com.pushpendra.canapp.MyCart;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pushpendra.canapp.R;

import java.util.ArrayList;
import java.util.List;

import p32929.androideasysql_library.Column;
import p32929.androideasysql_library.EasyDB;


public class MyCartFragment extends Fragment {

    List<MyCartModel> modellist;
    MyCartAdapter myCartAdapter;
    RecyclerView myCartRecyclerview;

    public MyCartFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_cart, container, false);

        myCartRecyclerview = view.findViewById(R.id.frag_mycar_recyclerview);
        myCartRecyclerview.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        myCartRecyclerview.setLayoutManager(linearLayoutManager);

        modellist = new ArrayList<>();
        //getcartItems();
        return view;

    }

//    private void getcartItems() {
//        EasyDB easyDB = EasyDB.init(getContext(),"ITEM_DB")
//                .setTableName("ITEM_TABLE")
//                .addColumn(new Column("item_id",new String[]{"text","notnull"}))
//                .addColumn(new Column("item_name",new String[]{"text","notnull"}))
//                .addColumn(new Column("item_image",new String[]{"text","notnull"}))
//                .addColumn(new Column("item_price",new String[]{"text","notnull"}))
//                .addColumn(new Column("item_Quantity",new String[]{"text","notnull"}))
//                .doneTableColumn();
//
//        Cursor res = easyDB.getAllData();
//        while(res.moveToNext()){
//            String id = res.getString(0);
//            String name = res.getString(1);
//            String image = res.getString(2);
//            String price = res.getString(3);
//            String quantity = res.getString(4);
//
//            MyCartModel myCartModel = new MyCartModel(id,name,image,price,quantity);
//            modellist.add(myCartModel);
//            myCartAdapter = new MyCartAdapter(getContext(),modellist);
//            myCartRecyclerview.setAdapter(myCartAdapter);
//
//
//        }
//    }
}