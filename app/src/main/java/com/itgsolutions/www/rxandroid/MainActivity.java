package com.itgsolutions.www.rxandroid;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.utils.Orientation;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    String[] zipTypes = {".txt"};

    private int MAX_ATTACHMENT_COUNT = 5;
    private ArrayList<String> photoPaths = new ArrayList<>();
    private ArrayList<String> docPaths = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img_holder = findViewById(R.id.img_holder);

        Log.e(TAG, "DEV Update Test");
        Log.e(TAG, "DEV Update Test");
        Log.e(TAG, "DEV Update Test");
        Log.e(TAG, "DEV Update Test");
        Log.e(TAG, "DEV Update Test");
        Log.e(TAG, "DEV Update Test");

        Glide.with(MainActivity.this).load("http://you-ps.ru/uploads/posts/2013-08/1376601606_1273.png")
                .apply(RequestOptions
                        .centerCropTransform()
                        .dontAnimate()
                        .error(R.mipmap.ic_launcher)
                        .placeholder(droidninja.filepicker.R.drawable.image_placeholder))
                .thumbnail(0.5f)
                .into(img_holder);

        img_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPickDoc();

            }
        });

    }

    public void onPickDoc() {
        String[] zips = {".zip",".rar",".txt"};
        String[] pdfs = {".mp3",".pdf"};
        int maxCount = MAX_ATTACHMENT_COUNT-photoPaths.size();
        if((docPaths.size()+photoPaths.size())==MAX_ATTACHMENT_COUNT)
            Toast.makeText(this, "Cannot select more than " + MAX_ATTACHMENT_COUNT + " items", Toast.LENGTH_SHORT).show();
        else
            FilePickerBuilder.getInstance().setMaxCount(maxCount)
                    .setSelectedFiles(docPaths)
                    .setActivityTheme(R.style.AppTheme_NoActionBar)
                    .addFileSupport("ZIP",zips)
                    .addFileSupport("PDF",pdfs)
                    .enableDocSupport(false)
                    .withOrientation(Orientation.UNSPECIFIED)
                    .pickFile(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FilePickerConst.REQUEST_CODE_DOC:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    docPaths = new ArrayList<>();
                    docPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS));
                }
                break;
        }

        for (String s : photoPaths) {
            Log.e(TAG, "url => " + s);
        }

    }


}
