package com.example.medi1;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class labupload extends Fragment {
    ArrayList<String> arr;
    ListView listView;
    ArrayAdapter adapter;
    int count;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.labfragment_upload,
                container, false);
        String[] list={"h"};
        arr = new ArrayList<String>();
        RequestQueue requestQueue;
        System.out.println("print");
        listView = (ListView)view.findViewById(R.id.labmobile_list);
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        global g = new global();
        String url ="http://" + g.ip + "/getlist/"+EMAIL.id;
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int count = 0;
                System.out.println("hi");
                try {
                    //firstname = response.getString("reports");
                    JSONArray jsonArray=response.getJSONArray("pending");
                    //JSONObject jsonObject=response.getJSONObject("pending");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        //JSONObject o = jsonObject.getJSONObject("")
                        // System.out.println(jsonArray.get(i).toString());
                        arr.add(jsonArray.get(i).toString());
                    }
                    adapter = new ArrayAdapter<String>(getActivity(), R.layout.labactivity_listview, arr);
                    listView.setAdapter(adapter);
                }catch (JSONException e){
                    e.printStackTrace();
                }
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
        for(int i=0;i<arr.size();i++){
            System.out.println(arr.get(i));
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
               // getFragmentManager().beginTransaction().replace(R.id.mobile_list, new Historyfragment()).addToBackStack(null).commit();
                String selectedFromList = (String) listView.getItemAtPosition(arg2);
                Fragment newfrag;
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.labfragment_container,new labf1(selectedFromList));
                fragmentTransaction.commit();
                Log.v("TAG", "CLICKED row number: " + arg2);
            }

        });
        return view;
    }
    /*public void getlist(String name)
    {
        arr = new ArrayList<String>();
        RequestQueue requestQueue;
        System.out.println("print");
        global g = new global();
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,
                "http://"+ g.ip +"/getlist/get?id="+EMAIL.id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int count = 0;
                System.out.println("hi");
                try {
                    //firstname = response.getString("reports");
                    JSONArray jsonArray=response.getJSONArray("pending");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                       // System.out.println(jsonArray.get(i).toString());
                        arr.add(jsonArray.get(i).toString());
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
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
    }*/
}