package com.example.medi1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class prescription_list extends AppCompatActivity {

    ListView a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_list);

        a=findViewById(R.id.patient_prescription_list);
        final ArrayList<String> b=new ArrayList<>();

        final  ArrayList<lab_appointment_information> lab_appointment_info=new ArrayList<>();

        global g = new global();
        String url ="http://" + g.ip + "/prescriptions";
        RequestQueue queue = Volley.newRequestQueue(this);




        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject m = new JSONObject(response);
                            JSONArray x=m.getJSONArray("result");
                            for(int i=0;i<x.length();i++)
                            {
                                JSONObject y=x.getJSONObject(i);
                                // String z=y.getString("email");
                                // String z1=y.getString("lab_name");
                                String z2=y.getString("patient_id");
                                // String z3=y.getString("date");
                                // String z4=y.getString("time");

                                String z5=y.getString("appointment_id");
                                // String z6=y.getString("doctor_id");
                               /* Log.e("hello",z);
                                Log.e("hello",z1);
                                Log.e("hello",z2);
                                Log.e("hello",z3);
                                Log.e("hello",z4);
                                Log.e("hello",z5);
                                Log.e("hello",z6); */

                                //b.add(z);
                                b.add(z2+"_"+z5);

                                // lab_appointment_info.add(new lab_appointment_information(z1,z2,z3,z4,z5,z6));

                                //appointment_info.add(new appointment_information(y.getString("doctor_id"),y.getString("doctor_name"),y.getString("date"),y.getString("time"),y.getString("status"),y.getString("appointment_id")));
                            }

                           /* Myadapter_lab abc=new Myadapter_lab(lab_report_list.this,R.layout.lab_appointment_info,lab_appointment_info);
                            a.setAdapter(abc); */

                            ArrayAdapter c=new ArrayAdapter(prescription_list.this, android.R.layout.simple_list_item_1,b);
                            a.setAdapter(c);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Handle error

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> map = new HashMap<String, String>();

                // pass your input text
                //map.put("doctor_id",doctor_id);
                //map.put("doctor_name",doctor_name);
                //map.put("date",date);
                //map.put("time",time);
                //map.put("problem",problem);
                map.put("email",EMAIL.email);
                // map.put("password",g1);
                return map;
            }

        };

        queue.add(stringRequest);
        a.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick (AdapterView<?> adapterView, View view, int i, long l)
            {
                // Toast.makeText (lab_report_list.this, "clicked item: "+i+""+ b.get (i).toString(), Toast.LENGTH_SHORT).show();
                String q= b.get (i).toString();
                startdisplay(q);


            }
        });
    }
    public void startdisplay(String q)
    {
        final Intent a=new Intent(prescription_list.this,lab_report_display.class);
        a.putExtra("id", q);
        startActivity(a);
    }






/*
    ListView a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_list);

        a=findViewById(R.id.patient_prescription_list);
        final ArrayList<String> b=new ArrayList<>();
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

        a.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick (AdapterView<?> adapterView, View view, int i, long l)
            {
                // Toast.makeText (prescription_list.this, "clicked item: "+i+""+ b.get (i).toString(), Toast.LENGTH_SHORT).show();
                startdisplay();


            }
        });

    }
    public void startdisplay()
    {
        final Intent a=new Intent(prescription_list.this,prescription_display.class);
        startActivity(a);
    }
    */


}
