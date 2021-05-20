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

public class lab_signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_signup);
    }


    public void lab_signup_send(View view)
    {
        //final TextView textView = (TextView) findViewById(R.id.text);
        EditText a=findViewById(R.id.editText18);
        EditText b=findViewById(R.id.editText19);
        EditText c=findViewById(R.id.editText20);
        EditText d=findViewById(R.id.editText21);
        // TextView d=findViewById(R.id.textView3);
        global g = new global();
        final String a1=a.getText().toString();
        final String b1=b.getText().toString();
        final String c1=c.getText().toString();
        final String d1=d.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://" + g.ip + "/lab_signup";
        Log.e("url",url);
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
                map.put("location",b1);
                map.put("email",c1);
                map.put("password",d1);
                return map;
            }

        };
        queue.add(stringRequest);

    }



}
