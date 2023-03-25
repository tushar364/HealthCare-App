package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText edFullname,edAddress,edPincode,edContactNumber;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edFullname = findViewById(R.id.BMbookFullName);
        edAddress  = findViewById(R.id.BMbookAddress);
        edPincode  = findViewById(R.id.BMbookPinCode);
        edContactNumber  = findViewById(R.id.BMbookContactnumber);
        btnConfirm = findViewById(R.id.btnBMbook);

        Intent intent= getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String data = intent.getStringExtra("date");
      //  String time = intent.getStringExtra("time");

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                database db = new database(getApplicationContext(),"healthcare",null,1);

                db.addOrder(username,edFullname.getText().toString(),edAddress.getText().toString(),edContactNumber.getText().toString(),Integer.parseInt(edPincode.getText().toString()),
                        data.toString(),"",Float.parseFloat(price[1].toString()),"medicine");
                db.removeCart(username,"medicine");
                Toast.makeText(getApplicationContext(), "Your Booking is Done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuyMedicineBookActivity.this,HomeActivity.class));
            }
        });
    }
}