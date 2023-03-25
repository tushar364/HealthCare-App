package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorsDetailActivity extends AppCompatActivity {

    private String[][] doctorDetails1 =
            {


    {"Doctor Name : Shankar Dev","Hospital Address : Rajabazar","Exp : 3yrs","Contact no : 9560056569","600"},
    {"Doctor Name : Kamlesh Rao","Hospital Address : Kankarbagh","Exp : 1yrs","Contact no. : 9910123460","500"},
    {"Doctor Name : Ashish Jha","Hospital Address : Mithapur","Exp : 6yrs","Contact no : 8800478520","900"},
    {"Doctor Name : Naman Mishra","Hospital Address : Boring Road","Exp : 8yrs","Contact no : 9912345680","600"},
    {"Doctor Name : Pawan Dubey","Hospital Address : Danapur","Exp : 4yrs","Contact no : 9060708010","800"}

    };
    private String[][] doctorDetails2 =
            {


                    {"Doctor Name : Mohan Singh","Hospital Address : Rajeev Nagar","Exp : 12yrs","Contact no : 9560056569","1000"},
                    {"Doctor Name : Jagdeo Sahu","Hospital Address : Kankarbagh","Exp : 7yrs","Contact no. : 9910123460","800"},
                    {"Doctor Name : Ram Bhagat","Hospital Address : Mithapur","Exp : 6yrs","Contact no : 8800478520","750"},
                    {"Doctor Name : Neha Arora","Hospital Address : Boring Road","Exp : 5yrs","Contact no : 9912345680","800"},
                    {"Doctor Name : Payal Dutt","Hospital Address : Rajabazar","Exp : 2yrs","Contact no : 9060708010","650"}

            };
    private String[][] doctorDetails3 =
            {


                    {"Doctor Name : Suman Shekhar","Hospital Address : Danapur","Exp : 3yrs","Contact no : 9560056569","600"},
                    {"Doctor Name : Megha Kashyap","Hospital Address : Kankarbagh","Exp : 1yrs","Contact no. : 9910123460","500"},
                    {"Doctor Name : Shalini Jha","Hospital Address : Mohanpur","Exp : 6yrs","Contact no : 8800478520","900"},
                    {"Doctor Name : Navneet Singh","Hospital Address :Kankarbagh","Exp : 8yrs","Contact no : 9912345680","600"},
                    {"Doctor Name : Abhishek Pukar","Hospital Address : Danapur","Exp : 4yrs","Contact no : 9060708010","8"}

            };
    private String[][] doctorDetails4 =
            {


                    {"Doctor Name : Abdul Samad","Hospital Address : Rajendar Nagar","Exp : 7yrs","Contact no : 9560056569","1000"},
                    {"Doctor Name : Raveen Yadav","Hospital Address : Gandhi Maidan","Exp : 3yrs","Contact no. : 9910123460","800"},
                    {"Doctor Name : Adita Giri","Hospital Address : Raja Bazar","Exp : 10yrs","Contact no : 8800478520","750"},
                    {"Doctor Name : Gulshan Singh","Hospital Address : Boring Road","Exp : 5yrs","Contact no : 9912345680","800"},
                    {"Doctor Name : Arvind rai","Hospital Address : Mithapur","Exp : 4yrs","Contact no : 9060708010","650"}

            };
    private String[][] doctorDetails5 =
            {


                    {"Doctor Name : Shivani Mishra","Hospital Address : Danapur","Exp : 3yrs","Contact no : 9560056569","600"},
                    {"Doctor Name : Richa Thakur","Hospital Address : Kankarbagh","Exp : 1yrs","Contact no. : 9910123460","500"},
                    {"Doctor Name : Rajesh Yadav","Hospital Address : Mohanpur","Exp : 6yrs","Contact no : 8800478520","900"},
                    {"Doctor Name : Anu Ojha","Hospital Address :Kankarbagh","Exp : 8yrs","Contact no : 9912345680","600"},
                    {"Doctor Name : Manu Singh","Hospital Address : Danapur","Exp : 4yrs","Contact no : 9060708010","800"}

            };

    TextView tv;
    Button backbtn;

    String[][] doctor_details = {};
    ArrayList list;
    SimpleAdapter simpleAdapter;
    HashMap<String,String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_detail);
        tv = findViewById(R.id.DDtTitle);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians")==0) {
            doctor_details = doctorDetails1;
        } else {
            if(title.compareTo("Dietician")==0) {
                doctor_details = doctorDetails2;
            }
        }
            if(title.compareTo("Dentist")==0) {
                doctor_details = doctorDetails3;
            }
                if(title.compareTo("Surgeon")==0) {
                    doctor_details = doctorDetails4;}


            else {
                if (title.compareTo("Cardiologist")==0)

                        doctor_details = doctorDetails5;
                }

        backbtn = findViewById(R.id.btnDDback);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorsDetailActivity.this,FindDoctorActivity.class));
            }
        });

        list = new ArrayList<>();
        for (int i=0;i<doctor_details.length;i++) {
            item = new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees:" + doctor_details[i][4] + "/-");
            list.add(item);
        }
        simpleAdapter = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView list = findViewById(R.id.listviewDD);
        list.setAdapter(simpleAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(DoctorsDetailActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);

                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);

            }
        });






    }
}