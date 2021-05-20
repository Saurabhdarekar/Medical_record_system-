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

public class docHistoryfragment extends Fragment {
    ArrayList<String> arr;
    ListView listView;
    ArrayAdapter adapter;
    int count;
    String pdf_url;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.docfragment_active,
                container, false);
        String[] list={"h"};
        arr = new ArrayList<String>();
        RequestQueue requestQueue;
        System.out.println("print");
        listView = (ListView)view.findViewById(R.id.doctreated);

        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,
                "\"http://\" + g.ip + \"history", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int count = 0;
                System.out.println("hi");
                try {
                    //firstname = response.getString("reports");
                    JSONArray jsonArray=response.getJSONArray("history");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                         System.out.println(jsonArray.get(i).toString());
                        arr.add(jsonArray.get(i).toString());
                    }
                    adapter = new ArrayAdapter<String>(getActivity(), R.layout.docactivity_listview1, arr);
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
                String selectedFromList = (String) listView.getItemAtPosition(arg2);
                System.out.println(selectedFromList);
                int i = selectedFromList.indexOf("image_name");
                int l = selectedFromList.length();
                String file = selectedFromList.substring(i+13,l-2);
                System.out.println(file);
                global g = new global();
                pdf_url ="http://" + g.ip + "/file/"+file;
                Fragment newfrag;
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.docfragment_container,new docf2(pdf_url));
                fragmentTransaction.commit();
                Log.v("TAG", "CLICKED row number: " + arg2);
            }

        });
        return view;
    }

}