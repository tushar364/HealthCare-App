package com.example.healthcare_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername,edEmail,edPassword,edConfirmPass;
    Button btnRegister;
    TextView existingUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextRegisterUsername);
        edPassword = findViewById(R.id.editTextRegisterPassword);
        edConfirmPass = findViewById(R.id.editTextRegisterConfirmPassword);
        edEmail = findViewById(R.id.editTextRegisterEmail);
        btnRegister = findViewById(R.id.btnRegister);
        existingUser = findViewById(R.id.textviewExistingUser);

        //to return login page
        existingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Iexituser = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(Iexituser);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirmPass = edConfirmPass.getText().toString();
                database db = new database(getApplicationContext(),"healthcare",null,1);

                if(username.length()==0||email.length()==0||password.length()==0||confirmPass.length()==0) {
                    Toast.makeText(RegisterActivity.this, "Please fill all this details", Toast.LENGTH_SHORT).show();
                } else {
                    if(password.compareTo(confirmPass)==0) {
                        if (isValid(password)) {

                           db.registration(username,email,password);

                            Toast.makeText(RegisterActivity.this, "Record Inserted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        }else {
                            Toast.makeText(RegisterActivity.this, "Password Must Contain at least 8 characters, having letter,digit and number", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(RegisterActivity.this, "Password and Confirm Password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
    public static boolean isValid(String passwordhere) {
        int f1=0,f2=0,f3=0;
        if(passwordhere.length()<8) {
            return false;
        } else {
            for (int p =0;p<passwordhere.length();p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1=1;
                }
            }
            for (int r =0;r<passwordhere.length();r++) {
                if(Character.isLetter(passwordhere.charAt(r))) {
                    f2=1;
                }
            }

            for (int s=0;s<passwordhere.length();s++) {
                if (Character.isLetter(passwordhere.charAt(s))) {
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1) {
                return true;

            }
        }

        return false;
    }

}