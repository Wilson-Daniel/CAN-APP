package com.wilson.shopping;


import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.lang.invoke.ConstantCallSite;

public class AdminCategoryActivity extends AppCompatActivity {
    private ConstraintLayout tShirts, sportsTShirts, femaleDresses,glasses, hatsCaps, walletsBagsPurses,Laptops, headPhonesHandFree,LogoutBtn,CheckOrdersBtn;
    private ImageView shoes,sweathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        LogoutBtn = (ConstraintLayout) findViewById(R.id.admin_logout_btn);
        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminCategoryActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        CheckOrdersBtn = (ConstraintLayout) findViewById(R.id.check_orders_btn);


        CheckOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminCategoryActivity.this,AdminNewOrdersActivity.class);
                startActivity(intent);
            }
        });



        tShirts = (ConstraintLayout) findViewById(R.id.t_shirts);
        sportsTShirts = (ConstraintLayout) findViewById(R.id.sports_t_shirts);
        femaleDresses = (ConstraintLayout) findViewById(R.id.female_dresses);


        glasses = (ConstraintLayout) findViewById(R.id.glasses);
        hatsCaps = (ConstraintLayout) findViewById(R.id.hats_caps);
        walletsBagsPurses = (ConstraintLayout) findViewById(R.id.purses_bags_wallets);

        headPhonesHandFree = (ConstraintLayout) findViewById(R.id.headphones_handfree);
        Laptops = (ConstraintLayout) findViewById(R.id.laptop_pc);


        tShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.wilson.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "tShirts");
                startActivity(intent);
            }
        });
        sportsTShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.wilson.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Sports tShirts");
                startActivity(intent);
            }
        });


        femaleDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.wilson.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Female Dresses");
                startActivity(intent);
            }
        });
        hatsCaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, com.wilson.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Female Dresses");
                startActivity(intent);
            }
        });




        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.wilson.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Glasses");
                startActivity(intent);
            }
        });






        walletsBagsPurses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.wilson.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Wallets Bags Purses");
                startActivity(intent);
            }
        });






        headPhonesHandFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.wilson.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "HeadPhones HandFree");
                startActivity(intent);
            }
        });


        Laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, com.wilson.shopping.AdminAddNewProductActivity.class);
                intent.putExtra("category", "Laptops");
                startActivity(intent);
            }
        });




    }
}
