package com.example.medi1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void patient_start_(View view)
    {
        Intent a=new Intent(this,patient_start.class);
        startActivity(a);
    }

    public  void doctor_start_(View view)
    {
        Intent a=new Intent(this,doctor_start.class);
        startActivity(a);
    }

    public  void lab_start_(View view)
    {
        Intent a=new Intent(this,lab_start.class);
        startActivity(a);
    }
}
