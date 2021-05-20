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

public class doccapts extends Fragment {
    ArrayList<String> arr;
    ListView listView;
    ArrayAdapter adapter;
    ListView userList;
    docreportadapter userAdapter;
    ArrayList<docreportlist> userArray = new ArrayList<docreportlist>();
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.docfragment_accepted,
                container, false);
        final ArrayList<String> StringArray = new ArrayList<String>();
        RequestQueue requestQueue;
        listView = (ListView) view.findViewById(R.id.doclistView4);
        global g = new global();
        String url ="http://" + g.ip + "/viewappointments/"+EMAIL.id;
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int count = 0;
                System.out.println("hi");
                try {
                    //firstname = response.getString("reports");
                    JSONArray jsonArray=response.getJSONArray("appointments");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        System.out.println(jsonArray.get(i).toString());
                        StringArray.add(jsonArray.get(i).toString());
                    }
                    adapter = new ArrayAdapter<String>(getActivity(), R.layout.docactivity_listview, StringArray);
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

/*
        StringArray.add("1");
        StringArray.add("2");
        StringArray.add("3");
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),,StringArray);
        listView = (ListView) view.findViewById(R.id.doclistView4);
        */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                String selectedFromList = (String) listView.getItemAtPosition(arg2);
                System.out.println(selectedFromList);
                Fragment newfrag;
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.docfragment_container,new docprescriptions(selectedFromList));
                fragmentTransaction.commit();
                Log.v("TAG", "CLICKED row number: " + arg2);
            }

        });
        return view;
    }
}
