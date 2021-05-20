package com.example.medi1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class lab_start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_start);
    }

    public  void lab_login_(View view)
    {
        Intent a=new Intent(this,lab_login.class);
        startActivity(a);
    }

    public void lab_signup_(View view) {
        Intent a = new Intent(this, lab_signup.class);
        startActivity(a);
    }
}
