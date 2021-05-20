package com.example.medi1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;



public class docprescriptions extends Fragment {
    private TextView tv;
    global g = new global();
    private String upload_URL = "http://" + g.ip + "/prescribe";
    private RequestQueue rQueue;
    private ArrayList<HashMap<String, String>> arraylist;
    String url = "https://www.google.com";
    int clickCounter = 0;
    EditText editText;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    String patientid;
    EditText e1,e2;
    public docprescriptions(String id) {
        patientid = id;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.docprescriptions,
                container, false);
        Button button = view.findViewById(R.id.docbutton2);
        Button submitbutton = view.findViewById(R.id.docsubmitbutton);
        final ListView listView = view.findViewById(R.id.doclistView);
        e1 = (EditText) view.findViewById(R.id.docet7);
        e2 = (EditText) view.findViewById(R.id.docet8);
        //Button photoButton = (Button) view.findViewById(R.id.docbutton5);
        rQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                String url = "http://" + g.ip + "/prescribe";
                Log.e("url", url);
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

                        String data = e1.getText().toString();
                        String report = e2.getText().toString();
                        // pass your input text
                        for (int i = 0; i < listItems.size(); i++) {
                            data += listItems.get(i) + ",";
                        }
                        EMAIL e = new EMAIL();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
                        String currentDateandTime = sdf.format(new Date());
                        String[] o = patientid.split(",");
                        String s = o[0];
                        o = s.split(":");
                        s = o[1].replace("\"","");
                        map.put("id", patientid);
                        map.put("date", currentDateandTime);
                        map.put("docid", EMAIL.id);
                        map.put("prescription", data);
                        map.put("reports", report);
                        return map;
                    }
                };
                rQueue.add(stringRequest);
            }
        });
        return view;
    }
}
