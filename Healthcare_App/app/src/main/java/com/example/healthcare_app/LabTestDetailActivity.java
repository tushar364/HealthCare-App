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

public class LabTestDetailActivity extends AppCompatActivity {
    TextView tvPackage,tvTotalCost;
    EditText edMultiline;
    Button btnBack,btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_detail);

        tvPackage = findViewById(R.id.ldPackageTitle);
        tvTotalCost = findViewById(R.id.lDtotalCost);
        edMultiline = findViewById(R.id.lDedittextMultiLine);
        btnBack = findViewById(R.id.btnlDback);
       btnAddToCart = findViewById(R.id.btnldAddCart);

       edMultiline.setOnKeyListener(null);
        Intent intent = getIntent();
        tvPackage.setText(intent.getStringExtra("text1"));
        edMultiline.setText(intent.getStringExtra("text2"));

        tvTotalCost.setText("Total Cost : " + intent.getStringExtra("text3") + "/-");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailActivity.this,LabTestActivity.class));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackage.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                database db = new database(getApplicationContext(),"healthcare",null,1);
                if (db.checkCart(username,product)==1) {
                    Toast.makeText(getApplicationContext(), "Product already added", Toast.LENGTH_SHORT).show();
                } else {
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(), "Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailActivity.this,LabTestActivity.class));
                }
            }
        });
    }
}