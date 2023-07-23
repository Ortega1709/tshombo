package com.ortega.tshombo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ortega.tshombo.adapters.PhoneAdapter;
import com.ortega.tshombo.models.TelephoneModel;

import java.util.ArrayList;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    SearchView searchView;
    PhoneAdapter phoneAdapter;
    ArrayList<TelephoneModel> telephoneArrayList;
    DatabaseReference databaseReference;

    TextView not_found;
    RecyclerView recyclerView;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        dataInitialize();
        initialize();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredList(newText);
                return false;
            }
        });

        recyclerView = findViewById(R.id.recyclerViewPhone);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        phoneAdapter = new PhoneAdapter(this, telephoneArrayList, telephoneModel -> {
            Intent intent = new Intent(this, DetailPhone.class);
            intent.putExtra("marque", telephoneModel.getMarque());
            intent.putExtra("uuid", telephoneModel.getId());
            startActivity(intent);
        });

        recyclerView.setAdapter(phoneAdapter);
        recyclerView.setClickable(true);

        phoneAdapter.notifyDataSetChanged();

    }


    protected void initialize() {
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        not_found = findViewById(R.id.not_found);
    }

    private void dataInitialize() {

        telephoneArrayList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("telephones");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                telephoneArrayList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {

                    TelephoneModel telephoneModel = itemSnapshot.getValue(TelephoneModel.class);
                    assert telephoneModel != null;
                    telephoneModel.setId(itemSnapshot.getKey());
                    System.out.println("[id] " + itemSnapshot.getKey());
                    telephoneArrayList.add(telephoneModel);
                }
                phoneAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void filteredList(String text) {
        ArrayList<TelephoneModel> filteredList = new ArrayList<>();
        for (TelephoneModel telephoneModel: telephoneArrayList) {
            if (telephoneModel.getNom().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(telephoneModel);
            }
        }

        if (filteredList.isEmpty()) {
            //Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            phoneAdapter.setFilteredList(new ArrayList<>());
            not_found.setVisibility(View.VISIBLE);
        } else {
            not_found.setVisibility(View.INVISIBLE);
            phoneAdapter.setFilteredList(filteredList);
        }
    }


}