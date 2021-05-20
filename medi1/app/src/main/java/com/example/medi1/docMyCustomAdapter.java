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

public class docMyCustomAdapter extends ArrayAdapter<docUser> {
    Context context;
    int layoutResourceId;
    ArrayList<docUser> data = new ArrayList <docUser>();

    public docMyCustomAdapter(Context context, int layoutResourceId,
                             ArrayList<docUser> data) {
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
            holder.textName = (TextView) row.findViewById(R.id.doctextView1);
            holder.textAddress = (TextView) row.findViewById(R.id.doctextView2);
            row.setTag(holder);
        } else {
            holder = (UserHolder) row.getTag();
        }
        docUser user = data.get(position);
        holder.textName.setText(user.getName());
        holder.textAddress.setText(user.getDescription());
        return row;

    }

    static class UserHolder {
        TextView textName;
        TextView textAddress;
        TextView textLocation;
        Button btnEdit;
        Button btnDelete;
    }
}