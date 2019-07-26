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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class CreateAnId1 extends AppCompatActivity
        implements View.OnClickListener {

    private EditText editTextName, editTextEmail, editTextPassword,editTextDonateDate, editTextMobile, editTextAge, editTextSans;
    private Spinner spinner_district, spinner_area, spinner_blood, spinner_sq, spinner_gender;

    String[] areaName;
    String[] districtName;
    String[] squestion;
    String[] sgender;

    String[] bloodg;
    private Spinner spinner1;
    private Button btn;
    private EditText edt, edt1, edt2;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_an_id1);
        districtName = getResources().getStringArray(R.array.District_name);
        spinner_district = (Spinner) findViewById(R.id.create_id_district);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.districtview, R.id.textView_Spinner_district, districtName);
        spinner_district.setAdapter(adapter1);

        areaName = getResources().getStringArray(R.array.Area_name);
        spinner_area = (Spinner) findViewById(R.id.create_id_area);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sampleview, R.id.textView_Spinner_area, areaName);
        spinner_area.setAdapter(adapter);

        sgender = getResources().getStringArray(R.array.gender);
        spinner_gender = (Spinner) findViewById(R.id.create_id_gender);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.gender, R.id.create_gender, sgender);
        spinner_gender.setAdapter(adapter4);


        bloodg = getResources().getStringArray(R.array.blood_group);
        spinner_blood = (Spinner) findViewById(R.id.spinner_blood_group);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.blood_group, R.id.textView_Spinner_blood, bloodg);
        spinner_blood.setAdapter(adapter3);


        editTextName = findViewById(R.id.create_id_Name);
        editTextEmail = findViewById(R.id.create_id_email);
        editTextAge = findViewById(R.id.create_id_age);
        editTextPassword = findViewById(R.id.create_id_password);
        editTextMobile = findViewById(R.id.create_id_mobile);
        editTextDonateDate = findViewById(R.id.create_id_donatedate);
        spinner_district = findViewById(R.id.create_id_district);
        spinner_area = findViewById(R.id.create_id_area);

        spinner_blood = findViewById(R.id.spinner_blood_group);
        spinner_gender = findViewById(R.id.create_id_gender);


        findViewById(R.id.create_id_btn).setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    private void registerUser() {
        final String name = editTextName.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String district = spinner_district.getSelectedItem().toString();
        final String area = spinner_area.getSelectedItem().toString();
        final String gender = spinner_gender.getSelectedItem().toString();
        final String blood = spinner_blood.getSelectedItem().toString();
        final String mobile = editTextMobile.getText().toString().trim();
        final String age = editTextAge.getText().toString().trim();
        final String donateDate = editTextDonateDate.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            User user = new User(
                                    name,
                                    email,
                                    district,
                                    blood,
                                    area,
                                    mobile,
                                    age,
                                    donateDate,
                                    gender
                            );

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(CreateAnId1.this, getString(R.string.Confirm), Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getApplicationContext(),LoginMain.class);
                                        startActivity(intent);

                                    } else {
                                        //display a failure message
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(CreateAnId1.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_id_btn:
                registerUser();
                break;
        }
    }


}