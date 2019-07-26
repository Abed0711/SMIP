package com.example.abed.smit;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class main_menu extends AppCompatActivity {

    private CardView cardView_search_doc;
    private CardView cardView_prescriptions;
    private CardView cardView_medicine;
    private CardView cardView_records;
    private CardView cardView_specialist;
    private CardView cardView_hospital;
    private CardView cardView_blood;
    private CardView cardView_internationalHospital;
    private CardView cardView_MedicineSearch;
    private CardView cardView_Exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main_menu);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditUserInformation.class);
                startActivity(intent);
            }
        });

        cardView_search_doc=(CardView) findViewById(R.id.main_menu_search_doc);
        cardView_search_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),FindDocActivity.class);
                startActivity(intent);
            }
        });

        cardView_prescriptions=(CardView) findViewById(R.id.main_menu_prescriptions);
        cardView_prescriptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PrescriptionUpload.class);
                startActivity(intent);
            }
        });

        cardView_medicine=(CardView)findViewById(R.id.main_menu_medicine);
        cardView_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FindMedicineActivity.class);
                startActivity(intent);
            }
        });



        cardView_records=(CardView) findViewById(R.id.recors);
        cardView_records.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(new ComponentName("com.google.android.apps.docs",
                        "com.google.android.apps.docs.app.NewMainProxyActivity"));
                try {
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Error: "+e, Toast.LENGTH_LONG).show();
                }

            }
        });

        cardView_specialist=(CardView)findViewById(R.id.speciality_main);
        cardView_specialist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SpecialstDoc.class);
                startActivity(intent);
            }
        });

        cardView_hospital=(CardView)findViewById(R.id.hospital);
        cardView_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AlarmActivity.class);
                startActivity(intent);
            }
        });

        cardView_blood=(CardView)findViewById(R.id.blood_donnor);
        cardView_blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SearchBloodActivity.class);
                startActivity(intent);
            }
        });

        cardView_internationalHospital=(CardView)findViewById(R.id.internationalHospital);
        cardView_internationalHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InternationalHospitacl.class);
                startActivity(intent);
            }
        });

        cardView_MedicineSearch=(CardView)findViewById(R.id.medicine_search);
        cardView_MedicineSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Medicinesearch1.class);
                startActivity(intent);
            }
        });

        cardView_Exit=(CardView)findViewById(R.id.Exit);
        cardView_Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),LoginMain.class);
                startActivity(intent);*/

                final AlertDialog.Builder builder = new AlertDialog.Builder(main_menu.this);
                builder.setMessage("Are you sure to Exit?");
                builder.setCancelable(true);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.exit(0);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });





    }
/*
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(main_menu.this);
        builder.setMessage("Are you sure to do this?");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

}
