package com.ortega.tshombo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ortega.tshombo.models.TelephoneModel;
import com.ortega.tshombo.services.TelephoneService;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initialize();

    }


    private void initialize() {

        TelephoneService telephoneService = new TelephoneService();
        TelephoneModel telephoneModel = new TelephoneModel(
                "",
                "Samsung",
                "Samsung galaxy s21",
                "Samsung galaxy s21 32Gb RAM, 512Gb",
                250.000);

        telephoneService.delete("-NSIut0kUZSl-_TWg2XD");
    }

}