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

public class LabTestBookActivity extends AppCompatActivity {
    EditText edFullName,edAddress,edPinCode,edContactNumber;
    Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);
        edFullName = findViewById(R.id.edtFullNameLTB);
        edAddress = findViewById(R.id.edtAddressLTB);
        edPinCode = findViewById(R.id.edtPinCodeLTB);
        edContactNumber = findViewById(R.id.edtContactnumberLTB);
        btnBook = findViewById(R.id.btnBookLTB);

        Intent intent= getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String data = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                database db = new database(getApplicationContext(),"healthcare",null,1);

                db.addOrder(username,edFullName.getText().toString(),edAddress.getText().toString(),edContactNumber.getText().toString(),Integer.parseInt(edPinCode.getText().toString()),
                        data.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removeCart(username,"lab");
                Toast.makeText(getApplicationContext(), "Your Booking is Done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestBookActivity.this,HomeActivity.class));
            }
        });
    }
}