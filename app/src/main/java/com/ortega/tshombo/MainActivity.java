package com.ortega.tshombo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ortega.tshombo.models.MagasinModel;
import com.ortega.tshombo.models.TelephoneModel;
import com.ortega.tshombo.models.UserModel;
import com.ortega.tshombo.services.MagasinService;
import com.ortega.tshombo.services.TelephoneService;
import com.ortega.tshombo.services.UserService;
import com.ortega.tshombo.utils.FIrebaseUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

    }


    private void initialize() {

        TelephoneModel telephoneModel = new TelephoneModel("-NSNVIL1J8ECkSQueEHE", "Iphone", "Iphone 14 pro Max", "12Gb RAM, 12Mpx, 256Gb Stockage", "2800000", "https://www.ipstore.fr/9783-large_default/iphone-14-pro-max.jpg");
        TelephoneService telephoneService = new TelephoneService();

        telephoneService.save(telephoneModel);

    }

}