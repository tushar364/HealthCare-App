package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4;
    TextView tv;
    Button btnDate,btnTime,btnBookApp,btnAppBack;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        ed1 = findViewById(R.id.editTextAppFullName);
        ed2 = findViewById(R.id.editTextAppAddress);
        ed3 = findViewById(R.id.editTextAppContactNumber);
        ed4 = findViewById(R.id.editTextAppFees);
        tv = findViewById(R.id.textviewAppTitle);
        btnDate = findViewById(R.id.BtnAppDate);
        btnTime = findViewById(R.id.BtnAppTime);
        btnBookApp = findViewById(R.id.btnBookAppointment);
        btnAppBack = findViewById(R.id.btnBookAppBack);

        ed1.setOnKeyListener(null);
        ed2.setOnKeyListener(null);
        ed3.setOnKeyListener(null);
        ed4.setOnKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullName = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        String fees = it.getStringExtra("text5");

        tv.setText("title");
        ed1.setText(fullName);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText(fees);



        inItDatePicker();
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        inItTimePicker();
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

        btnAppBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(BookAppointmentActivity.this,FindDoctorActivity.class);
                startActivity(it);
            }
        });

        btnBookApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database db = new database(getApplicationContext(),"healthcare",null,1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                if(db.checkAppointmentExists(username,title + "=>"+ fullName,address,contact,btnDate.getText().toString(),btnTime.getText().toString())==1) {
                    Toast.makeText(BookAppointmentActivity.this, "Appointment already booked", Toast.LENGTH_SHORT).show();
                } else {
                    db.addOrder(username,title + "=>"+ fullName,address,contact,0,btnDate.getText().toString(),btnTime.getText().toString(),
                            Float.parseFloat(fees),"appointment");
                    Toast.makeText(BookAppointmentActivity.this, "your Appointment Book is successfully Done", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                }
            }
        });





    }
    public void inItDatePicker() {

      DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

          @Override
          public void onDateSet(DatePicker view, int i, int i1, int i2) {
              i1 = i1+1;
              btnDate.setText(i2 + "/" + i1 +"/" + i);

          }
      };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);

    }

    //for time picker
    public void inItTimePicker() {

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {


            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                btnTime.setText(i + ":" + i1);

            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int minutes = cal.get(Calendar.MINUTE);


        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,minutes,true);


    }

}