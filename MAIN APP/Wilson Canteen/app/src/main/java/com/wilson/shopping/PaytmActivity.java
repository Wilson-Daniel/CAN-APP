package com.wilson.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

public class PaytmActivity extends AppCompatActivity {
    public static final String PAYTM_PACKAGE_NAME = "net.one97.paytm";
    EditText name, upiId, amount, note;
    TextView msg;
    Button pay;
    public static String payerName, vpa, msgNote, status, sendAmount;
    String studentname,stuSection;
    Uri uri;
    final int UPI_PAYMENT=0;
    private String totalAmount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paytm);
        totalAmount = getIntent().getStringExtra("Total Price");
        Toast.makeText(this, "Total Price = Rs. " + totalAmount, Toast.LENGTH_SHORT).show();

        name = findViewById(R.id.name);
        upiId = findViewById(R.id.upi_id);
        //amount = findViewById(R.id.amount);
        note = findViewById(R.id.txn_note);
        msg = findViewById(R.id.msg);
        pay = findViewById(R.id.payNow);


        // setting onClicklistener in paynow button

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // getting the text view data
                studentname = name.getText().toString();
                stuSection = upiId.getText().toString();
                payerName = "Avinash Kumar";
                vpa = "9716353888@ybl";
                sendAmount = totalAmount;
                msgNote = note.getText().toString();

//                if(studentname.isEmpty() == false && stuSection.isEmpty() == false  && msgNote.isEmpty() == false){
//
////                    uri = getPayTmUri(payerName, vpa, msgNote, sendAmount);
////                    payWithPayTm(PAYTM_PACKAGE_NAME);
//                    payusingupi(sendAmount,msgNote,payerName,vpa);
//
//                } else {
//                    Toast.makeText(PaytmActivity.this, "Please fill all the details.", Toast.LENGTH_SHORT).show();
//                }

            }
        });

    }


    private void payusingupi(String amounttxt, String notetxt, String nametxt, String upitxt) {
        Uri uri = Uri.parse("upi://pay").buildUpon().appendQueryParameter("pa",upitxt)
                .appendQueryParameter("pn",nametxt)
                .appendQueryParameter("tn",notetxt)
                .appendQueryParameter("am",amounttxt)
                .appendQueryParameter("cu","INR").build();
        Intent upi_payment = new Intent(Intent.ACTION_VIEW);
        upi_payment.setData(uri);
        Intent chooser = Intent.createChooser(upi_payment,"Pay with");
        if (null!=chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser,UPI_PAYMENT);
        }else{
            Toast.makeText(this, "No app", Toast.LENGTH_SHORT).show();
        }
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
        try {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(uri);
            i.setPackage(packageName);
            startActivityForResult(i, 0);

        } catch (Exception e){
            //Toast.makeText(this, "Paytm is not installed Please install and try again.", Toast.LENGTH_SHORT).show();
        }


    }

    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode || (resultCode == 11))) {
                    if (data != null) {
                        String txt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + txt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("Nothing");
                        upipaymentdataoperation(dataList);
                    } else {
                        Log.d("UPI", "null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("Nothing");
                        upipaymentdataoperation(dataList);
                    }
                } else {
                    Log.d("UPI", "null");
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("Nothing");
                    upipaymentdataoperation(dataList);
                }
                break;
        }


    }

    private void upipaymentdataoperation(ArrayList<String> data) {
        if (isConnectionAvailable(PaytmActivity.this)) {
            String str = data.get(0);
            Log.d("UPIPAY", "upipaymeny" + str);
            String paymentCancel = "";
            if (str == null) {
                str = "discard";
            }
            String status = "";
            String approvalRef = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("m");
                if (equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    } else if (equalStr[0].toLowerCase().equals("approval Ref".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRef = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancel by user";
                    if (status.equals("success")) {
                        Toast.makeText(this, "Transcation Success", Toast.LENGTH_SHORT).show();
                        Log.d("UPI", "responsestr: " + approvalRef);
                    } else if ("Payment cancalled by user.".equals(paymentCancel)) {
                        Toast.makeText(this, "cancel by user", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Trans fail", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        }
    }

    private boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected() && networkInfo.isConnectedOrConnecting()
                    && networkInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }
//    private void Check() {
//        if (TextUtils.isEmpty(nameEditText.getText().toString())) {
//            Toast.makeText(this, "Please Provide Your Full Name", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(phoneEditText.getText().toString())) {
//            Toast.makeText(this, "Please Provide Your Phone Number", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(addressEditText.getText().toString())) {
//            Toast.makeText(this, "Please Provide Your Valid Address.", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(cityEditText.getText().toString())) {
//            Toast.makeText(this, "Please Provide Your City Name", Toast.LENGTH_SHORT).show();
//        } else {
////            PaymentGateWayStart();
//            ConfirmOrder();
//        }
//    }


//    private void ConfirmOrder() {
//        final String saveCurrentTime, saveCurrentDate;
//        Calendar calForDate = Calendar.getInstance();
//        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd. yyy");
//        saveCurrentDate = currentDate.format(calForDate.getTime());
//        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
//        saveCurrentTime = currentDate.format(calForDate.getTime());
//        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference()
//                .child("Orders")
//                .child(Prevalent.currentOnlineUser.getPhone());
//        HashMap<String, Object> ordersMap = new HashMap<>();
//        ordersMap.put("totalAmount", totalAmount);
//        ordersMap.put("name", nameEditText.getText().toString());
//        ordersMap.put("phone", phoneEditText.getText().toString());
//        ordersMap.put("address", addressEditText.getText().toString());
//        ordersMap.put("city", cityEditText.getText().toString());
//        ordersMap.put("date", saveCurrentDate);
//        ordersMap.put("time", saveCurrentTime);
//        ordersMap.put("state", "Not Shipped");
//        ordersRef.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    FirebaseDatabase.getInstance().getReference()
//                            .child("Cart List")
//                            .child("User view")
//                            .child(Prevalent.currentOnlineUser.getPhone())
//                            .removeValue()
//                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NonNull Task<Void> task) {
//                                    if (task.isSuccessful()) {
//                                        Toast.makeText(ConfirmFinalOrderActivity.this, "Your final Order has been placed successfully.", Toast.LENGTH_SHORT).show();
//                                        Intent intent = new Intent(ConfirmFinalOrderActivity.this, HomeActivity.class);
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                        startActivity(intent);
//                                        finish();
//                                    }
//                                }
//                            });
//                }
//            }
//        });
//    }

}
