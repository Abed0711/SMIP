package com.example.abed.smit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abed.smit.FindMedicine;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FindMedicineActivity extends AppCompatActivity {

    private Button SearchButton_medicine;
    private EditText SearchInput_medicine;

    private RecyclerView SearchResult_medicine;

    private DatabaseReference allDocdatabaseref;

    FirebaseRecyclerAdapter<FindMedicine, FindMedicineViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_medicine);

        SearchResult_medicine = (RecyclerView) findViewById(R.id.search_result_list2);
        SearchResult_medicine.setHasFixedSize(true);
        SearchResult_medicine.setLayoutManager(new LinearLayoutManager(this));

        SearchButton_medicine = findViewById(R.id.search_people_btn2);
        SearchInput_medicine = (EditText) findViewById(R.id.Search_box_input2);

        allDocdatabaseref = FirebaseDatabase.getInstance().getReference().child("MEDICINE");


        SearchButton_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String searchBoxInput1 = SearchInput_medicine.getText().toString().trim();

                SearchMedicine(searchBoxInput1);


            }
        });
    }

    private void SearchMedicine(String searchBoxInput1) {

        Toast.makeText(this, "searching..", Toast.LENGTH_LONG).show();

        Query searchmedicinequery = allDocdatabaseref.orderByChild("batch").startAt(searchBoxInput1).endAt(searchBoxInput1 + "\uf8ff");

        firebaseRecyclerAdapter
                = new FirebaseRecyclerAdapter<FindMedicine, FindMedicineViewHolder>(
                FindMedicine.class,
                R.layout.all_medicine_display_layout,
                FindMedicineViewHolder.class,
                searchmedicinequery

        ) {
            @Override
            protected void populateViewHolder(FindMedicineViewHolder viewHolder, FindMedicine model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setCompany(model.getCompany());
                viewHolder.setEach(model.getEach());
                viewHolder.setMfgdate(model.getMfgdate());
                viewHolder.setExpdate(model.getExpdate());
                viewHolder.setGram(model.getGram());
            }
        };
        SearchResult_medicine.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    public static class FindMedicineViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public FindMedicineViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }


        public void setName(String name) {
            TextView Docname = mView.findViewById(R.id.all_user_profile_name2);
            Docname.setText(name);
        }
        public void setCompany(String company) {
            TextView Docname = mView.findViewById(R.id.all_user_profile_company2);
            Docname.setText(company);
        }

        public void setGram(String gram) {
            TextView Docname = mView.findViewById(R.id.all_user_profile_gram2);
            Docname.setText(gram);
        }

        public void setMfgdate(String mfgdate) {
            TextView Docname = mView.findViewById(R.id.all_user_profile_mfddate2);
            Docname.setText(mfgdate);
        }

        public void setExpdate(String expdate) {
            TextView Docname = mView.findViewById(R.id.all_user_profile_expdate2);
            Docname.setText(expdate);
        }

        public void setEach(String each) {
            TextView Docname = mView.findViewById(R.id.all_user_profile_Each2);
            Docname.setText(each);
        }

    }

}