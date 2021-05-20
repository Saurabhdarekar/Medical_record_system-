package com.example.medi1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class labncfragment extends Fragment {

    public String data;
    public TextView textView;
    public EditText editText;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.labfragment_nc,
                container, false);
        Button button = (Button) view.findViewById(R.id.labbt);
        Button button1 = (Button) view.findViewById(R.id.labbt1);
        textView = view.findViewById(R.id.labet2);
        editText = (EditText) view.findViewById(R.id.labet1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editText.getText().toString();
                String text = textView.getText().toString();
                String data = report(id);
                textView.setText(data);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editText.getText().toString();
                accept(id);
            }
        });
        return view;
    }    public String report(String id)
    {
        data = "";
        RequestQueue requestQueue;
        global g = new global();
        String url ="http://" + g.ip + "/reports/"+id;
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int count = 0;
                String firstname = "Reports:";
                try {
                    String a="repryts";
                    //firstname = response.getString("reports");
                    //String in = response.getString("_id");
                    //System.out.println(in);
                    JSONArray jsonArray=response.getJSONArray("reports");
                    System.out.println("hiasdfg"+jsonArray.toString());
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        firstname += jsonArray.get(i)+",";
                    }
                    System.out.println("hiasdfg"+firstname);
                    method(firstname);
                    textView.setText(firstname);
                }catch (JSONException e){
                    e.printStackTrace();
                }
                data="hi";
                //TextViewResult.setText(name);
                Log.d("myapp","The response is" );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d("myapp","wrong");
            }
        });
        requestQueue.add(jsonArrayRequest);
        System.out.println("hi");
        return data;
    }

    public String method(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == 'x') {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
    public void accept(final String id){
        global g = new global();
        String postUrl = "http://"+g.ip+"/accept";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, postUrl,
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
                String data = "";
                // pass your input
                map.put("id",id);
                map.put("labid",EMAIL.id);
                return map;
            }
        };
        requestQueue.add(stringRequest);

    }
}