package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages = {
            {"Uprise-D3 1000IU Capsule","","","","50"},
            {"Healthvit Chromium Picolinate 200Mcg Capsule","","","","311"},
            {"Vitamin B Complex Capsule","","","","450"},
            {"Inlife Vitamin E Wheat Germ Oil Capsule","","","","350"},
            {"Dolo 650 Tablet","","","","50"},
            {"Crocine 650 Advance Tablet","","","","48"},
            {"Strepsils Medicated Lozenges for sore throat ","","","","38"},
            {"TATA 1mg Calcium + Vitamin D3","","","","40"},
            {"Feronia-XT Tablet  ","","","","130"},

    };

    private String[] package_details = new String[]{

            "Building and Keeping the bones & teeth strong\n" +
                    "Reducing Fatigue/Stress and Muscular Pain\n" +
                    "Boosting Immunity and Increasing resistance against infection\n",
            "Chromium is an essential trace mineral that play an important role in helping insulin regulate Blood Glucose\n",
            "Provides Relief from Vitamin-B Deficiencies\n" +
                    "Help in Formation of Red Blood Cells\n" +
                    "Maintain Healthy Nervous System\n",
            "It Promotes Health as well as Skin benefit\n" +
                    "It helps reduce Skin Blemish and Pigmenation\n" +
                    "It act as Safeguard the Skin from the harsh UVA and UVB Sun rays\n",
            "Dolo 650 Tablet helps relief pain and fever by blocking the release of certain chemical messenger responsible for fever and pain\n",
            "help Relief fever and bring down a high temperature\n" +
                    "Suitable for a people with a heart condition for high blood pressure\n",
            "Relieves the Symptoms of bacterial throat infection and soothes the recovery process\n" +
                    "Provides a warm and Comforting feeling during sore throat\n",
            "Reduces the risk of Calcium Deficiency,Rickets and Osteoporosis\n" +
                    "Promotes Mobility and Flexibility of joints\n",
            "Help to reduce the Iron Deficiency due to chronic Blood loss or low intake of iron"

    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnBack,btnGoToCart;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        btnBack = findViewById(R.id.btnBMback);
        btnGoToCart = findViewById(R.id.btnBMgoToCart);
        listView = findViewById(R.id.listviewBM);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        list = new ArrayList<>();
        for (int i=0;i<packages.length;i++) {
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:" + packages[i][4] + "/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this,BuyMedicineDetailActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_details[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });

    }
}