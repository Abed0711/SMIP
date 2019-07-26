package com.example.abed.smit;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget_pass01 extends AppCompatActivity
         {
    private Button ResetPasswordSendEmailButton;
    private EditText ResetEmailInput;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_forget_pass01);

        mAuth = FirebaseAuth.getInstance();

        ResetPasswordSendEmailButton = (Button) findViewById(R.id.reset_password_button);
        ResetEmailInput = (EditText) findViewById(R.id.reset_password_email);

        ResetPasswordSendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = ResetEmailInput.getText().toString().trim();

                if (TextUtils.isEmpty(userEmail)) {
                    Toast.makeText(forget_pass01.this, "Please Enter Your Email Address", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(forget_pass01.this, "Please Check your email", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(forget_pass01.this, LoginMain.class));
                            } else {
                                String message = task.getException().getMessage();
                                Toast.makeText(forget_pass01.this, "Error Occurred : " + message, Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }

            }
        });


    }


}
