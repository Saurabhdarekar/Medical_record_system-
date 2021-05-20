package com.example.medi1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class appointment_history extends AppCompatActivity {

    ListView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_history);

        a=findViewById(R.id.patient_appointment_list);
        final ArrayList<String> b=new ArrayList<>();

        final  ArrayList<appointment_information> appointment_info=new ArrayList<>();
        global g = new global();
        String url ="http://" + g.ip + "/doctor_appointment_history";
        RequestQueue queue = Volley.newRequestQueue(this);

       // String s[];
       /*
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        //textView.setText("Response: " + response.toString());
                       // b.add(response.toString());

                        //JSONArray x=response.getJSONArray("ok");
                        try {
                            JSONArray x=response.getJSONArray("result");
                            for(int i=0;i<x.length();i++)
                            {
                                JSONObject y=x.getJSONObject(i);
                                String z=y.getString("email");
                                Log.e("hello",z);

                                //b.add(z);

                                appointment_info.add(new appointment_information(z,"1","2","3","4","5"));
                            }


                           Myadapter abc=new Myadapter(appointment_history.this,R.layout.appointment_info,appointment_info);
                           a.setAdapter(abc);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Log.i(TAG,"onResponse: ", response);
                        //System.out.println(response);
                       // Log.e("hello",response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        queue.add(jsonObjectRequest); */




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
                                String z=y.getString("email");
                                String z1=y.getString("doctor_name");
                                String z2=y.getString("doctor_id");
                                String z3=y.getString("date");
                                String z4=y.getString("time");
                                String z5=y.getString("status");
                                String z6=y.getString("appointment_id");
                                Log.e("hello",z);
                                Log.e("hello",z1);
                                Log.e("hello",z2);
                                Log.e("hello",z3);
                                Log.e("hello",z4);
                                Log.e("hello",z5);
                                Log.e("hello",z6);

                                //b.add(z);

                                appointment_info.add(new appointment_information(z1,z2,z3,z4,z5,z6));

                                //appointment_info.add(new appointment_information(y.getString("doctor_id"),y.getString("doctor_name"),y.getString("date"),y.getString("time"),y.getString("status"),y.getString("appointment_id")));
                            }

                            Myadapter abc=new Myadapter(appointment_history.this,R.layout.appointment_info,appointment_info);
                            a.setAdapter(abc);

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
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView text = (TextView) view.findViewById(R.id.textView4);

                String t = text.getText().toString();

                Toast.makeText(appointment_history.this,t + " " ,Toast.LENGTH_SHORT).show();
            }
        });
       /* b.add("parth joshi"+"     "+"234"+"\n"+"12/12/12   6:30 pm"+"      "+"pending");
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

        Log.e("hello","my name is parth");
        */

        // ArrayAdapter c=new ArrayAdapter(this,android.R.layout.simple_list_item_1,b);
        //a.setAdapter(c);
    }
}
