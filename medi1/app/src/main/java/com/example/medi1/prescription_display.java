package com.example.medi1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

public class prescription_display extends AppCompatActivity {
    ImageView img;

    String url = "";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_display);

        img = (ImageView) findViewById((R.id.imageView2));
        Intent intent = getIntent();
        String code=intent.getStringExtra("id");
        Toast.makeText(prescription_display.this,code,Toast.LENGTH_SHORT).show();

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
                Toast.makeText(prescription_display.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });


        mysingleton.getInstance(prescription_display.this).addToRequestQueue(image);

    }
}
