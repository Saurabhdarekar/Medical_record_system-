package com.example.medi1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class docncfragment extends Fragment {
    ArrayList<String> arr;
    ArrayAdapter adapter;
    global g = new global();
    String selecteditem;
    ListView listView;
    docMyCustomAdapter userAdapter;
    RequestQueue requestQueue;
    ArrayList<docUser> userArray = new ArrayList<docUser>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.docfragment_appointment,
                container, false);


        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        listView = view.findViewById(R.id.doclistView);
        String url = "http://" + g.ip + "/viewRequests/"+EMAIL.id;
        arr = new ArrayList<String>();
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int count = 0;
                System.out.println("hi");
                try {
                    //firstname = response.getString("reports");
                    JSONArray jsonArray = response.getJSONArray("requests");
                    System.out.println("hi");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String[] o = jsonArray.get(i).toString().split(",");
                        System.out.println();
                        arr.add(jsonArray.get(i).toString());
                        //userArray.add(new docUser(o[0]+" , "+o[1], o[2]+" , "+o[3]));
                    }
                    adapter = new ArrayAdapter<String>(getActivity(), R.layout.docactivity_listview1, arr);
                    listView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //TextViewResult.setText(name);
                Log.d("myapp", "The response is");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.d("myapp","wrong");
            }
        });
        requestQueue.add(jsonArrayRequest);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                selecteditem = (String) listView.getItemAtPosition(arg2);
                System.out.println(selecteditem);
            }

        });
        Button accept = view.findViewById(R.id.docbutton1);
        Button reject = view.findViewById(R.id.docbutton2);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postUrl = "http://" + g.ip + "/acceptReject";
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
                        EMAIL e = new EMAIL();
                        String[] o = selecteditem.split(",");
                        String s = o[0];
                        o = s.split(":");
                        s = o[1].replace("\"","");
                        System.out.println("Sss:"+s);
                        map.put("id",s);
                        map.put("docid",EMAIL.id);
                        map.put("accept","True");
                        return map;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postUrl = "http://" + g.ip + "/acceptReject";
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
                        // pass your input text
                        EMAIL e = new EMAIL();
                        String[] o = selecteditem.split(",");
                        String s = o[0];
                        o = s.split(":");
                        s = o[1].replace("\"","");
                        map.put("id",s);
                        System.out.println("Ssr:"+s);
                        map.put("docid",EMAIL.id);
                        map.put("accept","False");
                        return map;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
        return view;
    }

   /* public String report(String name)
    {
        data = "";
        RequestQueue requestQueue;
        global g = new global();
        String url ="http://" + g.ip + "/reports/"+name;
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
                    String in = response.getString("_id");
                    System.out.println(in);
                    JSONArray jsonArray=response.getJSONArray("reports");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        firstname += jsonArray.get(i)+",";
                    }
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
    }*/
}