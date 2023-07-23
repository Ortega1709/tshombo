package com.ortega.tshombo;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ortega.tshombo.models.MagasinModel;
import com.ortega.tshombo.models.TelephoneModel;

import java.util.Objects;

public class DetailPhone extends AppCompatActivity {

    MaterialToolbar materialToolbar;
    TextView phoneModele, phonePrice, phoneDesc;

    TextView magasinName, magasinAddress;

    LinearLayout goMaps;

    String localization;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_phone);

        // init
        initialize();


        String phoneMarque = getIntent().getStringExtra("marque");
        String phoneUuid = getIntent().getStringExtra("uuid");

        // data initialize
        dataInitialize(phoneUuid);

        // setup tool bar
        materialToolbar = findViewById(R.id.toolBar);
        materialToolbar.setTitle(phoneMarque);
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
            intent.putExtra("localization", localization);
            intent.putExtra("magasin", magasinName.getText());
            startActivity(intent);
        });

    }

    protected void initialize() {
        phoneModele = findViewById(R.id.phoneMarque);
        phonePrice = findViewById(R.id.phonePrice);
        phoneDesc = findViewById(R.id.phoneDesc);

        magasinName = findViewById(R.id.magasinName);
        magasinAddress = findViewById(R.id.magasinAddress);
    }

    protected void dataInitialize(String id) {

        databaseReference = FirebaseDatabase.getInstance().getReference("telephones");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {

                    TelephoneModel telephoneModel = itemSnapshot.getValue(TelephoneModel.class);
                    assert telephoneModel != null;
                    telephoneModel.setId(itemSnapshot.getKey());

                    if (telephoneModel.getId().equals(id)) {
                        phoneModele.setText(telephoneModel.getNom());
                        phonePrice.setText(telephoneModel.getPrix() + " Fc");
                        phoneDesc.setText(telephoneModel.getDescription());

                        dataInitializeMagasin(telephoneModel.getIdMagasin());
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    protected void dataInitializeMagasin(String id) {

        databaseReference = FirebaseDatabase.getInstance().getReference("magasins");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot: snapshot.getChildren()) {

                    MagasinModel magasinModel = itemSnapshot.getValue(MagasinModel.class);
                    assert magasinModel != null;
                    magasinModel.setId(itemSnapshot.getKey());

                    if (magasinModel.getId().equals(id)) {
                        magasinName.setText(magasinModel.getNom());
                        magasinAddress.setText(magasinModel.getAdresse());

                        localization = magasinModel.getMap();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}