package com.example.medi1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class docproFragment extends Fragment {
    public String data;
    public TextView textView;
    public TextView textView1;
    RequestQueue requestQueue;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.docfragment_profile,
                container, false);
        global g = new global();
        String url = "http://" + g.ip + "/doctordata/"+EMAIL.id;
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,
                url , null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int count = 0;
                System.out.println("hiregn:"+EMAIL.id);
                try {
                    JSONObject jsonObject = response.getJSONObject("Data");
                    TextView Name = view.findViewById(R.id.docet1);
                    System.out.println(jsonObject.get("Name").toString());
                    String s = jsonObject.get("Name").toString();
                    System.out.println("sssssss"+s);
                    Name.setText(s);
                    s = jsonObject.get("Type").toString();
                    TextView PT = view.findViewById(R.id.docet2);
                    PT.setText(s);
                    s = jsonObject.get("YoE").toString();
                    TextView Exp = view.findViewById(R.id.docet3);
                    Exp.setText(s);
                    TextView Loc = view.findViewById(R.id.docet4);
                    s = jsonObject.get("location").toString();
                    Loc.setText(s);
                    s = jsonObject.get("email").toString();
                    TextView email = view.findViewById(R.id.docet5);
                    email.setText(s);
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