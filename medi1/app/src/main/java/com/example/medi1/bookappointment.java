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

public class bookappointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookappointment);
    }

    public void book_doctor_appointment(View view)
    {
        EditText a=findViewById(R.id.editText);
        EditText b=findViewById(R.id.editText4);
        EditText c=findViewById(R.id.editText5);
        EditText d=findViewById(R.id.editText6);
        EditText e=findViewById(R.id.editText8);
        //Intent x=getIntent();
        //TextView x=(TextView)findViewById(R.id.textViewemail);
        //final String email=x.getStringExtra("email");
        final String email=EMAIL.email;
        //final String email="abc";
        final String doctor_id=a.getText().toString();
        final String doctor_name=b.getText().toString();
        final String date=c.getText().toString();
        final String time=d.getText().toString();
        final String problem=e.getText().toString();



        RequestQueue queue = Volley.newRequestQueue(this);
        global g = new global();
        String url ="http://" + g.ip + "book_doctor_appointment";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: "+ response.substring(0,500));
                        Log.e("res",response);
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
                map.put("doctor_id",doctor_id);
                map.put("doctor_name",doctor_name);
                map.put("date",date);
                map.put("time",time);
                map.put("problem",problem);
                map.put("email",email);
               // map.put("password",g1);
                return map;
            }
        };
        queue.add(stringRequest);
    }

}
