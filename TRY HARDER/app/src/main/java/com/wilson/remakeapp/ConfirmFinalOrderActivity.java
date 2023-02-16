package com.wilson.remakeapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wilson.remakeapp.Prevalent.Prevalent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmFinalOrderActivity extends AppCompatActivity {
    private EditText nameEditText,phoneEditText,addressEditText,cityEditText;
    private Button confirmOrderBtn;
    private String totalAmount = "";

    public static final String PAYTM_PACKAGE_NAME = "net.one97.paytm";
    EditText name, upiId, amount, note;
    TextView msg;
    Button pay;
    public static String payerName, vpa, msgNote, status, sendAmount;
    Uri uri;
    PackageManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_final_order);

        totalAmount = getIntent().getStringExtra("Total Price");
        Toast.makeText(this, "Total Price = Rs. "+totalAmount,Toast.LENGTH_SHORT).show();
        confirmOrderBtn = (Button) findViewById(R.id.confirm_final_order_btn);
        nameEditText =(EditText) findViewById(R.id.shippment_name);
        phoneEditText =(EditText) findViewById(R.id.shippment_phone_number);
        addressEditText =(EditText) findViewById(R.id.shippment_address);
        cityEditText =(EditText) findViewById(R.id.shippment_city);
        confirmOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check();
                // getting the text view data

                payerName = nameEditText.getText().toString();
                vpa = addressEditText.getText().toString();
                sendAmount = cityEditText.getText().toString();
                msgNote = phoneEditText.getText().toString();
                String url = "http://m.p-y.tm";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

//                uri = getPayTmUri(payerName,vpa , msgNote, sendAmount);
//                payWithPayTm("net.one97.paytm");

            }
        });

    }

    private void Check() {
        if(TextUtils.isEmpty(nameEditText.getText().toString())){
            Toast.makeText(this,"Please Provide Your Full Name",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phoneEditText.getText().toString())){
            Toast.makeText(this,"Please Provide Your Phone Number",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(addressEditText.getText().toString())){
            Toast.makeText(this,"Please Provide Your Valid Address.",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(cityEditText.getText().toString())){
            Toast.makeText(this,"Please Provide Your City Name",Toast.LENGTH_SHORT).show();
        }
        else {

            ConfirmOrder();
        }
    }

    private void ConfirmOrder() {
        final String saveCurrentTime,saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd. yyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentDate.format(calForDate.getTime());
        final DatabaseReference ordersRef= FirebaseDatabase.getInstance().getReference()
                .child("Orders")
                .child(Prevalent.currentOnlineUser.getPhone());
        HashMap<String, Object> ordersMap = new HashMap<>();
        ordersMap.put("totalAmount",totalAmount);
        ordersMap.put("name",nameEditText.getText().toString());
        ordersMap.put("phone",phoneEditText.getText().toString());
        ordersMap.put("address",addressEditText.getText().toString());
        ordersMap.put("city",cityEditText.getText().toString());
        ordersMap.put("date",saveCurrentDate);
        ordersMap.put("time",saveCurrentTime);
        ordersMap.put("state", "Not Shipped");
        ordersRef.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    FirebaseDatabase.getInstance().getReference()
                            .child("Cart List")
                            .child("User view")
                            .child(Prevalent.currentOnlineUser.getPhone())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(ConfirmFinalOrderActivity.this,"Your final Order has been placed successfully.",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ConfirmFinalOrderActivity.this,HomeActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });


    }
    private static Uri getPayTmUri(String name, String upiId, String note, String amount) {
        return new Uri.Builder()
                .scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();
    }

    private void payWithPayTm(String packageName) {

        if (isAppInstalled(this, packageName)) {

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(uri);
            i.setPackage(packageName);
            startActivityForResult(i, 0);

        } else {
            Toast.makeText(this, "Paytm is not installed Please install and try again.", Toast.LENGTH_SHORT).show();
        }


    }

    // Hi all today we are going to integrate paytm payment with android app.
    // without using any sdk.
    // so lets have the demo first.
    // so lets get started.
    // Please like share and dont forget to subscribe.
    // so transaction successful and we also got the transac ref no
    // Thanks for watching see you in the next video


    //cheking paytm app is install or not

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            status = data.getStringExtra("Status").toLowerCase();
        }
        if ((RESULT_OK == resultCode) && status.equals("success")) {
            Toast.makeText(ConfirmFinalOrderActivity.this, "Transaction successful." + data.getStringExtra("ApprovalRefNo"), Toast.LENGTH_SHORT).show();
            msg.setText("Transaction successful of ₹" + sendAmount);
            msg.setTextColor(Color.GREEN);

        } else {
            Toast.makeText(ConfirmFinalOrderActivity.this, "Transaction cancelled or failed please try again.", Toast.LENGTH_SHORT).show();
            msg.setText("Transaction Failed of ₹" + sendAmount);
            msg.setTextColor(Color.RED);
        }

    }
}

