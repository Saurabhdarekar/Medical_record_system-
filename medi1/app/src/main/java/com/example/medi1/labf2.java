package com.example.medi1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class labf2 extends Fragment {
    String pdf_url;
    public labf2(String pdf)
    {
        pdf_url=pdf;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.labpdf_view,
                container, false);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdf_url));
        startActivity(browserIntent);
        return view;
    }
}