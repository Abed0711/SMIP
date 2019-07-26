package com.example.abed.smit;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.inputmethodservice.ExtractEditText;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginMain extends AppCompatActivity {
    private TextView textView;
    private TextView textView_forgot_pass;
    private Button login_btn;
    FirebaseAuth mAuth;
    EditText useremail,userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        useremail=(EditText)findViewById(R.id.user_id);
        userpassword=(EditText)findViewById(R.id.user_password);
        mAuth=FirebaseAuth.getInstance();

        textView=(TextView)findViewById(R.id.createId);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateAnId1.class);
                startActivity(intent);

            }
        });

        textView_forgot_pass=(TextView)findViewById(R.id.forgot_password);
        textView_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),forget_pass01.class);
                startActivity(intent);
            }
        });

        login_btn=(Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=useremail.getText().toString().trim();
                String password=userpassword.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    startActivity(new Intent(getApplicationContext(),main_menu.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });

    }
/*
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginMain.this);
        builder.setMessage("Are you sure to do this?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        moveTaskToBack(false);
    }
*/
}
