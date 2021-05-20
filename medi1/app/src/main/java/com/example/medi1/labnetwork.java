package com.example.medi1;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class labnetwork extends MainActivity {
    public String data;
public String report(String name)
{
    data = "no reports";
    RequestQueue requestQueue;
    requestQueue = Volley.newRequestQueue(this);
    global g = new global();
    String url ="http://" + g.ip + "/reports/"+name;
    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
            url, null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            int count = 0;
            String name1 = "";
            while (count<response.length())
            {
                JSONObject jsonObject = null;
                try {
                    jsonObject = response.getJSONObject((count));
                    name1 = name1+","+jsonObject.getString("name");
                    //Log.d("myapp",name1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                data = name1;
                count++;
            }
            //TextViewResult.setText(name);
            //Log.d("myapp","The response is" + response.getString());
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            //Log.d("myapp","wrong");
        }
    });
    requestQueue.add(jsonArrayRequest);
    return data;
}
}