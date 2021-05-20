package com.example.medi1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Myadapter extends ArrayAdapter<appointment_information> {

   private  static final String TAG="Myadapter";
    private Context mContext;
    int mResource;


    public Myadapter(Context context, int resource, ArrayList<appointment_information> objects) {
        super(context,resource,objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        String doctor_id=getItem(position).getDoctor_id();
        String doctor_name=getItem(position).getDoctor_name();
        String date=getItem(position).getDate();
        String time=getItem(position).getTime();
        String status=getItem(position).getStatus();
        String appointment_id=getItem(position).getAppointment_id();

        appointment_information a=new appointment_information(doctor_id,doctor_name,date,time,appointment_id,status);
        LayoutInflater b=LayoutInflater.from(mContext);
        convertView=b.inflate(mResource,parent,false);
        TextView c=(TextView) convertView.findViewById(R.id.textView2);
        TextView d=(TextView) convertView.findViewById(R.id.textView4);
        TextView e=(TextView) convertView.findViewById(R.id.textView5);
        TextView f=(TextView) convertView.findViewById(R.id.textView6);
        TextView g=(TextView) convertView.findViewById(R.id.textView);
        TextView h=(TextView) convertView.findViewById(R.id.textView7);
        c.setText(doctor_id);
        d.setText(doctor_name);
        e.setText(date);
        f.setText(time);
        g.setText(appointment_id);
        h.setText(status);

        return convertView;
    }
}
