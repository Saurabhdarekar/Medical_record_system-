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

public class doctor_signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_signup);
    }


    public void doctor_signup_send(View view)
    {
        //final TextView textView = (TextView) findViewById(R.id.text);
        EditText a=findViewById(R.id.editText12);
        EditText b=findViewById(R.id.editText13);
        EditText c=findViewById(R.id.editText14);
        EditText d=findViewById(R.id.editText15);
        EditText e=findViewById(R.id.editText16);
        EditText f=findViewById(R.id.editText17);
        // TextView d=findViewById(R.id.textView3);

        final String a1=a.getText().toString();
        final String b1=b.getText().toString();
        final String c1=c.getText().toString();
        final String d1=d.getText().toString();
        final String e1=e.getText().toString();
        final String f1=f.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        global g = new global();
        String url ="http://" + g.ip + "/doctor_signup";

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
                map.put("name",a1);
                map.put("practice_type",b1);
                map.put("years_of_experience",c1);
                map.put("location",d1);
                map.put("email",e1);
                map.put("password",f1);
                return map;
            }

        };
        queue.add(stringRequest);

    }


}
