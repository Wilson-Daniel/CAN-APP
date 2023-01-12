package com.pushpendra.canapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pushpendra.canapp.Adapter.MainHorAdapter;
import com.pushpendra.canapp.Adapter.MainVerAdapter;
import com.pushpendra.canapp.Adapter.UpdateVerticalRec;
import com.pushpendra.canapp.Models.MainHorModel;
import com.pushpendra.canapp.Models.MainVerModel;
import com.pushpendra.canapp.MyCart.MyCartFragment;
import com.pushpendra.canapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

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
    ///////////////////////////////////////////
    ActivityMainBinding binding;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());
        mAuth = FirebaseAuth.getInstance();
        //FRAGMENT SWITCHING
        binding.mainBottomBar.setOnItemSelectedListener(item ->{
            switch(item.getItemId()){
                case R.id.main_navbar_Home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.main_navbar_profile:
                    replaceFragment(new MyCartFragment());
                    break;
            }
            return true;
        });

        ////////////////////////////////////////////////////////////////////////
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransition = fragmentManager.beginTransaction();
        fragmentTransition.replace(R.id.main_frameLayout,fragment);
        fragmentTransition.commit();
    }
}