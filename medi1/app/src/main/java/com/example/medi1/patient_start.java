package com.example.medi1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class patient_start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_start);
    }
    public void patient_login_(View view)
    {
        Intent a=new Intent(this,patient_login.class);
        startActivity(a);
    }

    public void patient_signup_(View view)
    {
        Intent a=new Intent(this,patient_signup.class);
        startActivity(a);
    }

}
