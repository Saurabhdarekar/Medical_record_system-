package com.example.medi1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class vaccine_list extends AppCompatActivity {

    ListView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_list);

        a=findViewById(R.id.patient_vaccine_list);
        ArrayList<String> b=new ArrayList<>();
        b.add("parth joshi"+"     "+"234"+"\n"+"12/12/12   6:30 pm");
        b.add("hello");
        b.add("hi");
        b.add("hello");
        b.add("hi");
        b.add("hello");
        b.add("hi");
        b.add("hello");
        b.add("hi");
        b.add("hello");
        b.add("hi");
        b.add("hello");
        b.add("hi");
        b.add("hello");
        b.add("hi");
        b.add("hello");
        b.add("hi");
        b.add("hello");
        b.add("hi");
        b.add("hello");

        ArrayAdapter c=new ArrayAdapter(this,android.R.layout.simple_list_item_1,b);
        a.setAdapter(c);
    }
}
