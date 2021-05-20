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
import android.widget.ListView;
import android.widget.Toast;

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

public class docAppointment extends Fragment {
    ArrayList<String> arr;
    ListView listView;
    ArrayAdapter adapter;
    ListView userList;
    docMyCustomAdapter userAdapter;
    ArrayList<docUser> userArray = new ArrayList<docUser>();
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.docfragment_appointment,
                container, false);
        RequestQueue requestQueue;
        userArray.add(new docUser("Mumer", "Spain"));
        userArray.add(new docUser("Jon", "EW"));
        userArray.add(new docUser("Broom", "Span"));
        userArray.add(new docUser("Lee", "Aus"));
        userArray.add(new docUser("Jon", "EW"));
        userArray.add(new docUser("Broom", "Span"));
        userArray.add(new docUser("Lee", "Aus"));
        /**
         * set item into adapter
         */
        userAdapter = new docMyCustomAdapter(getActivity(), R.layout.doclistviewbutton,
                userArray);
        userList = (ListView)view.findViewById(R.id.doclistView);
        //userList.setItemsCanFocus(false);
        userList.setAdapter(userAdapter);
        /**
         * get on item click listener
         */
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
                Log.i("List View Clicked", "**********");
                Toast.makeText(getActivity(),
                        "List View Clicked:" + position, Toast.LENGTH_LONG)
                        .show();
            }
        });
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        global g = new global();
        String url ="http://" + g.ip + "/history";
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
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
        return view;
    }
}
