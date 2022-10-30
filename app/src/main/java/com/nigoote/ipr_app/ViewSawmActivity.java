package com.nigoote.ipr_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class ViewSawmActivity extends AppCompatActivity {

    PDFView mPDFView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sawm);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //display back icon
        mPDFView = (PDFView) findViewById(R.id.pdfView1);
        mPDFView.fromAsset("sawm.pdf").load();
    }
}