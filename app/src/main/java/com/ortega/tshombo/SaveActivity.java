package com.ortega.tshombo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;
import com.ortega.tshombo.models.TelephoneModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SaveActivity extends AppCompatActivity {

    MaterialToolbar materialToolbar;
    TextInputEditText input_nom, input_prix, input_desc, input_photo, input_marque;
    MaterialButton save_btn;
    TextView delete;

    String mag = "-NTECD_NroCtQeUsIhXv";

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        initialize();

        String uuid = getIntent().getStringExtra("id");
        String idMagasin = getIntent().getStringExtra("idMagasin");
        String nom = getIntent().getStringExtra("nom");
        String marque = getIntent().getStringExtra("marque");
        String prix = getIntent().getStringExtra("prix");
        String description = getIntent().getStringExtra("description");
        String photo = getIntent().getStringExtra("photo");

        if (!Objects.equals(uuid, "")) {

            input_nom.setText(nom);
            input_nom.setTextColor(Color.WHITE);
            input_marque.setText(marque);
            input_marque.setTextColor(Color.WHITE);
            input_prix.setText(prix);
            input_prix.setTextColor(Color.WHITE);
            input_desc.setText(description);
            input_desc.setTextColor(Color.WHITE);
            input_photo.setText(photo);
            input_photo.setTextColor(Color.WHITE);

            save_btn.setText("Modifier");
            save_btn.setOnClickListener(view -> {
                if (input_marque.getText() != null
                        && input_prix.getText()!= null
                        && input_nom.getText() != null
                        && input_desc.getText() != null
                        && input_photo.getText() != null) {

                    TelephoneModel telephoneModel = new TelephoneModel(
                            idMagasin,
                            input_marque.getText().toString(),
                            input_nom.getText().toString(),
                            input_desc.getText().toString(),
                            input_prix.getText().toString(),
                            input_photo.getText().toString()
                    );

                    updatePhone(uuid, telephoneModel);

                }
            });

            delete.setVisibility(View.VISIBLE);

            delete.setOnClickListener(view -> {
                deletePhone(uuid);
            });

            materialToolbar = findViewById(R.id.toolBar);
            materialToolbar.setTitle("Modification");
            setSupportActionBar(materialToolbar);
            materialToolbar.setNavigationOnClickListener(view -> finish());

        } else {

            materialToolbar = findViewById(R.id.toolBar);
            materialToolbar.setTitle("Ajouter");
            setSupportActionBar(materialToolbar);
            materialToolbar.setNavigationOnClickListener(view -> finish());

            input_nom.setTextColor(Color.WHITE);
            input_prix.setTextColor(Color.WHITE);
            input_marque.setTextColor(Color.WHITE);
            input_desc.setTextColor(Color.WHITE);
            input_photo.setTextColor(Color.WHITE);


            save_btn.setOnClickListener(view -> {
                if (input_marque.getText() != null
                        && input_prix.getText()!= null
                        && input_nom.getText() != null
                        && input_desc.getText() != null
                        && input_photo.getText() != null) {

                    TelephoneModel telephoneModel = new TelephoneModel(
                            idMagasin,
                            input_marque.getText().toString(),
                            input_nom.getText().toString(),
                            input_desc.getText().toString(),
                            input_prix.getText().toString(),
                            input_photo.getText().toString()
                    );

                    savePhone(telephoneModel);
                }
            });

            save_btn.setText("Enregistrer");
            delete.setVisibility(View.GONE);


        }





    }

    private void deletePhone(String uuid) {
        FirebaseDatabase.getInstance().getReference("telephones").child(uuid).removeValue();
        finish();
        Toast.makeText(this, "Delete sucessfully", Toast.LENGTH_SHORT).show();
    }

    private void updatePhone(String uuid, TelephoneModel telephoneModel) {

        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("description", telephoneModel.getDescription());
        updatedData.put("idMagasin", telephoneModel.getIdMagasin());
        updatedData.put("marque", telephoneModel.getMarque());
        updatedData.put("nom", telephoneModel.getNom());
        updatedData.put("photo", telephoneModel.getPhoto());
        updatedData.put("prix", telephoneModel.getPrix());

        FirebaseDatabase.getInstance().getReference("telephones").child(uuid).updateChildren(updatedData);
        finish();

        Toast.makeText(this, "Update sucessfully", Toast.LENGTH_SHORT).show();
    }

    private void savePhone(TelephoneModel telephoneModel) {

        Map<String, Object> saveData = new HashMap<>();
        saveData.put("description", telephoneModel.getDescription());
        saveData.put("idMagasin", mag);
        saveData.put("marque", telephoneModel.getMarque());
        saveData.put("nom", telephoneModel.getNom());
        saveData.put("photo", telephoneModel.getPhoto());
        saveData.put("prix", telephoneModel.getPrix());

        FirebaseDatabase.getInstance().getReference("telephones").push().setValue(saveData);
        finish();

        Toast.makeText(this, "Add sucessfully", Toast.LENGTH_SHORT).show();
    }

    protected void initialize() {
        input_nom = findViewById(R.id.input_nom);
        input_prix = findViewById(R.id.input_prix);
        input_desc = findViewById(R.id.input_desc);
        input_marque = findViewById(R.id.input_marque);

        input_photo = findViewById(R.id.input_photo);
        save_btn = findViewById(R.id.save_btn);
        delete = findViewById(R.id.delete);
    }
}