package com.example.medi1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Myadapter_lab extends ArrayAdapter<lab_appointment_information> {



        private  static final String TAG="Myadapter";
        private Context mContext;
        int mResource;


        public Myadapter_lab(Context context, int resource, ArrayList<lab_appointment_information> objects) {
            super(context,resource,objects);
            mContext=context;
            mResource=resource;

}

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String doctor_id=getItem(position).getDoctor_id();
        String lab_name=getItem(position).getLab_name();
        String date=getItem(position).getDate();
        String time=getItem(position).getTime();
        String lab_id=getItem(position).getLab_id();
        String appointment_id=getItem(position).getAppointment_id();

        appointment_information a=new appointment_information(lab_name,lab_id,doctor_id,date,time,appointment_id);
        LayoutInflater b=LayoutInflater.from(mContext);
        convertView=b.inflate(mResource,parent,false);
        TextView c=(TextView) convertView.findViewById(R.id.textView40);
        TextView d=(TextView) convertView.findViewById(R.id.textView41);
        TextView e=(TextView) convertView.findViewById(R.id.textView42);
        TextView f=(TextView) convertView.findViewById(R.id.textView43);
        TextView g=(TextView) convertView.findViewById(R.id.textView44);
        TextView h=(TextView) convertView.findViewById(R.id.textView45);
        c.setText(lab_name);
        d.setText(lab_id);
        e.setText(date);
        f.setText(time);
        g.setText(appointment_id);
        h.setText(doctor_id);

        return convertView;
    }

    }
