package com.example.medi1;

import android.content.Intent;
import android.net.Uri;
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

public class docactivepatient extends Fragment {
    ListView listView1,listView;
    docreportadapter userAdapter;
    ArrayList<String> arr;
    ArrayAdapter adapter;
    ListView userList;
    RequestQueue requestQueue;
    ArrayList<docreportlist> userArray = new ArrayList<docreportlist>();
    String patientid;
    public docactivepatient(String id){
        patientid=id;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.docpatientdetails,
                container, false);
        final ArrayList<String> StringArray = new ArrayList<String>();
        final ArrayList<String> StringArray1 = new ArrayList<String>();
        arr = new ArrayList<String>();
        /*StringArray.add("1");
        StringArray.add("2");
        StringArray.add("3");
        userArray.add(new docreportlist("Mumer"));
        userArray.add(new docreportlist("Jon"));
        userArray.add(new docreportlist("Broom"));
        userAdapter = new docreportadapter(getActivity(), R.layout.docreportlist,
                userArray);
        userList = (ListView)view.findViewById(R.id.doclistView2);
        //userList.setItemsCanFocus(false);
        userList.setAdapter(userAdapter);*/

        /*userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
                Log.i("List View Clicked", "**********");
                Toast.makeText(getActivity(),
                        "List View Clicked:" + position, Toast.LENGTH_LONG)
                        .show();
            }
        });*/
        listView = (ListView) view.findViewById(R.id.doclistView2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                String selectedFromList = (String) listView.getItemAtPosition(arg2);
                System.out.println(selectedFromList);
                String[] o = selectedFromList.split(",");
                String s = o[1];
                o = s.split(":");
                s = o[1].replace("\"","");
                s = s.replace("}","");
                System.out.println(s);
                global g = new global();
                String pdf_url="http://"+g.ip+"/file/"+s;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url));
                startActivity(browserIntent);
                Log.v("TAG", "CLICKED row number: " + arg2);
            }

        });
        //listView.setAdapter(userAdapter);
        //rrayAdapter adapter1 = new ArrayAdapter<String>(getActivity(),R.layout.docactivity_listview,StringArray);
        listView1 = (ListView) view.findViewById(R.id.doclistView3);
        //listView.setAdapter(adapter1);

        global g = new global();
        String[] o = patientid.split(",");
        String s = o[1];
        o = s.split(":");
        s = o[1].replace("\"","");
        String url = "http://" + g.ip + "/viewprescription/"+s;
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int count = 0;
                System.out.println("hi");
                try {
                    //firstname = response.getString("reports");
                    JSONArray jsonArray=response.getJSONArray("Reports");
                    /*for(int i=0;i<jsonArray.length();i++)
                    {
                        System.out.println(jsonArray.get(i).toString());
                        userArray.add(new docreportlist(jsonArray.get(i).toString()));
                    }
                    userAdapter = new docreportadapter(getActivity(), R.layout.docreportlist,
                            userArray);
                    userList = (ListView)view.findViewById(R.id.doclistView2);
                    //userList.setItemsCanFocus(false);
                    userList.setAdapter(userAdapter);
                    listView.setAdapter(userAdapter);*/

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        System.out.println(jsonArray.get(i).toString());
                        StringArray1.add(jsonArray.get(i).toString());
                    }
                    ArrayAdapter adapter1 = new ArrayAdapter<String>(getActivity(),R.layout.docactivity_listview1,StringArray1);
                    listView.setAdapter(adapter1);

                    jsonArray=response.getJSONArray("Medicines");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        System.out.println(jsonArray.get(i).toString());
                        StringArray.add(jsonArray.get(i).toString());
                    }
                    ArrayAdapter adapter2 = new ArrayAdapter<String>(getActivity(),R.layout.docactivity_listview,StringArray);
                    listView1.setAdapter(adapter2);
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