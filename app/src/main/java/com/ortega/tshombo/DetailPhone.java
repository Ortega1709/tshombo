package com.ortega.tshombo;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailPhone extends AppCompatActivity {

    MaterialToolbar materialToolbar;
    LinearLayout goMaps;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_phone);

        String phoneName = getIntent().getStringExtra("name");
        String phoneUuid = getIntent().getStringExtra("uuid");

        // data initialize
        dataInitialize(phoneUuid);

        // setup tool bar
        materialToolbar = findViewById(R.id.toolBar);
        materialToolbar.setTitle(phoneName);
        setSupportActionBar(materialToolbar);

        // setup go maps
        goMaps = findViewById(R.id.go);

        // go back
        materialToolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        // go maps
        goMaps.setOnClickListener(v -> {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        });

    }

    protected void dataInitialize(String id) {

        databaseReference = FirebaseDatabase.getInstance().getReference("telephones").child(id);
        System.out.println(databaseReference.getKey());

    }
}