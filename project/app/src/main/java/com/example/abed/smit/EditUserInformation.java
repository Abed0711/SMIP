package com.example.abed.smit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EditUserInformation extends AppCompatActivity {

    private EditText userName , userMobile , donateDate ;
    private Button confirmButton;
    private DatabaseReference settinguserref;
    private FirebaseAuth mAuth;
    private String currentUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_information);

        mAuth=FirebaseAuth.getInstance();
        currentUserId = mAuth.getCurrentUser().getUid();
        settinguserref= FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);

        userName=findViewById(R.id.update_id_Name);
        userMobile=findViewById(R.id.update_id_mobile);
        donateDate=findViewById(R.id.update_id_donatedate);
        confirmButton=findViewById(R.id.update_id_btn);

        settinguserref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String uName= dataSnapshot.child("name").getValue().toString();
                    String uMobile= dataSnapshot.child("mobile").getValue().toString();
                    String uDate= dataSnapshot.child("donateDate").getValue().toString();
                    userName.setText(uName);
                    userMobile.setText(uMobile);
                    donateDate.setText(uDate);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAccountInfo();
            }
        });
    }

    private void validateAccountInfo() {
        String usName=userName.getText().toString().trim();
        String usMobile=userMobile.getText().toString().trim();
        String usdonateDate=donateDate.getText().toString().trim();

        if(TextUtils.isEmpty(usName)){
            Toast.makeText(this,"please write your name",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(usMobile)){
            Toast.makeText(this,"please write your Mobile Number",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(usdonateDate)){
            Toast.makeText(this,"please write your Donate Date",Toast.LENGTH_SHORT).show();
        }
        else {
            UpdateAccountInfo(usName,usMobile,usdonateDate);
        }
    }

    private void UpdateAccountInfo(String usName, String usMobile, String usdonateDate) {
        HashMap userMap = new HashMap();
        userMap.put("name",usName);
        userMap.put("mobile",usMobile);
        userMap.put("donateDate",usdonateDate);
        settinguserref.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
               if(task.isSuccessful()){
                   SendUserTomainMenu();
                   Toast.makeText(EditUserInformation.this,"ACCOUNT SETTINGS UPDATE SUCCESSFULLY",Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(EditUserInformation.this,"ERROR OCCURED , WHILE UPDATEING ACCOUNT SETTING INFO...",Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    private void SendUserTomainMenu(){
        Intent intent = new Intent(getApplicationContext(),main_menu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }
}
