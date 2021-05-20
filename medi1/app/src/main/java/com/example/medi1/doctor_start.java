package com.example.medi1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class doctor_start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_start);


    }

    public void doctor_login_(View view) {
        Intent a = new Intent(this, doctor_login.class);
        startActivity(a);
    }

    public void doctor_signup_(View view) {
        Intent a = new Intent(this, doctor_signup.class);
        startActivity(a);
    }
}

