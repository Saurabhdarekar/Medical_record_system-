package com.example.medi1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class patient_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
    }

    public void patient_login_send(View view) {

      final Intent a = new Intent(this, mainpage.class);
 /*startActivity(a); */



        EditText b = findViewById(R.id.editText2);
        EditText c = findViewById(R.id.editText3);


        final String d = b.getText().toString();
        final String e = c.getText().toString();


        global g = new global();
        String url ="http://" + g.ip + "/patient_login";
        RequestQueue queue = Volley.newRequestQueue(this);







        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //textView.setText("Response is: "+ response.substring(0,500));

                        try {

                            JSONObject x = new JSONObject(response);
                            JSONObject abc = x.getJSONObject("response");
                            String z = abc.getString("status");

                            if(z.equals("approved")) {

                                String z1 = abc.getString("id");
                                String z2 = abc.getString("email");
                                EMAIL.email=z2;
                                EMAIL.id=z1;
                                a.putExtra("id", z1);
                                a.putExtra("email", z2);
                                startActivity(a);
                            }
                            else //if(z=="wrong_password")
                            {
                                Toast.makeText(patient_login.this,z + " " ,Toast.LENGTH_SHORT).show();
                            }


                            //final TextView name = (TextView) findViewById(R.id.textViewname);
                            //name.setText(z1);
                            //final TextView email = (TextView) findViewById(R.id.textViewemail);
                            //email.setText(z2);


                            Log.e("res", z);
                            //Log.e("res", z1);
                            //Log.e("res", z2);
                            Log.e("res", "hello");
                            //startActivity(a);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //JSONObject abc = new JSONObject(response);
                        //Log.e("res",response);
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

                map.put("email", d);
                map.put("password", e);
                return map;
            }

        };
        queue.add(stringRequest);





    }
}
