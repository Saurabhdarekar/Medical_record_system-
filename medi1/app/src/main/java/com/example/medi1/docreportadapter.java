package com.example.medi1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class docreportadapter extends ArrayAdapter<docreportlist>{
    Context context;
    int layoutResourceId;
    ArrayList<docreportlist> data = new ArrayList <docreportlist>();

    public docreportadapter(Context context, int layoutResourceId,
                           ArrayList<docreportlist> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new UserHolder();
            holder.reportName = (TextView) row.findViewById(R.id.doctextView10);
            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        docreportlist user = data.get(position);
        holder.reportName.setText(user.getreportName());

        return row;

    }

    static class UserHolder {
        TextView reportName;
        Button btnopen;
    }
}