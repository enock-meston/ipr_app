package com.nigoote.ipr_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class ViewQuranActivity extends AppCompatActivity {

    PDFView mPDFView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quran);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //display back icon
        mPDFView = (PDFView) findViewById(R.id.pdfView);
        mPDFView.fromAsset("quran_english.pdf").load();
    }
}