package com.pushpendra.canapp.MyCart;

import android.annotation.SuppressLint;
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


public class MyCartFragment extends Fragment {

    List<MyCartModel> list;
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
        myCartRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        list.add(new MyCartModel(R.drawable.burgerburger,"order1","56"));
        list.add(new MyCartModel(R.drawable.burgerburger,"order1","56"));
        list.add(new MyCartModel(R.drawable.burgerburger,"order1","56"));
        list.add(new MyCartModel(R.drawable.burgerburger,"order1","56"));
        list.add(new MyCartModel(R.drawable.burgerburger,"order1","56"));
        list.add(new MyCartModel(R.drawable.burgerburger,"order1","56"));
        list.add(new MyCartModel(R.drawable.burgerburger,"order1","56"));
        list.add(new MyCartModel(R.drawable.burgerburger,"order1","56"));
        list.add(new MyCartModel(R.drawable.burgerburger,"order1","56"));
        list.add(new MyCartModel(R.drawable.burgerburger,"order1","56"));
        list.add(new MyCartModel(R.drawable.burgerburger,"order1","56"));
        list.add(new MyCartModel(R.drawable.frappe,"order1","56"));
        myCartAdapter = new MyCartAdapter(list);
        myCartRecyclerview.setAdapter(myCartAdapter);

        return view;

    }
}