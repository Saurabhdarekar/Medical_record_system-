package com.example.medi1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class mainpage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        //setContentView(R.layout.nav_header_mainpage);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Intent a=getIntent();
        //final TextView name = (TextView) findViewById(R.id.textViewname);
        //name.setText("pj");
        //final TextView email = (TextView) findViewById(R.id.textViewemail);
        //email.setText("wow");

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }); */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       // Intent a=getIntent();
        //final TextView name = (TextView) findViewById(R.id.textViewname);
        //name.setText("pj");
        //final TextView email = (TextView) findViewById(R.id.textViewemail);
        //email.setText("wow");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainpage, menu);
        Intent a=getIntent();
        final TextView name = (TextView) findViewById(R.id.textViewname);
        name.setText(EMAIL.username);
        final TextView email = (TextView) findViewById(R.id.textViewemail);
        email.setText(EMAIL.email);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //Intent z=getIntent();
        //final TextView email = (TextView) findViewById(R.id.textViewemail);
       // email.setText(z.getStringExtra("email"));

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        //Intent z=getIntent();

        int id = item.getItemId();

        if (id == R.id.book_appointment) {
            // Handle the camera action
            Intent a=new Intent(this,bookappointment.class);
            //a.putExtra("email",z.getStringExtra("email"));
            startActivity(a);
        } else if (id == R.id.prescription) {
            Intent c=new Intent(this,prescription_list.class);
            startActivity(c);

        }
        else if (id == R.id.lab_appoint) {
            Intent f = new Intent(this, book_lab_appointment.class);
            startActivity(f);
        }
        else if (id == R.id.lab_reports) {
            Intent d=new Intent(this,lab_report_list.class);
            startActivity(d);

        } else if (id == R.id.vaccines) {
            Intent e=new Intent(this,vaccine_list.class);
            startActivity(e);

        }
        else if (id == R.id.appointment_history) {
            Intent b=new Intent(this,appointment_history.class);


            startActivity(b);
        }
        else if (id == R.id.patient_logout) {
            Intent b=new Intent(this,MainActivity.class);


            startActivity(b);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
