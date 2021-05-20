package com.example.medi1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.android.volley.RequestQueue;


public class labMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.labactivity_main);



        Toolbar toolbar = findViewById(R.id.labtoolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.labdrawer_layout);
        NavigationView navigationView = findViewById(R.id.labnav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.labfragment_container,
                    new labproFragment()).commit();
            navigationView.setCheckedItem(R.id.labnav_account);

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.labnav_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.labfragment_container,
                        new labproFragment()).commit();
                break;
            case R.id.labnav_upload:
                getSupportFragmentManager().beginTransaction().replace(R.id.labfragment_container,
                        new labupload()).commit();
                break;

            case R.id.labnav_new_client:
                getSupportFragmentManager().beginTransaction().replace(R.id.labfragment_container,
                        new labncfragment()).commit();
                break;
            case R.id.labnav_history:
                getSupportFragmentManager().beginTransaction().replace(R.id.labfragment_container,
                        new labHistoryfragment()).commit();
                break;
            case R.id.labnav_logout:
                Intent b=new Intent(this,MainActivity.class);
                startActivity(b);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*public String getreport(String name) {
        String data = "";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "http://192.168.2.22:5000/reports/" + name, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                String name1 = "";
                while (count < response.length()) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = response.getJSONObject((count));
                        name1 = name1 + "," + jsonObject.getString("name");
                        //Log.d("myapp",name1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //data = name1;
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
   public void get()
    {
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "http://192.168.2.14:5000/users", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                String name = "";
                while (count<response.length())
                {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = response.getJSONObject((count));
                        name += jsonObject.getString("name");
                        Log.d("myapp",name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
                //TextViewResult.setText(name);
                //Log.d("myapp","The response is" + response.getString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myapp","wrong");
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
*/
}