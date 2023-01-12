package com.pushpendra.canapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.pushpendra.canapp.Adapter.MainHorAdapter;
import com.pushpendra.canapp.Adapter.MainVerAdapter;
import com.pushpendra.canapp.Adapter.UpdateVerticalRec;
import com.pushpendra.canapp.Models.MainHorModel;
import com.pushpendra.canapp.Models.MainVerModel;
import com.pushpendra.canapp.databinding.FragmentHomeBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements UpdateVerticalRec{
    FragmentHomeBinding binding;
    ConstraintLayout custConstraint;
    RecyclerView mainHorizontalRecycler,mainVerticalRecycler;

    //////////HORIZONTAL RECYCLERVIEW//////////
    ArrayList<MainHorModel> mainHorModelList;
    MainHorAdapter mainHorAdapter;
    ///////////////////////////////////////////

    ///////VERTICAL RECYCLERVIEW///////////////
    ArrayList<MainVerModel> mainVerModelList;
    MainVerAdapter mainVerAdapter;
    ////////////////////////////////////////////

    ///////////AUTHENTICATION///////////////////
    FirebaseAuth mAuth;
    FirebaseStorage storage;
    ///////////////////////////////////////////
    private DatabaseReference database,db2;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        //custConstraint = findViewById(R.id.main_customer_constraintLayout);
//        //custConstraint.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//
//            }
//        });

        ////////////HORIZONTAL RECYCLERVIEW///////////////////////////////////
        mainHorizontalRecycler = view.findViewById(R.id.main_hor_recyclerview);
        mainHorModelList = new ArrayList<>();
        mainHorModelList.add(new MainHorModel(R.drawable.frappe,"Burger","Burger"));
        mainHorModelList.add(new MainHorModel(R.drawable.burger,"Burger","Burger"));
        mainHorModelList.add(new MainHorModel(R.drawable.toaster,"Burger","Burger"));
        mainHorModelList.add(new MainHorModel(R.drawable.frappe,"Burger","Burger"));
        mainHorModelList.add(new MainHorModel(R.drawable.frappe,"Burger","Burger"));


        mainHorAdapter = new MainHorAdapter(this,getActivity(),mainHorModelList);
        mainHorizontalRecycler.setAdapter(mainHorAdapter);
        mainHorizontalRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        mainHorizontalRecycler.setHasFixedSize(true);
        mainHorizontalRecycler.setNestedScrollingEnabled(false);
        ///////////////////////////////////////////////////////////////////////

//        database = FirebaseDatabase.getInstance().getReference().child("Burger");
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    DishesData user = dataSnapshot.getValue(DishesData.class);
//                    mainVerModelList.add(user);
//                }
//                mainVerAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                error.getMessage();
//            }
//        });

        ///////////VERTICAL RECYCLERVIEW////////////////////////////////////////
        mainVerticalRecycler = view.findViewById(R.id.main_ver_recyclerview);
        mainVerModelList = new ArrayList<>();
        mainVerAdapter = new MainVerAdapter(getActivity(),mainVerModelList);
        mainVerticalRecycler.setAdapter(mainVerAdapter);
        mainVerticalRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

//        database = FirebaseDatabase.getInstance().getReference("Dishes").child("Burger");
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    DishesData user = dataSnapshot.getValue(DishesData.class);
//                    mainVerModelList.add(user);
//                }
//                mainVerAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                error.getMessage();
//            }
//        });



    }

    @Override
    public void callBack(int position, ArrayList<MainVerModel> list) {
        mainVerAdapter = new MainVerAdapter(getContext(),list);
        mainVerAdapter.notifyDataSetChanged();
        mainVerticalRecycler.setAdapter(mainVerAdapter);
    }
}