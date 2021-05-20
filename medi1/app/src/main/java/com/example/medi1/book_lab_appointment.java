package com.example.medi1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class book_lab_appointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_lab_appointment);
    }
        public void book_lab_appointment_(View view)
        {

            EditText a = findViewById(R.id.editText29);
            EditText b = findViewById(R.id.editText30);
            EditText c = findViewById(R.id.editText31);
            EditText d = findViewById(R.id.editText32);
            EditText e = findViewById(R.id.editText33);
            EditText f = findViewById(R.id.editText34);
            //Intent x=getIntent();
            //TextView x=(TextView)findViewById(R.id.textViewemail);
            //final String email=x.getStringExtra("email");
            final String email = EMAIL.email;
            //final String email="abc";
            final String lab_id = a.getText().toString();
            final String lab_name = b.getText().toString();
            final String date = c.getText().toString();
            final String time = d.getText().toString();
            final String diagnosis = e.getText().toString();
            final String doctor_id = f.getText().toString();


            RequestQueue queue = Volley.newRequestQueue(this);
            global g = new global();
            String url ="http://" + g.ip + "/book_lab_appointment";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            //textView.setText("Response is: "+ response.substring(0,500));
                            Log.e("res", response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // textView.setText("That didn't work!");
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    HashMap<String, String> map = new HashMap<String, String>();

                    // pass your input text
                    map.put("lab_id", lab_id);
                    map.put("lab_name", lab_name);
                    map.put("date", date);
                    map.put("time", time);
                    map.put("diagnosis", diagnosis);
                    map.put("email", email);
                    map.put("doctor_id", doctor_id);
                    // map.put("password",g1);
                    return map;
                }

            };
            queue.add(stringRequest);

        }
    }
