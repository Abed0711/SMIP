package com.example.abed.smit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.w3c.dom.Text;

public class  FindDocActivity extends AppCompatActivity {

    private Button SearchButton_doc;
    private EditText SearchInput_doc;

    private RecyclerView SearchResult_doc;

    private DatabaseReference allDocdatabaseref;

    FirebaseRecyclerAdapter<FindDoctors, FindDoctorsViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doc);

        SearchResult_doc = (RecyclerView) findViewById(R.id.search_result_list1);
        SearchResult_doc.setHasFixedSize(true);
        SearchResult_doc.setLayoutManager(new LinearLayoutManager(this));

        SearchButton_doc =  findViewById(R.id.search_people_btn1);
        SearchInput_doc = (EditText) findViewById(R.id.Search_box_input1);

        allDocdatabaseref = FirebaseDatabase.getInstance().getReference().child("BMDC");


        SearchButton_doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchBoxInput1 = SearchInput_doc.getText().toString().trim();

                SearchDoc(searchBoxInput1);


            }
        });


    }

    private void SearchDoc(String searchBoxInput1) {
        Toast.makeText(this, "searching..", Toast.LENGTH_LONG).show();

        Query searchdoctorquery = allDocdatabaseref.orderByChild("bmdc_no").startAt(searchBoxInput1).endAt(searchBoxInput1 + "\uf8ff");


        firebaseRecyclerAdapter
                = new FirebaseRecyclerAdapter<FindDoctors, FindDoctorsViewHolder>(
                FindDoctors.class,
                R.layout.all_userdoc_display_layout,
                FindDoctorsViewHolder.class,
                searchdoctorquery

        ) {
            @Override
            protected void populateViewHolder(FindDoctorsViewHolder viewHolder, FindDoctors model, int position) {
                /// Toast.makeText(getApplicationContext(),model.getName().toString(), Toast.LENGTH_LONG).show();

                viewHolder.setName(model.getName());
                viewHolder.setGender(model.getGender());
                viewHolder.setCareer(model.getCareer());
                viewHolder.setPhone_num(model.getPhone_num());
                viewHolder.setDegree(model.getDegree());
                viewHolder.setAge(model.getAge());
                viewHolder.setCource(model.getCource());
                viewHolder.setSpecialist(model.getSpecialist());
            }
        };
        SearchResult_doc.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    public static class FindDoctorsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public FindDoctorsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }


        public void setName(String name) {
            TextView Docname = mView.findViewById(R.id.all_user_profile_name1);
            Docname.setText(name);
        }

        public void setGender(String gender) {
            TextView Docname = mView.findViewById(R.id.all_user_profile_status1);
            Docname.setText(gender);
        }

        public void setCareer(String career) {
            TextView Docname = mView.findViewById(R.id.all_user_profile_career1);
            Docname.setText(career);
        }

        public void setPhone_num(String phone_num) {
            TextView Docname = mView.findViewById(R.id.all_user_profile_phone1);
            Docname.setText(phone_num);
        }

        public void setDegree(String degree) {
            TextView Docname = mView.findViewById(R.id.all_user_profile_Degree1);
            Docname.setText(degree);
        }

        public void setAge(String age) {
            TextView Docname = mView.findViewById(R.id.all_user_profile_age1);
            Docname.setText(age);
        }

        public void setCource(String cource) {
            TextView Docname = mView.findViewById(R.id.all_user_profile_cource1);
            Docname.setText(cource);
        }

        public void setSpecialist(String specialist) {
            TextView Docname = mView.findViewById(R.id.all_user_profile_specialist1);
            Docname.setText(specialist);
        }




    }
}