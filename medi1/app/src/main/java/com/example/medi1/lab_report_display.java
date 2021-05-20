package com.example.medi1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

public class lab_report_display extends AppCompatActivity {
    ImageView img;

    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_report_display);

       // img = (ImageView) findViewById((R.id.imageView3));
        WebView webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        String pdf = url;
        webview.loadUrl(url);



        Intent intent = getIntent();
        String code=intent.getStringExtra("id");
        Toast.makeText(lab_report_display.this,code,Toast.LENGTH_SHORT).show();

        /*
        ImageRequest image = new ImageRequest( url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        img.setImageBitmap(response);
                    }


                },0,0,ImageView.ScaleType.CENTER_CROP,null,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // textView.setText("That didn't work!");
                Toast.makeText(lab_report_display.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });


        mysingleton.getInstance(lab_report_display.this).addToRequestQueue(image);
        */


    }
}
