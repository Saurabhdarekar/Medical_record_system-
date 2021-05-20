package com.example.medi1;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class mysingleton {
    private static mysingleton mIstance;
    private RequestQueue requestQueue;
    private static Context mCtx;
    private mysingleton (Context context)
    {
        mCtx = context;

        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue==null) {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized mysingleton getInstance(Context context)
    {
        if(mIstance==null)
        {
            mIstance=new mysingleton((context));
        }
        return mIstance;
    }

    public<T> void addToRequestQueue(Request<T> request)
    {
        requestQueue.add(request);
    }

}
