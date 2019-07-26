package com.example.abed.smit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.abed.smit.R;

public class PrescriptionUpload extends AppCompatActivity implements View.OnClickListener {
    private static final int PICK_PDF_REQUEST = 234;
    Button lunch;
    private Button buttonChoose;
    private Uri filePath;
    private Button buttonUpload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_upload);
        buttonChoose = (Button) findViewById(R.id.button1);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);


        //attaching listener
        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);


    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*|application/pdf|audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture PDF "), PICK_PDF_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
        }
    }


    private void uploadFile() {
        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on

            Uri pdfUri = Uri.parse(String.valueOf(filePath));
            Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                    .setText("Share PDF doc")
                    .setType("image/*|application/pdf|audio/*")
                    .setStream(pdfUri)
                    .getIntent()
                    .setPackage("com.google.android.apps.docs");
            startActivity(shareIntent);

        }
        //if there is not any file
        else {
            //you can display an error toast
        }
    }

    public void onClick(View view) {
        //if the clicked button is choose
        if (view == buttonChoose) {
            showFileChooser();
        }
        //if the clicked button is upload
        else if (view == buttonUpload) {
            uploadFile();
        }
    }

}


