package com.example.abed.smit;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class SearchBloodActivity extends AppCompatActivity {


    private Button searchbtn;
    private EditText searchInput;
    String[] bloodg;
    private Spinner spinner1;
    private Spinner spinner_blood;

    private RecyclerView searchResulist;

    private DatabaseReference allusersDatabaseref;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_blood);




        searchResulist= findViewById(R.id.search_result_list);
        searchResulist.setHasFixedSize(true);
        searchResulist.setLayoutManager(new LinearLayoutManager(this));

        searchbtn=findViewById(R.id.search_people_btn);



        allusersDatabaseref= FirebaseDatabase.getInstance().getReference().child("Users");

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String searchBoxInput = spinner_blood.getSelectedItem().toString();
                SearchPropleAnddonnor(searchBoxInput);
            }
        });

        bloodg = getResources().getStringArray(R.array.blood_group);
        spinner_blood = (Spinner) findViewById(R.id.spinner_blood_group);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.blood_group, R.id.textView_Spinner_blood, bloodg);
        spinner_blood.setAdapter(adapter3);




    }

    private void SearchPropleAnddonnor(String searchBoxInput) {

        Toast.makeText(this,"Searching..",Toast.LENGTH_LONG).show();


        Query searchpeopleBloodquery=allusersDatabaseref.orderByChild("blood")
                .startAt(searchBoxInput).endAt(searchBoxInput+"\uf8ff");


        FirebaseRecyclerAdapter<FindBloods,FindBloodViewHolder>firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<FindBloods, FindBloodViewHolder>
                        (
                                FindBloods.class,
                                R.layout.all_user_display_layout,
                                FindBloodViewHolder.class,
                                searchpeopleBloodquery
                        ) {
                    @Override
                    protected void populateViewHolder(FindBloodViewHolder viewHolder, FindBloods model, int position) {
                        viewHolder.setName(model.getName());
                        viewHolder.setBlood(model.getBlood());
                        viewHolder.setArea(model.getArea());
                        viewHolder.setDistrict(model.getDistrict());
                        viewHolder.setAge(model.getAge());
                        viewHolder.setMobile(model.getMobile());
                        viewHolder.setGender(model.getGender());
                        viewHolder.setEmail(model.getEmail());
                        viewHolder.setDate(model.getDonateDate());
                    }
                };

            searchResulist.setAdapter(firebaseRecyclerAdapter);

    }

    public static class FindBloodViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public FindBloodViewHolder(View itemView ) {
            super(itemView);
            mView=itemView;
        }

        public void setName(String name){

            TextView myname=(TextView)mView.findViewById(R.id.all_user_profile_name);
            myname.setText(name);

        }

        public void setBlood(String blood){

            TextView mystatus=(TextView)mView.findViewById(R.id.all_user_profile_status);
            mystatus.setText(blood);

        }

        public void setArea(String area){

            TextView mystatus=(TextView)mView.findViewById(R.id.all_user_profile_area);
            mystatus.setText(area);

        }

        public void setDistrict(String district){

            TextView mystatus=(TextView)mView.findViewById(R.id.all_user_profile_district);
            mystatus.setText(district);

        }

        public void setAge(String age){

            TextView mystatus=(TextView)mView.findViewById(R.id.all_user_profile_age);
            mystatus.setText(age);

        }

        public void setMobile(String mobile){

            TextView mystatus=(TextView)mView.findViewById(R.id.all_user_profile_mobile);
            mystatus.setText(mobile);

        }

        public void setGender(String gender){

            TextView mystatus=(TextView)mView.findViewById(R.id.all_user_profile_gender);
            mystatus.setText(gender);

        }

        public void setEmail(String email){

            TextView mystatus=(TextView)mView.findViewById(R.id.all_user_profile_email);
            mystatus.setText(email);

        }

        public void setDate(String donateDate){

            TextView mystatus=(TextView)mView.findViewById(R.id.all_user_profile_date);
            mystatus.setText(donateDate);

        }

    }
}
