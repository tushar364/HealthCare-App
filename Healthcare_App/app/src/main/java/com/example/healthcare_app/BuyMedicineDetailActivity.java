package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailActivity extends AppCompatActivity {

    TextView tvPackageName,tvTotalCost;
    EditText edDetail;
    Button btnAddToCart,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_detail);

        btnBack = findViewById(R.id.BMDbtnback);
        btnAddToCart = findViewById(R.id.BMDbtnAddCart);
        tvPackageName = findViewById(R.id.BMDPackageName);
        tvTotalCost = findViewById(R.id.BMDDtotalCostTextview);
        edDetail = findViewById(R.id.BMDmultiLineEditText);
        edDetail.setKeyListener(null);

        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetail.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost : "+intent.getStringExtra("text3")+ "/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailActivity.this,BuyMedicineActivity.class));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                database db = new database(getApplicationContext(),"healthcare",null,1);

                if (db.checkCart(username,product)==1) {
                    Toast.makeText(BuyMedicineDetailActivity.this, "Product already added", Toast.LENGTH_SHORT).show();

                } else {
                    //db = new database(getApplicationContext(),"healthcare",null,1);
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(BuyMedicineDetailActivity.this, "Record inserted to cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailActivity.this,BuyMedicineActivity.class));
                }

            }
        });
    }
}